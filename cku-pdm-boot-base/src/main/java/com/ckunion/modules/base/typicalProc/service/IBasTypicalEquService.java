package com.ckunion.modules.base.typicalProc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalEqu;

import java.util.List;

/**
 * @Description: 设备仪器从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface IBasTypicalEquService extends IService<BasTypicalEqu> {

	public List<BasTypicalEqu> selectByMainId(String mainId);
}
