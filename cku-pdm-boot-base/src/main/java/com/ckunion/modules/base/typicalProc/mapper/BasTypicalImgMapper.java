package com.ckunion.modules.base.typicalProc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: bas_typical_img
 * @Author: jeecg-boot
 * @Date:   2021-04-12
 * @Version: V1.0
 */
public interface BasTypicalImgMapper extends BaseMapper<BasTypicalImg> {


    public boolean deleteByMainId(@Param("mainId") String mainId);

    public List<BasTypicalImg> selectByMainId(@Param("mainId") String mainId);

}
