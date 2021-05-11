package com.ckunion.modules.base.typicalProc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 典型工艺图片从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@ApiModel(value="bas_typical_proc对象", description="典型工艺库")
@Data
@TableName("bas_typical_img")
public class BasTypicalImg implements Serializable {
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
	/**masteroid*/
	@ApiModelProperty(value = "masteroid")
	private String masteroid;


	/**图片名称*/
	@ApiModelProperty(value = "图片名称")
	private String imgName;
	/**图片题注*/
	@ApiModelProperty(value = "图片题注")
	private String imgStr;
	/**图片宽度*/
	@ApiModelProperty(value = "图片宽度")
	private String imgWidth;
	/**图片高度*/
	@ApiModelProperty(value = "图片高度")
	private String imgHeight;
	/**图片路径*/
	@ApiModelProperty(value = "图片路径")
	private String imgSrc;

	@TableField(exist = false)
	private String imgId;//word文档中需要的图片ID
	@TableField(exist = false)
	private String imgTar;//word文档中需要的图片Tar
	@TableField(exist = false)
	private String imgData;//word文档中需要的图片内容
	@TableField(exist = false)
	private String docViewWidth;//word文档中需要的图片宽度
	@TableField(exist = false)
	private String docViewHeight;//word文档中需要的图片高度

}
