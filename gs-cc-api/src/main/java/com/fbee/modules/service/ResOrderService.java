package com.fbee.modules.service;

import com.fbee.modules.form.ResOrdersForm;
import com.fbee.modules.jsonData.basic.JsonResult;

/**
 *@Description: 总控平台订单预约管理服务层接口
 *
 *@author: zhangsq
 *@date:   2017年2月24日 下午1:40:11
 * 
 */
public interface ResOrderService {
	
	
	/**
	 * 获取预约订单列表
	 * @param tenantId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	JsonResult getResOrdersList(ResOrdersForm resOrdersForm ,Integer pageNumber, Integer pageSize); 

}
