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
@Api(tags="????????????")
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
   * ????????????
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
		
		return Result.OK("????????????", null);
	}
  	
  	/**
	 * ????????????
	 * activiti
	 * @param proPo
	 * @param request
	 * @return
	 */
  	@AutoLog(value = "????????????")
	@ApiOperation(value="????????????", notes="????????????")
	@RequestMapping(value = "/startProcess")
	@ResponseBody
	public Result<?> startProcess(@RequestParam(name="businessService",required=false) String businessService,
			@RequestParam(name="businessKey",required=true) String businessKey,
			@RequestParam(name="wfkey",required=true) String wfkey,HttpServletRequest request) {
		
		
		/*
		 * ???????????? 
		 * businessService   testActProPoServiceImpl
		 * wfkey			propo_test
		 * wf_approveUser_check  ????????????????????????????????????admin
		 * businessKey		??????id
			businessService:proRefitEntrustService
			businessKey:c6847cb271f940e90171ff752e524217
			wfkey:proRefitEntrust
			wf_approveUser_techUser:admin
			
		 */
		Result j = new Result();
		try {
			ActModel actModel=new ActModel();
			//?????????serviceName
			//????????????
			CkuActivitiList ckuAct = ckuActivitiListService.getByActCode(wfkey);
			if(StringUtils.isBlank(businessService)) {
				actModel.setBusinessService(ckuAct.getServerName());
			}else {
				actModel.setBusinessService(businessService);
			}
			
			//??????id
			actModel.setBusinessKey(businessKey);
			//??????id
			actModel.setWfkey(wfkey);
			actModel.setNodeId("-1");//?????????????????????
			Map<String,Object> variables=new HashMap<String, Object>();
			Map<String,String[]> params=request.getParameterMap();
			for (String key : params.keySet()) {
				if(key.startsWith("wf_approveUser_hq_")){//?????? ???????????????,???????????????????????????
					variables.put(key.replace("wf_approveUser_hq_", ""), Arrays.asList(params.get(key)[0].split(",")));
				}else if(key.startsWith("wf_approveUser_")){//?????? ?????????
					variables.put(key.replace("wf_approveUser_", ""), params.get(key)[0]);
				}
			}
			actModel.setVariables(variables);
			activitiServiceImpl.startProcess(actModel);
			j.setMessage("????????????");;
			j.setSuccess(true);
			return j;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			j.setMessage("???????????????"+e.getMessage());
			j.setSuccess(false);
			return j;
		}
	}
  	
  	/**
	 * ????????????
	 * @param proPo
	 * @param request
	 * @return
	 */
	@AutoLog(value = "????????????")
	@ApiOperation(value="????????????", notes="????????????")
	@RequestMapping(value = "/taskComplete")
	@ResponseBody
	public Result<?>  taskComplete(HttpServletRequest request) {
		// ?????? wf_result ???????????? Y/N  ???   actResult
		//??????????????????????????????????????????   ????????????/??????wf_comment  actComment
		//wf_approveUser_???????????????id??????????????????   wf_approveUser_approve   admin
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
				resultStr = "??????";
			}else {
				resultStr = "?????????";
			}
			if(actComment==null) {
				actComment = resultStr;
			}else if(!actComment.startsWith(resultStr)) {
				actComment = resultStr + "--"+actComment; 
			}
			actModel.setActResult(actResult);//??????
			actModel.setActComment(actComment);//??????
			//?????????serviceName
			actModel.setBusinessService(request.getParameter("businessService"));
			Map<String,Object> variables=new HashMap<String, Object>();
			Map<String,String[]> params=request.getParameterMap();
			for (String key : params.keySet()) {
				if(key.startsWith("wf_approveUser_hq_")){//?????? ???????????????,???????????????????????????
					variables.put(key.replace("wf_approveUser_hq_", ""), Arrays.asList(params.get(key)[0].split(",")));
				}else if(key.startsWith("wf_approveUser_")){//???????????????
					variables.put(key.replace("wf_approveUser_", ""), params.get(key)[0]);
				}
			}
			variables.put("result", actResult);//????????????????????????
			actModel.setVariables(variables);
			activitiServiceImpl.completeTask(actModel);
			j.setMessage("????????????");
			j.setSuccess(true);
			return j;
		} catch (Exception e) {
			log.error("?????????????????????,????????????id???"+actModel.getProcessInstanceId()+"??????:"+actModel.getNodeName(), e);
			j.setSuccess(false);
			j.setMessage(e.getMessage()+"-->"+e.getCause().getMessage());
			return j;
		}
	}
	/**
	 * ????????????
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
			//?????????serviceName
			actModel.setBusinessService(request.getParameter("businessService"));
			activitiServiceImpl.endProcess(actModel);
			j.setMessage("????????????");
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMessage("???????????????"+e.getMessage());
		}
		return j;
	}
  	
  	
  	/**
	 * ?????????????????????????????????xml
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
	 * ??????????????????????????????
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
		// ????????????????????????
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		// ?????????????????????????????????????????????????????????????????????
		List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
				.orderByHistoricActivityInstanceId().asc().list();
		// ??????????????????????????????ID??????
		List<String> highLightedActivitiIds = new ArrayList<>();
		for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
			highLightedActivitiIds.add(historicActivityInstance.getActivityId());
		}

		List<HistoricProcessInstance> historicFinishedProcessInstances = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).finished()
				.list();
		ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();  
		ProcessDiagramGenerator processDiagramGenerator = null;
		// ?????????????????????????????????????????????????????????????????????????????????
		if (!CollectionUtils.isEmpty(historicFinishedProcessInstances)) {
			// ????????????????????????????????????
			processDiagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
		} else {
			processDiagramGenerator = new DefaultProcessDiagramGenerator();
		}

		BpmnModel bpmnModel = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());
		// ?????????????????????????????????id??????
		List<String> highLightedFlowIds = getHighLightedFlows(bpmnModel, historicActivityInstances);
	    /** 
	     * ???????????? 
	     */  
	    if (null != highLightedFlowIds) {  
	    	//response.setContentType("application/force-download");// ???????????????????????????
	         // response.addHeader("Content-Disposition", "attachment;fileName=" + new String("1234536.png".getBytes("UTF-8"),"iso-8859-1"));
	        InputStream imageStream = null;  
	        try {  
	            // ????????????????????????  
	            // ??????????????????ID??????BpmnModel  
	            // ?????????????????????????????????
				if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1){
					imageStream = processDiagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitiIds,
							highLightedFlowIds, "??????", "????????????", "??????", null, 2.0);
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
	 * easyui ????????????????????????
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
	 * ????????????????????????????????????????????????
	 * 
	 * @param deploymentId
	 *            ????????????ID
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public Result del(@RequestParam("deploymentId") String deploymentId, HttpServletRequest request) {
		Result j = new Result();
		repositoryService.deleteDeployment(deploymentId, true);
		String message = "????????????";
		j.setMessage(message);
		return j;
	}

	/**
	 * easyui AJAX????????????
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
	 * easyui AJAX???????????? ????????????
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
	 * easyui AJAX???????????? ????????????
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
	 * easyui AJAX???????????? ????????????
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
	 * ????????????
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

		String message = "????????????";
		j.setMessage(message);
		return j;
	}
	

	/** 
	 * ??????????????? 5.17version
	 *  
	 * @param processDefinitionEntity 
	 *            ?????????????????? 
	 * @param historicActivityInstances 
	 *            ?????????????????? 
	 * @return ???ID?????? 
	 */  
	/*public List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity,
			List<HistoricActivityInstance> historicActivityInstances) {

		// ??? ?????????????????????flowId
		List<String> highFlows = new ArrayList<String>();
		// ?????????????????????????????????
		for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
			ActivityImpl activityImpl = processDefinitionEntity.findActivity(historicActivityInstances.get(i).getActivityId());
			// ??????????????????????????? ??????
			List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();
			// ?????????????????????????????????????????????
			ActivityImpl sameActivityImpl1 = processDefinitionEntity.findActivity(historicActivityInstances.get(i + 1).getActivityId());
			// ??????????????????????????? ?????????????????????????????????
			sameStartTimeNodes.add(sameActivityImpl1);
			for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
				// ?????????????????????
				HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);
				// ?????????????????????
				HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);
				
				// ????????? ??????????????????????????????????????????????????????
				if (activityImpl1.getStartTime().equals(activityImpl2.getStartTime())) {
					ActivityImpl sameActivityImpl2 = processDefinitionEntity.findActivity(activityImpl2.getActivityId());
					sameStartTimeNodes.add(sameActivityImpl2);
				} else {// ????????????????????????
					break;
				}
			}
			// ?????????????????????????????????
			List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
			for (PvmTransition pvmTransition : pvmTransitions) {// ???????????????????????????
				ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
				// ????????????????????????????????? ????????????????????????????????????????????????id?????????????????????
				if (sameStartTimeNodes.contains(pvmActivityImpl)) {
					highFlows.add(pvmTransition.getId());
				}
			}
		}
		return highFlows;
	}*/
	
	/**
	 * ????????????????????????
	 * 
	 * @param bpmnModel
	 * @param historicActivityInstances
	 * @return
	 */
	private static List<String> getHighLightedFlows(BpmnModel bpmnModel, List<HistoricActivityInstance> historicActivityInstances) {
		
		// 24?????????
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // ????????????????????????flowId
        List<String> highFlows = new ArrayList<String>();

        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
            // ?????????????????????????????????
            // ?????????????????????????????????
            FlowNode activityImpl = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(i).getActivityId());
            // ?????????????????????????????????????????????
            List<FlowNode> sameStartTimeNodes = new ArrayList<FlowNode>();
            FlowNode sameActivityImpl1 = null;
            // ???????????????
            HistoricActivityInstance activityImpl_ = historicActivityInstances.get(i);
            HistoricActivityInstance activityImp2_;

            for (int k = i + 1; k <= historicActivityInstances.size() - 1; k++) {
                // ?????????1?????????
                activityImp2_ = historicActivityInstances.get(k);

                // ??????usertask???????????????????????????????????????????????????????????????????????????????????????
                if (activityImpl_.getActivityType().equals("userTask") && activityImp2_.getActivityType().equals("userTask") &&
                        df.format(activityImpl_.getStartTime()).equals(df.format(activityImp2_.getStartTime()))) {

                } else {
                    // ????????????????????????????????????
                    sameActivityImpl1 = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(k).getActivityId());
                    break;
                }
            }
            // ????????????????????????????????????????????????????????????
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                // ?????????????????????
                HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);
                // ?????????????????????
                HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);

                // ???????????????????????????????????????????????????????????????
                if (df.format(activityImpl1.getStartTime()).equals(df.format(activityImpl2.getStartTime()))) {
                    FlowNode sameActivityImpl2 = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // ????????????????????????
                    break;
                }
            }
            // ?????????????????????????????????
            List<SequenceFlow> pvmTransitions = activityImpl.getOutgoingFlows();

            // ???????????????????????????
            for (SequenceFlow pvmTransition : pvmTransitions) {
                // ?????????????????????????????????????????????????????????????????????????????????id?????????????????????
                FlowNode pvmActivityImpl = (FlowNode) bpmnModel.getMainProcess().getFlowElement(pvmTransition.getTargetRef());
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
	}
	
	/**
	 * ??????????????????
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
			j.setMessage("????????????");
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMessage("???????????????"+e.getMessage());
		}
		return j;
	}
}
