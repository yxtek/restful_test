/** 
 * File Name:UrlFactory.java
 * 
 * Version:1.0
 *
 * Date:2015-8-28
 *
 * Description:
 *
 * Author:erik
 * Email:erik7@126.com
 * Copyright (c) 2015 yxtek Information Co.,Ltd.
 * All Rights Reserved. 
 */ 

package com.example.saf.core.http;

public class UrlFactory {

	/**
	 * 获取restful url全路径
	 * @param res_name	资源名
	 * @return
	 */
	public static String getUrl(String res_name) {
		return HttpConstant.SERVER+res_name;
	}
	
	
	public static String getUrl(String res_name,String param) {
		return HttpConstant.SERVER+res_name+"/"+param;
	}
}


