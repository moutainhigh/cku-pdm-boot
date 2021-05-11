package com.ckunion.constant;

public class ProConstant {


	/**
	 * BOM 节点类型 0结构件、1原材料、2文档
	 */
	public static final String BOM_PART_TYPE_PART = "0";
	public static final String BOM_PART_TYPE_MAT = "1";
	public static final String BOM_PART_TYPE_DOC = "2";

	/**
	 * BOM 节点 节点状态：1拟制中-2修改中-3审核中-4会签中-5批准中-6已批准-7已归档
	 */
	public static final String PRODUCTINFO_STATUS_ADD = "1";
	public static final String PRODUCTINFO_STATUS_EDIT = "2";
	public static final String PRODUCTINFO_STATUS_CHECK = "3";
	public static final String PRODUCTINFO_STATUS_SIGN = "4";
	public static final String PRODUCTINFO_STATUS_APPR_ING = "5";
	public static final String PRODUCTINFO_STATUS_APPR_FIN = "6";
	public static final String PRODUCTINFO_STATUS_ARCHIVED = "7";


	/**
	 * BOM 节点 发布状态：1申请中-4会签中-5批准中-6已批准-7已发布
	 */
	public static final String PRODUCTINFO_RELEASE_APPLY = "1";
	public static final String PRODUCTINFO_RELEASE_SIGN = "4";
	public static final String PRODUCTINFO_RELEASE_APPR_ING = "5";
	public static final String PRODUCTINFO_RELEASE_APPR_FIN = "6";
	public static final String PRODUCTINFO_RELEASE_RELE = "7";



	/**
	 * BOM 节点 借用关系:0正常1借用
	 */
	public static final String PRODUCTBOM_RELATION_NORMAL = "0";
	public static final String PRODUCTBOM_RELATION_BORROW = "1";


	/**
	 * ECN变更单 状态 1拟制中 5批准中 6已批准
	 */
	public static final String PRO_ECN_STATE_ADD = "1";
	public static final String PRO_ECN_STATE_APPR_ING = "5";
	public static final String PRO_ECN_STATE_APPR_FIN = "6";

	/**
	 * ECN变更单 处理状态 0未处理 1已处理
	 */
	public static final String PRO_ECN_CHANGESTATE_NOCHANGE = "0";
	public static final String PRO_ECN_CHANGESTATE_CHANGE = "1";


	/**
	 * 产品结构请求图号 状态 0未处理 1已处理
	 */
	public static final String PRO_APPLY_MAPNO_STATE_NOCHANGE = "0";
	public static final String PRO_APPLY_MAPNO_STATE_CHANGE = "1";



}
