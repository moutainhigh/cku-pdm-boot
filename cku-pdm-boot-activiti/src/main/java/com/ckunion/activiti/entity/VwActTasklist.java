package com.ckunion.activiti.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 我的任务
 * @Author: jeecg-boot
 * @Date:   2020-10-09
 * @Version: V1.0
 */
@Data
@TableName("vw_act_tasklist")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="vw_act_tasklist对象", description="我的任务")
public class VwActTasklist {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**taskId*/
	@Excel(name = "taskId", width = 15)
    @ApiModelProperty(value = "taskId")
	private String taskId;
	/**procInstId*/
	@Excel(name = "procInstId", width = 15)
    @ApiModelProperty(value = "procInstId")
	private String procInstId;
	/**actId*/
	@Excel(name = "actId", width = 15)
    @ApiModelProperty(value = "actId")
	private String actId;
	/**actName*/
	@Excel(name = "当前审批环节", width = 15)
    @ApiModelProperty(value = "actName")
	private String actName;
	/**assignee*/
	@Excel(name = "assignee", width = 15)
    @ApiModelProperty(value = "assignee")
	private String assignee;
	/**真实姓名*/
	@Excel(name = "当前处理人", width = 15)
    @ApiModelProperty(value = "真实姓名")
	private String assigneeName;
	/**delegationId*/
	@Excel(name = "delegationId", width = 15)
    @ApiModelProperty(value = "delegationId")
	private String delegationId;
	/**description*/
	@Excel(name = "description", width = 15)
    @ApiModelProperty(value = "description")
	private String description;
	/**createTime*/
	@Excel(name = "开始时间", width = 15)
    @ApiModelProperty(value = "createTime")
	private String createTime;
	/**dueDate*/
	@Excel(name = "dueDate", width = 15)
    @ApiModelProperty(value = "dueDate")
	private String dueDate;
	/**candidate*/
	@Excel(name = "candidate", width = 15)
    @ApiModelProperty(value = "candidate")
	private String candidate;
	/**businessKey*/
	@Excel(name = "businessKey", width = 15)
    @ApiModelProperty(value = "businessKey")
	private String businessKey;
	/**businessCode*/
	@Excel(name = "businessCode", width = 15)
    @ApiModelProperty(value = "businessCode")
	private String businessCode;
	/**businessTitle*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "businessTitle")
	private String businessTitle;
	/**businessDesc*/
	@Excel(name = "businessDesc", width = 15)
    @ApiModelProperty(value = "businessDesc")
	private String businessDesc;
	/**businessDesc*/
	@Excel(name = "procDefId", width = 15)
	@ApiModelProperty(value = "procDefId")
	private String procDefId;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
	@ApiModelProperty(value = "createBy")
	private String createBy;
	/**createName*/
	@Excel(name = "createName", width = 15)
	@ApiModelProperty(value = "createName")
	private String createName;


	/**consumingTime*/
	@TableField(exist = false)
	private String consumingTime;
}
