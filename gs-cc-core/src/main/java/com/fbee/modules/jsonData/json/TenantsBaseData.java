package com.fbee.modules.jsonData.json;

import java.math.BigDecimal;
import java.util.Date;

import com.fbee.modules.core.persistence.ModelSerializable;

/** 
* @ClassName: TenantsBaseData 
* @Description: 门店基本信息jsonData
* @author zhangsq
* @date  
*  
*/
public class TenantsBaseData implements ModelSerializable{

	private static final long serialVersionUID = 1L;

	private Integer applicationId;//门店ID
	
	private String tenantName;//门店名称
	
	private String registerPhone;//注册手机号
	
	private String privince;//省
	
	private String city;//市
	
	private String contactAddress;//详细地址

	private String addTime;//添加时间
	
	private String remarks;//备注审核不通过原因
	
	private String modifyTime;//修改时间
	
	private Integer recommendId;//推荐人ID
	
	private String id_number;//证件号码
	private String licence_img;//营业执照
	private String area;//地区
	private String tenantStatus;//门店状态01：待审核02审核通过03审核不通过
	private String agentName;//代理人姓名
	private String idType;//证件类型
	private String addAccount;//添加人
	private String modifyAccount;//修改人
	private String idPhotopositive;//身份证正面照片
	private String idPhotonegative;//身份证反面照片
	private String businessName;//工商名称
	private String accountProperties;//账号性质
	private String accountName;//账号名称
	private String bankAccount;//银行账号
	private String openingBand;//开户行
	private String telephonenumber;//门店电话
	private String openingBandBranch;//开户支行
	private String socialInformationCode;//社会信息代码
	
	public String getSocialInformationCode() {
		return socialInformationCode;
	}

	public void setSocialInformationCode(String socialInformationCode) {
		this.socialInformationCode = socialInformationCode;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getLicence_img() {
		return licence_img;
	}

	public void setLicence_img(String licence_img) {
		this.licence_img = licence_img;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTenantStatus() {
		return tenantStatus;
	}

	public void setTenantStatus(String tenantStatus) {
		this.tenantStatus = tenantStatus;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getAddAccount() {
		return addAccount;
	}

	public void setAddAccount(String addAccount) {
		this.addAccount = addAccount;
	}

	public String getModifyAccount() {
		return modifyAccount;
	}

	public void setModifyAccount(String modifyAccount) {
		this.modifyAccount = modifyAccount;
	}

	public String getIdPhotopositive() {
		return idPhotopositive;
	}

	public void setIdPhotopositive(String idPhotopositive) {
		this.idPhotopositive = idPhotopositive;
	}

	public String getIdPhotonegative() {
		return idPhotonegative;
	}

	public void setIdPhotonegative(String idPhotonegative) {
		this.idPhotonegative = idPhotonegative;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getAccountProperties() {
		return accountProperties;
	}

	public void setAccountProperties(String accountProperties) {
		this.accountProperties = accountProperties;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getOpeningBand() {
		return openingBand;
	}

	public void setOpeningBand(String openingBand) {
		this.openingBand = openingBand;
	}

	public String getTelephonenumber() {
		return telephonenumber;
	}

	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}

	public String getOpeningBandBranch() {
		return openingBandBranch;
	}

	public void setOpeningBandBranch(String openingBandBranch) {
		this.openingBandBranch = openingBandBranch;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

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

	public String getPrivince() {
		return privince;
	}

	public void setPrivince(String privince) {
		this.privince = privince;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(Integer recommendId) {
		this.recommendId = recommendId;
	}
	
	
}
