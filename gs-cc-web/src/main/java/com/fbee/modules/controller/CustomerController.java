package com.fbee.modules.controller;

import com.fbee.modules.basic.RequestMappingURL;
import com.fbee.modules.bean.Constants;
import com.fbee.modules.bean.UserBean;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.utils.SessionUtils;
import com.fbee.modules.form.CustomerQueryForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *@Description: 总控平台-客户管理控制器
 *
 *@author: zhangsq
 *@date:   2017年2月28日 上午11:50:35
 * 
 */
@Controller
@RequestMapping(RequestMappingURL.CUSTOMERS_BASE_URL)
public class CustomerController {

	@Autowired
	CustomerService customerService;

	/**
	 * 客户管理-客户查询
	 * @param request
	 * @param response
	 * @param pageNumber
	 * @param pageSize
	 * @param customerqueryform
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.QUERY_CUSTOMER_LIST_URL, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonResult queryCustomerList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageNumber", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize,
			CustomerQueryForm customerqueryform){
		try{
			HttpSession session = SessionUtils.getSession(request);
			UserBean userBean=(UserBean) session.getAttribute(Constants.USER_SESSION);
			if(userBean==null){
				return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}
			
			Log.info(customerqueryform.toString());
			
			return customerService.queryCustomerList(customerqueryform, pageNumber, pageSize);
		}catch (Exception e){
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);  
			return JsonResult.failure(ResultCode.ERROR);
		}

	}
	
	
}
