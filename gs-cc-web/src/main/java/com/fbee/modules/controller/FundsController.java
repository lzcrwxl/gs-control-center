package com.fbee.modules.controller;

import com.fbee.modules.basic.RequestMappingURL;
import com.fbee.modules.bean.Constants;
import com.fbee.modules.bean.UserBean;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.utils.SessionUtils;
import com.fbee.modules.form.FundsForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.service.FundService;
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
 *@Description: 总控平台-财务管理控制器
 *
 *@author: zhangsq
 *@date:   2017年3月1日 上午9:13:09
 * 
 */
@Controller
@RequestMapping(RequestMappingURL.FUNDS_BASE_URL)
public class FundsController {

	@Autowired
	FundService fundService;
	
	
	/**
	 * 财务管理-账户总览
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.QUERY_FUNDS_URL, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonResult queryFunds(HttpServletRequest request, HttpServletResponse response){
		try{
			HttpSession session = SessionUtils.getSession(request);
			UserBean userBean=(UserBean) session.getAttribute(Constants.USER_SESSION);
			if(userBean==null){
				return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}
			return fundService.queryFunds();
		}catch (Exception e){
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);  
			return JsonResult.failure(ResultCode.ERROR);
		}
	}
	
	/**
	 * 财务管理-财务流水
	 * @param request
	 * @param response
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.SELECT_FUNDS_LIST_URL, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonResult selectFundsList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageNumber", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize,
			FundsForm fundsForm){
		try{
			HttpSession session = SessionUtils.getSession(request);
			UserBean userBean=(UserBean) session.getAttribute(Constants.USER_SESSION);
			if(userBean==null){
				return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}
			Log.info(fundsForm.toString());
			return fundService.SelectFundsList(fundsForm, pageNumber, pageSize);
		}catch (Exception e){
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);  
			return JsonResult.failure(ResultCode.ERROR);
		}

	}
}
