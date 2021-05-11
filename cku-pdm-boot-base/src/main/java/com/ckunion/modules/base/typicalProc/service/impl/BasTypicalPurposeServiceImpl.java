package com.ckunion.modules.base.typicalProc.service.impl;

import com.ckunion.modules.base.typicalProc.entity.BasTypicalPurpose;
import com.ckunion.modules.base.typicalProc.mapper.BasTypicalPurposeMapper;
import com.ckunion.modules.base.typicalProc.service.IBasTypicalPurposeService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 目的和目标从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Service
public class BasTypicalPurposeServiceImpl extends ServiceImpl<BasTypicalPurposeMapper, BasTypicalPurpose> implements IBasTypicalPurposeService {
	
	@Autowired
	private BasTypicalPurposeMapper basTypicalPurposeMapper;
	
	@Override
	public List<BasTypicalPurpose> selectByMainId(String mainId) {
		return basTypicalPurposeMapper.selectByMainId(mainId);
	}
}
