package com.ckunion.group.service;

import com.ckunion.group.entity.ProGroupUser;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 团队成员从表
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
public interface IProGroupUserService extends IService<ProGroupUser> {

	public List<ProGroupUser> selectByMainId(String mainId);
}
