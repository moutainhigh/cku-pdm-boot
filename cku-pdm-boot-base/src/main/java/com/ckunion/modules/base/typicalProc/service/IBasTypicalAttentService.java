package com.ckunion.modules.base.typicalProc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalAttent;

import java.util.List;

/**
 * @Description: 注意事项从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface IBasTypicalAttentService extends IService<BasTypicalAttent> {

	public List<BasTypicalAttent> selectByMainId(String mainId);
}
