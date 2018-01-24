package com.fbee.modules.jsonData.extend;



import java.sql.Date;

import com.fbee.modules.core.persistence.ModelSerializable;

/**
* @Description：预约订单列表查询Json
* @author fry
* @Date 2017年2月6日 下午2:05:50
* 
*/

public class ReserveOrdersJson implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private String orderNo;//预约编号
	private String memberName;//客户姓名
	private String memberMobile;//客户电话//手机号
	private String serviceItemCode;//服务工种
	private String serviceProvice;//服务区域省
	private String serviceCity;//服务区域市
	private String serviceCounty;//服务区域区
	private String staffName;//员工姓名
	private String age;//员工年龄
	private String zodiac;//员工属性
	private String nativePlace;//员工籍贯
	private String headImage;//员工头像
	//private Date orderTime;//下单时间
	
	//总控平台
	private String memberSex;//性别
	private String price;//服务价格
	private String tenantName;//客户来源
	private String orderStatus;//预约状态
	private Date orderTime;//下单时间
	
	
	
	public String getMemberSex() {
		return memberSex;
	}
	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberMobile() {
		return memberMobile;
	}
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	public String getServiceItemCode() {
		return serviceItemCode;
	}
	public void setServiceItemCode(String serviceItemCode) {
		this.serviceItemCode = serviceItemCode;
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
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	
	
	
	
}

