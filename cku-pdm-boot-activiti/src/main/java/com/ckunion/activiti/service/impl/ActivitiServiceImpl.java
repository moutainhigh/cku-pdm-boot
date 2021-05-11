package com.ckunion.activiti.service.impl;

import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.hibernate.service.spi.ServiceException;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ckunion.activiti.entity.ActMesBusEntity;
import com.ckunion.activiti.mapper.ActMesBusEntityMapper;
import com.ckunion.activiti.service.ActServiceI;
import com.ckunion.activiti.service.ActivitiService;
import com.ckunion.activiti.vo.ActModel;
import com.ckunion.activiti.vo.ActivitiVo;

import lombok.extern.slf4j.Slf4j;
/**
 * 工作流业务类，项目工作流
 * @author liut
 */
@Slf4j
@Service("activitiServiceImpl")
@Transactional
public class ActivitiServiceImpl implements ActivitiService {


	@Autowired
	protected TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	protected RepositoryService repositoryService;
	
	@Autowired
	protected HistoryService historyService;
	@Autowired
	ActMesBusEntityMapper actMesBusEntityMapper;

	@Override
	public String startProcess(ActivitiVo vo, Map<String, Object> variables) {
		
		String processDefinitionKey = vo.getProcessDefinitionKey();
		String businessKey = vo.getBusinessKey();
		
		try {
			LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
			String userId = user.getUsername();
			identityService.setAuthenticatedUserId(userId);
			
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey,
					variables);
			String processInstanceId = processInstance.getId();
			
			//保存流程业务表
			ActMesBusEntity bus = new ActMesBusEntity(businessKey, vo.getBusinessCode(), vo.getBusinessTitle(), vo.getBusinessDesc(), processInstanceId);
			bus.setCreateName(user.getRealname());//20210225 wuj 增加，不知为何启动流程时，createName没有自动添加上，所以这里手动赋值下
			actMesBusEntityMapper.insert(bus);
			
			return processInstanceId;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("工作流程启动异常：流程："+processDefinitionKey+"，业务主键："+businessKey,e);
			throw new ServiceException("工作流程启动异常：流程："+processDefinitionKey+"，业务主键："+businessKey,e);
		}finally {
			identityService.setAuthenticatedUserId(null);
		}
	}
	@Override
	public void startProcess(ActModel actModel) {
		//获取业务类方法
		String serviceName=actModel.getBusinessService();
		ActServiceI actservice= (ActServiceI)ApplicationContextUtil.getContext().getBean(serviceName);
		//完成任务之前，先执行业务类postCompleteTask方法
		Map<String, Object> params=actservice.postCompleteTask(actModel);
		//启动流程
		LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		String userId = user.getUsername();
		identityService.setAuthenticatedUserId(userId);
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(actModel.getWfkey(), actModel.getBusinessKey(),
				actModel.getVariables());
		String processInstanceId = processInstance.getId();
		actModel.setProcessInstanceId(processInstanceId);
		if(params!=null){
			Object title=params.get("wf_title");
			if(title!=null){
				//保存流程业务表
				ActMesBusEntity bus = new ActMesBusEntity(actModel.getBusinessKey(), title+"", processInstanceId);
				bus.setCreateName(user.getRealname());//20210225 wuj 增加，不知为何启动流程时，createName没有自动添加上，所以这里手动赋值下
				actMesBusEntityMapper.insert(bus);
			}
		}
		//执行业务方法
		actservice.afterCompleteTask(actModel);
	}
	@Override
	public void completeTask(String taskId, Map<String, Object> variables, String comment) {
		try {
			// 添加批注信息
			if (StringUtils.isNotEmpty(comment)) {
				LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
				String userId = user.getUsername();
				identityService.setAuthenticatedUserId(userId);
				// comment为批注内容
				taskService.addComment(taskId, null, comment);
			}
			taskService.complete(taskId, variables);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("工作流程任务办理异常：流程："+taskId,e);
			throw new ServiceException("工作流程任务办理异常：流程："+taskId,e);
		}finally {
			identityService.setAuthenticatedUserId(null);
		}
	}

	@Override
	public void completeTask(ActModel actModel) {
		try {
			//获取业务类方法
			String serviceName=actModel.getBusinessService();
			ActServiceI actservice= (ActServiceI)ApplicationContextUtil.getContext().getBean(serviceName);
			//完成任务之前，先执行业务类postCompleteTask方法
			Map<String, Object> params=actservice.postCompleteTask(actModel);
			// 此处要和业务类传过来的参数对比下
			Map<String, Object> variables=actModel.getVariables();
			if(params!=null && params.size()>0){ 
				for (String key : params.keySet()) {
					//将业务类那边的参数值放到variables中，但是不能覆盖原有数据
					if(variables.get(key)==null){
						variables.put(key, params.get(key));
					}
				}
			}
			//2016-11-18 获取历史变量信息，覆盖默认变量，不替换已有变量！！！！！！！
			//《activiti插件画图说明以及规范.docx》 表达式必须是${result=='Y'} 或者 ${result=='N'}
			//获取历史变量，不能包含 表达式变量
			List<HistoricVariableInstance>
			 list = historyService.createHistoricVariableInstanceQuery().processInstanceId(actModel.getProcessInstanceId()).list();
			for(HistoricVariableInstance variableInstance : list) {
				//将业务类那边的参数值放到variables中，但是不能覆盖原有数据
				if(!variables.containsKey(variableInstance .getVariableName())
						&& variableInstance .getVariableName().indexOf("result")==0){
					variables.put(variableInstance .getVariableName(), variableInstance .getValue());
				}
			}
			//2016-11-18 获取历史变量信息，覆盖默认变量，不替换已有变量！！！！！！！
			//将启动人参数加到 starter变量中 -- jiangj add --2018-07-30
			//List<ActMesBusEntity> busList=this.findHql("from ActMesBusEntity where procInstId=? and businessKey=?", actModel.getProcessInstanceId(),actModel.getBusinessKey());
			List<ActMesBusEntity> busList = actMesBusEntityMapper.queryByProcInstIdAndBusinessKey(actModel.getProcessInstanceId(),actModel.getBusinessKey());
			if(busList!=null && busList.size()>0) {
				variables.put("starter", busList.get(0).getCreateBy());
			}
			setVariabledHq(actModel);
			// 添加批注信息
			if (StringUtils.isNotEmpty(actModel.getActComment())) {
				LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
				String userId = user.getUsername();
				identityService.setAuthenticatedUserId(userId);
				// comment为批注内容
				taskService.addComment(actModel.getTaskId(), null, actModel.getActComment());
			}
			taskService.complete(actModel.getTaskId(), variables);
			//完成任务之后，执行业务类afterCompleteTask方法
			Boolean bool=actservice.afterCompleteTask(actModel);
			if(!bool){
				throw new ServiceException("工作流程任务办理异常:执行业务方法afterCompleteTask未通过");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("工作流程任务办理异常：流程："+actModel.getTaskId(),e);
			throw new ServiceException("工作流任务办理异常：流程："+actModel.getTaskId(),e);
		}finally {
			identityService.setAuthenticatedUserId(null);
		}
		
	}
	/**
	 * 设置会签的全局变量  用于后续条件判断
	 * @param actModel
	 */
	public void setVariabledHq(ActModel actModel) {
		Object ys=runtimeService.getVariable(actModel.getProcessInstanceId(), "ys");//会签中 选同意的人数
		Object ns=runtimeService.getVariable(actModel.getProcessInstanceId(), "ns");//会签中选不同意的人数
		//设置会签节点的关键变量信息
		Task task=taskService.createTaskQuery().taskId(actModel.getTaskId()).singleResult();
		BpmnModel bpmnmodel=repositoryService.getBpmnModel(task.getProcessDefinitionId());
		if(bpmnmodel!=null){
			FlowElement flow=bpmnmodel.getMainProcess().getFlowElement(task.getTaskDefinitionKey());
			if(flow instanceof UserTask){
				UserTask userTask=(UserTask)flow;
				if(userTask.getLoopCharacteristics()!=null) {
					if(ys==null) {
						ys=0;
					}
					if(ns==null) {
						ns=0; 
					}
					if("Y".equals(actModel.getActResult())) {
						ys=new Integer(ys+"")+1;
					}else if("N".equals(actModel.getActResult())) {
						ns=new Integer(ns+"")+1;
					}
					runtimeService.setVariable(actModel.getProcessInstanceId(), "ys", new Integer(ys+""));
					runtimeService.setVariable(actModel.getProcessInstanceId(), "ns", new Integer(ns+""));
				}
			}
		}
	}
	@Override
	public ActModel queryActModel(String taskId) {
		String sql="select b.business_key_ as \"businessKey\",t.proc_inst_id_ as \"processInstanceId\"," +
				"t.id_ as \"taskId\",t.name_ as \"nodeName\",t.task_def_key_ as \"nodeId\",b.proc_def_id_ as \"procDefId\" " +
				"from  act_ru_task t left join act_hi_procinst b " +
				"on t.proc_inst_id_=b.proc_inst_id_ where t.id_='"+taskId+"'";
		//List<Object[]> objs=this.findListbySql(sql);
		List<Map<String,String>> objs = actMesBusEntityMapper.queryActModelSql(sql);
		ActModel actModel=new ActModel();
		actModel.setBusinessKey(objs.get(0).get("businessKey"));//业务key
		actModel.setProcessInstanceId(objs.get(0).get("processInstanceId"));//流程实例id
		actModel.setTaskId(objs.get(0).get("taskId"));//任务id
		actModel.setNodeName(objs.get(0).get("nodeName"));//节点名
		actModel.setNodeId(objs.get(0).get("nodeId"));//当前节点id
		String procDefId=objs.get(0).get("procDefId");
		actModel.setWfkey(procDefId.split(":")[0]);
		return actModel;
	}


	@Override
	public ActModel queryHqActModel(String taskId) {
		String sql="select b.business_key_ as \"businessKey\",t.proc_inst_id_ as \"processInstanceId\"," +
				"t.id_ as \"taskId\",t.name_ as \"nodeName\",t.task_def_key_ as \"nodeId\",b.proc_def_id_ as \"procDefId\" " +
				"from  act_ru_task t left join act_hi_procinst b " +
				"on t.proc_inst_id_=b.proc_inst_id_ where t.proc_inst_id_='"+taskId+"'";
		//List<Object[]> objs=this.findListbySql(sql);
		List<Map<String,String>> objs = actMesBusEntityMapper.queryActModelSql(sql);
		ActModel actModel=new ActModel();
		actModel.setBusinessKey(objs.get(0).get("businessKey"));//业务key
		actModel.setProcessInstanceId(objs.get(0).get("processInstanceId"));//流程实例id
		actModel.setTaskId(objs.get(0).get("taskId"));//任务id
		actModel.setNodeName(objs.get(0).get("nodeName"));//节点名
		actModel.setNodeId(objs.get(0).get("nodeId"));//当前节点id
		String procDefId=objs.get(0).get("procDefId");
		actModel.setWfkey(procDefId.split(":")[0]);
		return actModel;
	}

	@Override
	public String getBusinessKey(String processInstanceId) {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).active().singleResult();
		String businessKey = processInstance.getBusinessKey();
		return businessKey;
	}

	@Override
	public void claim(String taskId) {
		LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		String userId = user.getUsername();
		taskService.claim(taskId, userId);
	}
	
	/** 
     * 终止流程(特权人直接终止流程) 
     * @param taskId 
     */
	@Override
    public void endProcess(ActModel actModel) throws Exception {  
		//获取业务类方法
		String serviceName=actModel.getBusinessService();
		ActServiceI actservice= (ActServiceI)ApplicationContextUtil.getContext().getBean(serviceName);
		runtimeService.deleteProcessInstance(actModel.getProcessInstanceId(),"结束");
		actservice.postEndProcess(actModel);//执行业务方法的终止接口
        //ActivityImpl endActivity = findActivitiImpl(actModel.getTaskId(), "end");
        //turnTransition(actModel.getTaskId(), endActivity.getId(),null);  
    } 
    
	/** 
     * *************************************************<br> 
     * *************************以上为流程会签操作核心逻辑****<br> 
     * *************************************************<br> 
     */  
    /** 
     * 会签操作 
     * @param taskId 
     *            当前任务ID 
     * @param userCodes 
     *            会签人账号集合 
     * @throws Exception 
     */  
    public void jointProcess(String taskId, List<String> userCodes)  
            throws Exception {  
       /********开发中*************/
    }  
  
    /** 
     * 转办流程 
     *  
     * @param taskId 
     *            当前任务节点ID 
     * @param userCode 
     *            被转办人Code 
     */  
    public void transferAssignee(String taskId, String userCode) {  
        taskService.setAssignee(taskId, userCode);  
    } 
  
    /** 
     * *****************************************************************<br> 
     * ************以下为流程转向操作核心逻辑*********************************<br> 
     * ****************************************************************<br> 
     */  
    /** 
     * 清空指定活动节点流向 
     * @param activityImpl 活动节点 
     * @return 节点流向集合 
     */  
   /* private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {  
        // 存储当前节点所有流向临时变量  
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();  
        // 获取当前节点所有流向，存储到临时变量，然后清空  
        List<PvmTransition> pvmTransitionList = activityImpl  
                .getOutgoingTransitions();  
        for (PvmTransition pvmTransition : pvmTransitionList) {  
            oriPvmTransitionList.add(pvmTransition);  
        }  
        pvmTransitionList.clear();  
  
        return oriPvmTransitionList;  
    }  */
  
    /** 
     * 还原指定活动节点流向 
     *  
     * @param activityImpl 
     *            活动节点 
     * @param oriPvmTransitionList 
     *            原有节点流向集合 
     */  
   /* private void restoreTransition(ActivityImpl activityImpl,  
            List<PvmTransition> oriPvmTransitionList) {  
        // 清空现有流向  
        List<PvmTransition> pvmTransitionList = activityImpl  
                .getOutgoingTransitions();  
        pvmTransitionList.clear();  
        // 还原以前流向  
        for (PvmTransition pvmTransition : oriPvmTransitionList) {  
            pvmTransitionList.add(pvmTransition);  
        }  
    }  */
    
    /** 
     * 流程转向操作 
     *  
     * @param taskId 
     *            当前任务ID 
     * @param activityId 
     *            目标节点任务ID 
     * @param variables 
     *            流程变量 
     * @throws Exception 
     */  
    /*private void turnTransition(String taskId, String activityId,  
            Map<String, Object> variables) throws Exception {  
        // 当前节点  
        ActivityImpl currActivity = findActivitiImpl(taskId, null);  
        // 清空当前流向  
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);  
  
        // 创建新流向  
        TransitionImpl newTransition = currActivity.createOutgoingTransition();  
        // 目标节点  
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);  
        // 设置新流向的目标节点  
        newTransition.setDestination(pointActivity);  
  
        // 执行转向任务  
        taskService.complete(taskId, variables);  
        // 删除目标节点新流入  
        pointActivity.getIncomingTransitions().remove(newTransition);  
  
        // 还原以前流向  
        restoreTransition(currActivity, oriPvmTransitionList);  
    }  */
	/******************************************************************/

    /** 
     * 根据任务ID获得任务实例 
     *  
     * @param taskId 
     *            任务ID 
     * @return 
     * @throws Exception 
     */  
    private TaskEntity findTaskById(String taskId) throws Exception {  
        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(  
                taskId).singleResult();  
        if (task == null) {  
            throw new Exception("任务实例未找到!");  
        }  
        return task;  
    }  
  
    /** 
     * 根据流程实例ID和任务key值查询所有同级任务集合 
     *  
     * @param processInstanceId 
     * @param key 
     * @return 
     */  
    /*private List<Task> findTaskListByKey(String processInstanceId, String key) {  
        return taskService.createTaskQuery().processInstanceId(  
                processInstanceId).taskDefinitionKey(key).list();  
    }  */
  
    /** 
     * 根据任务ID获取对应的流程实例 
     *  
     * @param taskId 
     *            任务ID 
     * @return 
     * @throws Exception 
     */  
   /* private ProcessInstance findProcessInstanceByTaskId(String taskId)  
            throws Exception {  
        // 找到流程实例  
        ProcessInstance processInstance = runtimeService  
                .createProcessInstanceQuery().processInstanceId(  
                        findTaskById(taskId).getProcessInstanceId())  
                .singleResult();  
        if (processInstance == null) {  
            throw new Exception("流程实例未找到!");  
        }  
        return processInstance;  
    } */
    
	 /** 
     * 根据任务ID获取流程定义 
     *  
     * @param taskId 
     *            任务ID 
     * @return 
     * @throws Exception 
     */  
    private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(  
            String taskId) throws Exception {  
        // 取得流程定义  
    	ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(findTaskById(taskId).getProcessDefinitionId());
        if (definitionEntity == null) {  
            throw new Exception("流程定义未找到!");  
        }  
  
        return definitionEntity;  
    }  
    
	/** 
     * 根据任务ID和节点ID获取活动节点 <br> 
     *  
     * @param taskId 
     *            任务ID 
     * @param activityId 
     *            活动节点ID <br> 
     *            如果为null或""，则默认查询当前活动节点 <br> 
     *            如果为"end"，则查询结束节点 <br> 
     *  
     * @return 
     * @throws Exception 
     */  
   /* private ActivityImpl findActivitiImpl(String taskId, String activityId)  
            throws Exception {  
        // 取得流程定义  
    	ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);  
  
        // 获取当前活动节点ID  
        if (StringUtils.isEmpty(activityId)) {  
            activityId = findTaskById(taskId).getTaskDefinitionKey();  
        }  
  
        // 根据流程定义，获取该流程实例的结束节点  
        if (activityId.toUpperCase().equals("END")) {  
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {  
                List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();  
                if (pvmTransitionList.isEmpty()) {  
                    return activityImpl;  
                }  
            }  
        }  
  
        // 根据节点ID，获取对应的活动节点  
        ActivityImpl activityImpl = processDefinition.findActivity(activityId);  
		
        return activityImpl;  
    } */ 
}
