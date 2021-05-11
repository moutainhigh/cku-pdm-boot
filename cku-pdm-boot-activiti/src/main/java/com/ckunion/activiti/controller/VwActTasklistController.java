package com.ckunion.activiti.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
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
import com.ckunion.activiti.entity.VwActTasklist;
import com.ckunion.activiti.service.IVwActTasklistService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

 /**
 * @Description: 我的任务
 * @Author: jeecg-boot
 * @Date:   2020-10-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="我的任务")
@RestController
@RequestMapping("/activiti/vwActTasklist")
public class VwActTasklistController extends JeecgController<VwActTasklist, IVwActTasklistService> {
	@Autowired
	private IVwActTasklistService vwActTasklistService;
	
	/**
	 * 分页列表查询
	 *
	 * @param vwActTasklist
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "我的任务-分页列表查询")
	@ApiOperation(value="我的任务-分页列表查询", notes="我的任务-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VwActTasklist vwActTasklist,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VwActTasklist> queryWrapper = QueryGenerator.initQueryWrapper(vwActTasklist, req.getParameterMap());
		Page<VwActTasklist> page = new Page<VwActTasklist>(pageNo, pageSize);
		IPage<VwActTasklist> pageList = vwActTasklistService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 分页列表查询
	 *
	 * @param vwActTasklist
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "代办")
	@ApiOperation(value="代办", notes="代办")
	@GetMapping(value = "/waitlist")
	public Result<?> querywaitPageList(VwActTasklist vwActTasklist,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VwActTasklist> queryWrapper = QueryGenerator.initQueryWrapper(vwActTasklist, req.getParameterMap());
		LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		/*String sql=" assignee in (select r.participant from wf_commision_rules r "
				+ "where r.isvalid='1' and r.attorney='"+user.getUsername()+"' "
				+ "and starttime  <= sysdate and endtime >= sysdate )or assignee= '"+user.getUsername()+"' ";*/
		String sql=" assignee= '"+user.getUsername()+"' or candidate =  '"+user.getUsername()+"'";
		queryWrapper.apply(sql);
		Page<VwActTasklist> page = new Page<VwActTasklist>(pageNo, pageSize);
		IPage<VwActTasklist> pageList = vwActTasklistService.page(page, queryWrapper);

		Map<String, Object> actUser = vwActTasklistService.getActUser();
		for (VwActTasklist record : pageList.getRecords()) {
			if(StrUtil.isEmpty(record.getAssigneeName())&&StrUtil.isNotEmpty(record.getCandidate())){

				record.setAssigneeName(actUser.get(record.getCandidate())+"");
			}
		}


		pageList.getRecords().stream().forEach(e->{


			long number= System.currentTimeMillis()-Convert.toDate(e.getCreateTime()).getTime();

			//然后在将毫秒转换为date类型就可以了
			//计算天
			int d=(int) ((number/86400000));
			//计算小时
			int h=(int) ((number%86400000)/3600000);
			//计算分钟
			int m=(int)((number%86400000)%3600000)/60000;
			//计算秒
			int s=(int)(((number%86400000)%3600000)%60000)/1000;
			String date = "";
			if(h>0) {
				date = date+d+"天 ";
			}
			if(h>0) {
				date = date+h+"小时 ";
			}
			if(m>0) {
				date = date+m+"分 ";
			}
			if(s>0) {
				date = date+s+"秒 ";
			}

			e.setConsumingTime(date);
			//e.setBusinessTitle(e.getBusinessTitle()+",耗时："+date);

		});


		return Result.OK(pageList);
	}
	
	
	
	/**
	 * 添加
	 *
	 * @param vwActTasklist
	 * @return
	 */
	@AutoLog(value = "我的任务-添加")
	@ApiOperation(value="我的任务-添加", notes="我的任务-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VwActTasklist vwActTasklist) {
		vwActTasklistService.save(vwActTasklist);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vwActTasklist
	 * @return
	 */
	@AutoLog(value = "我的任务-编辑")
	@ApiOperation(value="我的任务-编辑", notes="我的任务-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VwActTasklist vwActTasklist) {
		vwActTasklistService.updateById(vwActTasklist);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "我的任务-通过id删除")
	@ApiOperation(value="我的任务-通过id删除", notes="我的任务-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vwActTasklistService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "我的任务-批量删除")
	@ApiOperation(value="我的任务-批量删除", notes="我的任务-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vwActTasklistService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "我的任务-通过id查询")
	@ApiOperation(value="我的任务-通过id查询", notes="我的任务-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VwActTasklist vwActTasklist = vwActTasklistService.getById(id);
		return Result.OK(vwActTasklist);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vwActTasklist
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VwActTasklist vwActTasklist) {
      return super.exportXls(request, vwActTasklist, VwActTasklist.class, "我的任务");
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
      return super.importExcel(request, response, VwActTasklist.class);
  }

}
