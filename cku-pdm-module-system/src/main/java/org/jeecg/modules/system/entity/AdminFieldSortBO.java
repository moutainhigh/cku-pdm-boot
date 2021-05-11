package org.jeecg.modules.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhangzhiwei
 */
@Data
@ToString
@ApiModel(value = "CrmFieldSort字段调整对象", description = "字段排序表")
public class AdminFieldSortBO {

    @ApiModelProperty(value = "不隐藏的ID")
    private List<String> noHideIds;

    @ApiModelProperty(value = "隐藏的ID")
    private List<String> hideIds;

    @NotNull
    @ApiModelProperty(value = "menu_id", required = true)
    public String menu_id;

}
