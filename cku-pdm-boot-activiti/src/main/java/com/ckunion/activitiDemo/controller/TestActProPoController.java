package com.ckunion.activitiDemo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import com.ckunion.activitiDemo.entity.TestActProPo;
import com.ckunion.activitiDemo.service.ITestActProPoService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2020-09-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="订单")
@RestController
@RequestMapping("/activitiDemo/testActProPo")
public class TestActProPoController extends JeecgController<TestActProPo, ITestActProPoService> {
	@Autowired
	private ITestActProPoService testActProPoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param testActProPo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "订单-分页列表查询")
	@ApiOperation(value="订单-分页列表查询", notes="订单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TestActProPo testActProPo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TestActProPo> queryWrapper = QueryGenerator.initQueryWrapper(testActProPo, req.getParameterMap());
		Page<TestActProPo> page = new Page<TestActProPo>(pageNo, pageSize);
		IPage<TestActProPo> pageList = testActProPoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param testActProPo
	 * @return
	 */
	@AutoLog(value = "订单-添加")
	@ApiOperation(value="订单-添加", notes="订单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TestActProPo testActProPo) {
		testActProPo.setActState("0");
		testActProPoService.save(testActProPo);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param testActProPo
	 * @return
	 */
	@AutoLog(value = "订单-编辑")
	@ApiOperation(value="订单-编辑", notes="订单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TestActProPo testActProPo) {
		testActProPoService.updateById(testActProPo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单-通过id删除")
	@ApiOperation(value="订单-通过id删除", notes="订单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		testActProPoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "订单-批量删除")
	@ApiOperation(value="订单-批量删除", notes="订单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testActProPoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单-通过id查询")
	@ApiOperation(value="订单-通过id查询", notes="订单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TestActProPo testActProPo = testActProPoService.getById(id);
		return Result.OK(testActProPo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param testActProPo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TestActProPo testActProPo) {
      return super.exportXls(request, testActProPo, TestActProPo.class, "订单");
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
      return super.importExcel(request, response, TestActProPo.class);
  }

}
