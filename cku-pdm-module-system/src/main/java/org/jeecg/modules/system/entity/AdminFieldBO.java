package org.jeecg.modules.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 业务功能字段表
 * @Author: jeecg-boot
 * @Date:   2020-10-10
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="admin_field对象", description="业务功能字段表")
public class AdminFieldBO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Excel(name = "数据list", width = 15)
    @ApiModelProperty(value = "数据list")
    private List<AdminFieldVO> data;

	
	@Excel(name = "menu_id", width = 15)
    @ApiModelProperty(value = "menu_id")
    private String menu_id;

}
