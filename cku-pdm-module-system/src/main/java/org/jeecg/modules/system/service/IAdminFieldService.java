package org.jeecg.modules.system.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.system.entity.AdminField;
import org.jeecg.modules.system.entity.AdminFieldSortBO;
import org.jeecg.modules.system.entity.AdminFieldSortVO;
import org.jeecg.modules.system.entity.AdminFieldStyleBO;

import java.util.List;

/**
 * @Description: 业务功能字段表
 * @Author: jeecg-boot
 * @Date:   2020-10-10
 * @Version: V1.0
 */
public interface IAdminFieldService extends IService<AdminField> {

    public List<AdminFieldSortVO> queryListHead(String menu_id, LoginUser sysUser);

    public JSONObject queryFieldConfig(String menu_id, LoginUser sysUser);

    public void setFieldStyle(AdminFieldStyleBO fieldStyle, LoginUser sysUser);

    public void setFieldConfig(LoginUser sysUser,AdminFieldSortBO fieldSort);

    public List<AdminFieldSortVO> queryAllListHead();

}
