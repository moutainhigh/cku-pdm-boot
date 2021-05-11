package com.ckunion.applyno.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ckunion.constant.ProConstant;
import com.ckunion.productbom.entity.BomTree;
import com.ckunion.productbom.entity.ProProductBom;
import com.ckunion.productbom.entity.ProProductInfo;
import com.ckunion.productbom.entity.VwProProductBom;
import com.ckunion.productbom.service.IProProductBomService;
import com.ckunion.productbom.service.IProProductInfoService;
import com.ckunion.productbom.service.IVwProProductBomService;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.ckunion.applyno.entity.ProApplyMapnoDt;
import com.ckunion.applyno.entity.ProApplyMapno;
import com.ckunion.applyno.vo.ProApplyMapnoPage;
import com.ckunion.applyno.service.IProApplyMapnoService;
import com.ckunion.applyno.service.IProApplyMapnoDtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: pro_apply_mapno
 * @Author: jeecg-boot
 * @Date:   2021-04-23
 * @Version: V1.0
 */
@Api(tags="pro_apply_mapno")
@RestController
@RequestMapping("/applyno/proApplyMapno")
@Slf4j
public class ProApplyMapnoController {
	@Autowired
	private IProApplyMapnoService proApplyMapnoService;
	@Autowired
	private IProApplyMapnoDtService proApplyMapnoDtService;
	@Autowired
	private IVwProProductBomService vwProProductBomService;

	
	/**
	 * 分页列表查询
	 *
	 * @param proApplyMapno
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "pro_apply_mapno-分页列表查询")
	@ApiOperation(value="pro_apply_mapno-分页列表查询", notes="pro_apply_mapno-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ProApplyMapno proApplyMapno,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ProApplyMapno> queryWrapper = QueryGenerator.initQueryWrapper(proApplyMapno, req.getParameterMap());

		//查询自己请求的数据
		String username = JwtUtil.getUserNameByToken(req);
		queryWrapper.eq("create_by",username);
		queryWrapper.orderByDesc("create_time");

		Page<ProApplyMapno> page = new Page<ProApplyMapno>(pageNo, pageSize);
		IPage<ProApplyMapno> pageList = proApplyMapnoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 * @param proApplyMapnoPage
	 * @return
	 */
	@AutoLog(value = "pro_apply_mapno-添加")
	@ApiOperation(value="pro_apply_mapno-添加", notes="pro_apply_mapno-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ProApplyMapnoPage proApplyMapnoPage) {
		ProApplyMapno proApplyMapno = new ProApplyMapno();
		BeanUtils.copyProperties(proApplyMapnoPage, proApplyMapno);
		proApplyMapnoService.saveMain(proApplyMapno, proApplyMapnoPage.getProApplyMapnoDtList());
		return Result.OK("添加成功！");
	}


	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "pro_apply_mapno-通过id删除")
	@ApiOperation(value="pro_apply_mapno-通过id删除", notes="pro_apply_mapno-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		proApplyMapnoService.delMain(id);
		return Result.OK("删除成功!");
	}

	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "pro_apply_mapno-通过id查询")
	@ApiOperation(value="pro_apply_mapno-通过id查询", notes="pro_apply_mapno-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ProApplyMapno proApplyMapno = proApplyMapnoService.getById(id);
		if(proApplyMapno==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(proApplyMapno);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "pro_apply_mapno_dt通过主表ID查询")
	@ApiOperation(value="pro_apply_mapno_dt主表ID查询", notes="pro_apply_mapno_dt-通主表ID查询")
	@GetMapping(value = "/queryProApplyMapnoDtByMainId")
	public Result<?> queryProApplyMapnoDtListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ProApplyMapnoDt> proApplyMapnoDtList = proApplyMapnoDtService.selectByMainId(id);
		return Result.OK(proApplyMapnoDtList);
	}

	 /**
	  * 通过传递的选中节点，组装到图号集合中
	  *
	  * @param parentId
	  * @return
	  */
	 @AutoLog(value = "通过传递的选中节点，组装到图号集合中")
	 @ApiOperation(value="通过传递的选中节点，组装到图号集合中", notes="通过传递的选中节点，组装到图号集合中")
	 @GetMapping(value = "/queryBomNodeListByParent")
	 public Result<?> queryBomNodeListByParent(@RequestParam(name="parentId",required=true) String parentId) {
	 	log.info("通过传递的选中节点，组装到图号集合中"+parentId);
		String[] partTypes = {ProConstant.BOM_PART_TYPE_PART,ProConstant.BOM_PART_TYPE_DOC};
		List<VwProProductBom> bomList = vwProProductBomService.getAllVwBomListByParent(parentId,partTypes);

		List<ProApplyMapnoDt> applyDtList = new ArrayList<>();
	 	for(VwProProductBom bom : bomList){
			ProApplyMapnoDt dt = new ProApplyMapnoDt();
			dt.setNodeCode(bom.getMapNo());//如果有，图号请求那边就不需要再给值，没有就要给定图号值
			dt.setNodeName(bom.getMaterialName());
			dt.setPartType(bom.getPartType());
			dt.setBasTypeCode(bom.getBasTypeCode());
			dt.setBasTypeName(bom.getBasTypeName());
			dt.setBasTypeCode(bom.getBasTypeCode());
			dt.setChildoid(bom.getChildoid());
			dt.setParentoid(bom.getParentoid());
			applyDtList.add(dt);
		}

	 	return Result.OK(applyDtList);

	 }


	 /**
	  * 获取请求图号产品结构树
	  *
	  * @return
	  */
	 @AutoLog(value = "获取请求图号产品结构树")
	 @ApiOperation(value="获取请求图号产品结构树", notes="获取请求图号产品结构树")
	 @GetMapping(value = "/getApplyBomTree")
	 public Result<?> getApplyBomTree(@RequestParam(name = "masteroid", required = true) String masteroid,
									  @RequestParam(name = "infoId", required = true) String infoId) {

		 log.info("获取请求图号产品结构树masteroid="+masteroid+",infoId="+infoId);
		 List<BomTree> treeList = new ArrayList<BomTree>();

		 ProApplyMapno apply =  proApplyMapnoService.getById(masteroid);
		 BomTree rootTree = new BomTree();
		 rootTree.setKey(apply.getId());
		 rootTree.setTitle(apply.getNodeName());
		 rootTree.setLeaf(true);
		 List<ProApplyMapnoDt> dtList = proApplyMapnoDtService.getApplyMapnoDtByParentoid(masteroid,infoId);
		 if(null != dtList && dtList.size()>0){
			 rootTree.setLeaf(false);
			 initBomTree(dtList,rootTree);
		 }else{
			 rootTree.setChildren(null);
		 }
		 treeList.add(rootTree);

		 return Result.OK(treeList);
	 }


	 //循环获获取下一层节点
	 private void initBomTree(List<ProApplyMapnoDt> dtList,BomTree treeNode){
		 List<BomTree> treeList = new ArrayList<BomTree>();
		 for(ProApplyMapnoDt dt : dtList){
			 BomTree tree1 = new BomTree();
			 tree1.setKey(dt.getId());
			 tree1.setTitle(dt.getNodeName());
			 tree1.setLeaf(true);
			 List<ProApplyMapnoDt> dtList2 = proApplyMapnoDtService.getApplyMapnoDtByParentoid(dt.getMasteroid(),dt.getChildoid());
			 if(null != dtList2 && dtList2.size()>0){
				 tree1.setLeaf(false);
				 initBomTree(dtList2,tree1);
			 }else{
				 tree1.setChildren(null);
			 }
			 treeList.add(tree1);
		 }
		 treeNode.setChildren(treeList);

	 }



}
