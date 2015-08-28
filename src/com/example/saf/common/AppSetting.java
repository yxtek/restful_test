/** 
 * File Name:AppSetting.java 
 * 
 * Version:1.0
 *
 * Date:2014-2-15
 *
 * Description:
 *
 * Author:Erik Wei
 * Email:erik7@126.com
 * Copyright (c) 2013 Erik Wei
 * All Rights Reserved. 
 */ 
package com.example.saf.common;

import android.content.Context;

public class AppSetting {
	
	private static final String SP_NAME = "jzol";
	private static final String SP_TOKEN_KEY = "token";
	
	
	
	public static void setToken(Context context,String token) {
		context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
		.edit().putString(SP_TOKEN_KEY, token).commit();
	}
	
	public static String getToken(Context context) {
		return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
		.getString(SP_TOKEN_KEY, "");
	}
	
}
