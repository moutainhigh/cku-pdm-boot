package com.ckunion.productbom.service;

import com.ckunion.productbom.entity.VwProProductBom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: vw_pro_product_bom
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
public interface IVwProProductBomService extends IService<VwProProductBom> {
    public List<VwProProductBom> getAllVwBomListByParent(String parentId, String[] partType);
}
