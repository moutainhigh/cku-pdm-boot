package com.ckunion.modules.materials.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.materials.entity.BasMaterialType;
import org.jeecg.common.exception.JeecgBootException;

import java.util.List;
import java.util.Map;

/**
 * @Description: bas_material_type
 * @Author: jeecg-boot
 * @Date:   2020-10-15
 * @Version: V1.0
 */
public interface IBasMaterialTypeService extends IService<BasMaterialType> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";
	
	/**树节点有子节点状态值*/
	public static final String HASCHILD = "1";
	
	/**树节点无子节点状态值*/
	public static final String NOCHILD = "0";

	/**新增节点*/
	void addBasMaterialType(BasMaterialType basMaterialType);
	
	/**修改节点*/
	void updateBasMaterialType(BasMaterialType basMaterialType) throws JeecgBootException;
	
	/**删除节点*/
	void deleteBasMaterialType(String id) throws JeecgBootException;

	/**查询所有数据，无分页*/
    List<BasMaterialType> queryTreeListNoPage(QueryWrapper<BasMaterialType> queryWrapper);


	Map queryBroConfig(String pid);

	/**
	 * 查询该节点下所有底层的类型id
	 */
	public List<String> getAllFloorId(String id);

}
