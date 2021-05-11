package com.ckunion.modules.series.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: prod_series
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
@ApiModel(value="prod_series对象", description="prod_series")
@Data
@TableName("prod_series")
public class ProdSeries implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**系列名称*/
	@Excel(name = "系列名称", width = 15)
    @ApiModelProperty(value = "系列名称")
    private String seriesName;
	/**系列编号*/
	@Excel(name = "系列编号", width = 15)
    @ApiModelProperty(value = "系列编号")
    private String serialNumber;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**型号名称*/
	@Excel(name = "型号名称", width = 15)
    @ApiModelProperty(value = "型号名称")
    private String typemodelName;
    /**型号ID*/
    @Excel(name = "型号ID", width = 15)
    @ApiModelProperty(value = "型号ID")
    private String typemodelId;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**工作流id*/
	@Excel(name = "工作流id", width = 15)
    @ApiModelProperty(value = "工作流id")
    private String processinsid;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remark;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private String state;
	/**版本*/
	@Excel(name = "版本", width = 15)
    @ApiModelProperty(value = "版本")
    private String seriesVer;

    /**技术标准*/
    @Excel(name = "技术标准", width = 15)
    @ApiModelProperty(value = "技术标准")
    private String execStandard;
    /**质量等级*/
    @Excel(name = "质量等级", width = 15)
    @ApiModelProperty(value = "质量等级")
    private String quaLevel;
    /**标称频率*/
    @Excel(name = "标称频率", width = 15)
    @ApiModelProperty(value = "标称频率")
    private String nominalFrequency;

    /**父ID,分指标需要指明父ID*/
    @Excel(name = "父ID,分指标需要指明父ID", width = 15)
    @ApiModelProperty(value = "父ID,分指标需要指明父ID")
    private String parentoid;

    /**分指标指定BOM节点*/
    @Excel(name = "分指标指定BOM节点", width = 15)
    @ApiModelProperty(value = "分指标指定BOM节点")
    private String bomId;
    /**分指标指定BOM节点名称*/
    @Excel(name = "分指标指定BOM节点名称", width = 15)
    @ApiModelProperty(value = "分指标指定BOM节点名称")
    private String bomName;


    /**项目负责人*/
    @Excel(name = "项目负责人", width = 15)
    @ApiModelProperty(value = "项目负责人")
    private String manager;

    /**项目负责人姓名*/
    @Excel(name = "项目负责人姓名", width = 15)
    @ApiModelProperty(value = "项目负责人姓名")
    private String managerName;

    /**是否加入到产品结构0否1是*/
    @Excel(name = "是否加入到产品结构0否1是", width = 15)
    @ApiModelProperty(value = "是否加入到产品结构0否1是")
    private String isAddBom;


}
