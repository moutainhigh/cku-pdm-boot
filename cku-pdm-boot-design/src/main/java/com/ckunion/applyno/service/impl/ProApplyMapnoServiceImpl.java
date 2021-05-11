package com.ckunion.applyno.service.impl;

import com.ckunion.applyno.entity.ProApplyMapno;
import com.ckunion.applyno.entity.ProApplyMapnoDt;
import com.ckunion.applyno.mapper.ProApplyMapnoDtMapper;
import com.ckunion.applyno.mapper.ProApplyMapnoMapper;
import com.ckunion.applyno.service.IProApplyMapnoService;
import com.ckunion.constant.ProConstant;
import com.ckunion.productbom.entity.ProProductBom;
import com.ckunion.productbom.entity.ProProductInfo;
import com.ckunion.productbom.mapper.ProProductBomMapper;
import com.ckunion.productbom.mapper.ProProductInfoMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: pro_apply_mapno
 * @Author: jeecg-boot
 * @Date:   2021-04-23
 * @Version: V1.0
 */
@Service
public class ProApplyMapnoServiceImpl extends ServiceImpl<ProApplyMapnoMapper, ProApplyMapno> implements IProApplyMapnoService {

	@Autowired
	private ProApplyMapnoMapper proApplyMapnoMapper;
	@Autowired
	private ProApplyMapnoDtMapper proApplyMapnoDtMapper;
	@Autowired
	private ProProductInfoMapper proProductInfoMapper;
	@Autowired
	private ProProductBomMapper proProductBomMapper;

	
	@Override
	@Transactional
	public void saveMain(ProApplyMapno proApplyMapno, List<ProApplyMapnoDt> proApplyMapnoDtList) {
		proApplyMapno.setState(ProConstant.PRO_APPLY_MAPNO_STATE_NOCHANGE);
		proApplyMapnoMapper.insert(proApplyMapno);
		if(proApplyMapnoDtList!=null && proApplyMapnoDtList.size()>0) {
			for(ProApplyMapnoDt entity:proApplyMapnoDtList) {
				//外键设置
				entity.setMasteroid(proApplyMapno.getId());
				proApplyMapnoDtMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void saveMapnoByProductApply(ProApplyMapno proApplyMapno, List<ProApplyMapnoDt> proApplyMapnoDtList){

		if(proApplyMapnoDtList!=null && proApplyMapnoDtList.size()>0) {
			for(ProApplyMapnoDt entity:proApplyMapnoDtList) {
				//1、先保存本表
				ProApplyMapnoDt dt = proApplyMapnoDtMapper.selectById(entity.getId());
				dt.setNodeCode(entity.getNodeCode());
				proApplyMapnoDtMapper.updateById(dt);

				//2、更新产品结构
				ProProductInfo info = proProductInfoMapper.selectById(dt.getChildoid());
				info.setMaterialCode(entity.getNodeCode());
				info.setMapNo(entity.getNodeCode());
				proProductInfoMapper.updateById(info);
				ProProductBom bom = proProductBomMapper.getBomByChildoid(dt.getChildoid());
				bom.setMapNo(entity.getNodeCode());
				proProductBomMapper.updateById(bom);

			}
			//3、更新主表状态
			proApplyMapno.setState(ProConstant.PRO_APPLY_MAPNO_STATE_CHANGE);
			proApplyMapnoMapper.updateById(proApplyMapno);

		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		proApplyMapnoDtMapper.deleteByMainId(id);
		proApplyMapnoMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			proApplyMapnoDtMapper.deleteByMainId(id.toString());
			proApplyMapnoMapper.deleteById(id);
		}
	}
	
}
