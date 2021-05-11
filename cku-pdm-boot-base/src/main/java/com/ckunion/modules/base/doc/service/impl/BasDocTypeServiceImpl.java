package com.ckunion.modules.base.doc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.modules.base.doc.entity.BasDocType;
import com.ckunion.modules.base.doc.mapper.BasDocTypeMapper;
import com.ckunion.modules.base.doc.service.IBasDocTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description: 文档类型
 * @Author: jeecg-boot
 * @Date:   2021-03-10
 * @Version: V1.0
 */
@Service
public class BasDocTypeServiceImpl extends ServiceImpl<BasDocTypeMapper, BasDocType> implements IBasDocTypeService {

    @Resource
    private BasDocTypeMapper basDocTypeMapper;

    @Override
    public Map<String, Object> getMaxCode(String pid, int parentCodeLength) {
        return basDocTypeMapper.getMaxCode(pid,parentCodeLength);
    }
}
