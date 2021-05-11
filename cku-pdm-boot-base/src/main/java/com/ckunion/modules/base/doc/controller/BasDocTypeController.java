package com.ckunion.modules.base.doc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.base.doc.entity.BasDocType;
import com.ckunion.modules.base.doc.service.IBasDocTypeService;
import com.ckunion.modules.base.entity.Tree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description: 文档类型
 * @Author: jeecg-boot
 * @Date:   2021-03-10
 * @Version: V1.0
 */
@Api(tags="文档类型")
@RestController
@RequestMapping("/base/basDocType")
@Slf4j
public class BasDocTypeController extends JeecgController<BasDocType, IBasDocTypeService> {
	@Autowired
	private IBasDocTypeService basDocTypeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param basDocType
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "文档类型-分页列表查询")
	@ApiOperation(value="文档类型-分页列表查询", notes="文档类型-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasDocType basDocType,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasDocType> queryWrapper = QueryGenerator.initQueryWrapper(basDocType, req.getParameterMap());
		Page<BasDocType> page = new Page<BasDocType>(pageNo, pageSize);
		IPage<BasDocType> pageList = basDocTypeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}



	 /**
	  * 文档类型树查询
	  *
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "文档类型-分页列表查询")
	 @ApiOperation(value="文档类型-分页列表查询", notes="文档类型-分页列表查询")
	 @GetMapping(value = "/treeList")
	 public Result<?> treeList(HttpServletRequest req) {
		 List<BasDocType> list = basDocTypeService.query().eq("is_root","1").list();

		 List<Tree> objects = new ArrayList<>();
		 for (BasDocType doc : list) {
			 Tree childNode = new Tree();
			 childNode.setKey(doc.getId());
			 childNode.setCode(doc.getDocCode());
			 childNode.setPid(doc.getPid());
			 childNode.setTitle(doc.getDocName());
			 objects.add(childNode);
		 }
		 getTree(objects);
		 return Result.OK(objects);
	 }




	private void getTree(List<Tree> parentList){
		for (Tree tree : parentList) {

			List<BasDocType> children = basDocTypeService.query().eq("pid",tree.getCode()).list();
			if (children != null && children.size() > 0) {
				List<Tree> objects = new ArrayList<>();
				for (BasDocType child : children) {
					Tree childNode = new Tree();
					childNode.setKey(child.getId());
					childNode.setPid(child.getPid());
					childNode.setCode(child.getDocCode());
					childNode.setTitle(child.getDocName());
					objects.add(childNode);
				}
				tree.setChildren(objects);
				parentList = objects;
				getTree(parentList);
			}
		}
	}

	/**
	 *   添加
	 *
	 * @param basDocType
	 * @return
	 */
	@AutoLog(value = "文档类型-添加")
	@ApiOperation(value="文档类型-添加", notes="文档类型-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasDocType basDocType) {

		int maxTypeCode = 0;
		String parentCode = "";
		if(StringUtils.isNotEmpty(basDocType.getPid())){
			basDocType.setIsRoot("0");
			basDocType.setPid("0");
			BasDocType parent = basDocTypeService.getById(basDocType.getPid());
			int parentCodeLength = parent.getDocCode().length();
			Map<String, Object> maxCodeMap = basDocTypeService.getMaxCode(basDocType.getPid(), parentCodeLength);
			maxTypeCode = Integer.parseInt(maxCodeMap.get("MAXCODE") + "")+1;

			parentCode = parent.getDocCode();
		}else{
			basDocType.setIsRoot("1");
			Map<String, Object> maxCodeMap = basDocTypeService.getMaxCode("", 0);
			maxTypeCode = Integer.parseInt(maxCodeMap.get("MAXCODE") + "")+1;
		}
		String docCode = "001";
		if(maxTypeCode < 10){
			docCode = parentCode+"00"+maxTypeCode;
		}else if (maxTypeCode < 100){
			docCode = parentCode+"0"+maxTypeCode;
		}else{
			docCode = parentCode+maxTypeCode;
		}
		basDocType.setDocCode(docCode);
		basDocType.setNodeId(docCode);
		basDocTypeService.save(basDocType);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param basDocType
	 * @return
	 */
	@AutoLog(value = "文档类型-编辑")
	@ApiOperation(value="文档类型-编辑", notes="文档类型-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasDocType basDocType) {
		basDocTypeService.updateById(basDocType);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文档类型-通过id删除")
	@ApiOperation(value="文档类型-通过id删除", notes="文档类型-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basDocTypeService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "文档类型-批量删除")
	@ApiOperation(value="文档类型-批量删除", notes="文档类型-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basDocTypeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文档类型-通过id查询")
	@ApiOperation(value="文档类型-通过id查询", notes="文档类型-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasDocType basDocType = basDocTypeService.getById(id);
		if(basDocType==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basDocType);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param basDocType
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasDocType basDocType) {
        return super.exportXls(request, basDocType, BasDocType.class, "文档类型");
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
        return super.importExcel(request, response, BasDocType.class);
    }

}
