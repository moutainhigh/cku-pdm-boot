package org.jeecg.modules.system.entity;

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
import java.util.Date;
import java.util.List;

/**
 * @Description: 业务功能字段表
 * @Author: jeecg-boot
 * @Date:   2020-10-10
 * @Version: V1.0
 */
@Data
@TableName("admin_field")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="admin_field对象", description="业务功能字段表")
public class AdminFieldVO implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**自定义字段英文标识*/
	@Excel(name = "自定义字段英文标识", width = 15)
    @ApiModelProperty(value = "自定义字段英文标识")
    private String fieldName;
	/**字段名称*/
	@Excel(name = "字段名称", width = 15)
    @ApiModelProperty(value = "字段名称")
    private String name;
	/**字段类型 1 单行文本 2 多行文本 3 单选*/
	@Excel(name = "字段类型 1 单行文本 2 多行文本 3 单选", width = 15)
    @ApiModelProperty(value = "字段类型 1 单行文本 2 多行文本 3 单选")
    private String inputType;
	/**字段属于哪个菜单*/
	@Excel(name = "字段属于哪个菜单", width = 20)
    @ApiModelProperty(value = "字段属于哪个菜单")
    private String menuId;
	/**所属部门*//*
	@Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
    private String remark;*/
	/**输入提示*/
	@Excel(name = "输入提示", width = 15)
    @ApiModelProperty(value = "输入提示")
    private String inputTips;
	/**最大长度*/
	@Excel(name = "最大长度", width = 15)
    @ApiModelProperty(value = "最大长度")
    private Integer maxLength;
	/**默认值*/
	@Excel(name = "默认值", width = 15)
    @ApiModelProperty(value = "默认值")
    private String defaultValue;
	/**是否唯一 1 是 0 否*/
	@Excel(name = "是否唯一 1 是 0 否", width = 15)
    @ApiModelProperty(value = "是否唯一 1 是 0 否")
    private Integer isUnique;
	/**是否必填 1 是 0 否*/
	@Excel(name = "是否必填 1 是 0 否", width = 15)
    @ApiModelProperty(value = "是否必填 1 是 0 否")
    private Integer isNull;
	/**排序 从小到大*/
	@Excel(name = "排序 从小到大", width = 15)
    @ApiModelProperty(value = "排序 从小到大")
    private Integer sorting;
	/**如果类型是选项，此处不能为空，多个选项以，隔开*/
	@Excel(name = "如果类型是选项，此处不能为空，多个选项以，隔开", width = 15)
    @ApiModelProperty(value = "如果类型是选项，此处不能为空，多个选项以，隔开")
    private String options;
	/**是否可以删除修改 0 改删 1 改 2 删 3 无*/
	@Excel(name = "是否可以删除修改 0 改删 1 改 2 删 3 无", width = 15)
    @ApiModelProperty(value = "是否可以删除修改 0 改删 1 改 2 删 3 无")
    private Integer operating;
	/**最后修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后修改时间")
    private Date updateTime;
	/**审批ID*/
	@Excel(name = "审批ID", width = 15)
    @ApiModelProperty(value = "审批ID")
    private Integer examineCategoryId;
	/**是否是自定义字段  0.自定义 1.固定*/
	@Excel(name = "是否是自定义字段  0.自定义 1.固定", width = 15)
    @ApiModelProperty(value = "是否是自定义字段  0.自定义 1.固定")
    private Integer fieldType;
	/**只有线索需要，装换客户的自定义字段ID*/
	@Excel(name = "只有线索需要，装换客户的自定义字段ID", width = 15)
    @ApiModelProperty(value = "只有线索需要，装换客户的自定义字段ID")
    private Integer relevant;
    /**只有线索需要，装换客户的自定义字段ID*/
    @Excel(name = "下拉列表内容", width = 15)
    @ApiModelProperty(value = "下拉列表内容")
    private List<String> setting;
}
