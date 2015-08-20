/** 
 * File Name:BaseActivity.java
 * 
 * Version:1.0
 *
 * Date:2015-8-20
 *
 * Description: app内activity基类
 * 				提供绑定view初始化，子类需要运用反射绑定view和viewId
 *
 * Author:erik
 * Email:erik7@126.com
 * Copyright (c) 2015 yxtek Information Co.,Ltd.
 * All Rights Reserved. 
 */ 

package com.example.saf.core;

import android.app.Activity;
import android.os.Bundle;
import cn.salesuite.saf.inject.Injector;

public abstract class BaseActivity extends Activity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			setContentView(getLayoutId());
			Injector.injectInto(this);
		}
		
		
		/**
		 * 由子类实现
		 * @return
		 */
		protected abstract int getLayoutId();
}


