package com.ckunion.modules.series.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.series.entity.ProdSeriesMapping;
import com.ckunion.modules.series.entity.VwProductSeriesMapping;
import com.ckunion.modules.series.vo.ProdSeriesMappingVo;

import java.util.List;

public interface IProdSeriesMappingService extends IService<ProdSeriesMapping> {

    public List<VwProductSeriesMapping> querySeriesColumn(String typeModelId);

    public void saveSeriesColumn(ProdSeriesMappingVo prodSeriesMappingVo);

}
