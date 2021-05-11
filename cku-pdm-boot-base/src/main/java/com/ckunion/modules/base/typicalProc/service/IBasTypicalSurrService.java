package com.ckunion.modules.base.typicalProc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalSurr;

import java.util.List;

/**
 * @Description: 工作环境要求从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface IBasTypicalSurrService extends IService<BasTypicalSurr> {

	public List<BasTypicalSurr> selectByMainId(String mainId);
}
