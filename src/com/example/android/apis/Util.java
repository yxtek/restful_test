/** 
 * File Name:Util.java
 * 
 * Version:1.0
 *
 * Date:2015-8-18
 *
 * Description:
 *
 * Author:erik
 * Email:erik7@126.com
 * Copyright (c) 2015 yxtek Information Co.,Ltd.
 * All Rights Reserved. 
 */ 

package com.example.android.apis;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class Util {

	/**
	 * 获取IP(wifi)
	 * @param context
	 * @return
	 */
	public static String getIp(Context context) {
		WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		
		// wifi 没启动
		if(!wifiManager.isWifiEnabled()){
			return null;
		}
		
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		
		return i2Ip(wifiInfo.getIpAddress());
	}
	
	private static String i2Ip(int i) {
		return (i & 0xFF ) + "." +       
		        ((i >> 8 ) & 0xFF) + "." +       
		        ((i >> 16 ) & 0xFF) + "." +       
		        ( i >> 24 & 0xFF) ; 
	}
}


