package com.ckunion.activiti.service;

import java.util.Map;

import com.ckunion.activiti.vo.ActModel;
import com.ckunion.activiti.vo.ActivitiVo;

/**	
 * 工作流业务接口
 * @author liut
 *
 */
public interface ActivitiService {
	/**
	 * 开启工作流
	 * @param processDefinitionKey ：工作流主键 （）
	 * @param businessKey  ： 业务主键
	 * @param variables    ： 工作流参数 
	 * @return  processInstanceId ： 流程启动实例ID，业务对象中存储
	 */
	//@Deprecated
	//public String startProcess(String processDefinitionKey, String businessKey, Map<String, Object> variables);
	
	/**
	 * 开启工作流
	 * @param vomap 
	 * @param variables
	 * @return
	 */
	public String startProcess(ActivitiVo vo, Map<String, Object> variables);
	/**
	 * 开启工作流（封装的标签是调用的这个方法）
	 * @param vomap 
	 * @param variables
	 * @return
	 */
	public void startProcess(ActModel actModel);
	/**
	 * 任务完成
	 * @param taskId ：任务ID
	 * @param variables ：工作流参数
	 * @param comment ：批注内容
	 */
	public void completeTask(String taskId, Map<String, Object> variables, String comment);
	/**
	 * 任务完成（封装的标签是调用的这个方法）
	 * @param actModel 包含完成任务所需要的所有数据
	 */
	public void completeTask(ActModel actModel);
	/**
	 * 根据任务id，查询相关的流程实例、业务key等关键数据
	 * @param taskId
	 * @return
	 */
	public ActModel queryActModel(String taskId);

	/**
	 * 会签数据查询 （ 取消用 ）
	 * @param taskId
	 * @return
	 */
	public ActModel queryHqActModel(String taskId);
	/**
	 * 任务签收
	 * @param taskId
	 */
	public void claim(String taskId);
	
	/**
	 * 根据流程定义对象ID获取业务主键
	 * @param processInstanceId
	 * @return
	 */
	public String getBusinessKey(String processInstanceId); 
	
	/**
	 * 转发
	 * @param taskId
	 * @param userCode
	 */
	public void transferAssignee(String taskId, String userCode);
	
	/**
	 * 流程终止
	 * @param actModel
	 * @throws Exception
	 */
	public void endProcess(ActModel actModel) throws Exception;
	
	//查询
	//挂起：如果某个流程暂时不用,则可以挂起,这样的话,就避免流程删除引
}
