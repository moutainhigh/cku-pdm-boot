package com.ckunion.modules.base.typicalProc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalProcess;

import java.util.List;

/**
 * @Description: 操作过程从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface IBasTypicalProcessService extends IService<BasTypicalProcess> {

	public List<BasTypicalProcess> selectByMainId(String mainId);
}
