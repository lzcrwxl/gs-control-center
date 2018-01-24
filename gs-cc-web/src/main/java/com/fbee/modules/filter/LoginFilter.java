package com.fbee.modules.filter;

import com.fbee.modules.bean.Constants;
import com.fbee.modules.core.utils.SessionUtils;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.google.common.collect.Maps;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class LoginFilter implements Filter {
	private static Map<String, Object> exclude;
	
	static {
		exclude = Maps.newHashMap();
		Object obj = new Object();
		exclude.put("/api/common/getCaptcha", obj);
		exclude.put("/api/user/login", obj);
		exclude.put("/api/user/register/confirm", obj);
		exclude.put("/api/user/register/smsCode", obj);
		exclude.put("/api/user/register/complete", obj);
		exclude.put("/api/user/resetPwd/confirm", obj);
		exclude.put("/api/user/resetPwd/smsCode", obj);
		exclude.put("/api/user/resetPwd/complete", obj);
		exclude.put("/api/service/smsReceiptPush", obj);
		exclude.put("/api/service/orderRemindSms", obj);
		exclude.put("/api/service/refundSuccessSms", obj);
		exclude.put("/api/test/views", obj);
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		
		String uri = request.getRequestURI();
		uri = uri.replaceFirst(request.getContextPath(), "");
		/*if (!uri.startsWith("/api") || exclude.get(uri) != null) {
			chain.doFilter(request, response);
			return ;
		}*/
		if(true){
			chain.doFilter(request, response);
			return ;
		}

		HttpSession session = SessionUtils.getSession(request);
		
		if (session.getAttribute(Constants.USER_SESSION) == null) {
			JsonResult jsonResult=new JsonResult(false,ResultCode.SESSION_TIMEOUT,ResultCode.getMsg(ResultCode.SESSION_TIMEOUT),null);
			JSONObject object = JSONObject.fromObject(jsonResult);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(object.toString());
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
