package com.ckunion.modules.base.typicalProc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalMat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 原材料、辅料、材料从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface BasTypicalMatMapper extends BaseMapper<BasTypicalMat> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BasTypicalMat> selectByMainId(@Param("mainId") String mainId);
}
