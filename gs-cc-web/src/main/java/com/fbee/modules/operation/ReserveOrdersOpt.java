package com.fbee.modules.operation;

import java.util.Date;

import com.fbee.modules.core.utils.StringUtils;
import com.fbee.modules.form.ReserveOrderDetailsForm;
import com.fbee.modules.mybatis.entity.OrderCustomersInfoEntity;
import com.fbee.modules.mybatis.entity.OrdersEntity;
import com.fbee.modules.mybatis.entity.ReserveOrdersCustomerInfoEntity;
import com.fbee.modules.mybatis.entity.ReserveOrdersEntity;

/**
 * 
 * @description: 预约订单操作类
 * @author  fanry
 * @date: 2017年2月14日 下午3:17:36
 */
public class ReserveOrdersOpt {
	
	/**
	 * 封装ReserveOrdersEntity
	 * @param reserveOrderDetailsForm
	 * @return
	 */
	public ReserveOrdersEntity buildReserveOrdersEntity(ReserveOrderDetailsForm reserveOrderDetailsForm) {
		ReserveOrdersEntity  reserveOrdersEntity = new ReserveOrdersEntity();
		
		reserveOrdersEntity.setOrderNo(reserveOrderDetailsForm.getOrderNo());
		reserveOrdersEntity.setMemberMobile(reserveOrderDetailsForm.getMemberMobile());
		reserveOrdersEntity.setMemberSex(reserveOrderDetailsForm.getMemberSex());
		reserveOrdersEntity.setMemberName(reserveOrderDetailsForm.getMemberName());
		
		reserveOrdersEntity.setOrderStatus("02");
		reserveOrdersEntity.setServiceItemCode(reserveOrderDetailsForm.getServiceItemCode());
		reserveOrdersEntity.setHandleOrderTime(new Date());
		return reserveOrdersEntity;
	}
	/**
	 * 封装ReserveOrdersCustomerInfoEntity
	 * @param reserveOrderDetailsForm
	 * @return
	 */
	public ReserveOrdersCustomerInfoEntity buildReserveOrdersCustomerInfoEntity(ReserveOrderDetailsForm reserveOrderDetailsForm) {
		
		ReserveOrdersCustomerInfoEntity reserveOrdersCustomerInfoEntity = new ReserveOrdersCustomerInfoEntity();
		
		reserveOrdersCustomerInfoEntity.setOrderNo(reserveOrderDetailsForm.getOrderNo());

		reserveOrdersCustomerInfoEntity.setChildrenCount(reserveOrderDetailsForm.getChildrenCount());
		reserveOrdersCustomerInfoEntity.setChildrenAgeRange(reserveOrderDetailsForm.getChildrenAgeRange());

		reserveOrdersCustomerInfoEntity.setFamilyCount(reserveOrderDetailsForm.getFamilyCount());
		
		reserveOrdersCustomerInfoEntity.setServiceStart(reserveOrderDetailsForm.getServiceStart());
		reserveOrdersCustomerInfoEntity.setServiceEnd(reserveOrderDetailsForm.getServiceEnd());
		
		reserveOrdersCustomerInfoEntity.setHouseType(reserveOrderDetailsForm.getHouseType());
		reserveOrdersCustomerInfoEntity.setHouseArea(reserveOrderDetailsForm.getHouseArea());
		
		reserveOrdersCustomerInfoEntity.setExpectedBirth(reserveOrderDetailsForm.getExpectedBirth());
		reserveOrdersCustomerInfoEntity.setIsBabyBorn(reserveOrderDetailsForm.getIsBabyBorn());
		
		reserveOrdersCustomerInfoEntity.setOlderCount(reserveOrderDetailsForm.getOlderCount());
		reserveOrdersCustomerInfoEntity.setOlderAgeRange(reserveOrderDetailsForm.getOlderAgeRange());
		
		reserveOrdersCustomerInfoEntity.setSalaryMax(reserveOrderDetailsForm.getSalaryMax());
		reserveOrdersCustomerInfoEntity.setSalaryMin(reserveOrderDetailsForm.getSalaryMin());
		
		reserveOrdersCustomerInfoEntity.setServiceProvice(reserveOrderDetailsForm.getServiceProvice());
		reserveOrdersCustomerInfoEntity.setServiceCity(reserveOrderDetailsForm.getServiceCity());
		reserveOrdersCustomerInfoEntity.setServiceCounty(reserveOrderDetailsForm.getServiceCounty());
		reserveOrdersCustomerInfoEntity.setServiceAddress(reserveOrderDetailsForm.getServiceAddress());
		
		
		String[] cookingReqirements=reserveOrderDetailsForm.getCookingReqirements();
		String[] languageRequirements=reserveOrderDetailsForm.getLanguageRequirements();
		String[] personalityRequirements=reserveOrderDetailsForm.getPersonalityRequirements();
		
		reserveOrdersCustomerInfoEntity.setCookingReqirements(StringUtils.strAppend(cookingReqirements));
		reserveOrdersCustomerInfoEntity.setLanguageRequirements(StringUtils.strAppend(languageRequirements));
		reserveOrdersCustomerInfoEntity.setPersonalityRequirements(StringUtils.strAppend(personalityRequirements));
		reserveOrdersCustomerInfoEntity.setSpecialNeeds(reserveOrderDetailsForm.getSpecialNeeds());
		
		reserveOrdersCustomerInfoEntity.setServiceType(reserveOrderDetailsForm.getServiceType());
		
		reserveOrdersCustomerInfoEntity.setPetRaising(reserveOrderDetailsForm.getPetRaising());
		
		return reserveOrdersCustomerInfoEntity;
	}


	/**
	 * 封装OrdersEntity
	 * @param reserveOrderDetailsForm
	 * @return
	 */
	public OrdersEntity buildOrdersEntity(ReserveOrderDetailsForm reserveOrderDetailsForm) {
		OrdersEntity  ordersEntity = new OrdersEntity();
		
		ordersEntity.setOrderNo("123");
		ordersEntity.setTenantId(reserveOrderDetailsForm.getTenantId());
		ordersEntity.setStaffId(reserveOrderDetailsForm.getStaffId());
		ordersEntity.setMemberId(reserveOrderDetailsForm.getMemberId());
		ordersEntity.setOrderStatus(reserveOrderDetailsForm.getNewOrderStatus());
		ordersEntity.setServiceItemCode(reserveOrderDetailsForm.getServiceItemCode());
		ordersEntity.setAmount(reserveOrderDetailsForm.getAmount());
		ordersEntity.setOrderTime(new Date());
		ordersEntity.setIsLock("1");
		ordersEntity.setServiceItemCode(reserveOrderDetailsForm.getServiceItemCode());
		return ordersEntity;
	}
	/**
	 * 封装OrderCustomersInfoEntity
	 * @param reserveOrderDetailsForm
	 * @return
	 */
	public OrderCustomersInfoEntity buildOrderCustomersInfoEntity(ReserveOrderDetailsForm reserveOrderDetailsForm) {
		OrderCustomersInfoEntity orderCustomersInfoEntity = new OrderCustomersInfoEntity();
		
		orderCustomersInfoEntity.setMemberMobile(reserveOrderDetailsForm.getMemberMobile());
		orderCustomersInfoEntity.setMemberSex(reserveOrderDetailsForm.getMemberSex());
		orderCustomersInfoEntity.setMemberName(reserveOrderDetailsForm.getMemberName());
		
		orderCustomersInfoEntity.setOrderNo("123");

		orderCustomersInfoEntity.setChildrenCount(reserveOrderDetailsForm.getChildrenCount());
		orderCustomersInfoEntity.setChildrenAgeRange(reserveOrderDetailsForm.getChildrenAgeRange());

		orderCustomersInfoEntity.setFamilyCount(reserveOrderDetailsForm.getFamilyCount());
		
		orderCustomersInfoEntity.setServiceStart(reserveOrderDetailsForm.getServiceStart());
		orderCustomersInfoEntity.setServiceEnd(reserveOrderDetailsForm.getServiceEnd());
		
		orderCustomersInfoEntity.setHouseType(reserveOrderDetailsForm.getHouseType());
		orderCustomersInfoEntity.setHouseArea(reserveOrderDetailsForm.getHouseArea());
		
		orderCustomersInfoEntity.setExpectedBirth(reserveOrderDetailsForm.getExpectedBirth());
		orderCustomersInfoEntity.setIsBabyBorn(reserveOrderDetailsForm.getIsBabyBorn());
		
		orderCustomersInfoEntity.setOlderCount(reserveOrderDetailsForm.getOlderCount());
		orderCustomersInfoEntity.setOlderAgeRange(reserveOrderDetailsForm.getOlderAgeRange());
		
		orderCustomersInfoEntity.setSalaryMax(reserveOrderDetailsForm.getSalaryMax());
		orderCustomersInfoEntity.setSalaryMin(reserveOrderDetailsForm.getSalaryMin());
		
		orderCustomersInfoEntity.setServiceProvice(reserveOrderDetailsForm.getServiceProvice());
		orderCustomersInfoEntity.setServiceCity(reserveOrderDetailsForm.getServiceCity());
		orderCustomersInfoEntity.setServiceCounty(reserveOrderDetailsForm.getServiceCounty());
		orderCustomersInfoEntity.setServiceAddress(reserveOrderDetailsForm.getServiceAddress());
		
		
		String[] cookingReqirements=reserveOrderDetailsForm.getCookingReqirements();
		String[] languageRequirements=reserveOrderDetailsForm.getLanguageRequirements();
		String[] personalityRequirements=reserveOrderDetailsForm.getPersonalityRequirements();
		
		orderCustomersInfoEntity.setCookingReqirements(StringUtils.strAppend(cookingReqirements));
		orderCustomersInfoEntity.setLanguageRequirements(StringUtils.strAppend(languageRequirements));
		orderCustomersInfoEntity.setPersonalityRequirements(StringUtils.strAppend(personalityRequirements));

		orderCustomersInfoEntity.setSpecialNeeds(reserveOrderDetailsForm.getSpecialNeeds());
		
		orderCustomersInfoEntity.setServiceType(reserveOrderDetailsForm.getServiceType());
		
		orderCustomersInfoEntity.setPetRaising(reserveOrderDetailsForm.getPetRaising());
		
		return orderCustomersInfoEntity;
	}
}
