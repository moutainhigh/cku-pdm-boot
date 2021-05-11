package com.ckunion.modules.base.typicalProc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalProcessCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 操作过程检验方法从从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface BasTypicalProcessCheckMapper extends BaseMapper<BasTypicalProcessCheck> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BasTypicalProcessCheck> selectByMainId(@Param("mainId") String mainId);

	public List<BasTypicalProcessCheck> selectByProcessId(@Param("processId") String processId);

}
