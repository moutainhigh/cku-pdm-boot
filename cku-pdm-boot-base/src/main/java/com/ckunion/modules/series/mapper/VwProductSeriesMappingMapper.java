package com.ckunion.modules.series.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.ckunion.modules.series.entity.VwProductSeriesMapping;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: vw_product_series_mapping
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
public interface VwProductSeriesMappingMapper extends BaseMapper<VwProductSeriesMapping> {

    /**
     * 根据型号ID获取型号系列配置属性
     * @param typeModelId
     * @return
     */
    public List<VwProductSeriesMapping> getSeriesMappingByTypeModelId(@Param("typeModelId") String typeModelId);
}
