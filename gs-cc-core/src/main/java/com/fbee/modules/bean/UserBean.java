package com.fbee.modules.bean;

import com.fbee.modules.core.persistence.ModelSerializable;

/** 
* @ClassName: UserBean 
* @Description: 后端登陆用户bean--用户session
* @author zhangsq
* @date 2017/2/21 
*  
*/
public class UserBean implements ModelSerializable{
	private static final long serialVersionUID = 1L;
	
	private Integer userId;//用户id
	
	private String loginAccount;//用户账号
	
	private String userName;//用户
	
	private Integer tenantId;//租户id

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	
}
