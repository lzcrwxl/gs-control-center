package com.fbee.modules.form;

import com.fbee.modules.core.persistence.ModelSerializable;

/**
 *@Description: 总控平台-清算管理查询条件Form
 *
 *@author: zhangsq
 *@date:   2017年3月2日 上午10:31:45
 * 
 */
public class ClearForm implements ModelSerializable{
	
	private static final long serialVersionUID = 1L;
	
	private String tradeTimeLeft; // 发起时间 左
	
	private String tradeTimeRight; // 发起时间 右
	
	private String tradeType; //支付类型
	
	private String tradeChannel; //清算渠道
	
	private String status; //状态

	public String getTradeChannel() {
		return tradeChannel;
	}

	public void setTradeChannel(String tradeChannel) {
		this.tradeChannel = tradeChannel;
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

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
