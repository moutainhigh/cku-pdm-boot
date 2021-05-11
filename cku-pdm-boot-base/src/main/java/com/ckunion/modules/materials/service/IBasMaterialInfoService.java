package com.ckunion.modules.materials.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.materials.entity.BasMaterialInfo;

import java.util.Map;

/**
 * @Description: 物料信息维护
 * @Author: jeecg-boot
 * @Date:   2020-10-26
 * @Version: V1.0
 */
public interface IBasMaterialInfoService extends IService<BasMaterialInfo> {


    public Map<String,Object> getMaxCode(String typeoid);

}
