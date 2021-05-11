package com.ckunion.applyno.mapper;

import java.util.List;
import com.ckunion.applyno.entity.ProApplyMapnoDt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: pro_apply_mapno_dt
 * @Author: jeecg-boot
 * @Date:   2021-04-23
 * @Version: V1.0
 */
public interface ProApplyMapnoDtMapper extends BaseMapper<ProApplyMapnoDt> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<ProApplyMapnoDt> selectByMainId(@Param("mainId") String mainId);

	List<ProApplyMapnoDt> getApplyMapnoDtByParentoid(String mainId, String parentoid);
}
