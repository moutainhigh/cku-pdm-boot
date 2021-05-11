package com.ckunion.productbom.service;

import com.ckunion.productbom.entity.ProProductBom;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: pro_product_bom
 * @Author: jeecg-boot
 * @Date:   2021-04-13
 * @Version: V1.0
 */
public interface IProProductBomService extends IService<ProProductBom> {

    public List<ProProductBom> getChildBomByParent(String parentoid, String[] partType);

    /**
     * 根据info表id，获取bom关系
     * @param childoid
     * @return
     */
    public ProProductBom getBomByChildoid(@Param("childoid") String childoid);
}
