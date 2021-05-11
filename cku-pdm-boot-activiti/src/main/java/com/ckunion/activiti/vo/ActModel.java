/**
 * @(#)WfAom.java	1.00 2016-11-18
 *
 * 中江联合 2016，版权所有.
 */
package com.ckunion.activiti.vo;

import java.io.Serializable;
import java.util.Map;

/**
 * <dl>
 * <dt>de-mes5</dt>
 * <dd>Description: 工作流相关属性、变量  实体类</dd >
 * <dd>Copyright: Copyright (c) 2016</dd >
 * <dd>Company: 中江联合</dd >
 * </dl>
 * 
 * @author Administrator
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ActModel  implements Serializable{
	public ActModel(String businessKey,String businessService, String processInstanceId, String taskId,
			String nodeId,String nodeName,String actResult, String actComment) {
		this.businessKey = businessKey;
		this.businessService=businessService;
		this.processInstanceId = processInstanceId;
		this.taskId = taskId;
		this.nodeId=nodeId;
		this.nodeName=nodeName;
		this.actResult=actResult;
		this.actComment=actComment;
	}

	public ActModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 业务ID
	 */
	private String businessKey;
	private String businessService;//service业务类注解到容器中的name
	private String processInstanceId;// 流程实例id
	private String taskId;// 工作项id
	private String nodeName;// 节点名称
	private String nodeId;// 节点ID
	private String actResult;// 审批结果
	private String actComment;// 审批内容、批注
	private String wfkey;//工作流id
	// 流程 流转中 所有的变量的Map
	private Map<String, Object> variables;
//	private String activityName;// 节点名称
//	private String activityId;// 节点定义ID
//	private String processName;// 流程名称
//	private String procState;// 流程状态
//	private String participant;// 当前参与者
//	private String participantDept;// 当前参与者的 归属部门
//	// 流程设置中 的 描述 信息
//	private String desc;
//    //	是否整个流程结束
//	private boolean isFinish=false;
	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getBusinessService() {
		return businessService;
	}

	public void setBusinessService(String businessService) {
		this.businessService = businessService;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getActResult() {
		return actResult;
	}

	public void setActResult(String actResult) {
		this.actResult = actResult;
	}

	public String getActComment() {
		return actComment;
	}

	public void setActComment(String actComment) {
		this.actComment = actComment;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public String getWfkey() {
		return wfkey;
	}

	public void setWfkey(String wfkey) {
		this.wfkey = wfkey;
	}
	


	
	
}
