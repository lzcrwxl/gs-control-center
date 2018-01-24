package com.fbee.modules.form;

import com.fbee.modules.core.persistence.ModelSerializable;

/**
 *@Description: 总控平台预约订单查询条件Form
 *
 *@author: zhangsq
 *@date:   2017年2月24日 下午2:19:47
 * 
 */
public class FundsForm implements ModelSerializable {

	private static final long serialVersionUID = 1L;
	
	private String tradeTimeLeft;//下单时间 左
	
	private String tradeTimeRight;//下单时间 右
	
	private String tenantName;//门店名称
	
	private String tradeType;//支付类型
	
	private String tradeChannel;//支付渠道
	
	private String orderNo;//支付渠道
	
	private String tradeFlowNo;//支付渠道

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
