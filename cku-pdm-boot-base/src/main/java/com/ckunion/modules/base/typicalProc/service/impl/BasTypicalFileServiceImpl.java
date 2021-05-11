package com.ckunion.modules.base.typicalProc.service.impl;

import com.ckunion.modules.base.typicalProc.entity.BasTypicalFile;
import com.ckunion.modules.base.typicalProc.mapper.BasTypicalFileMapper;
import com.ckunion.modules.base.typicalProc.service.IBasTypicalFileService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 引用文件从表
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Service
public class BasTypicalFileServiceImpl extends ServiceImpl<BasTypicalFileMapper, BasTypicalFile> implements IBasTypicalFileService {
	
	@Autowired
	private BasTypicalFileMapper basTypicalFileMapper;
	
	@Override
	public List<BasTypicalFile> selectByMainId(String mainId) {
		return basTypicalFileMapper.selectByMainId(mainId);
	}
}
