package com.ckunion.modules.series.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
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
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 产品系列匹配字段实体类
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
@Data
@TableName("vw_product_series_mapping")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="vw_product_series_mapping对象", description="vw_product_series_mapping")
public class VwProductSeriesMapping implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**选择系列页面显示顺序*/
	@Excel(name = "seriesOrder", width = 15)
    @ApiModelProperty(value = "seriesOrder")
    private Integer seriesOrder;
	/**BAS_MATERIAL_INFO_COLUMN（物料字段统计表）主键id*/
	@Excel(name = "infoColumnId", width = 15)
    @ApiModelProperty(value = "infoColumnId")
    private String infoColumnId;
	/**型号表-主键id*/
	@Excel(name = "typeModelId", width = 15)
    @ApiModelProperty(value = "typeModelId")
    private String typeModelId;
	/**选中1选中*/
	@Excel(name = "checked", width = 15)
    @ApiModelProperty(value = "checked")
    private String checked;
	/**字段中文*/
	@Excel(name = "columnLabel", width = 15)
    @ApiModelProperty(value = "columnLabel")
    private String columnLabel;
	/**字段*/
	@Excel(name = "columnName", width = 15)
    @ApiModelProperty(value = "columnName")
    private String columnName;
	/**系列匹配规则,=/>/</<=/>=/±(范围),2静态字典单选，,6静态字典多选，7静态字典多选可编辑*/
	@Excel(name = "seriesRule", width = 15)
    @ApiModelProperty(value = "seriesRule")
    private String seriesRule;
	/**系列字段是否必填*/
	@Excel(name = "seriesRequire", width = 15)
    @ApiModelProperty(value = "seriesRequire")
    private String seriesRequire;
	/**类型  1 普通类型 、2 静态字典  3.日期*/
	@Excel(name = "columnDisplayType", width = 15)
    @ApiModelProperty(value = "columnDisplayType")
    private String columnDisplayType;
	/**字典,抓取值列表的参数*/
	@Excel(name = "columnDisplayParam", width = 15)
    @ApiModelProperty(value = "columnDisplayParam")
    private String columnDisplayParam;
}
