package com.ckunion.modules.base.typicalProc.service.impl;

import com.ckunion.modules.base.typicalProc.entity.BasTypicalMat;
import com.ckunion.modules.base.typicalProc.mapper.BasTypicalMatMapper;
import com.ckunion.modules.base.typicalProc.service.IBasTypicalMatService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 原材料、辅料、材料从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Service
public class BasTypicalMatServiceImpl extends ServiceImpl<BasTypicalMatMapper, BasTypicalMat> implements IBasTypicalMatService {
	
	@Autowired
	private BasTypicalMatMapper basTypicalMatMapper;
	
	@Override
	public List<BasTypicalMat> selectByMainId(String mainId) {
		return basTypicalMatMapper.selectByMainId(mainId);
	}
}
