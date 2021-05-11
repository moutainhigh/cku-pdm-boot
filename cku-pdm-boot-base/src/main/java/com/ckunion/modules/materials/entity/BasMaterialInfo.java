package com.ckunion.modules.materials.entity;

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

import java.io.Serializable;

/**
 * @Description: 原材料基础信息
 * @Author: jeecg-boot
 * @Date:   2021-03-30
 * @Version: V1.0
 */
@Data
@TableName("bas_material_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bas_material_info对象", description="原材料基础信息")
public class BasMaterialInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**原材料编码*/
	@Excel(name = "原材料编码", width = 15)
    @ApiModelProperty(value = "原材料编码")
    private java.lang.String materialCode;
	/**原材料名称*/
	@Excel(name = "原材料名称", width = 15)
    @ApiModelProperty(value = "原材料名称")
    private java.lang.String materialName;
	/**原材料型号/牌号*/
	@Excel(name = "原材料型号/牌号", width = 15)
    @ApiModelProperty(value = "原材料型号/牌号")
    private java.lang.String mapNo;
	/**规格*/
	@Excel(name = "规格", width = 15)
    @ApiModelProperty(value = "规格")
    private java.lang.String model;
	/**质量等级*/
	@Excel(name = "质量等级", width = 15)
    @ApiModelProperty(value = "质量等级")
    private java.lang.String quaLevel;
	/**适用质量等级*/
	@Excel(name = "适用质量等级", width = 15)
    @ApiModelProperty(value = "适用质量等级")
    private java.lang.String applyQuaLevels;
	/**计量单位*/
	@Excel(name = "计量单位", width = 15)
    @ApiModelProperty(value = "计量单位")
    private java.lang.String unitName;
	/**生产厂家名称*/
	@Excel(name = "生产厂家名称", width = 15)
    @ApiModelProperty(value = "生产厂家名称")
    private java.lang.String manufacturerName;
	/**封装形式*/
	@Excel(name = "封装形式", width = 15)
    @ApiModelProperty(value = "封装形式")
    private java.lang.String encapsulation;
	/**外形尺寸*/
	@Excel(name = "外形尺寸", width = 15)
    @ApiModelProperty(value = "外形尺寸")
    private java.lang.String appearance;
	/**图纸号*/
	@Excel(name = "图纸号", width = 15)
    @ApiModelProperty(value = "图纸号")
    private java.lang.String cardNo;
	/**是否进口*/
	@Excel(name = "是否进口", width = 15)
    @ApiModelProperty(value = "是否进口")
    private java.lang.String isImport;
	/**最低库存*/
	@Excel(name = "最低库存", width = 15)
    @ApiModelProperty(value = "最低库存")
    private java.lang.Integer lowestQty;
	/**生产/非生产*/
	@Excel(name = "生产/非生产", width = 15)
    @ApiModelProperty(value = "生产/非生产")
    private java.lang.String isProduction;
	/**是否目录内*/
	@Excel(name = "是否目录内", width = 15)
    @ApiModelProperty(value = "是否目录内")
    private java.lang.String isDirectory;
	/**采购规范*/
	@Excel(name = "采购规范", width = 15)
    @ApiModelProperty(value = "采购规范")
    private java.lang.String purchaseStandard;
	/**是否检验*/
	@Excel(name = "是否检验", width = 15)
    @ApiModelProperty(value = "是否检验")
    private java.lang.String isCheck;
	/**进厂检验规范编号*/
	@Excel(name = "进厂检验规范编号", width = 15)
    @ApiModelProperty(value = "进厂检验规范编号")
    private java.lang.String inspectionSpecificationNo;
	/**复验文件编号*/
	@Excel(name = "复验文件编号", width = 15)
    @ApiModelProperty(value = "复验文件编号")
    private java.lang.String reSpecificationNo;

    /**类型名称*/
    @Excel(name = "类型名称", width = 15)
    @ApiModelProperty(value = "类型名称")
    private java.lang.String typeName;

    /**typeoid*/
    @ApiModelProperty(value = "类型oid")
    private java.lang.String typeoid;
}
