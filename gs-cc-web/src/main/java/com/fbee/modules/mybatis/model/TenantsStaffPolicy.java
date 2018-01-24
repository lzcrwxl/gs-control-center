package com.fbee.modules.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class TenantsStaffPolicy extends TenantsStaffPolicyKey {
	private static final long serialVersionUID = 1L;

	/**
     * 表：tenants_staff_policy
     * 字段：POLICY_NAME
     * 注释：保单名称
     *
     * @mbggenerated
     */
    private String policyName;

    /**
     * 表：tenants_staff_policy
     * 字段：POLICY_AMOUNT
     * 注释：保单金额
     *
     * @mbggenerated
     */
    private BigDecimal policyAmount;

    /**
     * 表：tenants_staff_policy
     * 字段：POLICY_AGENCY
     * 注释：保单机构
     *
     * @mbggenerated
     */
    private String policyAgency;

    /**
     * 表：tenants_staff_policy
     * 字段：add_time
     * 注释：添加时间
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：tenants_staff_policy
     * 字段：add_account
     * 注释：添加人
     *
     * @mbggenerated
     */
    private String addAccount;

    /**
     * 表：tenants_staff_policy
     * 字段：modify_time
     * 注释：修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：tenants_staff_policy
     * 字段：modify_account
     * 注释：修改人
     *
     * @mbggenerated
     */
    private String modifyAccount;

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName == null ? null : policyName.trim();
    }

    public BigDecimal getPolicyAmount() {
        return policyAmount;
    }

    public void setPolicyAmount(BigDecimal policyAmount) {
        this.policyAmount = policyAmount;
    }

    public String getPolicyAgency() {
        return policyAgency;
    }

    public void setPolicyAgency(String policyAgency) {
        this.policyAgency = policyAgency == null ? null : policyAgency.trim();
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