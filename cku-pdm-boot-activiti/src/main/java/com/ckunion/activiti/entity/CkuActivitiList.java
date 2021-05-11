package com.ckunion.activiti.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 流程列表
 * @Author: jeecg-boot
 * @Date:   2020-09-24
 * @Version: V1.0
 */
@Data
@TableName("cku_activiti_list")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cku_activiti_list对象", description="流程列表")
public class CkuActivitiList {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**流程名称*/
	@Excel(name = "流程名称", width = 15)
    @ApiModelProperty(value = "流程名称")
	private String actName;
	/**流程编码*/
	@Excel(name = "流程编码", width = 15)
    @ApiModelProperty(value = "流程编码")
	private String actCode;
	/**流程类型*/
	@Excel(name = "流程类型", width = 15)
    @ApiModelProperty(value = "流程类型")
	private String actType;
	/**流程状态0未发布1发布*/
	@Excel(name = "流程状态0未发布1发布", width = 15)
    @ApiModelProperty(value = "流程状态0未发布1发布")
	private String actState;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**文件路径*/
	@Excel(name = "文件路径", width = 15)
    @ApiModelProperty(value = "文件路径")
	private String filePath;
	/**文件名称*/
	@Excel(name = "文件名称", width = 15)
    @ApiModelProperty(value = "文件名称")
	private String fileName;
	/**图片路径*/
	@Excel(name = "图片路径", width = 15)
    @ApiModelProperty(value = "图片路径")
	private String imgPath;
	/**图片名称*/
	@Excel(name = "图片名称", width = 15)
    @ApiModelProperty(value = "图片名称")
	private String imgName;
	/**回调服务名称*/
	@Excel(name = "回调服务名称", width = 15)
    @ApiModelProperty(value = "回调服务名称")
	private String serverName;
	/**流程版本*/
	@Excel(name = "流程版本", width = 15)
    @ApiModelProperty(value = "流程版本")
	private String actVersion;
}
