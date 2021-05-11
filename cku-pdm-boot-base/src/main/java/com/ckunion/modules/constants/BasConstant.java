package com.ckunion.modules.constants;

public class BasConstant {


	/**
	 * 典型工艺状态
	 * 0:未归档
	 * 1:审批中
	 * 2:已归档
	 * 7:审批终止，统一状态
	 * 8:审批驳回、审批不同意，统一状态
	 */
	public static final String TYPICALPROC_STATE_PREPA = "0";
	public static final String TYPICALPROC_STATE_APPR = "1";
	public static final String TYPICALPROC_STATE_FIN = "2";
	public static final String TYPICALPROC_STATE_END = "7";
	public static final String TYPICALPROC_STATE_REJECT = "8";


	/**
	 * 产品指标状态
	 * 0:申请
	 * 1:审批中
	 * 2:已发布
	 * 7:审批终止，统一状态
	 * 8:审批驳回、审批不同意，统一状态
	 */
	public static final String PROD_SERIES_STATE_PREPA = "0";
	public static final String PROD_SERIES_STATE_APPR = "1";
	public static final String PROD_SERIES_STATE_FIN = "2";
	public static final String PROD_SERIES_STATE_END = "7";
	public static final String PROD_SERIES_STATE_REJECT = "8";


}
