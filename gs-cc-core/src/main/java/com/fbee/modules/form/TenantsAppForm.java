package com.fbee.modules.form;

import com.fbee.modules.core.persistence.ModelSerializable;

public class TenantsAppForm implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private String tenantName;//门店名称
	
	private String registerPhone;//申请人手机号
	
	private String recommendId;//推荐ID
	
	private Integer memberType;//会员类型
	
	private String tenantStatus;//门店状态
	
	private String applyTimeLeft;//创建时间 左
	
	private String applyTimeRight;//创建时间右
	
	private String provice;//所在地区 省
	
	private String city;//所在地区 市
	
	private String dueDateLeft;//有效日期左
	
	private String dueDateRight;//有效日期右

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getRegisterPhone() {
		return registerPhone;
	}

	public void setRegisterPhone(String registerPhone) {
		this.registerPhone = registerPhone;
	}

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	public String getTenantStatus() {
		return tenantStatus;
	}

	public void setTenantStatus(String tenantStatus) {
		this.tenantStatus = tenantStatus;
	}
	
	public String getApplyTimeLeft() {
		return applyTimeLeft;
	}

	public void setApplyTimeLeft(String applyTimeLeft) {
		this.applyTimeLeft = applyTimeLeft;
	}

	public String getApplyTimeRight() {
		return applyTimeRight;
	}

	public void setApplyTimeRight(String applyTimeRight) {
		this.applyTimeRight = applyTimeRight;
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

	public String getDueDateLeft() {
		return dueDateLeft;
	}

	public void setDueDateLeft(String dueDateLeft) {
		this.dueDateLeft = dueDateLeft;
	}

	public String getDueDateRight() {
		return dueDateRight;
	}

	public void setDueDateRight(String dueDateRight) {
		this.dueDateRight = dueDateRight;
	}

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	
	
	
}
