package com.example.saf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import cn.salesuite.saf.eventbus.EventBus;
import cn.salesuite.saf.http.rest.HttpResponseHandler;
import cn.salesuite.saf.http.rest.RestClient;
import cn.salesuite.saf.http.rest.RestException;
import cn.salesuite.saf.inject.Injector;
import cn.salesuite.saf.inject.annotation.InjectView;
import cn.salesuite.saf.log.L;

import com.alibaba.fastjson.JSON;
import com.example.android.apis.R;
import com.example.saf.domain.Contributor;

/**
 * 
 * @author ERIK
 *
 */
public class RestClientDemo extends Activity {


//	@InjectView(id=R.id.tv_content)
	private TextView tvContent;
	
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

		eventBus = EventBusManager.getInstance();
		eventBus.register(this);
		new Contributor().delete();
		
		Injector.injectInto(this);
		setContentView(R.layout.activity_restdemo);
		
		
		tvContent = (TextView)findViewById(R.id.tv_content);
		etUrl = (EditText)findViewById(R.id.editText1);
		
		etUrl.setText("http://192.168.1.2/test.php");
		
		url = etUrl.getText().toString();
		

		sendRest();
		
		findViewById(R.id.btn_send).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				url = etUrl.getText().toString();
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
		eventBus.unregister(this);
	}
	
	private void sendRest() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				RestClient.get(url, new HttpResponseHandler() {

					public void onSuccess(String content) {
						// contentΪhttp����ɹ��󷵻ص�response
						L.d("onSuccess[" + content + "]");
//						List<Contributor> list = JSON.parseArray(content, Contributor.class);
//						
//						for (Contributor contributor : list) {
////							listData.add(contributor.toString());
//							contributor.save();
//						}
//						handle.sendEmptyMessage(1);
						Message msg = Message.obtain();
						msg.what = 1;
						msg.obj = content;
						
						handle.sendMessage(msg);
						
					}

					@Override
					public void onFail(RestException exception) {
						Message msg = Message.obtain();
						msg.what = 1;
						msg.obj = exception.getMessage();
						
						handle.sendMessage(msg);
					}

					@Override
					public void onSuccess(String arg0,
							Map<String, List<String>> arg1) {
						Message msg = Message.obtain();
						msg.what = 1;
						msg.obj = arg0;
						
						Log.i("", arg1.toString());
						handle.sendMessage(msg);
					}
				});
			}
		}).start();
	}

}
