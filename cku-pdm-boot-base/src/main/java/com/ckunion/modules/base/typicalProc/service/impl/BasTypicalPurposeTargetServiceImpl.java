package com.ckunion.modules.base.typicalProc.service.impl;

import com.ckunion.modules.base.typicalProc.entity.BasTypicalPurposeTarget;
import com.ckunion.modules.base.typicalProc.mapper.BasTypicalPurposeTargetMapper;
import com.ckunion.modules.base.typicalProc.service.IBasTypicalPurposeTargetService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 目标从从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Service
public class BasTypicalPurposeTargetServiceImpl extends ServiceImpl<BasTypicalPurposeTargetMapper, BasTypicalPurposeTarget> implements IBasTypicalPurposeTargetService {
	
	@Autowired
	private BasTypicalPurposeTargetMapper basTypicalPurposeTargetMapper;
	
	@Override
	public List<BasTypicalPurposeTarget> selectByMainId(String mainId) {
		return basTypicalPurposeTargetMapper.selectByMainId(mainId);
	}
}
