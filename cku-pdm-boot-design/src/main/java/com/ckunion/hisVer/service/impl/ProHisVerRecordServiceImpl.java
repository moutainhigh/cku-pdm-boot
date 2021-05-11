package com.ckunion.hisVer.service.impl;

import com.ckunion.constant.ProConstant;
import com.ckunion.hisVer.entity.ProHisVerBom;
import com.ckunion.hisVer.entity.ProHisVerRecord;
import com.ckunion.hisVer.mapper.ProHisVerBomMapper;
import com.ckunion.hisVer.mapper.ProHisVerRecordMapper;
import com.ckunion.hisVer.service.IProHisVerRecordService;
import com.ckunion.productbom.entity.ProProductInfo;
import com.ckunion.productbom.entity.VwProProductBom;
import com.ckunion.productbom.mapper.ProProductInfoMapper;
import com.ckunion.productbom.service.IVwProProductBomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Iterator;
import java.util.List;

/**
 * @Description: pro_his_ver_record
 * @Author: jeecg-boot
 * @Date:   2021-05-07
 * @Version: V1.0
 */
@Slf4j
@Service
public class ProHisVerRecordServiceImpl extends ServiceImpl<ProHisVerRecordMapper, ProHisVerRecord> implements IProHisVerRecordService {

    @Autowired
    private IVwProProductBomService vwProProductBomService;

    @Autowired
    private ProHisVerRecordMapper proHisVerRecordMapper;
    @Autowired
    private ProHisVerBomMapper proHisVerBomMapper;
    @Autowired
    private ProProductInfoMapper proProductInfoMapper;


    @Override
    public void saveHisVerRecord(String currInfoId) {
        log.info("将该节点版本存入历史记录表中="+currInfoId);
        VwProProductBom vwBom = vwProProductBomService.query().eq("childoid",currInfoId).eq("relation", ProConstant.PRODUCTBOM_RELATION_NORMAL).one();
        saveHisVerRecord(currInfoId, vwBom.getId());
    }

    @Override
    public void saveHisVerRecord(String currInfoId, String bomId) {
        log.info("将该节点版本存入历史记录表中="+currInfoId+",bomId="+bomId);
        ProProductInfo currInfo = proProductInfoMapper.selectById(currInfoId);
        ProHisVerRecord hisVer = new ProHisVerRecord();
        BeanUtils.copyProperties(currInfo, hisVer,new String[]{"id","createBy","createTime","updateBy","updateTime","sysOrgCode"});
        hisVer.setInfoId(currInfoId);

        //获取跟节点（指标）用于赋值
        ProProductInfo rootInfo = proProductInfoMapper.getRootInfoByChildId(currInfoId);
        hisVer.setTypemodelId(rootInfo.getTypemodelId());
        hisVer.setTypemodelName(rootInfo.getTypemodelName());
        hisVer.setQuaLevel(rootInfo.getQuaLevel());
        hisVer.setSeriesId(rootInfo.getSeriesId());
        hisVer.setSeriesName(rootInfo.getMaterialName());//跟节点的名称就是指标名称

        proHisVerRecordMapper.insert(hisVer);


        //根节点（当前选中节点即为根节点）添加到关系从表中
        VwProProductBom vwRootBom = vwProProductBomService.getById(bomId);
        ProHisVerBom hisRootBom = new ProHisVerBom();
        BeanUtils.copyProperties(vwRootBom, hisRootBom,new String[]{"id","createBy","createTime","updateBy","updateTime","sysOrgCode"});
        hisRootBom.setMasteroid(hisVer.getId());
        hisRootBom.setParentId(null);
        hisRootBom.setNodeCreateBy(vwRootBom.getCreateBy());//记录好节点最初创建人和时间
        hisRootBom.setNodeCreateTime(vwRootBom.getCreateTime());
        proHisVerBomMapper.insert(hisRootBom);

        //循环添加
        initHisVerBom(hisVer.getId(), hisRootBom.getId(), currInfoId);
    }

    //循环添加到历史关系从表中
    private void initHisVerBom(String masteroid, String parentId, String childoid){
        List<VwProProductBom> vwBomList = vwProProductBomService.query().eq("parentoid",childoid).list();
        if(!vwBomList.isEmpty()){
            for(Iterator item = vwBomList.iterator(); item.hasNext();){
                VwProProductBom vwBom = (VwProProductBom)item.next();
                ProHisVerBom hisBom = new ProHisVerBom();
                BeanUtils.copyProperties(vwBom, hisBom,new String[]{"id","createBy","createTime","updateBy","updateTime","sysOrgCode"});
                hisBom.setParentId(parentId);
                hisBom.setMasteroid(masteroid);
                hisBom.setNodeCreateBy(vwBom.getCreateBy());//记录好节点最初创建人和时间
                hisBom.setNodeCreateTime(vwBom.getCreateTime());
                proHisVerBomMapper.insert(hisBom);
                initHisVerBom(masteroid, hisBom.getId(),vwBom.getChildoid());
            }
        }
    }

}
