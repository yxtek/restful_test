package com.example.saf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import cn.salesuite.saf.eventbus.EventBus;
import cn.salesuite.saf.http.rest.RestClient;
import cn.salesuite.saf.http.rest.RestConstant;
import cn.salesuite.saf.http.rest.RestException;
import cn.salesuite.saf.inject.annotation.InjectView;

import com.alibaba.fastjson.JSONObject;
import com.example.android.apis.R;
import com.example.saf.common.Util;
import com.example.saf.core.BaseActivity;
import com.example.saf.core.HttpTask;
import com.example.saf.core.ThreadPoolManager;
import com.example.saf.core.http.RequestEntity;

/**
 * 
 * @author ERIK
 *
 */
public class RestClientDemo extends BaseActivity {

	// log tag
	private static final String TAG = "RestClientDemo";

	@InjectView(id=R.id.tv_content)
	private TextView tvContent;
	
	@InjectView(id=R.id.editText1)
	private EditText etUrl;
	
	private String url;
	
	private EventBus eventBus;
	@SuppressLint("HandlerLeak")
	private Handler handle = new Handler() {
		
		public void handleMessage(android.os.Message msg) {
			String content = (String)msg.obj;
			tvContent.setText(content);
		};
	};
		

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(null!=savedInstanceState){
			url = savedInstanceState.getString("url");
		}
		else{
			getDefaultUrl();
		}
		
		etUrl.setText(url);

		findViewById(R.id.btn_send).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				url = etUrl.getText().toString();
				getTestRequestEntity();
				sendRest();
			}
		});
		
	}

//	static class Contributor {
//		public String login;
//		public int contributions;
//
//		@Override
//		public String toString() {
//			return login + "," + contributions;
//		}
//	}
	
	
//	private void adapterNotifyDataSetChanged() {
//		List<Contributor> data = new Contributor().getAll();
//		for (Contributor contributor : data) {
//			listData.add(contributor.toString());
//		}
//
//		if (adapter != null) {
//			adapter.notifyDataSetChanged();
//		}
//	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		String url = etUrl.getText().toString();
		outState.putString("url", url);
		
		super.onSaveInstanceState(outState);
	}
	
	private void sendRest() {
		
		ThreadPoolManager.getInstance().stopAllTasks();
		
		RequestEntity entity = getTestRequestEntity();
		
		ThreadPoolManager.getInstance().addHttpTask(new HttpTask(entity) {
			
			@Override
			protected void onResponse(String arg0, Map<String, List<String>> arg1) {
				Message msg = Message.obtain();
				msg.what = 1;
				msg.obj = arg0;
				
				Log.i(TAG, "arg0:"+arg0);
				Log.i(TAG, arg1.toString());
				handle.sendMessage(msg);
			}
			
			@Override
			protected void onException(RestException arg0) {
				Message msg = Message.obtain();
				msg.what = 1;
				msg.obj = arg0.getMessage();
				
				Log.i(TAG, "arg0:"+arg0);
				handle.sendMessage(msg);
				
			}
		});
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				RestClient client = new RestClient(url, RestConstant.METHOD_POST);
//				String path = Util.getTCardPath()+"/1.txt";
//				FileInputStream fileInputStream;
//				try {
//					fileInputStream = new FileInputStream(new File(path));
//					client.send(fileInputStream);
//					RestClient.post(url);
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		}).start();
	}
	
	private String getDefaultUrl() {
//		url = String.format("http://%s/", Util.getIp(this));
		url = "http://app_api.fuhui.com/v1/login";
		return url;
	}
	
	private RequestEntity getTestRequestEntity() {
		String method = RestConstant.METHOD_POST;
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("phone", "15800571861");
		jsonObject.put("password", "111111");
		
		return new RequestEntity(url, method, jsonObject);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_restdemo;
	}
	
	
	
}
