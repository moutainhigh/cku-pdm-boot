package com.ckunion.modules.materials.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.materials.entity.BasUnit;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 计量单位维护
 * @Author: jeecg-boot
 * @Date:   2020-10-21
 * @Version: V1.0
 */
public interface BasUnitMapper extends BaseMapper<BasUnit> {


    @Select(" select unit_code from (SELECT rownum a,LPAD( substr( nvl( unit_code, 0 ),- 3 ) + 1, 3, 0 ) AS unit_code  FROM bas_unit  WHERE unit_pid =  #{pid}  ORDER BY unit_code DESC) where a=1   ")
    public String maxUnitCode(String pid);
}
