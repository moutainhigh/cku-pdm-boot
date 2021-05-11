package com.ckunion.modules.series.vo;

import java.util.List;
import com.ckunion.modules.series.entity.ProdSeries;
import com.ckunion.modules.series.entity.ProdSeriesField;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.Map;

import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: prod_series
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
@Data
@ApiModel(value="prod_seriesPage对象", description="prod_series")
public class ProdSeriesPage {

	/**id*/
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
	/**产品型号*/
	@Excel(name = "产品型号", width = 15)
	@ApiModelProperty(value = "产品型号")
	private String mapNo;
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

	/**型号名称*/
	@Excel(name = "型号名称", width = 15)
	@ApiModelProperty(value = "型号名称")
	private String typemodelName;
	/**型号ID*/
	@Excel(name = "型号ID", width = 15)
	@ApiModelProperty(value = "型号ID")
	private String typemodelId;

	/**父ID,分指标需要指明父ID*/
	@Excel(name = "父ID,分指标需要指明父ID", width = 15)
	@ApiModelProperty(value = "父ID,分指标需要指明父ID")
	private String parentoid;


	private Map<String,Object> prodSeriesFieldList;

}
