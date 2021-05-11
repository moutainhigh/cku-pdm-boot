package com.ckunion.modules.base.typicalProc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalPurposeTarget;

import java.util.List;

/**
 * @Description: 目标从从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface IBasTypicalPurposeTargetService extends IService<BasTypicalPurposeTarget> {

	public List<BasTypicalPurposeTarget> selectByMainId(String mainId);
}
