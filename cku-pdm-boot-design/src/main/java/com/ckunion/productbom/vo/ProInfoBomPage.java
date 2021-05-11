package com.ckunion.productbom.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: prod_info_bom
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
@Data
@ApiModel(value="prod_info_bomPage对象", description="pro_product_info")
public class ProInfoBomPage {

	/**id*/
	@ApiModelProperty(value = "id")
	private String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;

	/**物料名称*/
	@Excel(name = "物料名称", width = 15)
	@ApiModelProperty(value = "物料名称")
	private String materialName;
	/**物料图号*/
	@Excel(name = "物料图号", width = 15)
	@ApiModelProperty(value = "物料图号")
	private String mapNo;
	/**节点和文档所属类型名称*/
	@Excel(name = "节点和文档所属类型名称", width = 15)
	@ApiModelProperty(value = "节点和文档所属类型名称")
	private String basTypeName;
	/**节点和文档所属类型编码*/
	@Excel(name = "节点和文档所属类型编码", width = 15)
	@ApiModelProperty(value = "节点和文档所属类型编码")
	private String basTypeCode;
	/**类型 0结构件、1原材料、2文档*/
	@Excel(name = "类型 0结构件、1原材料、2文档", width = 15)
	@ApiModelProperty(value = "类型 0结构件、1原材料、2文档")
	private String partType;

	/**上传文件*/
	@Excel(name = "上传文件", width = 15)
	@ApiModelProperty(value = "上传文件")
	private java.lang.String uploadFile;

	/**借用关系:0正常1借用*/
	@Excel(name = "借用关系:0正常1借用", width = 15)
	@ApiModelProperty(value = "借用关系:0正常1借用")
	private String relation;



	//////////////////////////////////以下为BOM表属性//////////////////////////////////


	/**物料信息父OID*/
	@Excel(name = "物料信息父OID", width = 15)
	@ApiModelProperty(value = "物料信息父OID")
	private String parentoid;

	/**每台数量*/
	@Excel(name = "每台数量", width = 15)
	@ApiModelProperty(value = "每台数量")
	private BigDecimal perQty;



}
