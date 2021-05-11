package com.ckunion.applyno.vo;

import java.util.List;
import com.ckunion.applyno.entity.ProApplyMapno;
import com.ckunion.applyno.entity.ProApplyMapnoDt;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: pro_apply_mapno
 * @Author: jeecg-boot
 * @Date:   2021-04-23
 * @Version: V1.0
 */
@Data
@ApiModel(value="pro_apply_mapnoPage对象", description="pro_apply_mapno")
public class ProApplyMapnoPage {

	/**id*/
	@ApiModelProperty(value = "id")
	private String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;
	/**状态*/
	@Excel(name = "状态", width = 15)
	@ApiModelProperty(value = "状态")
	private String state;
	/**申请描述*/
	@Excel(name = "申请描述", width = 15)
	@ApiModelProperty(value = "申请描述")
	private String applyDesc;

	/**申请节点名称*/
	@Excel(name = "申请节点名称", width = 15)
	@ApiModelProperty(value = "申请节点名称")
	private String nodeName;

	/**申请节点infoid*/
	@Excel(name = "申请节点infoid", width = 15)
	@ApiModelProperty(value = "申请节点infoid")
	private String infoId;


	@ExcelCollection(name="pro_apply_mapno_dt")
	@ApiModelProperty(value = "pro_apply_mapno_dt")
	private List<ProApplyMapnoDt> proApplyMapnoDtList;

}
