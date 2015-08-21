/** 
 * File Name:HttpTask.java
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

package com.example.saf.core;

import java.util.List;
import java.util.Map;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;

import cn.salesuite.saf.http.rest.HttpResponseHandler;
import cn.salesuite.saf.http.rest.RestClient;
import cn.salesuite.saf.http.rest.RestConstant;
import cn.salesuite.saf.http.rest.RestException;

public abstract class HttpTask implements Runnable {
	
	private String url;
	
	private JSONObject requetEntity;
	
	// 任务是否被取消
	private boolean canceled;
	
	/**
	 * PUT,GET,POST,DELETE
	 * 默认为PUT
	 */
	private String methodType = RestConstant.METHOD_POST;
	
	public HttpTask(String url){
		this.url = url;
	}
	
	public HttpTask(String url,JSONObject requestObj){
		this.url = url;
		this.requetEntity = requestObj;
	}
	
	@Override
	public void run() {
		RestClient.setRetryNum(1);
			
		try {
			if(methodType.equals(RestConstant.METHOD_POST)){
				RestClient.post(url, requetEntity, httpResponseHandler);
			}
			else if(methodType.equals(RestConstant.METHOD_PUT)){
				
			}
			else if(methodType.equals(RestConstant.METHOD_GET)){
				
			}
			else if(methodType.equals(RestConstant.METHOD_DELETE)){
				
			}
			else{
			}
			
		} catch (RestException e) {
			onException(e);
		}

	}
	
	public void cancel() {
		canceled = true;
	}
	
	private HttpResponseHandler httpResponseHandler = new HttpResponseHandler() {
		
		@Override
		public void onSuccess(String arg0, Map<String, List<String>> arg1) {
			if(canceled)
				return;

			onResponse(arg0, arg1);
		}
		
		@Override
		public void onFail(RestException arg0) {
			if(canceled)
				return;
			
			onException(arg0);
		}
	};

	protected abstract void onResponse(String arg0,Map<String, List<String>> arg1);
	protected abstract void onException(RestException arg0);
}
