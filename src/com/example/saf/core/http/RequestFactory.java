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

import com.alibaba.fastjson.JSONObject;

public class RequestFactory {

	public static JSONObject verifyCode(String phone) {
		JSONObject object = new JSONObject();
		object.put("phone", phone);
		
		return object;
	}
	
	public static JSONObject login(String phone,String password) {
		JSONObject object = new JSONObject();
		object.put("phone", phone);
		object.put("password", password);
		
		return object;
	}
}


