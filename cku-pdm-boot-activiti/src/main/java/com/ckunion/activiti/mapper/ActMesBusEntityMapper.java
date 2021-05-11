package com.ckunion.activiti.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.ckunion.activiti.entity.ActMesBusEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 工作流和业务对象关系表
 * @Author: jeecg-boot
 * @Date:   2020-08-04
 * @Version: V1.0
 */
public interface ActMesBusEntityMapper extends BaseMapper<ActMesBusEntity> {

	List<ActMesBusEntity> queryByProcInstIdAndBusinessKey(@Param("processInstanceId") String processInstanceId, @Param("businessKey") String businessKey);

	List<Map<String,String>> queryActModelSql(String sql);

}
