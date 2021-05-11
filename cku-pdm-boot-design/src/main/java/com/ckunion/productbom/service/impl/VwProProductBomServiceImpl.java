package com.ckunion.productbom.service.impl;

import com.ckunion.productbom.entity.VwProProductBom;
import com.ckunion.productbom.mapper.ProProductInfoMapper;
import com.ckunion.productbom.mapper.VwProProductBomMapper;
import com.ckunion.productbom.service.IVwProProductBomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: vw_pro_product_bom
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
@Service
public class VwProProductBomServiceImpl extends ServiceImpl<VwProProductBomMapper, VwProProductBom> implements IVwProProductBomService {

    @Autowired
    private VwProProductBomMapper vwProProductBomMapper;

    @Override
    public List<VwProProductBom> getAllVwBomListByParent(String parentId, String[] partType) {
        return vwProProductBomMapper.getAllVwBomListByParent(parentId, partType);
    }
}
