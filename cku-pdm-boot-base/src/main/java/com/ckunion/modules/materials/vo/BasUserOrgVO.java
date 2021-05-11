package com.ckunion.modules.materials.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 内外勤关系表
 * @Author: jeecg-boot
 * @Date:   2020-12-14
 * @Version: V1.0
 */
@Data
@TableName("bas_user_org")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bas_user_org对象", description="内外勤关系表")
public class BasUserOrgVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**内勤人员username*/
    private String neiuser;
    /**外勤人员集合*/
    private List<String> waiusers;

}
