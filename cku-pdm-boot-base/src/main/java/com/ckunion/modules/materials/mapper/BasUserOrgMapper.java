package com.ckunion.modules.materials.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckunion.modules.materials.entity.BasUserOrg;

/**
 * @Description: 内外勤关系表
 * @Author: jeecg-boot
 * @Date:   2020-12-14
 * @Version: V1.0
 */
public interface BasUserOrgMapper extends BaseMapper<BasUserOrg> {

    /**
     * 根据内勤查询外勤
     * @param page
     * @param
     * @return
     */
    //IPage<SysUser> getUserByUser(Page page, @Param("neiuser") String neiuser, @Param("username") String username);

}
