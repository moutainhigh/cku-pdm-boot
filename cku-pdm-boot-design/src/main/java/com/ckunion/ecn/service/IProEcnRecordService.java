package com.ckunion.ecn.service;

import com.ckunion.ecn.entity.ProEcnRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: pro_ecn_record
 * @Author: jeecg-boot
 * @Date:   2021-04-16
 * @Version: V1.0
 */
public interface IProEcnRecordService extends IService<ProEcnRecord> {
    public void saveProEcnRecord(ProEcnRecord proEcnRecord);

    /**
     * ECN变更完成
     * @param id
     */
    public String saveChangeEcnFin(String id);
}
