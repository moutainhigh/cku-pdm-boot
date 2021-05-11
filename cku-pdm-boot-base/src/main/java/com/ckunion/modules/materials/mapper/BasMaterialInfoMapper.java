package com.ckunion.modules.materials.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.materials.entity.BasMaterialInfo;

import java.util.Map;

/**
 * @Description: 物料信息维护
 * @Author: jeecg-boot
 * @Date:   2020-10-26
 * @Version: V1.0
 */
public interface BasMaterialInfoMapper extends BaseMapper<BasMaterialInfo> {

    public Map<String,Object> getMaxCode(String typeoid);

}
