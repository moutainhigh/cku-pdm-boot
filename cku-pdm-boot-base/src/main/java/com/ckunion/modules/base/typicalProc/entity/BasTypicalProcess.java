package com.ckunion.modules.base.typicalProc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
import java.util.List;

/**
 * @Description: 操作过程从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@ApiModel(value="bas_typical_proc对象", description="典型工艺库")
@Data
@TableName("bas_typical_process")
public class BasTypicalProcess implements Serializable {
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
	/**masteroid*/
	@ApiModelProperty(value = "masteroid")
	private String masteroid;

	/**大序号*/
	@Excel(name = "大序号", width = 15)
	@ApiModelProperty(value = "大序号")
	private String seqNo;
	/**小序号*/
	@Excel(name = "小序号", width = 15)
	@ApiModelProperty(value = "小序号")
	private String seqNo2;
	/**项目*/
	@Excel(name = "项目", width = 15)
	@ApiModelProperty(value = "项目")
	private String itemName;
	/**内容*/
	@Excel(name = "内容", width = 15)
	@ApiModelProperty(value = "内容")
	private String itemContent;
	/**是否包含检验方法集合*/
	@Excel(name = "是否包含检验方法集合", width = 15)
	@ApiModelProperty(value = "是否包含检验方法集合")
	private String havCheck;

	@TableField(exist = false)
	private List<BasTypicalProcessCheck> checkList;

}
