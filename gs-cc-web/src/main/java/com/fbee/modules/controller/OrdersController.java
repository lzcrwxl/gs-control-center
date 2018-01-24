package com.fbee.modules.controller;

import com.fbee.modules.basic.RequestMappingURL;
import com.fbee.modules.bean.Constants;
import com.fbee.modules.bean.UserBean;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.utils.SessionUtils;
import com.fbee.modules.form.OrdersForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.service.OrderService;
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
 *@Description: 总控平台-订单管理控制器
 *
 *@author: zhangsq
 *@date:   2017年2月27日 下午3:58:59
 * 
 */

@Controller
@RequestMapping(RequestMappingURL.ORDERS_BASE_URL)
public class OrdersController {

	@Autowired
	OrderService orderService;
	
	
	/**
	 * 获取订单列表
	 * @param request
	 * @param response
	 * @param form
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.ORDERS_LIST_URL, method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public JsonResult selectOrdersList(
			HttpServletRequest request, HttpServletResponse response,
			OrdersForm form,
			@RequestParam(value = "pageNumber", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize
			){
		try{	
			HttpSession session = SessionUtils.getSession(request);
			UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
			if (userBean == null) {
				return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}
			if(form.getPageNum() == null){
				form.setPageNum(pageNumber);
			}
			if(form.getPageSize() == null){
				form.setPageSize(pageSize);
			}
			return orderService.queryOrdersList(form);
		}catch(Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
			return JsonResult.failure(ResultCode.ERROR);
		}
		
	}

}
