package com.ckunion.group.service;

import com.ckunion.group.entity.ProGroupUser;
import com.ckunion.group.entity.ProGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 团队
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
public interface IProGroupService extends IService<ProGroup> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ProGroup proGroup, List<ProGroupUser> proGroupUserList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ProGroup proGroup, List<ProGroupUser> proGroupUserList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
