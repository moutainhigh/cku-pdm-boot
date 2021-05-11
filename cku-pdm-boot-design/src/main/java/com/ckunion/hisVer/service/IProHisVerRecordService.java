package com.ckunion.hisVer.service;

import com.ckunion.hisVer.entity.ProHisVerRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.productbom.entity.ProProductInfo;
import com.ckunion.productbom.entity.VwProProductBom;

/**
 * @Description: pro_his_ver_record
 * @Author: jeecg-boot
 * @Date:   2021-05-07
 * @Version: V1.0
 */
public interface IProHisVerRecordService extends IService<ProHisVerRecord> {


    /**
     * 保存历史版本
     * @param currInfoId
     */
    public void saveHisVerRecord(String currInfoId);
    /**
     * 保存历史版本
     * @param currInfoId
     * @param bomId
     */
    public void saveHisVerRecord(String currInfoId, String bomId);
}
