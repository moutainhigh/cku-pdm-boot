package com.ckunion.modules.base.type.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.modules.base.type.entity.BasTypeCategory;
import com.ckunion.modules.base.type.mapper.BasTypeCategoryMapper;
import com.ckunion.modules.base.type.service.IBasTypeCategoryService;
import org.springframework.stereotype.Service;

/**
 * @Description: 产品类别
 * @Author: jeecg-boot
 * @Date:   2021-04-01
 * @Version: V1.0
 */
@Service
public class BasTypeCategoryServiceImpl extends ServiceImpl<BasTypeCategoryMapper, BasTypeCategory> implements IBasTypeCategoryService {

}
