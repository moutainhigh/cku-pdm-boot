package com.ckunion.group.entity;

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
 * @Description: pro_group_user
 * @Author: jeecg-boot
 * @Date:   2021-04-30
 * @Version: V1.0
 */
@Data
@TableName("pro_group_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="pro_group_user对象", description="pro_group_user")
public class ProGroupUser implements Serializable {
	private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
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
	/**masteroid*/
	@Excel(name = "masteroid", width = 15)
	@ApiModelProperty(value = "masteroid")
	private java.lang.String masteroid;
	/**成员登录名*/
	@Excel(name = "成员登录名", width = 15)
	@ApiModelProperty(value = "成员登录名")
	private java.lang.String userName;
	/**成员姓名*/
	@Excel(name = "成员姓名", width = 15)
	@ApiModelProperty(value = "成员姓名")
	private java.lang.String realName;
	/**角色名称*/
	@Excel(name = "角色名称", width = 15)
	@ApiModelProperty(value = "角色名称")
	private java.lang.String roleName;
	/**权限名称，多个用逗号分隔*/
	@Excel(name = "权限名称，多个用逗号分隔", width = 15)
	@ApiModelProperty(value = "权限名称，多个用逗号分隔")
	private java.lang.String permissionName;
}
