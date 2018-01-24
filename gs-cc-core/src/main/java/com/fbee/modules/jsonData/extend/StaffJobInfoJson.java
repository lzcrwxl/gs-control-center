package com.fbee.modules.jsonData.extend;

import java.util.List;

import com.fbee.modules.core.persistence.ModelSerializable;

/** 
* @ClassName: StaffJobInfoJson 
* @Description: 求职信息
* @author 贺章鹏
* @date 2017年1月5日 下午3:52:16 
*  
*/
public class StaffJobInfoJson implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private String manageWay;//管理方式
	
	private String serviceProvice;//省
	
	private String serviceCity;//市
	
	private String serviceCounty;//区
	
	private String workExperience;//工作经历
	
	private String selfEvaluation;//自我评价
	
	private String teacherEvaluation;//老师评价
	
	private String featureValue;//个人特点
	
	private String petFeeding;//不做家庭：是否喂养宠物
	
	private String elderlySupport;//不做家庭：是否有老人

	private List<StaffServiceItemJson> serviceItemList;//服务工种
	
	public String getManageWay() {
		return manageWay;
	}

	public void setManageWay(String manageWay) {
		this.manageWay = manageWay;
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

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getSelfEvaluation() {
		return selfEvaluation;
	}

	public void setSelfEvaluation(String selfEvaluation) {
		this.selfEvaluation = selfEvaluation;
	}

	public String getTeacherEvaluation() {
		return teacherEvaluation;
	}

	public void setTeacherEvaluation(String teacherEvaluation) {
		this.teacherEvaluation = teacherEvaluation;
	}

	public List<StaffServiceItemJson> getServiceItemList() {
		return serviceItemList;
	}

	public void setServiceItemList(List<StaffServiceItemJson> serviceItemList) {
		this.serviceItemList = serviceItemList;
	}

	public String getFeatureValue() {
		return featureValue;
	}

	public void setFeatureValue(String featureValue) {
		this.featureValue = featureValue;
	}

	public String getPetFeeding() {
		return petFeeding;
	}

	public void setPetFeeding(String petFeeding) {
		this.petFeeding = petFeeding;
	}

	public String getElderlySupport() {
		return elderlySupport;
	}

	public void setElderlySupport(String elderlySupport) {
		this.elderlySupport = elderlySupport;
	}
}
