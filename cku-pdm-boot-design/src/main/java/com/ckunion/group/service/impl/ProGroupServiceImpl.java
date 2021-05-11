package com.ckunion.group.service.impl;

import com.ckunion.group.entity.ProGroup;
import com.ckunion.group.entity.ProGroupUser;
import com.ckunion.group.mapper.ProGroupUserMapper;
import com.ckunion.group.mapper.ProGroupMapper;
import com.ckunion.group.service.IProGroupService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 团队
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
@Service
public class ProGroupServiceImpl extends ServiceImpl<ProGroupMapper, ProGroup> implements IProGroupService {

	@Autowired
	private ProGroupMapper proGroupMapper;
	@Autowired
	private ProGroupUserMapper proGroupUserMapper;
	
	@Override
	@Transactional
	public void saveMain(ProGroup proGroup,List<ProGroupUser> proGroupUserList) {
		proGroupMapper.insert(proGroup);

		if(proGroupUserList!=null && proGroupUserList.size()>0) {
			for(ProGroupUser entity:proGroupUserList) {
				//外键设置
				entity.setMasteroid(proGroup.getId());
				proGroupUserMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(ProGroup proGroup,List<ProGroupUser> proGroupUserList) {
		proGroupMapper.updateById(proGroup);
		
		//1.先删除子表数据
		proGroupUserMapper.deleteByMainId(proGroup.getId());
		
		//2.子表数据重新插入
		if(proGroupUserList!=null && proGroupUserList.size()>0) {
			for(ProGroupUser entity:proGroupUserList) {
				//外键设置
				entity.setMasteroid(proGroup.getId());
				proGroupUserMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		proGroupUserMapper.deleteByMainId(id);
		proGroupMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			proGroupUserMapper.deleteByMainId(id.toString());
			proGroupMapper.deleteById(id);
		}
	}
	
}
