package com.ckunion.activiti.vo;

/**   
 * @Title: 查询我的任务列表
 * @Description: 我的任务列表展示信息，业务信息和流程信息
 * @author 刘涛
 * @date 2016-11-16 11:51:55
 * @version V1.0   
 *
 */
public class ActivitiVo {

	//工作流定义DI，  例如：propo.bpmp -> propo
	private String processDefinitionKey;
	
	private String businessKey;
	private String businessCode;
	private String businessTitle;
	private String businessDesc;
	
	
	public ActivitiVo(String processDefinitionKey, String businessKey, String businessCode, String businessTitle,
			String businessDesc) {
		super();
		this.processDefinitionKey = processDefinitionKey;
		this.businessKey = businessKey;
		this.businessCode = businessCode;
		this.businessTitle = businessTitle;
		this.businessDesc = businessDesc;
	}
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public String getBusinessCode() {
		return businessCode;
	}
	public String getBusinessTitle() {
		return businessTitle;
	}
	public String getBusinessDesc() {
		return businessDesc;
	}
	
	
}
