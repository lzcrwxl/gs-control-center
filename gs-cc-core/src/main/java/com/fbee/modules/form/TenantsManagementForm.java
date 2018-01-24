package com.fbee.modules.form;

import java.util.Date;

import com.fbee.modules.core.persistence.ModelSerializable;

public class TenantsManagementForm implements ModelSerializable {

	public String getFileLogo() {
		return fileLogo;
	}

	public void setFileLogo(String fileLogo) {
		this.fileLogo = fileLogo;
	}

	private static final long serialVersionUID = 1L;
	
	private Integer applicationId;
	
	private Date addTime;
	
	private String registerPhone;
	
	private String tenantName;//门店名称
	
	private String serviceProvice;//所在区域 省
	
	private String serviceCity;//所在区域 市
	
	private String serviceCounty;//所在区域 区
	
	private String contactAddress;//详细地址
	
	private String tenantsPhone;//详细地址
	
	private String contactName;//联系人姓名
	
	private String contactPhone;//联系人手机号
	
	private String domain;//二级域名
	
	private String loginAccount;//管理员账号
	
	private String password;//密码
	
	private String fileLogo;//Logo

	private String accountType;//账号性质
	
	private String bankName;//账号名称
	
	private String cardNo;//银行账号
	
	private String bankCode;//所属银行
	
	private String bankSecName;//支行名称
	
	private String companyName;//工商名称
	
	private String licenceNo;//营业执照注册号
	
	private String fileLience;//营业执照照片
	
	public String getFileLience() {
		return fileLience;
	}

	public void setFileLience(String fileLience) {
		this.fileLience = fileLience;
	}

	public String getFileFront() {
		return fileFront;
	}

	public void setFileFront(String fileFront) {
		this.fileFront = fileFront;
	}

	public String getFileBack() {
		return fileBack;
	}

	public void setFileBack(String fileBack) {
		this.fileBack = fileBack;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String agentName;//代理人姓名
	
	private String idNumber;//代理人身份证号
	
	private String fileFront;//身份证照片正面
	
	private String fileBack;//身份证照片反面
	
	private Integer recommendId;//推荐人ID
	
	private String salesPerson;//销售人姓名

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getRegisterPhone() {
		return registerPhone;
	}

	public void setRegisterPhone(String registerPhone) {
		this.registerPhone = registerPhone;
	}

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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Integer getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(Integer recommendId) {
		this.recommendId = recommendId;
	}

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public String getTenantsPhone() {
		return tenantsPhone;
	}

	public void setTenantsPhone(String tenantsPhone) {
		this.tenantsPhone = tenantsPhone;
	}

	

	
	
}

