package org.jeecg.modules.demo3.reschecker.entity;

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
 * @Description: 检验员表
 * @Author: jeecg-boot
 * @Date:   2021-05-10
 * @Version: V1.0
 */
@Data
@TableName("res_checker")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="res_checker对象", description="检验员表")
public class ResChecker implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**员工登录名*/
	@Excel(name = "员工登录名", width = 15)
    @ApiModelProperty(value = "员工登录名")
    private java.lang.String loginName;
	/**工人名称*/
	@Excel(name = "工人名称", width = 15)
    @ApiModelProperty(value = "工人名称")
    private java.lang.String workerName;
	/**状态*/
	@Dict(dicCode = "workState")
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private java.lang.String sate;
	/**部门*/
	@Excel(name = "部门", width = 15)
    @ApiModelProperty(value = "部门")
    private java.lang.String deptId;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private java.lang.String description;
	/**是否检验员*/
	@Excel(name = "是否检验员", width = 15)
    @ApiModelProperty(value = "是否检验员")
    private java.lang.String isChecker;
	/**检验员资质有效期*/
	@Excel(name = "检验员资质有效期", width = 15)
    @ApiModelProperty(value = "检验员资质有效期")
    private java.lang.String checkValid;
	/**附件*/
	@Excel(name = "附件", width = 15)
    @ApiModelProperty(value = "附件")
    private java.lang.String attach;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
	/**是否具有不合格品审理资格*/
	@Excel(name = "是否具有不合格品审理资格", width = 15)
    @ApiModelProperty(value = "是否具有不合格品审理资格")
    private java.lang.String hasNcrQual;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建人姓名*/
	@Excel(name = "创建人姓名", width = 15)
    @ApiModelProperty(value = "创建人姓名")
    private java.lang.String createName;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createDate;
	/**最后修改人*/
    @ApiModelProperty(value = "最后修改人")
    private java.lang.String updateBy;
	/**最后修改人姓名*/
	@Excel(name = "最后修改人姓名", width = 15)
    @ApiModelProperty(value = "最后修改人姓名")
    private java.lang.String updateName;
	/**最后修改时间*/
	@Excel(name = "最后修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最后修改时间")
    private java.util.Date updateDate;
}
