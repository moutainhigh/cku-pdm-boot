package com.ckunion.modules.materials.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.materials.entity.BasMaterialType;
import com.ckunion.modules.base.entity.Tree;
import com.ckunion.modules.materials.service.IBasMaterialInfoService;
import com.ckunion.modules.materials.service.IBasMaterialTypeService;
import com.ckunion.modules.materials.vo.BasMaterialTypeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.service.IAdminFieldService;
import org.jeecg.modules.system.service.IAdminFieldSortService;
import org.jeecg.modules.system.service.IAdminFieldValueService;
import org.jeecg.modules.system.util.ExtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

 /**
 * @Description: bas_material_type
 * @Author: jeecg-boot
 * @Date:   2020-10-16
 * @Version: V1.0
 */
@Api(tags="bas_material_type")
@RestController
@RequestMapping("/basMaterialType")
@Slf4j
public class BasMaterialTypeController extends JeecgController<BasMaterialType, IBasMaterialTypeService>{

	 @Autowired
	 private IBasMaterialTypeService basMaterialTypeService;
	 @Autowired
	 private IBasMaterialInfoService basMaterialInfoService;
	 @Autowired
	 private IAdminFieldService adminFieldService;
	 @Autowired
	 private IAdminFieldSortService adminFieldSortService;
	 @Autowired
	 private IAdminFieldValueService adminFieldValueService;



	 public String ext_id="BasMaterialTypeList";

	/**
	 * 分页列表查询
	 *
	 * @param basMaterialType
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "bas_material_type-分页列表查询")
	@ApiOperation(value="bas_material_type-分页列表查询", notes="bas_material_type-分页列表查询")
	@GetMapping(value = "/rootList")
	public Result<?> queryPageList(BasMaterialType basMaterialType,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {

			/*
			修改默认为查询本级及下级数据内容
			 */
            String parentId = basMaterialType.getPid();
			if (oConvertUtils.isEmpty(parentId)) {
				parentId = "0";
			}
            basMaterialType.setPid(null);
			//模糊查询在前后添加*号
			/*if(!StrUtil.hasEmpty(basMaterialType.getTypeName())){
				basMaterialType.setTypeName("*"+basMaterialType.getTypeName()+"*");
			}*/

            QueryWrapper<BasMaterialType> queryWrapper = QueryGenerator.initQueryWrapper(basMaterialType, req.getParameterMap());
			//Lambda 表达式必须用final
			String bb=parentId;
//			queryWrapper.like("type_name",basMaterialType.getTypeName());
			queryWrapper.and(qw->qw.eq("pid", bb).or().eq("id",bb));
			queryWrapper.orderByAsc("id_class").orderByAsc("type_code");


            Page<BasMaterialType> page = new Page<BasMaterialType>(pageNo, pageSize);
            IPage<BasMaterialType> pageList = basMaterialTypeService.page(page, queryWrapper);



            //return Result.OK(ExtUtils.SelectJointExt(ext_id,pageList,BasMaterialTypeVO.class));
            return Result.OK(pageList);
	}

	 /**
	  *   添加
	  *
	  * @return
	  */
	 @AutoLog(value = "bas_material_type-添加")
	 @ApiOperation(value="bas_material_type-添加", notes="bas_material_type-添加")
	 @PostMapping(value = "/add")
	 public Result<?> add(HttpServletRequest request,@RequestBody BasMaterialTypeVO basMaterialTypeVO) {
		 String id= IdUtil.simpleUUID();
		 //增加一个获取附加参数的类
		 basMaterialTypeVO.setId(id);
		 BasMaterialType basMaterialType =new BasMaterialType();
		 BeanUtils.copyProperties(basMaterialTypeVO, basMaterialType);

		 //是否根节点:0根节点,1非根节点
		 if(StrUtil.hasEmpty(basMaterialType.getPid())||"0".equals(basMaterialType.getPid())){
			 basMaterialType.setIsRoot("0");
		 }else{
			 basMaterialType.setIsRoot("1");
		 }

		 //后台处理字段
		 BasMaterialType parent = null;
		 if(basMaterialTypeVO.getPid()!=null) {
		 	parent = basMaterialTypeService.getById(basMaterialTypeVO.getPid());
		 }

		 //node_id
		 String node="";
		 if("0".equals(basMaterialType.getIsRoot())){
		 	 /*Map friend=basMaterialTypeService.queryBroConfig("0");
			 if(friend==null){
				 node="001";
			 }else{
				 node=friend.get("node_id")+"";
			 }*/
			 node="001";
			 basMaterialType.setNodeId(node);
		 }else{
		 	 Map friend=basMaterialTypeService.queryBroConfig(basMaterialTypeVO.getPid());
		 	 if(friend==null){
				 node="001";
			 }else{
				 node=friend.get("NODE_ID")+"";
			 }
		 	 basMaterialType.setNodeId(parent.getNodeId()+node);
		 }
		 //id_class
		 if("0".equals(basMaterialType.getIsRoot())){
			 basMaterialType.setIdClass(1);
		 }else{
			 basMaterialType.setIdClass(parent.getIdClass()+1);
		 }
		 //type_code
		 if(basMaterialTypeVO.getTypeCode()!=null&&!"null".equals(basMaterialTypeVO.getTypeCode())){
			 basMaterialType.setTypeCode(parent.getTypeCode()+basMaterialTypeVO.getTypeCode());
		 }


		 ExtUtils.AddJointExt(ext_id,basMaterialTypeVO);


		 //拓展字段结束
		 basMaterialTypeService.addBasMaterialType(basMaterialType);
		 return Result.OK("添加成功！");
	 }

	 /**
	  *  编辑
	  *
	  * @return
	  */
	 @AutoLog(value = "bas_material_type-编辑")
	 @ApiOperation(value="bas_material_type-编辑", notes="bas_material_type-编辑")
	 @PutMapping(value = "/edit")
	 public Result<?> edit(@RequestBody BasMaterialTypeVO basMaterialTypeVO) {

		 //增加一个获取附加参数的类
		 BasMaterialType basMaterialType =new BasMaterialType();
		 BeanUtils.copyProperties(basMaterialTypeVO, basMaterialType);
		 basMaterialTypeService.updateBasMaterialType(basMaterialType);

		 ExtUtils.AddJointExt(ext_id,basMaterialTypeVO);

		 return Result.OK("编辑成功!");
	 }




	 /**
	  *  修改状态
	  *
	  * @return
	  */
	 @AutoLog(value = "bas_material_type-状态修改")
	 @ApiOperation(value="bas_material_type-状态修改", notes="bas_material_type-状态修改")
	 @PutMapping(value = "/edit_status")
	 @Transactional
	 public Result<?> edit_status(@RequestParam("id") String id,@RequestParam("state") Integer state) {

		 //增加一个获取附加参数的类
		 BasMaterialType  update= basMaterialTypeService.getById(id);
		 update.setId(id);
		 update.setState(state);
		 basMaterialTypeService.updateBasMaterialType(update);
		 return Result.OK("编辑成功!");
	 }
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "bas_material_type-通过id删除")
	@ApiOperation(value="bas_material_type-通过id删除", notes="bas_material_type-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basMaterialTypeService.deleteBasMaterialType(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "bas_material_type-批量删除")
	@ApiOperation(value="bas_material_type-批量删除", notes="bas_material_type-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basMaterialTypeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}


	 /**
	  * 类型编码重复验证
	  *
	  * @return
	  */
	 @AutoLog(value = "类型编码重复验证")
	 @ApiOperation(value="类型编码重复验证", notes="类型编码重复验证")
	 @GetMapping(value = "/checkTypeCode")
	 public Result<?> checkTypeCode(@RequestParam(value="pid", required = false) String pid,@RequestParam(name="typeCode") String typeCode) {


		 if(pid==null||"".equals(pid)){
			 pid="0";
		 }
		 BasMaterialType parent=basMaterialTypeService.getById(pid);
		 String ptypecode=(parent==null?"":parent.getTypeCode());
		 if(parent==null&&!"0".equals(pid)){
			 return Result.error("请检查父级节点是否存在");
		 }

		 int count=basMaterialTypeService.query().eq("pid",pid).eq("type_code",ptypecode+typeCode).count();

		 if(count>0){
			 return Result.error("重复验证失败,请重新填写");
		 }else{
			 return Result.OK("验证成功");
		 }

	 }

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "bas_material_type-通过id查询")
	@ApiOperation(value="bas_material_type-通过id查询", notes="bas_material_type-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasMaterialType basMaterialType = basMaterialTypeService.getById(id);
		if(basMaterialType==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(basMaterialType);
	}


	 /**
	  * 通过id查询
	  *
	  * @return
	  */
	 @AutoLog(value = "bas_material_type-tree")
	 @ApiOperation(value="bas_material_type-tree", notes="bas_material_type-tree")
	 @GetMapping(value = "/typeTree")
	 public Result<?> typeTree() {

		 List<BasMaterialType> list = basMaterialTypeService.query().list();
				 //.comment("id")
				 //.comment("pid")
				 //.comment("type_name").list();

		 return Result.OK(getTree("0",list));
	 }


	public List<Tree> getTree(String Pid,List<BasMaterialType> list){

		List<Tree> treelist=new ArrayList<>();

		for(BasMaterialType one:list){
			if(Pid.equals(one.getPid())){
				Tree tree1=new Tree();
				tree1.setKey(one.getId());
				tree1.setTitle(one.getTypeName());
				tree1.setPid(one.getPid());
				tree1.setChildren(getTree(one.getId(),list));
				treelist.add(tree1);
			}
		}
		if(treelist.size()==0){
			return new ArrayList<Tree>();
		}

	 	return treelist;
	}



    /**
    * 导出excel
    *
    * @param request
    * @param basMaterialType
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasMaterialType basMaterialType) {
		return super.exportXls(request, basMaterialType, BasMaterialType.class, "bas_material_type");
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
		return super.importExcel(request, response, BasMaterialType.class);
    }

}
