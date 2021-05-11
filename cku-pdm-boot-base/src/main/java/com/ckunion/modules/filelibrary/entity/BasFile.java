package com.ckunion.modules.filelibrary.entity;

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
 * @Description: 文件库管理
 * @Author: jeecg-boot
 * @Date:   2021-04-08
 * @Version: V1.0
 */
@Data
@TableName("bas_file")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bas_file对象", description="文件库管理")
public class BasFile implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
    /**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**创建日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
    /**更新日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
    /**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
    /**图号*/
    @Excel(name = "图号", width = 15)
    @ApiModelProperty(value = "图号")
    private java.lang.String mapNo;
    /**文件编号*/
    @Excel(name = "文件编号", width = 15)
    @ApiModelProperty(value = "文件编号")
    private java.lang.String fileCode;
    /**文件名称*/
    @Excel(name = "文件名称", width = 15)
    @ApiModelProperty(value = "文件名称")
    private java.lang.String fileName;
    /**文件类型*/
    @Excel(name = "文件类型", width = 15)
    @ApiModelProperty(value = "文件类型")
    private java.lang.String fileType;
    /**公布日期*/
    @Excel(name = "公布日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "公布日期")
    private java.util.Date issueDate;
    /**实施日期*/
    @Excel(name = "实施日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "实施日期")
    private java.util.Date executeDate;
    /**版本号*/
    @Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
    private java.lang.String versions;
    /**状态*/
    @Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private java.lang.String state;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
    /**编制*/
    @Excel(name = "编制", width = 15)
    @ApiModelProperty(value = "编制")
    private java.lang.String organize;
    /**审批*/
    @Excel(name = "审批", width = 15)
    @ApiModelProperty(value = "审批")
    private java.lang.String approver;
    /**批准*/
    @Excel(name = "批准", width = 15)
    @ApiModelProperty(value = "批准")
    private java.lang.String ratify;
    /**文档后缀*/
    @Excel(name = "文档后缀", width = 15)
    @ApiModelProperty(value = "文档后缀")
    private java.lang.String postfix;
    /**文档路径*/
    @Excel(name = "文档路径", width = 15)
    @ApiModelProperty(value = "文档路径")
    private java.lang.String filePath;
    /**产品型号*/
    @Excel(name = "产品型号", width = 15)
    @ApiModelProperty(value = "产品型号")
    private java.lang.String productModel;
    /**质量等级*/
    @Excel(name = "质量等级", width = 15)
    @ApiModelProperty(value = "质量等级")
    private java.lang.String quaLevel;
}
