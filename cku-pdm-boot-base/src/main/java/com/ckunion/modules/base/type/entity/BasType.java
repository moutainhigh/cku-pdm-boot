package com.ckunion.modules.base.type.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 基础类型表
 * @Author: jeecg-boot
 * @Date:   2021-03-11
 * @Version: V1.0
 */
@Data
@TableName("bas_type")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bas_type对象", description="基础类型表")
public class BasType implements Serializable {
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
    /**基础类型*/
    @Excel(name = "基础类型", width = 15, dicCode = "basType")
    @Dict(dicCode = "basType")
    @ApiModelProperty(value = "basType")
    private String basType;
	/**类型编码*/
	@Excel(name = "类型编码", width = 15)
    @ApiModelProperty(value = "类型编码")
    private java.lang.String typeCode;
	/**类型名称*/
	@Excel(name = "类型名称", width = 15)
    @ApiModelProperty(value = "类型名称")
    private java.lang.String typeName;
	/**类型id*/
	@Excel(name = "类型id", width = 15)
    @ApiModelProperty(value = "类型id")
    private java.lang.String typePid;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
	/**状态*/
	private String state;

}
