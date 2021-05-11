package com.ckunion.modules.base.type.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.modules.base.type.entity.BasType;
import com.ckunion.modules.base.type.mapper.BasTypeMapper;
import com.ckunion.modules.base.type.service.IBasTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 基础类型表
 * @Author: jeecg-boot
 * @Date:   2021-03-11
 * @Version: V1.0
 */
@Service
public class BasTypeServiceImpl extends ServiceImpl<BasTypeMapper, BasType> implements IBasTypeService {

    @Autowired
    private BasTypeMapper basTypeMapper;

    @Override
    public Map<String,Object> getMaxCode(String pid) {
        return basTypeMapper.getMaxCode(pid);
    }

    @Override
    public Map<String,Object> getMaxCodeByTable(String table) {
        return basTypeMapper.getMaxCodeByTable(table);
    }
}
