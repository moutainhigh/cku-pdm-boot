package com.ckunion.modules.base.typicalProc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalPurposeTarget;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 目标从从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface BasTypicalPurposeTargetMapper extends BaseMapper<BasTypicalPurposeTarget> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BasTypicalPurposeTarget> selectByMainId(@Param("mainId") String mainId);
}
