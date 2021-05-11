package com.ckunion.activiti.service.impl;

import com.ckunion.activiti.entity.CkuActivitiList;
import com.ckunion.activiti.mapper.CkuActivitiListMapper;
import com.ckunion.activiti.service.ICkuActivitiListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 流程列表
 * @Author: jeecg-boot
 * @Date:   2020-09-24
 * @Version: V1.0
 */
@Service
public class CkuActivitiListServiceImpl extends ServiceImpl<CkuActivitiListMapper, CkuActivitiList> implements ICkuActivitiListService {
	@Autowired
	private CkuActivitiListMapper ckuActivitiListMapper;
	
	@Override
	public CkuActivitiList getByActCode(String actCode) {
		return ckuActivitiListMapper.getByActCode(actCode);
	}

}
