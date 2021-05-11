package com.ckunion.modules.materials.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.modules.materials.entity.BasMaterialInfo;
import com.ckunion.modules.materials.mapper.BasMaterialInfoMapper;
import com.ckunion.modules.materials.service.IBasMaterialInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 物料信息维护
 * @Author: jeecg-boot
 * @Date:   2020-10-26
 * @Version: V1.0
 */
@Service
public class BasMaterialInfoServiceImpl extends ServiceImpl<BasMaterialInfoMapper, BasMaterialInfo> implements IBasMaterialInfoService {

    @Autowired
    private BasMaterialInfoMapper basMaterialInfoMapper;

    @Override
    public Map<String, Object> getMaxCode(String typeoid) {
        return basMaterialInfoMapper.getMaxCode(typeoid);
    }
}
