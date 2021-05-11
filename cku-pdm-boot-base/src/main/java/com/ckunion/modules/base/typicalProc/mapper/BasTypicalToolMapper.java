package com.ckunion.modules.base.typicalProc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalTool;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 工装、用品从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface BasTypicalToolMapper extends BaseMapper<BasTypicalTool> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BasTypicalTool> selectByMainId(@Param("mainId") String mainId);
}
