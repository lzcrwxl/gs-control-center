package com.fbee.modules.mybatis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TradeRecords implements Serializable{
    
	private static final long serialVersionUID = 1L;

	/**
     * 表：trade_records
     * 字段：TRADE_FLOW_NO
     * 注释：交易流水号
     *
     * @mbggenerated
     */
    private String tradeFlowNo;

    /**
     * 表：trade_records
     * 字段：TRADE_TYPE
     * 注释：交易类型
     *
     * @mbggenerated
     */
    private String tradeType;

    /**
     * 表：trade_records
     * 字段：TRADE_AMOUNT
     * 注释：交易金额
     *
     * @mbggenerated
     */
    private BigDecimal tradeAmount;

    /**
     * 表：trade_records
     * 字段：COUNTER_FEE
     * 注释：交易手续费
     *
     * @mbggenerated
     */
    private BigDecimal counterFee;

    /**
     * 表：trade_records
     * 字段：TRADE_TIME
     * 注释：交易时间
     *
     * @mbggenerated
     */
    private Date tradeTime;

    /**
     * 表：trade_records
     * 字段：TRADE_STATIS
     * 注释：交易状态
     *
     * @mbggenerated
     */
    private String tradeStatis;
    
    private String initatorType;

   
   
    /**
     * 表：trade_records
     * 字段：FAILURE_MSG
     * 注释：失败原因
     *
     * @mbggenerated
     */
    private String failureMsg;

    /**
     * 表：trade_records
     * 字段：TRADE_RESULT_CODE
     * 注释：交易返回code
     *
     * @mbggenerated
     */
    private String tradeResultCode;
    
    private String isUsable;

    /**
     * 表：trade_records
     * 字段：MODIFY_TIME
     * 注释：修改时间
     *
     * @mbggenerated
     */
    private Date addTime;
    
    private String addAccount;
    
    private Date modifyTime;
    
    private String modifyAccount;
    /**
     * 表：trade_records
     * 字段：REQUEST_RESULT
     * 注释：请求结果
     *
     * @mbggenerated
     */
    private String requestResult;

    /**
     * 表：trade_records
     * 字段：IN_ORDER_NO
     * 注释：内部订单号
     *
     * @mbggenerated
     */
    private String orderNo;
    
    private String tradeChannel;
    
    private String tradeChannelNo;

    /**
     * 表：trade_records
     * 字段：OUT_ORDER_NO
     * 注释：外部交易订单号
     *
     * @mbggenerated
     */
    private String outOrderNo;

    public String getTradeFlowNo() {
        return tradeFlowNo;
    }

    public void setTradeFlowNo(String tradeFlowNo) {
        this.tradeFlowNo = tradeFlowNo == null ? null : tradeFlowNo.trim();
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getCounterFee() {
        return counterFee;
    }

    public void setCounterFee(BigDecimal counterFee) {
        this.counterFee = counterFee;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getTradeStatis() {
        return tradeStatis;
    }

    public void setTradeStatis(String tradeStatis) {
        this.tradeStatis = tradeStatis == null ? null : tradeStatis.trim();
    }

   

    public String getFailureMsg() {
        return failureMsg;
    }

    public void setFailureMsg(String failureMsg) {
        this.failureMsg = failureMsg == null ? null : failureMsg.trim();
    }

    public String getTradeResultCode() {
        return tradeResultCode;
    }

    public void setTradeResultCode(String tradeResultCode) {
        this.tradeResultCode = tradeResultCode == null ? null : tradeResultCode.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRequestResult() {
        return requestResult;
    }

    public void setRequestResult(String requestResult) {
        this.requestResult = requestResult == null ? null : requestResult.trim();
    }

    public String getInitatorType() {
		return initatorType;
	}

	public void setInitatorType(String initatorType) {
		this.initatorType = initatorType;
	}

	public String getIsUsable() {
		return isUsable;
	}

	public void setIsUsable(String isUsable) {
		this.isUsable = isUsable;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTradeChannel() {
		return tradeChannel;
	}

	public void setTradeChannel(String tradeChannel) {
		this.tradeChannel = tradeChannel;
	}

	public String getTradeChannelNo() {
		return tradeChannelNo;
	}

	public void setTradeChannelNo(String tradeChannelNo) {
		this.tradeChannelNo = tradeChannelNo;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
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

	public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo == null ? null : outOrderNo.trim();
    }
}