package com.fbee.modules.controller;


import com.fbee.modules.basic.RequestMappingURL;
import com.fbee.modules.core.Log;
import com.fbee.modules.form.CertCheckForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.service.InCertCkeckService;
import com.fbee.modules.utils.ZSCXQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;

@Controller
@RequestMapping(RequestMappingURL.ZSCX_BASE_URL)
public class InCertCheckController {

	@Autowired
	InCertCkeckService inCertCkeckService;

	//图形码验证
	@RequestMapping(value = RequestMappingURL.ZSCX_CAPTCHA_URL, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public  JsonResult getCaptcha(HttpServletRequest request, HttpServletResponse response) {
		try {
			
		    String mapkey=ZSCXQuery.getImageRequest(); 
		     //clientkey存入session中
			HttpSession session = request.getSession();
			 session.setAttribute("clientkey",mapkey);
			 System.out.println(mapkey);
			return JsonResult.success(ResultCode.SUCCESS);
		} catch (Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);  
			return JsonResult.failure(ResultCode.ERROR);
		}
	}
	
	/**
	 *  证书数据爬取
	 * @param request
	 * @param response
	 * @param certCheckForm
	 * @return
	 */
	
		@RequestMapping(value = RequestMappingURL.ZSCX_CKECK_URL, method = {RequestMethod.GET, RequestMethod.POST})
		@ResponseBody
	public JsonResult selectCertInfoById(HttpServletRequest request, HttpServletResponse response,
			CertCheckForm certCheckForm  ){
		try{	
			//中文解码
			String nameurl= URLDecoder.decode(certCheckForm.getName(),"UTF-8");
			certCheckForm.setName(nameurl);
			Log.info("+++++++++++++++++++"+certCheckForm.getName());
			//取出session clientKey
			 HttpSession session = request.getSession();
			 Object   clientkey = session.getAttribute("clientkey");
			 Log.info("++++++++++++++++++"+clientkey);
			 if (clientkey==null) {
				 JsonResult.failure(ResultCode.ERROR);
			}
			 JsonResult success = inCertCkeckService.checkCertInfo(certCheckForm,clientkey);
			 //查询有数据后放进缓存中
			 if (success.getCode().equals(0)||success.getCode().equals(999015)) {
				
				
				 session.setAttribute("capCode",certCheckForm);
			}
			 	
				return success;
				
		}catch(Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
			return JsonResult.failure(ResultCode.ERROR);
		}		
	}
		/**
		 *  证书列表查询
		 * @param request
		 * @param response
		 * @param certCheckForm
		 * @return
		 */	
		@RequestMapping(value = RequestMappingURL.QUERY_CERT_INFO, method = {RequestMethod.GET, RequestMethod.POST})
		@ResponseBody
	public JsonResult queryCertInfoById(HttpServletRequest request, HttpServletResponse response,
			String idcard, @RequestParam(value = "name")String name  ){
		try{	
			
			HttpSession session = request.getSession();
			 Object capCode = session.getAttribute("capCode");
			 
			 Log.info("++++++++++++++++++++"+capCode);
			 
				if(capCode==null){
					return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
				}else if(idcard==null){
					return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
				}else if (name == null) {
					return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
				}else if (capCode!=null) {
					CertCheckForm  certCheckForm = (CertCheckForm) capCode;
					if (!certCheckForm.getIdcard().equals(idcard)) {
						Log.info("-----------------------------"+certCheckForm.getIdcard());
						Log.info("+++++++++++++++++++++++++++++"+idcard);
						return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
					}else if (!certCheckForm.getName().equals(name)) {
						Log.info("-----------------------------"+certCheckForm.getName());
						Log.info("+++++++++++++++++++++++++++++"+name);
						
						return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
					}
				}
				
				return inCertCkeckService.queryCertInfo( idcard,name);
				
		}catch(Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
			return JsonResult.failure(ResultCode.ERROR);
		}		
	}
		
		
	
}
