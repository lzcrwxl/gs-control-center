package com.fbee.modules.mybatis.model;

import java.util.Date;

public class OrderShareInfo {
    /**
     * 表：order_share_info
     * 字段：ORDER_NO
     * 注释：订单流水号
     *
     * @mbggenerated
     */
    private String orderNo;

    /**
     * 表：order_share_info
     * 字段：TENANT_ID
     * 注释：租户id
     *
     * @mbggenerated
     */
    private Integer tenantId;

    /**
     * 表：order_share_info
     * 字段：ORDER_NAME
     * 注释：订单名称
     *
     * @mbggenerated
     */
    private String orderName;

    /**
     * 表：order_share_info
     * 字段：AGE_REQUIREMENT
     * 注释：年龄要求
     *
     * @mbggenerated
     */
    private String ageRequirement;

    /**
     * 表：order_share_info
     * 字段：SALARY_SITUATION
     * 注释：工资情况
     *
     * @mbggenerated
     */
    private String salarySituation;

    /**
     * 表：order_share_info
     * 字段：REMARK
     * 注释：备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 表：order_share_info
     * 字段：STATUS
     * 注释：状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 表：order_share_info
     * 字段：SINGLE_REWARD
     * 注释：成单奖励
     *
     * @mbggenerated
     */
    private String singleReward;

    /**
     * 表：order_share_info
     * 字段：add_time
     * 注释：添加时间
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：order_share_info
     * 字段：add_account
     * 注释：添加人
     *
     * @mbggenerated
     */
    private String addAccount;

    /**
     * 表：order_share_info
     * 字段：modify_time
     * 注释：修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：order_share_info
     * 字段：modify_account
     * 注释：修改人
     *
     * @mbggenerated
     */
    private String modifyAccount;

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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName == null ? null : orderName.trim();
    }

    public String getAgeRequirement() {
        return ageRequirement;
    }

    public void setAgeRequirement(String ageRequirement) {
        this.ageRequirement = ageRequirement == null ? null : ageRequirement.trim();
    }

    public String getSalarySituation() {
        return salarySituation;
    }

    public void setSalarySituation(String salarySituation) {
        this.salarySituation = salarySituation == null ? null : salarySituation.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getSingleReward() {
        return singleReward;
    }

    public void setSingleReward(String singleReward) {
        this.singleReward = singleReward == null ? null : singleReward.trim();
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
}