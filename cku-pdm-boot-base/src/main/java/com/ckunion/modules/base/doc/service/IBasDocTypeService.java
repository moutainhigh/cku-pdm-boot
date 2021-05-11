package com.ckunion.modules.base.doc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.doc.entity.BasDocType;

import java.util.Map;

/**
 * @Description: 文档类型
 * @Author: jeecg-boot
 * @Date:   2021-03-10
 * @Version: V1.0
 */
public interface IBasDocTypeService extends IService<BasDocType> {

    public Map<String,Object> getMaxCode(String pid, int parentCodeLength);

}
