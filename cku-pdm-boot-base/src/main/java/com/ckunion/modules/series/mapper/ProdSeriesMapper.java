package com.ckunion.modules.series.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.materials.entity.BasMaterialInfo;
import org.apache.ibatis.annotations.Param;
import com.ckunion.modules.series.entity.ProdSeries;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: prod_series
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
public interface ProdSeriesMapper extends BaseMapper<ProdSeries> {

    /**
     * 根据分指标id查询以配置物料
     * @return
     */
    public String getMaxSeriesNum();


    /**
     * 获取最大编码值
     * @return
     */
    @Select("SELECT info.*  FROM PROD_SERIES_MATERIAL  gx left join bas_material_info info on info.id=gx.material_id ${ew.customSqlSegment}  ")
    public IPage<BasMaterialInfo> getMaterialBySeries(Page<?> page, @Param(Constants.WRAPPER) QueryWrapper<BasMaterialInfo> queryWrapper);
}
