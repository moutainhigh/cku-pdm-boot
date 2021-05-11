package org.jeecg.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: admin_field_sort
 * @Author: jeecg-boot
 * @Date:   2020-10-10
 * @Version: V1.0
 */
@Data
@TableName("admin_field_sort")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="admin_field_sort对象", description="admin_field_sort")
public class AdminFieldSort implements Serializable {
    private static final long serialVersionUID = 1L;

	/**字段id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "字段id")
    private String id;
	/**标签 1 线索 2 客户 3 联系人 4 产品 5 商机 6 合同 7回款 8公海*/
	@Excel(name = "标签 1 线索 2 客户 3 联系人 4 产品 5 商机 6 合同 7回款 8公海", width = 15)
    @ApiModelProperty(value = "标签 1 线索 2 客户 3 联系人 4 产品 5 商机 6 合同 7回款 8公海")
    private String menuId;
	/**字段排序*/
	@Excel(name = "字段排序", width = 15)
    @ApiModelProperty(value = "字段排序")
    private Integer sort;
	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
    private String userId;
	/**是否隐藏 0、不隐藏 1、隐藏*/
	@Excel(name = "是否隐藏 0、不隐藏 1、隐藏", width = 15)
    @ApiModelProperty(value = "是否隐藏 0、不隐藏 1、隐藏")
    private Integer isHide;
	/**fieldId*/
	@Excel(name = "fieldId", width = 15)
    @ApiModelProperty(value = "fieldId")
    private String fieldId;
    /**isColor*/
    @Excel(name = "isColor", width = 15)
    @ApiModelProperty(value = "isColor")
    private String isColor;

    public AdminFieldSort(){

    }

}
