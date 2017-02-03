package com.rupeng.view.constants;

public class Constants {
	public static final String AWS_MESSAGE = "awsmessage";
	
	public static final String PREF_DIRECTORY_LISTING = "prefDirectotyListing";
	public static final String PREF_DIRECTORY = "prefDirectory";
	public static final String PREF_VIBRATE = "prefVibrate";
	public static final String PREF_PLAYSOUND = "prefPlaysound";
	public static final String PREF_RINGTONE = "prefMessageringtone";
	public static final String PREF_SERVER_PORT = "prefServerPort";

	public static final int DEFAULT_SERVER_PORT = 8080;


	public static final String REQUEST_SUCCESS = "1000";
	//1 申请签单  <br/>
	//		2 已付预付款 <br/>
	//		3 已付尾款(不满足返现条件) <br/>
	//		4 已付尾款(满足返现条件) <br/>
	//		5 已返现给推荐人
	public static String getSignStatusDesc(String code){
		if("1".equals(code)){
			return  "申请签单中";
		}
		if("2".equals(code)){
			return  "已付预付款";
		}
		if("3".equals(code)){
			return  "已付尾款(不满足返现条件)";
		}
		if("4".equals(code)){
			return  "已付尾款(满足返现条件)";
		}
		if("5".equals(code)){
			return  "已返现给推荐人";
		}
		return code;

	}
}
