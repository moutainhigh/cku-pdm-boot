package com.ckunion.modules.base.typicalProc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.typicalProc.entity.BasTypicalFile;

import java.util.List;

/**
 * @Description: 引用文件从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface IBasTypicalFileService extends IService<BasTypicalFile> {

	public List<BasTypicalFile> selectByMainId(String mainId);
}
