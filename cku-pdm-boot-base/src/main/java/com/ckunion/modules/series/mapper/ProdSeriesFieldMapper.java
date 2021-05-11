package com.ckunion.modules.series.mapper;

import java.util.List;
import com.ckunion.modules.series.entity.ProdSeriesField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: prod_series_field
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
public interface ProdSeriesFieldMapper extends BaseMapper<ProdSeriesField> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<ProdSeriesField> selectByMainId(@Param("mainId") String mainId);
}
