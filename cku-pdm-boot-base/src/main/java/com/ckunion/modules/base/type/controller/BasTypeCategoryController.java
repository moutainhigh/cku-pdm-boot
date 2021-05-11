package com.ckunion.modules.base.type.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.base.type.entity.BasTypeCategory;
import com.ckunion.modules.base.type.entity.BasTypeMold;
import com.ckunion.modules.base.type.service.IBasTypeCategoryService;
import com.ckunion.modules.base.type.service.IBasTypeService;
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
import java.util.Map;

/**
 * @Description: 产品类别
 * @Author: jeecg-boot
 * @Date:   2021-04-01
 * @Version: V1.0
 */
@Api(tags="产品类别")
@RestController
@RequestMapping("/base/basTypeCategory")
@Slf4j
public class BasTypeCategoryController extends JeecgController<BasTypeCategory, IBasTypeCategoryService> {
	@Autowired
	private IBasTypeCategoryService basTypeCategoryService;

	 @Autowired
	 private IBasTypeService basTypeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param basTypeCategory
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "产品类别-分页列表查询")
	@ApiOperation(value="产品类别-分页列表查询", notes="产品类别-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasTypeCategory basTypeCategory,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasTypeCategory> queryWrapper = QueryGenerator.initQueryWrapper(basTypeCategory, req.getParameterMap());
		Page<BasTypeCategory> page = new Page<BasTypeCategory>(pageNo, pageSize);
		IPage<BasTypeCategory> pageList = basTypeCategoryService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param basTypeCategory
	 * @return
	 */
	@AutoLog(value = "产品类别-添加")
	@ApiOperation(value="产品类别-添加", notes="产品类别-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasTypeCategory basTypeCategory) {

		BasTypeCategory one = basTypeCategoryService.getOne(new QueryWrapper<BasTypeCategory>().eq("type_name", basTypeCategory.getTypeName()));
		if(one != null){
			return Result.error("当前类型下已存在:"+basTypeCategory.getTypeName());
		}else{
			Map<String,Object> maxCodeMap = basTypeService.getMaxCodeByTable("BAS_TYPE_CATEGORY");
			int maxTypeCode = Integer.parseInt(maxCodeMap.get("MAXCODE") + "")+1;
			String typeCode ;
			if(maxTypeCode < 10){
				typeCode = "00"+maxTypeCode;
			}else if (maxTypeCode < 100){
				typeCode = "0"+maxTypeCode;
			}else{
				typeCode = ""+maxTypeCode;
			}
			basTypeCategory.setTypeCode(typeCode);
			basTypeCategoryService.save(basTypeCategory);
			return Result.OK("添加成功！");
		}
	}
	
	/**
	 *  编辑
	 *
	 * @param basTypeCategory
	 * @return
	 */
	@AutoLog(value = "产品类别-编辑")
	@ApiOperation(value="产品类别-编辑", notes="产品类别-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasTypeCategory basTypeCategory) {
		basTypeCategoryService.updateById(basTypeCategory);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "产品类别-通过id删除")
	@ApiOperation(value="产品类别-通过id删除", notes="产品类别-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basTypeCategoryService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "产品类别-批量删除")
	@ApiOperation(value="产品类别-批量删除", notes="产品类别-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basTypeCategoryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "产品类别-通过id查询")
	@ApiOperation(value="产品类别-通过id查询", notes="产品类别-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasTypeCategory basTypeCategory = basTypeCategoryService.getById(id);
		if(basTypeCategory==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basTypeCategory);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param basTypeCategory
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasTypeCategory basTypeCategory) {
        return super.exportXls(request, basTypeCategory, BasTypeCategory.class, "产品类别");
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
        return super.importExcel(request, response, BasTypeCategory.class);
    }

}
