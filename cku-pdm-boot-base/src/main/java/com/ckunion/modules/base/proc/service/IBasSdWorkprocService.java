package com.ckunion.modules.base.proc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.proc.entity.BasSdWorkproc;

import java.util.Map;

/**
 * @Description: 标准工序
 * @Author: jeecg-boot
 * @Date:   2021-03-22
 * @Version: V1.0
 */
public interface IBasSdWorkprocService extends IService<BasSdWorkproc> {

    public Map<String,Object> getMaxTypeCode();

}
