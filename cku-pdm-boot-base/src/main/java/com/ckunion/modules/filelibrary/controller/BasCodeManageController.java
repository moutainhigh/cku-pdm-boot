package com.ckunion.modules.filelibrary.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.filelibrary.entity.BasCodeManage;
import com.ckunion.modules.filelibrary.service.IBasCodeManageService;
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
 * @Description: 编码管理表
 * @Author: jeecg-boot
 * @Date:   2021-03-24
 * @Version: V1.0
 */
@Api(tags="PDM-编码管理表")
@RestController
@RequestMapping("/com.cku.common/basCodeManage")
@Slf4j
public class BasCodeManageController extends JeecgController<BasCodeManage, IBasCodeManageService> {
	@Autowired
	private IBasCodeManageService basCodeManageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param basCodeManage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "编码管理表-分页列表查询")
	@ApiOperation(value="编码管理表-分页列表查询", notes="编码管理表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasCodeManage basCodeManage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasCodeManage> queryWrapper = QueryGenerator.initQueryWrapper(basCodeManage, req.getParameterMap());
		Page<BasCodeManage> page = new Page<BasCodeManage>(pageNo, pageSize);
		IPage<BasCodeManage> pageList = basCodeManageService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param basCodeManage
	 * @return
	 */
	@AutoLog(value = "编码管理表-添加")
	@ApiOperation(value="编码管理表-添加", notes="编码管理表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasCodeManage basCodeManage) {
		basCodeManageService.save(basCodeManage);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param basCodeManage
	 * @return
	 */
	@AutoLog(value = "编码管理表-编辑")
	@ApiOperation(value="编码管理表-编辑", notes="编码管理表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasCodeManage basCodeManage) {
		basCodeManageService.updateById(basCodeManage);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "编码管理表-通过id删除")
	@ApiOperation(value="编码管理表-通过id删除", notes="编码管理表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basCodeManageService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "编码管理表-批量删除")
	@ApiOperation(value="编码管理表-批量删除", notes="编码管理表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basCodeManageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "编码管理表-通过id查询")
	@ApiOperation(value="编码管理表-通过id查询", notes="编码管理表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasCodeManage basCodeManage = basCodeManageService.getById(id);
		if(basCodeManage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basCodeManage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param basCodeManage
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasCodeManage basCodeManage) {
        return super.exportXls(request, basCodeManage, BasCodeManage.class, "编码管理表");
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
        return super.importExcel(request, response, BasCodeManage.class);
    }

}
