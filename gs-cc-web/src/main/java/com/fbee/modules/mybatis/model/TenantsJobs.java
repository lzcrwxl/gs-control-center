package com.fbee.modules.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class TenantsJobs implements Serializable{
    
	private static final long serialVersionUID = 1L;

	/**
     * 表：tenants_jobs
     * 字段：id
     * 注释：主键ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 表：tenants_jobs
     * 字段：TENANT_ID
     * 注释：租户id
     *
     * @mbggenerated
     */
    private Integer tenantId;

    /**
     * 表：tenants_jobs
     * 字段：SERVICE_TYPE
     * 注释：服务工种
     *
     * @mbggenerated
     */
    private String serviceType;

    /**
     * 表：tenants_jobs
     * 字段：POSITION_NAME
     * 注释：岗位名称
     *
     * @mbggenerated
     */
    private String positionName;

    /**
     * 表：tenants_jobs
     * 字段：AGE
     * 注释：年龄
     *
     * @mbggenerated
     */
    private Integer age;

    /**
     * 表：tenants_jobs
     * 字段：SALARY
     * 注释：工资
     *
     * @mbggenerated
     */
    private String salary;

    /**
     * 表：tenants_jobs
     * 字段：DEMAND
     * 注释：要求
     *
     * @mbggenerated
     */
    private String demand;

    /**
     * 表：tenants_jobs
     * 字段：is_usable
     * 注释：是否可用
     *
     * @mbggenerated
     */
    private String isUsable;

    /**
     * 表：tenants_jobs
     * 字段：add_time
     * 注释：添加时间
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：tenants_jobs
     * 字段：add_account
     * 注释：添加人
     *
     * @mbggenerated
     */
    private String addAccount;

    /**
     * 表：tenants_jobs
     * 字段：modify_time
     * 注释：修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：tenants_jobs
     * 字段：modify_account
     * 注释：修改人
     *
     * @mbggenerated
     */
    private String modifyAccount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand == null ? null : demand.trim();
    }

    public String getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable == null ? null : isUsable.trim();
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