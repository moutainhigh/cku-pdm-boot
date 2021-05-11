package com.ckunion.modules.materials.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.materials.entity.BasUnit;
import com.ckunion.modules.materials.service.IBasUnitService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 /**
 * @Description: 计量单位维护
 * @Author: jeecg-boot
 * @Date:   2020-10-21
 * @Version: V1.0
 */
@Api(tags="计量单位维护")
@RestController
@RequestMapping("/basUnit")
@Slf4j
public class BasUnitController extends JeecgController<BasUnit, IBasUnitService> {
	@Autowired
	private IBasUnitService basUnitService;
	
	/**
	 * 分页列表查询
	 *
	 * @param basUnit
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "计量单位维护-分页列表查询")
	@ApiOperation(value="计量单位维护-分页列表查询", notes="计量单位维护-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasUnit basUnit,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasUnit> queryWrapper = QueryGenerator.initQueryWrapper(basUnit, req.getParameterMap());
		Page<BasUnit> page = new Page<BasUnit>(pageNo, pageSize);
		IPage<BasUnit> pageList = basUnitService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param basUnit
	 * @return
	 */
	@AutoLog(value = "计量单位维护-添加")
	@ApiOperation(value="计量单位维护-添加", notes="计量单位维护-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasUnit basUnit) {

		String UnitCode=basUnitService.maxUnitCode(basUnit.getUnitPid());
		basUnit.setUnitCode(UnitCode);
		basUnitService.save(basUnit);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param basUnit
	 * @return
	 */
	@AutoLog(value = "计量单位维护-编辑")
	@ApiOperation(value="计量单位维护-编辑", notes="计量单位维护-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasUnit basUnit) {

		basUnitService.updateById(basUnit);
		return Result.OK("编辑成功!");
	}


	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "计量单位维护-通过id删除")
	@ApiOperation(value="计量单位维护-通过id删除", notes="计量单位维护-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basUnitService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "计量单位维护-批量删除")
	@ApiOperation(value="计量单位维护-批量删除", notes="计量单位维护-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basUnitService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}



	 /**
	  *  批量删除
	  *
	  * @param pid
	  * @return
	  */
	 @AutoLog(value = "计量单位维护-通过pid批量删除")
	 @ApiOperation(value="计量单位维护-通过pid批量删除", notes="计量单位维护-通过pid批量删除")
	 @DeleteMapping(value = "/deleteBatchByCode")
	 public Result<?> deleteBatchByCode(@RequestParam(name="pid",required=true) String pid) {

		 List<BasUnit> unit_pid = this.basUnitService.query().eq("unit_pid", pid).list();
		 List<String> intlist=new ArrayList<String>();

		 unit_pid.forEach(e->{intlist.add(e.getId());});

		 this.basUnitService.removeByIds(intlist);

		 return Result.OK("批量删除成功!");
	 }
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "计量单位维护-通过id查询")
	@ApiOperation(value="计量单位维护-通过id查询", notes="计量单位维护-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasUnit basUnit = basUnitService.getById(id);
		if(basUnit==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basUnit);
	}


	 /**
	  * 修改状态
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "修改状态")
	 @ApiOperation(value="修改状态", notes="修改状态")
	 @GetMapping(value = "/editStatus")
	 public Result<?> edit_status(@RequestParam("id") String id,@RequestParam("state") Integer state) {

		 //增加一个获取附加参数的类
		 BasUnit update= basUnitService.getById(id);
		 update.setId(id);
		 update.setState(state);
		 basUnitService.updateById(update);

		 return Result.OK("编辑成功!");
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param basUnit
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasUnit basUnit) {
        return super.exportXls(request, basUnit, BasUnit.class, "计量单位维护");
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
        return super.importExcel(request, response, BasUnit.class);
    }

}
