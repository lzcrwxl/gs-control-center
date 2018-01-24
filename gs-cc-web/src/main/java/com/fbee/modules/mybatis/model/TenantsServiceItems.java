package com.fbee.modules.mybatis.model;

import java.math.BigDecimal;

public class TenantsServiceItems extends TenantsServiceItemsKey {
    
	private static final long serialVersionUID = 1L;

	/**
     * 表：tenants_service_items
     * 字段：TENANT_ID
     * 注释：
     *
     * @mbggenerated
     */
	private Integer tenantId;
	/**
     * 表：tenants_service_items
     * 字段：SERVICE_PRICE_UNIT
     * 注释：
     *
     * @mbggenerated
     */
    private String servicePriceUnit;

    /**
     * 表：tenants_service_items
     * 字段：SERVICE_ITEM_NAME
     * 注释：服务项目名称
     *
     * @mbggenerated
     */
    private String serviceItemName;

    /**
     * 表：tenants_service_items
     * 字段：SERVICE_PRICE
     * 注释：服务价格
     *
     * @mbggenerated
     */
    private BigDecimal servicePrice;

    /**
     * 表：tenants_service_items
     * 字段：SERVICE_DESC
     * 注释：服务描述
     *
     * @mbggenerated
     */
    private String serviceDesc;

    /**
     * 表：tenants_service_items
     * 字段：IMAGE_URL
     * 注释：首页图片
     *
     * @mbggenerated
     */
    private String imageUrl;

    /**
     * 表：tenants_service_items
     * 字段：IS_HOT
     * 注释：是否hot
     *
     * @mbggenerated
     */
    private String isHot;

    /**
     * 表：tenants_service_items
     * 字段：SERVICE_OBJECT
     * 注释：服务对象
     *
     * @mbggenerated
     */
    private String serviceObject;

    /**
     * 表：tenants_service_items
     * 字段：SERVICE_CONTENT
     * 注释：服务内容
     *
     * @mbggenerated
     */
    private String serviceContent;

    /**
     * 表：tenants_service_items
     * 字段：IS_SHOW
     * 注释：
     *
     * @mbggenerated
     */
    private String isShow;
    
    /**
     * 表：tenants_service_items
     * 字段：IS_DEFAULT
     * 注释：
     *
     * @mbggenerated
     */
    private String isDefault;

    public String getServicePriceUnit() {
        return servicePriceUnit;
    }

    public void setServicePriceUnit(String servicePriceUnit) {
        this.servicePriceUnit = servicePriceUnit == null ? null : servicePriceUnit.trim();
    }

    public String getServiceItemName() {
        return serviceItemName;
    }

    public void setServiceItemName(String serviceItemName) {
        this.serviceItemName = serviceItemName == null ? null : serviceItemName.trim();
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc == null ? null : serviceDesc.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot == null ? null : isHot.trim();
    }

    public String getServiceObject() {
        return serviceObject;
    }

    public void setServiceObject(String serviceObject) {
        this.serviceObject = serviceObject == null ? null : serviceObject.trim();
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent == null ? null : serviceContent.trim();
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }
    
    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
    
    
}