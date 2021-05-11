package com.ckunion.modules.base.typicalProc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalAttent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 注意事项从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface BasTypicalAttentMapper extends BaseMapper<BasTypicalAttent> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BasTypicalAttent> selectByMainId(@Param("mainId") String mainId);
}
