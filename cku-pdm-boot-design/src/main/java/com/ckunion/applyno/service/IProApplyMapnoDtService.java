package com.ckunion.applyno.service;

import com.ckunion.applyno.entity.ProApplyMapnoDt;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: pro_apply_mapno_dt
 * @Author: jeecg-boot
 * @Date:   2021-04-23
 * @Version: V1.0
 */
public interface IProApplyMapnoDtService extends IService<ProApplyMapnoDt> {

	public List<ProApplyMapnoDt> selectByMainId(String mainId);

	public List<ProApplyMapnoDt> getApplyMapnoDtByParentoid(String mainId, String parentoid);
}
