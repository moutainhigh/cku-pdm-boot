package com.ckunion.modules.base.typicalProc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 引用文件从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface BasTypicalFileMapper extends BaseMapper<BasTypicalFile> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BasTypicalFile> selectByMainId(@Param("mainId") String mainId);
}
