package com.ckunion.activiti.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 历史
 * @Author: jeecg-boot
 * @Date:   2020-10-10
 * @Version: V1.0
 */
@Data
@TableName("vw_act_hi_actinst")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="vw_act_hi_actinst对象", description="历史")
public class VwActHiActinst {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**procDefId*/
	@Excel(name = "procDefId", width = 15)
    @ApiModelProperty(value = "procDefId")
	private String procDefId;
	/**procInstId*/
	@Excel(name = "procInstId", width = 15)
    @ApiModelProperty(value = "procInstId")
	private String procInstId;
	/**executionId*/
	@Excel(name = "executionId", width = 15)
    @ApiModelProperty(value = "executionId")
	private String executionId;
	/**actId*/
	@Excel(name = "actId", width = 15)
    @ApiModelProperty(value = "actId")
	private String actId;
	/**taskId*/
	@Excel(name = "taskId", width = 15)
    @ApiModelProperty(value = "taskId")
	private String taskId;
	/**callProcInstId*/
	@Excel(name = "callProcInstId", width = 15)
    @ApiModelProperty(value = "callProcInstId")
	private String callProcInstId;
	/**审批环节*/
	@Excel(name = "审批环节", width = 15)
    @ApiModelProperty(value = "审批环节")
	private String actName;
	/**actType*/
	@Excel(name = "actType", width = 15)
    @ApiModelProperty(value = "actType")
	private String actType;
	/**当前处理人*/
	@Excel(name = "当前处理人", width = 15)
    @ApiModelProperty(value = "当前处理人")
	private String assignee;
	/**任务开始时间*/
	@Excel(name = "任务开始时间", width = 15)
    @ApiModelProperty(value = "任务开始时间")
	private String startTime;
	/**任务结束时间*/
	@Excel(name = "任务结束时间", width = 15)
    @ApiModelProperty(value = "任务结束时间")
	private String endTime;
	/**持续时间*/
	@Excel(name = "持续时间", width = 15)
    @ApiModelProperty(value = "持续时间")
	private Integer duration;
	/**businessKey*/
	@Excel(name = "businessKey", width = 15)
    @ApiModelProperty(value = "businessKey")
	private String businessKey;
	/**businessCode*/
	@Excel(name = "businessCode", width = 15)
    @ApiModelProperty(value = "businessCode")
	private String businessCode;
	/**任务标题*/
	@Excel(name = "任务标题", width = 15)
    @ApiModelProperty(value = "任务标题")
	private String businessTitle;
	/**businessDesc*/
	@Excel(name = "businessDesc", width = 15)
    @ApiModelProperty(value = "businessDesc")
	private String businessDesc;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createName;
	/**批注*/
	@Excel(name = "批注", width = 15)
    @ApiModelProperty(value = "批注")
	private String message;
	
	@TableField(exist = false)
	private String timeDiff;

	/**审批人*/
	@Excel(name = "审批人", width = 15)
	@ApiModelProperty(value = "审批人")
	private String spr;



	/**审批人2  20210302 wuj增加 为解决上面spr中不包含，启动，end，会签，以及没有结论（同意/不同意）的审批节点的人，
	 *    而assignee，不知为何，领导审批节点没有值（如市场部领导），无奈增加此字段
	 */
	@Excel(name = "审批人2", width = 15)
	@ApiModelProperty(value = "operationby")
	private String operationby;

	
	public String getTimeDiff() {

		if( StringUtils.isNotBlank(this.startTime)) {
			SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//先将两个时间转换为毫秒相减，得到相差的毫秒数
			long number=0;
			try {
				if(StringUtils.isBlank(this.endTime)){
					number = System.currentTimeMillis()- fromat.parse(this.startTime).getTime();
				}else{
					number = fromat.parse(this.endTime).getTime()- fromat.parse(this.startTime).getTime();
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
			//然后在将毫秒转换为date类型就可以了
			//计算天
			int d=(int) ((number/86400000));
			//计算小时
			int h=(int) ((number%86400000)/3600000);
			//计算分钟
			int m=(int)((number%86400000)%3600000)/60000;
			//计算秒
			int s=(int)(((number%86400000)%3600000)%60000)/1000;
			String date = "";
			if(h>0) {
				date = date+d+"天 ";
			}
			if(h>0) {
				date = date+h+"小时 ";
			}
			if(m>0) {
				date = date+m+"分 ";
			}
			if(s>0) {
				date = date+s+"秒 ";
			}
			return date;
		}
		return null;
	}
	
}
