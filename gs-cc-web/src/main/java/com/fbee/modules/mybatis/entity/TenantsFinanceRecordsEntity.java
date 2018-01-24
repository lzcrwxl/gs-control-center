package com.fbee.modules.mybatis.entity;

import com.fbee.modules.mybatis.model.TenantsFinanceRecords;

public class TenantsFinanceRecordsEntity extends TenantsFinanceRecords {

	private static final long serialVersionUID = 1L;
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	
	private String tradeTimeLeft;// 创建时间-左
	
	private String tradeTimeRight;// 创建时间-右
	
	private String tenantName;// 门店名称
	
	private String tradeType;// 支付类型
	
	private String tradeChannel;// 支付渠道
	
	private String status;// 支付渠道
	
	private String orderNo;// 订单编号
	
	private String tradeFlowNo;// 支付流水号
	
	private int pageNumber;// 当前页数
	
	private int pageSize;// 每页显示几条
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTradeTimeLeft() {
		return tradeTimeLeft;
	}

	public void setTradeTimeLeft(String tradeTimeLeft) {
		this.tradeTimeLeft = tradeTimeLeft;
	}

	public String getTradeTimeRight() {
		return tradeTimeRight;
	}

	public void setTradeTimeRight(String tradeTimeRight) {
		this.tradeTimeRight = tradeTimeRight;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeChannel() {
		return tradeChannel;
	}

	public void setTradeChannel(String tradeChannel) {
		this.tradeChannel = tradeChannel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTradeFlowNo() {
		return tradeFlowNo;
	}

	public void setTradeFlowNo(String tradeFlowNo) {
		this.tradeFlowNo = tradeFlowNo;
	}

}