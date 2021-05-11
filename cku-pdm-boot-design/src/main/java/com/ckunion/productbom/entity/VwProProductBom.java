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
 * @Description: vw_pro_product_bom
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
@Data
@TableName("vw_pro_product_bom")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="vw_pro_product_bom对象", description="vw_pro_product_bom")
public class VwProProductBom implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
    private String createBy;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
    private Date createTime;
	/**materialName*/
	@Excel(name = "materialName", width = 15)
    @ApiModelProperty(value = "materialName")
    private String materialName;
	/**mapNo*/
	@Excel(name = "mapNo", width = 15)
    @ApiModelProperty(value = "mapNo")
    private String mapNo;
	/**perQty*/
	@Excel(name = "perQty", width = 15)
    @ApiModelProperty(value = "perQty")
    private BigDecimal perQty;
	/**isRoot*/
	@Excel(name = "isRoot", width = 15)
    @ApiModelProperty(value = "isRoot")
    private String isRoot;
	/**parentoid*/
	@Excel(name = "parentoid", width = 15)
    @ApiModelProperty(value = "parentoid")
    private String parentoid;

	/**childoid*/
	@Excel(name = "childoid", width = 15)
    @ApiModelProperty(value = "childoid")
    private String childoid;


	//////////////////////////////以下是info信息//////////////////////////////
	/**versionNum*/
	@Excel(name = "versionNum", width = 15)
    @ApiModelProperty(value = "versionNum")
    private String versionNum;
	/**releaseStatus*/
	@Excel(name = "releaseStatus", width = 15)
    @ApiModelProperty(value = "releaseStatus")
    private String releaseStatus;
	/**status*/
	@Excel(name = "status", width = 15)
    @ApiModelProperty(value = "status")
    private String status;
	/**basTypeName*/
	@Excel(name = "basTypeName", width = 15)
    @ApiModelProperty(value = "basTypeName")
    private String basTypeName;
	/**basTypeCode*/
	@Excel(name = "basTypeCode", width = 15)
    @ApiModelProperty(value = "basTypeCode")
    private String basTypeCode;
	/**signInoff*/
	@Excel(name = "signInoff", width = 15)
    @ApiModelProperty(value = "signInoff")
    private String signInoff;
	/**partType*/
	@Excel(name = "partType", width = 15)
    @ApiModelProperty(value = "partType")
    private String partType;
	/**seriesId*/
	@Excel(name = "seriesId", width = 15)
    @ApiModelProperty(value = "seriesId")
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
    @Excel(name = "借用关系", width = 15)
    @ApiModelProperty(value = "借用关系")
    private String relation;

    /**借用INFO-ID*/
    @Excel(name = "借用INFO-ID", width = 15)
    @ApiModelProperty(value = "借用INFO-ID")
    private String borrowInfoId;

}
