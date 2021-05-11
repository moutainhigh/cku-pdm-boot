package com.ckunion.modules.materials.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @Description: bas_material_type
 * @Author: jeecg-boot
 * @Date:   2020-10-15
 * @Version: V1.0
 */
@Data
@TableName("bas_material_type")
@ApiModel(value="bas_material_type对象", description="bas_material_type")
public class BasMaterialTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;
	@Excel(name = "助记码", width = 15)
	@ApiModelProperty(value = "助记码")
	private String cap;
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
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;

	/**状态:0启用 1停用*/
	@Excel(name = "状态:0启用 1停用", width = 15, dicCode = "is_enable")
	@Dict(dicCode = "is_enable")
	@ApiModelProperty(value = "状态:0启用 1停用")
	private Integer state;
	/**有效年限*/
	@Excel(name = "有效年限", width = 15)
	@ApiModelProperty(value = "有效年限")
	private Integer effectYear;
	/**类型编码*/
	@Excel(name = "类型编码", width = 15)
	@ApiModelProperty(value = "类型编码")
	private String typeCode;
	/**类型名称*/
	@Excel(name = "类型名称", width = 15)
	@ApiModelProperty(value = "类型名称")
	private String typeName;
	/**是否根节点:0根节点,1非根节点*/
	@Excel(name = "是否根节点:0根节点,1非根节点", width = 15)
	@ApiModelProperty(value = "是否根节点:0根节点,1非根节点")
	private String isRoot;
	/**节点层次码,比如000 000001 000000001*/
	@Excel(name = "节点层次码,比如000 000001 000000001", width = 15)
	@ApiModelProperty(value = "节点层次码,比如000 000001 000000001")
	private String nodeId;
	/**节点层次,比如001  层次是1, 000001 层次是2, 000000001 层次是3*/
	@Excel(name = "节点层次,比如001  层次是1, 000001 层次是2, 000000001 层次是3", width = 15)
	@ApiModelProperty(value = "节点层次,比如001  层次是1, 000001 层次是2, 000000001 层次是3")
	private Integer idClass;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String remark;
	/**默认库房*/
	@Excel(name = "默认库房", width = 15)
	@ApiModelProperty(value = "默认库房")
	private String defhouse;
	/**检验员*/
	@Excel(name = "检验员", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@ApiModelProperty(value = "检验员")
	private String inspector;
	/**检验员姓名*/
	@Excel(name = "检验员姓名", width = 15)
	@ApiModelProperty(value = "检验员姓名")
	private String inspectorName;
	/**是否检验*/
	@Excel(name = "是否检验", width = 15)
	@ApiModelProperty(value = "是否检验")
	private String isTest;
	/**ERP同步专用*/
	@Excel(name = "ERP同步专用", width = 15)
	@ApiModelProperty(value = "ERP同步专用")
	private String typeNewname;
	/**0未同步1已同步,发生更新后设置为0未同步*/
	@Excel(name = "0未同步1已同步,发生更新后设置为0未同步", width = 15)
	@ApiModelProperty(value = "0未同步1已同步,发生更新后设置为0未同步")
	private String integrateState;
	/**调度员*/
	@Excel(name = "调度员", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@ApiModelProperty(value = "调度员")
	private String dispatchUser;
	/**采购员*/
	@Excel(name = "采购员", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@ApiModelProperty(value = "采购员")
	private String purchaseUser;
	/**技术员*/
	@Excel(name = "技术员", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@ApiModelProperty(value = "技术员")
	private String technologyUser;
	/**打印排产单类型*/
	@Excel(name = "打印排产单类型", width = 15, dicCode = "print_type")
	@Dict(dicCode = "print_type")
	@ApiModelProperty(value = "打印排产单类型")
	private String printType;
	/**库房分类*/
	@Excel(name = "库房分类", width = 15)
	@ApiModelProperty(value = "库房分类")
	private String houseType;
	/**父级节点*/
	@Excel(name = "父级节点", width = 15)
	@ApiModelProperty(value = "父级节点")
	private String pid;
	/**是否有子节点*/
	@Excel(name = "是否有子节点", width = 15)
	@ApiModelProperty(value = "是否有子节点")
	private String hasChild;
	//附加字段
	/**是否有子节点*/
	@Excel(name = "附加字段1", width = 15)
	@ApiModelProperty(value = "附加字段")
	private Map ext;
}
