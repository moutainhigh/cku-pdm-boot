package com.ckunion.modules.base.typicalProc.vo;

import com.ckunion.modules.base.typicalProc.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 典型工艺库
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Data
@ApiModel(value="bas_typical_procPage对象", description="典型工艺库")
public class BasTypicalProcPage {

	/**主键*/
	@ApiModelProperty(value = "主键")
	private String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;
	/**工序编码*/
	@Excel(name = "工序编码", width = 15)
	@ApiModelProperty(value = "工序编码")
	private String workprocCode;
	/**工序名称*/
	@Excel(name = "工序名称", width = 15)
	@ApiModelProperty(value = "工序名称")
	private String workprocName;
	/**工序类型*/
	@Excel(name = "工序类型", width = 15)
	@ApiModelProperty(value = "工序类型")
	private String procType;
	/**单品生产*/
	@Excel(name = "单品生产", width = 15)
	@ApiModelProperty(value = "单品生产")
	private String singleProc;
	/**版本*/
	@Excel(name = "版本", width = 15)
	@ApiModelProperty(value = "版本")
	private String procVersion;
	/**状态*/
	@Excel(name = "状态", width = 15)
	@ApiModelProperty(value = "状态")
	private String procState;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String procRemark;


	/**工艺名称*/
	@Excel(name = "工艺名称", width = 15)
	@ApiModelProperty(value = "工艺名称")
	private String procName;
	/**图号*/
	@Excel(name = "图号", width = 15)
	@ApiModelProperty(value = "图号")
	private String mapNo;
	/**质量等级*/
	@Excel(name = "质量等级", width = 15)
	@ApiModelProperty(value = "质量等级")
	private String quaLevel;



	@ExcelCollection(name="目的和目标从表")
	@ApiModelProperty(value = "目的和目标从表")
	private List<BasTypicalPurpose> basTypicalPurposeList;
	@ExcelCollection(name="目标从从表")
	@ApiModelProperty(value = "目标从从表")
	private List<BasTypicalPurposeTarget> basTypicalPurposeTargetList;
	@ExcelCollection(name="原材料、辅料、材料从表")
	@ApiModelProperty(value = "原材料、辅料、材料从表")
	private List<BasTypicalMat> basTypicalMatList;
	@ExcelCollection(name="设备仪器从表")
	@ApiModelProperty(value = "设备仪器从表")
	private List<BasTypicalEqu> basTypicalEquList;
	@ExcelCollection(name="工装、用品从表")
	@ApiModelProperty(value = "工装、用品从表")
	private List<BasTypicalTool> basTypicalToolList;
	@ExcelCollection(name="操作过程从表")
	@ApiModelProperty(value = "操作过程从表")
	private List<BasTypicalProcess> basTypicalProcessList;
	@ExcelCollection(name="工作环境要求从表")
	@ApiModelProperty(value = "工作环境要求从表")
	private List<BasTypicalSurr> basTypicalSurrList;
	@ExcelCollection(name="注意事项从表")
	@ApiModelProperty(value = "注意事项从表")
	private List<BasTypicalAttent> basTypicalAttentList;
	@ExcelCollection(name="引用文件从表")
	@ApiModelProperty(value = "引用文件从表")
	private List<BasTypicalFile> basTypicalFileList;
	@ExcelCollection(name="图片从表")
	@ApiModelProperty(value = "图片从表")
	private List<BasTypicalImg> basTypicalImgList;

}
