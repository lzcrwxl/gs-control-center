package com.fbee.modules.service;

import com.fbee.modules.form.FundsForm;
import com.fbee.modules.jsonData.basic.JsonResult;

public interface FundService {
	
	
	/**
	 * 财务管理-账户总览
	 * @param fundsform
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	JsonResult queryFunds();
	
	
	/**
	 * 财务管理-账户流水
	 * @param fundsform
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	JsonResult SelectFundsList(FundsForm fundsForm, int pageNumber, int pageSize);
}
