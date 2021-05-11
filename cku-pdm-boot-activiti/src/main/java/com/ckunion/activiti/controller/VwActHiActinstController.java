package com.ckunion.activiti.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.MyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.activiti.entity.VwActHiActinst;
import com.ckunion.activiti.entity.VwActTasklist;
import com.ckunion.activiti.service.IVwActHiActinstService;
import com.ckunion.activiti.service.IVwActTasklistService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

 /**
 * @Description: 历史
 * @Author: jeecg-boot
 * @Date:   2020-10-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="历史")
@RestController
@RequestMapping("/activiti/vwActHiActinst")
public class VwActHiActinstController extends JeecgController<VwActHiActinst, IVwActHiActinstService> {
	@Autowired
	private IVwActHiActinstService vwActHiActinstService;
	@Autowired
	private IVwActTasklistService vwActTasklistService;

	/**
	 * 分页列表查询
	 *
	 * @param vwActHiActinst
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "历史-分页列表查询")
	@ApiOperation(value="历史-分页列表查询", notes="历史-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VwActHiActinst vwActHiActinst,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {

		Map<String,String[]> param = req.getParameterMap();
		//20210207 wuj原有的req.getParameterMap();不知为何会有createTime desc 排序，但是该视图没有这个字段，造成报错，所以改为新的map
		Map<String,String[]> newParam = new HashMap<String,String[]>();
		newParam.put("field",param.get("field"));
		newParam.put("pageNo",param.get("pageNo"));
		newParam.put("pageSize",param.get("pageSize"));


		//按照标题模糊查询
		if(StringUtils.isNotBlank(vwActHiActinst.getBusinessTitle())){
			vwActHiActinst.setBusinessTitle("*"+vwActHiActinst.getBusinessTitle()+"*");
		}

		LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		QueryWrapper<VwActHiActinst> queryWrapper = QueryGenerator.initQueryWrapper(vwActHiActinst, newParam);

		//20210207 wuj 由于本地环境mysql服务器版本太高，导致查询operationby时会提示字符编码的问题，生产环境mysql版本较低（5），
		//所以生产环境保留。本地环境需要删除这个条件
		String sql=" operationby= '"+user.getUsername()+"' ";
		queryWrapper.apply(sql);


		//queryWrapper.orderByDesc("start_time");
		Page<VwActHiActinst> page = new Page<VwActHiActinst>(pageNo, pageSize);
		IPage<VwActHiActinst> pageList = vwActHiActinstService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	/**
	 * 分页列表查询
	 *
	 * @param vwActHiActinst
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "历史-分页列表查询")
	@ApiOperation(value="历史-分页列表查询", notes="历史-分页列表查询")
	@GetMapping(value = "/hilist")
	public Result<?> hilist(VwActHiActinst vwActHiActinst,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		//procInstId
		String procInstId="-1";
		if(StringUtils.isNotBlank(vwActHiActinst.getProcInstId())) {
			procInstId = vwActHiActinst.getProcInstId();
			vwActHiActinst.setProcInstId(null);
		}
		Map<String, Object> actUser = vwActTasklistService.getActUser();
		QueryWrapper<VwActHiActinst> queryWrapper = QueryGenerator.initQueryWrapper(vwActHiActinst, req.getParameterMap());
		queryWrapper.eq("proc_inst_id", procInstId);
		queryWrapper.orderByAsc("start_time","end_time");
		Page<VwActHiActinst> page = new Page<VwActHiActinst>(pageNo, pageSize);
		IPage<VwActHiActinst> pageList = vwActHiActinstService.page(page, queryWrapper);
		for (VwActHiActinst record : pageList.getRecords()) {
			if(StringUtils.isEmpty(record.getAssignee())&&StringUtils.isNotEmpty(record.getSpr())){
				record.setAssignee(actUser.get(record.getSpr())+"");
			}
		}
		//查询当前待审批的
		Map<String,Object> queryMap = new HashMap();
		queryMap.put("proc_inst_id", procInstId);
		List<VwActTasklist> taskList = vwActTasklistService.listByMap(queryMap);

		if(taskList.size()>0) {
			for (VwActTasklist task : taskList) {
				VwActHiActinst tmpActyhi = new VwActHiActinst();
				try {
					MyBeanUtils.copyBeanNotNull2Bean( task,tmpActyhi);
					tmpActyhi.setAssignee(task.getAssigneeName());
					System.out.println(task.getAssigneeName());
					if(StringUtils.isEmpty(task.getAssigneeName())&&StringUtils.isNotEmpty(task.getCandidate())){
						tmpActyhi.setAssignee(actUser.get(task.getCandidate())+"");
					}
					tmpActyhi.setActId(task.getActId());
					tmpActyhi.setActName(task.getActName());
					tmpActyhi.setBusinessTitle(task.getBusinessTitle());
					tmpActyhi.setProcInstId(task.getProcInstId());
					tmpActyhi.setStartTime(task.getCreateTime());
					pageList.getRecords().add(tmpActyhi);
					//pageList.setPages(pageList.getPages()+1);
					pageList.setTotal(pageList.getTotal()+1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param vwActHiActinst
	 * @return
	 */
	@AutoLog(value = "历史-添加")
	@ApiOperation(value="历史-添加", notes="历史-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VwActHiActinst vwActHiActinst) {
		vwActHiActinstService.save(vwActHiActinst);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vwActHiActinst
	 * @return
	 */
	@AutoLog(value = "历史-编辑")
	@ApiOperation(value="历史-编辑", notes="历史-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VwActHiActinst vwActHiActinst) {
		vwActHiActinstService.updateById(vwActHiActinst);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "历史-通过id删除")
	@ApiOperation(value="历史-通过id删除", notes="历史-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vwActHiActinstService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "历史-批量删除")
	@ApiOperation(value="历史-批量删除", notes="历史-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vwActHiActinstService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "历史-通过id查询")
	@ApiOperation(value="历史-通过id查询", notes="历史-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VwActHiActinst vwActHiActinst = vwActHiActinstService.getById(id);
		return Result.OK(vwActHiActinst);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vwActHiActinst
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VwActHiActinst vwActHiActinst) {
      return super.exportXls(request, vwActHiActinst, VwActHiActinst.class, "历史");
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
      return super.importExcel(request, response, VwActHiActinst.class);
  }

}
