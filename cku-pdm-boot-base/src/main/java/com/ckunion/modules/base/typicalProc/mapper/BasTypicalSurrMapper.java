package com.ckunion.modules.base.typicalProc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalSurr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 工作环境要求从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface BasTypicalSurrMapper extends BaseMapper<BasTypicalSurr> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BasTypicalSurr> selectByMainId(@Param("mainId") String mainId);
}
