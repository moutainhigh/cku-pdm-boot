package com.ckunion.productbom.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ckunion.constant.ProConstant;
import com.ckunion.productbom.entity.*;
import com.ckunion.productbom.service.IProProductInfoService;
import com.ckunion.productbom.service.IVwProProductBomService;
import com.ckunion.productbom.vo.BasFilePage;
import com.ckunion.productbom.vo.BasMaterialInfoPage;
import com.ckunion.productbom.vo.ProInfoBomPage;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.ckunion.productbom.service.IProProductBomService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import java.util.Date;

 /**
 * @Description: pro_product_bom
 * @Author: jeecg-boot
 * @Date:   2021-04-13
 * @Version: V1.0
 */
@Api(tags="pro_product_bom")
@RestController
@RequestMapping("/productbom/proProductBom")
@Slf4j
public class ProProductBomController extends JeecgController<ProProductBom, IProProductBomService> {
	@Autowired
	private IProProductBomService proProductBomService;
	@Autowired
	private IProProductInfoService proProductInfoService;
	@Autowired
	private IVwProProductBomService vwProProductBomService;


	 /**
	  * 根据parentid 查询 part/mat/doc集合
	  * @return
	  */
	 @AutoLog(value = "根据parentid 查询 doc集合")
	 @ApiOperation(value="根据parentid 查询 doc集合", notes="根据parentid 查询 doc集合")
	 @GetMapping(value = "/queryInfoListByParentId")
	 public Result<?> queryInfoListByParentId(@RequestParam(name = "parentId", required = true) String parentId,
											  @RequestParam(name = "partType", required = true) String partType) {
         String[] partTypes = {partType};
		 List<VwProProductBom> docList = vwProProductBomService.getAllVwBomListByParent(parentId,partTypes);
		 return Result.OK(docList);
	 }

	
	/**
	 *   添加 part 和 doc
	 * @param infoBomPage
	 * @return
	 */
	@AutoLog(value = "pro_product_bom-添加")
	@ApiOperation(value="pro_product_bom-添加", notes="pro_product_bom-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ProInfoBomPage infoBomPage) {
		if(StringUtils.isEmpty(infoBomPage.getParentoid())){
			return Result.error("添加失败，没有找到父节点");
		}
		if(StringUtils.isEmpty(infoBomPage.getPartType())){
			return Result.error("添加失败，未知的节点类型");
		}

		ProProductInfo info = new ProProductInfo();
		BeanUtils.copyProperties(infoBomPage, info);

		ProProductBom bom = new ProProductBom();
		BeanUtils.copyProperties(infoBomPage, bom);

		proProductInfoService.saveProductInfo(info,bom);
		return Result.OK("添加成功！");
	}


	 /**
	  *  选择文件库添加文档节点和bom关系
	  * @param basFilePage
	  * @return
	  */
	 @AutoLog(value = "根据文件库添加文档节点")
	 @ApiOperation(value="根据文件库添加文档节点", notes="根据文件库添加文档节点")
	 @PostMapping(value = "/addDocByBasFile")
	 public Result<?> addDocByBasFile(@RequestBody BasFilePage basFilePage) {
	 	if(StringUtils.isEmpty(basFilePage.getParentoid())){
			return Result.error("添加失败，缺少所属产品节点!");
		}
		 if(StringUtils.isEmpty(basFilePage.getFileName())){
			 return Result.error("添加失败，文件名称不能为空!");
		 }


		 proProductInfoService.saveProductInfoByBasFile(basFilePage);
		 return Result.OK("添加成功！");
	 }

	 /**
	  *  选择原材料基础信息 添加物料基础信息和bom关系
	  * @param basMatPage
	  * @return
	  */
	 @AutoLog(value = "根据文件库添加文档节点")
	 @ApiOperation(value="根据文件库添加文档节点", notes="根据文件库添加文档节点")
	 @PostMapping(value = "/addMatByBasMat")
	 public Result<?> addMatByBasMat(@RequestBody BasMaterialInfoPage basMatPage) {
		 if(StringUtils.isEmpty(basMatPage.getParentoid())){
			 return Result.error("添加失败，缺少所属产品节点!");
		 }
		 if(StringUtils.isEmpty(basMatPage.getMaterialName())){
			 return Result.error("添加失败，物料名称不能为空!");
		 }


		 proProductInfoService.saveProductInfoByBasMat(basMatPage);
		 return Result.OK("添加成功！");
	 }

	
	/**
	 *  编辑
	 * @param vwBom
	 * @return
	 */
	@AutoLog(value = "pro_product_bom-编辑")
	@ApiOperation(value="pro_product_bom-编辑", notes="pro_product_bom-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VwProProductBom vwBom) {

		ProProductBom bom = new ProProductBom();
		BeanUtils.copyProperties(vwBom, bom);
		ProProductBom tempBom = proProductBomService.getById(bom.getId());
		if(tempBom==null) {
			return Result.error("未找到对应数据");
		}

		ProProductInfo info = proProductInfoService.getById(vwBom.getChildoid());
		info.setMaterialName(vwBom.getMaterialName());
		info.setMapNo(vwBom.getMapNo());
		info.setMaterialCode(vwBom.getMapNo());
		proProductInfoService.updateProductInfo(info,bom);

		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "pro_product_bom-通过id删除")
	@ApiOperation(value="pro_product_bom-通过id删除", notes="pro_product_bom-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		proProductInfoService.delProductInfo(id);
		return Result.OK("删除成功!");
	}


	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "pro_product_bom-通过id查询")
	@ApiOperation(value="pro_product_bom-通过id查询", notes="pro_product_bom-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VwProProductBom vwBom = vwProProductBomService.getById(id);
		if(vwBom==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(vwBom);
	}





	//////////////////////////////////////////树操作相关//////////////////////////////////////////////

	 //字符串空处理
	 private String formatValue(String mapNo, String ver, String materialName, BigDecimal perQty, String typeName){
		if(StringUtils.isEmpty(mapNo)){
			mapNo = "";
		}
		if(StringUtils.isEmpty(ver)){
			ver = "";
		}
		if(StringUtils.isEmpty(materialName)){
			materialName = "";
		}
		if(null == perQty){
			perQty = new BigDecimal("0");
		}
		 if(StringUtils.isEmpty(typeName)){
			 typeName = "";
		 }

		return mapNo+"/"+ver+"-"+materialName+"*"+perQty+"["+typeName+"]";

	 }

     /**
      * 获取所有的产品结构树根节点（异步加载树方式）
      *
      * @return
      */
     @AutoLog(value = "获取所有的产品结构树根节点")
     @ApiOperation(value="获取所有的产品结构树根节点", notes="获取所有的产品结构树根节点")
     @GetMapping(value = "/getAllRootBomTree")
     public Result<?> getAllRootBomTree() {

         Map<String,Object> map = new HashMap<String,Object>();
         map.put("is_root","Y");
         List<ProProductBom> bomList = proProductBomService.listByMap(map);

         List<BomTree> treeList = new ArrayList<BomTree>();
         for(ProProductBom bom : bomList){
             BomTree rootTree = new BomTree();
             rootTree.setKey(bom.getId());
			 rootTree.setMaterialName(bom.getMaterialName());
			 rootTree.setInfoId(bom.getChildoid());
             rootTree.setMapNo(null == bom.getMapNo() ? "" :bom.getMapNo());

             ProProductInfo info = proProductInfoService.getById(bom.getChildoid());
             rootTree.setSeriesId(info.getSeriesId());
			 rootTree.setVersionNum(info.getVersionNum());
			 rootTree.setPartType(info.getPartType());
			 rootTree.setStatus(info.getStatus());
			 rootTree.setSignInoff(info.getSignInoff());
			 rootTree.setSignOffUser(info.getSignOffUser());
			 rootTree.setSlots(new BomTreeSlots(info.getSeriesId(),info.getPartType()));

			 rootTree.setTitle(bom.getMapNo()+"/"+bom.getMaterialName());

			 rootTree.setLeaf(true);
			 List<ProProductBom> childList = proProductBomService.getChildBomByParent(bom.getChildoid(),new String[]{ProConstant.BOM_PART_TYPE_PART});
			 if(null != childList && childList.size()>0){
				 rootTree.setLeaf(false);
			 }
             treeList.add(rootTree);
         }
         return Result.OK(treeList);
     }

	 /**
	  * 获取指定节点产品结构树
	  *
	  * @return
	  */
	 @AutoLog(value = "获取指定节点产品结构树")
	 @ApiOperation(value="获取指定节点产品结构树", notes="获取指定节点产品结构树")
	 @GetMapping(value = "/getBomTreeByParentId")
	 public Result<?> getBomTreeByParentId(@RequestParam(name = "parentId", required = true) String parentId,
                                           @RequestParam(name = "expandAll", required = false) String expandAll,
										   @RequestParam(name = "partType", required = false) String partType) {

		log.info("获取指定节点产品结构树="+parentId);
		String[] ptKeys;
		if(StringUtils.isNotEmpty(partType)){
			if(partType.indexOf(",")!=-1){
				String[] types = partType.split(",");
				ptKeys = new String[3];
				ptKeys[0] = ProConstant.BOM_PART_TYPE_PART;
				ptKeys[1] = types[0];
				ptKeys[2] = types[1];
			}else{
				ptKeys = new String[2];
				ptKeys[0] = ProConstant.BOM_PART_TYPE_PART;
				ptKeys[1] = partType;
			}
		}else{
			ptKeys = new String[1];
			ptKeys[0] = ProConstant.BOM_PART_TYPE_PART;
		}
		List<ProProductBom> childList = proProductBomService.getChildBomByParent(parentId,ptKeys);

         List<BomTree> treeList = new ArrayList<BomTree>();
         for(ProProductBom bom : childList) {
             BomTree tree1 = new BomTree();
             tree1.setKey(bom.getId());
			 tree1.setMaterialName(bom.getMaterialName());
			 tree1.setInfoId(bom.getChildoid());
             tree1.setMapNo(null == bom.getMapNo() ? "" :bom.getMapNo());

             ProProductInfo info = proProductInfoService.getById(bom.getChildoid());
             tree1.setSeriesId(info.getSeriesId());
			 tree1.setVersionNum(info.getVersionNum());
			 tree1.setPartType(info.getPartType());
			 tree1.setStatus(info.getStatus());
			 tree1.setSignInoff(info.getSignInoff());
			 tree1.setSignOffUser(info.getSignOffUser());
			 tree1.setSlots(new BomTreeSlots(info.getSeriesId(),info.getPartType()));
			 tree1.setTitle(formatValue(bom.getMapNo(),info.getVersionNum(),bom.getMaterialName(),bom.getPerQty(),info.getBasTypeName()));

			 tree1.setLeaf(true);
			 if("Y".equals(expandAll)){
				 //全部展开
				 List<ProProductBom> expanList = proProductBomService.getChildBomByParent(bom.getChildoid(),ptKeys);
				 if(null != expanList && expanList.size()>0){
					 tree1.setLeaf(false);
					 initBomTree(expanList,tree1,ptKeys);
				 }
			 }else{
				 //不是全部展开，只展示一层
				 List<ProProductBom> childList2 = proProductBomService.getChildBomByParent(bom.getChildoid(),ptKeys);
				 if(null != childList2 && childList2.size()>0){
					 tree1.setLeaf(false);
				 }
			 }

			 treeList.add(tree1);
         }

	 	return Result.OK(treeList);
	 }

	 //循环获获取下一层节点
	 private void initBomTree(List<ProProductBom> childList,BomTree treeNode, String[] ptKeys){
		 List<BomTree> treeList = new ArrayList<BomTree>();
		 for(ProProductBom bom : childList){
			 BomTree tree1 = new BomTree();
			 tree1.setKey(bom.getId());
			 tree1.setMaterialName(bom.getMaterialName());
			 tree1.setInfoId(bom.getChildoid());
			 tree1.setMapNo(null == bom.getMapNo() ? "" :bom.getMapNo());

             ProProductInfo info = proProductInfoService.getById(bom.getChildoid());
             tree1.setSeriesId(info.getSeriesId());
			 tree1.setVersionNum(info.getVersionNum());
			 tree1.setPartType(info.getPartType());
			 tree1.setStatus(info.getStatus());
			 tree1.setSignInoff(info.getSignInoff());
			 tree1.setSignOffUser(info.getSignOffUser());
			 tree1.setSlots(new BomTreeSlots(info.getSeriesId(),info.getPartType()));
			 tree1.setTitle(formatValue(bom.getMapNo(),info.getVersionNum(),bom.getMaterialName(),bom.getPerQty(),info.getBasTypeName()));

			 tree1.setLeaf(true);
			 List<ProProductBom> childList2 = proProductBomService.getChildBomByParent(bom.getChildoid(),ptKeys);
			 if(null != childList2 && childList2.size()>0){
				 tree1.setLeaf(false);
				 initBomTree(childList2,tree1,ptKeys);
			 }
			 treeList.add(tree1);
		 }
		 treeNode.setChildren(treeList);

	 }


	 /**
	  * 节点搜索功能方法,根据关键字模糊搜索相关节点
	  * @param keyWord
	  * @return
	  */
	 @RequestMapping(value = "/searchBy", method = RequestMethod.GET)
	 public Result<?> searchBy(@RequestParam(name = "keyWord", required = true) String keyWord) {

		 LambdaQueryWrapper<ProProductBom> query = new LambdaQueryWrapper<ProProductBom>();
		 query.like(ProProductBom::getMaterialName, keyWord);
		 List<ProProductBom> bomList = proProductBomService.list(query);

		 List<BomTree> treeList = new ArrayList<BomTree>();
		 for(ProProductBom bom : bomList){
			 BomTree rootTree = new BomTree();
			 rootTree.setKey(bom.getId());
			 rootTree.setMaterialName(bom.getMaterialName());
			 rootTree.setInfoId(bom.getChildoid());
			 rootTree.setMapNo(null == bom.getMapNo() ? "" :bom.getMapNo());
			 rootTree.setLeaf(true);

             ProProductInfo info = proProductInfoService.getById(bom.getChildoid());
             rootTree.setSeriesId(info.getSeriesId());
			 rootTree.setVersionNum(info.getVersionNum());
			 rootTree.setPartType(info.getPartType());
			 rootTree.setStatus(info.getStatus());
			 rootTree.setSignInoff(info.getSignInoff());
			 rootTree.setSignOffUser(info.getSignOffUser());
			 rootTree.setSlots(new BomTreeSlots(info.getSeriesId(),info.getPartType()));
			 rootTree.setTitle(formatValue(bom.getMapNo(),info.getVersionNum(),bom.getMaterialName(),bom.getPerQty(),info.getBasTypeName()));
			 treeList.add(rootTree);
		 }
		 return Result.OK(treeList);

	 }


	 /**
	  * BOM粘贴
	  * @param toId 粘贴到哪儿
	  * @param pasteId 粘贴什么
	  * @param pasteType 粘贴类型0本身1本身及子节点
	  * @return
	  */
	 @AutoLog(value = "BOM粘贴")
	 @ApiOperation(value="BOM粘贴", notes="BOM粘贴")
	 @PostMapping(value = "/addBomByPaste")
	 public Result<?> addBomByPaste(@RequestParam(name = "toId", required = true) String toId,
									@RequestParam(name = "pasteId", required = true) String pasteId,
									@RequestParam(name = "pasteType", required = true) int pasteType) {
		 log.info("BOM粘贴toId="+toId+",pasteId="+pasteId+",pasteType="+pasteType);
		 String mess = proProductInfoService.saveBomByPaste(toId,pasteId,String.valueOf(pasteType));
		 if(StringUtils.isNotEmpty(mess)){
			 return Result.error(mess);
		 }
		 return Result.OK("粘贴成功！");

	 }


	 /**
	  * BOM签入签出
	  * @return
	  */
	 @AutoLog(value = "BOM签入签出")
	 @ApiOperation(value="BOM签入签出", notes="BOM签入签出")
	 @PostMapping(value = "/changeBomSignInoff")
	 public Result<?> changeBomSignInoff(@RequestParam(name = "bomId", required = true) String bomId,
										 @RequestParam(name = "inoffFlag", required = true) String inoffFlag) {
		 log.info("BOM签入签出bomId="+bomId+",inoffFlag="+inoffFlag);
         String messStr = proProductInfoService.changeInfoSignInoff(bomId,inoffFlag);

         if(StringUtils.isNotEmpty(messStr)){
             return Result.error(messStr);
         }

		 if("IN".equals(inoffFlag)){
			 return Result.OK("签入成功！");
		 }else{
			 return Result.OK("签出成功！");
		 }

	 }


	 /**
	  * BOM节点归档（模拟归档，因为审批还没弄）
	  * @return
	  */
	 @AutoLog(value = "BOM节点归档")
	 @ApiOperation(value="BOM节点归档", notes="BOM节点归档")
	 @GetMapping(value = "/bomArchived")
	 public Result<?> bomArchived(@RequestParam(name = "id", required = true) String id) {
		 log.info("BOM节点归档infoId="+id);
		 VwProProductBom vwBom = vwProProductBomService.getById(id);
		 String messStr = proProductInfoService.saveBomArchived(vwBom.getChildoid());
		 if(StringUtils.isNotEmpty(messStr)){
			 return Result.error(messStr);
		 }else{
			 return Result.OK("归档成功！");
		 }

	 }


	 /**
	  * @return
	  */
	 @AutoLog(value = "BOM节点归档")
	 @ApiOperation(value="BOM节点归档", notes="BOM节点归档")
	 @GetMapping(value = "/saveBomQty")
	 public Result<?> saveBomQty(@RequestParam(name = "id", required = true) String id,
								 @RequestParam(name = "qty", required = true) String qty,
								 @RequestParam(name = "is_selected", required = true) String is_selected) {
		 ProProductBom byId = proProductBomService.getById(id);
		 if (byId == null) {
			 return Result.OK("节点为空");
		 }
		 byId.setPerQty(new BigDecimal(qty));
		 byId.setIsSelected(Integer.parseInt(is_selected));
		 proProductBomService.updateById(byId);

		 return Result.OK("编辑成功");
	 }


}
