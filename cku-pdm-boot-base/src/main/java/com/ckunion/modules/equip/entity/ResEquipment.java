package com.ckunion.modules.equip.entity;

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
 * @Description: res_equipment
 * @Author: jeecg-boot
 * @Date:   2021-04-08
 * @Version: V1.0
 */
@Data
@TableName("res_equipment")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="res_equipment对象", description="res_equipment")
public class ResEquipment implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private java.lang.String id;
	/**类型*/
	@Excel(name = "类型", width = 15)
    @ApiModelProperty(value = "类型")
    private java.lang.String type;
	/**设备编码*/
	@Excel(name = "设备编码", width = 15)
    @ApiModelProperty(value = "设备编码")
    private java.lang.String equipmentNo;
	/**设备名称*/
	@Excel(name = "设备名称", width = 15)
    @ApiModelProperty(value = "设备名称")
    private java.lang.String equipmentName;
	/**设备型号*/
	@Excel(name = "设备型号", width = 15)
    @ApiModelProperty(value = "设备型号")
    private java.lang.String model;
	/**设备状态*/
	@Excel(name = "设备状态", width = 15)
    @ApiModelProperty(value = "设备状态")
    private java.lang.String state;
	/**启用日期*/
	@Excel(name = "启用日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "启用日期")
    private java.util.Date startDate;
	/**生产日期*/
	@Excel(name = "生产日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "生产日期")
    private java.util.Date prodDate;
	/**入库日期*/
	@Excel(name = "入库日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入库日期")
    private java.util.Date putinDate;
	/**设备分类*/
	@Excel(name = "设备分类", width = 15)
    @ApiModelProperty(value = "设备分类")
    private java.lang.String equipmenttypeId;
	/**供应商*/
	@Excel(name = "供应商", width = 15)
    @ApiModelProperty(value = "供应商")
    private java.lang.String supply;
	/**制造商*/
	@Excel(name = "制造商", width = 15)
    @ApiModelProperty(value = "制造商")
    private java.lang.String manufacturer;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private java.lang.String description;
	/**存放位置*/
	@Excel(name = "存放位置", width = 15)
    @ApiModelProperty(value = "存放位置")
    private java.lang.String place;
	/**出厂日期*/
	@Excel(name = "出厂日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出厂日期")
    private java.util.Date leaveDate;
	/**有效期*/
	@Excel(name = "有效期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "有效期")
    private java.util.Date validity;
	/**使用单位*/
	@Excel(name = "使用单位", width = 15)
    @ApiModelProperty(value = "使用单位")
    private java.lang.String createDepartmentid;
	/**使用单位名称*/
	@Excel(name = "使用单位名称", width = 15)
    @ApiModelProperty(value = "使用单位名称")
    private java.lang.String createDepartmentname;
	/**限用原因*/
	@Excel(name = "限用原因", width = 15)
    @ApiModelProperty(value = "限用原因")
    private java.lang.String restrictcause;
	/**限用范围*/
	@Excel(name = "限用范围", width = 15)
    @ApiModelProperty(value = "限用范围")
    private java.lang.String restrictscope;
	/**设备用途*/
	@Excel(name = "设备用途", width = 15)
    @ApiModelProperty(value = "设备用途")
    private java.lang.String purpose;
	/**责任人*/
	@Excel(name = "责任人", width = 15)
    @ApiModelProperty(value = "责任人")
    private java.lang.String workId;
	/**责任人姓名*/
	@Excel(name = "责任人姓名", width = 15)
    @ApiModelProperty(value = "责任人姓名")
    private java.lang.String workname;
	/**原值*/
	@Excel(name = "原值", width = 15)
    @ApiModelProperty(value = "原值")
    private java.lang.String originalvalue;
	/**出厂编号*/
	@Excel(name = "出厂编号", width = 15)
    @ApiModelProperty(value = "出厂编号")
    private java.lang.String leavecode;
	/**使用零件图号*/
	@Excel(name = "使用零件图号", width = 15)
    @ApiModelProperty(value = "使用零件图号")
    private java.lang.String usepartno;
	/**零（部）整件图纸版次*/
	@Excel(name = "零（部）整件图纸版次", width = 15)
    @ApiModelProperty(value = "零（部）整件图纸版次")
    private java.lang.String usepartmapVersion;
	/**使用零件名称*/
	@Excel(name = "使用零件名称", width = 15)
    @ApiModelProperty(value = "使用零件名称")
    private java.lang.String usepartname;
	/**工艺过程名称*/
	@Excel(name = "工艺过程名称", width = 15)
    @ApiModelProperty(value = "工艺过程名称")
    private java.lang.String usepartProc;
	/**工装使用方法*/
	@Excel(name = "工装使用方法", width = 15)
    @ApiModelProperty(value = "工装使用方法")
    private java.lang.String useMethod;
	/**当前领用人姓名*/
	@Excel(name = "当前领用人姓名", width = 15)
    @ApiModelProperty(value = "当前领用人姓名")
    private java.lang.String neckuser;
	/**设计寿命*/
	@Excel(name = "设计寿命", width = 15)
    @ApiModelProperty(value = "设计寿命")
    private java.lang.String planlife;
	/**可使用寿命*/
	@Excel(name = "可使用寿命", width = 15)
    @ApiModelProperty(value = "可使用寿命")
    private java.lang.String remainderlife;
	/**预警时间*/
	@Excel(name = "预警时间", width = 15)
    @ApiModelProperty(value = "预警时间")
    private java.lang.String warntime;
	/**库存数量*/
	@Excel(name = "库存数量", width = 15)
    @ApiModelProperty(value = "库存数量")
    private java.lang.Integer storeNum;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建人姓名*/
	@Excel(name = "创建人姓名", width = 15)
    @ApiModelProperty(value = "创建人姓名")
    private java.lang.String createName;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**最后修改人*/
    @ApiModelProperty(value = "最后修改人")
    private java.lang.String updateBy;
	/**最后修改人姓名*/
	@Excel(name = "最后修改人姓名", width = 15)
    @ApiModelProperty(value = "最后修改人姓名")
    private java.lang.String updateName;
	/**最后修改时间*/
	@Excel(name = "最后修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最后修改时间")
    private java.util.Date updateTime;
	/**自定义项1*/
	@Excel(name = "自定义项1", width = 15)
    @ApiModelProperty(value = "自定义项1")
    private java.lang.String cdefine1;
	/**自定义项2*/
	@Excel(name = "自定义项2", width = 15)
    @ApiModelProperty(value = "自定义项2")
    private java.lang.String cdefine2;
	/**自定义项3*/
	@Excel(name = "自定义项3", width = 15)
    @ApiModelProperty(value = "自定义项3")
    private java.lang.String cdefine3;
	/**自定义项4*/
	@Excel(name = "自定义项4", width = 15)
    @ApiModelProperty(value = "自定义项4")
    private java.lang.String cdefine4;
	/**自定义项5*/
	@Excel(name = "自定义项5", width = 15)
    @ApiModelProperty(value = "自定义项5")
    private java.lang.String cdefine5;
	/**设备编码*/
	@Excel(name = "设备编码", width = 15)
    @ApiModelProperty(value = "设备编码")
    private java.lang.String equipmentNoBymes;
	/**是否为采集 0否，1是*/
	@Excel(name = "是否为采集 0否，1是", width = 15)
    @ApiModelProperty(value = "是否为采集 0否，1是")
    private java.lang.String isDds;
	/**operator*/
	@Excel(name = "operator", width = 15)
    @ApiModelProperty(value = "operator")
    private java.lang.String operator;
	/**operatorName*/
	@Excel(name = "operatorName", width = 15)
    @ApiModelProperty(value = "operatorName")
    private java.lang.String operatorName;
	/**maintainId*/
	@Excel(name = "maintainId", width = 15)
    @ApiModelProperty(value = "maintainId")
    private java.lang.String maintainId;
	/**DDS-使用*/
	@Excel(name = "DDS-使用", width = 15)
    @ApiModelProperty(value = "DDS-使用")
    private java.lang.String modelid;
	/**oid*/
	@Excel(name = "oid", width = 15)
    @ApiModelProperty(value = "oid")
    private java.lang.String oid;
	/**检定依据*/
	@Excel(name = "检定依据", width = 15)
    @ApiModelProperty(value = "检定依据")
    private java.lang.String examineAccordingAs;
	/**检定周期*/
	@Excel(name = "检定周期", width = 15)
    @ApiModelProperty(value = "检定周期")
    private java.lang.String examinePeriods;
	/**是否删除0删除，1未删除（删除设备只修改此字段的值，不删除数据库信息）*/
	@Excel(name = "是否删除0删除，1未删除（删除设备只修改此字段的值，不删除数据库信息）", width = 15)
    @ApiModelProperty(value = "是否删除0删除，1未删除（删除设备只修改此字段的值，不删除数据库信息）")
    private java.lang.String isDelete;
	/**lineid*/
	@Excel(name = "lineid", width = 15)
    @ApiModelProperty(value = "lineid")
    private java.lang.String lineid;
	/**abcType*/
	@Excel(name = "abcType", width = 15)
    @ApiModelProperty(value = "abcType")
    private java.lang.String abcType;
	/**fixedAssetCode*/
	@Excel(name = "fixedAssetCode", width = 15)
    @ApiModelProperty(value = "fixedAssetCode")
    private java.lang.String fixedAssetCode;
	/**examineDate*/
	@Excel(name = "examineDate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "examineDate")
    private java.util.Date examineDate;
	/**paramSetting*/
	@Excel(name = "paramSetting", width = 15)
    @ApiModelProperty(value = "paramSetting")
    private java.lang.String paramSetting;
	/**measuredExtent*/
	@Excel(name = "measuredExtent", width = 15)
    @ApiModelProperty(value = "measuredExtent")
    private java.lang.String measuredExtent;
	/**unit*/
	@Excel(name = "unit", width = 15)
    @ApiModelProperty(value = "unit")
    private java.lang.String unit;
	/**assetsCode*/
	@Excel(name = "assetsCode", width = 15)
    @ApiModelProperty(value = "assetsCode")
    private java.lang.String assetsCode;
	/**isSpecial*/
	@Excel(name = "isSpecial", width = 15)
    @ApiModelProperty(value = "isSpecial")
    private java.lang.String isSpecial;
	/**specialTime*/
	@Excel(name = "specialTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "specialTime")
    private java.util.Date specialTime;
	/**meteringPeriod*/
	@Excel(name = "meteringPeriod", width = 15)
    @ApiModelProperty(value = "meteringPeriod")
    private java.lang.String meteringPeriod;
	/**military*/
	@Excel(name = "military", width = 15)
    @ApiModelProperty(value = "military")
    private java.lang.String military;
	/**process1*/
	@Excel(name = "process1", width = 15)
    @ApiModelProperty(value = "process1")
    private java.lang.String process1;
	/**process2*/
	@Excel(name = "process2", width = 15)
    @ApiModelProperty(value = "process2")
    private java.lang.String process2;
	/**process3*/
	@Excel(name = "process3", width = 15)
    @ApiModelProperty(value = "process3")
    private java.lang.String process3;
	/**classify*/
	@Excel(name = "classify", width = 15)
    @ApiModelProperty(value = "classify")
    private java.lang.String classify;
	/**protectFile*/
	@Excel(name = "protectFile", width = 15)
    @ApiModelProperty(value = "protectFile")
    private java.lang.String protectFile;
	/**protectPath*/
	@Excel(name = "protectPath", width = 15)
    @ApiModelProperty(value = "protectPath")
    private java.lang.String protectPath;
	/**testFile*/
	@Excel(name = "testFile", width = 15)
    @ApiModelProperty(value = "testFile")
    private java.lang.String testFile;
	/**testPath*/
	@Excel(name = "testPath", width = 15)
    @ApiModelProperty(value = "testPath")
    private java.lang.String testPath;
	/**paramFile*/
	@Excel(name = "paramFile", width = 15)
    @ApiModelProperty(value = "paramFile")
    private java.lang.String paramFile;
	/**paramPath*/
	@Excel(name = "paramPath", width = 15)
    @ApiModelProperty(value = "paramPath")
    private java.lang.String paramPath;
	/**specialFile*/
	@Excel(name = "specialFile", width = 15)
    @ApiModelProperty(value = "specialFile")
    private java.lang.String specialFile;
	/**specialPath*/
	@Excel(name = "specialPath", width = 15)
    @ApiModelProperty(value = "specialPath")
    private java.lang.String specialPath;
	/**confirmFile*/
	@Excel(name = "confirmFile", width = 15)
    @ApiModelProperty(value = "confirmFile")
    private java.lang.String confirmFile;
	/**confirmPath*/
	@Excel(name = "confirmPath", width = 15)
    @ApiModelProperty(value = "confirmPath")
    private java.lang.String confirmPath;
	/**yg*/
	@Excel(name = "yg", width = 15)
    @ApiModelProperty(value = "yg")
    private java.lang.String yg;
	/**process*/
	@Excel(name = "process", width = 15)
    @ApiModelProperty(value = "process")
    private java.lang.String process;
	/**maintOneGroupId*/
	@Excel(name = "maintOneGroupId", width = 15)
    @ApiModelProperty(value = "maintOneGroupId")
    private java.lang.String maintOneGroupId;
	/**maintTwoGroupId*/
	@Excel(name = "maintTwoGroupId", width = 15)
    @ApiModelProperty(value = "maintTwoGroupId")
    private java.lang.String maintTwoGroupId;
	/**repairGroupId*/
	@Excel(name = "repairGroupId", width = 15)
    @ApiModelProperty(value = "repairGroupId")
    private java.lang.String repairGroupId;
	/**checkGroupId*/
	@Excel(name = "checkGroupId", width = 15)
    @ApiModelProperty(value = "checkGroupId")
    private java.lang.String checkGroupId;
	/**meteringGroupId*/
	@Excel(name = "meteringGroupId", width = 15)
    @ApiModelProperty(value = "meteringGroupId")
    private java.lang.String meteringGroupId;
	/**检定有效期*/
	@Excel(name = "检定有效期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "检定有效期")
    private java.util.Date checkValidityDate;
	/**检定状态 0检定完成  1预警器 2 超期待鉴定3待确认*/
	@Excel(name = "检定状态 0检定完成  1预警器 2 超期待鉴定3待确认", width = 15)
    @ApiModelProperty(value = "检定状态 0检定完成  1预警器 2 超期待鉴定3待确认")
    private java.lang.String checkState;
	/**计量状态 0检定完成  1预警器 2 超期待鉴定3待确认*/
	@Excel(name = "计量状态 0检定完成  1预警器 2 超期待鉴定3待确认", width = 15)
    @ApiModelProperty(value = "计量状态 0检定完成  1预警器 2 超期待鉴定3待确认")
    private java.lang.String calculateState;
	/**检定人登录名*/
	@Excel(name = "检定人登录名", width = 15)
    @ApiModelProperty(value = "检定人登录名")
    private java.lang.String checker;
	/**检定人名称*/
	@Excel(name = "检定人名称", width = 15)
    @ApiModelProperty(value = "检定人名称")
    private java.lang.String checkerName;
}
