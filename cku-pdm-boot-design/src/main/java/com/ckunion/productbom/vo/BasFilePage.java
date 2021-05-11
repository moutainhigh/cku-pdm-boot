package com.ckunion.productbom.vo;

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
 * @Description: 文件库
 * @Author: jeecg-boot
 * @Date:   2021-04-08
 * @Version: V1.0
 */
@Data
@ApiModel(value="bas_file对象", description="文件库管理")
public class BasFilePage {


    /**图号*/
    @Excel(name = "图号", width = 15)
    @ApiModelProperty(value = "图号")
    private String mapNo;
    /**文件编号*/
    @Excel(name = "文件编号", width = 15)
    @ApiModelProperty(value = "文件编号")
    private String fileCode;
    /**文件名称*/
    @Excel(name = "文件名称", width = 15)
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    /**文件类型*/
    @Excel(name = "文件类型", width = 15)
    @ApiModelProperty(value = "文件类型")
    private String fileType;
    /**版本号*/
    @Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
    private String versions;
    /**状态*/
    @Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private String state;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remark;
    /**编制*/
    @Excel(name = "编制", width = 15)
    @ApiModelProperty(value = "编制")
    private String organize;
    /**审批*/
    @Excel(name = "审批", width = 15)
    @ApiModelProperty(value = "审批")
    private String approver;
    /**批准*/
    @Excel(name = "批准", width = 15)
    @ApiModelProperty(value = "批准")
    private String ratify;
    /**文档后缀*/
    @Excel(name = "文档后缀", width = 15)
    @ApiModelProperty(value = "文档后缀")
    private String postfix;
    /**文档路径*/
    @Excel(name = "文档路径", width = 15)
    @ApiModelProperty(value = "文档路径")
    private String filePath;
    /**产品型号*/
    @Excel(name = "产品型号", width = 15)
    @ApiModelProperty(value = "产品型号")
    private String productModel;
    /**质量等级*/
    @Excel(name = "质量等级", width = 15)
    @ApiModelProperty(value = "质量等级")
    private String quaLevel;


    /**文件所属节点*/
    @Excel(name = "文件所属节点", width = 15)
    @ApiModelProperty(value = "文件所属节点")
    private String parentoid;
}
