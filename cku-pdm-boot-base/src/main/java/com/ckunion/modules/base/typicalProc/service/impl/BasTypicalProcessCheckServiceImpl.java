package com.ckunion.modules.base.typicalProc.service.impl;

import com.ckunion.modules.base.typicalProc.entity.BasTypicalProcessCheck;
import com.ckunion.modules.base.typicalProc.mapper.BasTypicalProcessCheckMapper;
import com.ckunion.modules.base.typicalProc.service.IBasTypicalProcessCheckService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 操作过程检验方法从从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Service
public class BasTypicalProcessCheckServiceImpl extends ServiceImpl<BasTypicalProcessCheckMapper, BasTypicalProcessCheck> implements IBasTypicalProcessCheckService {
	
	@Autowired
	private BasTypicalProcessCheckMapper basTypicalProcessCheckMapper;
	
	@Override
	public List<BasTypicalProcessCheck> selectByMainId(String mainId) {
		return basTypicalProcessCheckMapper.selectByMainId(mainId);
	}
}
