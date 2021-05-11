package com.ckunion.modules.filelibrary.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.filelibrary.entity.BasFile;
import com.ckunion.modules.filelibrary.service.IBasFileService;
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
 * @Description: 文件库管理
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Api(tags="文件库管理")
@RestController
@RequestMapping("/file/basFile")
@Slf4j
public class BasFileController extends JeecgController<BasFile, IBasFileService> {
	@Autowired
	private IBasFileService basFileService;
	
	/**
	 * 分页列表查询
	 *
	 * @param basFile
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "文件库管理-分页列表查询")
	@ApiOperation(value="文件库管理-分页列表查询", notes="文件库管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasFile basFile,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasFile> queryWrapper = QueryGenerator.initQueryWrapper(basFile, req.getParameterMap());
		Page<BasFile> page = new Page<BasFile>(pageNo, pageSize);
		IPage<BasFile> pageList = basFileService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param basFile
	 * @return
	 */
	@AutoLog(value = "文件库管理-添加")
	@ApiOperation(value="文件库管理-添加", notes="文件库管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasFile basFile) {
		basFileService.save(basFile);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param basFile
	 * @return
	 */
	@AutoLog(value = "文件库管理-编辑")
	@ApiOperation(value="文件库管理-编辑", notes="文件库管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasFile basFile) {
		basFileService.updateById(basFile);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文件库管理-通过id删除")
	@ApiOperation(value="文件库管理-通过id删除", notes="文件库管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basFileService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "文件库管理-批量删除")
	@ApiOperation(value="文件库管理-批量删除", notes="文件库管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basFileService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文件库管理-通过id查询")
	@ApiOperation(value="文件库管理-通过id查询", notes="文件库管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasFile basFile = basFileService.getById(id);
		if(basFile==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basFile);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param basFile
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasFile basFile) {
        return super.exportXls(request, basFile, BasFile.class, "文件库管理");
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
        return super.importExcel(request, response, BasFile.class);
    }

}
