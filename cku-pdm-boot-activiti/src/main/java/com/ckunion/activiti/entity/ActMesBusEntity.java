package com.ckunion.activiti.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 工作流和业务对象关系表
 * @Author: jeecg-boot
 * @Date:   2020-08-04
 * @Version: V1.0
 */
@Data
@TableName("act_mes_bus")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="act_mes_bus对象", description="工作流和业务对象关系表")
public class ActMesBusEntity {
    
	/**主键ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**businessKey*/
	@Excel(name = "businessKey", width = 15)
    @ApiModelProperty(value = "businessKey")
	private String businessKey;
	/**businessCode*/
	@Excel(name = "businessCode", width = 15)
    @ApiModelProperty(value = "businessCode")
	private String businessCode;
	/**businessTitle*/
	@Excel(name = "businessTitle", width = 15)
    @ApiModelProperty(value = "businessTitle")
	private String businessTitle;
	/**businessDesc*/
	@Excel(name = "businessDesc", width = 15)
    @ApiModelProperty(value = "businessDesc")
	private String businessDesc;
	/**procInstId*/
	@Excel(name = "procInstId", width = 15)
    @ApiModelProperty(value = "procInstId")
	private String procInstId;
	/**endTime*/
	@Excel(name = "endTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "endTime")
	private Date endTime;
	/**state*/
	@Excel(name = "state", width = 15)
    @ApiModelProperty(value = "state")
	private String state;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createName;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateName;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;

	public ActMesBusEntity() {
		super();
	}
	
	public ActMesBusEntity(String businessKey, String businessTitle,String procInstId) {
		super();
		this.businessKey = businessKey;
		this.businessTitle = businessTitle;
		this.procInstId=procInstId;
	}



	public ActMesBusEntity(String businessKey, String businessCode, String businessTitle, String businessDesc,
			String procInstId) {
		super();
		this.businessKey = businessKey;
		this.businessCode = businessCode;
		this.businessTitle = businessTitle;
		this.businessDesc = businessDesc;
		this.procInstId = procInstId;
	}
}
