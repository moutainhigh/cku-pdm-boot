package com.ckunion.modules.series.service;

import com.ckunion.modules.series.entity.ProdSeriesField;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: prod_series_field
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
public interface IProdSeriesFieldService extends IService<ProdSeriesField> {

	public List<ProdSeriesField> selectByMainId(String mainId);
}
