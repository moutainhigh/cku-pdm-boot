package com.ckunion.hisVer.entity;

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
 * @Description: 历史版本记录-关系从表
 * @Author: jeecg-boot
 * @Date:   2021-05-07
 * @Version: V1.0
 */
@Data
@TableName("pro_his_ver_bom")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="pro_his_ver_bom对象", description="pro_his_ver_bom")
public class ProHisVerBom implements Serializable {
    private static final long serialVersionUID = 1L;

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
	/**masteroid*/
	@Excel(name = "masteroid", width = 15)
    @ApiModelProperty(value = "masteroid")
    private String masteroid;
	/**图号*/
	@Excel(name = "图号", width = 15)
    @ApiModelProperty(value = "图号")
    private String mapNo;
	/**物料名称*/
	@Excel(name = "物料名称", width = 15)
    @ApiModelProperty(value = "物料名称")
    private String materialName;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
    private String versionNum;
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
	/**质量等级*/
	@Excel(name = "质量等级", width = 15)
    @ApiModelProperty(value = "质量等级")
    private String quaLevel;
	/**规格*/
	@Excel(name = "规格", width = 15)
    @ApiModelProperty(value = "规格")
    private String model;
	/**计量单位*/
	@Excel(name = "计量单位", width = 15)
    @ApiModelProperty(value = "计量单位")
    private String unitName;
	/**生产厂家名称*/
	@Excel(name = "生产厂家名称", width = 15)
    @ApiModelProperty(value = "生产厂家名称")
    private String manufacturerName;
	/**封装形式*/
	@Excel(name = "封装形式", width = 15)
    @ApiModelProperty(value = "封装形式")
    private String encapsulation;
	/**外形尺寸*/
	@Excel(name = "外形尺寸", width = 15)
    @ApiModelProperty(value = "外形尺寸")
    private String appearance;
	/**是否进口*/
	@Excel(name = "是否进口", width = 15)
    @ApiModelProperty(value = "是否进口")
    private String isImport;
	/**每台数量*/
	@Excel(name = "每台数量", width = 15)
    @ApiModelProperty(value = "每台数量")
    private BigDecimal perQty;
	/**借用关系:0正常1借用*/
	@Excel(name = "借用关系:0正常1借用", width = 15)
    @ApiModelProperty(value = "借用关系:0正常1借用")
    private String relation;
	/**是否选中*/
	@Excel(name = "是否选中", width = 15)
    @ApiModelProperty(value = "是否选中")
    private Integer isSelected;
	/**父节点id，关联自己*/
	@Excel(name = "父节点id，关联自己", width = 15)
    @ApiModelProperty(value = "父节点id，关联自己")
    private String parentId;

    /**发布状态*/
    @Excel(name = "发布状态", width = 15)
    @ApiModelProperty(value = "发布状态")
    private String releaseStatus;
    /**节点状态*/
    @Excel(name = "节点状态", width = 15)
    @ApiModelProperty(value = "节点状态")
    private String status;

    /**节点最初创建人*/
    @ApiModelProperty(value = "节点最初创建人")
    private String nodeCreateBy;
    /**节点最初创建日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "节点最初创建日期")
    private Date nodeCreateTime;

    /**上传文件*/
    @Excel(name = "上传文件", width = 15)
    @ApiModelProperty(value = "上传文件")
    private String uploadFile;

}
