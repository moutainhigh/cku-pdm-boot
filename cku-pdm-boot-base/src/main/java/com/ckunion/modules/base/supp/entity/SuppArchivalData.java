package com.ckunion.modules.base.supp.entity;

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
 * @Description: supp_archival_data
 * @Author: jeecg-boot
 * @Date:   2021-03-30
 * @Version: V1.0
 */
@Data
@TableName("supp_archival_data")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="supp_archival_data对象", description="supp_archival_data")
public class SuppArchivalData implements Serializable {
    private static final long serialVersionUID = 1L;

	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
    private java.lang.String phone;
	/**传真*/
	@Excel(name = "传真", width = 15)
    @ApiModelProperty(value = "传真")
    private java.lang.String tax;
	/**纳税号*/
	@Excel(name = "纳税号", width = 15)
    @ApiModelProperty(value = "纳税号")
    private java.lang.String taxationNo;
	/**有效期*/
	@Excel(name = "有效期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "有效期")
    private java.util.Date periodOfValidity;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入日期")
    private java.util.Date enterDate;
	/**联系人*/
	@Excel(name = "联系人", width = 15)
    @ApiModelProperty(value = "联系人")
    private java.lang.String contacts;
	/**供方账号*/
	@Excel(name = "供方账号", width = 15)
    @ApiModelProperty(value = "供方账号")
    private java.lang.String account;
	/**供方网址*/
	@Excel(name = "供方网址", width = 15)
    @ApiModelProperty(value = "供方网址")
    private java.lang.String website;
	/**境内/境外*/
	@Excel(name = "境内/境外", width = 15)
    @ApiModelProperty(value = "境内/境外")
    private java.lang.String isTerritory;
	/**组织机构代码*/
	@Excel(name = "组织机构代码", width = 15)
    @ApiModelProperty(value = "组织机构代码")
    private java.lang.String organizationCode;
	/**remark*/
	@Excel(name = "remark", width = 15)
    @ApiModelProperty(value = "remark")
    private java.lang.String remark;
	/**cdefine1*/
	@Excel(name = "cdefine1", width = 15)
    @ApiModelProperty(value = "cdefine1")
    private java.lang.String cdefine1;
	/**cdefine2*/
	@Excel(name = "cdefine2", width = 15)
    @ApiModelProperty(value = "cdefine2")
    private java.lang.String cdefine2;
	/**cdefine3*/
	@Excel(name = "cdefine3", width = 15)
    @ApiModelProperty(value = "cdefine3")
    private java.lang.String cdefine3;
	/**cdefine4*/
	@Excel(name = "cdefine4", width = 15)
    @ApiModelProperty(value = "cdefine4")
    private java.lang.String cdefine4;
	/**cdefine5*/
	@Excel(name = "cdefine5", width = 15)
    @ApiModelProperty(value = "cdefine5")
    private java.lang.String cdefine5;
	/**cdefine6*/
	@Excel(name = "cdefine6", width = 15)
    @ApiModelProperty(value = "cdefine6")
    private java.lang.String cdefine6;
	/**cdefine7*/
	@Excel(name = "cdefine7", width = 15)
    @ApiModelProperty(value = "cdefine7")
    private java.lang.String cdefine7;
	/**cdefine8*/
	@Excel(name = "cdefine8", width = 15)
    @ApiModelProperty(value = "cdefine8")
    private java.lang.String cdefine8;
	/**cdefine9*/
	@Excel(name = "cdefine9", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "cdefine9")
    private java.util.Date cdefine9;
	/**cdefine10*/
	@Excel(name = "cdefine10", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "cdefine10")
    private java.util.Date cdefine10;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
    private java.lang.String createBy;
	/**createName*/
	@Excel(name = "createName", width = 15)
    @ApiModelProperty(value = "createName")
    private java.lang.String createName;
	/**createDate*/
	@Excel(name = "createDate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createDate")
    private java.util.Date createDate;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
    private java.lang.String updateBy;
	/**updateName*/
	@Excel(name = "updateName", width = 15)
    @ApiModelProperty(value = "updateName")
    private java.lang.String updateName;
	/**updateDate*/
	@Excel(name = "updateDate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateDate")
    private java.util.Date updateDate;
	/**开户行账号*/
	@Excel(name = "开户行账号", width = 15)
    @ApiModelProperty(value = "开户行账号")
    private java.lang.String openingBankNo;
	/**开户行*/
	@Excel(name = "开户行", width = 15)
    @ApiModelProperty(value = "开户行")
    private java.lang.String openingBank;
	/**openingBank2*/
	@Excel(name = "openingBank2", width = 15)
    @ApiModelProperty(value = "openingBank2")
    private java.lang.String openingBank2;
	/**openingBankNo2*/
	@Excel(name = "openingBankNo2", width = 15)
    @ApiModelProperty(value = "openingBankNo2")
    private java.lang.String openingBankNo2;
	/**isProduct*/
	@Excel(name = "isProduct", width = 15)
    @ApiModelProperty(value = "isProduct")
    private java.lang.String isProduct;
	/**采购员，多个逗号分隔*/
	@Excel(name = "采购员，多个逗号分隔", width = 15)
    @ApiModelProperty(value = "采购员，多个逗号分隔")
    private java.lang.String buyers;
	/**采购员名称*/
	@Excel(name = "采购员名称", width = 15)
    @ApiModelProperty(value = "采购员名称")
    private java.lang.String buyersName;
	/**有效标志  0无效 1有效*/
	@Excel(name = "有效标志  0无效 1有效", width = 15)
    @ApiModelProperty(value = "有效标志  0无效 1有效")
    private java.lang.String effectiveFlag;
	/**电报*/
	@Excel(name = "电报", width = 15)
    @ApiModelProperty(value = "电报")
    private java.lang.String telegram;
	/**是否目录内0否1是*/
	@Excel(name = "是否目录内0否1是", width = 15)
    @ApiModelProperty(value = "是否目录内0否1是")
    private java.lang.String isInDirectory;
	/**工作流ID*/
	@Excel(name = "工作流ID", width = 15)
    @ApiModelProperty(value = "工作流ID")
    private java.lang.String processinsid;
	/**状态1审批中，2审批驳回，3审批完成*/
	@Excel(name = "状态1审批中，2审批驳回，3审批完成", width = 15)
    @ApiModelProperty(value = "状态1审批中，2审批驳回，3审批完成")
    private java.lang.String state;
	/**招标类型 1 招标 2非招标竞争性谈判，3非招标询价采购4非招标框架采购5非招标单一来源*/
	@Excel(name = "招标类型 1 招标 2非招标竞争性谈判，3非招标询价采购4非招标框架采购5非招标单一来源", width = 15)
    @ApiModelProperty(value = "招标类型 1 招标 2非招标竞争性谈判，3非招标询价采购4非招标框架采购5非招标单一来源")
    private java.lang.String tenderType;
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**简称*/
	@Excel(name = "简称", width = 15)
    @ApiModelProperty(value = "简称")
    private java.lang.String shortName;
	/**供方编码*/
	@Excel(name = "供方编码", width = 15)
    @ApiModelProperty(value = "供方编码")
    private java.lang.String supplierCode;
	/**供方名称*/
	@Excel(name = "供方名称", width = 15)
    @ApiModelProperty(value = "供方名称")
    private java.lang.String supplierName;
	/**是否合格供方 : 1、是
2、否*/
	@Excel(name = "是否合格供方 : 1、是 2、否", width = 15)
    @ApiModelProperty(value = "是否合格供方 : 1、是2、否")
    private java.lang.String isQualiSupplier;
	/**1、制造商名录
2、外包商名录
3、外协方名录
4、经销（代理）商名录*/
	@Excel(name = "1、制造商名录2、外包商名录3、外协方名录4、经销（代理）商名录", width = 15)
    @ApiModelProperty(value = "1、制造商名录2、外包商名录3、外协方名录4、经销（代理）商名录")
    private java.lang.String supplierType;
	/**1、新建
2、审理中
3、未通过
4、已准入
5、临时*/
	@Excel(name = "1、新建2、审理中3、未通过4、已准入5、临时", width = 15)
    @ApiModelProperty(value = "1、新建2、审理中3、未通过4、已准入5、临时")
    private java.lang.String supplierStatus;
	/**1、A类
2、B类
3、C类
4、D类*/
	@Excel(name = "1、A类2、B类3、C类4、D类", width = 15)
	@ApiModelProperty(value = "1、A类2、B类3、C类4、D类")
    private java.lang.String supplierLevel;
	/**1、I
2、II
3、III*/
	@Excel(name = "1、I2、II3、III", width = 15)
    @ApiModelProperty(value = "1、I2、II3、III")
    private java.lang.String supplierCategory;
	/**是否 科研 1、是
2、否*/
	@Excel(name = "是否 科研 1、是2、否", width = 15)
    @ApiModelProperty(value = "是否 科研 1、是2、否")
    private java.lang.String isResearch;
	/**省*/
	@Excel(name = "省", width = 15)
    @ApiModelProperty(value = "省")
    private java.lang.String province;
	/**法人代表*/
	@Excel(name = "法人代表", width = 15)
    @ApiModelProperty(value = "法人代表")
    private java.lang.String legalRepresentative;
	/**通讯地址*/
	@Excel(name = "通讯地址", width = 15)
    @ApiModelProperty(value = "通讯地址")
    private java.lang.String contactAddress;
	/**邮政编码*/
	@Excel(name = "邮政编码", width = 15)
    @ApiModelProperty(value = "邮政编码")
    private java.lang.String postalCode;
}
