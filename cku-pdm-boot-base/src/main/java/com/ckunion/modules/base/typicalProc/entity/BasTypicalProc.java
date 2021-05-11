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
 * @Description: 典型工艺库 主表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@ApiModel(value="bas_typical_proc对象", description="典型工艺库")
@Data
@TableName("bas_typical_proc")
public class BasTypicalProc implements Serializable {
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
	/**工序编码*/
	@Excel(name = "工序编码", width = 15)
    @ApiModelProperty(value = "工序编码")
    private String workprocCode;
	/**工序名称*/
	@Excel(name = "工序名称", width = 15)
    @ApiModelProperty(value = "工序名称")
    private String workprocName;
	/**工序类型*/
	@Excel(name = "工序类型", width = 15)
    @ApiModelProperty(value = "工序类型")
    private String procType;
	/**单品生产*/
	@Excel(name = "单品生产", width = 15)
    @ApiModelProperty(value = "单品生产")
    private String singleProc;
	/**版本*/
	@Excel(name = "版本", width = 15)
    @ApiModelProperty(value = "版本")
    private String procVersion;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private String procState;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String procRemark;

    /**工作流id*/
    @Excel(name = "工作流id", width = 15)
    @ApiModelProperty(value = "工作流id")
    private String processinsid;


    /**工艺名称*/
    @Excel(name = "工艺名称", width = 15)
    @ApiModelProperty(value = "工艺名称")
    private String procName;
    /**图号*/
    @Excel(name = "图号", width = 15)
    @ApiModelProperty(value = "图号")
    private String mapNo;
    /**质量等级*/
    @Excel(name = "质量等级", width = 15)
    @ApiModelProperty(value = "质量等级")
    private String quaLevel;

}
