package com.ckunion.productbom.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.ckunion.productbom.entity.ProProductInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: pro_product_info
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
public interface ProProductInfoMapper extends BaseMapper<ProProductInfo> {

    public boolean deleteInfoByBomChildoid(@Param("childoid") String childoid);

    /**
     * 根据父节点id获取info集合
     * @param parentId
     * @return
     */
    public List<ProProductInfo> getProductInfoByParentId(String parentId);

    /**
     * 归档节点
     * @param status
     * @param parentId
     */
    public void updateInfoStatusByArchived(String status, String parentId);

    /**
     * 根据parentid和签入签出标识获取info集合
     * @param inoff
     * @param parentId
     * @return
     */
    public List<ProProductInfo> getProductInfoListBySignInoff(String inoff, String parentId);

    /**
     * 根据childoid 获取所属根节点
     * @param childoid
     * @return
     */
    public ProProductInfo getRootInfoByChildId(String childoid);

}
