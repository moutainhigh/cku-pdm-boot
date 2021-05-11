package com.ckunion.modules.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.applyno.entity.ProApplyMapno;
import com.ckunion.applyno.entity.ProApplyMapnoDt;
import com.ckunion.modules.code.entity.BaseNodeCode;

import java.util.List;

/**
 * @Description: 节点编码管理
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface IBaseNodeCodeService extends IService<BaseNodeCode> {
    public void saveNodeCodeByProductApplyMapno(ProApplyMapno proApplyMapno, List<ProApplyMapnoDt> proApplyMapnoDtList);
}
