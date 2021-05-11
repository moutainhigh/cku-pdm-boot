package com.ckunion.modules.materials.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecg.modules.system.entity.ExtBase;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 物料信息维护
 * @Author: jeecg-boot
 * @Date:   2020-10-26
 * @Version: V1.0
 */
@Data
@TableName("bas_material_info")
@ApiModel(value="bas_material_info对象", description="物料信息维护")
public class BasMaterialInfoVO extends ExtBase implements Serializable {
	private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
	private String id;
	@Excel(name = "助记码", width = 15)
	@ApiModelProperty(value = "助记码")
	private String cap;
//	/**创建人*/
//	@ApiModelProperty(value = "创建人")
//	private String createBy;
//	/**创建日期*/
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//	@ApiModelProperty(value = "创建日期")
//	private Date createTime;
//	/**更新人*/
//	@ApiModelProperty(value = "更新人")
//	private String updateBy;
//	/**更新日期*/
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//	@ApiModelProperty(value = "更新日期")
//	private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;
	/**物资编码*/
	@Excel(name = "物资编码", width = 15)
	@ApiModelProperty(value = "物资编码")
	private String materialCode;
	/**物资名称*/
	@Excel(name = "物资名称", width = 15)
	@ApiModelProperty(value = "物资名称")
	private String materialName;
	/**状态*/
	@Excel(name = "状态", width = 15, dicCode = "is_enable")
	@Dict(dicCode = "is_enable")
	@ApiModelProperty(value = "状态")
	private String state;
	/**产品型号*/
	@Excel(name = "产品型号", width = 15)
	@ApiModelProperty(value = "产品型号")
	private String productModel;
	/**质量等级(用于物料导出的字段)*/
	@Excel(name = "质量等级(用于物料导出的字段)", width = 15, dicCode = "quaLevel")
	@Dict(dicCode = "quaLevel")
	@ApiModelProperty(value = "质量等级(用于物料导出的字段)")
	private String quaLevel;
	@Excel(name = "产品型号()", width = 15, dicCode = "mapNo")
	@Dict(dicCode = "mapNo")
	@ApiModelProperty(value = "产品型号()")
	private String mapNo;
	/**计量单位OID*/
	@Excel(name = "计量单位OID", width = 15, dicCode = "unit")
	@Dict(dicCode = "unit")
	@ApiModelProperty(value = "计量单位OID")
	private Integer unitoid;
	/**标称频率*/
	@Excel(name = "标称频率", width = 15)
	@ApiModelProperty(value = "标称频率")
	private String nominalFrequency;
	/**标称频率单位*/
	@Excel(name = "标称频率单位", width = 15)
	@ApiModelProperty(value = "标称频率单位")
	private String FrequencyUnit;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String remark;
	/**物料类型OID*/
	@Excel(name = "物料类型OID", width = 15)
	@ApiModelProperty(value = "物料类型OID")
	private String typeoid;
	/**是否产品*/
	@Excel(name = "是否产品", width = 15)
	@ApiModelProperty(value = "是否产品")
	private String isProduct;
//	/**包装*/
//	@Excel(name = "包装", width = 15)
//	@ApiModelProperty(value = "包装")
//	private String packing ;
//	/**振动模式*/
//	@Excel(name = "振动模式", width = 15)
//	@ApiModelProperty(value = "振动模式")
//	private String vibration_mode ;
//	/**调整频差*/
//	@Excel(name = "调整频差", width = 15)
//	@ApiModelProperty(value = "调整频差")
//	private String frequency_difference ;
//	/**温度频差*/
//	@Excel(name = "温度频差", width = 15)
//	@ApiModelProperty(value = "温度频差")
//	private String temperature_difference ;
//	/**总频差*/
//	@Excel(name = "总频差", width = 15)
//	@ApiModelProperty(value = "总频差")
//	private String  total_difference;
//	/**工作最高温度*/
//	@Excel(name = "工作最高温度", width = 15)
//	@ApiModelProperty(value = "工作最高温度")
//	private String max_range ;
//	/**负载电容*/
//	@Excel(name = "负载电容", width = 15)
//	@ApiModelProperty(value = "负载电容")
//	private String  load_capacitance;
//	/**谐振电阻*/
//	@Excel(name = "谐振电阻", width = 15)
//	@ApiModelProperty(value = "谐振电阻")
//	private String resistance ;
//	/**负载谐振电阻*/
//	@Excel(name = "负载谐振电阻", width = 15)
//	@ApiModelProperty(value = "负载谐振电阻")
//	private String load_resistance ;
//	/**电源电压*/
//	@Excel(name = "电源电压", width = 15)
//	@ApiModelProperty(value = "电源电压")
//	private String boltage ;
//	/**基准温度初始精度*/
//	@Excel(name = "基准温度初始精度", width = 15)
//	@ApiModelProperty(value = "基准温度初始精度")
//	private String range_precision ;
//	/**初始频率温度精度*/
//	@Excel(name = "初始频率温度精度", width = 15)
//	@ApiModelProperty(value = "初始频率温度精度")
//	private String  start_range_precision;
//	/**输出波形*/
//	@Excel(name = "输出波形", width = 15)
//	@ApiModelProperty(value = "输出波形")
//	private String  waveform;
//	/**压控端*/
//	@Excel(name = "压控端", width = 15)
//	@ApiModelProperty(value = "压控端")
//	private String voltage_control ;
//	/**频率温度稳定性*/
//	@Excel(name = "频率温度稳定性", width = 15)
//	@ApiModelProperty(value = "频率温度稳定性")
//	private String range_stable ;
//	/**焊盘数量*/
//	@Excel(name = "焊盘数量", width = 15)
//	@ApiModelProperty(value = "焊盘数量")
//	private String welding_plate_qty ;
//	/**占空比*/
//	@Excel(name = "占空比", width = 15)
//	@ApiModelProperty(value = "占空比")
//	private String duty_cycle ;
//	/**激励电平*/
//	@Excel(name = "激励电平", width = 15)
//	@ApiModelProperty(value = "激励电平")
//	private String excitation_level ;
//	/**输出状态*/
//	@Excel(name = "输出状态", width = 15)
//	@ApiModelProperty(value = "输出状态")
//	private String out_state ;
//	/**工作最低温度*/
//	@Excel(name = "工作最低温度", width = 15)
//	@ApiModelProperty(value = "工作最低温度")
//	private String min_range ;
//	/**印字*/
//	@Excel(name = "印字", width = 15)
//	@ApiModelProperty(value = "印字")
//	private String printing ;
//	/**技术标准*/
//	@Excel(name = "技术标准", width = 15)
//	@ApiModelProperty(value = "技术标准")
//	private String exec_standard ;
//	/**外形尺寸*/
//	@Excel(name = "外形尺寸", width = 15)
//	@ApiModelProperty(value = "外形尺寸")
//	private String appearance ;
//	/**盒型*/
//	@Excel(name = "盒型", width = 15)
//	@ApiModelProperty(value = "盒型")
//	private String box_type ;

//	//附加字段
//	/**是否有子节点*/
//	@Excel(name = "附加字段", width = 15)
//	@ApiModelProperty(value = "附加字段")
//	private Map ext;




	@Excel(name = "技术标准", width = 15)
	@ApiModelProperty(value = "技术标准")
	private String execStandard;
	@Excel(name = "电源电压", width = 15)
	@ApiModelProperty(value = "电源电压")
	private String boltage;
	/**温度频差*/
	@Excel(name = "温度频差", width = 15)
	@ApiModelProperty(value = "温度频差")
	private String temperatureDifference;
	/**负载电容*/
	@Excel(name = "负载电容", width = 15)
	@ApiModelProperty(value = "负载电容")
	private String  loadCapacitance;
	/**总频差*/
	@Excel(name = "总频差", width = 15)
	@ApiModelProperty(value = "总频差")
	private String  totalDifference;
	/**工作最低温度*/
	@Excel(name = "工作最低温度", width = 15)
	@ApiModelProperty(value = "工作最低温度")
	private String minRange;
	/**工作最高温度*/
	@Excel(name = "工作最高温度", width = 15)
	@ApiModelProperty(value = "工作最高温度")
	private String maxRange;
	/**基准温度初始精度*/
	@Excel(name = "基准温度初始精度", width = 15)
	@ApiModelProperty(value = "基准温度初始精度")
	private String rangePrecision;
	/**初始频率温度精度*/
	@Excel(name = "初始频率温度精度", width = 15)
	@ApiModelProperty(value = "初始频率温度精度")
	private String  startRangePrecision;
	/**振动模式*/
	@Excel(name = "振动模式", width = 15)
	@ApiModelProperty(value = "振动模式")
	private String vibrationMode;
	/**调整频差*/
	@Excel(name = "调整频差", width = 15)
	@ApiModelProperty(value = "调整频差")
	private String frequencyDifference;
	/**激励电平*/
	@Excel(name = "激励电平", width = 15)
	@ApiModelProperty(value = "激励电平")
	private String excitationLevel;
	/**频率温度稳定性*/
	@Excel(name = "频率温度稳定性", width = 15)
	@ApiModelProperty(value = "频率温度稳定性")
	private String rangeStable;
	/**焊盘数量*/
	@Excel(name = "焊盘数量", width = 15)
	@ApiModelProperty(value = "焊盘数量")
	private String weldingPlateQty;
	/**基座型号*/
	@Excel(name = "基座型号", width = 15)
	@ApiModelProperty(value = "基座型号")
	private String pedestalModel;
	/**芯片型号*/
	@Excel(name = "芯片型号", width = 15)
	@ApiModelProperty(value = "芯片型号")
	private java.lang.String chipModel;

	/**盒型*/
	@Excel(name = "盒型", width = 15)
	@ApiModelProperty(value = "盒型")
	private String boxType;
	/**印字*/
	@Excel(name = "印字", width = 15)
	@ApiModelProperty(value = "印字")
	private String printing;
	/**印字*/
	@Excel(name = "输出波形", width = 15)
	@ApiModelProperty(value = "输出波形")
	private String waveform;
	/**印字*/
	@Excel(name = "压控端", width = 15)
	@ApiModelProperty(value = "压控端")
	private String voltageControl;

}
