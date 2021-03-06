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
	 * ??????????????????
	 *
	 * @param basMaterialType
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "bas_material_type-??????????????????")
	@ApiOperation(value="bas_material_type-??????????????????", notes="bas_material_type-??????????????????")
	@GetMapping(value = "/rootList")
	public Result<?> queryPageList(BasMaterialType basMaterialType,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {

			/*
			????????????????????????????????????????????????
			 */
            String parentId = basMaterialType.getPid();
			if (oConvertUtils.isEmpty(parentId)) {
				parentId = "0";
			}
            basMaterialType.setPid(null);
			//???????????????????????????*???
			/*if(!StrUtil.hasEmpty(basMaterialType.getTypeName())){
				basMaterialType.setTypeName("*"+basMaterialType.getTypeName()+"*");
			}*/

            QueryWrapper<BasMaterialType> queryWrapper = QueryGenerator.initQueryWrapper(basMaterialType, req.getParameterMap());
			//Lambda ??????????????????final
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
	  *   ??????
	  *
	  * @return
	  */
	 @AutoLog(value = "bas_material_type-??????")
	 @ApiOperation(value="bas_material_type-??????", notes="bas_material_type-??????")
	 @PostMapping(value = "/add")
	 public Result<?> add(HttpServletRequest request,@RequestBody BasMaterialTypeVO basMaterialTypeVO) {
		 String id= IdUtil.simpleUUID();
		 //????????????????????????????????????
		 basMaterialTypeVO.setId(id);
		 BasMaterialType basMaterialType =new BasMaterialType();
		 BeanUtils.copyProperties(basMaterialTypeVO, basMaterialType);

		 //???????????????:0?????????,1????????????
		 if(StrUtil.hasEmpty(basMaterialType.getPid())||"0".equals(basMaterialType.getPid())){
			 basMaterialType.setIsRoot("0");
		 }else{
			 basMaterialType.setIsRoot("1");
		 }

		 //??????????????????
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


		 //??????????????????
		 basMaterialTypeService.addBasMaterialType(basMaterialType);
		 return Result.OK("???????????????");
	 }

	 /**
	  *  ??????
	  *
	  * @return
	  */
	 @AutoLog(value = "bas_material_type-??????")
	 @ApiOperation(value="bas_material_type-??????", notes="bas_material_type-??????")
	 @PutMapping(value = "/edit")
	 public Result<?> edit(@RequestBody BasMaterialTypeVO basMaterialTypeVO) {

		 //????????????????????????????????????
		 BasMaterialType basMaterialType =new BasMaterialType();
		 BeanUtils.copyProperties(basMaterialTypeVO, basMaterialType);
		 basMaterialTypeService.updateBasMaterialType(basMaterialType);

		 ExtUtils.AddJointExt(ext_id,basMaterialTypeVO);

		 return Result.OK("????????????!");
	 }




	 /**
	  *  ????????????
	  *
	  * @return
	  */
	 @AutoLog(value = "bas_material_type-????????????")
	 @ApiOperation(value="bas_material_type-????????????", notes="bas_material_type-????????????")
	 @PutMapping(value = "/edit_status")
	 @Transactional
	 public Result<?> edit_status(@RequestParam("id") String id,@RequestParam("state") Integer state) {

		 //????????????????????????????????????
		 BasMaterialType  update= basMaterialTypeService.getById(id);
		 update.setId(id);
		 update.setState(state);
		 basMaterialTypeService.updateBasMaterialType(update);
		 return Result.OK("????????????!");
	 }
	
	/**
	 *   ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "bas_material_type-??????id??????")
	@ApiOperation(value="bas_material_type-??????id??????", notes="bas_material_type-??????id??????")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basMaterialTypeService.deleteBasMaterialType(id);
		return Result.OK("????????????!");
	}
	
	/**
	 *  ????????????
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "bas_material_type-????????????")
	@ApiOperation(value="bas_material_type-????????????", notes="bas_material_type-????????????")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basMaterialTypeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("?????????????????????");
	}


	 /**
	  * ????????????????????????
	  *
	  * @return
	  */
	 @AutoLog(value = "????????????????????????")
	 @ApiOperation(value="????????????????????????", notes="????????????????????????")
	 @GetMapping(value = "/checkTypeCode")
	 public Result<?> checkTypeCode(@RequestParam(value="pid", required = false) String pid,@RequestParam(name="typeCode") String typeCode) {


		 if(pid==null||"".equals(pid)){
			 pid="0";
		 }
		 BasMaterialType parent=basMaterialTypeService.getById(pid);
		 String ptypecode=(parent==null?"":parent.getTypeCode());
		 if(parent==null&&!"0".equals(pid)){
			 return Result.error("?????????????????????????????????");
		 }

		 int count=basMaterialTypeService.query().eq("pid",pid).eq("type_code",ptypecode+typeCode).count();

		 if(count>0){
			 return Result.error("??????????????????,???????????????");
		 }else{
			 return Result.OK("????????????");
		 }

	 }

	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "bas_material_type-??????id??????")
	@ApiOperation(value="bas_material_type-??????id??????", notes="bas_material_type-??????id??????")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasMaterialType basMaterialType = basMaterialTypeService.getById(id);
		if(basMaterialType==null) {
			return Result.error("?????????????????????");
		}
		return Result.OK(basMaterialType);
	}


	 /**
	  * ??????id??????
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
    * ??????excel
    *
    * @param request
    * @param basMaterialType
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BasMaterialType basMaterialType) {
		return super.exportXls(request, basMaterialType, BasMaterialType.class, "bas_material_type");
    }

    /**
      * ??????excel????????????
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
