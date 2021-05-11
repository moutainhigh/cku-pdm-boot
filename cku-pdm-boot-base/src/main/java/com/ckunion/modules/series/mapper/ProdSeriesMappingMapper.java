package com.ckunion.modules.series.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.series.entity.ProdSeriesMapping;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: prod_series
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
public interface ProdSeriesMappingMapper extends BaseMapper<ProdSeriesMapping> {


    public void resetMappingCheck(@Param("typeModelId") String typeModelId);

}
