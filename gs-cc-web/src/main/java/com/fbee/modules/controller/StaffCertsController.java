package com.fbee.modules.controller;

import com.fbee.modules.basic.RequestMappingURL;
import com.fbee.modules.bean.Constants;
import com.fbee.modules.bean.UserBean;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.utils.SessionUtils;
import com.fbee.modules.form.StaffCertForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.service.StaffCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(RequestMappingURL.CERTS_BASE_URL)
public class StaffCertsController {

	
	@Autowired
	StaffCertService staffCertService;
	
	/**
	 * 总控平台-证书管理-证书列表查询
	 * @param request
	 * @param response
	 * @param pageNumber
	 * @param pageSize
	 * @param staffCertFrom
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.QUERY_CERTS_LIST_URL, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonResult queryCertsList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageNumber", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize,
			StaffCertForm staffCertFrom){
		try{
			HttpSession session = SessionUtils.getSession(request);
			UserBean userBean=(UserBean) session.getAttribute(Constants.USER_SESSION);
			if(userBean==null){
				return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}
			Log.info(staffCertFrom.toString());
			
			return staffCertService.getStaffCertsList(staffCertFrom, pageNumber, pageSize);
		}catch (Exception e){
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);  
			return JsonResult.failure(ResultCode.ERROR);
		}

	}
	
	/**
	 * 总控平台-证书管理-详情
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.CERTS_INFO_URL, method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public JsonResult selectCertInfoById(HttpServletRequest request, HttpServletResponse response,
			 Integer id){
		
		try{	
			HttpSession session = SessionUtils.getSession(request);
			UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
			if (userBean == null) {
				return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}
			
			return staffCertService.selectCertInfoById(id);
		}catch(Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
			return JsonResult.failure(ResultCode.ERROR);
		}		
	}
	/**
	 * 总控平台-证书审核(通过)
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.PASS_CERT_URL, method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public JsonResult passStaffCertById(HttpServletRequest request, HttpServletResponse response,
			 Integer id){
		
		try{	
			HttpSession session = SessionUtils.getSession(request);
			UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
			if (userBean == null) {
				return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}
		
			return staffCertService.passStaffCertById(id);
		}catch(Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
			return JsonResult.failure(ResultCode.ERROR);
		}		
	}
	/**
	 * 总控平台-证书审核(驳回)
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.Nopass_CERT_URL, method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public JsonResult NgStaffCertById(HttpServletRequest request, HttpServletResponse response,
			Integer id){
		try{	
			HttpSession session = SessionUtils.getSession(request);
			UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
			if (userBean == null) {
				return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}
			return staffCertService.nopassStaffCertById(id);
		}catch(Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
			return JsonResult.failure(ResultCode.ERROR);
		}		
	}
	
	/**
	 * 总控平台-真伪查询
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.ZSCX_CKECK_URL, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
public JsonResult checkCertInfoById(HttpServletRequest request, HttpServletResponse response,
		Integer id ){
	try{	
//		HttpSession session = SessionUtils.getSession(request);
//		UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
		
		//return staffCertService.checkCertInfoById(id);
		return null;	
	}catch(Exception e) {
		Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
		return JsonResult.failure(ResultCode.ERROR);
	}		
}
	
}
