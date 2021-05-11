package com.ckunion.modules.materials.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.materials.entity.BasUserOrg;
import com.ckunion.modules.materials.service.IBasUserOrgService;
import com.ckunion.modules.materials.vo.BasUserOrgVO;
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

 /**
 * @Description: 内外勤关系表
 * @Author: jeecg-boot
 * @Date:   2020-12-14
 * @Version: V1.0
 */
@Api(tags="内外勤关系表")
@RestController
@RequestMapping("/basUserOrg")
@Slf4j
public class BasUserOrgController extends JeecgController<BasUserOrg, IBasUserOrgService> {
	@Autowired
	private IBasUserOrgService basUserOrgService;

	 /*@Autowired
	 private ISysUserService sysUserService;

	 @RequestMapping(value = "/getWaiByNei", method = RequestMethod.GET)
	 public Result<IPage<SysUser>> userRoleList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												@RequestParam(name="pageSize", defaultValue="10") Integer pageSize, HttpServletRequest req) {
		 Result<IPage<SysUser>> result = new Result<IPage<SysUser>>();
		 Page<SysUser> page = new Page<SysUser>(pageNo, pageSize);
		 String neiuser = req.getParameter("neiuser");
		 String username = req.getParameter("username");
		 IPage<SysUser> pageList = basUserOrgService.queryBroConfig(page,neiuser,username);
		 result.setSuccess(true);
		 result.setResult(pageList);
		 return result;
	 }*/

	/**
	 * 分页列表查询
	 *
	 * @param basUserOrg
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "内外勤关系表-分页列表查询")
	@ApiOperation(value="内外勤关系表-分页列表查询", notes="内外勤关系表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasUserOrg basUserOrg,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasUserOrg> queryWrapper = QueryGenerator.initQueryWrapper(basUserOrg, req.getParameterMap());
		Page<BasUserOrg> page = new Page<BasUserOrg>(pageNo, pageSize);
		IPage<BasUserOrg> pageList = basUserOrgService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "内外勤关系表-添加")
	@ApiOperation(value="内外勤关系表-添加", notes="内外勤关系表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasUserOrgVO BasUserOrgVO) {

		Result<String> result = new Result<String>();
		try {
			String neiuser = BasUserOrgVO.getNeiuser();
			for(String waiuser:BasUserOrgVO.getWaiusers()) {
				BasUserOrg basUserOrg = new BasUserOrg();
				basUserOrg.setNeiUser(neiuser);
				basUserOrg.setWaiUser(waiuser);
				QueryWrapper<BasUserOrg> queryWrapper = new QueryWrapper<BasUserOrg>();
				queryWrapper.eq("nei_user", neiuser).eq("wai_user",waiuser);
				BasUserOrg one = basUserOrgService.getOne(queryWrapper);
				if(one==null){
					basUserOrgService.save(basUserOrg);
				}

			}
			result.setMessage("添加成功!");
			result.setSuccess(true);
			return result;
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			result.setSuccess(false);
			result.setMessage("出错了: " + e.getMessage());
			return result;
		}


	}
	
	/**
	 *  编辑
	 *
	 * @param basUserOrg
	 * @return
	 */
	@AutoLog(value = "内外勤关系表-编辑")
	@ApiOperation(value="内外勤关系表-编辑", notes="内外勤关系表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasUserOrg basUserOrg) {
		basUserOrgService.updateById(basUserOrg);
		return Result.OK("编辑成功!");
	}

	 /**
	  *   删除指定角色的内外勤关系
	  * @param
	  * @return
	  */
	 //@RequiresRoles({"admin"})
	 @RequestMapping(value = "/deleteUserOrg", method = RequestMethod.DELETE)
	 public Result<BasUserOrg> deleteUserOrg(@RequestParam(name="waiuser") String waiuser,
											   @RequestParam(name="neiuser",required=true) String neiuser
	 ) {
		 Result<BasUserOrg> result = new Result<BasUserOrg>();
		 try {
			 QueryWrapper<BasUserOrg> queryWrapper = new QueryWrapper<BasUserOrg>();
			 queryWrapper.eq("nei_user", neiuser).eq("wai_user",waiuser);
			 basUserOrgService.remove(queryWrapper);
			 result.success("删除成功!");
		 }catch(Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("删除失败！");
		 }
		 return result;
	 }

	 /**
	  *  批量删除指定角色的内外勤关系
	  *
	  * @param
	  * @return
	  */
	 //@RequiresRoles({"admin"})
	 @RequestMapping(value = "/deleteUserOrgBatch", method = RequestMethod.DELETE)
	 public Result<BasUserOrg> deleteUserOrgBatch(
			 @RequestParam(name="neiuser") String neiuser,
			 @RequestParam(name="waiusers",required=true) String waiusers) {
		 Result<BasUserOrg> result = new Result<BasUserOrg>();
		 try {
			 QueryWrapper<BasUserOrg> queryWrapper = new QueryWrapper<BasUserOrg>();
			 queryWrapper.eq("nei_user", neiuser).in("wai_user",Arrays.asList(waiusers.split(",")));
			 basUserOrgService.remove(queryWrapper);
			 result.success("删除成功!");
		 }catch(Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("删除失败！");
		 }
		 return result;
	 }


	 /**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "内外勤关系表-通过id查询")
	@ApiOperation(value="内外勤关系表-通过id查询", notes="内外勤关系表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasUserOrg basUserOrg = basUserOrgService.getById(id);
		if(basUserOrg==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basUserOrg);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param basUserOrg
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasUserOrg basUserOrg) {
        return super.exportXls(request, basUserOrg, BasUserOrg.class, "内外勤关系表");
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
        return super.importExcel(request, response, BasUserOrg.class);
    }

}
