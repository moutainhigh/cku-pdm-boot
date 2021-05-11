package com.ckunion.modules.base.typicalProc.service.impl;

import com.ckunion.modules.base.typicalProc.entity.BasTypicalAttent;
import com.ckunion.modules.base.typicalProc.mapper.BasTypicalAttentMapper;
import com.ckunion.modules.base.typicalProc.service.IBasTypicalAttentService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 注意事项从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Service
public class BasTypicalAttentServiceImpl extends ServiceImpl<BasTypicalAttentMapper, BasTypicalAttent> implements IBasTypicalAttentService {
	
	@Autowired
	private BasTypicalAttentMapper basTypicalAttentMapper;
	
	@Override
	public List<BasTypicalAttent> selectByMainId(String mainId) {
		return basTypicalAttentMapper.selectByMainId(mainId);
	}
}
