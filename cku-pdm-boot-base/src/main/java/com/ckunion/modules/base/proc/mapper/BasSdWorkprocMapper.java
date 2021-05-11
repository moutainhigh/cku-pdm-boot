package com.ckunion.modules.base.proc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.proc.entity.BasSdWorkproc;

import java.util.Map;

/**
 * @Description: 标准工序
 * @Author: jeecg-boot
 * @Date:   2021-03-22
 * @Version: V1.0
 */
public interface BasSdWorkprocMapper extends BaseMapper<BasSdWorkproc> {

    public Map<String,Object> getMaxTypeCode();

}
