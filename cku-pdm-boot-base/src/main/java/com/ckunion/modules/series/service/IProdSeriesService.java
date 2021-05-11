package com.ckunion.modules.series.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.materials.entity.BasMaterialInfo;
import com.ckunion.modules.series.entity.ProdSeriesField;
import com.ckunion.modules.series.entity.ProdSeries;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.system.entity.SysUser;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: prod_series
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
public interface IProdSeriesService extends IService<ProdSeries> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ProdSeries prodSeries, Map<String,Object> fieldMap);
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ProdSeries prodSeries, Map<String,Object> fieldMap);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

	/**
	 * 根据型号动态获取自定义字段
	 * @param seriesId
	 * @param modelId
	 * @return
	 */
	public String getFieldByModelId(String seriesId,String modelId);

	/**
	 * 将产品指标添加产品结构BOM中，作为根使用
	 * @param ser
	 */
	public void addRootProductBom(ProdSeries ser);

	/**
	 * 根据分指标id查询以配置物料
	 * @param ser
	 */
	public IPage<BasMaterialInfo> getMaterialBySeries(Page<BasMaterialInfo> page, QueryWrapper<BasMaterialInfo> queryWrapper);
	
}
