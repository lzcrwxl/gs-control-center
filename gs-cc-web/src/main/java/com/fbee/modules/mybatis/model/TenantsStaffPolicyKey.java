package com.fbee.modules.mybatis.model;

import java.io.Serializable;

public class TenantsStaffPolicyKey implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
     * 表：tenants_staff_policy
     * 字段：STAFF_ID
     * 注释：员工id
     *
     * @mbggenerated
     */
    private Integer staffId;

    /**
     * 表：tenants_staff_policy
     * 字段：TENANT_ID
     * 注释：租户ID
     *
     * @mbggenerated
     */
    private Integer tenantId;

    /**
     * 表：tenants_staff_policy
     * 字段：POLICY_NO
     * 注释：保单号
     *
     * @mbggenerated
     */
    private String policyNo;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo == null ? null : policyNo.trim();
    }
}