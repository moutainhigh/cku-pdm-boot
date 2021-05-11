package com.ckunion.modules.series.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.activiti.service.ActServiceI;
import com.ckunion.activiti.vo.ActModel;
import com.ckunion.constant.ProConstant;
import com.ckunion.modules.constants.BasConstant;
import com.ckunion.modules.materials.entity.BasMaterialInfo;
import com.ckunion.modules.series.entity.ProdSeries;
import com.ckunion.modules.series.entity.ProdSeriesField;
import com.ckunion.modules.series.mapper.ProdSeriesFieldMapper;
import com.ckunion.modules.series.mapper.ProdSeriesMapper;
import com.ckunion.modules.series.mapper.VwProductSeriesMappingMapper;
import com.ckunion.modules.series.service.IProdSeriesService;
import com.ckunion.productbom.entity.ProProductBom;
import com.ckunion.productbom.entity.ProProductInfo;
import com.ckunion.productbom.mapper.ProProductBomMapper;
import com.ckunion.productbom.mapper.ProProductInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.tomcat.jni.Proc;
import org.jeecg.common.system.vo.LoginUser;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jeecg.modules.system.mapper.SysDictMapper;

import com.ckunion.modules.series.entity.VwProductSeriesMapping;

/**
 * @Description: prod_series
 * @Author: jeecg-boot
 * @Date:   2021-04-06
 * @Version: V1.0
 */
@Slf4j
@Service
public class ProdSeriesServiceImpl extends ServiceImpl<ProdSeriesMapper, ProdSeries>
		implements IProdSeriesService, ActServiceI {

	@Autowired
	private ProdSeriesMapper prodSeriesMapper;
	@Autowired
	private ProdSeriesFieldMapper prodSeriesFieldMapper;
	@Autowired
	private VwProductSeriesMappingMapper vwProductSeriesMappingMapper;
	@Autowired
	private SysDictMapper sysDictMapper;
	@Autowired
	private ProProductBomMapper proProductBomMapper;
	@Autowired
	private ProProductInfoMapper proProductInfoMapper;



	public IPage<BasMaterialInfo> getMaterialBySeries(Page<BasMaterialInfo> page, QueryWrapper<BasMaterialInfo> queryWrapper){
		return prodSeriesMapper.getMaterialBySeries(page,queryWrapper);
	}


	@Override
	public String getFieldByModelId(String seriesId,String modelId) {

        //循环型号配置的《系列属性》集合
        List<VwProductSeriesMapping> mappList = vwProductSeriesMappingMapper.getSeriesMappingByTypeModelId(modelId);
        if(null == mappList || mappList.size()<1){
            return null;
        }

		StringBuilder columns = new StringBuilder();
		columns.append("[");
		for(VwProductSeriesMapping sm : mappList){

			StringBuilder builder = new StringBuilder();
			builder.append("{");

			//中文属性
			builder.append("title:'"+sm.getColumnLabel()+"',");
			//获取驼峰
			String tfName = changeTF(sm.getColumnName());
			//英文字段名称
			builder.append("key:'"+tfName+"',");

			//输入控件类型
			String htmlStr = "";
			if("execStandard".equals(tfName)) {
				//弹出选择方式，技术标准，根据型号+质量等级，需先选择质量等级

				builder.append("type:'FormTypes.popup',");
				builder.append("typeCode:'res_type5',");//报表模板配置code

			}else if("rangeTemperature".equals(tfName)) {
				//区间选择，工作温度范围
				htmlStr = getRangeTemperatureHtml(sm.getColumnLabel(), "minRange", "maxRange");
				builder.append(htmlStr);
			}else if("allowTempRange".equals(tfName)) {
				//区间选择，可工作温度范围
				htmlStr = getRangeTemperatureHtml(sm.getColumnLabel(), "allowTempRange", "allowTempRangeMax");
				builder.append(htmlStr);
			}else if("nominalFrequency".equals(tfName)) {//"nominalFrequency".equals(tfName)


				//区间选择（带有单位选择），标称频率 命名规则  nominalFrequency
				builder.append("type:'FormTypes.section2',");
				builder.append("inNumType:3,");//请输入数字且≥0   正则表达式/^[+]?(0|([1-9]\d*))(\.\d+)?$/g

			}else if("shuntCapacitance".equals(tfName)) {
				//区间选择，并电容
				htmlStr = getRangeTemperatureHtml(sm.getColumnLabel(), "shuntCapacitance", "shuntCapacitanceMax");
				builder.append(htmlStr);
			}else if(   "referenceTempStability".equals(tfName)) {
				//区间选择，基准温度初始精度  ，初始频率温度精度,  总频差,调整频差,温度频差，频率温度稳定性，额定负载，基准温度稳定性
				htmlStr = getRangeTemperatureHtml(sm.getColumnLabel(), tfName, tfName+"Max");
				builder.append(htmlStr);
			}else if("±".equals(sm.getSeriesRule())) {//"nominalFrequency".equals(tfName)
				//区间选择，标称频率 命名规则  nominalFrequency nominalFrequency
				htmlStr = getRangeTemperatureHtml(sm.getColumnLabel(), tfName, tfName+"Max");
				builder.append(htmlStr);
			}else if("2".equals(sm.getSeriesRule())) {
				//下拉单选，静态字典
				builder.append("type:'FormTypes.select',");
				builder.append("typeCode:'"+sm.getColumnDisplayParam()+"',");//数据字典code
			}else if("6".equals(sm.getSeriesRule())) {
				//下拉多选，字典类型 多选
				builder.append("type:'FormTypes.list_multi',");
				builder.append("typeCode:'"+tfName+"',");

			}else if("7".equals(sm.getSeriesRule())) {
				//下拉多选，字典类型 多选 可填写
				builder.append("type:'FormTypes.list_multi',");
				builder.append("typeCode:'"+tfName+"',");

			}else {
				//普通文本框
				builder.append("type:'FormTypes.input',");
			}

			//是否必填，=全部必填
			//if("1".equals(sm.getSeriesRequire())){
			//builder.append("validateRules:'[{ required: true, message: '${title}不能为空' }]',");
			//}

			//默认值
			String defaultValue="";
			if(StringUtils.isNotEmpty(seriesId)){
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("masteroid",seriesId);
				params.put("column_name",tfName);

				List<ProdSeriesField> fieldList = prodSeriesFieldMapper.selectByMap(params);
				if(null != fieldList && fieldList.size()>0){
					defaultValue = fieldList.get(0).getColumnVal();
				}
			}

			builder.append("defaultValue:'"+defaultValue+"'");

			builder.append("},");

			columns.append(builder);

		}

		columns = columns.deleteCharAt(columns.length()-1);

		columns.append("]");

		log.info("组合字符串="+columns);

		return columns.toString();

		//return columns.substring(0,columns.length()-1);//.toString();

	}

	//区间选择
	private String getRangeTemperatureHtml(String title,String minRange,String maxRange){
		StringBuilder builder =  new StringBuilder();
		if(title.contains("∞")) {
			builder.append("type:'FormTypes.section',");
			builder.append("inNumType:1,");//请输入数字或-∞   正则表达式/^[+-]?(0|([1-9]\d*)|[-∞])(\.\d+)?$/g
		}else if("temperatureDifference".equals(minRange)||"minRange".equals(minRange)||"allowTempRange".equals(minRange)){
			//温度频差、工作温度范围和可工作温度范围可以给负值，其他不可以
			builder.append("type:'FormTypes.section',");
			builder.append("inNumType:2,");//请输入数字   正则表达式/^[+-]?(0|([1-9]\d*))(\.\d+)?$/g
		}else {
			builder.append("type:'FormTypes.section',");
			builder.append("inNumType:3,");//请输入数字且≥0   正则表达式/^[+]?(0|([1-9]\d*))(\.\d+)?$/g
		}
		return builder.toString();
	}
	
	@Override
	@Transactional
	public void saveMain(ProdSeries prodSeries, Map<String,Object> fieldMap) {
		log.info("新增产品指标="+prodSeries);

		prodSeries.setSeriesName(getSeriesIdentifier(fieldMap, prodSeries.getTypemodelId()));
		prodSeries.setSerialNumber(getMaxSeriesNum());
        prodSeries.setState(BasConstant.PROD_SERIES_STATE_PREPA);
		prodSeries.setIsAddBom("0");

        if(null != fieldMap.get("execStandard")){
			prodSeries.setExecStandard(fieldMap.get("execStandard").toString());//技术标准
		}
        if(null != fieldMap.get("quaLevel")){
			prodSeries.setQuaLevel(fieldMap.get("quaLevel").toString());//质量等级
		}
		if(null != fieldMap.get("nominalFrequency")){
			prodSeries.setNominalFrequency(fieldMap.get("nominalFrequency").toString());//标称频率
		}
		prodSeriesMapper.insert(prodSeries);

		for(Map.Entry<String, Object> entry : fieldMap.entrySet()){
			ProdSeriesField sf = new ProdSeriesField();
			sf.setColumnName(entry.getKey());
			sf.setColumnVal(entry.getValue()==null ? "" : entry.getValue().toString());

			sf.setIsSection("N");
			if(entry.getKey().lastIndexOf("_begin")!=-1 || entry.getKey().lastIndexOf("_end")!=-1){
				sf.setIsSection("Y");
			}

			sf.setMasteroid(prodSeries.getId());//外键设置
			prodSeriesFieldMapper.insert(sf);

		}

		//判断添加的是分指标
		if(StringUtils.isNotEmpty(prodSeries.getParentoid())){
			//addBranchSeriesToBom(prodSeries);
		}

	}

	@Override
	@Transactional
	public void updateMain(ProdSeries prodSeries,Map<String,Object> fieldMap) {
		log.info("修改产品指标="+prodSeries);

		prodSeries.setSeriesName(getSeriesIdentifier(fieldMap,prodSeries.getTypemodelId()));

		if(null != fieldMap.get("execStandard")){
			prodSeries.setExecStandard(fieldMap.get("execStandard").toString());//技术标准
		}
		if(null != fieldMap.get("quaLevel")){
			prodSeries.setQuaLevel(fieldMap.get("quaLevel").toString());//质量等级
		}
		if(null != fieldMap.get("nominalFrequency")){
			prodSeries.setNominalFrequency(fieldMap.get("nominalFrequency").toString());//标称频率
		}


		prodSeriesMapper.updateById(prodSeries);
		
		//1.先删除子表数据
		prodSeriesFieldMapper.deleteByMainId(prodSeries.getId());

		
		//2.子表数据重新插入
		for(Map.Entry<String, Object> entry : fieldMap.entrySet()){
			ProdSeriesField sf = new ProdSeriesField();
			sf.setColumnName(entry.getKey());
			sf.setColumnVal(entry.getValue()==null ? "" : entry.getValue().toString());

			sf.setIsSection("N");
			if(entry.getKey().lastIndexOf("_begin")!=-1 || entry.getKey().lastIndexOf("_end")!=-1){
				sf.setIsSection("Y");
			}

			sf.setMasteroid(prodSeries.getId());//外键设置
			prodSeriesFieldMapper.insert(sf);

		}
	}


	@Override
	@Transactional
	public void delMain(String id) {
		prodSeriesFieldMapper.deleteByMainId(id);
		prodSeriesMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			prodSeriesFieldMapper.deleteByMainId(id.toString());
			prodSeriesMapper.deleteById(id);
		}
	}


	//组装系列名称
	private String getSeriesIdentifier(Map fieldMap,String modelId){

		//循环型号配置的《系列属性》集合
		List<VwProductSeriesMapping> mappList = vwProductSeriesMappingMapper.getSeriesMappingByTypeModelId(modelId);
		StringBuffer strBuf = new StringBuffer();
		for(VwProductSeriesMapping sm : mappList){

			//获取驼峰 属性名称
			String tfName = changeTF(sm.getColumnName());

			if("execStandard".equals(tfName)) {
				continue;
			}else if("quaLevel".equals(tfName)) {
				//质量等级
				String[] keys = fieldMap.get("quaLevel").toString().split(",");

				//这个方法返回乱码，好奇怪，没找到原因，也不输出查询语句
				//String itemText = sysDictMapper.getItemTextsByKeys("quaLevel",keys);

				StringBuilder tempDictText = new StringBuilder();
				for(String k : keys){
					String itemText = sysDictMapper.queryDictTextByKey("quaLevel",k);
					tempDictText.append(itemText+",");
				}
				if(null != tempDictText){
					tempDictText = tempDictText.deleteCharAt(tempDictText.length()-1);
					strBuf.append(tempDictText);
					strBuf.append(" ");
				}else{
					strBuf.append(" ");
				}


			}else if("rangeTemperature".equals(tfName)){
				//工作温度范围
				//strBuf.append(fieldMap.get("rangeTemperature_begin")  == null ? "" : fieldMap.get("minRange") );
				//strBuf.append("~");
				//strBuf.append(fieldMap.get("maxRange")  == null ? "" : fieldMap.get("maxRange"));
				strBuf.append(fieldMap.get("rangeTemperature"));
				strBuf.append("_");
			}else if("±".equals(sm.getSeriesRule())) {
				//判断匹配规则是不是±
				//（命名规则如标称频率   nominalFrequency nominalFrequencyMin nominalFrequencyMax） //标称平率
				//strBuf.append(fieldMap.get(tfName)  == null ? "" : fieldMap.get(tfName));
				//strBuf.append("~");
				//strBuf.append(fieldMap.get(tfName+"Max")  == null ? "" : fieldMap.get(tfName+"Max"));
				strBuf.append(fieldMap.get(tfName));

				strBuf.append("_");
			}else if("2".equals(sm.getSeriesRule()) || "6".equals(sm.getSeriesRule())||
					"7".equals(sm.getSeriesRule())) {

				String strs = (String) fieldMap.get(tfName);
				if(StringUtils.isEmpty(strs)) {
					strBuf.append("_");
				}else {
					String[] keys = strs.split(",");

					//String itemText = sysDictMapper.getItemTextsByKeys(tfName,keys);
					StringBuilder tempDictText = new StringBuilder();
					for(String k : keys){
						String itemText = sysDictMapper.queryDictTextByKey(tfName,k);
						tempDictText.append(itemText+",");
					}
					if(null != tempDictText){
						tempDictText = tempDictText.deleteCharAt(tempDictText.length()-1);
						strBuf.append(tempDictText);
						strBuf.append("_");
					}else{
						strBuf.append("_");
					}

				}

			}else{
				//其他
				strBuf.append(fieldMap.get(tfName)  == null ? "" : fieldMap.get(tfName));
				strBuf.append("_");
			}

		}

		log.info("组合系列名称="+strBuf);
		return strBuf.toString();
	}

	//获取系列编码
	private String getMaxSeriesNum(){
		String str = "";
		try {
			for(int i=0;i<100;i++){
				String str2 = getMaxSeriesNum(i);
				if(StringUtils.isNotEmpty(str2)){

					Map<String, Object> paramsMap = new HashMap<String, Object>();
					paramsMap.put("serial_number",str2);
					List<ProdSeries> infoList = this.prodSeriesMapper.selectByMap(paramsMap);
					if(infoList == null || infoList.size()<1){
						return str2;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(StringUtils.isEmpty(str)){
			return "编码生成100次，仍未找到唯一编码，请联系管理员处理！物料类型Id为：";
		}
		return str;
	}

	private String getMaxSeriesNum(int increase) throws Exception{

		String maxCode = this.prodSeriesMapper.getMaxSeriesNum();
		if(StringUtils.isEmpty(maxCode)){
			return  "000001";
		}else{
			long codeIndex = 0;
			try{
				codeIndex = Long.parseLong(maxCode);
			}catch(Exception e){
				e.printStackTrace();
				throw new Exception("当前类型最大物料后四位不正确，无法转化成数字！目前系统最大值为："+maxCode);
			}

			if(codeIndex >= 99999){
				//在bean里进行处理，提示流水号已经到最大的，不能再分配物料编码
				throw new Exception("该物料类型下的流水码已经生成到最大值9999！目前系统最大值为："+maxCode);
			}else{
				if((codeIndex + 1+increase) > 99999){
					//在bean里进行处理，提示流水号已经到最大的，不能再分配物料编码
					throw new Exception("该物料类型下的流水码已经生成到最大值9999！目前系统最大值为："+maxCode);
				}
				codeIndex=codeIndex+increase;
				if(codeIndex < 9){
					return  "00000" + (codeIndex + 1);
				}
				if(codeIndex < 99){
					return  "0000" + (codeIndex + 1);
				}
				if(codeIndex < 999){
					return "000" + (codeIndex + 1);
				}
				if(codeIndex < 9999){
					//return typeCode + "0" +(codeIndex + 1);
					return "00" +(codeIndex + 1);
				}
				if(codeIndex < 99999){
					return "0" +(codeIndex + 1);
				}
				if(codeIndex < 999999){
					return "" +(codeIndex + 1);
				}
			}
		}
		return null;
	}


	//将字符串转换成驼峰命名
	private String changeTF(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		//将字符串全部转为小写
		str = str.toLowerCase();
		//按_划线分割
		String[] strList = str.split("_");
		if (strList.length < 2) {
			return str;
		}else {
			for (int i = 1; i < strList.length; i++) {
				strList[i] = strList[i].substring(0,1).toUpperCase() + strList[i].substring(1,strList[i].length());
			}
			return StringUtils.join(strList);
		}
	}



	@Override
	public Map<String, Object> postCompleteTask(ActModel actModel) {
		log.info("产品指标审批postCompleteTask=" +actModel.getNodeId());
		String wfkey = actModel.getWfkey();
		String nodeId=actModel.getNodeId();
		String businessKey=actModel.getBusinessKey();
		HashMap<String, Object> map=new HashMap<String, Object>();
		ProdSeries ser = this.getById(businessKey);
		ser.setProcessinsid(actModel.getProcessInstanceId());
		this.updateById(ser);
		map.put("wf_title", "产品指标审批：指标名称="+ser.getSeriesName());
		return map;

	}

	@Override
	public Boolean afterCompleteTask(ActModel actModel) {
		log.info("产品指标审批afterCompleteTask=" +actModel.getBusinessKey());
		log.info("getNodeId=" +actModel.getNodeId());
		ProdSeries ser = this.getById(actModel.getBusinessKey());
		String nodeId=actModel.getNodeId();
		String result=actModel.getActResult();
		if("-1".equals(actModel.getNodeId())){//开始节点发起审批
			ser.setProcessinsid(actModel.getProcessInstanceId());
			ser.setState(BasConstant.PROD_SERIES_STATE_APPR);
			this.updateById(ser);
			return true;
		}else if("dept_leader".equals(nodeId)){//领导审批
			if("Y".equals(result)){
				ser.setState(BasConstant.PROD_SERIES_STATE_FIN);
				//版本号累加
				String verCode = ser.getSeriesVer();
				if(me.zhyd.oauth.utils.StringUtils.isEmpty(verCode)){
					verCode = "A.1";
				}else{
					String[] strs = verCode.split(".");
					int code = Integer.parseInt(strs[1])+1;
					verCode = "A."+code;
				}
				ser.setSeriesVer(verCode);

				//将产品指标添加产品结构BOM中，作为根使用
				//addRootProductBom(ser);
			}else {
				ser.setState(BasConstant.PROD_SERIES_STATE_REJECT);
			}
			this.updateById(ser);
		}
		return true;

	}

	@Override
	public void postEndProcess(ActModel actModel) {
		log.info("产品指标审批终止=" +actModel.getBusinessKey());
		String businessKey = actModel.getBusinessKey();
		ProdSeries ser = this.getById(businessKey);
		ser.setState(BasConstant.PROD_SERIES_STATE_END);//标记审批终止
		this.updateById(ser);
	}

	@Override
	public String getUsers(Map<String, Object> map) {
		log.info("产品指标审批获取用户=" +map.toString());
		return null;
	}

	@Override
	public void addRootProductBom(ProdSeries ser){
		log.info("产品指标添加到产品结构中");
		//物料基础
		ProProductInfo info = new ProProductInfo();
		info.setMaterialName(ser.getSeriesName());
		info.setMaterialCode(ser.getSerialNumber());
		info.setMapNo(ser.getSerialNumber());
		info.setPartType(ProConstant.BOM_PART_TYPE_PART);
		info.setVersionNum("A.1");
		info.setSeriesId(ser.getId());
		info.setRelation(ProConstant.PRODUCTBOM_RELATION_NORMAL);
		info.setSignInoff("OFF");
		info.setStatus(ProConstant.PRODUCTINFO_STATUS_ADD);
		LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		info.setSignOffUser(user.getUsername());
		info.setSignOffUsername(user.getRealname());

		proProductInfoMapper.insert(info);

		//bom关系
		ProProductBom rootBom = new ProProductBom();
		rootBom.setMaterialName(ser.getSeriesName());
		rootBom.setMapNo(ser.getSerialNumber());
		rootBom.setChildoid(info.getId());
		rootBom.setIsRoot("Y");
		rootBom.setPerQty(new BigDecimal("1"));


		proProductBomMapper.insert(rootBom);

		//更新指标加入产品结构标识
        ser.setIsAddBom("1");
        prodSeriesMapper.updateById(ser);
	}

	/**
	 * 20210425 wuj 注释，分指标不需要再结构树上展示
	//添加分指标到结构树中
	private void addBranchSeriesToBom(ProdSeries ser){
		log.info("分指标添加到产品结构中");
		ProProductInfo info = new ProProductInfo();
		info.setMaterialName(ser.getSeriesName());
		info.setMaterialCode(ser.getSerialNumber());
		info.setMapNo(ser.getSerialNumber());
		info.setPartType(ProConstant.BOM_PART_TYPE_PART);
		info.setVersionNum(ser.getSeriesVer());
		info.setSeriesId(ser.getId());
		info.setSignInoff("OFF");
		info.setStatus(ProConstant.PRODUCTINFO_STATUS_ADD);
		proProductInfoMapper.insert(info);

		ProProductBom childBom = new ProProductBom();
		childBom.setMaterialName(ser.getSeriesName());
		childBom.setMapNo(ser.getSerialNumber());
		childBom.setChildoid(info.getId());
		childBom.setPerQty(new BigDecimal("1"));
		childBom.setRelation("0");


		//指定分指标的bom父id
		Map<String,Object> parentMap = new HashMap<String,Object>();
		parentMap.put("series_id",ser.getParentoid());
		parentMap.put("part_type",ProConstant.BOM_PART_TYPE_PART);
		List<ProProductInfo> parentInfoList = proProductInfoMapper.selectByMap(parentMap);
		childBom.setParentoid(parentInfoList.get(0).getId());



		proProductBomMapper.insert(childBom);
	}*/
	
}
