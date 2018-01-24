package com.fbee.modules.form;

import com.fbee.modules.core.persistence.ModelSerializable;

/**
* @Description：门店管理租户查询条件Form
* @author zhangsq
* @date   2017/2/22
* 
*/

public class MendianForm implements ModelSerializable{
	
	private static final long serialVersionUID = 1L;
	
	private String tenantName;//门店名称
	
	private String registerPhone;//申请人手机号
	
	private String tenantStatus;//门店状态
	
	private String addTimeLeft;//申请时间 左
	
	private String addTimeRight;//申请时间右
	
	private String provice;//所在地区 省
	
	private String city;//所在地区 市
	
	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getTenantStatus() {
		return tenantStatus;
	}

	public void setTenantStatus(String tenantStatus) {
		this.tenantStatus = tenantStatus;
	}

	public String getAddTimeLeft() {
		return addTimeLeft;
	}

	public void setAddTimeLeft(String addTimeLeft) {
		this.addTimeLeft = addTimeLeft;
	}

	public String getAddTimeRight() {
		return addTimeRight;
	}

	public void setAddTimeRight(String addTimeRight) {
		this.addTimeRight = addTimeRight;
	}

	public String getRegisterPhone() {
		return registerPhone;
	}

	public void setRegisterPhone(String registerPhone) {
		this.registerPhone = registerPhone;
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
