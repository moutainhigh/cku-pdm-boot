package com.ckunion.modules.base.typicalProc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalEqu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 设备仪器从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface BasTypicalEquMapper extends BaseMapper<BasTypicalEqu> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BasTypicalEqu> selectByMainId(@Param("mainId") String mainId);
}
