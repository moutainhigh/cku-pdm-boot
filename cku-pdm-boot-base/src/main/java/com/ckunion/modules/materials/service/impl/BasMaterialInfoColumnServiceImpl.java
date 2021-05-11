package com.ckunion.modules.materials.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.modules.materials.entity.BasMaterialInfoColumn;
import com.ckunion.modules.materials.mapper.BasMaterialInfoColumnMapper;
import com.ckunion.modules.materials.mapper.BasMaterialInfoMapper;
import com.ckunion.modules.materials.service.IBasMaterialInfoColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 物料信息维护
 * @Author: jeecg-boot
 * @Date:   2020-10-26
 * @Version: V1.0
 */
@Service
public class BasMaterialInfoColumnServiceImpl extends ServiceImpl<BasMaterialInfoColumnMapper, BasMaterialInfoColumn> implements IBasMaterialInfoColumnService {

    @Autowired
    private BasMaterialInfoColumnMapper basMaterialInfoColumnMapper;

}
