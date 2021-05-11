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
 * @Description: pro_product_bom
 * @Author: jeecg-boot
 * @Date:   2021-04-13
 * @Version: V1.0
 */
@Data
@TableName("pro_product_bom")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="pro_product_bom对象", description="pro_product_bom")
public class ProProductBom implements Serializable {
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

    /**物料名称*/
    @Excel(name = "物料名称", width = 15)
    @ApiModelProperty(value = "物料名称")
    private String materialName;
	/**图号*/
	@Excel(name = "图号", width = 15)
    @ApiModelProperty(value = "图号")
    private String mapNo;

    /**物料信息子OID*/
    @Excel(name = "物料信息子OID", width = 15)
    @ApiModelProperty(value = "物料信息子OID")
    private String childoid;
    /**物料信息父OID*/
    @Excel(name = "物料信息父OID", width = 15)
    @ApiModelProperty(value = "物料信息父OID")
    private String parentoid;

	/**每台数量*/
	@Excel(name = "每台数量", width = 15)
    @ApiModelProperty(value = "每台数量")
    private BigDecimal perQty;
	/**是否根节点：Y表示根节点*/
	@Excel(name = "是否根节点：Y表示根节点", width = 15)
    @ApiModelProperty(value = "是否根节点：Y表示根节点")
    private String isRoot;

    /**节点层次码*/
    @Excel(name = "节点层次码", width = 15)
    @ApiModelProperty(value = "节点层次码")
    private String nodeId;
    /**节点层次*/
    @Excel(name = "节点层次", width = 15)
    @ApiModelProperty(value = "节点层次")
    private Integer idClass;

    /**节点层次*/
    @Excel(name = "是否选中", width = 15)
    @ApiModelProperty(value = "是否选中")
    private Integer isSelected;




}
