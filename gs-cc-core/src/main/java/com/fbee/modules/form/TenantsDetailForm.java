package com.fbee.modules.form;

import com.fbee.modules.core.persistence.ModelSerializable;

public class TenantsDetailForm implements ModelSerializable{
	
	private static final long serialVersionUID = 1L;
	
	//已入驻门店修改
	private String tenantName;//门店名称

	private String serviceProvice;//所在区域 省
	
	private String serviceCity;//所在区域 市
	
	private String serviceCounty;//所在区域 区
	
	private String contactAddress;//详细地址	
	
	private String contactName;//联系人姓名
	
	private String contactPhone;//联系人手机号
	
	private String loginAccount;//管理员账号
	
	private String password;//密码
	
	private String domain;//二级域名
	
	private String accountType;//账号性质
	
	private String bankName;//账号名称
	
	private String cardNo;//银行账号
	
	private String bankCode;//所属银行
	
	private String bankSecName;//支行名称
	
	private String salesPerson;//销售人姓名

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getServiceProvice() {
		return serviceProvice;
	}

	public void setServiceProvice(String serviceProvice) {
		this.serviceProvice = serviceProvice;
	}

	public String getServiceCity() {
		return serviceCity;
	}

	public void setServiceCity(String serviceCity) {
		this.serviceCity = serviceCity;
	}

	public String getServiceCounty() {
		return serviceCounty;
	}

	public void setServiceCounty(String serviceCounty) {
		this.serviceCounty = serviceCounty;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankSecName() {
		return bankSecName;
	}

	public void setBankSecName(String bankSecName) {
		this.bankSecName = bankSecName;
	}

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}
	
	
}
