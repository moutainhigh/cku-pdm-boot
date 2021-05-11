package com.ckunion.productbom.service.impl;

import com.ckunion.constant.CkuCommons;
import com.ckunion.constant.ProConstant;
import com.ckunion.hisVer.service.IProHisVerRecordService;
import com.ckunion.productbom.entity.ProProductBom;
import com.ckunion.productbom.entity.ProProductInfo;
import com.ckunion.productbom.mapper.ProProductBomMapper;
import com.ckunion.productbom.mapper.ProProductInfoMapper;
import com.ckunion.productbom.service.IProProductInfoService;
import com.ckunion.productbom.vo.BasFilePage;
import com.ckunion.productbom.vo.BasMaterialInfoPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: pro_product_info
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
@Slf4j
@Service
public class ProProductInfoServiceImpl extends ServiceImpl<ProProductInfoMapper, ProProductInfo> implements IProProductInfoService {

    @Autowired
    private IProHisVerRecordService proHisVerRecordService;

    @Autowired
    private ProProductInfoMapper proProductInfoMapper;
    @Autowired
    private ProProductBomMapper proProductBomMapper;


    @Override
    public void saveProductInfo(ProProductInfo info,ProProductBom bom) {
        log.info("新增产品结构或Doc="+info);
        info.setId(null);
        info.setStatus(ProConstant.PRODUCTINFO_STATUS_ADD);
        info.setVersionNum("A.1");
        info.setSignInoff("OFF");

        //签出人
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        info.setSignOffUser(user.getUsername());
        info.setSignOffUsername(user.getRealname());

        proProductInfoMapper.insert(info);

        bom.setId(null);
        bom.setChildoid(info.getId());
        proProductBomMapper.insert(bom);

    }

    @Override
    public void updateProductInfo(ProProductInfo info,ProProductBom bom) {
        proProductInfoMapper.updateById(info);
        proProductBomMapper.updateById(bom);
    }

    @Override
    public void delProductInfo(String bomId) {
        log.info("删除产品结构bomId="+bomId);

        ProProductBom bom = proProductBomMapper.selectById(bomId);

        //如果还包含子节点，一并删除
        proProductInfoMapper.deleteInfoByBomChildoid(bom.getChildoid());
        proProductBomMapper.deleteBomByChildoid(bom.getChildoid());


    }


    @Override
    public String changeInfoSignInoff(String bomId, String inoffFlag) {
        ProProductBom bom = proProductBomMapper.selectById(bomId);

        ProProductInfo info = proProductInfoMapper.selectById(bom.getChildoid());
        if(!ProConstant.BOM_PART_TYPE_PART.equals(info.getPartType())){
            return "不是节点类型不能签入签出";
        }

        if(!ProConstant.PRODUCTINFO_STATUS_ADD.equals(info.getStatus())){
            return "不是拟制中的节点不能使用签入签出工具";
        }

        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //签入签出的条件判断
        if("OFF".equals(inoffFlag)){
            //签出
            if("OFF".equals(info.getSignInoff())){
                return "节点"+info.getMaterialName()+"已被 ["+info.getSignOffUsername()+"] 签出，不能再次签出";
            }
            info.setSignInoff("OFF");
            //记录签出人
            info.setSignOffUser(user.getUsername());
            info.setSignOffUsername(user.getRealname());

            //记录历史版本
            proHisVerRecordService.saveHisVerRecord(info.getId(), bomId);

        }else{
            //签入
            if("IN".equals(info.getSignInoff())){
                return "节点"+info.getMaterialName()+"已签入，不能再次签入";
            }
            if(!user.getUsername().equals(info.getSignOffUser())){
                return "节点"+info.getMaterialName()+"由 ["+info.getSignOffUsername()+"] 签出，不能代为签入";
            }

            info.setSignInoff("IN");
            //升级小版本
            info.setVersionNum(CkuCommons.getVersionNum(info.getVersionNum(),"N"));

        }
        proProductInfoMapper.updateById(info);

        return null;
    }



    @Override
    public void addSeriesAndBomRela(String branchSeriesId, String bomId) {


        /*
        //先获取分指标对应的info表信息
        Map<String,Object> infoMap = new HashMap<String,Object>();
        infoMap.put("series_id",branchSeriesId);
        List<ProProductInfo> infoList = proProductInfoMapper.selectByMap(infoMap);
        ProProductInfo info = infoList.get(0);


        20210425 wuj 注释，分指标不需要挂接到bom关系中
        //更换选中bom对应的指标info表id
        ProProductBom bom = proProductBomMapper.selectById(bomId);
        bom.setParentoid(infoList.get(0).getId());
        proProductBomMapper.updateById(bom);

        //更改分指标包含子节点标识
        ProProductBom parentBom = proProductBomMapper.getBomByChildoid(branchSeriesId);
        parentBom.setHasChild("Y");
        proProductBomMapper.updateById(parentBom);
         */
    }

    @Override
    public void saveProductInfoByBasFile(BasFilePage basFilePage) {
        log.info("文件库添加到bom基础信息和bom关系,basFile"+basFilePage);
        ProProductInfo info = new ProProductInfo();
        info.setMaterialName(basFilePage.getFileName());
        info.setMaterialCode(basFilePage.getFileCode());
        info.setMapNo(basFilePage.getFileCode());
        //info.setBasTypeCode()
        info.setBasTypeName(basFilePage.getFileType());
        info.setPartType(ProConstant.BOM_PART_TYPE_DOC);
        info.setVersionNum(basFilePage.getVersions());
        info.setUploadFile(basFilePage.getFilePath());
        info.setRelation(ProConstant.PRODUCTBOM_RELATION_NORMAL);

        info.setSignInoff("OFF");
        info.setStatus(ProConstant.PRODUCTINFO_STATUS_ADD);
        proProductInfoMapper.insert(info);

        ProProductBom childBom = new ProProductBom();
        childBom.setMaterialName(basFilePage.getFileName());
        childBom.setMapNo(basFilePage.getFileCode());
        childBom.setChildoid(info.getId());
        childBom.setPerQty(new BigDecimal("1"));


        childBom.setParentoid(basFilePage.getParentoid());

        proProductBomMapper.insert(childBom);

    }

    @Override
    public void saveProductInfoByBasMat(BasMaterialInfoPage basMatInfoPage) {
        log.info("原材料基础信息添加到bom基础信息和bom关系,basMaterialInfo"+basMatInfoPage);
        ProProductInfo info = new ProProductInfo();
        BeanUtils.copyProperties(basMatInfoPage, info);

        info.setMapNo(basMatInfoPage.getMaterialCode());
        //info.setBasTypeCode(basMatInfoPage.getTypeoid());
        info.setBasTypeName(basMatInfoPage.getTypeName());
        info.setRelation(ProConstant.PRODUCTBOM_RELATION_NORMAL);
        info.setPartType(ProConstant.BOM_PART_TYPE_MAT);
        info.setSignInoff("OFF");
        info.setStatus(ProConstant.PRODUCTINFO_STATUS_ADD);
        proProductInfoMapper.insert(info);

        ProProductBom childBom = new ProProductBom();
        childBom.setMaterialName(basMatInfoPage.getMaterialName());
        childBom.setMapNo(basMatInfoPage.getMaterialCode());
        childBom.setChildoid(info.getId());
        childBom.setPerQty(new BigDecimal("1"));

        childBom.setParentoid(basMatInfoPage.getParentoid());

        proProductBomMapper.insert(childBom);
    }

    @Override
    public String saveBomByPaste(String toId, String pasteId, String pasteType) {
        ProProductBom toBom = proProductBomMapper.selectById(toId);
        ProProductBom pasteBom = proProductBomMapper.selectById(pasteId);
        if(null == toBom || null ==pasteBom){
            return "根据参数无法获取到bom节点";
        }

        if("0".equals(pasteType)){
            //本身
            ProProductInfo pasteInfo = proProductInfoMapper.selectById(pasteBom.getChildoid());
            //注意，这里一定是粘贴到哪儿去节点的id
            saveInfoBom(pasteInfo, pasteBom, toBom.getChildoid());

        }else{
            //本身+所有子节点
            List<ProProductBom> pasteBomList = proProductBomMapper.getAllChildBomByParent(pasteBom.getChildoid());
            for(ProProductBom bom : pasteBomList){
                ProProductInfo info = proProductInfoMapper.selectById(bom.getChildoid());

                String parentOid = null;
                if(pasteBom.getId().equals(bom.getId())){
                    //根节点所属parentoid 一定是粘贴到哪儿去节点，其它bom的不变
                    parentOid=toBom.getChildoid();
                }

                saveInfoBom(info, bom, parentOid);

            }
        }
        return null;

    }

    //添加info表和bom关系表公共方法
    private void saveInfoBom(ProProductInfo pasteInfo,ProProductBom pasteBom, String parentOid){
        //拷贝info表
        ProProductInfo newInfo = new ProProductInfo();
        BeanUtils.copyProperties(pasteInfo, newInfo);
        newInfo.setId(null);
        newInfo.setCreateBy(null);
        newInfo.setCreateTime(null);
        newInfo.setUpdateBy(null);
        newInfo.setUpdateTime(null);
        newInfo.setSysOrgCode(null);

        newInfo.setSignInoff("OFF");
        newInfo.setStatus(ProConstant.PRODUCTINFO_STATUS_ADD);

        proProductInfoMapper.insert(newInfo);

        //拷贝bom关系表
        ProProductBom newBom = new ProProductBom();
        BeanUtils.copyProperties(pasteBom, newBom);
        newBom.setId(null);
        newBom.setCreateBy(null);
        newBom.setCreateTime(null);
        newBom.setUpdateBy(null);
        newBom.setUpdateTime(null);
        newBom.setSysOrgCode(null);
        newBom.setChildoid(newInfo.getId());

        //如果传递了，就使用传递的parentOid（传递的是粘贴到哪儿去的节点），空就仍是原来的，关系不变
        if(StringUtils.isNotEmpty(parentOid)){
            newBom.setParentoid(parentOid);
        }

        proProductBomMapper.insert(newBom);
    }


    @Override
    public List<ProProductInfo> getProductInfoByParentId(String parentId) {
        return proProductInfoMapper.getProductInfoByParentId(parentId);
    }

    @Override
    public String saveBomArchived(String infoId) {
        log.info("节点归档="+infoId);

        //先判断是否还存在没有签入的节点
        //List<ProProductInfo> infoList = proProductInfoMapper.getProductInfoListBySignInoff("OFF",infoId);
        List<ProProductInfo> infoList = proProductInfoMapper.getProductInfoByParentId(infoId);
        if(null != infoList && infoList.size()>0){
            for(ProProductInfo info : infoList){
                if("OFF".equals(info.getSignInoff())){
                    return "存在未签入的节点，不能完成归档："+info.getMaterialName();
                }
                if(StringUtils.isEmpty(info.getMapNo())){
                    return "存在没有图号的节点，不能完成归档："+info.getMaterialName();
                }
            }

        }

        proProductInfoMapper.updateInfoStatusByArchived(ProConstant.PRODUCTINFO_STATUS_ARCHIVED,infoId);

        return null;
    }
}
