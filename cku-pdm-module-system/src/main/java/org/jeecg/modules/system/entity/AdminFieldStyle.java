package org.jeecg.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: admin_field_style
 * @Author: jeecg-boot
 * @Date:   2020-10-10
 * @Version: V1.0
 */
@Data
@TableName("admin_field_style")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="admin_field_style对象", description="admin_field_style")
public class AdminFieldStyle implements Serializable {
    private static final long serialVersionUID = 1L;

	/**样式表id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "样式表id")
    private Integer id;
	/**字段宽度*/
	@Excel(name = "字段宽度", width = 15)
    @ApiModelProperty(value = "字段宽度")
    private Integer style;
	/**字段类型 '1 线索 2 客户 3 联系人 4 产品 5 商机 6 合同 7回款 8公海'*/
	@Excel(name = "字段类型 '1 线索 2 客户 3 联系人 4 产品 5 商机 6 合同 7回款 8公海'", width = 15)
    @ApiModelProperty(value = "字段类型 '1 线索 2 客户 3 联系人 4 产品 5 商机 6 合同 7回款 8公海'")
    private String menuId;
	/**字段名称*/
	@Excel(name = "字段名称", width = 15)
    @ApiModelProperty(value = "字段名称")
    private String fieldName;
	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
    private String userId;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
