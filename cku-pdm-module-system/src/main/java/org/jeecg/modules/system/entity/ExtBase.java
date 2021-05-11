package org.jeecg.modules.system.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.Map;

@Data
public class ExtBase {

    //附加字段
    /**附加字段*/
    @Excel(name = "附加字段", width = 15)
    @ApiModelProperty(value = "附加字段")
    private Map ext;
}
