package com.ckunion.modules.base.node.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.node.entity.BasNodeType;

import java.util.Map;

/**
 * @Description: 节点类型
 * @Author: jeecg-boot
 * @Date:   2021-03-09
 * @Version: V1.0
 */
public interface IBasNodeTypeService extends IService<BasNodeType> {

    public Map<String,Object> getMaxTypeCode();


}
