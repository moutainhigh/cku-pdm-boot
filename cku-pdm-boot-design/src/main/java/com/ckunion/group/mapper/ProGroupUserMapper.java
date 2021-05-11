package com.ckunion.group.mapper;

import java.util.List;
import com.ckunion.group.entity.ProGroupUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 团队成员从表
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
public interface ProGroupUserMapper extends BaseMapper<ProGroupUser> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<ProGroupUser> selectByMainId(@Param("mainId") String mainId);
}
