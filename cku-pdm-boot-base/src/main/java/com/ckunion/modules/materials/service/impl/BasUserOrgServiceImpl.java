package com.ckunion.modules.materials.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.modules.materials.entity.BasUserOrg;
import com.ckunion.modules.materials.mapper.BasUserOrgMapper;
import com.ckunion.modules.materials.service.IBasUserOrgService;
import org.springframework.stereotype.Service;

/**
 * @Description: 内外勤关系表
 * @Author: jeecg-boot
 * @Date:   2020-12-14
 * @Version: V1.0
 */
@Service
public class BasUserOrgServiceImpl extends ServiceImpl<BasUserOrgMapper, BasUserOrg> implements IBasUserOrgService {
    /**
     * 查询兄弟节点信息
     * @return
     */
    /*public IPage<SysUser>  queryBroConfig(Page<SysUser> page ,String nei, String username){

        IPage<SysUser> data = baseMapper.getUserByUser(page,nei,username);
        return data;
    }*/
}
