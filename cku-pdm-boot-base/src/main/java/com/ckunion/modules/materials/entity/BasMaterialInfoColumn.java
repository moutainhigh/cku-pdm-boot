package com.ckunion.modules.materials.entity;

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
 * @Description: bas_material_info_column
 * @Author: jeecg-boot
 * @Date:   2021-04-19
 * @Version: V1.0
 */
@Data
@TableName("bas_material_info_column")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bas_material_info_column对象", description="bas_material_info_column")
public class BasMaterialInfoColumn implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**字段中文*/
	@Excel(name = "字段中文", width = 15)
    @ApiModelProperty(value = "字段中文")
    private String columnLabel;
	/** 字段*/
	@Excel(name = " 字段", width = 15)
    @ApiModelProperty(value = " 字段")
    private String columnName;
	/**字段数据存储类型*/
	@Excel(name = "字段数据存储类型", width = 15)
    @ApiModelProperty(value = "字段数据存储类型")
    private String columnType;
	/**类型  1 普通类型 、2 静态字典 3 动态字典   4.日期  5. 金额*/
	@Excel(name = "类型  1 普通类型 、2 静态字典 3 动态字典   4.日期  5. 金额", width = 15)
    @Dict(dicCode = "columnType")
    @ApiModelProperty(value = "类型  1 普通类型 、2 静态字典 3 动态字典   4.日期  5. 金额")
    private String columnDisplayType;
	/**字典,抓取值列表的参数*/
	@Excel(name = "字典,抓取值列表的参数", width = 15)
    @ApiModelProperty(value = "字典,抓取值列表的参数")
    private String columnDisplayParam;
	/**列排序*/
	@Excel(name = "列排序", width = 15)
    @ApiModelProperty(value = "列排序")
    private Integer orderIndex;
	/**是否是产品属性 1 是 0不是*/
	@Excel(name = "是否是产品属性 1 是 0不是", width = 15)
    @Dict(dicCode = "YesOrNo")
    @ApiModelProperty(value = "是否是产品属性 1 是 0不是")
    private String isProduct;
	/**是否是系列匹配属性 1是0或空不是*/
	@Excel(name = "是否是系列匹配属性 1是0或空不是", width = 15, dicCode = "YesOrNo")
    @Dict(dicCode = "YesOrNo")
    @ApiModelProperty(value = "是否是系列匹配属性 1是0或空不是")
    private String isSeries;
	/**选择系列页面显示顺序*/
	@Excel(name = "选择系列页面显示顺序", width = 15)
    @ApiModelProperty(value = "选择系列页面显示顺序")
    private Integer seriesOrder;
	/**系列匹配规则,=/>/</<=/>=/±(范围),2静态字典单选，,6静态字典多选，7静态字典多选可编辑*/
	@Excel(name = "系列匹配规则,=/>/</<=/>=/±(范围),2静态字典单选，,6静态字典多选，7静态字典多选可编辑", width = 15)
    @ApiModelProperty(value = "系列匹配规则,=/>/</<=/>=/±(范围),2静态字典单选，,6静态字典多选，7静态字典多选可编辑")
    private String seriesRule;
	/**nullable*/
	@Excel(name = "nullable", width = 15)
    @ApiModelProperty(value = "nullable")
    private String nullable;
	/**系列字段是否必填*/
	@Excel(name = "系列字段是否必填", width = 15)
    @Dict(dicCode = "YesOrNo")
    @ApiModelProperty(value = "系列字段是否必填")
    private String seriesRequire;
}
