package com.ckunion.modules.base.typicalProc.service.impl;

import com.ckunion.modules.base.typicalProc.entity.BasTypicalTool;
import com.ckunion.modules.base.typicalProc.mapper.BasTypicalToolMapper;
import com.ckunion.modules.base.typicalProc.service.IBasTypicalToolService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 工装、用品从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Service
public class BasTypicalToolServiceImpl extends ServiceImpl<BasTypicalToolMapper, BasTypicalTool> implements IBasTypicalToolService {
	
	@Autowired
	private BasTypicalToolMapper basTypicalToolMapper;
	
	@Override
	public List<BasTypicalTool> selectByMainId(String mainId) {
		return basTypicalToolMapper.selectByMainId(mainId);
	}
}
