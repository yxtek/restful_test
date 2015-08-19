package com.example.saf.common;

import java.lang.ref.WeakReference;

import com.alibaba.fastjson.JSONObject;

import cn.salesuite.saf.http.rest.HttpResponseHandler;
import cn.salesuite.saf.http.rest.RestClient;

public class MyHttpTask implements Runnable {
	
	private WeakReference<HttpResponseHandler> handler;
	private String url;
	
	public MyHttpTask(HttpResponseHandler handler,String url){
		this.handler = new WeakReference<HttpResponseHandler>(handler);
		this.url = url;
	}
	
	@Override
	public void run() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", 87);
		RestClient.post(url, jsonObject, handler.get());
	}
	
	public void cancel() {
		handler.clear();
	}

}
