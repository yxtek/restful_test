/** 
 * File Name:CommonTest.java
 * 
 * Version:1.0
 *
 * Date:2015-8-24
 *
 * Description:
 *
 * Author:erik
 * Email:erik7@126.com
 * Copyright (c) 2015 yxtek Information Co.,Ltd.
 * All Rights Reserved. 
 */ 

package com.example.saf.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.alibaba.fastjson.JSONObject;

import android.test.AndroidTestCase;
import android.util.Log;

@SuppressWarnings("deprecation")
public class CommonTest extends AndroidTestCase {

	public void test() {
		Log.i("", "start test");
		long phone = 13681885398L;
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("phone", phone);
		
		String string = jsonObject.toJSONString();
		long phone2 = jsonObject.getLongValue("phone");

		Log.i("", "phone num:"+phone2);
	}
	
	private void testHttp() {
		HttpPost httpPost = new HttpPost("http://192.168.0.200/v1/login");
		HttpClient httpClient = new DefaultHttpClient();
		
		InputStream inputStream = null;
		try{
            // 发送请求并获得响应对象
        	HttpResponse mHttpResponse = null;
        	HttpEntity mHttpEntity = null;
            mHttpResponse = httpClient.execute(httpPost);
            // 获得响应的消息实体
            mHttpEntity = mHttpResponse.getEntity();

            // 获取一个输入流
            inputStream = mHttpEntity.getContent();

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream));

            String result = "";
            String line = "";

            while (null != (line = bufferedReader.readLine()))
            {
                result += line;
            }

            // 将结果打印出来，可以在LogCat查看
            System.out.println(result);

            // 将内容载入WebView显示
//            mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
//            // 直接使用mWebView.loadData(result, "text/html", "utf-8");会显示找不到网页
//
//            // 换成下面的方式可以正常显示（但是比较宽，拖动可见百度logo）
//            mWebView.loadDataWithBaseURL(null, result, "text/html",
//                    "utf-8", null);
            
            // 直接载入URL也可以显示页面（但是此例子主要是为了验证响应返回的字符串是否正确，所以不用下面这行代码）
            // mWebView.loadUrl("http://www.baidu.com/");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                inputStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
	}
}


