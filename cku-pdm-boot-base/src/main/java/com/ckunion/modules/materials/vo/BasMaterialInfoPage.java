package com.ckunion.modules.materials.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 物料信息维护
 * @Author: jeecg-boot
 * @Date:   2020-10-26
 * @Version: V1.0
 */
@Data
@TableName("bas_material_info")
@ApiModel(value="bas_material_info对象", description="物料信息维护")
public class BasMaterialInfoPage extends BasMaterialInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String menu_id;

	private String supplcode;

	private Integer pageNo;

	private Integer pageSize;

}
