package com.ckunion.modules.base.typicalProc.service.impl;

import com.ckunion.modules.base.typicalProc.entity.BasTypicalEqu;
import com.ckunion.modules.base.typicalProc.mapper.BasTypicalEquMapper;
import com.ckunion.modules.base.typicalProc.service.IBasTypicalEquService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 设备仪器从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Service
public class BasTypicalEquServiceImpl extends ServiceImpl<BasTypicalEquMapper, BasTypicalEqu> implements IBasTypicalEquService {
	
	@Autowired
	private BasTypicalEquMapper basTypicalEquMapper;
	
	@Override
	public List<BasTypicalEqu> selectByMainId(String mainId) {
		return basTypicalEquMapper.selectByMainId(mainId);
	}
}
