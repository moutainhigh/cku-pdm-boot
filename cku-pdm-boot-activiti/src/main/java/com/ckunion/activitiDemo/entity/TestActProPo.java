package com.ckunion.activitiDemo.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2020-09-28
 * @Version: V1.0
 */
@Data
@TableName("test_act_pro_po")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="test_act_pro_po对象", description="订单")
public class TestActProPo {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**订单号*/
	@Excel(name = "订单号", width = 15)
    @ApiModelProperty(value = "订单号")
	private String poCode;
	/**订单名称*/
	@Excel(name = "订单名称", width = 15)
    @ApiModelProperty(value = "订单名称")
	private String poName;
	/**工作流状态*/
	@Excel(name = "工作流状态", width = 15)
    @ApiModelProperty(value = "工作流状态")
	private String actState;
	/**流程发起人*/
	@Excel(name = "流程发起人", width = 15)
    @ApiModelProperty(value = "流程发起人")
	private String actStarter;
	/**流程发起人姓名*/
	@Excel(name = "流程发起人姓名", width = 15)
    @ApiModelProperty(value = "流程发起人姓名")
	private String actStarterName;
	/**发起时间*/
	@Excel(name = "发起时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发起时间")
	private Date actStartDate;
	/**批准人*/
	@Excel(name = "批准人", width = 15)
    @ApiModelProperty(value = "批准人")
	private String actApprove;
	/**批准名称*/
	@Excel(name = "批准名称", width = 15)
    @ApiModelProperty(value = "批准名称")
	private String actApproveName;
	/**批准时间*/
	@Excel(name = "批准时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "批准时间")
	private Date actApproveDate;
	/**订单状态0新建1审批通过2审批未通过*/
	@Excel(name = "订单状态0新建1审批通过2审批未通过", width = 15)
    @ApiModelProperty(value = "订单状态0新建1审批通过2审批未通过")
	private String state;
	/**审核人*/
	@Excel(name = "审核人", width = 15)
    @ApiModelProperty(value = "审核人")
	private String actCheck;
	/**审核人名称*/
	@Excel(name = "审核人名称", width = 15)
    @ApiModelProperty(value = "审核人名称")
	private String actCheckName;
	/**审核时间*/
	@Excel(name = "审核时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "审核时间")
	private Date actCheckDate;
	/**流程id*/
	@Excel(name = "流程id", width = 15)
    @ApiModelProperty(value = "流程id")
	private String processinsid;
}
