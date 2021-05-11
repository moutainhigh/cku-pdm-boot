package com.ckunion.modules.base.type.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.base.type.entity.BasType;
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
 * @Description: 基础类型表
 * @Author: jeecg-boot
 * @Date:   2021-03-11
 * @Version: V1.0
 */
@Api(tags="基础类型表")
@RestController
@RequestMapping("/basType")
@Slf4j
public class BasTypeController extends JeecgController<BasType, IBasTypeService> {
	@Autowired
	private IBasTypeService basTypeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param basType
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "基础类型表-分页列表查询")
	@ApiOperation(value="基础类型表-分页列表查询", notes="基础类型表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasType basType,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasType> queryWrapper = QueryGenerator.initQueryWrapper(basType, req.getParameterMap());
		Page<BasType> page = new Page<BasType>(pageNo, pageSize);
		IPage<BasType> pageList = basTypeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param basType
	 * @return
	 */
	@AutoLog(value = "基础类型表-添加")
	@ApiOperation(value="基础类型表-添加", notes="基础类型表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasType basType) {
		BasType one = basTypeService.getOne(new QueryWrapper<BasType>().eq("type_name", basType.getTypeName()).eq("type_Pid", basType.getTypePid()));
		if(one != null){
			return Result.error("当前类型下已存在:"+basType.getTypeName());
		}else{
			Map<String,Object> maxCodeMap = basTypeService.getMaxCode(basType.getTypePid());
			int maxTypeCode = Integer.parseInt(maxCodeMap.get("MAXCODE") + "")+1;
			String typeCode = "001";
			if(maxTypeCode < 10){
				typeCode = "00"+maxTypeCode;
			}else if (maxTypeCode < 100){
				typeCode = "0"+maxTypeCode;
			}else{
				typeCode = ""+maxTypeCode;
			}
			basType.setTypeCode(typeCode);
			basTypeService.save(basType);
			return Result.OK("添加成功！");
		}

	}
	
	/**
	 *  编辑
	 *
	 * @param basType
	 * @return
	 */
	@AutoLog(value = "基础类型表-编辑")
	@ApiOperation(value="基础类型表-编辑", notes="基础类型表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasType basType) {
		basTypeService.updateById(basType);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "基础类型表-通过id删除")
	@ApiOperation(value="基础类型表-通过id删除", notes="基础类型表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basTypeService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "基础类型表-批量删除")
	@ApiOperation(value="基础类型表-批量删除", notes="基础类型表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basTypeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "基础类型表-通过id查询")
	@ApiOperation(value="基础类型表-通过id查询", notes="基础类型表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasType basType = basTypeService.getById(id);
		if(basType==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basType);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param basType
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasType basType) {
        return super.exportXls(request, basType, BasType.class, "基础类型表");
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
        return super.importExcel(request, response, BasType.class);
    }

}
