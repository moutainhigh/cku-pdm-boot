package com.ckunion.applyno.service.impl;

import com.ckunion.applyno.entity.ProApplyMapnoDt;
import com.ckunion.applyno.mapper.ProApplyMapnoDtMapper;
import com.ckunion.applyno.service.IProApplyMapnoDtService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: pro_apply_mapno_dt
 * @Author: jeecg-boot
 * @Date:   2021-04-23
 * @Version: V1.0
 */
@Service
public class ProApplyMapnoDtServiceImpl extends ServiceImpl<ProApplyMapnoDtMapper, ProApplyMapnoDt> implements IProApplyMapnoDtService {
	
	@Autowired
	private ProApplyMapnoDtMapper proApplyMapnoDtMapper;
	
	@Override
	public List<ProApplyMapnoDt> selectByMainId(String mainId) {
		return proApplyMapnoDtMapper.selectByMainId(mainId);
	}

	@Override
	public List<ProApplyMapnoDt> getApplyMapnoDtByParentoid(String mainId, String parentoid) {
		return proApplyMapnoDtMapper.getApplyMapnoDtByParentoid(mainId,parentoid);
	}
}
