package com.fbee.modules.jsonData.json;

import com.fbee.modules.core.persistence.ModelSerializable;

/** 
* @ClassName: CaptchaJsonData 
* @Description: 用户验证码jsonData
* @author zhangsq
* @date   2017/2/21
*  
*/
public class CaptchaJsonData implements ModelSerializable{
	private static final long serialVersionUID = 1L;
	
	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
