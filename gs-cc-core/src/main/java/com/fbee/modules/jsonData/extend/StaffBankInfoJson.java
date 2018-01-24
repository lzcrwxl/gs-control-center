package com.fbee.modules.jsonData.extend;

import com.fbee.modules.core.persistence.ModelSerializable;

/** 
* @ClassName: StaffBankJson 
* @Description: 银行信息
* @author 贺章鹏
* @date 2017年1月5日 下午3:50:27 
*  
*/
public class StaffBankInfoJson implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private String bankName;//银行名称
	
	private String cardNo;//银行卡号

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
}
