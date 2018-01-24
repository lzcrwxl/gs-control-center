package com.fbee.modules.service;

import com.fbee.modules.core.bean.SmsCode;

public interface SmsService {
	/*
	门店审核通过发送短信
	*/
	SmsCode sendTenantPass(String contactPhone,String websiteName,String loginAccount,String password);
	
	/*
	*总控平台-重置密码	
	*/
	SmsCode sendTenantResetPWD(String contactPhone,String tenantsName,String password);
	
}
