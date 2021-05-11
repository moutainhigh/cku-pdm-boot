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
 * @Description: 历史版本记录
 * @Author: jeecg-boot
 * @Date:   2021-05-07
 * @Version: V1.0
 */
@Data
@TableName("pro_his_ver_record")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="pro_his_ver_record对象", description="pro_his_ver_record")
public class ProHisVerRecord implements Serializable {
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
    /**型号ID*/
    @Excel(name = "型号ID", width = 15)
    @ApiModelProperty(value = "型号ID")
    private String typemodelId;
    /**型号名称*/
    @Excel(name = "型号名称", width = 15)
    @ApiModelProperty(value = "型号名称")
    private String typemodelName;
	/**质量等级*/
	@Excel(name = "质量等级", width = 15)
    @ApiModelProperty(value = "质量等级")
    private String quaLevel;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标ID")
    private String seriesId;
	/**指标名称*/
	@Excel(name = "指标名称", width = 15)
    @ApiModelProperty(value = "指标名称")
    private String seriesName;
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
    /**BOM基础物料ID-proProductInfo表id*/
    @Excel(name = "BOM基础物料", width = 15)
    @ApiModelProperty(value = "BOM基础物料")
    private String infoId;
}
