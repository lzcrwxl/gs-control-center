package com.fbee.modules.form;

import java.math.BigDecimal;
import java.util.List;

import com.fbee.modules.core.persistence.ModelSerializable;
import com.fbee.modules.form.extend.SelfFeatureForm;

/** 
* @ClassName: StaffQueryForm 
* @Description: 员工查询表单
* @author 贺章鹏
* @date 2016年12月29日 下午4:53:29 
*  
*/
public class StaffQueryForm implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private String staffName;//家政员姓名
	
	private String mobile;//家政员手机号码
	
	private String serviceType;//服务工种
	
	private String skillKeys;//技能点
	
	private BigDecimal minPrice;//价格区间[0,400)-最小值
	
	private BigDecimal maxPrice;//价格区间[0,400)-最大值
	
	private BigDecimal minExperience;//经验-最小值
	
	private BigDecimal maxExperience;//经验-最大值
	
	private Integer minAge;//年龄段[0,10)-最小值
	
	private Integer maxAge;//年龄段[0,10)-最大值
	
	private String zodiac;//属相
	
	private String nativePlace;//籍贯
	
	private String workStatus;//工作状态
	
	private String onOffShelf;//上下架
	
	private List<SelfFeatureForm> features;//个人特点
	
	private String education;//学历
	
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public String getSkillKeys() {
		return skillKeys;
	}

	public void setSkillKeys(String skillKeys) {
		this.skillKeys = skillKeys;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public BigDecimal getMinExperience() {
		return minExperience;
	}

	public void setMinExperience(BigDecimal minExperience) {
		this.minExperience = minExperience;
	}

	public BigDecimal getMaxExperience() {
		return maxExperience;
	}

	public void setMaxExperience(BigDecimal maxExperience) {
		this.maxExperience = maxExperience;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public String getZodiac() {
		return zodiac;
	}

	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getOnOffShelf() {
		return onOffShelf;
	}

	public void setOnOffShelf(String onOffShelf) {
		this.onOffShelf = onOffShelf;
	}

	public List<SelfFeatureForm> getFeatures() {
		return features;
	}

	public void setFeatures(List<SelfFeatureForm> features) {
		this.features = features;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
}
