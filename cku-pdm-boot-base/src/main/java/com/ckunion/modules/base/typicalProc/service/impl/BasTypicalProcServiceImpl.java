package com.ckunion.modules.base.typicalProc.service.impl;

import com.ckunion.activiti.service.ActServiceI;
import com.ckunion.activiti.vo.ActModel;
import com.ckunion.modules.base.typicalProc.entity.*;
import com.ckunion.modules.base.typicalProc.mapper.*;
import com.ckunion.modules.base.typicalProc.service.IBasTypicalProcService;
import com.ckunion.modules.constants.BasConstant;
import me.zhyd.oauth.utils.StringUtils;
import org.jeecg.modules.system.mapper.SysDictMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.*;
import java.util.Collection;

import lombok.extern.slf4j.Slf4j;
import com.ckunion.modules.utils.freemarker.handler.DocumentHandler;
import org.springframework.util.ResourceUtils;

/**
 * @Description: 典型工艺库
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Slf4j
@Service
public class BasTypicalProcServiceImpl extends ServiceImpl<BasTypicalProcMapper, BasTypicalProc>
		implements IBasTypicalProcService,ActServiceI {

	@Autowired
	private BasTypicalProcMapper basTypicalProcMapper;
	@Autowired
	private BasTypicalPurposeMapper basTypicalPurposeMapper;
	@Autowired
	private BasTypicalPurposeTargetMapper basTypicalPurposeTargetMapper;
	@Autowired
	private BasTypicalMatMapper basTypicalMatMapper;
	@Autowired
	private BasTypicalEquMapper basTypicalEquMapper;
	@Autowired
	private BasTypicalToolMapper basTypicalToolMapper;
	@Autowired
	private BasTypicalProcessMapper basTypicalProcessMapper;
	@Autowired
	private BasTypicalProcessCheckMapper basTypicalProcessCheckMapper;
	@Autowired
	private BasTypicalSurrMapper basTypicalSurrMapper;
	@Autowired
	private BasTypicalAttentMapper basTypicalAttentMapper;
	@Autowired
	private BasTypicalFileMapper basTypicalFileMapper;
	@Autowired
	private BasTypicalImgMapper basTypicalImgMapper;
	@Autowired
	private SysDictMapper sysDictMapper;
	
	@Override
	@Transactional
	public void saveMain(BasTypicalProc basTypicalProc, List<BasTypicalPurpose> basTypicalPurposeList,List<BasTypicalPurposeTarget> basTypicalPurposeTargetList,List<BasTypicalMat> basTypicalMatList,List<BasTypicalEqu> basTypicalEquList,List<BasTypicalTool> basTypicalToolList,List<BasTypicalProcess> basTypicalProcessList,List<BasTypicalSurr> basTypicalSurrList,List<BasTypicalAttent> basTypicalAttentList,List<BasTypicalFile> basTypicalFileList, List<BasTypicalImg> basTypicalImgList) {
		basTypicalProc.setProcState(BasConstant.TYPICALPROC_STATE_PREPA);
		basTypicalProc.setId(null);
		basTypicalProcMapper.insert(basTypicalProc);
		if(basTypicalPurposeList!=null && basTypicalPurposeList.size()>0) {
			for(BasTypicalPurpose entity:basTypicalPurposeList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalPurposeMapper.insert(entity);
			}
		}
		if(basTypicalPurposeTargetList!=null && basTypicalPurposeTargetList.size()>0) {
			for(BasTypicalPurposeTarget entity:basTypicalPurposeTargetList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalPurposeTargetMapper.insert(entity);
			}
		}
		if(basTypicalMatList!=null && basTypicalMatList.size()>0) {
			for(BasTypicalMat entity:basTypicalMatList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalMatMapper.insert(entity);
			}
		}
		if(basTypicalEquList!=null && basTypicalEquList.size()>0) {
			for(BasTypicalEqu entity:basTypicalEquList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalEquMapper.insert(entity);
			}
		}
		if(basTypicalToolList!=null && basTypicalToolList.size()>0) {
			for(BasTypicalTool entity:basTypicalToolList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalToolMapper.insert(entity);
			}
		}
		if(basTypicalProcessList!=null && basTypicalProcessList.size()>0) {
			for(BasTypicalProcess entity:basTypicalProcessList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				entity.setHavCheck("N");
				basTypicalProcessMapper.insert(entity);

				if(null != entity.getCheckList()) {
					List<BasTypicalProcessCheck> checkList = entity.getCheckList();
					if (null != checkList && checkList.size() > 0) {
						for (BasTypicalProcessCheck entity2 : checkList) {
							//外键设置
							entity2.setId(null);
							entity2.setMasteroid(basTypicalProc.getId());
							entity2.setProcessId(entity.getId());
							basTypicalProcessCheckMapper.insert(entity2);
						}
						entity.setHavCheck("Y");
						basTypicalProcessMapper.updateById(entity);
					}
				}

			}
		}

		if(basTypicalSurrList!=null && basTypicalSurrList.size()>0) {
			for(BasTypicalSurr entity:basTypicalSurrList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalSurrMapper.insert(entity);
			}
		}
		if(basTypicalAttentList!=null && basTypicalAttentList.size()>0) {
			for(BasTypicalAttent entity:basTypicalAttentList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalAttentMapper.insert(entity);
			}
		}
		if(basTypicalFileList!=null && basTypicalFileList.size()>0) {
			for(BasTypicalFile entity:basTypicalFileList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalFileMapper.insert(entity);
			}
		}
		if(basTypicalImgList!=null && basTypicalImgList.size()>0) {
			for(BasTypicalImg entity:basTypicalImgList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalImgMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(BasTypicalProc basTypicalProc,List<BasTypicalPurpose> basTypicalPurposeList,List<BasTypicalPurposeTarget> basTypicalPurposeTargetList,List<BasTypicalMat> basTypicalMatList,List<BasTypicalEqu> basTypicalEquList,List<BasTypicalTool> basTypicalToolList,List<BasTypicalProcess> basTypicalProcessList,List<BasTypicalSurr> basTypicalSurrList,List<BasTypicalAttent> basTypicalAttentList,List<BasTypicalFile> basTypicalFileList, List<BasTypicalImg> basTypicalImgList) {
		basTypicalProcMapper.updateById(basTypicalProc);
		
		//1.先删除子表数据
		basTypicalPurposeMapper.deleteByMainId(basTypicalProc.getId());
		basTypicalPurposeTargetMapper.deleteByMainId(basTypicalProc.getId());
		basTypicalMatMapper.deleteByMainId(basTypicalProc.getId());
		basTypicalEquMapper.deleteByMainId(basTypicalProc.getId());
		basTypicalToolMapper.deleteByMainId(basTypicalProc.getId());
		basTypicalProcessMapper.deleteByMainId(basTypicalProc.getId());
		basTypicalProcessCheckMapper.deleteByMainId(basTypicalProc.getId());
		basTypicalSurrMapper.deleteByMainId(basTypicalProc.getId());
		basTypicalAttentMapper.deleteByMainId(basTypicalProc.getId());
		basTypicalFileMapper.deleteByMainId(basTypicalProc.getId());
		basTypicalImgMapper.deleteByMainId(basTypicalProc.getId());
		
		//2.子表数据重新插入
		if(basTypicalPurposeList!=null && basTypicalPurposeList.size()>0) {
			for(BasTypicalPurpose entity:basTypicalPurposeList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalPurposeMapper.insert(entity);
			}
		}
		if(basTypicalPurposeTargetList!=null && basTypicalPurposeTargetList.size()>0) {
			for(BasTypicalPurposeTarget entity:basTypicalPurposeTargetList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalPurposeTargetMapper.insert(entity);
			}
		}
		if(basTypicalMatList!=null && basTypicalMatList.size()>0) {
			for(BasTypicalMat entity:basTypicalMatList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalMatMapper.insert(entity);
			}
		}
		if(basTypicalEquList!=null && basTypicalEquList.size()>0) {
			for(BasTypicalEqu entity:basTypicalEquList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalEquMapper.insert(entity);
			}
		}
		if(basTypicalToolList!=null && basTypicalToolList.size()>0) {
			for(BasTypicalTool entity:basTypicalToolList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalToolMapper.insert(entity);
			}
		}
		if(basTypicalProcessList!=null && basTypicalProcessList.size()>0) {
			for(BasTypicalProcess entity:basTypicalProcessList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				entity.setHavCheck("N");
				basTypicalProcessMapper.insert(entity);


				List<BasTypicalProcessCheck> checkList = entity.getCheckList();
				if(null !=checkList && checkList.size()>0) {
					for(BasTypicalProcessCheck entity2:checkList) {
						//外键设置
						entity2.setId(null);
						entity2.setMasteroid(basTypicalProc.getId());
						entity2.setProcessId(entity.getId());
						basTypicalProcessCheckMapper.insert(entity2);
					}
					entity.setHavCheck("Y");
					basTypicalProcessMapper.updateById(entity);
				}

			}
		}
		if(basTypicalSurrList!=null && basTypicalSurrList.size()>0) {
			for(BasTypicalSurr entity:basTypicalSurrList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalSurrMapper.insert(entity);
			}
		}
		if(basTypicalAttentList!=null && basTypicalAttentList.size()>0) {
			for(BasTypicalAttent entity:basTypicalAttentList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalAttentMapper.insert(entity);
			}
		}
		if(basTypicalFileList!=null && basTypicalFileList.size()>0) {
			for(BasTypicalFile entity:basTypicalFileList) {
				//外键设置
				entity.setId(null);
				entity.setMasteroid(basTypicalProc.getId());
				basTypicalFileMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		basTypicalPurposeMapper.deleteByMainId(id);
		basTypicalPurposeTargetMapper.deleteByMainId(id);
		basTypicalMatMapper.deleteByMainId(id);
		basTypicalEquMapper.deleteByMainId(id);
		basTypicalToolMapper.deleteByMainId(id);
		basTypicalProcessMapper.deleteByMainId(id);
		basTypicalProcessCheckMapper.deleteByMainId(id);
		basTypicalSurrMapper.deleteByMainId(id);
		basTypicalAttentMapper.deleteByMainId(id);
		basTypicalFileMapper.deleteByMainId(id);
		basTypicalImgMapper.deleteByMainId(id);
		basTypicalProcMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			basTypicalPurposeMapper.deleteByMainId(id.toString());
			basTypicalPurposeTargetMapper.deleteByMainId(id.toString());
			basTypicalMatMapper.deleteByMainId(id.toString());
			basTypicalEquMapper.deleteByMainId(id.toString());
			basTypicalToolMapper.deleteByMainId(id.toString());
			basTypicalProcessMapper.deleteByMainId(id.toString());
			basTypicalProcessCheckMapper.deleteByMainId(id.toString());
			basTypicalSurrMapper.deleteByMainId(id.toString());
			basTypicalAttentMapper.deleteByMainId(id.toString());
			basTypicalFileMapper.deleteByMainId(id.toString());
			basTypicalImgMapper.deleteByMainId(id.toString());
			basTypicalProcMapper.deleteById(id);
		}
	}


	@Override
	public Map<String, Object> postCompleteTask(ActModel actModel) {
		log.info("典型工艺审批postCompleteTask=" +actModel.getNodeId());
		String wfkey = actModel.getWfkey();
		String nodeId=actModel.getNodeId();
		String businessKey=actModel.getBusinessKey();
		HashMap<String, Object> map=new HashMap<String, Object>();
		BasTypicalProc proc = this.getById(businessKey);
		proc.setProcessinsid(actModel.getProcessInstanceId());
		this.updateById(proc);
		map.put("wf_title", "典型工艺审批：工序名称="+proc.getWorkprocName());
		return map;

	}

	@Override
	public Boolean afterCompleteTask(ActModel actModel) {
		log.info("典型工艺审批afterCompleteTask=" +actModel.getBusinessKey());
		log.info("getNodeId=" +actModel.getNodeId());
		BasTypicalProc proc = this.getById(actModel.getBusinessKey());
		String nodeId=actModel.getNodeId();
		String result=actModel.getActResult();
		if("-1".equals(actModel.getNodeId())){//开始节点发起审批
			proc.setProcessinsid(actModel.getProcessInstanceId());
			proc.setProcState(BasConstant.TYPICALPROC_STATE_APPR);
			this.updateById(proc);
			return true;
		}else if("dept_leader".equals(nodeId)){//领导审批
			if("Y".equals(result)){
				proc.setProcState(BasConstant.TYPICALPROC_STATE_FIN);
				//版本号累加
				String verCode = proc.getProcVersion();
				if(StringUtils.isEmpty(verCode)){
					verCode = "A.1";
				}else{
					String[] strs = verCode.split(".");
					int code = Integer.parseInt(strs[1])+1;
					verCode = "A."+code;
				}
				proc.setProcVersion(verCode);
			}else {
				proc.setProcState(BasConstant.TYPICALPROC_STATE_REJECT);
			}
			this.updateById(proc);
		}
		return true;

	}

	@Override
	public void postEndProcess(ActModel actModel) {
		log.info("典型工艺审批终止=" +actModel.getBusinessKey());
		String businessKey = actModel.getBusinessKey();
		BasTypicalProc proc = this.getById(businessKey);
		proc.setProcState(BasConstant.TYPICALPROC_STATE_END);//标记审批终止
		this.updateById(proc);
	}

	@Override
	public String getUsers(Map<String, Object> map) {
		log.info("典型工艺审批获取用户=" +map.toString());
		return null;
	}

    @Override
	public String printTypicalProc(BasTypicalProc proc) {
		try {
			// 封装要传入到freemarker模板的参数信息，用于动态生成word
			Map<String, Object> dataMap = new HashMap<String, Object>();

			/************************** 开始组装freemarker传入参数 ******************************************/
			dataMap.put("mapNo", DocumentHandler.formatValue(proc.getMapNo()));
			dataMap.put("procName", DocumentHandler.formatValue(proc.getProcName()));

			if(StringUtils.isEmpty(proc.getQuaLevel())){
				dataMap.put("quaLevel", DocumentHandler.formatValue(""));//质量等级
			}else{
				String itemText = sysDictMapper.queryDictTextByKey("quaLevel",proc.getQuaLevel());
				dataMap.put("quaLevel", DocumentHandler.formatValue(itemText));//质量等级
			}
			dataMap.put("workprocName", DocumentHandler.formatValue(proc.getWorkprocName()));

			//目的和目标
			List<BasTypicalPurpose> pose = this.basTypicalPurposeMapper.selectByMainId(proc.getId());
			if(null != pose && pose.size()>0){
				dataMap.put("purpose", DocumentHandler.formatValue(pose.get(0).getPurpose()));
				dataMap.put("target", DocumentHandler.formatValue(pose.get(0).getTarget()));
			}else{
				dataMap.put("purpose", DocumentHandler.formatValue(""));
				dataMap.put("target", DocumentHandler.formatValue(""));
			}


			//注意事项
			List<BasTypicalAttent> attention = this.basTypicalAttentMapper.selectByMainId(proc.getId());
			if(null != attention && attention.size()>0){
				dataMap.put("attention", DocumentHandler.formatValue(attention.get(0).getAttention()));
			}else{
				dataMap.put("attention", DocumentHandler.formatValue(""));
			}

			List<BasTypicalPurposeTarget> targetList = new ArrayList<BasTypicalPurposeTarget>();
			List<BasTypicalMat> matList = new ArrayList<BasTypicalMat>();
			List<BasTypicalEqu> equList = new ArrayList<BasTypicalEqu>();
			List<BasTypicalTool> toolList = new ArrayList<BasTypicalTool>();
			List<BasTypicalProcess> processList = new ArrayList<BasTypicalProcess>();
			List<BasTypicalSurr> surrList = new ArrayList<BasTypicalSurr>();
			List<BasTypicalFile> fileList = new ArrayList<BasTypicalFile>();
			List<BasTypicalImg> imgList = new ArrayList<BasTypicalImg>();


            targetList = this.basTypicalPurposeTargetMapper.selectByMainId(proc.getId());
            matList = this.basTypicalMatMapper.selectByMainId(proc.getId());
            equList = this.basTypicalEquMapper.selectByMainId(proc.getId());
            toolList = this.basTypicalToolMapper.selectByMainId(proc.getId());
            surrList = this.basTypicalSurrMapper.selectByMainId(proc.getId());
            fileList = this.basTypicalFileMapper.selectByMainId(proc.getId());


            dataMap.put("targetList",  targetList);//目标从从表集合
            dataMap.put("matList",  matList);//物资集合
            dataMap.put("equList",  equList);//设备集合
            dataMap.put("toolList",  toolList);//工装集合
            dataMap.put("surrList",  surrList);//工作环境集合
            dataMap.put("fileList",  fileList);//引用文件集合


			//处理操作过程集合与检验方法嵌套
			processList = this.basTypicalProcessMapper.selectByMainId(proc.getId());
			for(BasTypicalProcess process : processList){
				if(StringUtils.isEmpty(process.getHavCheck())){
					//这种是错误数据，跳过
					process.setHavCheck("N");
				}else {
					List<BasTypicalProcessCheck> checkList = this.basTypicalProcessCheckMapper.selectByProcessId(proc.getId());
					process.setCheckList(checkList);
				}
			}
			dataMap.put("processList",  processList);//操作过程集合



			//图片需要的属性
			//imgName("大幅度是");
			//imgId("rId101");
			//imgTar("image101.png");
			//imgData(base64Img2);
			//imgStr("图号2：代表灌灌灌灌");
			//docViewWidth("3543300");
			//docViewHeight("1731543");

			List<BasTypicalImg> imgTempList = this.basTypicalImgMapper.selectByMainId(proc.getId());
			int indexNo = 100;//序号定义高一些，免得跟word中冲突
			for(BasTypicalImg img : imgTempList){
				if(StringUtils.isEmpty(img.getImgSrc())){
					continue;
				}

				BasTypicalImg img2 = new BasTypicalImg();
				img2.setImgName("图片"+indexNo);
				img2.setImgId("rId"+indexNo);
				img2.setImgTar("image"+indexNo+".png");
				img2.setDocViewWidth("3543300");
				img2.setDocViewHeight("1731543");

				if(StringUtils.isEmpty(img.getImgStr())){
					img2.setImgStr("");
				}else{
					img2.setImgStr(img.getImgStr());
				}

				String imgUrl = "D:\\pdf-file\\uploads\\"+img.getImgSrc();
				String base64Img = DocumentHandler.getImageBase(imgUrl);
				img2.setImgData(base64Img);

				imgList.add(img2);
				indexNo++;
			}
			dataMap.put("imgList",  imgList);//图片集合


			/*
			File pathFile = new File(ResourceUtils.getURL("classpath:").getPath());
			//D:\mes\mes-707\PDM2\707PDM\cku-pdm-boot\cku-pdm-module-start\target\classes
			File fileUpload = new File(pathFile.getAbsolutePath(),"processes");
			//D:\mes\mes-707\PDM2\707PDM\cku-pdm-boot\cku-pdm-module-start\target\classes\processes
			String activitepath= fileUpload.getAbsolutePath();
			//D:\mes\mes-707\PDM2\707PDM\cku-pdm-boot\cku-pdm-module-start\target\classes\processes
			String filePath = activitepath + File.separator + "aaa.jpg";
			//D:\mes\mes-707\PDM2\707PDM\cku-pdm-boot\cku-pdm-module-start\target\classes\processes\aaa.jpg
			*/


			// 调用封装好的方法，创建文件夹目录
			String file_path = DocumentHandler.buildFile("basTypicalProc",String.valueOf(System.currentTimeMillis()), String.valueOf(System.currentTimeMillis()));
			// 定义即将要生成的word文件名
			file_path = file_path + File.separatorChar + proc.getWorkprocCode() + ".doc";
			// 核心，调用生成word方法，将模板名、绑定的动态参数值、要存放的文件路径传入
			DocumentHandler.createDoc("typicalProc-V4", dataMap, file_path);
			return file_path;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
}
