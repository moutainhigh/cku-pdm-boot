package com.ckunion.modules.base.tool.entity;

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
 * @Description: res_equipment_tool
 * @Author: jeecg-boot
 * @Date:   2021-04-08
 * @Version: V1.0
 */
@Data
@TableName("res_equipment_tool")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="res_equipment_tool对象", description="res_equipment_tool")
public class ResEquipmentTool implements Serializable {
    private static final long serialVersionUID = 1L;

	/**库房名称*/
	@Excel(name = "库房名称", width = 15)
    @ApiModelProperty(value = "库房名称")
    private String warehouseName;
	/**库房编码*/
	@Excel(name = "库房编码", width = 15)
    @ApiModelProperty(value = "库房编码")
    private String warehouseCode;
	/**库区编码*/
	@Excel(name = "库区编码", width = 15)
    @ApiModelProperty(value = "库区编码")
    private String wareareaCode;
	/**复检周期*/
	@Excel(name = "复检周期", width = 15)
    @ApiModelProperty(value = "复检周期")
    private Integer recheckDate;
	/**复验周期单位*/
	@Excel(name = "复验周期单位", width = 15)
    @ApiModelProperty(value = "复验周期单位")
    private String recheckDateUnit;
	/**牌号*/
	@Excel(name = "牌号", width = 15)
    @ApiModelProperty(value = "牌号")
    private String makings;
	/**适用等级*/
	@Excel(name = "适用等级", width = 15)
    @ApiModelProperty(value = "适用等级")
    private String applyLevels;
	/**是否检验*/
	@Excel(name = "是否检验", width = 15)
    @ApiModelProperty(value = "是否检验")
    private String isCheck;
	/**维护班组id*/
	@Excel(name = "维护班组id", width = 15)
    @ApiModelProperty(value = "维护班组id")
    private String maintGroupId;
	/**allowUseNum*/
	@Excel(name = "allowUseNum", width = 15)
    @ApiModelProperty(value = "allowUseNum")
    private Integer allowUseNum;
	/**图纸号*/
	@Excel(name = "图纸号", width = 15)
    @ApiModelProperty(value = "图纸号")
    private String cardNo;
	/**验证准则号*/
	@Excel(name = "验证准则号", width = 15)
    @ApiModelProperty(value = "验证准则号")
    private String validateNo;
	/**维护规则号*/
	@Excel(name = "维护规则号", width = 15)
    @ApiModelProperty(value = "维护规则号")
    private String protectNo;
	/**是否长期*/
	@Excel(name = "是否长期", width = 15)
    @ApiModelProperty(value = "是否长期")
    private String longValidateState;
	/**protectFile*/
	@Excel(name = "protectFile", width = 15)
    @ApiModelProperty(value = "protectFile")
    private String protectFile;
	/**protectPath*/
	@Excel(name = "protectPath", width = 15)
    @ApiModelProperty(value = "protectPath")
    private String protectPath;
	/**供应商id*/
	@Excel(name = "供应商id", width = 15)
    @ApiModelProperty(value = "供应商id")
    private String supplyId;
	/**库区名称*/
	@Excel(name = "库区名称", width = 15)
    @ApiModelProperty(value = "库区名称")
    private String wareareaName;
	/**检定单位*/
	@Excel(name = "检定单位", width = 15)
    @ApiModelProperty(value = "检定单位")
    private String originalvalue;
	/**出厂编号*/
	@Excel(name = "出厂编号", width = 15)
    @ApiModelProperty(value = "出厂编号")
    private String leavecode;
	/**使用零件图号 /工装图号*/
	@Excel(name = "使用零件图号 /工装图号", width = 15)
    @ApiModelProperty(value = "使用零件图号 /工装图号")
    private String usepartno;
	/**零（部）整件图纸版次*/
	@Excel(name = "零（部）整件图纸版次", width = 15)
    @ApiModelProperty(value = "零（部）整件图纸版次")
    private String usepartmapVersion;
	/**使用零件名称*/
	@Excel(name = "使用零件名称", width = 15)
    @ApiModelProperty(value = "使用零件名称")
    private String usepartname;
	/**工艺过程名称*/
	@Excel(name = "工艺过程名称", width = 15)
    @ApiModelProperty(value = "工艺过程名称")
    private String usepartProc;
	/**工装使用方法*/
	@Excel(name = "工装使用方法", width = 15)
    @ApiModelProperty(value = "工装使用方法")
    private String useMethod;
	/**当前领用人姓名*/
	@Excel(name = "当前领用人姓名", width = 15)
    @ApiModelProperty(value = "当前领用人姓名")
    private String neckuser;
	/**设计寿命*/
	@Excel(name = "设计寿命", width = 15)
    @ApiModelProperty(value = "设计寿命")
    private String planlife;
	/**可使用寿命*/
	@Excel(name = "可使用寿命", width = 15)
    @ApiModelProperty(value = "可使用寿命")
    private String remainderlife;
	/**预警时间*/
	@Excel(name = "预警时间", width = 15)
    @ApiModelProperty(value = "预警时间")
    private String warntime;
	/**库存数量*/
	@Excel(name = "库存数量", width = 15)
    @ApiModelProperty(value = "库存数量")
    private Integer storeNum;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
    private String createBy;
	/**createName*/
	@Excel(name = "createName", width = 15)
    @ApiModelProperty(value = "createName")
    private String createName;
	/**createDate*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
    private Date createTime;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
    private String updateBy;
	/**updateName*/
	@Excel(name = "updateName", width = 15)
    @ApiModelProperty(value = "updateName")
    private String updateName;
	/**updateDate*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
    private Date updateTime;
	/**cdefine1*/
	@Excel(name = "cdefine1", width = 15)
    @ApiModelProperty(value = "cdefine1")
    private String cdefine1;
	/**cdefine2*/
	@Excel(name = "cdefine2", width = 15)
    @ApiModelProperty(value = "cdefine2")
    private String cdefine2;
	/**cdefine3*/
	@Excel(name = "cdefine3", width = 15)
    @ApiModelProperty(value = "cdefine3")
    private String cdefine3;
	/**cdefine4*/
	@Excel(name = "cdefine4", width = 15)
    @ApiModelProperty(value = "cdefine4")
    private String cdefine4;
	/**cdefine5*/
	@Excel(name = "cdefine5", width = 15)
    @ApiModelProperty(value = "cdefine5")
    private String cdefine5;
	/**equipmentNoBymes*/
	@Excel(name = "equipmentNoBymes", width = 15)
    @ApiModelProperty(value = "equipmentNoBymes")
    private String equipmentNoBymes;
	/**是否为采集 0否，1是*/
	@Excel(name = "是否为采集 0否，1是", width = 15)
    @ApiModelProperty(value = "是否为采集 0否，1是")
    private String isDds;
	/**运维责任人*/
	@Excel(name = "运维责任人", width = 15)
    @ApiModelProperty(value = "运维责任人")
    private String operator;
	/**运维责任人姓名*/
	@Excel(name = "运维责任人姓名", width = 15)
    @ApiModelProperty(value = "运维责任人姓名")
    private String operatorName;
	/**维修记录*/
	@Excel(name = "维修记录", width = 15)
    @ApiModelProperty(value = "维修记录")
    private String maintainId;
	/**modelid*/
	@Excel(name = "modelid", width = 15)
    @ApiModelProperty(value = "modelid")
    private String modelid;
	/**oid*/
	@Excel(name = "oid", width = 15)
    @ApiModelProperty(value = "oid")
    private String oid;
	/**管理方式,0:批次,1:单只*/
	@Excel(name = "管理方式,0:批次,1:单只", width = 15)
    @ApiModelProperty(value = "管理方式,0:批次,1:单只")
    private String useType;
	/**yg*/
	@Excel(name = "yg", width = 15)
    @ApiModelProperty(value = "yg")
    private String yg;
	/**normal*/
	@Excel(name = "normal", width = 15)
    @ApiModelProperty(value = "normal")
    private String normal;
	/**工装类别*/
	@Excel(name = "工装类别", width = 15)
    @ApiModelProperty(value = "工装类别")
    private String classify;
	/**所属标准工序*/
	@Excel(name = "所属标准工序", width = 15)
    @ApiModelProperty(value = "所属标准工序")
    private String procName;
	/**适用产品型号*/
	@Excel(name = "适用产品型号", width = 15)
    @ApiModelProperty(value = "适用产品型号")
    private String seriesName;
	/**mapNoFile*/
	@Excel(name = "mapNoFile", width = 15)
    @ApiModelProperty(value = "mapNoFile")
    private String mapNoFile;
	/**mapNoPath*/
	@Excel(name = "mapNoPath", width = 15)
    @ApiModelProperty(value = "mapNoPath")
    private String mapNoPath;
	/**validateFile*/
	@Excel(name = "validateFile", width = 15)
    @ApiModelProperty(value = "validateFile")
    private String validateFile;
	/**validatePath*/
	@Excel(name = "validatePath", width = 15)
    @ApiModelProperty(value = "validatePath")
    private String validatePath;
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**type*/
	@Excel(name = "type", width = 15)
    @ApiModelProperty(value = "type")
    private String type;
	/**固定资产编码*/
	@Excel(name = "固定资产编码", width = 15)
    @ApiModelProperty(value = "固定资产编码")
    private String equipmentNo;
	/**仪器名称*/
	@Excel(name = "仪器名称", width = 15)
    @ApiModelProperty(value = "仪器名称")
    private String equipmentName;
	/**仪器型号*/
	@Excel(name = "仪器型号", width = 15)
    @ApiModelProperty(value = "仪器型号")
    private String model;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private String state;
	/**启用日期*/
	@Excel(name = "启用日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "启用日期")
    private Date startDate;
	/**生产日期*/
	@Excel(name = "生产日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "生产日期")
    private Date prodDate;
	/**入库日期*/
	@Excel(name = "入库日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入库日期")
    private Date putinDate;
	/**仪器类型ID*/
	@Excel(name = "仪器类型ID", width = 15)
    @ApiModelProperty(value = "仪器类型ID")
    private String equipmenttypeId;
	/**生产厂家*/
	@Excel(name = "生产厂家", width = 15)
    @ApiModelProperty(value = "生产厂家")
    private String supply;
	/**制造商*/
	@Excel(name = "制造商", width = 15)
    @ApiModelProperty(value = "制造商")
    private String manufacturer;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private String description;
	/**存放位置*/
	@Excel(name = "存放位置", width = 15)
    @ApiModelProperty(value = "存放位置")
    private String place;
	/**出厂日期*/
	@Excel(name = "出厂日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出厂日期")
    private Date leaveDate;
	/**有效期*/
	@Excel(name = "有效期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "有效期")
    private Date validity;
	/**使用单位*/
	@Excel(name = "使用单位", width = 15)
    @ApiModelProperty(value = "使用单位")
    private String createDepartmentid;
	/**使用单位名称*/
	@Excel(name = "使用单位名称", width = 15)
    @ApiModelProperty(value = "使用单位名称")
    private String createDepartmentname;
	/**限用原因*/
	@Excel(name = "限用原因", width = 15)
    @ApiModelProperty(value = "限用原因")
    private String restrictcause;
	/**限用范围*/
	@Excel(name = "限用范围", width = 15)
    @ApiModelProperty(value = "限用范围")
    private String restrictscope;
	/**设备用途*/
	@Excel(name = "设备用途", width = 15)
    @ApiModelProperty(value = "设备用途")
    private String purpose;
	/**责任人*/
	@Excel(name = "责任人", width = 15)
    @ApiModelProperty(value = "责任人")
    private String workId;
	/**责任人姓名*/
	@Excel(name = "责任人姓名", width = 15)
    @ApiModelProperty(value = "责任人姓名")
    private String workname;
}
