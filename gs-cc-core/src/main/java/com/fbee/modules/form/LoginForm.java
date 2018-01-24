package com.fbee.modules.form;

import com.fbee.modules.core.persistence.ModelSerializable;

/** 
* @ClassName: LoginForm 
* @Description: TODO
* @author zhangsq
* @date 2017/2/21
*  
*/
public class LoginForm implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private String loginAccount;//登陆账号
	
	private String captcha;//验证码
	
	private String password;//密码

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder();
		stringBuilder.append("user login params,{loginAccount:").append(loginAccount)
			.append(",password:").append(password).append(",captcha:").append(captcha);
		return stringBuilder.toString();
	}
}
