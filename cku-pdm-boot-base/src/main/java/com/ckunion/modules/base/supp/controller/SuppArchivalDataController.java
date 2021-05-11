package com.ckunion.modules.base.supp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.base.supp.entity.SuppArchivalData;
import com.ckunion.modules.base.supp.service.ISuppArchivalDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: supp_archival_data
 * @Author: jeecg-boot
 * @Date:   2021-03-30
 * @Version: V1.0
 */
@Api(tags="supp_archival_data")
@RestController
@RequestMapping("/supp/suppArchivalData")
@Slf4j
public class SuppArchivalDataController extends JeecgController<SuppArchivalData, ISuppArchivalDataService> {
	@Autowired
	private ISuppArchivalDataService suppArchivalDataService;
	
	/**
	 * 分页列表查询
	 *
	 * @param suppArchivalData
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "supp_archival_data-分页列表查询")
	@ApiOperation(value="supp_archival_data-分页列表查询", notes="supp_archival_data-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SuppArchivalData suppArchivalData,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SuppArchivalData> queryWrapper = QueryGenerator.initQueryWrapper(suppArchivalData, req.getParameterMap());
		Page<SuppArchivalData> page = new Page<SuppArchivalData>(pageNo, pageSize);
		IPage<SuppArchivalData> pageList = suppArchivalDataService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param suppArchivalData
	 * @return
	 */
	@AutoLog(value = "supp_archival_data-添加")
	@ApiOperation(value="supp_archival_data-添加", notes="supp_archival_data-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SuppArchivalData suppArchivalData) {
		suppArchivalDataService.save(suppArchivalData);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param suppArchivalData
	 * @return
	 */
	@AutoLog(value = "supp_archival_data-编辑")
	@ApiOperation(value="supp_archival_data-编辑", notes="supp_archival_data-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SuppArchivalData suppArchivalData) {
		suppArchivalDataService.updateById(suppArchivalData);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "supp_archival_data-通过id删除")
	@ApiOperation(value="supp_archival_data-通过id删除", notes="supp_archival_data-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		suppArchivalDataService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "supp_archival_data-批量删除")
	@ApiOperation(value="supp_archival_data-批量删除", notes="supp_archival_data-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.suppArchivalDataService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "supp_archival_data-通过id查询")
	@ApiOperation(value="supp_archival_data-通过id查询", notes="supp_archival_data-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SuppArchivalData suppArchivalData = suppArchivalDataService.getById(id);
		if(suppArchivalData==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(suppArchivalData);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param suppArchivalData
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SuppArchivalData suppArchivalData) {
        return super.exportXls(request, suppArchivalData, SuppArchivalData.class, "supp_archival_data");
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
        return super.importExcel(request, response, SuppArchivalData.class);
    }

}
