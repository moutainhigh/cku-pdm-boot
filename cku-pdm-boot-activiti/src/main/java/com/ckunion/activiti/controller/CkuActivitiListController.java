package com.ckunion.activiti.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.StrUtil;
import com.ckunion.activiti.entity.VwActTasklist;
import com.ckunion.activiti.service.ActServiceI;
import com.ckunion.activiti.service.IVwActTasklistService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.CommonUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.activiti.entity.CkuActivitiList;
import com.ckunion.activiti.service.ICkuActivitiListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

 /**
 * @Description: 流程列表
 * @Author: jeecg-boot
 * @Date:   2020-09-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="流程列表")
@RestController
@RequestMapping("/activiti/ckuActivitiList")
public class CkuActivitiListController extends JeecgController<CkuActivitiList, ICkuActivitiListService> {

	// @Value(value = "${jeecg.path.activite}")
   /* private String activitepath = ResourceUtils.getURL("classpath:").getPath();*/

    /**
     * 本地：local minio：minio 阿里：alioss
     */
    @Value(value="${jeecg.uploadType}")
    private String uploadType;
    
	@Autowired
	private ICkuActivitiListService ckuActivitiListService;

     @Autowired
     private IVwActTasklistService vwActTasklistService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ckuActivitiList
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "流程列表-分页列表查询")
	@ApiOperation(value="流程列表-分页列表查询", notes="流程列表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CkuActivitiList ckuActivitiList,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CkuActivitiList> queryWrapper = QueryGenerator.initQueryWrapper(ckuActivitiList, req.getParameterMap());
		Page<CkuActivitiList> page = new Page<CkuActivitiList>(pageNo, pageSize);
		IPage<CkuActivitiList> pageList = ckuActivitiListService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param ckuActivitiList
	 * @return
	 */
	@AutoLog(value = "流程列表-添加")
	@ApiOperation(value="流程列表-添加", notes="流程列表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CkuActivitiList ckuActivitiList) {
		ckuActivitiListService.save(ckuActivitiList);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ckuActivitiList
	 * @return
	 */
	@AutoLog(value = "流程列表-编辑")
	@ApiOperation(value="流程列表-编辑", notes="流程列表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CkuActivitiList ckuActivitiList) {
		ckuActivitiListService.updateById(ckuActivitiList);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "流程列表-通过id删除")
	@ApiOperation(value="流程列表-通过id删除", notes="流程列表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckuActivitiListService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "流程列表-批量删除")
	@ApiOperation(value="流程列表-批量删除", notes="流程列表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckuActivitiListService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "流程列表-通过id查询")
	@ApiOperation(value="流程列表-通过id查询", notes="流程列表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CkuActivitiList ckuActivitiList = ckuActivitiListService.getById(id);
		return Result.OK(ckuActivitiList);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckuActivitiList
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CkuActivitiList ckuActivitiList) {
      return super.exportXls(request, ckuActivitiList, CkuActivitiList.class, "流程列表");
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
      return super.importExcel(request, response, CkuActivitiList.class);
  }
  
  /**
   * 工作流上传图片
   * @param request
   * @param response
   * @return
 * @throws FileNotFoundException 
   */
  @PostMapping(value = "/upload")
  public Result<?> upload(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
	  
      Result<?> result = new Result<>();
      String savePath = "";
      String bizPath = request.getParameter("biz");
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      MultipartFile file = multipartRequest.getFile("file");// 获取上传文件对象
      if(oConvertUtils.isEmpty(bizPath)){
          if(CommonConstant.UPLOAD_TYPE_OSS.equals(uploadType)){
              //未指定目录，则用阿里云默认目录 upload
              bizPath = "upload";
              //result.setMessage("使用阿里云文件上传时，必须添加目录！");
              //result.setSuccess(false);
              //return result;
          }else{
              bizPath = "";
          }
      }
      if(CommonConstant.UPLOAD_TYPE_LOCAL.equals(uploadType)){
          //针对jeditor编辑器如何使 lcaol模式，采用 base64格式存储
          String jeditor = request.getParameter("jeditor");
          if(oConvertUtils.isNotEmpty(jeditor)){
              result.setMessage(CommonConstant.UPLOAD_TYPE_LOCAL);
              result.setSuccess(true);
              return result;
          }else{
              savePath = this.uploadLocal(file,bizPath);
          }
      }else{
          //update-begin-author:taoyan date:20200814 for:文件上传改造
          savePath = CommonUtils.upload(file, bizPath, uploadType);
          //update-end-author:taoyan date:20200814 for:文件上传改造
      }
      if(oConvertUtils.isNotEmpty(savePath)){
          result.setMessage(savePath);
          result.setSuccess(true);
      }else {
          result.setMessage("上传失败！");
          result.setSuccess(false);
      }
      return result;
  }
  	
  /**
   * 本地文件上传
   * @param mf 文件
   * @param bizPath  自定义路径
   * @return
   */
  private String uploadLocal(MultipartFile mf,String bizPath){
      try {
    	  File pathFile = new File(ResourceUtils.getURL("classpath:").getPath());
          File fileUpload = new File(pathFile.getAbsolutePath(),"processes");
          String activitepath= fileUpload.getAbsolutePath();
          String ctxPath = activitepath;
          String fileName = null;
          File file = new File(ctxPath + File.separator + bizPath + File.separator );
          if (!file.exists()) {
              file.mkdirs();// 创建文件根目录
          }
          String orgName = mf.getOriginalFilename();// 获取文件名
          orgName = CommonUtils.getFileName(orgName);
          if(orgName.indexOf(".")!=-1){
              fileName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.indexOf("."));
          }else{
              fileName = orgName+ "_" + System.currentTimeMillis();
          }
          String savePath = file.getPath() + File.separator + fileName;
          File savefile = new File(savePath);
          FileCopyUtils.copy(mf.getBytes(), savefile);
          String dbpath = null;
          if(oConvertUtils.isNotEmpty(bizPath)){
              dbpath = bizPath + File.separator + fileName;
          }else{
              dbpath = fileName;
          }
          if (dbpath.contains("\\")) {
              dbpath = dbpath.replace("\\", "/");
          }
          return dbpath;
      } catch (IOException e) {
          log.error(e.getMessage(), e);
      }
      return "";
  }
  /**
   * 预览图片&下载文件
   * 请求地址：http://localhost:8080/common/static/{user/20190119/e1fe9925bc315c60addea1b98eb1cb1349547719_1547866868179.jpg}
   *
   * @param request
   * @param response
   */
  @GetMapping(value = "/static/**")
  public void view(HttpServletRequest request, HttpServletResponse response) {
      // ISO-8859-1 ==> UTF-8 进行编码转换
      String imgPath = extractPathFromPattern(request);
      if(oConvertUtils.isEmpty(imgPath) || imgPath=="null"){
          return;
      }
      // 其余处理略
      InputStream inputStream = null;
      OutputStream outputStream = null;
      try {
          imgPath = imgPath.replace("..", "");
          if (imgPath.endsWith(",")) {
              imgPath = imgPath.substring(0, imgPath.length() - 1);
          }
          File pathFile = new File(ResourceUtils.getURL("classpath:").getPath());
          File fileUpload = new File(pathFile.getAbsolutePath(),"processes");
          String activitepath= fileUpload.getAbsolutePath();
          String filePath = activitepath + File.separator + imgPath;
          File file = new File(filePath);
          if(!file.exists()){
              response.setStatus(404);
              throw new RuntimeException("文件不存在..");
          }
          response.setContentType("application/force-download");// 设置强制下载不打开
          response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"),"iso-8859-1"));
          inputStream = new BufferedInputStream(new FileInputStream(filePath));
          outputStream = response.getOutputStream();
          byte[] buf = new byte[1024];
          int len;
          while ((len = inputStream.read(buf)) > 0) {
              outputStream.write(buf, 0, len);
          }
          response.flushBuffer();
      } catch (IOException e) {
          log.error("预览文件失败" + e.getMessage());
          response.setStatus(404);
          e.printStackTrace();
      } finally {
          if (inputStream != null) {
              try {
                  inputStream.close();
              } catch (IOException e) {
                  log.error(e.getMessage(), e);
              }
          }
          if (outputStream != null) {
              try {
                  outputStream.close();
              } catch (IOException e) {
                  log.error(e.getMessage(), e);
              }
          }
      }

  }
  
  /**
   *  把指定URL后的字符串全部截断当成参数
   *  这么做是为了防止URL中包含中文或者特殊字符（/等）时，匹配不了的问题
   * @param request
   * @return
   */
  private static String extractPathFromPattern(final HttpServletRequest request) {
      String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
      String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
      return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
  }
  
	/**
	 * 流程-参数
	 * @param type		请求类型  1 开始节点   2办理节点
	 * @param id		流程id
	 * @return
	 */
	@AutoLog(value = "流程-参数")
	@ApiOperation(value="流程-参数", notes="流程-参数")
	@GetMapping(value = "/actParam")
	@ResponseBody
	public Result<?> actParam(HttpServletRequest request,@RequestParam(name="type",required=true) String type,
                              @RequestParam(name="id",required=false) String id,
                              @RequestParam(name="taskid",required=false) String taskid,
                              @RequestParam(name="actCode",required=false) String actCode) {

        String token = request.getHeader(CommonConstant.X_ACCESS_TOKEN);
        String username = JwtUtil.getUsername(token);

        Map<String,Object> dataMap = new HashMap();

        VwActTasklist tasklist=vwActTasklistService.query().eq("id",taskid).count()>1?
                vwActTasklistService.query().eq("id",taskid).eq("candidate",username).one():
                vwActTasklistService.getById(taskid);

        //不是流程启动 就通过参数自己获取 节点名称和流程名称
        if(!"1".equals(type)){
            actCode=tasklist.getProcDefId();
            actCode=actCode.substring(0,actCode.indexOf(":"));
        }

        CkuActivitiList ckuAct = ckuActivitiListService.getByActCode(actCode);

		if("propo_test".equals(actCode)) {
		    if(ckuAct.getServerName()==null){
                ckuAct.setServerName("testActProPoService");
            }
			if("1".equals(type)) {
				//开始
				//选人类型   1选人   2 选角色  0 不需要
				dataMap.put("chooseUserType", 1);//
				dataMap.put("userCode", "checks");//wf_approveUser_
				dataMap.put("businessService", ckuAct.getServerName());

				/*dataMap.put("", "");
				dataMap.put("", "");
				dataMap.put("", "");
				dataMap.put("", "");
				dataMap.put("", "");
				dataMap.put("", "");*/

			}else {
				if("check".equals(tasklist.getActId())) {
					//办理
					//选人类型   1选人   2 选角色  0 不需要
					dataMap.put("chooseUserType", 1);//
					dataMap.put("userCode", "approve");//wf_approveUser_
					dataMap.put("businessService", ckuAct.getServerName());
					//是否需要审批结论  1需要  0不需要
					dataMap.put("isResult", "1");
					//isComment 是否需要审批意见 1需要  0不需要
					dataMap.put("isComment", "1");
				}else {
					//办理
					//选人类型   1选人   2 选角色  0 不需要
					dataMap.put("chooseUserType", 0);//
					//dataMap.put("userCode", "check");//wf_approveUser_
					dataMap.put("businessService", ckuAct.getServerName());
					//是否需要审批结论  1需要  0不需要
					dataMap.put("isResult", "1");
					//isComment 是否需要审批意见 1需要  0不需要
					dataMap.put("isComment", "1");
				}
			}
		}

        if("order_approval_military".equals(actCode)) {
            if(ckuAct.getServerName()==null) {
                ckuAct.setServerName("orderApprovalMilitary");
            }
            String[] ids=id.split(",");

            ActServiceI actservice= (ActServiceI) ApplicationContextUtil.getContext().getBean("orderApprovalMilitary");
            List<Map> relist=new ArrayList<>();
            for(String nextid:ids){
                if(StrUtil.isEmpty(nextid)){
                    continue;
                }
                dataMap = new HashMap();

                Map map= new HashMap();
                map.put("type",type);
                map.put("id",nextid);
                if(!"1".equals(type)){
                    map.put("nodename",tasklist.getActId());
                }


                dataMap.put("users", actservice.getUsers(map));

                if("1".equals(type)) {
                    //开始
                    //选人类型   1选人   2 选角色  0 不需要
                    dataMap.put("chooseUserType", 1);//
                    dataMap.put("userCode", "hq_users");//wf_approveUser_
                    dataMap.put("businessService", ckuAct.getServerName());
                    dataMap.put("wfkey", "order_approval_military");
                    dataMap.put("businessKey", nextid);

                }else {
                    if("Led_sign".equals(tasklist.getActId())) {
                        //办理
                        //选人类型   1选人   2 选角色  0 不需要
                        dataMap.put("chooseUserType", 2);//
                        dataMap.put("userCode", "group_gyrole");//wf_approveUser_
                        dataMap.put("businessService", ckuAct.getServerName());
                        //是否需要审批结论  1需要  0不需要
                        dataMap.put("isResult", "1");
                        //isComment 是否需要审批意见 1需要  0不需要
                        dataMap.put("isComment", "1");

                    }else if("update_contract".equals(tasklist.getActId())) {
                        //办理
                        //选人类型   1选人   2 选角色  0 不需要
                        dataMap.put("chooseUserType", 1);//
                        dataMap.put("userCode", "hq_users");//wf_approveUser_
                        dataMap.put("businessService", ckuAct.getServerName());

                    }else {
                        //办理
                        //选人类型   1选人   2 选角色  0 不需要
                        dataMap.put("chooseUserType", 0);//
                        //dataMap.put("userCode", "check");//wf_approveUser_
                        dataMap.put("businessService", ckuAct.getServerName());
                        //是否需要审批结论  1需要  0不需要
                        dataMap.put("isResult", "1");
                        //isComment 是否需要审批意见 1需要  0不需要
                        dataMap.put("isComment", "1");
                    }
                }
                relist.add(dataMap);
            }
            return Result.OK(relist);
        }

        if("order_approval".equals(actCode)) {
            if(ckuAct.getServerName()==null) {
                ckuAct.setServerName("orderApproval");
            }
            String[] ids=id.split(",");

            ActServiceI actservice= (ActServiceI) ApplicationContextUtil.getContext().getBean("orderApproval");
            List<Map> relist=new ArrayList<>();
            for(String nextid:ids){
                if(StrUtil.isEmpty(nextid)){
                    continue;
                }
                dataMap = new HashMap();

                Map map= new HashMap();
                map.put("type",type);
                map.put("id",nextid);
                if(!"1".equals(type)){
                    map.put("nodename",tasklist.getActId());
                }

                dataMap.put("users", actservice.getUsers(map));

                if("1".equals(type)) {
                    //开始
                    //选人类型   1选人   2 选角色  0 不需要
                    dataMap.put("chooseUserType", 1);//
                    dataMap.put("userCode", "hq_users");//wf_approveUser_
                    dataMap.put("businessService", ckuAct.getServerName());
                    dataMap.put("wfkey", "order_approval");
                    dataMap.put("businessKey", nextid);

                }else {
                    if("user_sign".equals(tasklist.getActId())) {
                        //办理
                        //选人类型   1选人   2 选角色  0 不需要
                        dataMap.put("chooseUserType", 2);//
                        dataMap.put("userCode", "group_gyrole");//wf_approveUser_
                        dataMap.put("businessService", ckuAct.getServerName());
                        //是否需要审批结论  1需要  0不需要
                        dataMap.put("isResult", "1");
                        //isComment 是否需要审批意见 1需要  0不需要
                        dataMap.put("isComment", "1");
                    }else if("update_contract".equals(tasklist.getActId())) {
                        //办理
                        //选人类型   1选人   2 选角色  0 不需要
                        dataMap.put("chooseUserType", 1);//
                        dataMap.put("userCode", "hq_users");//wf_approveUser_
                        dataMap.put("businessService", ckuAct.getServerName());

                    }else {
                        //办理
                        //选人类型   1选人   2 选角色  0 不需要
                        dataMap.put("chooseUserType", 0);//
                        //dataMap.put("userCode", "check");//wf_approveUser_
                        dataMap.put("businessService", ckuAct.getServerName());
                        //是否需要审批结论  1需要  0不需要
                        dataMap.put("isResult", "1");
                        //isComment 是否需要审批意见 1需要  0不需要
                        dataMap.put("isComment", "1");
                    }
                }
                relist.add(dataMap);
            }
            return Result.OK(relist);
        }

        if("order_modify_approval_military".equals(actCode)) {
            if(ckuAct.getServerName()==null) {
                ckuAct.setServerName("orderModifyApprovalMilitary");
            }
            String[] ids=id.split(",");

            ActServiceI actservice= (ActServiceI) ApplicationContextUtil.getContext().getBean("orderModifyApprovalMilitary");
            List<Map> relist=new ArrayList<>();
            for(String nextid:ids){
                if(StrUtil.isEmpty(nextid)){
                    continue;
                }
                dataMap = new HashMap();

                Map map= new HashMap();
                map.put("type",type);
                map.put("id",nextid);
                if(!"1".equals(type)){
                    map.put("nodename",tasklist.getActId());
                }


                dataMap.put("users", actservice.getUsers(map));

                if("1".equals(type)) {
                    //开始
                    //选人类型   1选人   2 选角色  0 不需要
                    dataMap.put("chooseUserType", 1);//
                    dataMap.put("userCode", "hq_users");//wf_approveUser_
                    dataMap.put("businessService", ckuAct.getServerName());
                    dataMap.put("wfkey", "order_modify_approval_military");
                    dataMap.put("businessKey", nextid);

                }else {
                    if("Led_sign".equals(tasklist.getActId())) {
                        //办理
                        //选人类型   1选人   2 选角色  0 不需要
                        dataMap.put("chooseUserType", 2);//
                        dataMap.put("userCode", "group_gyrole");//wf_approveUser_
                        dataMap.put("businessService", ckuAct.getServerName());
                        //是否需要审批结论  1需要  0不需要
                        dataMap.put("isResult", "1");
                        //isComment 是否需要审批意见 1需要  0不需要
                        dataMap.put("isComment", "1");

                    }else if("update_contract".equals(tasklist.getActId())) {
                        //办理
                        //选人类型   1选人   2 选角色  0 不需要
                        dataMap.put("chooseUserType", 1);//
                        dataMap.put("userCode", "hq_users");//wf_approveUser_
                        dataMap.put("businessService", ckuAct.getServerName());

                    }else {
                        //办理
                        //选人类型   1选人   2 选角色  0 不需要
                        dataMap.put("chooseUserType", 0);//
                        //dataMap.put("userCode", "check");//wf_approveUser_
                        dataMap.put("businessService", ckuAct.getServerName());
                        //是否需要审批结论  1需要  0不需要
                        dataMap.put("isResult", "1");
                        //isComment 是否需要审批意见 1需要  0不需要
                        dataMap.put("isComment", "1");
                    }
                }
                relist.add(dataMap);
            }
            return Result.OK(relist);
        }

        if("order_modify_approval".equals(actCode)) {
            if(ckuAct.getServerName()==null) {
                ckuAct.setServerName("orderModifyApproval");
            }
            String[] ids=id.split(",");

            ActServiceI actservice= (ActServiceI) ApplicationContextUtil.getContext().getBean("orderModifyApproval");
            List<Map> relist=new ArrayList<>();
            for(String nextid:ids){
                if(StrUtil.isEmpty(nextid)){
                    continue;
                }
                dataMap = new HashMap();

                Map map= new HashMap();
                map.put("type",type);
                map.put("id",nextid);
                if(!"1".equals(type)){
                    map.put("nodename",tasklist.getActId());
                }

                dataMap.put("users", actservice.getUsers(map));

                if("1".equals(type)) {
                    //开始
                    //选人类型   1选人   2 选角色  0 不需要
                    dataMap.put("chooseUserType", 1);//
                    dataMap.put("userCode", "hq_users");//wf_approveUser_
                    dataMap.put("businessService", ckuAct.getServerName());
                    dataMap.put("wfkey", "order_modify_approval");
                    dataMap.put("businessKey", nextid);

                }else {
                    if("user_sign".equals(tasklist.getActId())) {
                        //办理
                        //选人类型   1选人   2 选角色  0 不需要
                        dataMap.put("chooseUserType", 2);//
                        dataMap.put("userCode", "group_gyrole");//wf_approveUser_
                        dataMap.put("businessService", ckuAct.getServerName());
                        //是否需要审批结论  1需要  0不需要
                        dataMap.put("isResult", "1");
                        //isComment 是否需要审批意见 1需要  0不需要
                        dataMap.put("isComment", "1");
                    }else if("update_contract".equals(tasklist.getActId())) {
                        //办理
                        //选人类型   1选人   2 选角色  0 不需要
                        dataMap.put("chooseUserType", 1);//
                        dataMap.put("userCode", "hq_users");//wf_approveUser_
                        dataMap.put("businessService", ckuAct.getServerName());

                    }else {
                        //办理
                        //选人类型   1选人   2 选角色  0 不需要
                        dataMap.put("chooseUserType", 0);//
                        //dataMap.put("userCode", "check");//wf_approveUser_
                        dataMap.put("businessService", ckuAct.getServerName());
                        //是否需要审批结论  1需要  0不需要
                        dataMap.put("isResult", "1");
                        //isComment 是否需要审批意见 1需要  0不需要
                        dataMap.put("isComment", "1");
                    }
                }
                relist.add(dataMap);
            }
            return Result.OK(relist);
        }

        //典型工艺审批
        if("bas_typical_proc".equals(actCode)) {

            log.info("典型工艺审批--->");

            if(ckuAct.getServerName()==null) {
                ckuAct.setServerName("basTypicalProcServiceImpl");
            }

            if("1".equals(type)) {
                //开始
                //选人类型   1选人   2 选角色  0 不需要
                dataMap.put("chooseUserType", 1);//
                dataMap.put("userCode", "dept_leader");//wf_approveUser_
                dataMap.put("businessService", ckuAct.getServerName());

				/*dataMap.put("", "");
				dataMap.put("", "");
				dataMap.put("", "");
				dataMap.put("", "");
				dataMap.put("", "");
				dataMap.put("", "");*/

            }else {

                //办理
                //选人类型   1选人   2 选角色  0 不需要
                dataMap.put("chooseUserType", 0);//
                dataMap.put("businessService", ckuAct.getServerName());
                //是否需要审批结论  1需要  0不需要
                dataMap.put("isResult", "1");
                //isComment 是否需要审批意见 1需要  0不需要
                dataMap.put("isComment", "1");

            }
        }

        //产品指标审批
        if("prod_series".equals(actCode)) {

            log.info("产品指标审批--->");

            if(ckuAct.getServerName()==null) {
                ckuAct.setServerName("prodSeriesServiceImpl");
            }

            if("1".equals(type)) {
                //开始
                //选人类型   1选人   2 选角色  0 不需要
                dataMap.put("chooseUserType", 1);//
                dataMap.put("userCode", "dept_leader");//wf_approveUser_
                dataMap.put("businessService", ckuAct.getServerName());

				/*dataMap.put("", "");
				dataMap.put("", "");
				dataMap.put("", "");
				dataMap.put("", "");
				dataMap.put("", "");
				dataMap.put("", "");*/

            }else {

                //办理
                //选人类型   1选人   2 选角色  0 不需要
                dataMap.put("chooseUserType", 0);//
                dataMap.put("businessService", ckuAct.getServerName());
                //是否需要审批结论  1需要  0不需要
                dataMap.put("isResult", "1");
                //isComment 是否需要审批意见 1需要  0不需要
                dataMap.put("isComment", "1");

            }
        }


		return Result.OK(dataMap);
	}

}
