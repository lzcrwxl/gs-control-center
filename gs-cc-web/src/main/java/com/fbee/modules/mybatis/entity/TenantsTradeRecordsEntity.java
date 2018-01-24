package com.fbee.modules.mybatis.entity;

import com.fbee.modules.mybatis.model.TenantsTradeRecords;
import com.fbee.modules.mybatis.model.TradeRecords;

public class TenantsTradeRecordsEntity extends TenantsTradeRecords{
    
	private static final long serialVersionUID = 1L;
	
	private TradeRecords tradeRecords;
	
	private String tradeTimeLeft;// 创建时间-左
	
	private String tradeTimeRight;// 创建时间-右
	
	private String tenantName;// 门店名称
	
	private String tradeType;// 支付类型
	
	private String tradeChannel;// 支付渠道
	
	private String status;// 支付渠道
	
	private int pageNumber;// 当前页数
	
	private int pageSize;// 每页显示几条


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public TradeRecords getTradeRecords() {
		return tradeRecords;
	}

	public void setTradeRecords(TradeRecords tradeRecords) {
		this.tradeRecords = tradeRecords;
	}
	

}