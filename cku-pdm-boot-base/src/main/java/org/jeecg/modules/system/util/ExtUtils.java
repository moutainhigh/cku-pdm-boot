package org.jeecg.modules.system.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.system.entity.AdminFieldValue;
import org.jeecg.modules.system.service.IAdminFieldValueService;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 增加拓展字段属性
 *
 * @Author: 李鸿昊
 * @Date
 * @Version 1.0
 */
public class ExtUtils {

    /**
     *
     * @param menu_id 菜单id
     * @param pageList 需要增加附加值的list  这个方法是循环添加  需要定义id属性
     * @param clz 展示类 需要定义id属性 和 Map类型的 ext属性
     * @return
     */
    public static IPage<Object> SelectJointExt(String menu_id, IPage<?> pageList, Class clz) {

        IAdminFieldValueService adminFieldValueService= SpringContextUtils.getBean(IAdminFieldValueService.class);

        List<Object> extlist=new ArrayList<>();

        pageList.getRecords().stream().forEach(one->{
            Object reone = null;
            Method method = null;
            Method method1 = null;
            List<AdminFieldValue> valuelist = new ArrayList<>();
            try {
                method = clz.getMethod("getId", null);
                method1 = clz.getMethod("setExt", new Class[]{Map.class});
                reone = clz.newInstance();
                BeanUtils.copyProperties(one, reone);
                valuelist = adminFieldValueService.query().eq("master_id", method.invoke(reone, null)).eq("menu_id", menu_id).list();
                Map remap = new HashMap();
                for (AdminFieldValue field : valuelist) {
                    remap.put(field.getFieldName(), field.getValue());
                }
                method1.invoke(reone, remap);
                extlist.add(reone);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        IPage<Object> repageList=new Page<>();
        BeanUtils.copyProperties(pageList, repageList);
        repageList.setRecords(extlist);

        return repageList;
    }



    public static boolean AddJointExt(String menu_id,Object obj) {

        IAdminFieldValueService adminFieldValueService= SpringContextUtils.getBean(IAdminFieldValueService.class);

        List<Object> extlist=new ArrayList<>();

        Method idm = null;
        Method extm = null;
        Map<String,String> ExtMap=null;
        Object id=null;
        boolean ok=false;

        Class clz=obj.getClass();

        try {
            idm = clz.getMethod("getId", null);
            extm = clz.getMethod("getExt", null);
            ExtMap= (Map<String, String>) extm.invoke(obj, null);
            id=idm.invoke(obj, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }




        if(ExtMap!=null){
            for(Map.Entry<String, String> onemap:ExtMap.entrySet()){
                AdminFieldValue one=adminFieldValueService.query()
                        .eq("menu_id",menu_id)
                        .eq("field_name",onemap.getKey())
                        .eq("master_id",id)
                        .one();
                //如果又tableext注解则增加
                AdminFieldValue save=new AdminFieldValue();
                if(one!=null){
                    BeanUtils.copyProperties(one, save);
                }
                save.setMasterId(id+"");
                save.setFieldName(onemap.getKey());
                save.setValue(onemap.getValue());
                save.setMenuId(menu_id);
                ok=adminFieldValueService.saveOrUpdate(save);
            }
        }

        return ok;
    }



    /**
     *
     * @param menu_id 菜单id
     * @param one 实体
     * @param clz 展示类 需要定义id属性 和 Map类型的 ext属性
     * @return
     */
    public static Object SelectJointExtOne(String menu_id, Object one, Class clz) {

            IAdminFieldValueService adminFieldValueService= SpringContextUtils.getBean(IAdminFieldValueService.class);

            Object reone = null;
            Method method = null;
            Method method1 = null;
            List<AdminFieldValue> valuelist = new ArrayList<>();
            try {
                method = clz.getMethod("getId", null);
                method1 = clz.getMethod("setExt", new Class[]{Map.class});
                reone = clz.newInstance();
                BeanUtils.copyProperties(one, reone);
                valuelist = adminFieldValueService.query().eq("master_id", method.invoke(reone, null)).eq("menu_id", menu_id).list();
                Map remap = new HashMap();
                for (AdminFieldValue field : valuelist) {
                    remap.put(field.getFieldName(), field.getValue());
                }
                method1.invoke(reone, remap);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


        return reone;
    }


    
}