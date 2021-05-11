package com.ckunion.modules.base.typicalProc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalProcessCheck;

import java.util.List;

/**
 * @Description: 操作过程检验方法从从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface IBasTypicalProcessCheckService extends IService<BasTypicalProcessCheck> {

	public List<BasTypicalProcessCheck> selectByMainId(String mainId);
}
