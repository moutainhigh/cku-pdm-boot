package com.ckunion.modules.base.type.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.base.type.entity.BasTypeModel;
import com.ckunion.modules.base.type.entity.BasTypeMold;
import com.ckunion.modules.base.type.service.IBasTypeMoldService;
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
 * @Description: 产品类型
 * @Author: jeecg-boot
 * @Date:   2021-04-01
 * @Version: V1.0
 */
@Api(tags="产品类型")
@RestController
@RequestMapping("/base/basTypeMold")
@Slf4j
public class BasTypeMoldController extends JeecgController<BasTypeMold, IBasTypeMoldService> {
	@Autowired
	private IBasTypeMoldService basTypeMoldService;
	@Autowired
	private IBasTypeService basTypeService;

	/**
	 * 分页列表查询
	 *
	 * @param basTypeMold
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "产品类型-分页列表查询")
	@ApiOperation(value="产品类型-分页列表查询", notes="产品类型-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasTypeMold basTypeMold,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasTypeMold> queryWrapper = QueryGenerator.initQueryWrapper(basTypeMold, req.getParameterMap());
		Page<BasTypeMold> page = new Page<BasTypeMold>(pageNo, pageSize);
		IPage<BasTypeMold> pageList = basTypeMoldService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param basTypeMold
	 * @return
	 */
	@AutoLog(value = "产品类型-添加")
	@ApiOperation(value="产品类型-添加", notes="产品类型-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasTypeMold basTypeMold) {

		BasTypeMold one = basTypeMoldService.getOne(new QueryWrapper<BasTypeMold>().eq("type_name", basTypeMold.getTypeName()));
		if(one != null){
			return Result.error("当前类型下已存在:"+basTypeMold.getTypeName());
		}else{
			Map<String,Object> maxCodeMap = basTypeService.getMaxCodeByTable("BAS_TYPE_MOLD");
			int maxTypeCode = Integer.parseInt(maxCodeMap.get("MAXCODE") + "")+1;
			String typeCode ;
			if(maxTypeCode < 10){
				typeCode = "00"+maxTypeCode;
			}else if (maxTypeCode < 100){
				typeCode = "0"+maxTypeCode;
			}else{
				typeCode = ""+maxTypeCode;
			}
			basTypeMold.setTypeCode(typeCode);
			basTypeMoldService.save(basTypeMold);
			return Result.OK("添加成功！");
		}
	}
	
	/**
	 *  编辑
	 *
	 * @param basTypeMold
	 * @return
	 */
	@AutoLog(value = "产品类型-编辑")
	@ApiOperation(value="产品类型-编辑", notes="产品类型-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasTypeMold basTypeMold) {
		basTypeMoldService.updateById(basTypeMold);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "产品类型-通过id删除")
	@ApiOperation(value="产品类型-通过id删除", notes="产品类型-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basTypeMoldService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "产品类型-批量删除")
	@ApiOperation(value="产品类型-批量删除", notes="产品类型-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basTypeMoldService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "产品类型-通过id查询")
	@ApiOperation(value="产品类型-通过id查询", notes="产品类型-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasTypeMold basTypeMold = basTypeMoldService.getById(id);
		if(basTypeMold==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basTypeMold);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param basTypeMold
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasTypeMold basTypeMold) {
        return super.exportXls(request, basTypeMold, BasTypeMold.class, "产品类型");
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
        return super.importExcel(request, response, BasTypeMold.class);
    }

}
