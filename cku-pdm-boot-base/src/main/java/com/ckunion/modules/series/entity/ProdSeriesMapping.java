package com.ckunion.modules.series.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 产品系列匹配字段实体类
 * @author zhangdaihao
 * @date 2019-05-27 14:15:11
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
@ApiModel(value="PROD_SERIES_MAPPING对象", description="PROD_SERIES_MAPPING")
@Data
@TableName("PROD_SERIES_MAPPING")
public class ProdSeriesMapping implements java.io.Serializable {
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;
	/**选择系列页面显示顺序*/
	@ApiModelProperty(value = "选择系列页面显示顺序")
	private Integer seriesOrder;
	/**BAS_MATERIAL_INFO_COLUMN（物料字段统计表）主键id*/
	@ApiModelProperty(value = "BAS_MATERIAL_INFO_COLUMN ID")
	private String infoColumnId;
	/**RES_TYPE(类型表)主键id*/
	@ApiModelProperty(value = "产品型号id")
	private String typeModelId;
	/**选中1选中*/
	@ApiModelProperty(value = "选中1选中")
	private String checked;
	/**字段显示*/
	@ApiModelProperty(value = "字段显示")
	private String columnLabel;
	/**字段名称*/
	@ApiModelProperty(value = "字段名称")
	private String columnName;

}
