package org.jeecg.modules.demo3.reschecker.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xkcoding.http.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.hibernate.service.spi.ServiceException;
import org.jeecg.common.api.CommonAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.demo3.reschecker.entity.ResChecker;
import org.jeecg.modules.demo3.reschecker.mapper.ResCheckerMapper;
import org.jeecg.modules.demo3.reschecker.service.IResCheckerService;
import org.jeecg.modules.demo3.reschecker.util.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 检验员表
 * @Author: jeecg-boot
 * @Date:   2021-05-10
 * @Version: V1.0
 */
@Service
public class ResCheckerServiceImpl extends ServiceImpl<ResCheckerMapper, ResChecker> implements IResCheckerService {

    @Autowired
    private CommonAPI commonAPI;

    @Override
    public void saveResChecker(ResChecker resChecker) {
        try {
            //根据用户登录账户名获取用户信息
            LoginUser sysUser = commonAPI.getUserByName(resChecker.getLoginName());
            //获取当前登录人信息
            LoginUser sysUserLogin = (LoginUser)SecurityUtils.getSubject().getPrincipal();

            //设置员工真实姓名
            resChecker.setWorkerName(sysUser.getRealname());
            //设置状态
            resChecker.setSate(State.CHECKER_0);
            if(StringUtil.isEmpty(resChecker.getId())){
                //设置创建人真实姓名
                resChecker.setCreateName(sysUserLogin.getRealname());
                //设置创建时间
                resChecker.setCreateDate(new Date());
                this.save(resChecker);
            }else{
                //设置更新人真实姓名
                resChecker.setUpdateName(sysUserLogin.getRealname());
                //设置更新时间
                resChecker.setUpdateDate(new Date());
                this.updateById(resChecker);
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
}
