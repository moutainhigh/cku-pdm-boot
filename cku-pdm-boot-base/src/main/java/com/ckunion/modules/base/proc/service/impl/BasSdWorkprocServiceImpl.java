package com.ckunion.modules.base.proc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.modules.base.proc.entity.BasSdWorkproc;
import com.ckunion.modules.base.proc.mapper.BasSdWorkprocMapper;
import com.ckunion.modules.base.proc.service.IBasSdWorkprocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 标准工序
 * @Author: jeecg-boot
 * @Date:   2021-03-22
 * @Version: V1.0
 */
@Service
public class BasSdWorkprocServiceImpl extends ServiceImpl<BasSdWorkprocMapper, BasSdWorkproc> implements IBasSdWorkprocService {

    @Autowired
    private BasSdWorkprocMapper basSdWorkprocMapper;

    @Override
    public Map<String, Object> getMaxTypeCode() {
        return basSdWorkprocMapper.getMaxTypeCode();
    }
}
