package com.ckunion.modules.filelibrary.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.filelibrary.entity.BasCraftSurroundings;
import com.ckunion.modules.filelibrary.service.IBasCraftSurroundingsService;
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
 * @Description: 工艺环境表
 * @Author: jeecg-boot
 * @Date:   2021-03-24
 * @Version: V1.0
 */
@Api(tags="PDM-工艺环境表")
@RestController
@RequestMapping("/com.cku.common/basCraftSurroundings")
@Slf4j
public class BasCraftSurroundingsController extends JeecgController<BasCraftSurroundings, IBasCraftSurroundingsService> {
	@Autowired
	private IBasCraftSurroundingsService basCraftSurroundingsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param basCraftSurroundings
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "工艺环境表-分页列表查询")
	@ApiOperation(value="工艺环境表-分页列表查询", notes="工艺环境表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasCraftSurroundings basCraftSurroundings,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasCraftSurroundings> queryWrapper = QueryGenerator.initQueryWrapper(basCraftSurroundings, req.getParameterMap());
		Page<BasCraftSurroundings> page = new Page<BasCraftSurroundings>(pageNo, pageSize);
		IPage<BasCraftSurroundings> pageList = basCraftSurroundingsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param basCraftSurroundings
	 * @return
	 */
	@AutoLog(value = "工艺环境表-添加")
	@ApiOperation(value="工艺环境表-添加", notes="工艺环境表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasCraftSurroundings basCraftSurroundings) {
		basCraftSurroundingsService.save(basCraftSurroundings);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param basCraftSurroundings
	 * @return
	 */
	@AutoLog(value = "工艺环境表-编辑")
	@ApiOperation(value="工艺环境表-编辑", notes="工艺环境表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasCraftSurroundings basCraftSurroundings) {
		basCraftSurroundingsService.updateById(basCraftSurroundings);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工艺环境表-通过id删除")
	@ApiOperation(value="工艺环境表-通过id删除", notes="工艺环境表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basCraftSurroundingsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "工艺环境表-批量删除")
	@ApiOperation(value="工艺环境表-批量删除", notes="工艺环境表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basCraftSurroundingsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工艺环境表-通过id查询")
	@ApiOperation(value="工艺环境表-通过id查询", notes="工艺环境表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasCraftSurroundings basCraftSurroundings = basCraftSurroundingsService.getById(id);
		if(basCraftSurroundings==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basCraftSurroundings);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param basCraftSurroundings
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasCraftSurroundings basCraftSurroundings) {
        return super.exportXls(request, basCraftSurroundings, BasCraftSurroundings.class, "工艺环境表");
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
        return super.importExcel(request, response, BasCraftSurroundings.class);
    }

}
