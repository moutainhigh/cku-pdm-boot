package com.ckunion.group.service.impl;

import com.ckunion.group.entity.ProGroupUser;
import com.ckunion.group.mapper.ProGroupUserMapper;
import com.ckunion.group.service.IProGroupUserService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 团队成员从表
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
@Service
public class ProGroupUserServiceImpl extends ServiceImpl<ProGroupUserMapper, ProGroupUser> implements IProGroupUserService {
	
	@Autowired
	private ProGroupUserMapper proGroupUserMapper;
	
	@Override
	public List<ProGroupUser> selectByMainId(String mainId) {
		return proGroupUserMapper.selectByMainId(mainId);
	}
}
