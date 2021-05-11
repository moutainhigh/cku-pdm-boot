package com.ckunion.productbom.service;

import com.ckunion.productbom.entity.ProProductBom;
import com.ckunion.productbom.entity.ProProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.productbom.vo.BasFilePage;
import com.ckunion.productbom.vo.BasMaterialInfoPage;

import java.util.List;

/**
 * @Description: pro_product_info
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
public interface IProProductInfoService extends IService<ProProductInfo> {

    public void saveProductInfo(ProProductInfo info, ProProductBom bom);

    /**
     * 根据文件库库 添加文档节点信息及bom关系
     * @param basFilePage
     */
    public void saveProductInfoByBasFile(BasFilePage basFilePage);

    /**
     * 根据原材料基础信息 添加bom物料信息及bom关系
     * @param basMatInfoPage
     */
    public void saveProductInfoByBasMat(BasMaterialInfoPage basMatInfoPage);

    public void updateProductInfo(ProProductInfo info,ProProductBom bom);

    public void delProductInfo(String bomId);

    /**
     * bom粘贴
     * @param toId
     * @param pasteId
     * @param pasteType
     */
    public String saveBomByPaste(String toId, String pasteId, String pasteType);

    /**
     * 修改节点状况签入或签出
     */
    public String changeInfoSignInoff(String bomId, String inoffFlag);

    /**
     * 分指标关联产品结构
     * @param branchSeriesId
     * @param bomId
     */
    public void addSeriesAndBomRela(String branchSeriesId, String bomId);


    /**
     * 根据父节点id获取info集合
     * @param parentId
     * @return
     */
    public List<ProProductInfo> getProductInfoByParentId(String parentId);

    /**
     * bom节点归档
     * @param infoId
     */
    public String saveBomArchived(String infoId);


}
