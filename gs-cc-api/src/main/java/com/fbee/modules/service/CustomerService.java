package com.fbee.modules.service;

import com.fbee.modules.form.CustomerQueryForm;
import com.fbee.modules.jsonData.basic.JsonResult;

public interface CustomerService {

	
	/**
	 * 客户管理-客户查询
	 * @param customerqueryform
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	JsonResult queryCustomerList(CustomerQueryForm customerqueryform, int pageNumber, int pageSize);
	
}
