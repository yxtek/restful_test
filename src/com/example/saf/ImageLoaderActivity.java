package com.example.saf;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import cn.salesuite.saf.eventbus.EventBus;
import cn.salesuite.saf.imagecache.ImageLoader;
import cn.salesuite.saf.inject.Injector;
import cn.salesuite.saf.inject.annotation.InjectView;
import com.example.android.apis.R;
import com.example.saf.domain.Contributor;


/**
 * 
 * @author ERIK
 *
 */
public class ImageLoaderActivity extends Activity {

	// log tag
	private static final String TAG = "ImageLoaderActivity";
	
	@InjectView(id=R.id.et_image_url)
	private EditText etUrl;
	
	@InjectView(id=R.id.imageView1)
	private ImageView iv;
	
	private ImageLoader imageLoader;
	
	private String url;
	
	private EventBus eventBus;
	@SuppressLint("HandlerLeak")
	private Handler handle = new Handler() {
		
		public void handleMessage(android.os.Message msg) {
			
		};
	};
		

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		eventBus = EventBusManager.getInstance();
		eventBus.register(this);
		new Contributor().delete();
		
		imageLoader = new ImageLoader(this, R.drawable.alert_dialog_icon,getCacheFolder());
		
		setContentView(R.layout.activity_imageloader_demo);
		Injector.injectInto(this);
		
		
		if(null!=savedInstanceState){
			url = savedInstanceState.getString("url");
		}
		else{
			getDefaultUrl();
		}
		
		etUrl.setText(url);
		
//		iv.setImageBitmap(imageLoader.loadBitmap(url));
		imageLoader.displayImage(url, iv);
		
		findViewById(R.id.btn_send).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				url = etUrl.getText().toString();
				iv.setImageBitmap(imageLoader.loadBitmap(url));
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
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		String url = etUrl.getText().toString();
		outState.putString("url", url);
		
		super.onSaveInstanceState(outState);
	}
	
	
	private void getDefaultUrl() {
//		url = String.format("http://%s/", Util.getIp(this));
		url = "http://www.shcars.cn/upload/news/f37f247df24f4e6e9c13386296698830/201503270511506757.jpg";
	}
	
	private String getCacheFolder() {
		return "yxtek";
	}
	
}
