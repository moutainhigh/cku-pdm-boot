package com.ckunion.modules.base.proc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.base.proc.entity.BasSdWorkproc;
import com.ckunion.modules.base.proc.service.IBasSdWorkprocService;
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
import java.util.List;
import java.util.Map;

/**
 * @Description: 标准工序
 * @Author: jeecg-boot
 * @Date:   2021-03-22
 * @Version: V1.0
 */
@Api(tags="标准工序")
@RestController
@RequestMapping("/base/basSdWorkproc")
@Slf4j
public class BasSdWorkprocController extends JeecgController<BasSdWorkproc, IBasSdWorkprocService> {
	@Autowired
	private IBasSdWorkprocService basSdWorkprocService;
	
	/**
	 * 分页列表查询
	 *
	 * @param basSdWorkproc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "标准工序-分页列表查询")
	@ApiOperation(value="标准工序-分页列表查询", notes="标准工序-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasSdWorkproc basSdWorkproc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasSdWorkproc> queryWrapper = QueryGenerator.initQueryWrapper(basSdWorkproc, req.getParameterMap());
		Page<BasSdWorkproc> page = new Page<BasSdWorkproc>(pageNo, pageSize);
		IPage<BasSdWorkproc> pageList = basSdWorkprocService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param basSdWorkproc
	 * @return
	 */
	@AutoLog(value = "标准工序-添加")
	@ApiOperation(value="标准工序-添加", notes="标准工序-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasSdWorkproc basSdWorkproc) {
		//判断是否存在该工序
		List<BasSdWorkproc> hasList = basSdWorkprocService.list(new QueryWrapper<BasSdWorkproc>()
				.eq("WORKPROC_NAME", basSdWorkproc.getWorkprocName())
				.eq("IS_CHECK",basSdWorkproc.getIsCheck())
				.eq("PROC_TYPE",basSdWorkproc.getProcType()));
		if(hasList != null  &&  hasList.size() != 0){
			return Result.error("已添加过相同工序！");
		}else{
			Map<String, Object> maxCodeMap = basSdWorkprocService.getMaxTypeCode();
			int maxTypeCode = Integer.parseInt(maxCodeMap.get("MAXCODE") + "")+1;
			String procCode = "001";
			if(maxTypeCode < 10){
				procCode = "00"+maxTypeCode;
			}else if (maxTypeCode < 100){
				procCode = "0"+maxTypeCode;
			}else{
				procCode = ""+maxTypeCode;
			}
			basSdWorkproc.setWorkprocCode(procCode);

			basSdWorkprocService.save(basSdWorkproc);
			return Result.OK("添加成功！");
		}


	}
	
	/**
	 *  编辑
	 *
	 * @param basSdWorkproc
	 * @return
	 */
	@AutoLog(value = "标准工序-编辑")
	@ApiOperation(value="标准工序-编辑", notes="标准工序-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasSdWorkproc basSdWorkproc) {
		basSdWorkprocService.updateById(basSdWorkproc);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "标准工序-通过id删除")
	@ApiOperation(value="标准工序-通过id删除", notes="标准工序-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basSdWorkprocService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "标准工序-批量删除")
	@ApiOperation(value="标准工序-批量删除", notes="标准工序-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basSdWorkprocService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "标准工序-通过id查询")
	@ApiOperation(value="标准工序-通过id查询", notes="标准工序-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasSdWorkproc basSdWorkproc = basSdWorkprocService.getById(id);
		if(basSdWorkproc==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basSdWorkproc);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param basSdWorkproc
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasSdWorkproc basSdWorkproc) {
        return super.exportXls(request, basSdWorkproc, BasSdWorkproc.class, "标准工序");
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
        return super.importExcel(request, response, BasSdWorkproc.class);
    }

}
