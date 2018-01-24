package com.fbee.modules.service;

import org.springframework.web.multipart.MultipartFile;

import com.fbee.modules.form.CreateOrderForm;
import com.fbee.modules.form.OrderShareInfoForm;
import com.fbee.modules.form.OrdersForm;
import com.fbee.modules.jsonData.basic.JsonResult;

public interface OrderService {
	
	/**
	 * 总控平台
	 * 订单列表查询
	 */
	JsonResult queryOrdersList(OrdersForm form);

	
	
	
	/**
	 * 订单列表查询
	 */
	//JsonResult selectOrdersList(OrdersForm form);
	
	/**
	 * 通过订单流水号查询订单信息
	 */
	//JsonResult selectByOrderNo(String orderNo);
	
	/**
	 * 通过订单流水号查询客户信息
	 */
	//JsonResult selectMemberInfoByOrderNo(String orderNo);
	
    /**
     * 通过订单流水号查询服务信息
     */
	//JsonResult selectServiceInfoByOrderNo(String orderNo);
	
	/**
     * 通过订单流水号查询支付信息
     */
	//JsonResult selectPayInfoByOrderNo(String orderNo);
	
	/**
	 * 根据订单流水号获取合同详情
	 */
	//JsonResult selectContractInfoByOrderNo(String orderNo);
	
	/**
     * 根据订单流水号获取分享信息
     */
	//JsonResult selectShareInfoByOrderNo(String orderNo);
	
	/**
     * 根据订单流水号获取淘蜂享信息
     */
	//JsonResult selectTaoShareInfoByOrderNo(String orderNo);
	
	/**
	 * 面试通过更新订单信息
	 */
	//JsonResult passInterviewInfo(String orderNo);
	
	/**
	 * 保存订单详情
	 */
	//JsonResult saveOrder(String orderNo, String amount, String remark);
	
	/**
	 * 更换订单阿姨
	 */
	//JsonResult changeStaff(String orderNo, Integer staffId);
	
	/**
	 * 分享订单
	 */
    //JsonResult orderShare(OrderShareInfoForm form);	
    
    /**
     * 客户合同图片上传
     */
    //JsonResult saveContractInfo(String orderNo, MultipartFile file);
    
    /**
     * 创建订单
     */
    //JsonResult createOrder(CreateOrderForm form);
    
}
