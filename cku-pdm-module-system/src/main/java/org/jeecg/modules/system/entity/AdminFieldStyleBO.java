package org.jeecg.modules.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhangzhiwei
 * 字段宽度BO
 */
@Data
@ToString
@ApiModel(value="CrmFieldStyle字段宽度对象", description="字段排序表")
public class AdminFieldStyleBO {
    @ApiModelProperty(value = "主键ID")
    private Integer id;
    @ApiModelProperty(value = "宽度")
    private Integer width;
    @ApiModelProperty(value = "menu_id")
    private String menu_id;

    private String fieldName;
}
