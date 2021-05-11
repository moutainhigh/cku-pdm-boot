package com.ckunion.modules.materials.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

;

/**
 * @Description: 计量单位维护
 * @Author: jeecg-boot
 * @Date:   2020-10-21
 * @Version: V1.0
 */
@Data
@TableName("bas_unit")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bas_unit对象", description="计量单位维护")
public class BasUnit implements Serializable {
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
	/**计量单位组*/
	@Excel(name = "计量单位组", width = 15, dicCode = "unit")
	@Dict(dicCode = "unit")
    @ApiModelProperty(value = "计量单位组")
    private String unitPid;
	/**计量单位编号*/
	@Excel(name = "计量单位编号", width = 15)
    @ApiModelProperty(value = "计量单位编号")
    private String unitCode;
	/**计量单位名称*/
	@Excel(name = "计量单位名称", width = 15)
    @ApiModelProperty(value = "计量单位名称")
    private String unitName;

    @Excel(name = "计量单位符号", width = 15)
    @ApiModelProperty(value = "计量单位符号")
    private String unitSymbol;

	/**状态*/
	@Excel(name = "状态", width = 15, dicCode = "is_enable")
	@Dict(dicCode = "is_enable")
    @ApiModelProperty(value = "状态")
    private Integer state;
	/**0未同步1已同步,发生更新后设置为0未同步*/
	@Excel(name = "0未同步1已同步,发生更新后设置为0未同步", width = 15)
    @ApiModelProperty(value = "0未同步1已同步,发生更新后设置为0未同步")
    private String integrateState;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remark;
}
