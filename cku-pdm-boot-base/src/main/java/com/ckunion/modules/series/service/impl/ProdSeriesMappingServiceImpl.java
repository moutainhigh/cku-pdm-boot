package com.ckunion.modules.series.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.modules.materials.entity.BasMaterialInfoColumn;
import com.ckunion.modules.materials.service.IBasMaterialInfoColumnService;
import com.ckunion.modules.series.entity.ProdSeriesMapping;
import com.ckunion.modules.series.entity.VwProductSeriesMapping;
import com.ckunion.modules.series.mapper.ProdSeriesMappingMapper;
import com.ckunion.modules.series.mapper.VwProductSeriesMappingMapper;
import com.ckunion.modules.series.service.IProdSeriesMappingService;
import com.ckunion.modules.series.service.IVwProdSeriesMappingService;
import com.ckunion.modules.series.vo.ProdSeriesMappingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProdSeriesMappingServiceImpl extends ServiceImpl<ProdSeriesMappingMapper, ProdSeriesMapping> implements IProdSeriesMappingService {

    @Autowired
    private IBasMaterialInfoColumnService basMaterialInfoColumnService;

    @Autowired
    private IVwProdSeriesMappingService vwProdSeriesMappingService;

    @Override
    public List<VwProductSeriesMapping> querySeriesColumn(String typeModelId) {
        Integer count = this.query().eq("TYPE_MODEL_ID", typeModelId).count();
        if(count == 0){
            List<BasMaterialInfoColumn> columnList = basMaterialInfoColumnService.query().eq("is_series", "1").eq("is_product", "1").list();
            ArrayList<ProdSeriesMapping> seriesMappingList = new ArrayList<>();
            for (BasMaterialInfoColumn column : columnList) {
                ProdSeriesMapping seriesMapping = new ProdSeriesMapping();
                seriesMapping.setTypeModelId(typeModelId);
                seriesMapping.setInfoColumnId(column.getId());
                seriesMapping.setChecked("0");
                seriesMapping.setSeriesOrder(column.getOrderIndex());
                seriesMapping.setColumnLabel(column.getColumnLabel());
                seriesMapping.setColumnName(column.getColumnName());
                seriesMappingList.add(seriesMapping);
            }
            this.saveBatch(seriesMappingList);
        }

        List<VwProductSeriesMapping> list = vwProdSeriesMappingService.query().eq("TYPE_MODEL_ID", typeModelId).orderByAsc("SERIES_ORDER").list();

        return list;
    }

    @Override
    public void saveSeriesColumn(ProdSeriesMappingVo prodSeriesMappingVo) {
        List<String> checkIds = prodSeriesMappingVo.getCheckIds();

        String typeModelId = prodSeriesMappingVo.getTypeModelId();


        ArrayList<ProdSeriesMapping> updateMappingList = new ArrayList<>();
        int index = 0;
        for (String checkId : checkIds) {
            ProdSeriesMapping mapping = this.getById(checkId);
            mapping.setSeriesOrder(index);
            mapping.setChecked("1");
            updateMappingList.add(mapping);
            index++;
        }
        this.updateBatchById(updateMappingList);

    }
}
