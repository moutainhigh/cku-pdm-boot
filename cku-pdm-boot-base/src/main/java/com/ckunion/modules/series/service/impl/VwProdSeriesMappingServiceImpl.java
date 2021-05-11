package com.ckunion.modules.series.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.modules.series.entity.VwProductSeriesMapping;
import com.ckunion.modules.series.mapper.VwProductSeriesMappingMapper;
import com.ckunion.modules.series.service.IVwProdSeriesMappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VwProdSeriesMappingServiceImpl extends ServiceImpl<VwProductSeriesMappingMapper, VwProductSeriesMapping> implements IVwProdSeriesMappingService {

}
