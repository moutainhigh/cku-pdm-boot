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
 * @Description: 操作过程-检验方法从从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@ApiModel(value="bas_typical_proc对象", description="典型工艺库")
@Data
@TableName("bas_typical_process_check")
public class BasTypicalProcessCheck implements Serializable {
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
	/**参数名称*/
	@Excel(name = "参数名称", width = 15)
	@ApiModelProperty(value = "参数名称")
	private String paramName;
	/**目标值*/
	@Excel(name = "目标值", width = 15)
	@ApiModelProperty(value = "目标值")
	private String targetVal;
	/**控制线*/
	@Excel(name = "控制线", width = 15)
	@ApiModelProperty(value = "控制线")
	private String controlLine;
	/**抽样方案*/
	@Excel(name = "抽样方案", width = 15)
	@ApiModelProperty(value = "抽样方案")
	private String sampleDesign;
	/**检验方法*/
	@Excel(name = "检验方法", width = 15)
	@ApiModelProperty(value = "检验方法")
	private String checkMeth;
	/**批次合格判据*/
	@Excel(name = "批次合格判据", width = 15)
	@ApiModelProperty(value = "批次合格判据")
	private String batchQuaReason;
	/**masteroid-proc主表id*/
	@ApiModelProperty(value = "masteroid-proc主表id")
	private String masteroid;
	/**操作过程表ID*/
	@ApiModelProperty(value = "操作过程表ID")
	private String processId;

}
