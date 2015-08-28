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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;
import cn.salesuite.saf.http.rest.HttpResponseHandler;
import cn.salesuite.saf.http.rest.RestClient;
import cn.salesuite.saf.http.rest.RestConstant;
import cn.salesuite.saf.http.rest.RestException;

import com.example.saf.core.http.HttpConstant;
import com.example.saf.core.http.RequestEntity;

public abstract class HttpTask implements Runnable {
	
	// 任务是否被取消
	private boolean canceled;
	
	private RequestEntity requestEntity;
	

	public HttpTask(RequestEntity entity) {
		this.requestEntity = entity;
	}
	
	@Override
	public void run() {
		RestClient.setRetryNum(1);
			
		if(requestEntity==null)
			return;
		
		String methodType = requestEntity.getRestMethod();
		String url = requestEntity.getUrl();
		
		try {
			if(methodType.equals(RestConstant.METHOD_POST)){
				RestClient.post(url, requestEntity.getToken(),requestEntity.getEntity(), 
						httpResponseHandler);
			}
			else if(methodType.equals(RestConstant.METHOD_PUT)){
//				Map<String, String> params = requestEntity.getParams();
//				if(params==null)
//					params = new HashMap<String, String>();
//				
//				params.put(HttpConstant.AUTH, HttpConstant.BEARER+requestEntity.getToken());
				
				RestClient.put(requestEntity.getUrl(), requestEntity.getToken(),
						requestEntity.getEntity(),httpResponseHandler);
			}
			else if(methodType.equals(RestConstant.METHOD_GET)){
				Map<String, String> params = requestEntity.getParams();
				
				if(params==null)
					params = new HashMap<String, String>();
				
				params.put(HttpConstant.AUTH, HttpConstant.BEARER+requestEntity.getToken());
				
				RestClient.get(url, params, httpResponseHandler);
			}
			else if(methodType.equals(RestConstant.METHOD_DELETE)){
				RestClient.delete(url,requestEntity.getToken(),httpResponseHandler);
			}
			else{
				Log.e("", "no methed:"+requestEntity.getRestMethod());
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
		public void onFail(RestException arg0) {
			if(canceled)
				return;
			
			onException(arg0);
		}

		@Override
		public void onSuccess(int code,String arg0, Map<String, List<String>> arg1) {
			if(canceled)
				return;

			onResponse(code,arg0, arg1);
			
		}
	};

	protected abstract void onResponse(int code,String arg0,Map<String, List<String>> arg1);
	protected abstract void onException(RestException arg0);
}
