package com.ckunion.modules.materials.controller;

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
import com.ckunion.modules.materials.entity.BasMaterialInfoColumn;
import com.ckunion.modules.materials.service.IBasMaterialInfoColumnService;

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
 * @Description: bas_material_info_column
 * @Author: jeecg-boot
 * @Date:   2021-04-16
 * @Version: V1.0
 */
@Api(tags="bas_material_info_column")
@RestController
@RequestMapping("/basMaterialInfoColumn")
@Slf4j
public class BasMaterialInfoColumnController extends JeecgController<BasMaterialInfoColumn, IBasMaterialInfoColumnService> {
	@Autowired
	private IBasMaterialInfoColumnService basMaterialInfoColumnService;
	
	/**
	 * 分页列表查询
	 *
	 * @param basMaterialInfoColumn
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "bas_material_info_column-分页列表查询")
	@ApiOperation(value="bas_material_info_column-分页列表查询", notes="bas_material_info_column-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasMaterialInfoColumn basMaterialInfoColumn,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasMaterialInfoColumn> queryWrapper = QueryGenerator.initQueryWrapper(basMaterialInfoColumn, req.getParameterMap());
		Page<BasMaterialInfoColumn> page = new Page<BasMaterialInfoColumn>(pageNo, pageSize);
		IPage<BasMaterialInfoColumn> pageList = basMaterialInfoColumnService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param basMaterialInfoColumn
	 * @return
	 */
	@AutoLog(value = "bas_material_info_column-添加")
	@ApiOperation(value="bas_material_info_column-添加", notes="bas_material_info_column-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasMaterialInfoColumn basMaterialInfoColumn) {
		basMaterialInfoColumnService.save(basMaterialInfoColumn);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param basMaterialInfoColumn
	 * @return
	 */
	@AutoLog(value = "bas_material_info_column-编辑")
	@ApiOperation(value="bas_material_info_column-编辑", notes="bas_material_info_column-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasMaterialInfoColumn basMaterialInfoColumn) {
		basMaterialInfoColumnService.updateById(basMaterialInfoColumn);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "bas_material_info_column-通过id删除")
	@ApiOperation(value="bas_material_info_column-通过id删除", notes="bas_material_info_column-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basMaterialInfoColumnService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "bas_material_info_column-批量删除")
	@ApiOperation(value="bas_material_info_column-批量删除", notes="bas_material_info_column-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basMaterialInfoColumnService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "bas_material_info_column-通过id查询")
	@ApiOperation(value="bas_material_info_column-通过id查询", notes="bas_material_info_column-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasMaterialInfoColumn basMaterialInfoColumn = basMaterialInfoColumnService.getById(id);
		if(basMaterialInfoColumn==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basMaterialInfoColumn);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param basMaterialInfoColumn
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasMaterialInfoColumn basMaterialInfoColumn) {
        return super.exportXls(request, basMaterialInfoColumn, BasMaterialInfoColumn.class, "bas_material_info_column");
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
        return super.importExcel(request, response, BasMaterialInfoColumn.class);
    }

}
