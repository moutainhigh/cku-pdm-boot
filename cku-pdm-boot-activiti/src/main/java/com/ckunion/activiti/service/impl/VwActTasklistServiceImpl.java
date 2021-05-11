package com.ckunion.activiti.service.impl;

import com.ckunion.activiti.entity.CkuActivitiList;
import com.ckunion.activiti.entity.VwActTasklist;
import com.ckunion.activiti.mapper.CkuActivitiListMapper;
import com.ckunion.activiti.mapper.VwActTasklistMapper;
import com.ckunion.activiti.service.IVwActTasklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 我的任务
 * @Author: jeecg-boot
 * @Date:   2020-10-09
 * @Version: V1.0
 */
@Service
public class VwActTasklistServiceImpl extends ServiceImpl<VwActTasklistMapper, VwActTasklist> implements IVwActTasklistService {

    @Autowired
    private VwActTasklistMapper vwActTasklistMapper;

    @Override
    public Map<String,Object> getActUser() {
        List<Map<String, Object>> actUser = vwActTasklistMapper.getActUser();
        Map<String,Object> map=new HashMap();
        for (Map<String, Object> stringObjectMap : actUser) {
            map.put(stringObjectMap.get("id_")+"",stringObjectMap.get("first_"));
        }

        return map;
    }

}
