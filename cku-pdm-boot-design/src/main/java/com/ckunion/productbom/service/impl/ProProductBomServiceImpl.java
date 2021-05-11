package com.ckunion.productbom.service.impl;

import com.ckunion.constant.ProConstant;
import com.ckunion.productbom.entity.ProProductBom;
import com.ckunion.productbom.mapper.ProProductBomMapper;
import com.ckunion.productbom.service.IProProductBomService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: pro_product_bom
 * @Author: jeecg-boot
 * @Date:   2021-04-13
 * @Version: V1.0
 */
@Slf4j
@Service
public class ProProductBomServiceImpl extends ServiceImpl<ProProductBomMapper, ProProductBom> implements IProProductBomService {

    @Autowired
    private ProProductBomMapper proProductBomMapper;

    @Override
    public List<ProProductBom> getChildBomByParent(String parentoid, String[] partType) {
        return proProductBomMapper.getChildBomByParent(parentoid,partType);
    }

    @Override
    public ProProductBom getBomByChildoid(String childoid) {
        return proProductBomMapper.getBomByChildoid(childoid);
    }
}
