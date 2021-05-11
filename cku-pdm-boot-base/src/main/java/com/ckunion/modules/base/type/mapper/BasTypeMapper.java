package com.ckunion.modules.base.type.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.type.entity.BasType;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Description: 基础类型表
 * @Author: jeecg-boot
 * @Date:   2021-03-11
 * @Version: V1.0
 */
public interface BasTypeMapper extends BaseMapper<BasType> {

    public Map<String,Object> getMaxCode(@Param("pid") String pid);

    public Map<String,Object> getMaxCodeByTable(@Param("table")String table);

}
