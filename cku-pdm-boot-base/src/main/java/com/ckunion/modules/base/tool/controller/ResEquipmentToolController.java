package com.ckunion.modules.base.tool.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.ckunion.modules.base.tool.entity.ResEquipmentTool;
import com.ckunion.modules.base.tool.service.IResEquipmentToolService;

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
 * @Description: res_equipment_tool
 * @Author: jeecg-boot
 * @Date:   2021-04-08
 * @Version: V1.0
 */
@Api(tags="res_equipment_tool")
@RestController
@RequestMapping("/tool/resEquipmentTool")
@Slf4j
public class ResEquipmentToolController extends JeecgController<ResEquipmentTool, IResEquipmentToolService> {
	@Autowired
	private IResEquipmentToolService resEquipmentToolService;
	
	/**
	 * ??????????????????
	 *
	 * @param resEquipmentTool
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "res_equipment_tool-??????????????????")
	@ApiOperation(value="res_equipment_tool-??????????????????", notes="res_equipment_tool-??????????????????")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ResEquipmentTool resEquipmentTool,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		log.info("bbbbbbbbb");
		QueryWrapper<ResEquipmentTool> queryWrapper = QueryGenerator.initQueryWrapper(resEquipmentTool, req.getParameterMap());
		Page<ResEquipmentTool> page = new Page<ResEquipmentTool>(pageNo, pageSize);
		IPage<ResEquipmentTool> pageList = resEquipmentToolService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   ??????
	 *
	 * @param resEquipmentTool
	 * @return
	 */
	@AutoLog(value = "res_equipment_tool-??????")
	@ApiOperation(value="res_equipment_tool-??????", notes="res_equipment_tool-??????")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ResEquipmentTool resEquipmentTool) {
		resEquipmentToolService.save(resEquipmentTool);
		return Result.OK("???????????????");
	}
	
	/**
	 *  ??????
	 *
	 * @param resEquipmentTool
	 * @return
	 */
	@AutoLog(value = "res_equipment_tool-??????")
	@ApiOperation(value="res_equipment_tool-??????", notes="res_equipment_tool-??????")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ResEquipmentTool resEquipmentTool) {
		resEquipmentToolService.updateById(resEquipmentTool);
		return Result.OK("????????????!");
	}
	
	/**
	 *   ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "res_equipment_tool-??????id??????")
	@ApiOperation(value="res_equipment_tool-??????id??????", notes="res_equipment_tool-??????id??????")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		resEquipmentToolService.removeById(id);
		return Result.OK("????????????!");
	}
	
	/**
	 *  ????????????
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "res_equipment_tool-????????????")
	@ApiOperation(value="res_equipment_tool-????????????", notes="res_equipment_tool-????????????")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.resEquipmentToolService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("??????????????????!");
	}
	
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "res_equipment_tool-??????id??????")
	@ApiOperation(value="res_equipment_tool-??????id??????", notes="res_equipment_tool-??????id??????")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ResEquipmentTool resEquipmentTool = resEquipmentToolService.getById(id);
		if(resEquipmentTool==null) {
			return Result.error("?????????????????????");
		}
		return Result.OK(resEquipmentTool);
	}

    /**
    * ??????excel
    *
    * @param request
    * @param resEquipmentTool
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ResEquipmentTool resEquipmentTool) {
        return super.exportXls(request, resEquipmentTool, ResEquipmentTool.class, "res_equipment_tool");
    }

    /**
      * ??????excel????????????
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ResEquipmentTool.class);
    }

}
