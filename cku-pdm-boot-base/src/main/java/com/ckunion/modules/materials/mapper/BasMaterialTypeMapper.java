package com.ckunion.modules.materials.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.materials.entity.BasMaterialType;
import com.ckunion.modules.materials.vo.BasMaterialTypeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: bas_material_type
 * @Author: jeecg-boot
 * @Date:   2020-10-16
 * @Version: V1.0
 */
public interface BasMaterialTypeMapper extends BaseMapper<BasMaterialType> {

	/**
	 * 编辑节点状态
	 * @param id
	 * @param status
	 */
	void updateTreeNodeStatus(@Param("id") String id,@Param("status") String status);

	/**
	 * 查询附加字段值并按格式返回
	 * @param id
	 */
	List<BasMaterialTypeVO> ExtPage(@Param("menu_id") String id);

	/**
	 * 查询兄弟节点的信息用来添加新信息
	 */
	Map queryBroConfig(@Param("pid") String pid);
}
