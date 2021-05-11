package com.ckunion.modules.base.typicalProc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalPurpose;

import java.util.List;

/**
 * @Description: 目的和目标从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface IBasTypicalPurposeService extends IService<BasTypicalPurpose> {

	public List<BasTypicalPurpose> selectByMainId(String mainId);
}
