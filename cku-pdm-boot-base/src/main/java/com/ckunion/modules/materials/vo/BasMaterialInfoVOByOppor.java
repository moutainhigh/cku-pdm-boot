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
 * @Description: 物料信息维护（增加了物料类型、计量单位Dict）
 * @Author: wuj
 * @Date:   2020-11-30
 * @Version: V1.0
 */
@Data
@TableName("bas_material_info")
@ApiModel(value="bas_material_info对象", description="物料信息维护")
public class BasMaterialInfoVOByOppor extends ExtBase implements Serializable {
	private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
	private String id;
	@Excel(name = "助记码", width = 15)
	@ApiModelProperty(value = "助记码")
	private String cap;
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
	/**计量单位OID*/
	@Excel(name = "计量单位OID", width = 15, dicCode = "unit")
	@Dict(dictTable = "bas_unit", dicText = "unit_name", dicCode = "id")
	@ApiModelProperty(value = "计量单位OID")
	private Integer unitoid;
	/**标称频率*/
	@Excel(name = "标称频率", width = 15)
	@ApiModelProperty(value = "标称频率")
	private String nominalFrequency;
	/**标称频率单位*/
	@Excel(name = "标称频率单位", width = 15)
	@ApiModelProperty(value = "标称频率单位")
	private String frequencyUnit;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String remark;
	/**物料类型OID*/
	@Excel(name = "物料类型OID", width = 15)
	@Dict(dictTable = "bas_material_type", dicText = "type_name", dicCode = "id")
	@ApiModelProperty(value = "物料类型OID")
	private String typeoid;
	/**是否产品*/
	@Excel(name = "是否产品", width = 15)
	@ApiModelProperty(value = "是否产品")
	private String isProduct;


	@Excel(name = "电源电压", width = 15)
	@ApiModelProperty(value = "电源电压")
	private String boltage;
	/**温度频差*/
	@Excel(name = "温度频差", width = 15)
	@ApiModelProperty(value = "温度频差")
	private String temperatureDifference ;
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
	private String frequencyDifference ;
	/**激励电平*/
	@Excel(name = "激励电平", width = 15)
	@ApiModelProperty(value = "激励电平")
	private String excitationLevel;
	/**频率温度稳定性*/
	@Excel(name = "频率温度稳定性", width = 15)
	@ApiModelProperty(value = "频率温度稳定性")
	private String rangeStable;

}
