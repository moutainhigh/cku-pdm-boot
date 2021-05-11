package com.ckunion.modules.base.node.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.node.entity.BasNodeType;

import java.util.Map;

/**
 * @Description: 节点类型
 * @Author: jeecg-boot
 * @Date:   2021-03-09
 * @Version: V1.0
 */
public interface BasNodeTypeMapper extends BaseMapper<BasNodeType> {

    public Map<String,Object> getMaxTypeCode();

}
