package com.ckunion.modules.materials.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.materials.entity.BasMaterialInfo;
import com.ckunion.modules.materials.service.IBasMaterialInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Map;

/**
 * @Description: 原材料基础信息
 * @Author: jeecg-boot
 * @Date:   2021-03-30
 * @Version: V1.0
 */
@Api(tags="原材料基础信息")
@RestController
@RequestMapping("/basMaterialInfo")
@Slf4j
public class BasMaterialInfoController extends JeecgController<BasMaterialInfo, IBasMaterialInfoService> {
	@Autowired
	private IBasMaterialInfoService basMaterialInfoService;

	/**
	 * 分页列表查询
	 *
	 * @param basMaterialInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "原材料基础信息-分页列表查询")
	@ApiOperation(value="原材料基础信息-分页列表查询", notes="原材料基础信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasMaterialInfo basMaterialInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasMaterialInfo> queryWrapper = QueryGenerator.initQueryWrapper(basMaterialInfo, req.getParameterMap());
		Page<BasMaterialInfo> page = new Page<BasMaterialInfo>(pageNo, pageSize);
		IPage<BasMaterialInfo> pageList = basMaterialInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param basMaterialInfo
	 * @return
	 */
	@AutoLog(value = "原材料基础信息-添加")
	@ApiOperation(value="原材料基础信息-添加", notes="原材料基础信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasMaterialInfo basMaterialInfo) {

		String typeoid = basMaterialInfo.getTypeoid();
		if(StringUtils.isEmpty(typeoid)){
			typeoid = "";
		}

		Map<String, Object> maxCodeMap = basMaterialInfoService.getMaxCode(typeoid);
		int maxTypeCode = Integer.parseInt(maxCodeMap.get("MAXCODE") + "")+1;
		String procCode = "001";
		if(maxTypeCode < 10){
			procCode = "00"+maxTypeCode;
		}else if (maxTypeCode < 100){
			procCode = "0"+maxTypeCode;
		}else{
			procCode = ""+maxTypeCode;
		}
		basMaterialInfo.setMaterialCode(procCode);

		basMaterialInfoService.save(basMaterialInfo);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param basMaterialInfo
	 * @return
	 */
	@AutoLog(value = "原材料基础信息-编辑")
	@ApiOperation(value="原材料基础信息-编辑", notes="原材料基础信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasMaterialInfo basMaterialInfo) {
		basMaterialInfoService.updateById(basMaterialInfo);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "原材料基础信息-通过id删除")
	@ApiOperation(value="原材料基础信息-通过id删除", notes="原材料基础信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basMaterialInfoService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "原材料基础信息-批量删除")
	@ApiOperation(value="原材料基础信息-批量删除", notes="原材料基础信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basMaterialInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "原材料基础信息-通过id查询")
	@ApiOperation(value="原材料基础信息-通过id查询", notes="原材料基础信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasMaterialInfo basMaterialInfo = basMaterialInfoService.getById(id);
		if(basMaterialInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basMaterialInfo);
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param basMaterialInfo
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, BasMaterialInfo basMaterialInfo) {
		return super.exportXls(request, basMaterialInfo, BasMaterialInfo.class, "原材料基础信息");
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
		return super.importExcel(request, response, BasMaterialInfo.class);
	}

}
