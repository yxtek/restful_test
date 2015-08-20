/** 
 * File Name:PageInfo.java
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

package com.example.saf.model.bean;

import java.io.Serializable;

public class PageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  currentPage	当前页
		pageCount	总页数
		perPage	每页多少条
		totalCount	总条数
	 */
	public int currentPage;
	public int pageCount;
	public int perPage;
	public int totalCount;
}


