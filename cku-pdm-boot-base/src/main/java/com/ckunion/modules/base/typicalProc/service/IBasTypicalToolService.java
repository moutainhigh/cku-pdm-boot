package com.ckunion.modules.base.typicalProc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalTool;

import java.util.List;

/**
 * @Description: 工装、用品从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface IBasTypicalToolService extends IService<BasTypicalTool> {

	public List<BasTypicalTool> selectByMainId(String mainId);
}
