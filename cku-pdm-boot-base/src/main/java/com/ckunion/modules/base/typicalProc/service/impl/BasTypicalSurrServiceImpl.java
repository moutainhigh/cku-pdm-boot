package com.ckunion.modules.base.typicalProc.service.impl;

import com.ckunion.modules.base.typicalProc.entity.BasTypicalSurr;
import com.ckunion.modules.base.typicalProc.mapper.BasTypicalSurrMapper;
import com.ckunion.modules.base.typicalProc.service.IBasTypicalSurrService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 工作环境要求从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Service
public class BasTypicalSurrServiceImpl extends ServiceImpl<BasTypicalSurrMapper, BasTypicalSurr> implements IBasTypicalSurrService {
	
	@Autowired
	private BasTypicalSurrMapper basTypicalSurrMapper;
	
	@Override
	public List<BasTypicalSurr> selectByMainId(String mainId) {
		return basTypicalSurrMapper.selectByMainId(mainId);
	}
}
