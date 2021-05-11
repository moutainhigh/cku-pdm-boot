package com.ckunion.modules.materials.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.materials.entity.BasUnit;

/**
 * @Description: 计量单位维护
 * @Author: jeecg-boot
 * @Date:   2020-10-21
 * @Version: V1.0
 */
public interface IBasUnitService extends IService<BasUnit> {

    public String maxUnitCode(String pid);

}
