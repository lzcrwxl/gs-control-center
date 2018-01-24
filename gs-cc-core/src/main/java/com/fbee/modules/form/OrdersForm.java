package com.fbee.modules.form;

import com.fbee.modules.core.persistence.ModelSerializable;

public class OrdersForm implements ModelSerializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 表：orders
     * 字段：ORDER_NO
     * 注释：订单流水号
     *
     * @mbggenerated
     */
    private String orderNo;
	
    /**
     * 表：orders
     * 字段：TENANT_ID
     * 注释：租户id
     *
     * @mbggenerated
     */
    private Integer tenantId;
    
    /**
     * 表：orders
     * 字段：ORDER_STATUS
     * 注释：订单状态 01待支付定金 02 待面试 03 待支付尾款 04 订单完成 05 已取消 06 待完成 07 已结单
     *
     * @mbggenerated
     */
    private String orderStatus;
    
    
    /**
     * 表：orders
     * 字段：SERVICE_ITEM_CODE
     * 注释：服务工种
     *
     * @mbggenerated
     */
    private String serviceItemCode;
    
    /**
     * 表：orders
     * 字段：ORDER_SOURCE
     * 注释：订单来源
     *
     * @mbggenerated
     */
    private String orderSource;
    
    /**
     * 注释：条件查询开始时间
     *
     * @mbggenerated
     */
    private String orderStartTime;
    
    /**
     * 注释：条件查询结束时间
     *
     * @mbggenerated
     */
    private String orderEndTime;
    
    /**
     * 注释：条件查询客户姓名
     *
     * @mbggenerated
     */
    private String memberName;
    
    /**
     * 注释：条件查询手机号
     *
     * @mbggenerated
     */
    private String memberMobile;
    
    /**
     * 页数
     */
    private Integer pageNum;
    
    /**
     * 页面大小
     */
    private Integer pageSize;
    
    
    private String shareOrderNo;//关联订单编号
	

	public String getShareOrderNo() {
		return shareOrderNo;
	}

	public void setShareOrderNo(String shareOrderNo) {
		this.shareOrderNo = shareOrderNo;
	}

	public String getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public String getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(String orderEndTime) {
		this.orderEndTime = orderEndTime;
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

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getServiceItemCode() {
		return serviceItemCode;
	}

	public void setServiceItemCode(String serviceItemCode) {
		this.serviceItemCode = serviceItemCode;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

}
