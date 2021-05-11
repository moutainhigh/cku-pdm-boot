package com.ckunion.modules.base.node.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.base.node.entity.BasNodeType;
import com.ckunion.modules.base.node.service.IBasNodeTypeService;
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
 * @Description: 节点类型
 * @Author: jeecg-boot
 * @Date:   2021-03-09
 * @Version: V1.0
 */
@Api(tags="节点类型")
@RestController
@RequestMapping("/base/basNodeType")
@Slf4j
public class BasNodeTypeController extends JeecgController<BasNodeType, IBasNodeTypeService> {
	@Autowired
	private IBasNodeTypeService basNodeTypeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param basNodeType
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "节点类型-分页列表查询")
	@ApiOperation(value="节点类型-分页列表查询", notes="节点类型-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasNodeType basNodeType,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasNodeType> queryWrapper = QueryGenerator.initQueryWrapper(basNodeType, req.getParameterMap());
		Page<BasNodeType> page = new Page<BasNodeType>(pageNo, pageSize);
		IPage<BasNodeType> pageList = basNodeTypeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param basNodeType
	 * @return
	 */
	@AutoLog(value = "节点类型-添加")
	@ApiOperation(value="节点类型-添加", notes="节点类型-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasNodeType basNodeType) {
		BasNodeType getOne = basNodeTypeService.getOne(new QueryWrapper<BasNodeType>().eq("node_name", basNodeType.getNodeName()));
		if(null == getOne){
			Map<String, Object> maxCodeMap = basNodeTypeService.getMaxTypeCode();
			int maxTypeCode = Integer.parseInt(maxCodeMap.get("MAXCODE") + "")+1;
			String nodeCode = "001";
			if(maxTypeCode < 10){
				nodeCode = "00"+maxTypeCode;
			}else if (maxTypeCode < 100){
				nodeCode = "0"+maxTypeCode;
			}else{
				nodeCode = ""+maxTypeCode;
			}
			basNodeType.setNodeCode(nodeCode);
			basNodeTypeService.save(basNodeType);
			return Result.OK("添加成功！");
		}else{
			return  Result.error("节点类型:"+basNodeType.getNodeName()+" 已存在！");
		}
	}
	
	/**
	 *  编辑
	 *
	 * @param basNodeType
	 * @return
	 */
	@AutoLog(value = "节点类型-编辑")
	@ApiOperation(value="节点类型-编辑", notes="节点类型-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasNodeType basNodeType) {
		basNodeTypeService.updateById(basNodeType);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "节点类型-通过id删除")
	@ApiOperation(value="节点类型-通过id删除", notes="节点类型-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basNodeTypeService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "节点类型-批量删除")
	@ApiOperation(value="节点类型-批量删除", notes="节点类型-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basNodeTypeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "节点类型-通过id查询")
	@ApiOperation(value="节点类型-通过id查询", notes="节点类型-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasNodeType basNodeType = basNodeTypeService.getById(id);
		if(basNodeType==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basNodeType);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param basNodeType
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasNodeType basNodeType) {
        return super.exportXls(request, basNodeType, BasNodeType.class, "节点类型");
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
        return super.importExcel(request, response, BasNodeType.class);
    }

}
