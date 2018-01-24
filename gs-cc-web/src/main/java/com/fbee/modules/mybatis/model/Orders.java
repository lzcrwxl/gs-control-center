package com.fbee.modules.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class Orders {
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
     * 字段：MEMBER_ID
     * 注释：客户ID
     *
     * @mbggenerated
     */
    private Integer memberId;

    /**
     * 表：orders
     * 字段：AMOUNT
     * 注释：订单金额
     *
     * @mbggenerated
     */
    private BigDecimal amount;

    /**
     * 表：orders
     * 字段：ORDER_TIME
     * 注释：提交时间
     *
     * @mbggenerated
     */
    private Date orderTime;

    /**
     * 表：orders
     * 字段：ORDER_STATUS
     * 注释：订单状态 01待支付定金 02 待面试 03 待支付尾款 04 订单完成 05 退款
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
     * 字段：STAFF_ID
     * 注释：阿姨id
     *
     * @mbggenerated
     */
    private Integer staffId;

    /**
     * 表：orders
     * 字段：ORDER_SOURCE
     * 注释：订单来源
     *
     * @mbggenerated
     */
    private String orderSource;

    /**
     * 表：orders
     * 字段：ORDER_DEPOSIT
     * 注释：支付定金
     *
     * @mbggenerated
     */
    private BigDecimal orderDeposit;

    /**
     * 表：orders
     * 字段：ORDER_BALANCE
     * 注释：支付尾款
     *
     * @mbggenerated
     */
    private BigDecimal orderBalance;

    /**
     * 表：orders
     * 字段：DEPOSIT_DATE
     * 注释：定金支付时间
     *
     * @mbggenerated
     */
    private Date depositDate;

    /**
     * 表：orders
     * 字段：IDEPOSIT_OVER
     * 注释：定金是否支付
     *
     * @mbggenerated
     */
    private String idepositOver;

    /**
     * 表：orders
     * 字段：IS_INTERVIEW
     * 注释：是否已面试
     *
     * @mbggenerated
     */
    private String isInterview;

    /**
     * 表：orders
     * 字段：PASS_VIEW_DATE
     * 注释：通过面试时间
     *
     * @mbggenerated
     */
    private Date passViewDate;

    /**
     * 表：orders
     * 字段：BALANCE_DATE
     * 注释：尾款支付时间
     *
     * @mbggenerated
     */
    private Date balanceDate;

    /**
     * 表：orders
     * 字段：IS_LOCK
     * 注释：是否锁定(预留字段)
     *
     * @mbggenerated
     */
    private String isLock;

    /**
     * 表：orders
     * 字段：add_time
     * 注释：添加时间
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：orders
     * 字段：add_account
     * 注释：添加人
     *
     * @mbggenerated
     */
    private String addAccount;

    /**
     * 表：orders
     * 字段：modify_time
     * 注释：修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：orders
     * 字段：modify_account
     * 注释：修改人
     *
     * @mbggenerated
     */
    private String modifyAccount;

    /**
     * 表：orders
     * 字段：RESERVE_ORDER_NO
     * 注释：关联预约编号
     *
     * @mbggenerated
     */
    private String reserveOrderNo;

    /**
     * 表：orders
     * 字段：ORDER_TYPE
     * 注释：订单类型
     *
     * @mbggenerated
     */
    private String orderType;

    /**
     * 表：orders
     * 字段：MEMBER_MOBILE
     * 注释：
     *
     * @mbggenerated
     */
    private String memberMobile;

    /**
     * 表：orders
     * 字段：REMARK
     * 注释：
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 表：orders
     * 字段：BALANCE_OVER
     * 注释：尾款是否支付
     *
     * @mbggenerated
     */
    private String balanceOver;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getServiceItemCode() {
        return serviceItemCode;
    }

    public void setServiceItemCode(String serviceItemCode) {
        this.serviceItemCode = serviceItemCode == null ? null : serviceItemCode.trim();
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource == null ? null : orderSource.trim();
    }

    public BigDecimal getOrderDeposit() {
        return orderDeposit;
    }

    public void setOrderDeposit(BigDecimal orderDeposit) {
        this.orderDeposit = orderDeposit;
    }

    public BigDecimal getOrderBalance() {
        return orderBalance;
    }

    public void setOrderBalance(BigDecimal orderBalance) {
        this.orderBalance = orderBalance;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public String getIdepositOver() {
        return idepositOver;
    }

    public void setIdepositOver(String idepositOver) {
        this.idepositOver = idepositOver == null ? null : idepositOver.trim();
    }

    public String getIsInterview() {
        return isInterview;
    }

    public void setIsInterview(String isInterview) {
        this.isInterview = isInterview == null ? null : isInterview.trim();
    }

    public Date getPassViewDate() {
        return passViewDate;
    }

    public void setPassViewDate(Date passViewDate) {
        this.passViewDate = passViewDate;
    }

    public Date getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock == null ? null : isLock.trim();
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
        this.addAccount = addAccount == null ? null : addAccount.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyAccount() {
        return modifyAccount;
    }

    public void setModifyAccount(String modifyAccount) {
        this.modifyAccount = modifyAccount == null ? null : modifyAccount.trim();
    }

    public String getReserveOrderNo() {
        return reserveOrderNo;
    }

    public void setReserveOrderNo(String reserveOrderNo) {
        this.reserveOrderNo = reserveOrderNo == null ? null : reserveOrderNo.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile == null ? null : memberMobile.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getBalanceOver() {
        return balanceOver;
    }

    public void setBalanceOver(String balanceOver) {
        this.balanceOver = balanceOver == null ? null : balanceOver.trim();
    }
}