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

package com.example.saf.common;

import java.io.File;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;

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
	
	
	/**
	 * 获取t卡路径
	 * @return
	 */
	public static String getTCardPath() {
		
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {			// 有T卡的路径
			
			File file = Environment.getExternalStorageDirectory();
			if ( file != null )
			{
				if ( file.canWrite () )
				{
					return file.getAbsolutePath();
				}
				String filePath = file.getAbsolutePath ();
				if ( filePath.endsWith ( "sdcard" ) )
				{
					filePath = filePath + "-ext";
					file = new File ( filePath );
					if ( file != null && file.canWrite () )
					{
						return file.getAbsolutePath();
					}
				}
			}

			// HTC One
			file = new File ( "/mnt/emmc" );
			if ( file != null && file.canWrite () )
			{
				return file.getAbsolutePath();
			}

			//
			file = new File ( "/mnt/sdcard/external_sd/" );
			if ( file != null && file.canWrite () )
			{
				return file.getAbsolutePath();
			}

			System.out.println ( Environment.getExternalStorageDirectory () + " is not exists" );

//			File tmpDir = applicationContext.getDir ( "sdcard_tmp", Context.MODE_PRIVATE );
//			if ( !tmpDir.exists () )
//			{
//				tmpDir.mkdirs ();
//			}
//			return tmpDir;
			return Environment.getExternalStorageDirectory().toString();
		} else {	// 无t卡的路径
			return Environment.getDownloadCacheDirectory().toString();
		}
	}
}


