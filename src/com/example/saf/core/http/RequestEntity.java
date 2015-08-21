/** 
 * File Name:RequestEntity.java
 * 
 * Version:1.0
 *
 * Date:2015-8-21
 *
 * Description:网络请求实体
 *
 * Author:erik
 * Email:erik7@126.com
 * Copyright (c) 2015 yxtek Information Co.,Ltd.
 * All Rights Reserved. 
 */ 

package com.example.saf.core.http;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class RequestEntity {

	private String url;
	private String restMethod;
	private JSONObject entity;
	private Map<String, String> params;
	
	public RequestEntity(String url,String method) {
		this.url = url;
		this.restMethod = method;
	}
	
	public RequestEntity(String url,String method,JSONObject entity) {
		this.url = url;
		this.restMethod = method;
		this.entity = entity;
	}
	
	public RequestEntity(String url,String method,Map<String, String> param) {
		this.url = url;
		this.restMethod = method;
		this.params = param;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRestMethod() {
		return restMethod;
	}
	public void setRestMethod(String restMethod) {
		this.restMethod = restMethod;
	}
	public JSONObject getEntity() {
		return entity;
	}
	public void setEntity(JSONObject entity) {
		this.entity = entity;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	
}


