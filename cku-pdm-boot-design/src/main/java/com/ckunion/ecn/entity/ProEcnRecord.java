package com.ckunion.ecn.entity;

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
 * @Description: pro_ecn_record
 * @Author: jeecg-boot
 * @Date:   2021-04-16
 * @Version: V1.0
 */
@Data
@TableName("pro_ecn_record")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="pro_ecn_record对象", description="pro_ecn_record")
public class ProEcnRecord implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**变更单号*/
	@Excel(name = "变更单号", width = 15)
    @ApiModelProperty(value = "变更单号")
    private String ecnNumber;
	/**变更名称*/
	@Excel(name = "变更名称", width = 15)
    @ApiModelProperty(value = "变更名称")
    private String ecnName;
	/**变更原因*/
	@Excel(name = "变更原因", width = 15)
    @ApiModelProperty(value = "变更原因")
    private String ecnCategory;
	/**发起者*/
	@Excel(name = "发起者", width = 15)
    @ApiModelProperty(value = "发起者")
    private String creator;
	/**变更说明*/
	@Excel(name = "变更说明", width = 15)
    @ApiModelProperty(value = "变更说明")
    private String ecnDes;
	/**发起时间*/
	@Excel(name = "发起时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发起时间")
    private Date beginDate;
    /**状态*/
    @Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private String state;
	/**处理状态*/
	@Excel(name = "处理状态", width = 15)
    @ApiModelProperty(value = "处理状态")
    private String changeState;
	/**所属指标*/
	@Excel(name = "所属指标", width = 15)
    @ApiModelProperty(value = "所属指标")
    private String belongSeries;
	/**处理意见*/
	@Excel(name = "处理意见", width = 15)
    @ApiModelProperty(value = "处理意见")
    private String suggest;
	/**变更内容*/
	@Excel(name = "变更内容", width = 15)
    @ApiModelProperty(value = "变更内容")
    private String encComment;
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
	/**工作流id*/
	@Excel(name = "工作流id", width = 15)
    @ApiModelProperty(value = "工作流id")
    private String processinsid;
	/**重要程度 I II III*/
	@Excel(name = "重要程度 I II III", width = 15)
    @ApiModelProperty(value = "重要程度 I II III")
    private String ecnSignif;
	/**变更节点类型0结构件、1原材料、2文档*/
	@Excel(name = "变更节点类型0结构件、1原材料、2文档", width = 15)
    @ApiModelProperty(value = "变更节点类型0结构件、1原材料、2文档")
    private String ecnRecordType;
    /**变更前名称*/
    @Excel(name = "变更前名称", width = 15)
    @ApiModelProperty(value = "变更前名称")
    private String befName;
	/**变更前编号*/
	@Excel(name = "变更前编号", width = 15)
    @ApiModelProperty(value = "变更前编号")
    private String befNumber;
	/**变更前版本*/
	@Excel(name = "变更前版本", width = 15)
    @ApiModelProperty(value = "变更前版本")
    private String befVer;
	/**变更后编号*/
	@Excel(name = "变更后编号", width = 15)
    @ApiModelProperty(value = "变更后编号")
    private String affNumber;
	/**变更后版本*/
	@Excel(name = "变更后版本", width = 15)
    @ApiModelProperty(value = "变更后版本")
    private String affVer;
	/**受影响基线*/
	@Excel(name = "受影响基线", width = 15)
    @ApiModelProperty(value = "受影响基线")
    private String baselineNo;
    /**所属productInfo表ID*/
    @Excel(name = "所属productInfo表ID", width = 15)
    @ApiModelProperty(value = "所属productInfo表ID")
    private String infoId;

}
