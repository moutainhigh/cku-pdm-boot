package com.ckunion.modules.code.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.applyno.entity.ProApplyMapno;
import com.ckunion.applyno.entity.ProApplyMapnoDt;
import com.ckunion.applyno.mapper.ProApplyMapnoDtMapper;
import com.ckunion.modules.code.entity.BaseNodeCode;
import com.ckunion.modules.code.mapper.BaseNodeCodeMapper;
import com.ckunion.modules.code.service.IBaseNodeCodeService;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 节点编码管理
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Service
public class BaseNodeCodeServiceImpl extends ServiceImpl<BaseNodeCodeMapper, BaseNodeCode> implements IBaseNodeCodeService {

    @Autowired
    private BaseNodeCodeMapper baseNodeCodeMapper;
    @Autowired
    private ProApplyMapnoDtMapper proApplyMapnoDtMapper;
    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public void saveNodeCodeByProductApplyMapno(ProApplyMapno proApplyMapno, List<ProApplyMapnoDt> proApplyMapnoDtList) {
        if(proApplyMapnoDtList!=null && proApplyMapnoDtList.size()>0) {
            for(ProApplyMapnoDt entity:proApplyMapnoDtList) {

                ProApplyMapnoDt dt = proApplyMapnoDtMapper.selectById(entity.getId());

                //2、保存到编码表中
                BaseNodeCode code = new BaseNodeCode();
                code.setNodeCode(entity.getNodeCode());
                code.setCodeVer("A.1");
                code.setCodeType(dt.getBasTypeName());
                code.setApplicant(dt.getCreateBy());
                SysUser user = sysUserMapper.getUserByName(dt.getCreateBy());
                code.setApplicantName(user.getRealname());
                code.setApplicantTime(dt.getCreateTime());
                code.setApplicantDesc(proApplyMapno.getApplyDesc());
                baseNodeCodeMapper.insert(code);
            }
        }
    }
}
