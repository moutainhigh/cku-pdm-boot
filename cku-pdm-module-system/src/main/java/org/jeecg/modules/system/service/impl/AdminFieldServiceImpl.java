package org.jeecg.modules.system.service.impl;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.core.instrument.util.StringUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.constant.RedisConstant;
import org.jeecg.modules.system.entity.*;
import org.jeecg.modules.system.mapper.AdminFieldMapper;
import org.jeecg.modules.system.mapper.AdminFieldSortMapper;
import org.jeecg.modules.system.mapper.AdminFieldStyleMapper;
import org.jeecg.modules.system.mapper.AdminFieldvMapper;
import org.jeecg.modules.system.service.IAdminFieldService;
import org.jeecg.modules.system.service.IAdminFieldSortService;
import org.jeecg.modules.system.service.IAdminFieldStyleService;
import org.jeecg.modules.system.service.IAdminFieldvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 业务功能字段表
 * @Author: jeecg-boot
 * @Date:   2020-10-10
 * @Version: V1.0
 */
@Service
public class AdminFieldServiceImpl extends ServiceImpl<AdminFieldMapper, AdminField> implements IAdminFieldService {

    @Autowired
    private AdminFieldMapper adminFieldMapper;
    @Autowired
    private AdminFieldSortMapper adminFieldSortMapper;
    @Autowired
    private AdminFieldStyleMapper adminFieldStyleMapper;
    @Autowired
    private AdminFieldvMapper adminFieldvMapper;

    @Autowired
    private IAdminFieldService adminFieldService;
    @Autowired
    private IAdminFieldSortService adminFieldSortService;
    @Autowired
    private IAdminFieldStyleService adminFieldStyleService;
    @Autowired
    private IAdminFieldvService adminFieldvService;
    @Autowired
    private RedisUtil redisUtil;


    /**
     * 查询模块字段列表
     * @return data
     */
    @Override
    public List<AdminFieldSortVO> queryListHead(String menu_id, LoginUser sysUser)  {
        List<AdminFieldSortVO> adminFieldSortVOS = new ArrayList<>();
        try {
            //之后可能增加字段权限功能 需要字段权限表
            String jsonStr = (String)redisUtil.hget(RedisConstant.MENUHEAD + sysUser.getUsername(), menu_id);
            if(StringUtils.isEmpty(jsonStr) ||  "[]".equals(jsonStr)){
                adminFieldSortVOS = adminFieldSortMapper.queryListHead(menu_id, sysUser.getUsername());
                jsonStr = JSONUtil.toJsonStr(adminFieldSortVOS);
                redisUtil.hset(RedisConstant.MENUHEAD + sysUser.getUsername() , menu_id, jsonStr);
            }else{
                adminFieldSortVOS = JSONUtil.toBean(jsonStr, new TypeReference<List<AdminFieldSortVO>>(){},false);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
       // System.out.println();
        return adminFieldSortVOS;
    }


    /**
     * 查询模块字段列表
     * @return data
     */
    @Override
    public List<AdminFieldSortVO> queryAllListHead() {


        List<AdminFieldSortVO> fieldSortVOList = adminFieldSortMapper.queryAllListHead();

        //之后可能增加字段权限功能 需要字段权限表

        return fieldSortVOList;
    }

    /**
     * 查询字段配置
     *
     * @param menu_id 菜单类型
     */
    public JSONObject queryFieldConfig(String menu_id, LoginUser sysUser) {

        //查出自定义字段，查看顺序表是否存在该字段，没有则插入，设为隐藏
        List<AdminFieldSort> list = adminFieldSortService.query().select()
                .eq("user_id",sysUser.getUsername())
                .eq("menu_id",menu_id)
                .groupBy("sort").list();
        List hideValuelist=new ArrayList();
        List Valuelist=new ArrayList();

        for(AdminFieldSort one :list){
            if(one.getIsHide()==1){
                hideValuelist.add(one);
            }
            if(one.getIsHide()==0){
                Valuelist.add(one);
            }
        }

        Map remap=new HashMap();
        remap.put("hideValue",hideValuelist);
        remap.put("Value",Valuelist);

        return new JSONObject().fluentPutAll(remap);
    }


    /**
     * 修改字段宽度
     *
     * @param fieldStyle data
     */
    public void setFieldStyle(AdminFieldStyleBO fieldStyle, LoginUser sysUser) {

        QueryWrapper<AdminFieldStyle> queryWrapper = new QueryWrapper<AdminFieldStyle>();
        queryWrapper.eq("menu_id",fieldStyle.getMenu_id());
        queryWrapper.eq("field_name",fieldStyle.getFieldName());
        queryWrapper.eq("user_id",sysUser.getUsername());

        AdminFieldStyle crmFieldSort = adminFieldStyleService.getOne(queryWrapper);

        if (crmFieldSort != null) {
            crmFieldSort.setStyle(fieldStyle.getWidth());
    //      adminFieldStyleService.updateById(crmFieldSort);
            adminFieldStyleService.saveOrUpdate(crmFieldSort);
        }else{
            crmFieldSort=new AdminFieldStyle();
            crmFieldSort.setStyle(fieldStyle.getWidth());
            crmFieldSort.setFieldName(fieldStyle.getFieldName());
            crmFieldSort.setMenuId(fieldStyle.getMenu_id());
            crmFieldSort.setUserId(sysUser.getUsername());
            adminFieldStyleService.saveOrUpdate(crmFieldSort);
        }

    }




    /**
     * 修改字段配置
     *
     * @param fieldSort data
     */
    public void setFieldConfig(LoginUser sysUser,AdminFieldSortBO fieldSort) {

        List<AdminFieldSort> fieldSortList = adminFieldSortService.query().eq("menu_id", fieldSort.getMenu_id()).eq("user_id",sysUser.getUsername()).list();
        List<String> noHideIds = fieldSort.getNoHideIds();
        for (int i = 0; i < fieldSortList.size(); i++) {
            AdminFieldSort sort = fieldSortList.get(i);
            if (noHideIds.contains(sort.getId())) {
                sort.setSort(noHideIds.indexOf(sort.getId()) + 1);
            }
            if (fieldSort.getHideIds().contains(sort.getId())) {
                sort.setIsHide(1);
                continue;
            }
            if (fieldSort.getNoHideIds().contains(sort.getId())) {
                sort.setIsHide(0);
            }
        }
       // System.out.println();

        adminFieldSortService.updateBatchById(fieldSortList, 200);
        //更新缓存

        List<AdminFieldSortVO> adminFieldSortVOS = adminFieldSortMapper.queryListHead(fieldSort.getMenu_id(), sysUser.getUsername());
        String userId = fieldSortList.get(0).getUserId();
        String jsonStr = JSONUtil.toJsonStr(adminFieldSortVOS);
        redisUtil.hset(RedisConstant.MENUHEAD + userId , fieldSort.getMenu_id(), jsonStr);
    }



}
