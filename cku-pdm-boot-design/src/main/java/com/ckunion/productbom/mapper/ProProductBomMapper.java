package com.ckunion.productbom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.ckunion.productbom.entity.ProProductBom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: pro_product_bom
 * @Author: jeecg-boot
 * @Date:   2021-04-13
 * @Version: V1.0
 */
public interface ProProductBomMapper extends BaseMapper<ProProductBom> {

    public List<ProProductBom> getChildBomByParent(@Param("parentoid") String parentoid, @Param("partType") String[] partType);

    public boolean deleteBomByChildoid(@Param("childoid") String childoid);

    /**
     * 根据info表id，获取bom关系
     * @param childoid
     * @return
     */
    public ProProductBom getBomByChildoid(@Param("childoid") String childoid);


    /**
     * 获取所有子节点
     * @param parentoid
     * @return
     */
    public List<ProProductBom> getAllChildBomByParent(@Param("parentoid") String parentoid);
}
