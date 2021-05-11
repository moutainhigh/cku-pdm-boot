package com.ckunion.activiti.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ckunion.activiti.entity.CkuActivitiList;
import com.ckunion.activiti.service.ActivitiService;
import com.ckunion.activiti.service.ICkuActivitiListService;
import com.ckunion.activiti.vo.ActModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@Slf4j
@Api(tags="流程管理")
@RestController
@RequestMapping("/activiti/activitiController")
public class ActivitiController{
//	 @Value(value = "${jeecg.path.activite}")
//	    private String activitepath;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;

	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	protected RepositoryService repositoryService;
	
	@Autowired
	private ICkuActivitiListService ckuActivitiListService;
	@Autowired
	private ActivitiService activitiServiceImpl;
	
	/**
   * 流程发布
   */
  
  	@RequestMapping(value = "/deploy")
	@ResponseBody
	public Result<?> deploy(HttpServletRequest request, @RequestParam("id") String id) {
		
	  	CkuActivitiList ckuAct = ckuActivitiListService.getById(id);
	  	String processName = ckuAct.getFileName();
	  	//processName = processName.replace(".bpmn", "");
	  	processName = processName.replace("//", "/");
	  	String imgName = ckuAct.getImgName();
	  	//imgName = imgName.replace(".png", "");
	  	imgName = imgName.replace("//", "/");
		Deployment deploy = processEngine.getRepositoryService().createDeployment()
				.addClasspathResource("processes/" +processName )
				.addClasspathResource("processes/"+ imgName).deploy();
		ckuAct.setActState("1");
		List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery().deploymentId(deploy.getId()).list();
		if(list.size()>0) {
			ckuAct.setActVersion(list.get(0).getVersion()+"");
		}
		ckuActivitiListService.updateById(ckuAct);
		
		return Result.OK("发布成功", null);
	}
  	
  	/**
	 * 发起流程
	 * activiti
	 * @param proPo
	 * @param request
	 * @return
	 */
  	@AutoLog(value = "流程启动")
	@ApiOperation(value="流程启动", notes="流程启动")
	@RequestMapping(value = "/startProcess")
	@ResponseBody
	public Result<?> startProcess(@RequestParam(name="businessService",required=false) String businessService,
			@RequestParam(name="businessKey",required=true) String businessKey,
			@RequestParam(name="wfkey",required=true) String wfkey,HttpServletRequest request) {
		
		
		/*
		 * 提交审批 
		 * businessService   testActProPoServiceImpl
		 * wfkey			propo_test
		 * wf_approveUser_check  选人或者角色，暂时写死用admin
		 * businessKey		订单id
			businessService:proRefitEntrustService
			businessKey:c6847cb271f940e90171ff752e524217
			wfkey:proRefitEntrust
			wf_approveUser_techUser:admin
			
		 */
		Result j = new Result();
		try {
			ActModel actModel=new ActModel();
			//业务类serviceName
			//流程编码
			CkuActivitiList ckuAct = ckuActivitiListService.getByActCode(wfkey);
			if(StringUtils.isBlank(businessService)) {
				actModel.setBusinessService(ckuAct.getServerName());
			}else {
				actModel.setBusinessService(businessService);
			}
			
			//业务id
			actModel.setBusinessKey(businessKey);
			//流程id
			actModel.setWfkey(wfkey);
			actModel.setNodeId("-1");//表示是开始节点
			Map<String,Object> variables=new HashMap<String, Object>();
			Map<String,String[]> params=request.getParameterMap();
			for (String key : params.keySet()) {
				if(key.startsWith("wf_approveUser_hq_")){//表示 会签选择框,变量类型设置成集合
					variables.put(key.replace("wf_approveUser_hq_", ""), Arrays.asList(params.get(key)[0].split(",")));
				}else if(key.startsWith("wf_approveUser_")){//表示 选择框
					variables.put(key.replace("wf_approveUser_", ""), params.get(key)[0]);
				}
			}
			actModel.setVariables(variables);
			activitiServiceImpl.startProcess(actModel);
			j.setMessage("启动成功");;
			j.setSuccess(true);
			return j;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			j.setMessage("启动失败："+e.getMessage());
			j.setSuccess(false);
			return j;
		}
	}
  	
  	/**
	 * 节点审批
	 * @param proPo
	 * @param request
	 * @return
	 */
	@AutoLog(value = "节点审批")
	@ApiOperation(value="节点审批", notes="节点审批")
	@RequestMapping(value = "/taskComplete")
	@ResponseBody
	public Result<?>  taskComplete(HttpServletRequest request) {
		// 参数 wf_result 审批结论 Y/N  ；   actResult
		//审批结论如果是否审批意见必填   审批意见/批注wf_comment  actComment
		//wf_approveUser_（流程节点id）当前审批人   wf_approveUser_approve   admin
		//wf_approveUserName_
		//wf_approveUser_*_label
		//taskId  
		//businessService    testActProPoServiceImpl
		Result j = new Result();
		ActModel actModel=new ActModel();
		String taskId=request.getParameter("taskId");
		actModel=activitiServiceImpl.queryActModel(taskId);
		try {
			String actResult=request.getParameter("actResult");
			String actComment=request.getParameter("actComment");
			String resultStr = "";
			if("Y".equals(actResult)){
				resultStr = "同意";
			}else {
				resultStr = "不同意";
			}
			if(actComment==null) {
				actComment = resultStr;
			}else if(!actComment.startsWith(resultStr)) {
				actComment = resultStr + "--"+actComment; 
			}
			actModel.setActResult(actResult);//结论
			actModel.setActComment(actComment);//批注
			//业务类serviceName
			actModel.setBusinessService(request.getParameter("businessService"));
			Map<String,Object> variables=new HashMap<String, Object>();
			Map<String,String[]> params=request.getParameterMap();
			for (String key : params.keySet()) {
				if(key.startsWith("wf_approveUser_hq_")){//表示 会签选择框,变量类型设置成集合
					variables.put(key.replace("wf_approveUser_hq_", ""), Arrays.asList(params.get(key)[0].split(",")));
				}else if(key.startsWith("wf_approveUser_")){//表示选择框
					variables.put(key.replace("wf_approveUser_", ""), params.get(key)[0]);
				}
			}
			variables.put("result", actResult);//用于工作流中判断
			actModel.setVariables(variables);
			activitiServiceImpl.completeTask(actModel);
			j.setMessage("提交成功");
			j.setSuccess(true);
			return j;
		} catch (Exception e) {
			log.error("工作流提交异常,流程实例id："+actModel.getProcessInstanceId()+"节点:"+actModel.getNodeName(), e);
			j.setSuccess(false);
			j.setMessage(e.getMessage()+"-->"+e.getCause().getMessage());
			return j;
		}
	}
	/**
	 * 流程终止
	 * @param request
	 * @return
	 */
  	@RequestMapping(value = "/endProcess")
	@ResponseBody
	public Result endProcess(HttpServletRequest request) {
  		Result j = new Result();
		String taskId=request.getParameter("taskId");
		ActModel actModel=activitiServiceImpl.queryHqActModel(taskId);
		try {
			//业务类serviceName
			actModel.setBusinessService(request.getParameter("businessService"));
			activitiServiceImpl.endProcess(actModel);
			j.setMessage("撤回成功");
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMessage("撤回失败："+e.getMessage());
		}
		return j;
	}
  	
  	
  	/**
	 * 读取工作流定义的图片或xml
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/resourceRead")
	public void resourceRead(@RequestParam("processDefinitionId") String processDefinitionId,
			@RequestParam("resourceType") String resourceType, HttpServletResponse response) throws Exception {

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		String resourceName = "";
		if (resourceType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resourceType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}
		InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
				resourceName);
		byte[] b = new byte[1024];
		int len = -1;

		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

	/**
	 * 读取带跟踪的流程图片
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/traceImage")
	public void traceImage(@RequestParam("processInstanceId") String processInstanceId, HttpServletResponse response)
			throws Exception {
		if (StringUtils.isEmpty(processInstanceId)) {
			log.error("processInstanceId is null");
			return;
		}
		// 获取历史流程实例
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		// 获取流程中已经执行的节点，按照执行先后顺序排序
		List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
				.orderByHistoricActivityInstanceId().asc().list();
		// 高亮已经执行流程节点ID集合
		List<String> highLightedActivitiIds = new ArrayList<>();
		for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
			highLightedActivitiIds.add(historicActivityInstance.getActivityId());
		}

		List<HistoricProcessInstance> historicFinishedProcessInstances = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).finished()
				.list();
		ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();  
		ProcessDiagramGenerator processDiagramGenerator = null;
		// 如果还没完成，流程图高亮颜色为绿色，如果已经完成为红色
		if (!CollectionUtils.isEmpty(historicFinishedProcessInstances)) {
			// 如果不为空，说明已经完成
			processDiagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
		} else {
			processDiagramGenerator = new DefaultProcessDiagramGenerator();
		}

		BpmnModel bpmnModel = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());
		// 高亮流程已发生流转的线id集合
		List<String> highLightedFlowIds = getHighLightedFlows(bpmnModel, historicActivityInstances);
	    /** 
	     * 绘制图形 
	     */  
	    if (null != highLightedFlowIds) {  
	    	//response.setContentType("application/force-download");// 设置强制下载不打开
	         // response.addHeader("Content-Disposition", "attachment;fileName=" + new String("1234536.png".getBytes("UTF-8"),"iso-8859-1"));
	        InputStream imageStream = null;  
	        try {  
	            // 获得流程引擎配置  
	            // 根据流程定义ID获得BpmnModel  
	            // 输出资源内容到相应对象
				if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1){
					imageStream = processDiagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitiIds,
							highLightedFlowIds, "宋体", "微软雅黑", "黑体", null, 2.0);
				}else{
					imageStream = processDiagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitiIds,
							highLightedFlowIds, "AR PL UMing HK", "AR PL UMing HK", "AR PL UMing HK", null, 2.0);
				}


	            int len = 0;
	    		byte[] b = new byte[1024];
	    		while ((len = imageStream.read(b, 0, 1024)) != -1) {
	    			response.getOutputStream().write(b, 0, len);
	    		}
	        } finally {  
	        	imageStream.close();
	        }  
	    }  
	}
	

	

	/**
	 * easyui 流程历史数据获取
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(value = "/taskHistoryList")
	public void taskHistoryList(@RequestParam("processInstanceId") String processInstanceId, HttpServletRequest request,
			HttpServletResponse response) {

		List<HistoricTaskInstance> historicTasks = processEngine.getHistoryService().createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId).list();
		StringBuffer rows = new StringBuffer();
		for (HistoricTaskInstance hi : historicTasks) {
			rows.append("{'name':'" + hi.getName() + "','processInstanceId':'" + hi.getProcessInstanceId()
					+ "','startTime':'" + hi.getStartTime() + "','endTime':'" + hi.getEndTime() + "','assignee':'"
					+ hi.getAssignee() + "','deleteReason':'" + hi.getDeleteReason() + "'},");
		}

		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		JSONObject jObject = JSONObject.fromObject("{'total':" + historicTasks.size() + ",'rows':[" + rowStr + "]}");
		//responseDatagrid(response, jObject);
	}

	/**
	 * 删除部署的流程，级联删除流程实例
	 * 
	 * @param deploymentId
	 *            流程部署ID
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public Result del(@RequestParam("deploymentId") String deploymentId, HttpServletRequest request) {
		Result j = new Result();
		repositoryService.deleteDeployment(deploymentId, true);
		String message = "删除成功";
		j.setMessage(message);
		return j;
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(value = "/datagrid")
	public void datagrid(HttpServletRequest request, HttpServletResponse response) {
		String key=request.getParameter("key");
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		if(StringUtils.isNotBlank(key)) {
			query=query.processDefinitionKey(key);
		}
		query.orderByProcessDefinitionVersion().desc();
		List<ProcessDefinition> list = query.list();
		StringBuffer rows = new StringBuffer();
		int i = 0;
		for (ProcessDefinition pi : list) {
			i++;
			rows.append("{'id':" + i + ",'processDefinitionId':'" + pi.getId() + "','startPage':'" + pi.getDescription()
					+ "','resourceName':'" + pi.getResourceName() + "','deploymentId':'" + pi.getDeploymentId()
					+ "','key':'" + pi.getKey() + "','name':'" + pi.getName() + "','version':'" + pi.getVersion()
					+ "','isSuspended':'" + pi.isSuspended() + "'},");
		}
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");

		JSONObject jObject = JSONObject.fromObject("{'total':" + query.count() + ",'rows':[" + rowStr + "]}");
		//responseDatagrid(response, jObject);
	}


	/**
	 * easyui AJAX请求数据 待领任务
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(value = "/waitingClaimTaskDataGrid")
	public void waitingClaimTaskDataGrid(HttpServletRequest request, HttpServletResponse response) {
		LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		String userId = user.getUsername();

		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userId).active().list();// taskCandidateUser.taskCandidateGroup("hr").active().list();

		StringBuffer rows = new StringBuffer();
		for (Task t : tasks) {
			rows.append("{'name':'" + t.getName() + "','taskId':'" + t.getId() + "','processDefinitionId':'"
					+ t.getProcessDefinitionId() + "'},");
		}
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");

		JSONObject jObject = JSONObject.fromObject("{'total':" + tasks.size() + ",'rows':[" + rowStr + "]}");
		//responseDatagrid(response, jObject);
	}

	/**
	 * easyui AJAX请求数据 待办任务
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(value = "/claimedTaskDataGrid")
	public void claimedTaskDataGrid(HttpServletRequest request, HttpServletResponse response) {

		LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		String userId = user.getUsername();
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).list();

		StringBuffer rows = new StringBuffer();
		for (Task t : tasks) {
			rows.append("{'name':'" + t.getName() + "','description':'" + t.getDescription() + "','taskId':'"
					+ t.getId() + "','processDefinitionId':'" + t.getProcessDefinitionId() + "','processInstanceId':'"
					+ t.getProcessInstanceId() + "'},");
		}
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");

		JSONObject jObject = JSONObject.fromObject("{'total':" + tasks.size() + ",'rows':[" + rowStr + "]}");
		//responseDatagrid(response, jObject);
	}

	/**
	 * easyui AJAX请求数据 已办任务
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(value = "/finishedTaskDataGrid")
	public void finishedTask(HttpServletRequest request, HttpServletResponse response) {

		LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		String userId = user.getUsername();
		List<HistoricTaskInstance> historicTasks = processEngine.getHistoryService().createHistoricTaskInstanceQuery()
				.taskAssignee(userId).finished().list();

		StringBuffer rows = new StringBuffer();
		for (HistoricTaskInstance t : historicTasks) {
			rows.append("{'name':'" + t.getName() + "','description':'" + t.getDescription() + "','taskId':'"
					+ t.getId() + "','processDefinitionId':'" + t.getProcessDefinitionId() + "','processInstanceId':'"
					+ t.getProcessInstanceId() + "'},");
		}
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");

		JSONObject jObject = JSONObject.fromObject("{'total':" + historicTasks.size() + ",'rows':[" + rowStr + "]}");
		//responseDatagrid(response, jObject);
	}

	/**
	 * 签收任务
	 * 
	 * @param taskId
	 */
	@RequestMapping(value = "claimTask")
	@ResponseBody
	public Result claimTask(@RequestParam("taskId") String taskId, HttpServletRequest request) {
		Result j = new Result();

		LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		String userId = user.getUsername();

		taskService.claim(taskId, userId);

		String message = "签收成功";
		j.setMessage(message);
		return j;
	}
	

	/** 
	 * 获得高亮线 5.17version
	 *  
	 * @param processDefinitionEntity 
	 *            流程定义实体 
	 * @param historicActivityInstances 
	 *            历史活动实体 
	 * @return 线ID集合 
	 */  
	/*public List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity,
			List<HistoricActivityInstance> historicActivityInstances) {

		// 用 以保存高亮的线flowId
		List<String> highFlows = new ArrayList<String>();
		// 对历史流程节点进行遍历
		for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
			ActivityImpl activityImpl = processDefinitionEntity.findActivity(historicActivityInstances.get(i).getActivityId());
			// 得到节点定义的详细 信息
			List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();
			// 用以保存后需开始时间相同的节点
			ActivityImpl sameActivityImpl1 = processDefinitionEntity.findActivity(historicActivityInstances.get(i + 1).getActivityId());
			// 将后面第一个节点放 在时间相同节点的集合里
			sameStartTimeNodes.add(sameActivityImpl1);
			for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
				// 后续第一个节点
				HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);
				// 后续第二个节点
				HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);
				
				// 如果第 一个节点和第二个节点开始时间相同保存
				if (activityImpl1.getStartTime().equals(activityImpl2.getStartTime())) {
					ActivityImpl sameActivityImpl2 = processDefinitionEntity.findActivity(activityImpl2.getActivityId());
					sameStartTimeNodes.add(sameActivityImpl2);
				} else {// 有不相同跳出循环
					break;
				}
			}
			// 取出节点的所有出去的线
			List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
			for (PvmTransition pvmTransition : pvmTransitions) {// 对所有的线进行遍历
				ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
				// 如果取出的线的目标节点 存在时间相同的节点里，保存该线的id，进行高亮显示
				if (sameStartTimeNodes.contains(pvmActivityImpl)) {
					highFlows.add(pvmTransition.getId());
				}
			}
		}
		return highFlows;
	}*/
	
	/**
	 * 获取已经流转的线
	 * 
	 * @param bpmnModel
	 * @param historicActivityInstances
	 * @return
	 */
	private static List<String> getHighLightedFlows(BpmnModel bpmnModel, List<HistoricActivityInstance> historicActivityInstances) {
		
		// 24小时制
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 用以保存高亮的线flowId
        List<String> highFlows = new ArrayList<String>();

        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
            // 对历史流程节点进行遍历
            // 得到节点定义的详细信息
            FlowNode activityImpl = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(i).getActivityId());
            // 用以保存后续开始时间相同的节点
            List<FlowNode> sameStartTimeNodes = new ArrayList<FlowNode>();
            FlowNode sameActivityImpl1 = null;
            // 第一个节点
            HistoricActivityInstance activityImpl_ = historicActivityInstances.get(i);
            HistoricActivityInstance activityImp2_;

            for (int k = i + 1; k <= historicActivityInstances.size() - 1; k++) {
                // 后续第1个节点
                activityImp2_ = historicActivityInstances.get(k);

                // 都是usertask，且主节点与后续节点的开始时间相同，说明不是真实的后继节点
                if (activityImpl_.getActivityType().equals("userTask") && activityImp2_.getActivityType().equals("userTask") &&
                        df.format(activityImpl_.getStartTime()).equals(df.format(activityImp2_.getStartTime()))) {

                } else {
                    // 找到紧跟在后面的一个节点
                    sameActivityImpl1 = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(k).getActivityId());
                    break;
                }
            }
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                // 后续第一个节点
                HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);
                // 后续第二个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);

                // 如果第一个节点和第二个节点开始时间相同保存
                if (df.format(activityImpl1.getStartTime()).equals(df.format(activityImpl2.getStartTime()))) {
                    FlowNode sameActivityImpl2 = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            // 取出节点的所有出去的线
            List<SequenceFlow> pvmTransitions = activityImpl.getOutgoingFlows();

            // 对所有的线进行遍历
            for (SequenceFlow pvmTransition : pvmTransitions) {
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                FlowNode pvmActivityImpl = (FlowNode) bpmnModel.getMainProcess().getFlowElement(pvmTransition.getTargetRef());
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
	}
	
	/**
	 * 流程转发操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/forward")
	@ResponseBody
	public Result forward(HttpServletRequest request) {
		Result j = new Result();
		String taskId=request.getParameter("taskId");
		String userName=request.getParameter("userName");
		try {
			activitiServiceImpl.transferAssignee(taskId, userName);
			j.setMessage("转发成功");
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMessage("转发失败："+e.getMessage());
		}
		return j;
	}
}
