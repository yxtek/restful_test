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
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import com.alibaba.fastjson.JSONObject;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.test.AndroidTestCase;
import android.util.Log;

@SuppressWarnings("deprecation")
public class CommonTest extends AndroidTestCase {

	public void test() {
		Log.i("", "start test");
		
//		getIpTest();
		testHttp2();
		
	}
	
	private static final String TAG = "CommonTest";
	private static final String URL = "http://10.10.3.52/v1/address/7";
//	private static final String URL = "http://10.10.4.27/test/test3.php";
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 1:
				String log = (String)msg.obj;
				Log.i("", "response from server:"+log);
				break;
			}
		};
	};
	
	private void testHttp(){
		
		new Thread(new Runnable(){
			 
            @Override
            public void run() {

                HttpURLConnection connection=null;
                 
                try {
                    URL url=new URL(URL);
                    connection =(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("PUT");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoOutput(true);
                    
//                    connection.setRequestProperty("http.keepAlive","false");
                    connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//                    connection.setRequestProperty("Accept","application/json");
//                    connection.setRequestProperty("Authorization","Bearer");
                    
//                    InputStream in=connection.getInputStream();
                    //下面对获取到的输入流进行读取
//                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
//                    StringBuilder response=new StringBuilder();
//                    String line;
//                    while((line=reader.readLine())!=null){
//                        response.append(line);
//                    }
//                    Message message=new Message();
//                    message.what=1;
//                    //将服务器返回的结果存放到Message中
//                    message.obj=response.toString();
//                    handler.sendMessage(message);
//                     Log.i("", "response from server:"+response.toString());
                    
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("phone", "1368899");
                    DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                    dataOutputStream.writeBytes(jsonObject.toJSONString());
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    
                    connection.connect();
                    
                     
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
             
        }).start();
		
		
	}
	
	
	private void showResponseResult(HttpResponse response)
    {
        if (null == response)
        {
            return;
        }

        HttpEntity httpEntity = response.getEntity();
        try
        {
            InputStream inputStream = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inputStream));
            String result = "";
            String line = "";
            while (null != (line = reader.readLine()))
            {
                result += line;

            }

            System.out.println(result);
            Log.i("","Response Content from server: " + result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
	
	private void testHttp2() {
		
		 HttpURLConnection connection=null;
         
         try {
             URL url=new URL(URL);
             connection =(HttpURLConnection) url.openConnection();
             
//             connection.setConnectTimeout(8000);
//             connection.setReadTimeout(8000);
//             connection.setDoOutput(true);
             
             connection.setRequestProperty("Authorization", "Bearer dlzEw5hIfCneFv9WYcqSAEqeJxm0a_0rLbBUa0579Es4Ycz4dLv2KobuS3z3T3HyWbDujpZ");
             connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
             
             connection.setRequestMethod("PUT");
             
             JSONObject jsonObject = new JSONObject();
             jsonObject.put("phone", "1368899");
             DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
             dataOutputStream.writeBytes(jsonObject.toJSONString());
             dataOutputStream.flush();
             dataOutputStream.close();
             
             connection.connect();
             
//             connection.setRequestProperty("Content-Type","application/json");
//           connection.setRequestProperty("Accept","application/json");
             
             int responseCode = connection.getResponseCode();
             Log.i("", "response code:"+ responseCode);
//             
             
//             InputStream in=connection.getInputStream();
//             //下面对获取到的输入流进行读取
//             BufferedReader reader=new BufferedReader(new InputStreamReader(in));
//             StringBuilder response=new StringBuilder();
//             String line;
//             while((line=reader.readLine())!=null){
//                 response.append(line);
//             }
////             Message message=new Message();
////             message.what=SHOW_RESPONSE;
////             //将服务器返回的结果存放到Message中
////             message.obj=response.toString();
////             handler.sendMessage(message);
//              Log.i("", "response from server:"+response.toString());
              
         } catch (MalformedURLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }catch(Exception e){
             e.printStackTrace();
         }finally{
             if(connection!=null){
                 connection.disconnect();
             }
         }
     }
	
	
	private void testHttp3() {
		
		HttpGet http;
		
		try {
			String s1 = URL;
			Log.i(TAG, "url:"+s1);
			http = new HttpGet(s1);
			
			HttpResponse httpResponse = new DefaultHttpClient().execute(http);
			showResponseResult(httpResponse);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getIpTest() {
		InetAddress address;
		try {
			address = InetAddress.getByName("app_api.fuhui.com");
			String ip = address.getHostAddress();
			Log.i(TAG, "ip:"+ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}


