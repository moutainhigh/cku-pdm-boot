package com.ckunion.productbom.entity;

import lombok.Data;
import java.util.List;


@Data
public class BomTree {

    //（前台vue组件自带和规定的） 给的是product_bom表的 id
    public String key;

    //节点名称（前台vue组件自带和规定的）给的是拼接的数据
    public String title;

    //是否有叶子节点（这个是反的isLeaf为true是没有子节点，为false是有子节点，前台vue组件自带和规定的）
    public boolean isLeaf;

    //图标类
    public BomTreeSlots slots;
    //孩子节点
    public List<BomTree> children;


    //product_info表的 id
    public String infoId;

    //图号
    public String mapNo;
    //名称
    public String materialName;
    //版本
    public String versionNum;
    //类型 0结构件、1原材料、2文档
    private String partType;
    //节点状态
    private String status;
    //签入签出状况IN为签入OFF为签出
    private String signInoff;
    //签出人
    private String signOffUser;

    //指标id
    public String seriesId;

}
