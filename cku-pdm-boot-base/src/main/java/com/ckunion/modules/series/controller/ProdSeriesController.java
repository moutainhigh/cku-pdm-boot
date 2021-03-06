package com.ckunion.modules.series.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ckunion.modules.materials.entity.BasMaterialInfo;
import com.ckunion.modules.materials.service.IBasMaterialInfoService;
import com.ckunion.modules.series.entity.ProdSeriesMaterial;
import com.ckunion.modules.series.service.IProdSeriesMaterialService;
import com.ckunion.productbom.entity.ProProductBom;
import com.ckunion.productbom.service.IProProductBomService;
import me.zhyd.oauth.utils.StringUtils;
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
import com.ckunion.modules.series.entity.ProdSeriesField;
import com.ckunion.modules.series.entity.ProdSeries;
import com.ckunion.modules.series.vo.ProdSeriesPage;
import com.ckunion.modules.series.service.IProdSeriesService;
import com.ckunion.modules.series.service.IProdSeriesFieldService;
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
 * @Description: prod_series
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
@Api(tags="prod_series")
@RestController
@RequestMapping("/series/prodSeries")
@Slf4j
public class ProdSeriesController {
	@Autowired
	private IProdSeriesService prodSeriesService;
	@Autowired
	private IBasMaterialInfoService basMaterialInfoService;
	@Autowired
	private IProdSeriesMaterialService prodSeriesMaterialService;
	@Autowired
	private IProdSeriesFieldService prodSeriesFieldService;
	@Autowired
	private IProProductBomService proProductBomService;
	
	/**
	 * ?????????????????????????????????
	 * @param prodSeries
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "prod_series-??????????????????")
	@ApiOperation(value="prod_series-??????????????????", notes="prod_series-??????????????????")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ProdSeries prodSeries,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ProdSeries> queryWrapper = QueryGenerator.initQueryWrapper(prodSeries, req.getParameterMap());

		//??????????????????
		queryWrapper.isNull("parentoid");

		Page<ProdSeries> page = new Page<ProdSeries>(pageNo, pageSize);
		IPage<ProdSeries> pageList = prodSeriesService.page(page, queryWrapper);
		return Result.OK(pageList);
	}


	 /**
	  * ?????????????????????????????????
	  * @param prodSeries
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "prod_series-??????????????????")
	 @ApiOperation(value="prod_series-??????????????????", notes="prod_series-??????????????????")
	 @GetMapping(value = "/branchList")
	 public Result<?> branchList(ProdSeries prodSeries,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {

	 	 if(StringUtils.isEmpty(prodSeries.getParentoid())){
			 prodSeries.setParentoid("-1");
		 }

		 QueryWrapper<ProdSeries> queryWrapper = QueryGenerator.initQueryWrapper(prodSeries, req.getParameterMap());
		 Page<ProdSeries> page = new Page<ProdSeries>(pageNo, pageSize);
		 IPage<ProdSeries> pageList = prodSeriesService.page(page, queryWrapper);
		 return Result.OK(pageList);
	 }



	 /**
	  * ??????????????????????????????????????????
	  * @param prodSeries
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "prod_series-??????????????????")
	 @ApiOperation(value="prod_series-??????????????????", notes="prod_series-??????????????????")
	 @GetMapping(value = "/listByManager")
	 public Result<?> listByManager(ProdSeries prodSeries,
								 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								 HttpServletRequest req) {

	 	 //?????????????????????
		 String username = JwtUtil.getUserNameByToken(req);
		 prodSeries.setManager(username);


		 QueryWrapper<ProdSeries> queryWrapper = QueryGenerator.initQueryWrapper(prodSeries, req.getParameterMap());
		 Page<ProdSeries> page = new Page<ProdSeries>(pageNo, pageSize);
		 IPage<ProdSeries> pageList = prodSeriesService.page(page, queryWrapper);
		 return Result.OK(pageList);
	 }




	 /**
	 *   ??????
	 *
	 * @param prodSeriesPage
	 * @return
	 */
	@AutoLog(value = "prod_series-??????")
	@ApiOperation(value="prod_series-??????", notes="prod_series-??????")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ProdSeriesPage prodSeriesPage) {
		ProdSeries prodSeries = new ProdSeries();
		BeanUtils.copyProperties(prodSeriesPage, prodSeries);
		prodSeriesService.saveMain(prodSeries, prodSeriesPage.getProdSeriesFieldList());
		return Result.OK("???????????????");
	}
	
	/**
	 *  ??????
	 *
	 * @param prodSeriesPage
	 * @return
	 */
	@AutoLog(value = "prod_series-??????")
	@ApiOperation(value="prod_series-??????", notes="prod_series-??????")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ProdSeriesPage prodSeriesPage) {
		ProdSeries prodSeries = new ProdSeries();
		BeanUtils.copyProperties(prodSeriesPage, prodSeries);
		ProdSeries prodSeriesEntity = prodSeriesService.getById(prodSeries.getId());
		if(prodSeriesEntity==null) {
			return Result.error("?????????????????????");
		}
		prodSeriesService.updateMain(prodSeries, prodSeriesPage.getProdSeriesFieldList());
		return Result.OK("????????????!");
	}

	 /**
	  *  ???????????????
	  * @param prodSeries
	  * @return
	  */
	 @AutoLog(value = "???????????????")
	 @ApiOperation(value="???????????????", notes="???????????????")
	 @PutMapping(value = "/saveManager")
	 public Result<?> saveManager(@RequestBody ProdSeries prodSeries) {

		 ProdSeries prodSeriesEntity = prodSeriesService.getById(prodSeries.getId());
		 if(prodSeriesEntity==null) {
			 return Result.error("?????????????????????");
		 }
		 prodSeriesEntity.setManager(prodSeries.getManager());
		 prodSeriesService.updateById(prodSeriesEntity);
		 return Result.OK("????????????!");
	 }
	
	/**
	 *   ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "prod_series-??????id??????")
	@ApiOperation(value="prod_series-??????id??????", notes="prod_series-??????id??????")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		prodSeriesService.delMain(id);
		return Result.OK("????????????!");
	}
	
	/**
	 *  ????????????
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "prod_series-????????????")
	@ApiOperation(value="prod_series-????????????", notes="prod_series-????????????")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.prodSeriesService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("?????????????????????");
	}
	
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "prod_series-??????id??????")
	@ApiOperation(value="prod_series-??????id??????", notes="prod_series-??????id??????")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ProdSeries prodSeries = prodSeriesService.getById(id);
		if(prodSeries==null) {
			return Result.error("?????????????????????");
		}
		return Result.OK(prodSeries);

	}

	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "prod_series_field????????????ID??????")
	@ApiOperation(value="prod_series_field??????ID??????", notes="prod_series_field-?????????ID??????")
	@GetMapping(value = "/queryProdSeriesFieldByMainId")
	public Result<?> queryProdSeriesFieldListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ProdSeriesField> prodSeriesFieldList = prodSeriesFieldService.selectByMainId(id);
		return Result.OK(prodSeriesFieldList);
	}



	 /**
	  * ???????????????????????????????????????
	  *
	  * @param request
	  * @return
	  */
	 @ApiOperation("???????????????????????????????????????")
	 @PostMapping(value = "/getFieldByModelId")
	 public Result getFieldByModelId(HttpServletRequest request) {

		 String seriesId = request.getParameter("seriesId");
		 String modelId = request.getParameter("modelId");

		 log.info("???????????????????????????????????????---??????seriesId="+seriesId+",modelId="+modelId);
		 String fieldStr = prodSeriesService.getFieldByModelId(seriesId, modelId);
		 if(null == fieldStr){
             return Result.OK("????????????????????????????????????");
         }else{
		    Object o = JSON.parseArray(fieldStr);
		    return Result.OK(o);
         }

	 }

	 /**
	  * ???????????????????????????
	  * @return
	  */
	 @AutoLog(value = "???????????????????????????")
	 @ApiOperation(value="???????????????????????????", notes="???????????????????????????")
	 @PostMapping(value = "/addSeriesAndBomRela")
	 public Result<?> addSeriesAndBomRela(@RequestParam(name = "branchSeriesId", required = true) String branchSeriesId,
										  @RequestParam(name = "bomId", required = true) String bomId) {
		 log.info("???????????????????????????branchSeriesId="+branchSeriesId+",bomId="+bomId);

		 ProdSeries ps = prodSeriesService.getById(branchSeriesId);
		 ps.setBomId(bomId);
		 ProProductBom bom = proProductBomService.getById(bomId);
		 ps.setBomName(bom.getMaterialName());
		 prodSeriesService.updateById(ps);
		 return Result.OK("???????????????");
	 }


	 /**
	  * ??????????????????????????????
	  * @return
	  */
	 @AutoLog(value = "??????????????????????????????")
	 @ApiOperation(value="??????????????????????????????", notes="??????????????????????????????")
	 @GetMapping(value = "/addProductBom")
	 public Result<?> addProductBom(@RequestParam(name = "id", required = true) String id) {
		 log.info("??????????????????????????????id="+id);
		 ProdSeries ps = prodSeriesService.getById(id);
		 prodSeriesService.addRootProductBom(ps);
		 return Result.OK("???????????????");
	 }



	 /**
	  * ?????????????????????????????????
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "prod_series-??????????????????")
	 @ApiOperation(value="prod_series-??????????????????", notes="prod_series-??????????????????")
	 @GetMapping(value = "/MaterialListBySeries")
	 public Result<?> MaterialListBySeries(@RequestParam(name="SERIE_ID") String SERIE_ID,
								 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								 HttpServletRequest req) {

		 QueryWrapper<BasMaterialInfo> queryWrapper = new QueryWrapper<>();

		 Page<BasMaterialInfo> page = new Page<BasMaterialInfo>(pageNo, pageSize);

		 IPage<BasMaterialInfo> pageList = prodSeriesService.getMaterialBySeries(page, queryWrapper);

		 return Result.OK(pageList);
	 }


	 /**
	  * @return
	  */
	 @GetMapping(value = "/addMaterialBySeries")
	 public Result<?> addMaterialBySeries(@RequestParam(name="seriesId") String seriesId,
										   @RequestParam(name="materials") String materials,
										   HttpServletRequest req) {


		 String[] materialarr = materials.split(",");
		 List<String> strings = Arrays.asList(materialarr);
		 ProdSeries prodSeries = prodSeriesService.query().eq("id", seriesId).one();

		 List<BasMaterialInfo>  basMaterialInfoList = basMaterialInfoService.query().in("id", strings).list();

		 List<ProdSeriesMaterial> insertlist=new ArrayList();
		 for (BasMaterialInfo basMaterialInfo : basMaterialInfoList) {
			 ProdSeriesMaterial prodSeriesMaterial=new ProdSeriesMaterial();
			 prodSeriesMaterial.setBomId(prodSeries.getBomId());
			 prodSeriesMaterial.setParentoid(prodSeries.getParentoid());
			 prodSeriesMaterial.setNominalFrequency(prodSeries.getNominalFrequency());
			 prodSeriesMaterial.setQuaLevel(prodSeries.getQuaLevel());
			 prodSeriesMaterial.setSerieId(prodSeries.getId());
			 prodSeriesMaterial.setMaterialId(basMaterialInfo.getId());
			 insertlist.add(prodSeriesMaterial);
		 }

		 boolean b = prodSeriesMaterialService.saveBatch(insertlist);
		 if(b){
			 return Result.OK("????????????");
		 }else{
			 return Result.error("????????????");
		 }


	 }



	 @GetMapping(value = "/delMaterialBySeries")
	 public Result<?> delMaterialBySeries(@RequestParam(name="seriesId") String seriesId,
										  @RequestParam(name="material") String material,
										  HttpServletRequest req) {


		 ProdSeriesMaterial one = prodSeriesMaterialService.query().eq("material_id", material).eq("serie_id", seriesId).one();

		 boolean b = prodSeriesMaterialService.removeById(one);

		 if(b){
			 return Result.OK("????????????");
		 }else{
			 return Result.error("????????????");
		 }


	 }


 }
