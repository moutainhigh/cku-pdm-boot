package com.ckunion.modules.base.typicalProc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ckunion.modules.base.typicalProc.entity.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 典型工艺库
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
public interface IBasTypicalProcService extends IService<BasTypicalProc> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(BasTypicalProc basTypicalProc, List<BasTypicalPurpose> basTypicalPurposeList, List<BasTypicalPurposeTarget> basTypicalPurposeTargetList, List<BasTypicalMat> basTypicalMatList, List<BasTypicalEqu> basTypicalEquList, List<BasTypicalTool> basTypicalToolList, List<BasTypicalProcess> basTypicalProcessList, List<BasTypicalSurr> basTypicalSurrList, List<BasTypicalAttent> basTypicalAttentList, List<BasTypicalFile> basTypicalFileList, List<BasTypicalImg> basTypicalImgList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(BasTypicalProc basTypicalProc, List<BasTypicalPurpose> basTypicalPurposeList, List<BasTypicalPurposeTarget> basTypicalPurposeTargetList, List<BasTypicalMat> basTypicalMatList, List<BasTypicalEqu> basTypicalEquList, List<BasTypicalTool> basTypicalToolList, List<BasTypicalProcess> basTypicalProcessList, List<BasTypicalSurr> basTypicalSurrList, List<BasTypicalAttent> basTypicalAttentList, List<BasTypicalFile> basTypicalFileList, List<BasTypicalImg> basTypicalImgList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

	/**
	 * 打印典型工艺
	 * @param proc
	 * @return
	 */
	public String printTypicalProc(BasTypicalProc proc);
	
}
