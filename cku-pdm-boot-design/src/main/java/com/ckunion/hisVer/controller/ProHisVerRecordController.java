package com.ckunion.hisVer.controller;

import java.util.ArrayList;
import java.util.List;

import com.ckunion.hisVer.entity.ProHisVerBom;
import com.ckunion.hisVer.service.IProHisVerBomService;
import com.ckunion.hisVer.vo.ProHisVerBomTree;
import org.jeecg.common.api.vo.Result;
import com.ckunion.hisVer.entity.ProHisVerRecord;
import com.ckunion.hisVer.service.IProHisVerRecordService;

import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: pro_his_ver_record
 * @Author: jeecg-boot
 * @Date:   2021-05-07
 * @Version: V1.0
 */
@Api(tags="pro_his_ver_record")
@RestController
@RequestMapping("/hisVer/proHisVerRecord")
@Slf4j
public class ProHisVerRecordController extends JeecgController<ProHisVerRecord, IProHisVerRecordService> {
	@Autowired
	private IProHisVerRecordService proHisVerRecordService;
	@Autowired
	private IProHisVerBomService proHisVerBomService;

	
	/**
	 * 分页列表查询

	 * @return
	 */
	@AutoLog(value = "pro_his_ver_record-分页列表查询")
	@ApiOperation(value="pro_his_ver_record-分页列表查询", notes="pro_his_ver_record-分页列表查询")
	@GetMapping(value = "/getHisVerListBy")
	public Result<?> getHisVerListBy(@RequestParam(name = "infoId", required = true) String infoId) {
		List<ProHisVerRecord> pageList = proHisVerRecordService.query().eq("info_id",infoId).list();
		return Result.OK(pageList);
	}


	 /**
	  * 获取历史版本结构树
	  *
	  * @return
	  */
	 @AutoLog(value = "获取历史版本结构树")
	 @ApiOperation(value="获取历史版本结构树", notes="获取历史版本结构树")
	 @GetMapping(value = "/getHisVerBomTree")
	 public Result<?> getHisVerBomTree(@RequestParam(name = "id", required = true) String id) {

		 log.info("获取历史版本结构树="+id);

		 ProHisVerRecord record = proHisVerRecordService.getById(id);
		 ProHisVerBom rootBom = proHisVerBomService.query().eq("masteroid",id).eq("material_name",record.getMaterialName()).isNull("parent_id").one();

		 List<ProHisVerBomTree> treeList = new ArrayList<>();
		 ProHisVerBomTree rootTree = new ProHisVerBomTree();
		 BeanUtils.copyProperties(rootBom, rootTree);

		 //rootTree.setMaterialName("<img src=\"temp/order_modify_approval_1619505330179.png\" />"+rootBom.getMaterialName());

		 List<ProHisVerBom> expanList = proHisVerBomService.query().eq("masteroid",id).eq("parent_id",rootBom.getId()).list();
		 if(null != expanList && expanList.size()>0){
			 initBomTree(id,expanList,rootTree);
		 }
		 treeList.add(rootTree);


		 return Result.OK(treeList);
	 }

	 //循环获获取下一层节点
	 private void initBomTree(String masteroid, List<ProHisVerBom> childList,ProHisVerBomTree bomTree){
		 List<ProHisVerBomTree> treeList = new ArrayList<ProHisVerBomTree>();
		 for(ProHisVerBom bom : childList){
			 ProHisVerBomTree tree1 = new ProHisVerBomTree();
			 BeanUtils.copyProperties(bom, tree1);

			 List<ProHisVerBom> childList2 = proHisVerBomService.query().eq("masteroid",masteroid).eq("parent_id",bom.getId()).list();
			 if(null != childList2 && childList2.size()>0){
				 initBomTree(masteroid,childList2,tree1);
			 }
			 treeList.add(tree1);
		 }
		 bomTree.setChildren(treeList);

	 }

}
