package com.ckunion.modules.base.typicalProc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalMat;

import java.util.List;

/**
 * @Description: 原材料、辅料、材料从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface IBasTypicalMatService extends IService<BasTypicalMat> {

	public List<BasTypicalMat> selectByMainId(String mainId);
}
