package com.ckunion.activiti.service;

import com.ckunion.activiti.entity.CkuActivitiList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 流程列表
 * @Author: jeecg-boot
 * @Date:   2020-09-24
 * @Version: V1.0
 */
public interface ICkuActivitiListService extends IService<CkuActivitiList> {

	/**
	 * 根据流程编码获取流程列表实例
	 * @param wfkey
	 * @return
	 */
	CkuActivitiList getByActCode(String actCode);

}
