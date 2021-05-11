package com.ckunion.modules.materials.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.modules.materials.entity.BasUnit;
import com.ckunion.modules.materials.mapper.BasUnitMapper;
import com.ckunion.modules.materials.service.IBasUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 计量单位维护
 * @Author: jeecg-boot
 * @Date:   2020-10-21
 * @Version: V1.0
 */
@Service
public class BasUnitServiceImpl extends ServiceImpl<BasUnitMapper, BasUnit> implements IBasUnitService {
    @Autowired
    private BasUnitMapper basUnitMapper;

    public String maxUnitCode(String pid){
        return basUnitMapper.maxUnitCode(pid);
    }
}
