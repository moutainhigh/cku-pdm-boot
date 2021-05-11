package com.ckunion.modules.base.type.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.type.entity.BasType;

import java.util.Map;

/**
 * @Description: 基础类型表
 * @Author: jeecg-boot
 * @Date:   2021-03-11
 * @Version: V1.0
 */
public interface IBasTypeService extends IService<BasType> {

    public Map<String,Object> getMaxCode(String pid);

    public Map<String,Object> getMaxCodeByTable(String table);
}
