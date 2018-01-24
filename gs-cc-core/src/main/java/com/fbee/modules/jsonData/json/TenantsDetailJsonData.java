package com.fbee.modules.jsonData.json;

import com.fbee.modules.core.persistence.ModelSerializable;

public class TenantsDetailJsonData implements ModelSerializable {
	
	private static final long serialVersionUID = 1L;
	
	private String tenantName;//门店名称
	
	private String serviceProvice;//省
	
	private String serviceCity;//市
	
	private String serviceCounty;//市
	
	private String contactAddress;//详细地址
	
	private String contactName;//联系人姓名
	
	private String contactPhone;//联系人手机号
	
	private  String loginAccount;//管理员账户
	
	private String password;//密码
	
	private String domain;//二级域名
	
	private String recommendId;//推荐ID
	
	private String totalAmount;//账户余额
	
	private String frozenAmount;//冻结金额
	
	private String  availableAmount;//可用余额
	
	private String accountType;//账户性质
	
	private  String bankName;//账户名称
	
	private String bankCode;//所属银行
	
	private String bankSecName;//所属银行支行
	
	private String cardNo;//银行卡号
	
	private String memberType;//会员类型
	
	private String tenantStatus;//门店状态
	
	private String dueDate;//有效期至
	
	private String applyTime;//创建时间
	
	private String companyName;//工商名称
	
	private String licenceNo;//营业执照编号
	
	private String agentName;//代理人姓名
	
	private String idNumber;//身份证号
	
	private String salesPerson;//销售人

	private String websiteLogo;//logo
	
	private String licenceImg;//营业执照
	
	private String idcardFrontImg;//身份证正面
	
	private String idcardBackImg;//身份证正面
	
	public String getWebsiteLogo() {
		return websiteLogo;
	}

	public void setWebsiteLogo(String websiteLogo) {
		this.websiteLogo = websiteLogo;
	}

	public String getLicenceImg() {
		return licenceImg;
	}

	public void setLicenceImg(String licenceImg) {
		this.licenceImg = licenceImg;
	}

	public String getIdcardFrontImg() {
		return idcardFrontImg;
	}

	public void setIdcardFrontImg(String idcardFrontImg) {
		this.idcardFrontImg = idcardFrontImg;
	}

	public String getIdcardBackImg() {
		return idcardBackImg;
	}

	public void setIdcardBackImg(String idcardBackImg) {
		this.idcardBackImg = idcardBackImg;
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

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(String frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

	public String getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(String availableAmount) {
		this.availableAmount = availableAmount;
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getTenantStatus() {
		return tenantStatus;
	}

	public void setTenantStatus(String tenantStatus) {
		this.tenantStatus = tenantStatus;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
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

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}
	
	
}
