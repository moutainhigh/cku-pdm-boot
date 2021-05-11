package com.ckunion.applyno.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: pro_apply_mapno_dt
 * @Author: jeecg-boot
 * @Date:   2021-04-23
 * @Version: V1.0
 */
@ApiModel(value="pro_apply_mapno对象", description="pro_apply_mapno")
@Data
@TableName("pro_apply_mapno_dt")
public class ProApplyMapnoDt implements Serializable {
    private static final long serialVersionUID = 1L;

	/**节点和文档所属类型名称*/
	@Excel(name = "节点和文档所属类型名称", width = 15)
	@ApiModelProperty(value = "节点和文档所属类型名称")
	private String basTypeName;
	/**节点和文档所属类型编码*/
	@Excel(name = "节点和文档所属类型编码", width = 15)
	@ApiModelProperty(value = "节点和文档所属类型编码")
	private String basTypeCode;
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
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
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;
	/**节点图号*/
	@Excel(name = "节点图号", width = 15)
	@ApiModelProperty(value = "节点图号")
	private String nodeCode;
	/**节点名称*/
	@Excel(name = "节点名称", width = 15)
	@ApiModelProperty(value = "节点名称")
	private String nodeName;
	/**masteroid*/
	@ApiModelProperty(value = "masteroid")
	private String masteroid;

	/**类型 0结构件、1原材料、2文档*/
	@Excel(name = "类型 0结构件、1原材料、2文档", width = 15)
	@ApiModelProperty(value = "类型 0结构件、1原材料、2文档")
	private String partType;

	/**物料信息子OID*/
	@Excel(name = "物料信息子OID", width = 15)
	@ApiModelProperty(value = "物料信息子OID")
	private String childoid;
	/**物料信息父OID*/
	@Excel(name = "物料信息父OID", width = 15)
	@ApiModelProperty(value = "物料信息父OID")
	private String parentoid;


}
