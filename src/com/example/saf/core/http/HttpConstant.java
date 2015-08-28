/** 
 * File Name:HttpConstant.java
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

public interface HttpConstant {
	
	public static final String SERVER = "http://app_api.fuhui.com/";
	
	// 根据手机号码请求验证码
	public static final String API_VERIFY_CODE = "v1/verifyCode";
	
	// 登陆接口
	public static final String API_LOGIN = "v1/login";
	
	public static final String API_ADDRESS = "v1/address";
	
	public static final String API_EMPLOYER = "v1/employer";
	
	public static final String API_FEEDBACK = "v1/feedback";
	
	public static final String AUTH = "Authorization";
	
	public static final String BEARER = "Bearer ";
}


