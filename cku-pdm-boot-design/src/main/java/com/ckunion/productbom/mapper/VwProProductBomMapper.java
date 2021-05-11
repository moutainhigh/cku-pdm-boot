package com.ckunion.productbom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.ckunion.productbom.entity.VwProProductBom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: vw_pro_product_bom
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
public interface VwProProductBomMapper extends BaseMapper<VwProProductBom> {

    public List<VwProProductBom> getAllVwBomListByParent(String parentId, String[] partType);
}
