package com.ckunion.modules.base.typicalProc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalPurpose;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 目的和目标从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface BasTypicalPurposeMapper extends BaseMapper<BasTypicalPurpose> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BasTypicalPurpose> selectByMainId(@Param("mainId") String mainId);
}
