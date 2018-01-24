package com.fbee.modules.controller;

import com.fbee.modules.basic.RequestMappingURL;
import com.fbee.modules.bean.Constants;
import com.fbee.modules.bean.UserBean;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.bean.CaptchaCode;
import com.fbee.modules.core.config.Global;
import com.fbee.modules.core.utils.GenerateCaptcha;
import com.fbee.modules.core.utils.SessionUtils;
import com.fbee.modules.core.utils.StringUtils;
import com.fbee.modules.form.LoginForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.jsonData.json.CaptchaJsonData;
import com.fbee.modules.jsonData.json.UserMenusJsonData;
import com.fbee.modules.service.UserService;
import com.fbee.modules.validate.LoginFormValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
* @ClassName: UserController 
* @Description: 用户登陆、登出、图形验证码
* @author 贺章鹏
* @date 2016年12月27日 下午4:19:25 
*  
*/
@Controller
@RequestMapping(RequestMappingURL.USERS_BASE_URL)
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = RequestMappingURL.CAPTCHA_URL, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonResult getCaptcha(HttpServletRequest request, HttpServletResponse response) {
		CaptchaJsonData jsonData = new CaptchaJsonData();
		try {
			GenerateCaptcha randomCode = new GenerateCaptcha();
			String captcha = randomCode.getRandcodeBase64(request, response,null,null);
			
			if (StringUtils.isNotBlank(captcha)) {
				jsonData.setCaptcha(captcha);
				return JsonResult.success(jsonData);
			} else {
				return JsonResult.failure(ResultCode.User.CAPTCHA_FAILURE);
			}
		} catch (Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);  
			return JsonResult.failure(ResultCode.ERROR);
		}
	}
	
	@RequestMapping(value = RequestMappingURL.LOGIN_URL, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonResult userLogin(HttpServletRequest request, HttpServletResponse response,LoginForm loginForm) {
		try {

			if(!"prod".equalsIgnoreCase(Global.getConfig("env"))){
				return userService.login(loginForm,request);
			}

			JsonResult jsonResult=LoginFormValidate.validLogin(loginForm);
			if (!jsonResult.isSuccess()) {
				return jsonResult;
			}

			//验证图形验证码
			HttpSession session = SessionUtils.getSession(request);
			CaptchaCode captchaObj = (CaptchaCode) session.getAttribute(GenerateCaptcha.RANDOMCODEKEY);
			session.removeAttribute(GenerateCaptcha.RANDOMCODEKEY);
			if (captchaObj == null || captchaObj.isExpired()) { // 验证码过期
				return JsonResult.failure(ResultCode.User.CAPTCHA_TIMEOUT);
			} else if (!captchaObj.getCode().toUpperCase().equals(loginForm.getCaptcha().toUpperCase())) {// 验证码不正确
				return JsonResult.failure(ResultCode.User.CAPTCHA_ERROR);
			}
			jsonResult=userService.login(loginForm,request);
			
			return jsonResult;
		} catch (Exception e) {
			System.out.println(e.toString());
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);  
			return JsonResult.failure(ResultCode.ERROR);
		}
	}
	
	
	
	@RequestMapping(value = RequestMappingURL.LOGOUT_URL, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonResult userLogout(HttpServletRequest request, HttpServletResponse response) {
		try {
			JsonResult jsonResult = userService.logout(request);
			return jsonResult;
		} catch (Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);  
			return JsonResult.failure(ResultCode.ERROR);
		}
	}
	//获取用户名
	@RequestMapping(value={"getSession.do"}, method={RequestMethod.POST, RequestMethod.GET})
	  @ResponseBody
	  public JsonResult getSession(HttpServletRequest request, HttpServletResponse response) {
	    HttpSession session = SessionUtils.getSession(request);
	    UserBean userBean = (UserBean)session.getAttribute("useSession");
	    return JsonResult.success(userBean);
	  }
	
	@RequestMapping(value = RequestMappingURL.USER_MENUS_URL, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonResult getUserMenus(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = SessionUtils.getSession(request);
			UserBean userBean=(UserBean) session.getAttribute(Constants.USER_SESSION);
			if(userBean==null){
				return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}
			
			UserMenusJsonData userMenusJsonData = userService.getUserMenus(userBean);
			if (userMenusJsonData!=null) {
				return JsonResult.success(userMenusJsonData);
			} else {
				return JsonResult.failure(ResultCode.DATA_IS_NULL);
			}
		} catch (Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);  
			return JsonResult.failure(ResultCode.ERROR);
		}
	}

}
