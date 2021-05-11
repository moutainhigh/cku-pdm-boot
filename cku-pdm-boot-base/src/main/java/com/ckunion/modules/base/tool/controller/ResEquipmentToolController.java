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
	 * 分页列表查询
	 *
	 * @param resEquipmentTool
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "res_equipment_tool-分页列表查询")
	@ApiOperation(value="res_equipment_tool-分页列表查询", notes="res_equipment_tool-分页列表查询")
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
	 *   添加
	 *
	 * @param resEquipmentTool
	 * @return
	 */
	@AutoLog(value = "res_equipment_tool-添加")
	@ApiOperation(value="res_equipment_tool-添加", notes="res_equipment_tool-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ResEquipmentTool resEquipmentTool) {
		resEquipmentToolService.save(resEquipmentTool);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param resEquipmentTool
	 * @return
	 */
	@AutoLog(value = "res_equipment_tool-编辑")
	@ApiOperation(value="res_equipment_tool-编辑", notes="res_equipment_tool-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ResEquipmentTool resEquipmentTool) {
		resEquipmentToolService.updateById(resEquipmentTool);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "res_equipment_tool-通过id删除")
	@ApiOperation(value="res_equipment_tool-通过id删除", notes="res_equipment_tool-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		resEquipmentToolService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "res_equipment_tool-批量删除")
	@ApiOperation(value="res_equipment_tool-批量删除", notes="res_equipment_tool-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.resEquipmentToolService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "res_equipment_tool-通过id查询")
	@ApiOperation(value="res_equipment_tool-通过id查询", notes="res_equipment_tool-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ResEquipmentTool resEquipmentTool = resEquipmentToolService.getById(id);
		if(resEquipmentTool==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(resEquipmentTool);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param resEquipmentTool
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ResEquipmentTool resEquipmentTool) {
        return super.exportXls(request, resEquipmentTool, ResEquipmentTool.class, "res_equipment_tool");
    }

    /**
      * 通过excel导入数据
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
