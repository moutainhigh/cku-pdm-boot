package com.ckunion.activiti.mapper;

import java.util.List;
import java.util.Map;

import com.ckunion.activiti.entity.CkuActivitiList;
import org.apache.ibatis.annotations.Param;
import com.ckunion.activiti.entity.VwActTasklist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 我的任务
 * @Author: jeecg-boot
 * @Date:   2020-10-09
 * @Version: V1.0
 */
public interface VwActTasklistMapper extends BaseMapper<VwActTasklist> {


    /**
     * 根据流程编码获取流程列表实例
     * @param
     * @return
     */
    @Select("select id_,first_ from act_id_user ")
    public List<Map<String,Object>> getActUser();

}
