package com.ckunion.modules.base.typicalProc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 工作环境要求从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@ApiModel(value="bas_typical_proc对象", description="典型工艺库")
@Data
@TableName("bas_typical_surr")
public class BasTypicalSurr implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
	private String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;
	/**项目*/
	@Excel(name = "项目", width = 15)
	@ApiModelProperty(value = "项目")
	private String project;
	/**目标值/下限（要求）*/
	@Excel(name = "目标值/下限（要求）", width = 15)
	@ApiModelProperty(value = "目标值/下限（要求）")
	private String qtyMin;
	/**容差/上限*/
	@Excel(name = "容差/上限", width = 15)
	@ApiModelProperty(value = "容差/上限")
	private String qtyMax;
	/**使用环境*/
	@Excel(name = "使用环境", width = 15)
	@ApiModelProperty(value = "使用环境")
	private String useSurroundings;
	/**masteroid*/
	@ApiModelProperty(value = "masteroid")
	private String masteroid;
}
