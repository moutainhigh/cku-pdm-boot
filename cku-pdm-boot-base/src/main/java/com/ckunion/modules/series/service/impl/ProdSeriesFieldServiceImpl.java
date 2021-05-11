package com.ckunion.modules.series.service.impl;

import com.ckunion.modules.series.entity.ProdSeriesField;
import com.ckunion.modules.series.mapper.ProdSeriesFieldMapper;
import com.ckunion.modules.series.service.IProdSeriesFieldService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: prod_series_field
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
@Service
public class ProdSeriesFieldServiceImpl extends ServiceImpl<ProdSeriesFieldMapper, ProdSeriesField> implements IProdSeriesFieldService {
	
	@Autowired
	private ProdSeriesFieldMapper prodSeriesFieldMapper;
	
	@Override
	public List<ProdSeriesField> selectByMainId(String mainId) {
		return prodSeriesFieldMapper.selectByMainId(mainId);
	}
}
