package com.ckunion.modules.code.entity;

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
 * @Description: 节点编码管理
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Data
@TableName("base_node_code")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="base_node_code对象", description="节点编码管理")
public class BaseNodeCode implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
    /**图号*/
    @Excel(name = "图号", width = 15)
    @ApiModelProperty(value = "图号")
    private java.lang.String nodeCode;
    /**版本*/
    @Excel(name = "版本", width = 15)
    @ApiModelProperty(value = "版本")
    private java.lang.String codeVer;
    /**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**创建日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
    /**更新日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
    /**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
    /**编码类型*/
    @Excel(name = "编码类型", width = 15)
    @ApiModelProperty(value = "编码类型")
    private java.lang.String codeType;
    /**申请人*/
    @Excel(name = "申请人", width = 15)
    @ApiModelProperty(value = "申请人")
    private java.lang.String applicant;
    /**申请人名称*/
    @Excel(name = "申请人名称", width = 15)
    @ApiModelProperty(value = "申请人名称")
    private java.lang.String applicantName;
    /**申请时间*/
    @Excel(name = "申请时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "申请时间")
    private java.util.Date applicantTime;
    /**申请描述*/
    @Excel(name = "申请描述", width = 15)
    @ApiModelProperty(value = "申请描述")
    private java.lang.String applicantDesc;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remarks;
}
