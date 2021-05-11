package com.ckunion.group.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.system.util.JwtUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.ckunion.group.entity.ProGroupUser;
import com.ckunion.group.entity.ProGroup;
import com.ckunion.group.vo.ProGroupPage;
import com.ckunion.group.service.IProGroupService;
import com.ckunion.group.service.IProGroupUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 团队
 * @Author: jeecg-boot
 * @Date:   2021-04-20
 * @Version: V1.0
 */
@Api(tags="团队")
@RestController
@RequestMapping("/group/proGroup")
@Slf4j
public class ProGroupController {
	@Autowired
	private IProGroupService proGroupService;
	@Autowired
	private IProGroupUserService proGroupUserService;
	
	/**
	 * 分页列表查询
	 *
	 * @param proGroup
	 * @param req
	 * @return
	 */
	@AutoLog(value = "团队-分页列表查询")
	@ApiOperation(value="团队-分页列表查询", notes="团队-分页列表查询")
	@GetMapping(value = "/getMyGroupList")
	public Result<?> getMyGroupList(ProGroup proGroup,HttpServletRequest req) {

		String username = JwtUtil.getUserNameByToken(req);
		proGroup.setCreateBy(username);
		QueryWrapper<ProGroup> queryWrapper = QueryGenerator.initQueryWrapper(proGroup, req.getParameterMap());
		List<ProGroup> list = proGroupService.list(queryWrapper);
		return Result.OK(list);
	}
	
	/**
	 *   添加
	 *
	 * @param proGroupPage
	 * @return
	 */
	@AutoLog(value = "团队-添加")
	@ApiOperation(value="团队-添加", notes="团队-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ProGroupPage proGroupPage) {
		ProGroup proGroup = new ProGroup();
		BeanUtils.copyProperties(proGroupPage, proGroup);
		proGroupService.saveMain(proGroup,proGroupPage.getProGroupUserList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param proGroupPage
	 * @return
	 */
	@AutoLog(value = "团队-编辑")
	@ApiOperation(value="团队-编辑", notes="团队-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ProGroupPage proGroupPage) {
		ProGroup proGroup = new ProGroup();
		BeanUtils.copyProperties(proGroupPage, proGroup);
		ProGroup proGroupEntity = proGroupService.getById(proGroup.getId());
		if(proGroupEntity==null) {
			return Result.error("未找到对应数据");
		}
		proGroupService.updateMain(proGroup,proGroupPage.getProGroupUserList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "团队-通过id删除")
	@ApiOperation(value="团队-通过id删除", notes="团队-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		proGroupService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "团队-批量删除")
	@ApiOperation(value="团队-批量删除", notes="团队-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.proGroupService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "团队-通过id查询")
	@ApiOperation(value="团队-通过id查询", notes="团队-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ProGroup proGroup = proGroupService.getById(id);
		if(proGroup==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(proGroup);

	}
	

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "团队成员从表通过主表ID查询")
	@ApiOperation(value="团队成员从表主表ID查询", notes="团队成员从表-通主表ID查询")
	@GetMapping(value = "/queryProGroupUserByMainId")
	public Result<?> queryProGroupUserListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ProGroupUser> proGroupUserList = proGroupUserService.selectByMainId(id);
		return Result.OK(proGroupUserList);
	}



}
