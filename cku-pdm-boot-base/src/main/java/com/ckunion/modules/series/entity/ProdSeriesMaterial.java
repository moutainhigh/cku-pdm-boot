package com.ckunion.modules.series.entity;

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
 * @Description: 分指标物料配置表
 * @Author: jeecg-boot
 * @Date:   2021-04-29
 * @Version: V1.0
 */
@Data
@TableName("prod_series_material")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="prod_series_material对象", description="分指标物料配置表")
public class ProdSeriesMaterial implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**分指标id*/
	@Excel(name = "分指标id", width = 15)
    @ApiModelProperty(value = "分指标id")
    private String serieId;
	/**分指标指定BOM节点*/
	@Excel(name = "分指标指定BOM节点", width = 15)
    @ApiModelProperty(value = "分指标指定BOM节点")
    private String bomId;
	/**父ID,分指标需要指明父ID*/
	@Excel(name = "父ID,分指标需要指明父ID", width = 15)
    @ApiModelProperty(value = "父ID,分指标需要指明父ID")
    private String parentoid;
	/**质量等级*/
	@Excel(name = "质量等级", width = 15)
    @ApiModelProperty(value = "质量等级")
    private String quaLevel;
	/**标称频率*/
	@Excel(name = "标称频率", width = 15)
    @ApiModelProperty(value = "标称频率")
    private String nominalFrequency;
	/**原材料id*/
	@Excel(name = "原材料id", width = 15)
    @ApiModelProperty(value = "原材料id")
    private String materialId;
}
