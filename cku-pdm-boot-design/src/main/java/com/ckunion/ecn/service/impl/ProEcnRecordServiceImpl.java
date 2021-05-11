package com.ckunion.ecn.service.impl;

import com.ckunion.constant.CkuCommons;
import com.ckunion.constant.ProConstant;
import com.ckunion.ecn.entity.ProEcnRecord;
import com.ckunion.ecn.mapper.ProEcnRecordMapper;
import com.ckunion.ecn.service.IProEcnRecordService;
import com.ckunion.hisVer.service.IProHisVerRecordService;
import com.ckunion.productbom.entity.ProProductInfo;
import com.ckunion.productbom.mapper.ProProductInfoMapper;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jeecg.common.util.FillRuleUtil;
import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: pro_ecn_record
 * @Author: jeecg-boot
 * @Date:   2021-04-16
 * @Version: V1.0
 */
@Service
public class ProEcnRecordServiceImpl extends ServiceImpl<ProEcnRecordMapper, ProEcnRecord> implements IProEcnRecordService {

    @Autowired
    private IProHisVerRecordService proHisVerRecordService;

    @Autowired
    private ProEcnRecordMapper proEcnRecordMapper;
    @Autowired
    private ProProductInfoMapper infoMapper;


    @Override
    public void saveProEcnRecord(ProEcnRecord proEcnRecord) {
        proEcnRecord.setEcnNumber(FillRuleUtil.executeRule("pro_ecn_number", new JSONObject()) + "");
        proEcnRecord.setAffNumber(proEcnRecord.getBefNumber());
        proEcnRecord.setState(ProConstant.PRO_ECN_STATE_ADD);
        proEcnRecord.setChangeState(ProConstant.PRO_ECN_CHANGESTATE_NOCHANGE);
        proEcnRecord.setBeginDate(new Date());
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        proEcnRecord.setCreator(user.getRealname());


        //记录历史版本
        proHisVerRecordService.saveHisVerRecord(proEcnRecord.getInfoId());

        proEcnRecordMapper.insert(proEcnRecord);
    }

    @Override
    public String saveChangeEcnFin(String id) {
        //变更单本身处理
        ProEcnRecord ecn = proEcnRecordMapper.selectById(id);
        ecn.setState(ProConstant.PRO_ECN_STATE_APPR_FIN);
        proEcnRecordMapper.updateById(ecn);


        //TODO 此处没有想好是新建一个节点，还是用原来的节点

        //节点本身处理
        ProProductInfo info = infoMapper.selectById(ecn.getInfoId());
        //版本升级后，状态和签出等属性都重头开始
        info.setVersionNum(CkuCommons.getVersionNum(info.getVersionNum(),"Y"));
        info.setStatus(ProConstant.PRODUCTINFO_STATUS_ADD);
        info.setSignInoff("OFF");
        //签出人
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        info.setSignOffUser(user.getUsername());
        info.setSignOffUsername(user.getRealname());
        infoMapper.updateById(info);


        return null;
    }
}
