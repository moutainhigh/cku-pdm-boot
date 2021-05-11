package com.ckunion.modules.base.typicalProc.service.impl;

import com.ckunion.modules.base.typicalProc.entity.BasTypicalProcess;
import com.ckunion.modules.base.typicalProc.mapper.BasTypicalProcessMapper;
import com.ckunion.modules.base.typicalProc.service.IBasTypicalProcessService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 操作过程从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Service
public class BasTypicalProcessServiceImpl extends ServiceImpl<BasTypicalProcessMapper, BasTypicalProcess> implements IBasTypicalProcessService {
	
	@Autowired
	private BasTypicalProcessMapper basTypicalProcessMapper;
	
	@Override
	public List<BasTypicalProcess> selectByMainId(String mainId) {
		return basTypicalProcessMapper.selectByMainId(mainId);
	}
}
