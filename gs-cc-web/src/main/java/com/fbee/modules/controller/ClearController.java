package com.fbee.modules.controller;

import com.fbee.modules.basic.RequestMappingURL;
import com.fbee.modules.bean.Constants;
import com.fbee.modules.bean.UserBean;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.utils.SessionUtils;
import com.fbee.modules.form.ClearForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.service.ClearService;
import com.fbee.modules.utils.PoiExportExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *@Description: 清算管理控制器
 *
 *@author: zhangsq
 *@date:   2017年3月2日 上午10:16:11
 * 
 */
@Controller
@RequestMapping(RequestMappingURL.CLEAR_BASE_URL)
public class ClearController {

	@Autowired
	ClearService clearService;
	
	/**
	 * 清算列表查询
	 * @param request
	 * @param response
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = RequestMappingURL.QUERY_CLEAR_LIST_URL, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonResult selectClearList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageNumber", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize,
			ClearForm clearForm){
		try{
			HttpSession session = SessionUtils.getSession(request);
			UserBean userBean=(UserBean) session.getAttribute(Constants.USER_SESSION);
			if(userBean==null){
				return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}
			Log.info(clearForm.toString());
			return clearService.SelectClearList(clearForm, pageNumber, pageSize);
		}catch (Exception e){
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);  
			return JsonResult.failure(ResultCode.ERROR);
		}
}
	/**
	 * 提现清算
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value=RequestMappingURL.TIXIAN_CLEAR,method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonResult tiXianClearById(HttpServletRequest request,HttpServletResponse response,
		 String inOutNo,String tradeNo ,Integer tenantId,String tradeFlowNo){
		try {
			HttpSession session = SessionUtils.getSession(request);
			UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
			if(userBean == null){
				return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}
			if(inOutNo==null){
				return JsonResult.failure(ResultCode.ERROR);
			}
			if(tradeNo==null){
				return JsonResult.failure(ResultCode.ERROR);
			}
			if(tenantId==null){
				return JsonResult.failure(ResultCode.ERROR);
			}
			return clearService.tiXianClearById(inOutNo,tradeNo,tenantId,tradeFlowNo);
		} catch (Exception e){
			e.printStackTrace();
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
			return JsonResult.failure(ResultCode.ERROR);
		}
	}
	
	/**
	 * 退款清算
	 * @param request
	 * @param response
	 * @param orderNo,traedNo
	 * @return
	 */
	@RequestMapping(value=RequestMappingURL.TUIKUAN_CLEAR,method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonResult tuiKuanClearById(HttpServletRequest request,HttpServletResponse response,
		 String orderNo,String inOutNo,	String tradeNo ,Integer tenantId,String tradeFlowNo){
		try {
			HttpSession session = SessionUtils.getSession(request);
			UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
			if(userBean == null){
				return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
			}
			if(orderNo ==null){
				return JsonResult.failure(ResultCode.ERROR);
			}
			if(inOutNo==null){
				return JsonResult.failure(ResultCode.ERROR);
			}
			if(tradeNo==null){
				return JsonResult.failure(ResultCode.ERROR);
			}
			if(tenantId==null){
				return JsonResult.failure(ResultCode.ERROR);
			}
			return clearService.tuiKuanClearById(orderNo,inOutNo,tradeNo,tenantId,tradeFlowNo);
		} catch (Exception e){
			e.printStackTrace();
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
			return JsonResult.failure(ResultCode.ERROR);
		}
	}
	
	/**
	 * 导出excel
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value=RequestMappingURL.EXPORT_EXCEL,method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ModelAndView   exportExcel(HttpServletRequest request,HttpServletResponse response,
			ClearForm clearForm)
			throws Exception{
		 	try{
		 		  	//UserInfoPO user =new UserInfoPO();  
		            // 查出用户数据  
		            //List<UserInfoPO> userlist = userInfoService.queryList(user);  
		            //String title ="用户信息表";  
		            //String[] rowsName=new String[]{"序号","ID","用户名","性别","登录id","登录密码"};  
		           // List<Object[]>  dataList = new ArrayList<Object[]>();  
		           /* Object[] objs = null;  
		            for (int i = 0; i < userlist.size(); i++) {  
		                UserInfoPO po =userlist.get(i);  
		                objs = new Object[rowsName.length];  
		                objs[0] = i;  
		                objs[1] = po.getId();  
		                objs[2] = po.getUserName();  
		                objs[3] = po.getSex();  
		                objs[4] = po.getLoginId();  
		                objs[5] = po.getLoginPassword();  
		                dataList.add(objs);  
		            }  */
		            //   
		            //PoiExportExcelUtils ex =new PoiExportExcelUtils(title, rowsName, dataList,response);  
		            //ex.exportData();  
		 		  
		 		List<Map> list = clearService.SelectClearList(clearForm);
		 		String title = "清算明细表";
		 		String[] rowsName=new String[]{"序号","发起时间","支付流水号","订单编号","门店/用户名称","金额","支付类型","清算渠道","处理时间","状态","原支付流水号"}; 
		 		List<Object[]>  dataList = new ArrayList<Object[]>();
		 		Object[] objs = null;
		 		for (int i = 0; i < list.size(); i++) {  
		 			Map map =list.get(i);  
	                objs = new Object[rowsName.length];  
	                objs[0] = i;  
	                objs[1] = map.get("tradeTime"); 
	                objs[2] = map.get("tradeFlowNo");
	                objs[3] = map.get("orderNo");
	                objs[4] = map.get("tenantName");
	                objs[5] = map.get("tradeAmount");
	                objs[6] = map.get("tradeType");
	                objs[7] = map.get("tradeChannel");
	                objs[8] = map.get("modifyTime");
	                objs[9] = map.get("status");
	                objs[10] = map.get("tradeFlowNoBt");
	                
	                dataList.add(objs);  
	            }
		 		PoiExportExcelUtils ex =new PoiExportExcelUtils(title, rowsName, dataList,response);  
	            ex.exportData();
		 	}catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	          
	        return null;  
	}
}