/*
 * RedisExcepiton.java
 * Copyright (c) 2013 kinjo
 * All rights reserved.
 * ---------------------------------------------------------------------
 * 2013-8-17 Created
 */
package com.fbee.modules.redis.exception;


/**
 * 缓存存储异常
 * 
 * @author kinjoYang
 * @version 1.0 2013-08-17
 * @since 1.0
 */
public class RedisExcepiton extends RuntimeException {

	private static final long serialVersionUID = 5116529467330605012L;

	public RedisExcepiton() {
		super();
	}

	public RedisExcepiton(String msg) {
		super(msg);
	}

	public RedisExcepiton(Throwable cause) {
		super(cause);
	}

	public RedisExcepiton(String msg, Throwable cause) {
		super(msg, cause);
	}

}
