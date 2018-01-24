package com.fbee.modules.bean;

/** 
* @ClassName: MatchSpecial 
* @Description: 用户存储--个人特点、服务内容计算值
* @author 贺章鹏
* @date 2017年2月8日 上午10:44:09 
*  
*/
public class MatchSpecial {
	
	// 个人特点
	private int doNotFamily;// 不做家庭

	// 服务内容
	private int serviceContents;// 服务内容

	public int getDoNotFamily() {
		return doNotFamily;
	}

	public void setDoNotFamily(int doNotFamily) {
		this.doNotFamily = doNotFamily;
	}

	public int getServiceContents() {
		return serviceContents;
	}

	public void setServiceContents(int serviceContents) {
		this.serviceContents = serviceContents;
	}
	
}
