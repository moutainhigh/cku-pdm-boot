package com.ckunion.productbom.entity;

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
 * @Description: pro_product_info
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
@Data
@TableName("pro_product_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="pro_product_info对象", description="pro_product_info")
public class ProProductInfo implements Serializable {
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


    /**物料编码*/
    @Excel(name = "物料编码", width = 15)
    @ApiModelProperty(value = "物料编码")
    private String materialCode;
    /**物料名称*/
    @Excel(name = "物料名称", width = 15)
    @ApiModelProperty(value = "物料名称")
    private String materialName;
	/**物料图号*/
	@Excel(name = "物料图号", width = 15)
    @ApiModelProperty(value = "物料图号")
    private String mapNo;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
    private String versionNum;
	/**发布状态：1申请中-4会签中-5批准中-6已批准-7已发布*/
	@Excel(name = "发布状态：1申请中-4会签中-5批准中-6已批准-7已发布", width = 15)
    @ApiModelProperty(value = "发布状态：1申请中-4会签中-5批准中-6已批准-7已发布")
    private String releaseStatus;
	/**节点状态：1拟制中-2修改中-3审核中-4会签中-5批准中-6已批准-7已归档*/
	@Excel(name = "节点状态：1拟制中-2修改中-3审核中-4会签中-5批准中-6已批准-7已归档", width = 15)
    @ApiModelProperty(value = "节点状态：1拟制中-2修改中-3审核中-4会签中-5批准中-6已批准-7已归档")
    private String status;
	/**节点和文档所属类型名称*/
	@Excel(name = "节点和文档所属类型名称", width = 15)
    @ApiModelProperty(value = "节点和文档所属类型名称")
    private String basTypeName;
	/**节点和文档所属类型编码*/
	@Excel(name = "节点和文档所属类型编码", width = 15)
    @ApiModelProperty(value = "节点和文档所属类型编码")
    private String basTypeCode;
	/**签入签出状况IN为签入OFF为签出*/
	@Excel(name = "签入签出状况IN为签入OFF为签出", width = 15)
    @ApiModelProperty(value = "签入签出状况IN为签入OFF为签出")
    private String signInoff;
	/**类型 0结构件、1原材料、2文档*/
	@Excel(name = "类型 0结构件、1原材料、2文档", width = 15)
    @ApiModelProperty(value = "类型 0结构件、1原材料、2文档")
    private String partType;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标ID")
    private String seriesId;


    /**型号名称*/
    @Excel(name = "型号名称", width = 15)
    @ApiModelProperty(value = "型号名称")
    private String typemodelName;
    /**型号ID*/
    @Excel(name = "型号ID", width = 15)
    @ApiModelProperty(value = "型号ID")
    private String typemodelId;
    /**质量等级*/
    @Excel(name = "质量等级", width = 15)
    @ApiModelProperty(value = "质量等级")
    private java.lang.String quaLevel;
    /**规格*/
    @Excel(name = "规格", width = 15)
    @ApiModelProperty(value = "规格")
    private java.lang.String model;
    /**计量单位*/
    @Excel(name = "计量单位", width = 15)
    @ApiModelProperty(value = "计量单位")
    private java.lang.String unitName;
    /**生产厂家名称*/
    @Excel(name = "生产厂家名称", width = 15)
    @ApiModelProperty(value = "生产厂家名称")
    private java.lang.String manufacturerName;
    /**封装形式*/
    @Excel(name = "封装形式", width = 15)
    @ApiModelProperty(value = "封装形式")
    private java.lang.String encapsulation;
    /**外形尺寸*/
    @Excel(name = "外形尺寸", width = 15)
    @ApiModelProperty(value = "外形尺寸")
    private java.lang.String appearance;
    /**是否进口*/
    @Excel(name = "是否进口", width = 15)
    @ApiModelProperty(value = "是否进口")
    private java.lang.String isImport;

    /**上传文件*/
    @Excel(name = "上传文件", width = 15)
    @ApiModelProperty(value = "上传文件")
    private java.lang.String uploadFile;

    /**签出人，记录最后一次签出的人*/
    @Excel(name = "签出人", width = 15)
    @ApiModelProperty(value = "签出人")
    private java.lang.String signOffUser;
    /**签出人姓名*/
    @Excel(name = "签出人姓名", width = 15)
    @ApiModelProperty(value = "签出人姓名")
    private java.lang.String signOffUsername;

    /**借用关系:0正常1借用*/
    @Excel(name = "借用关系:0正常1借用", width = 15)
    @ApiModelProperty(value = "借用关系:0正常1借用")
    private String relation;

    /**借用INFO-ID*/
    @Excel(name = "借用INFO-ID", width = 15)
    @ApiModelProperty(value = "借用INFO-ID")
    private String borrowInfoId;

}
