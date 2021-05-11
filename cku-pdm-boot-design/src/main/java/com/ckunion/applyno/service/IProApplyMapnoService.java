package com.ckunion.applyno.service;

import com.ckunion.applyno.entity.ProApplyMapnoDt;
import com.ckunion.applyno.entity.ProApplyMapno;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: pro_apply_mapno
 * @Author: jeecg-boot
 * @Date:   2021-04-23
 * @Version: V1.0
 */
public interface IProApplyMapnoService extends IService<ProApplyMapno> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ProApplyMapno proApplyMapno, List<ProApplyMapnoDt> proApplyMapnoDtList) ;
	
	/**
	 * 保存输入的图号，来源产品结构请求
	 * 
	 */
	public void saveMapnoByProductApply(ProApplyMapno proApplyMapno, List<ProApplyMapnoDt> proApplyMapnoDtList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

	
}
