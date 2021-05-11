package com.ckunion.ecn.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.ckunion.ecn.entity.ProEcnRecord;
import com.ckunion.ecn.service.IProEcnRecordService;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: pro_ecn_record
 * @Author: jeecg-boot
 * @Date:   2021-04-16
 * @Version: V1.0
 */
@Api(tags="pro_ecn_record")
@RestController
@RequestMapping("/ecn/proEcnRecord")
@Slf4j
public class ProEcnRecordController extends JeecgController<ProEcnRecord, IProEcnRecordService> {
	@Autowired
	private IProEcnRecordService proEcnRecordService;
	
	/**
	 * 分页列表查询
	 *
	 * @param proEcnRecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "pro_ecn_record-分页列表查询")
	@ApiOperation(value="pro_ecn_record-分页列表查询", notes="pro_ecn_record-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ProEcnRecord proEcnRecord,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ProEcnRecord> queryWrapper = QueryGenerator.initQueryWrapper(proEcnRecord, req.getParameterMap());
		//queryWrapper.inSql("info_id", "select childoid from pro_product_bom start with CHILDOID ='1' connect by prior CHILDOID=PARENTOID");
		queryWrapper.orderByDesc("create_time");


		Page<ProEcnRecord> page = new Page<ProEcnRecord>(pageNo, pageSize);
		IPage<ProEcnRecord> pageList = proEcnRecordService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param proEcnRecord
	 * @return
	 */
	@AutoLog(value = "pro_ecn_record-添加")
	@ApiOperation(value="pro_ecn_record-添加", notes="pro_ecn_record-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ProEcnRecord proEcnRecord) {
		if(StringUtils.isEmpty(proEcnRecord.getBefNumber()) || StringUtils.isEmpty(proEcnRecord.getBefVer())){
			return Result.error("变更编号或版本不能空！");
		}
		if(StringUtils.isEmpty(proEcnRecord.getInfoId())){
			return Result.error("没有指定节点！");
		}
		proEcnRecordService.saveProEcnRecord(proEcnRecord);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param proEcnRecord
	 * @return
	 */
	@AutoLog(value = "pro_ecn_record-编辑")
	@ApiOperation(value="pro_ecn_record-编辑", notes="pro_ecn_record-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ProEcnRecord proEcnRecord) {
		proEcnRecordService.updateById(proEcnRecord);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "pro_ecn_record-通过id删除")
	@ApiOperation(value="pro_ecn_record-通过id删除", notes="pro_ecn_record-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		proEcnRecordService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "pro_ecn_record-批量删除")
	@ApiOperation(value="pro_ecn_record-批量删除", notes="pro_ecn_record-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.proEcnRecordService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "pro_ecn_record-通过id查询")
	@ApiOperation(value="pro_ecn_record-通过id查询", notes="pro_ecn_record-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ProEcnRecord proEcnRecord = proEcnRecordService.getById(id);
		if(proEcnRecord==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(proEcnRecord);
	}


	 /**
	  * ECN完成（模拟完成，因为审批还没弄）
	  * @return
	  */
	 @AutoLog(value = "BOM节点归档")
	 @ApiOperation(value="BOM节点归档", notes="BOM节点归档")
	 @GetMapping(value = "/changeEcnFin")
	 public Result<?> changeEcnFin(@RequestParam(name = "id", required = true) String id) {
		 log.info("BOM节点归档infoId="+id);

		 String messStr = proEcnRecordService.saveChangeEcnFin(id);
		 if(StringUtils.isNotEmpty(messStr)){
			 return Result.error(messStr);
		 }else{
			 return Result.OK("ECN完成！");
		 }

	 }



}
