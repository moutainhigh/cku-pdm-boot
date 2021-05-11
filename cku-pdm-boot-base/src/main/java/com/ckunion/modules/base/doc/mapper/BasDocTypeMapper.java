package com.ckunion.modules.base.doc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.base.doc.entity.BasDocType;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Description: 文档类型
 * @Author: jeecg-boot
 * @Date:   2021-03-10
 * @Version: V1.0
 */
public interface BasDocTypeMapper extends BaseMapper<BasDocType> {

    public Map<String,Object> getMaxCode(@Param("pid") String pid, @Param("parentCodeLength") int parentCodeLength);

}
