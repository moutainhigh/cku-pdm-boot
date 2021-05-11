package com.ckunion.modules.base.typicalProc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ckunion.modules.base.typicalProc.entity.*;
import com.ckunion.modules.base.typicalProc.service.*;
import com.ckunion.modules.constants.BasConstant;
import com.xkcoding.http.util.StringUtil;
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
import com.ckunion.modules.base.typicalProc.vo.BasTypicalProcPage;
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
 * @Description: 典型工艺库
 * @Author: wuj
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Api(tags="典型工艺库")
@RestController
@RequestMapping("/typicalProc/basTypicalProc")
@Slf4j
public class BasTypicalProcController {
	@Autowired
	private IBasTypicalProcService basTypicalProcService;
	@Autowired
	private IBasTypicalPurposeService basTypicalPurposeService;
	@Autowired
	private IBasTypicalPurposeTargetService basTypicalPurposeTargetService;
	@Autowired
	private IBasTypicalMatService basTypicalMatService;
	@Autowired
	private IBasTypicalEquService basTypicalEquService;
	@Autowired
	private IBasTypicalToolService basTypicalToolService;
	@Autowired
	private IBasTypicalProcessService basTypicalProcessService;
	@Autowired
	private IBasTypicalProcessCheckService basTypicalProcessCheckService;
	@Autowired
	private IBasTypicalSurrService basTypicalSurrService;
	@Autowired
	private IBasTypicalAttentService basTypicalAttentService;
	@Autowired
	private IBasTypicalFileService basTypicalFileService;
	@Autowired
	private IBasTypicalImgService basTypicalImgService;
	
	/**
	 * 分页列表查询
	 *
	 * @param basTypicalProc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "典型工艺库-分页列表查询")
	@ApiOperation(value="典型工艺库-分页列表查询", notes="典型工艺库-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasTypicalProc basTypicalProc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasTypicalProc> queryWrapper = QueryGenerator.initQueryWrapper(basTypicalProc, req.getParameterMap());
		Page<BasTypicalProc> page = new Page<BasTypicalProc>(pageNo, pageSize);
		IPage<BasTypicalProc> pageList = basTypicalProcService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param basTypicalProcPage
	 * @return
	 */
	@AutoLog(value = "典型工艺库-添加")
	@ApiOperation(value="典型工艺库-添加", notes="典型工艺库-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasTypicalProcPage basTypicalProcPage) {
		BasTypicalProc basTypicalProc = new BasTypicalProc();
		BeanUtils.copyProperties(basTypicalProcPage, basTypicalProc);
		basTypicalProcService.saveMain(basTypicalProc, basTypicalProcPage.getBasTypicalPurposeList(),basTypicalProcPage.getBasTypicalPurposeTargetList(),basTypicalProcPage.getBasTypicalMatList(),basTypicalProcPage.getBasTypicalEquList(),basTypicalProcPage.getBasTypicalToolList(),basTypicalProcPage.getBasTypicalProcessList(),basTypicalProcPage.getBasTypicalSurrList(),basTypicalProcPage.getBasTypicalAttentList(),basTypicalProcPage.getBasTypicalFileList(),basTypicalProcPage.getBasTypicalImgList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param basTypicalProcPage
	 * @return
	 */
	@AutoLog(value = "典型工艺库-编辑")
	@ApiOperation(value="典型工艺库-编辑", notes="典型工艺库-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasTypicalProcPage basTypicalProcPage) {
		BasTypicalProc basTypicalProc = new BasTypicalProc();
		BeanUtils.copyProperties(basTypicalProcPage, basTypicalProc);
		BasTypicalProc basTypicalProcEntity = basTypicalProcService.getById(basTypicalProc.getId());
		if(basTypicalProcEntity==null) {
			return Result.error("未找到对应数据");
		}
		basTypicalProcService.updateMain(basTypicalProc, basTypicalProcPage.getBasTypicalPurposeList(),basTypicalProcPage.getBasTypicalPurposeTargetList(),basTypicalProcPage.getBasTypicalMatList(),basTypicalProcPage.getBasTypicalEquList(),basTypicalProcPage.getBasTypicalToolList(),basTypicalProcPage.getBasTypicalProcessList(),basTypicalProcPage.getBasTypicalSurrList(),basTypicalProcPage.getBasTypicalAttentList(),basTypicalProcPage.getBasTypicalFileList(),basTypicalProcPage.getBasTypicalImgList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "典型工艺库-通过id删除")
	@ApiOperation(value="典型工艺库-通过id删除", notes="典型工艺库-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basTypicalProcService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "典型工艺库-批量删除")
	@ApiOperation(value="典型工艺库-批量删除", notes="典型工艺库-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basTypicalProcService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "典型工艺库-通过id查询")
	@ApiOperation(value="典型工艺库-通过id查询", notes="典型工艺库-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasTypicalProc basTypicalProc = basTypicalProcService.getById(id);
		if(basTypicalProc==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basTypicalProc);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "目的和目标从表通过主表ID查询")
	@ApiOperation(value="目的和目标从表主表ID查询", notes="目的和目标从表-通主表ID查询")
	@GetMapping(value = "/queryBasTypicalPurposeByMainId")
	public Result<?> queryBasTypicalPurposeListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BasTypicalPurpose> basTypicalPurposeList = basTypicalPurposeService.selectByMainId(id);
		return Result.OK(basTypicalPurposeList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "目标从从表通过主表ID查询")
	@ApiOperation(value="目标从从表主表ID查询", notes="目标从从表-通主表ID查询")
	@GetMapping(value = "/queryBasTypicalPurposeTargetByMainId")
	public Result<?> queryBasTypicalPurposeTargetListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BasTypicalPurposeTarget> basTypicalPurposeTargetList = basTypicalPurposeTargetService.selectByMainId(id);
		return Result.OK(basTypicalPurposeTargetList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "原材料、辅料、材料从表通过主表ID查询")
	@ApiOperation(value="原材料、辅料、材料从表主表ID查询", notes="原材料、辅料、材料从表-通主表ID查询")
	@GetMapping(value = "/queryBasTypicalMatByMainId")
	public Result<?> queryBasTypicalMatListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BasTypicalMat> basTypicalMatList = basTypicalMatService.selectByMainId(id);
		return Result.OK(basTypicalMatList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备仪器从表通过主表ID查询")
	@ApiOperation(value="设备仪器从表主表ID查询", notes="设备仪器从表-通主表ID查询")
	@GetMapping(value = "/queryBasTypicalEquByMainId")
	public Result<?> queryBasTypicalEquListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BasTypicalEqu> basTypicalEquList = basTypicalEquService.selectByMainId(id);
		return Result.OK(basTypicalEquList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工装、用品从表通过主表ID查询")
	@ApiOperation(value="工装、用品从表主表ID查询", notes="工装、用品从表-通主表ID查询")
	@GetMapping(value = "/queryBasTypicalToolByMainId")
	public Result<?> queryBasTypicalToolListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BasTypicalTool> basTypicalToolList = basTypicalToolService.selectByMainId(id);
		return Result.OK(basTypicalToolList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "操作过程从表通过主表ID查询")
	@ApiOperation(value="操作过程从表主表ID查询", notes="操作过程从表-通主表ID查询")
	@GetMapping(value = "/queryBasTypicalProcessByMainId")
	public Result<?> queryBasTypicalProcessListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BasTypicalProcess> basTypicalProcessList = basTypicalProcessService.selectByMainId(id);
		return Result.OK(basTypicalProcessList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "操作过程检验方法从从表通过主表ID查询")
	@ApiOperation(value="操作过程检验方法从从表主表ID查询", notes="操作过程检验方法从从表-通主表ID查询")
	@GetMapping(value = "/queryBasTypicalProcessCheckByMainId")
	public Result<?> queryBasTypicalProcessCheckListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BasTypicalProcessCheck> basTypicalProcessCheckList = basTypicalProcessCheckService.selectByMainId(id);
		return Result.OK(basTypicalProcessCheckList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工作环境要求从表通过主表ID查询")
	@ApiOperation(value="工作环境要求从表主表ID查询", notes="工作环境要求从表-通主表ID查询")
	@GetMapping(value = "/queryBasTypicalSurrByMainId")
	public Result<?> queryBasTypicalSurrListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BasTypicalSurr> basTypicalSurrList = basTypicalSurrService.selectByMainId(id);
		return Result.OK(basTypicalSurrList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "注意事项从表通过主表ID查询")
	@ApiOperation(value="注意事项从表主表ID查询", notes="注意事项从表-通主表ID查询")
	@GetMapping(value = "/queryBasTypicalAttentByMainId")
	public Result<?> queryBasTypicalAttentListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BasTypicalAttent> basTypicalAttentList = basTypicalAttentService.selectByMainId(id);
		return Result.OK(basTypicalAttentList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "引用文件从表通过主表ID查询")
	@ApiOperation(value="引用文件从表主表ID查询", notes="引用文件从表-通主表ID查询")
	@GetMapping(value = "/queryBasTypicalFileByMainId")
	public Result<?> queryBasTypicalFileListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BasTypicalFile> basTypicalFileList = basTypicalFileService.selectByMainId(id);
		return Result.OK(basTypicalFileList);
	}
	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "图片从表通过主表ID查询")
	 @ApiOperation(value="图片从表主表ID查询", notes="图片从表-通主表ID查询")
	 @GetMapping(value = "/queryBasTypicalImgByMainId")
	 public Result<?> queryBasTypicalImgByMainId(@RequestParam(name="id",required=true) String id) {
		 List<BasTypicalImg> basTypicalImgList = basTypicalImgService.selectByMainId(id);
		 return Result.OK(basTypicalImgList);
	 }




    /**
    * 导出excel
    *
    * @param request
    * @param basTypicalProc
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasTypicalProc basTypicalProc) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<BasTypicalProc> queryWrapper = QueryGenerator.initQueryWrapper(basTypicalProc, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<BasTypicalProc> queryList = basTypicalProcService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<BasTypicalProc> basTypicalProcList = new ArrayList<BasTypicalProc>();
      if(oConvertUtils.isEmpty(selections)) {
          basTypicalProcList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          basTypicalProcList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<BasTypicalProcPage> pageList = new ArrayList<BasTypicalProcPage>();
      for (BasTypicalProc main : basTypicalProcList) {
          BasTypicalProcPage vo = new BasTypicalProcPage();
          BeanUtils.copyProperties(main, vo);
          List<BasTypicalPurpose> basTypicalPurposeList = basTypicalPurposeService.selectByMainId(main.getId());
          vo.setBasTypicalPurposeList(basTypicalPurposeList);
          List<BasTypicalPurposeTarget> basTypicalPurposeTargetList = basTypicalPurposeTargetService.selectByMainId(main.getId());
          vo.setBasTypicalPurposeTargetList(basTypicalPurposeTargetList);
          List<BasTypicalMat> basTypicalMatList = basTypicalMatService.selectByMainId(main.getId());
          vo.setBasTypicalMatList(basTypicalMatList);
          List<BasTypicalEqu> basTypicalEquList = basTypicalEquService.selectByMainId(main.getId());
          vo.setBasTypicalEquList(basTypicalEquList);
          List<BasTypicalTool> basTypicalToolList = basTypicalToolService.selectByMainId(main.getId());
          vo.setBasTypicalToolList(basTypicalToolList);
          List<BasTypicalProcess> basTypicalProcessList = basTypicalProcessService.selectByMainId(main.getId());
          vo.setBasTypicalProcessList(basTypicalProcessList);
          //List<BasTypicalProcessCheck> basTypicalProcessCheckList = basTypicalProcessCheckService.selectByMainId(main.getId());
          //vo.setBasTypicalProcessCheckList(basTypicalProcessCheckList);
          List<BasTypicalSurr> basTypicalSurrList = basTypicalSurrService.selectByMainId(main.getId());
          vo.setBasTypicalSurrList(basTypicalSurrList);
          List<BasTypicalAttent> basTypicalAttentList = basTypicalAttentService.selectByMainId(main.getId());
          vo.setBasTypicalAttentList(basTypicalAttentList);
          List<BasTypicalFile> basTypicalFileList = basTypicalFileService.selectByMainId(main.getId());
          vo.setBasTypicalFileList(basTypicalFileList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "典型工艺库列表");
      mv.addObject(NormalExcelConstants.CLASS, BasTypicalProcPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("典型工艺库数据", "导出人:"+sysUser.getRealname(), "典型工艺库"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }



	 /**
	  * 典型工艺变更申请
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "典型工艺变更申请")
	 @ApiOperation(value = "典型工艺变更申请", notes = "典型工艺变更申请")
	 @GetMapping(value = "/changeApply")
	 public Result<Map<String, Object>> changeApply(@RequestParam(name = "id", required = true) String id) {
		 Result<Map<String, Object>> result = new Result<Map<String, Object>>();

		 BasTypicalProc proc = basTypicalProcService.getById(id);
		 try {
			 log.info("典型工艺变更申请---开始="+proc.getWorkprocName());
			 proc.setProcState(BasConstant.TYPICALPROC_STATE_PREPA);
			 basTypicalProcService.updateById(proc);

			 result.setSuccess(true);
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("status", true);
			 map.put("errMsg", "变更申请成功");
			 result.setResult(map);

		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 result.setSuccess(true);
		 return result;
	 }

	 /**
	  * 打印典型工艺
	  * @param request
	  */
	 @RequestMapping(value = "/printProc")
	 public ModelAndView printProc(HttpServletRequest request, @RequestParam(name="id",required=true) String id, HttpServletResponse response) {

		 log.info("打印典型工艺="+id);
		 BasTypicalProc proc = basTypicalProcService.getById(id);
		 String filePath = basTypicalProcService.printTypicalProc(proc);
		 try {
			 String url = filePath;
			 log.info("进入下载文件方法，路径："+url+" ");
			 if(StringUtil.isNotEmpty(url)){
				 url=java.net.URLDecoder.decode(url,"UTF-8");
				 //创建文件对象
				 File f = new File(url);
				 //判断文件是否存在
				 if(f.exists()){
					 response.setContentType("application/octet-stream;charset=UTF-8");
					 try {
						 response.setHeader("Content-Disposition","attachment;filename="+java.net.URLEncoder.encode(f.getName(),"UTF-8"));
					 } catch (UnsupportedEncodingException e) {
						 e.printStackTrace();
					 }
					 //客户端不缓存
					 response.addHeader("Pargam","no-cache");
					 response.addHeader("Cache-Control","no-cache");
					 //columnsMap;
					 InputStream in = null;
					 in = new FileInputStream(f);
					 //获取输出流准备下载
					 OutputStream out = response.getOutputStream();
					 //定义一次大小
					 byte[] b = new byte[1024];
					 //定义每次读取文件的大小(最大1024)
					 int count=in.read(b);
					 while(count>0){
						 //输出文件(从0开始读取，读取count)
						 out.write(b, 0, count);
						 count=in.read(b);
					 }
					 out.flush();
					 out.close();
					 in.close();
				 }else{
					 String a="cannot find file!";
					 OutputStream out = response.getOutputStream();
					 out.write(a.getBytes());
					 out.flush();
					 out.close();
					 log.info("下载文件出错，路径："+url+" 无效，找不到对应文件");
				 }
			 }else{
				 String a="path is empty!";
				 OutputStream out = response.getOutputStream();
				 out.write(a.getBytes());
				 out.flush();
				 out.close();
				 log.info("下载文件出错，文件路径为空");
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }


		 return null;

		 /*
		 Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		 result.setSuccess(true);
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("status", true);
		 map.put("filePath", filePath);
		 result.setResult(map);

		 return result;
		 */

	 }

}
