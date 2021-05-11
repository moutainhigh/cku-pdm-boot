package com.ckunion.activitiDemo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.activiti.service.ActServiceI;
import com.ckunion.activiti.vo.ActModel;
import com.ckunion.activitiDemo.entity.TestActProPo;
import com.ckunion.activitiDemo.mapper.TestActProPoMapper;
import com.ckunion.activitiDemo.service.ITestActProPoService;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2020-09-28
 * @Version: V1.0
 */
@Service
public class TestActProPoServiceImpl extends ServiceImpl<TestActProPoMapper, TestActProPo> implements ITestActProPoService,ActServiceI {
	
	@Autowired
	private TestActProPoMapper testActProPoMapper;
	//任务办理之前执行
	@Override
	public Map<String, Object> postCompleteTask(ActModel actModel) {
		String wfkey = actModel.getWfkey();
		String nodeId=actModel.getNodeId();
		String businessKey=actModel.getBusinessKey();
		HashMap<String, Object> map=new HashMap<String, Object>();
		TestActProPo proPo = this.getById(businessKey);
		proPo.setProcessinsid(actModel.getProcessInstanceId());
		this.updateById(proPo);
		map.put("wf_title","订单审批:"+"订单名称："+proPo.getPoName()+ "，订单号："+proPo.getPoCode());
		return map;
	}

	//任务办理成功之后 
	@Override
	public Boolean afterCompleteTask(ActModel actModel) {
		String businessKey=actModel.getBusinessKey();
		String wfkey = actModel.getWfkey();
		String nodeId=actModel.getNodeId();
		LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		String result=actModel.getActResult();
		TestActProPo proPo = this.getById(businessKey);
			String state="";
			if("-1".equals(actModel.getNodeId())){//开始节点发起审批
				proPo.setActStarter(user.getUsername());
				proPo.setActStarterName(user.getRealname());
				proPo.setActStartDate(new Date());
				proPo.setProcessinsid(actModel.getProcessInstanceId());
			    String proSql="";
				state="1";
			}else if("check".equals(nodeId)){//审核人同意
				proPo.setActCheck(user.getUsername());
				proPo.setActCheckName(user.getRealname());
				proPo.setActCheckDate(new Date());
				if("Y".equals(result)){
					state="1";
				}else {
					state="2";
				}
			}else if("approve".equals(nodeId)){//批准
				proPo.setActApprove(user.getUsername());
				proPo.setActApproveName(user.getRealname());
				proPo.setActApproveDate(new Date());
				if("Y".equals(result)){
					state="2";
				}else {
					state="3";
				}
			}
			proPo.setActState(state);
			this.updateById(proPo);
			return true;
	}

	//流程终止前执行的方法（业务service需要实现此方法，对流程终止时实际业务表进行逻辑处理）
	@Override
	public void postEndProcess(ActModel actModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUsers(Map<String, Object> map) {
		return null;
	}

}
