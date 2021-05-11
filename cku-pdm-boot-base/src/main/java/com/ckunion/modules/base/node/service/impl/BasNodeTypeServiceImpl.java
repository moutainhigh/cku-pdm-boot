package com.ckunion.modules.base.node.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.modules.base.node.entity.BasNodeType;
import com.ckunion.modules.base.node.mapper.BasNodeTypeMapper;
import com.ckunion.modules.base.node.service.IBasNodeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 节点类型
 * @Author: jeecg-boot
 * @Date:   2021-03-09
 * @Version: V1.0
 */
@Service
public class BasNodeTypeServiceImpl extends ServiceImpl<BasNodeTypeMapper, BasNodeType> implements IBasNodeTypeService {


    @Autowired
    private BasNodeTypeMapper basNodeTypeMapper;

    @Override
    public Map<String, Object> getMaxTypeCode() {
        return basNodeTypeMapper.getMaxTypeCode();
    }
}
