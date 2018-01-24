package com.fbee.modules.core.sms;

import java.util.HashMap;
import java.util.Map;

public class SmsTemplates {
	
	/**
	 * 注册验证码模板ID
	 */
	public static final String REG_SMS_CODE = "139826";
	/**
	 * 重置密码验证码模板ID
	 */
	public static final String RESET_PWD_SMS_CODE = "114869";

	
	
	
	/**
	 * 总控平台-门店审核通过模板ID
	 */
	public static final String TENANT_PASS_SMS_CODE = "170845";
	
	/**
	 * 总控平台-门店重置密码模板
	 */
	public static final String TENANT_RESET_PWD_SMS_CODE = "170849";
	
	
	/**
	 * 短信模板内容
	 */
	private static Map<String, String> CONTENT;
	
	static {
		CONTENT = new HashMap<String, String>();
		CONTENT.put(REG_SMS_CODE, "【家策商学院】注册验证码：{1}，{2}分钟内有效。为了确保您的账户安全，请勿向任何人泄露您的短信验证码。");
		CONTENT.put(RESET_PWD_SMS_CODE, "【家策商学院】密码重置验证码：{1}，{2}分钟内有效。为了确保您的账户安全，请勿向任何人泄露您的短信验证码。");
		CONTENT.put(TENANT_PASS_SMS_CODE, "【家策好服务】：恭喜，您申请的{1}已成功开通。管理员账号{2}，登录口令{3}，请在首次登录后修改密码。请妥善保管好您的账号，以防泄漏。");
		CONTENT.put(TENANT_RESET_PWD_SMS_CODE, "【家策好服务】{1}的管理员账号已被重置，新登录口令为{2}，请在登录后修改密码。请妥善保管好您的账号，以防泄漏。");
	}
	
	public static String getContent(String templateId) {
		return CONTENT.get(templateId);
	}

}
