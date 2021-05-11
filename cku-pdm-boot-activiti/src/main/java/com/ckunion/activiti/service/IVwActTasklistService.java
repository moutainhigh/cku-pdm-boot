package com.ckunion.activiti.service;

import com.ckunion.activiti.entity.VwActTasklist;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 我的任务
 * @Author: jeecg-boot
 * @Date:   2020-10-09
 * @Version: V1.0
 */
public interface IVwActTasklistService extends IService<VwActTasklist> {

    public Map<String,Object> getActUser();

}
