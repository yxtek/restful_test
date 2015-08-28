/** 
 * File Name:RequestFactory.java
 * 
 * Version:1.0
 *
 * Date:2015-8-20
 *
 * Description:
 *
 * Author:erik
 * Email:erik7@126.com
 * Copyright (c) 2015 yxtek Information Co.,Ltd.
 * All Rights Reserved. 
 */ 

package com.example.saf.core.http;

import cn.salesuite.saf.http.rest.RestConstant;

import com.alibaba.fastjson.JSONObject;

public class RequestFactory {

	public static RequestEntity verifyCode(String phone) {
		JSONObject object = new JSONObject();
		object.put("phone", phone);
		
		return new RequestEntity(HttpConstant.SERVER+HttpConstant.API_VERIFY_CODE, RestConstant.METHOD_POST,
				object);
	}
	
	public static RequestEntity login(String phone,String password) {
		JSONObject object = new JSONObject();
		object.put("phone", phone);
		object.put("password", password);
		
		return new RequestEntity(UrlFactory.getUrl(HttpConstant.API_LOGIN), RestConstant.METHOD_POST,
				object);
	}
}


