package com.ckunion.modules.base.typicalProc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalProcess;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 操作过程从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface BasTypicalProcessMapper extends BaseMapper<BasTypicalProcess> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BasTypicalProcess> selectByMainId(@Param("mainId") String mainId);
}
