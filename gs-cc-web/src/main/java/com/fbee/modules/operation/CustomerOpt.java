package com.fbee.modules.operation;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fbee.modules.core.utils.DateUtils;
import com.fbee.modules.form.CustomerSaveForm;
import com.fbee.modules.jsonData.extend.TenantsCustomerJson;
import com.fbee.modules.mybatis.entity.MembersInfoEntity;
import com.fbee.modules.mybatis.entity.TenantsCustomersBaseEntity;
import com.fbee.modules.utils.DictionarysCacheUtils;

public class CustomerOpt {
	
	/**
	 * 构建返回json
	 * @param entity
	 * @return
	 */
	public TenantsCustomerJson buildtenantscustomerJson(TenantsCustomersBaseEntity entity,int tenantId){
		
//		TenantsCustomerJson tenantscustomerJson = new TenantsCustomerJson();
//		tenantscustomerJson.setTenantId(tenantId);//租户id
//		tenantscustomerJson.setCustomerId(entity.getCustomerId());//客户id
//		tenantscustomerJson.setCustomerName(entity.getCustomerName());//客户姓名
//		tenantscustomerJson.setSex(entity.getSex());//客户性别
//		tenantscustomerJson.setCustomerMobile(entity.getCustomerMobile());//客户手机号
//		tenantscustomerJson.setServiceCity(entity.getServiceCity());//客户服务区域
//		tenantscustomerJson.setServiceCounty(entity.getServiceCounty());
//		tenantscustomerJson.setServiceProvice(entity.getServiceProvice());
//		tenantscustomerJson.setContactAddress(entity.getContactAddress());//客户地址
//		tenantscustomerJson.setFamilyCount(entity.getFamilyCount());//家庭人数
//		tenantscustomerJson.setHouseType(entity.getHouseType());//住宅类型
//		tenantscustomerJson.setHouseArea(entity.getHouseArea());//住宅面积
//		tenantscustomerJson.setChildrenCount(entity.getChildrenCount());//儿童数
//		tenantscustomerJson.setChildrenAgeRange(entity.getChildrenAgeRange());//儿童年龄段
//		tenantscustomerJson.setOlderAgeRange(entity.getOlderAgeRange());//老人数
//		tenantscustomerJson.setOlderCount(entity.getOlderCount());//老人年龄段
//		tenantscustomerJson.setSelfCares(entity.getSelfCares());//老人能否自理
//		tenantscustomerJson.setAddTime(entity.getAddTime());//创建时间
//		tenantscustomerJson.setModifyTime(entity.getModifyTime());//修改时间

//		tenantscustomerJson.setAddAccount(entity.getAddAccount());
//		tenantscustomerJson.setAgeRange(entity.getAgeRange());
//		tenantscustomerJson.setContactMobile(entity.getContactMobile());
//		tenantscustomerJson.setIsUsable(entity.getIsUsable());
//		tenantscustomerJson.setModifyAccount(entity.getModifyAccount());
//		tenantscustomerJson.setRemarks(entity.getRemarks());
//		return tenantscustomerJson;
		return null;
	}
	
	/**
	 * 构建返回用户集合的Json
	 * @param entity
	 * @param orderscount
	 * @return
	 */
	public TenantsCustomerJson buildtenantscustomerJsons(TenantsCustomersBaseEntity entity,int orderscount){
//		
//		TenantsCustomerJson tenantscustomerJson = new TenantsCustomerJson();
//		tenantscustomerJson.setCustomerId(entity.getCustomerId());//客户id
//		tenantscustomerJson.setCustomerName(entity.getCustomerName());//客户姓名
//		tenantscustomerJson.setSex(entity.getSex());//客户性别
//		tenantscustomerJson.setCustomerMobile(entity.getCustomerMobile());//客户手机号
//		tenantscustomerJson.setServiceCity(entity.getServiceCity());//客户服务区域
//		tenantscustomerJson.setServiceCounty(entity.getServiceCounty());
//		tenantscustomerJson.setServiceProvice(entity.getServiceProvice());
//		tenantscustomerJson.setContactAddress(entity.getContactAddress());//客户地址
//		tenantscustomerJson.setOrderscount(orderscount);
		/*
		 * 后期维护
		 */
//		tenantscustomerJson.setAddAccount(entity.getAddAccount());
//		tenantscustomerJson.setAddTime(entity.getAddTime());
//		tenantscustomerJson.setAgeRange(entity.getAgeRange());
//		tenantscustomerJson.setChildrenAgeRange(entity.getChildrenAgeRange());
//		tenantscustomerJson.setChildrenCount(entity.getChildrenCount());
//		tenantscustomerJson.setContactMobile(entity.getContactMobile());
//		tenantscustomerJson.setFamilyCount(entity.getFamilyCount());
//		tenantscustomerJson.setHouseArea(entity.getHouseArea());
//		tenantscustomerJson.setHouseType(entity.getHouseType());
//		tenantscustomerJson.setIsUsable(entity.getIsUsable());
//		tenantscustomerJson.setModifyAccount(entity.getModifyAccount());
//		tenantscustomerJson.setModifyTime(entity.getModifyTime());
//		tenantscustomerJson.setOlderAgeRange(entity.getOlderAgeRange());
//		tenantscustomerJson.setOlderCount(entity.getOlderCount());
//		tenantscustomerJson.setRemarks(entity.getRemarks());
//		tenantscustomerJson.setSelfCares(entity.getSelfCares());
//		return tenantscustomerJson;
		return null;
	}
	/**
	 * 总控平台用
	 * 构建返回用户集合的Json
	 * @param entity
	 * @return
	 */
	public TenantsCustomerJson zkbuildtenantscustomerJsons(MembersInfoEntity entity){
		
		TenantsCustomerJson tenantscustomerJson = new TenantsCustomerJson();
		tenantscustomerJson.setId(entity.getId());
		if (entity.getRegisterTime()!=null) {
			tenantscustomerJson.setRegisterTime(DateUtils.dateToStr(entity.getRegisterTime(), "yyyy-MM-dd HH:mm:ss"));//注册时间
		}else {
			tenantscustomerJson.setRegisterTime("");
		}
		
		if (StringUtils.isNoneBlank(entity.getName())) {
			tenantscustomerJson.setName(entity.getName());//客户姓名
		}else {
			tenantscustomerJson.setName("");
		}
		
		if (StringUtils.isNotBlank(entity.getSex())) {
			tenantscustomerJson.setSex(DictionarysCacheUtils.getSexName(entity.getSex()));//客户性别
		}else {
			tenantscustomerJson.setSex("");
		}
		
		if (StringUtils.isNotBlank(entity.getMobile())) {
			tenantscustomerJson.setMobile(entity.getMobile());//客户手机号
		}else {
			tenantscustomerJson.setMobile("");
		}
		
		if (StringUtils.isNotBlank(entity.getCity())) {
			tenantscustomerJson.setCity(DictionarysCacheUtils.getCityName(entity.getCity()));//客户服务区域，市
		}else {
			tenantscustomerJson.setCity("");
		}
		
		if (StringUtils.isNotBlank(entity.getCounty())) {
			tenantscustomerJson.setCounty(DictionarysCacheUtils.getCountyName(entity.getCounty()));//客户服务区域，区
		}else {
			tenantscustomerJson.setCounty("");
		}
		
		if (StringUtils.isNotBlank(entity.getProvice())) {
			tenantscustomerJson.setProvice(DictionarysCacheUtils.getProviceName(entity.getProvice()));//客户服务区域，省
		}else {
			tenantscustomerJson.setProvice("");
		}
		
		if (entity.getLastLoginTime()!=null) {
			tenantscustomerJson.setLastLoginTime(DateUtils.dateToStr(entity.getLastLoginTime(), "yyyy-MM-dd HH:mm:ss"));//最后登陆时间
		}else {
			tenantscustomerJson.setLastLoginTime("");
		}
		
		
		
		
	
		return tenantscustomerJson;
	}

	
	/**
	 * 构建新增Entity
	 * @param tenantId
	 * @param customersaveform
	 */
	public TenantsCustomersBaseEntity buildSaveTenantsCustomersBaseEntity(Integer tenantId, CustomerSaveForm customersaveform,String loginAccount) {
		
		TenantsCustomersBaseEntity tenantsCustomersBaseEntity = new TenantsCustomersBaseEntity();
		tenantsCustomersBaseEntity.setTenantId(tenantId);//租户id
		tenantsCustomersBaseEntity.setCustomerName(customersaveform.getCustomerName());//客户姓名
		tenantsCustomersBaseEntity.setSex(customersaveform.getSex());//客户性别
		tenantsCustomersBaseEntity.setCustomerMobile(customersaveform.getCustomerMobile());//客户手机号
		tenantsCustomersBaseEntity.setServiceCity(customersaveform.getServiceCity());//客户服务区域
		tenantsCustomersBaseEntity.setServiceCounty(customersaveform.getServiceCounty());
		tenantsCustomersBaseEntity.setServiceProvice(customersaveform.getServiceProvice());
		tenantsCustomersBaseEntity.setContactAddress(customersaveform.getContactAddress());//客户地址
		tenantsCustomersBaseEntity.setAddAccount(loginAccount);//添加人
		tenantsCustomersBaseEntity.setAddTime(new Date());//添加时间
		tenantsCustomersBaseEntity.setChildrenAgeRange(customersaveform.getChildrenAgeRange());//儿童年龄段
		tenantsCustomersBaseEntity.setChildrenCount(customersaveform.getChildrenCount());//儿童数
		tenantsCustomersBaseEntity.setContactMobile(customersaveform.getContactMobile());//紧急联系电话
		tenantsCustomersBaseEntity.setFamilyCount(customersaveform.getFamilyCount());//家庭人数
		tenantsCustomersBaseEntity.setHouseArea(customersaveform.getHouseArea());//住宅面积
		tenantsCustomersBaseEntity.setHouseType(customersaveform.getHouseType());//住宅类型
		tenantsCustomersBaseEntity.setOlderAgeRange(customersaveform.getOlderAgeRange());//老人年龄段
		tenantsCustomersBaseEntity.setOlderCount(customersaveform.getOlderCount());//老人数
		tenantsCustomersBaseEntity.setSelfCares(customersaveform.getSelfCares());//老人能否自理
		tenantsCustomersBaseEntity.setIsUsable("1");//是否可用,默认可用
		tenantsCustomersBaseEntity.setRemarks(customersaveform.getRemarks());//备注
		
//		tenantsCustomersBaseEntity.setAgeRange(customersaveform.getAgeRange());//年龄段
//		tenantsCustomersBaseEntity.setModifyAccount(customersaveform.getModifyAccount());//修改人
//		tenantsCustomersBaseEntity.setModifyTime(customersaveform.getModifyTime());//修改时间
		
		return tenantsCustomersBaseEntity;
	}
	
	/**
	 * 构建修改Entity
	 * @param tenantId
	 * @param customersaveform
	 * @param loginAccount
	 * @return
	 */
	public TenantsCustomersBaseEntity buildUpdateTenantsCustomersBaseEntity(String customerid,CustomerSaveForm customersaveform,String loginAccount) {
		
		TenantsCustomersBaseEntity tenantsCustomersBaseEntity = new TenantsCustomersBaseEntity();
		tenantsCustomersBaseEntity.setCustomerId(Integer.parseInt(customerid));//客户id
		tenantsCustomersBaseEntity.setCustomerName(customersaveform.getCustomerName());//客户姓名
		tenantsCustomersBaseEntity.setSex(customersaveform.getSex());//客户性别
		tenantsCustomersBaseEntity.setCustomerMobile(customersaveform.getCustomerMobile());//客户手机号
		tenantsCustomersBaseEntity.setServiceCity(customersaveform.getServiceCity());//客户服务区域
		tenantsCustomersBaseEntity.setServiceCounty(customersaveform.getServiceCounty());
		tenantsCustomersBaseEntity.setServiceProvice(customersaveform.getServiceProvice());
		tenantsCustomersBaseEntity.setContactAddress(customersaveform.getContactAddress());//客户地址
		tenantsCustomersBaseEntity.setModifyAccount(loginAccount);//修改人
		tenantsCustomersBaseEntity.setModifyTime(new Date());//修改时间
		tenantsCustomersBaseEntity.setFamilyCount(customersaveform.getFamilyCount());//家庭人数
		tenantsCustomersBaseEntity.setHouseArea(customersaveform.getHouseArea());//住宅面积
		tenantsCustomersBaseEntity.setHouseType(customersaveform.getHouseType());//住宅类型
		tenantsCustomersBaseEntity.setOlderAgeRange(customersaveform.getOlderAgeRange());//老人年龄段
		tenantsCustomersBaseEntity.setOlderCount(customersaveform.getOlderCount());//老人数
		tenantsCustomersBaseEntity.setSelfCares(customersaveform.getSelfCares());//老人能否自理
		tenantsCustomersBaseEntity.setChildrenAgeRange(customersaveform.getChildrenAgeRange());//儿童年龄段
		tenantsCustomersBaseEntity.setChildrenCount(customersaveform.getChildrenCount());//儿童数
		
//		tenantsCustomersBaseEntity.setContactMobile(customersaveform.getContactMobile());//紧急联系电话	
//		tenantsCustomersBaseEntity.setRemarks(customersaveform.getRemarks());//备注
//		tenantsCustomersBaseEntity.setAgeRange(customersaveform.getAgeRange());//年龄段
//		tenantsCustomersBaseEntity.setTenantId(tenantId);//租户id
//		tenantsCustomersBaseEntity.setAddAccount(loginAccount);//添加人
//		tenantsCustomersBaseEntity.setAddTime(new Date());//添加时间
//		tenantsCustomersBaseEntity.setIsUsable("1");//是否可用,默认可用
		
		return tenantsCustomersBaseEntity;
	}
	
}
