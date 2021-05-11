package com.ckunion.modules.base.typicalProc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @Description: 引用文件从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@ApiModel(value="bas_typical_proc对象", description="典型工艺库")
@Data
@TableName("bas_typical_file")
public class BasTypicalFile implements Serializable {
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
	/**文件名称*/
	@Excel(name = "文件名称", width = 15)
	@ApiModelProperty(value = "文件名称")
	private String fileName;
	/**文件编号*/
	@Excel(name = "文件编号", width = 15)
	@ApiModelProperty(value = "文件编号")
	private String fileCode;
	/**文件类型*/
	@Excel(name = "文件类型", width = 15)
	@ApiModelProperty(value = "文件类型")
	private String fileType;
	/**版本*/
	@Excel(name = "版本", width = 15)
	@ApiModelProperty(value = "版本")
	private String versions;
	/**来源（选取/上传）*/
	@Excel(name = "来源（选取/上传）", width = 15)
	@ApiModelProperty(value = "来源（选取/上传）")
	private String fileSource;
	/**masteroid*/
	@ApiModelProperty(value = "masteroid")
	private String masteroid;
}
