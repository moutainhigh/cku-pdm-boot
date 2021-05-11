package com.ckunion.modules.code.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.applyno.entity.ProApplyMapno;
import com.ckunion.applyno.service.IProApplyMapnoService;
import com.ckunion.applyno.vo.ProApplyMapnoPage;
import com.ckunion.modules.code.entity.BaseNodeCode;
import com.ckunion.modules.code.service.IBaseNodeCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 节点编码管理
 * @Author: jeecg-boot
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Api(tags="节点编码管理")
@RestController
@RequestMapping("/node.code/baseNodeCode")
@Slf4j
public class BaseNodeCodeController extends JeecgController<BaseNodeCode, IBaseNodeCodeService> {
	@Autowired
	private IBaseNodeCodeService baseNodeCodeService;
	@Autowired
	private IProApplyMapnoService proApplyMapnoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param baseNodeCode
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "节点编码管理-分页列表查询")
	@ApiOperation(value="节点编码管理-分页列表查询", notes="节点编码管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BaseNodeCode baseNodeCode,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BaseNodeCode> queryWrapper = QueryGenerator.initQueryWrapper(baseNodeCode, req.getParameterMap());
		Page<BaseNodeCode> page = new Page<BaseNodeCode>(pageNo, pageSize);
		IPage<BaseNodeCode> pageList = baseNodeCodeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param baseNodeCode
	 * @return
	 */
	@AutoLog(value = "节点编码管理-添加")
	@ApiOperation(value="节点编码管理-添加", notes="节点编码管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BaseNodeCode baseNodeCode) {
		baseNodeCodeService.save(baseNodeCode);
		return Result.OK("添加成功！");
	}

	/**
	  *  保存产品结构输入的图号
	  * @param proApplyMapnoPage
	  * @return
	  */
	@AutoLog(value = "保存产品结构输入的图号")
	@ApiOperation(value="保存产品结构输入的图号", notes="保存产品结构输入的图号")
	@PutMapping(value = "/saveMapnoByProductApply")
	public Result<?> saveMapnoByProductApply(@RequestBody ProApplyMapnoPage proApplyMapnoPage) {
		 ProApplyMapno proApplyMapno = new ProApplyMapno();
		 BeanUtils.copyProperties(proApplyMapnoPage, proApplyMapno);
		 ProApplyMapno proApplyMapnoEntity = proApplyMapnoService.getById(proApplyMapno.getId());
		 if(proApplyMapnoEntity==null) {
			 return Result.error("未找到对应数据");
		 }
		 baseNodeCodeService.saveNodeCodeByProductApplyMapno(proApplyMapno, proApplyMapnoPage.getProApplyMapnoDtList());
		 proApplyMapnoService.saveMapnoByProductApply(proApplyMapno, proApplyMapnoPage.getProApplyMapnoDtList());
		 return Result.OK("编辑成功!");
	}

	
	/**
	 *  编辑
	 *
	 * @param baseNodeCode
	 * @return
	 */
	@AutoLog(value = "节点编码管理-编辑")
	@ApiOperation(value="节点编码管理-编辑", notes="节点编码管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BaseNodeCode baseNodeCode) {
		baseNodeCodeService.updateById(baseNodeCode);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "节点编码管理-通过id删除")
	@ApiOperation(value="节点编码管理-通过id删除", notes="节点编码管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		baseNodeCodeService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "节点编码管理-批量删除")
	@ApiOperation(value="节点编码管理-批量删除", notes="节点编码管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.baseNodeCodeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "节点编码管理-通过id查询")
	@ApiOperation(value="节点编码管理-通过id查询", notes="节点编码管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BaseNodeCode baseNodeCode = baseNodeCodeService.getById(id);
		if(baseNodeCode==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(baseNodeCode);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param baseNodeCode
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BaseNodeCode baseNodeCode) {
        return super.exportXls(request, baseNodeCode, BaseNodeCode.class, "节点编码管理");
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
        return super.importExcel(request, response, BaseNodeCode.class);
    }

}
