package com.ckunion.modules.series.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: prod_series_field
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
@ApiModel(value="prod_series对象", description="prod_series")
@Data
@TableName("prod_series_field")
public class ProdSeriesField implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;
	/**中文字段*/
	@Excel(name = "中文字段", width = 15)
	@ApiModelProperty(value = "中文字段")
	private String columnLabel;
	/**英文字段*/
	@Excel(name = "英文字段", width = 15)
	@ApiModelProperty(value = "英文字段")
	private String columnName;
	/**字段内容*/
	@Excel(name = "字段内容", width = 15)
	@ApiModelProperty(value = "字段内容")
	private String columnVal;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
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
	/**masteroid*/
	@ApiModelProperty(value = "masteroid")
	private String masteroid;
	/**是否截取字段*/
	@ApiModelProperty(value = "是否截取字段")
	private String isSection;

}
