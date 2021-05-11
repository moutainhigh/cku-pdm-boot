package org.jeecg.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: admin_field_sort
 * @Author: jeecg-boot
 * @Date:   2020-10-10
 * @Version: V1.0
 */
@Data
@ToString
@ApiModel(value = "CrmFieldSort字段调整对象", description = "字段排序表")
public class AdminFieldSortVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**字段id*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "字段id")
    private String id;
    /**标签 1 线索 2 客户 3 联系人 4 产品 5 商机 6 合同 7回款 8公海*/
    @Excel(name = "菜单id", width = 15)
    @ApiModelProperty(value = "菜单id")
    private String menuId;
    /**字段名称*/
    @Excel(name = "字段名称", width = 15)
    @ApiModelProperty(value = "字段名称")
    private String fieldName;
    /**字段中文名称*/
    @Excel(name = "字段中文名称", width = 15)
    @ApiModelProperty(value = "字段中文名称")
    private String name;
    /**字段类型 1 单行文本 2 多行文本 3 单选 4日期 5 数字 6 小数 7 手机  8 文件 9 多选 10 人员 11 附件 12 部门 13 日期时间 14 邮箱 15客户 16 商机 17 联系人 18 地图 19 产品类型 20 合同 21 回款计划*/
    @Excel(name = "字段类型 1 单行文本 2 多行文本 3 单选 4日期 5 数字 6 小数 7 手机  8 文件 9 多选 10 人员 11 附件 12 部门 13 日期时间 14 邮箱 15客户 16 商机 17 联系人 18 地图 19 产品类型 20 合同 21 回款计划", width = 15)
    @ApiModelProperty(value = "字段类型 1 单行文本 2 多行文本 3 单选 4日期 5 数字 6 小数 7 手机  8 文件 9 多选 10 人员 11 附件 12 部门 13 日期时间 14 邮箱 15客户 16 商机 17 联系人 18 地图 19 产品类型 20 合同 21 回款计划")
    private Integer inputType;
    /**字段排序*/
    @Excel(name = "字段排序", width = 15)
    @ApiModelProperty(value = "字段排序")
    private Integer sort;
    /**用户id*/
    @Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
    private String userId;
    /**是否隐藏 0、不隐藏 1、隐藏*/
    @Excel(name = "是否隐藏 0、不隐藏 1、隐藏", width = 15)
    @ApiModelProperty(value = "是否隐藏 0、不隐藏 1、隐藏")
    private Integer isHide;
    /**fieldId*/
    @Excel(name = "主表id", width = 15)
    @ApiModelProperty(value = "主表id")
    private String fieldId;
    /**fieldId*/
    @Excel(name = "列宽", width = 15)
    @ApiModelProperty(value = "width")
    private String width;
    /**isSearch*/
    @Excel(name = "是否查询：0不查询，1查询，2隐藏搜索", width = 15)
    @ApiModelProperty(value = "是否查询：0不查询，1查询，2隐藏搜索")
    private Integer isSearch;
    /**isAdd*/
    @Excel(name = "是否放到添加编辑界面：0不放入，1放入", width = 15)
    @ApiModelProperty(value = "是否放到添加编辑界面：0不放入，1放入")
    private Integer isAdd;
    /**remark*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remark;
    /**inputTips*/
    @Excel(name = "输入提示", width = 15)
    @ApiModelProperty(value = "输入提示")
    private String inputTips;
    /**maxLength*/
    @Excel(name = "最大长度", width = 15)
    @ApiModelProperty(value = "最大长度")
    private Integer maxLength;
    /**defaultValue*/
    @Excel(name = "默认值", width = 15)
    @ApiModelProperty(value = "默认值")
    private String defaultValue;
    /**isUnique*/
    @Excel(name = "是否唯一 1 是 0 否", width = 15)
    @ApiModelProperty(value = "是否唯一 1 是 0 否")
    private Integer isUnique;
    /**isNull*/
    @Excel(name = "是否必填 1 是 0 否", width = 15)
    @ApiModelProperty(value = "是否必填 1 是 0 否")
    private Integer isNull;
    /**sorting*/
    @Excel(name = "排序 从小到大", width = 15)
    @ApiModelProperty(value = "排序 从小到大")
    private Integer sorting;
    /**options*/
    @Excel(name = "如果类型是选项，此处不能为空，多个选项以，隔开", width = 15)
    @ApiModelProperty(value = "如果类型是选项，此处不能为空，多个选项以，隔开")
    private String options;
    /**operating*/
    @Excel(name = "是否可以删除修改 0 改删 1 改 2 删 3 无", width = 15)
    @ApiModelProperty(value = "是否可以删除修改 0 改删 1 改 2 删 3 无")
    private Integer operating;
    /**examine_category_id*/
    @Excel(name = "审批ID", width = 15)
    @ApiModelProperty(value = "审批ID")
    private Integer examineCategoryId;

    /**字段名称*/
    @Excel(name = "字段名称", width = 15)
    @ApiModelProperty(value = "字段名称")
    private String title;
    /**字段中文名称*/
    @Excel(name = "字段中文名称", width = 15)
    @ApiModelProperty(value = "字段中文名称")
    private String key;

    public String getTitle() {
        return this.name;
    }
    public String getKey() {
        return this.fieldName;
    }

    /**fieldType*/
    @Excel(name = "是否自定义字段 1固定字段 0自定义字段", width = 15)
    @ApiModelProperty(value = "是否自定义字段 1固定字段 0自定义字段")
    private String fieldType;

    /**dicType*/
    @Excel(name = "字典类型 0 默认数据字典  1自定义类型", width = 15)
    @ApiModelProperty(value = "字典字典代码 空等于没有")
    private String dictType;

    /**dicCode*/
    @Excel(name = "字典字典代码 空等于没有", width = 15)
    @ApiModelProperty(value = "字典字典代码 空等于没有")
    private String dicCode;

    /**isColor*/
    @Excel(name = "isColor", width = 15)
    @ApiModelProperty(value = "isColor")
    private String isColor;

    /**是否样式自定义*/
    @Excel(name = "是否样式自定义", width = 15)
    @ApiModelProperty(value = "是否样式自定义")
    private String isSlot;

}
