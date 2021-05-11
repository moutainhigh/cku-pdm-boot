/*
* @(#)WFService.java	1.00 2016-11-18
*
* 中江联合 2016，版权所有.
*/
package com.ckunion.activiti.service;

import java.util.Map;

import com.ckunion.activiti.vo.ActModel;

/**
 * <dl>
 * <dt>de-mes5</dt>
 * <dd>Description: 工作流审批接口</dd>
 * <dd>Copyright: Copyright (c) 2016</dd>
 * <dd>Company: 中江联合</dd>
 * </dl>
 * @author 蒋建
 * @version 1.0
 */
public interface ActServiceI {
	/**
	 * 任务办理之前执行
	 * (比如说要在发起流程之前给默认参与者，就用到这个方法)
	 * @param actModel
	 * @return Map 
	 */
	public Map<String, Object> postCompleteTask(ActModel actModel);
	/**
	 * 任务办理成功之后 
	 * @param model
	 * @return
	 */
	public Boolean afterCompleteTask(ActModel actModel);
	/**
	 * 流程终止前执行的方法（业务service需要实现此方法，对流程终止时实际业务表进行逻辑处理）
	 * @param actModel
	 */
	public void postEndProcess(ActModel actModel);
	/**
	 * 获取工作流不同阶段需要的审批人员字符串  "admin,admin,admin,xxx"
	 * @param map
	 */
	public String getUsers(Map<String, Object> map);
}

