package com.ckunion.activiti.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.ckunion.activiti.entity.CkuActivitiList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 流程列表
 * @Author: jeecg-boot
 * @Date:   2020-09-24
 * @Version: V1.0
 */
public interface CkuActivitiListMapper extends BaseMapper<CkuActivitiList> {

	/**
	 * 根据流程编码获取流程列表实例
	 * @param actCode
	 * @return
	 */
	CkuActivitiList getByActCode(@Param("actCode") String actCode);

}
