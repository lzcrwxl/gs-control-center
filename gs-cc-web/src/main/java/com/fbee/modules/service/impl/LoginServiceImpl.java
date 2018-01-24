package com.fbee.modules.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbee.modules.bean.Constants;
import com.fbee.modules.bean.ErrorMsg;
import com.fbee.modules.bean.Status;
import com.fbee.modules.bean.UserBean;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.utils.SessionUtils;
import com.fbee.modules.form.LoginForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.mybatis.dao.TenantsOperateRecordsMapper;
import com.fbee.modules.mybatis.dao.TenantsUsersMapper;
import com.fbee.modules.mybatis.entity.TenantsOperateRecordsEntity;
import com.fbee.modules.mybatis.entity.TenantsUsersEntity;
import com.fbee.modules.service.LoginService;
import com.fbee.modules.service.basic.BaseService;
import com.fbee.modules.utils.EntryptUtils;

@Service
public  class LoginServiceImpl extends BaseService implements LoginService{

	@Autowired
	TenantsUsersMapper tenantsUsersDao;

	@Autowired
	TenantsOperateRecordsMapper operateRecorDao;
	
	//@Autowired
	//TenantsMenusMapper tenantsMenusDao;
	
	//@Override
	//用户菜单
	/*public UserMenusJsonData getUserMenus(UserBean userBean) {
		Map<String, Object> params= Maps.newHashMap();
		params.put("tenantId", userBean.getTenantId());
		List<TenantsMenusEntity> userMenuList=tenantsMenusDao.getUserMenus(params);
		
		if(userMenuList==null || userMenuList.size()<0){
			return null;
		}
		
		List<UserMenusJson> reslutList=Lists.newArrayList();
		sortList(reslutList,userMenuList,1,true);
		
		UserMenusJsonData userMenusJsonData=new UserMenusJsonData();
		userMenusJsonData.setUserMenus(reslutList);
		return userMenusJsonData;
	}
	
	public static void sortList(List<UserMenusJson> list, List<TenantsMenusEntity> sourcelist, Integer parentId, boolean cascade){
		UserMenusJson userMenusBean=null;
		for (int i=0; i<sourcelist.size(); i++){
			TenantsMenusEntity e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().getId()!=null
					&& e.getParent().getId().equals(parentId)){
				userMenusBean=new UserMenusJson();
				userMenusBean.setMenuId(e.getId());
				userMenusBean.setMenuName(e.getName());
				userMenusBean.setIcon(e.getIcon());
				userMenusBean.setHref(e.getHref());
				userMenusBean.setChildrenMenus(new ArrayList<UserMenusJson>());
				list.add(userMenusBean);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						TenantsMenusEntity child = sourcelist.get(j);
						if (child.getParent()!=null && child.getParent().getId()!=null
								&& child.getParent().getId().equals(e.getId())){
							sortList(userMenusBean.getChildrenMenus(), sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}*/
	
	@Override
	//登陆
	public JsonResult login(LoginForm loginForm, HttpServletRequest request) {
		JsonResult jsonResult=JsonResult.success(null);
		//根据用户账号获取用户的信息
		TenantsUsersEntity user=tenantsUsersDao.getByLoginAccount(loginForm.getLoginAccount());
		
		if(user==null){
			return JsonResult.failure(ResultCode.User.ACCOUNT_NOT_EXIST);
		}
		if (!user.getPassword().equals(EntryptUtils.entryptUserPassword(loginForm.getPassword().toUpperCase(), user.getSalt()))) {
			return JsonResult.failure(ResultCode.User.ACCOUNT_PASSWORD_ERROR);
		}
		
		HttpSession session = SessionUtils.getSession(request);
		session.invalidate();
		session = SessionUtils.getSession(request);
		UserBean userBean=new UserBean();
		userBean.setLoginAccount(user.getLoginAccount());
		userBean.setTenantId(user.getTenantId());
		userBean.setUserId(user.getId());
		userBean.setUserName(user.getLoginName());
		session.setAttribute(Constants.USER_SESSION, userBean);
		jsonResult = JsonResult.success(null);
		try {
			TenantsOperateRecordsEntity record = new TenantsOperateRecordsEntity();
			record.setOperateAccount(loginForm.getLoginAccount());
			record.setAction(Status.Actions.LOGIN);
			record.setOperateTime(new Date());
			record.setTenantId(user.getTenantId());
			operateRecorDao.insert(record);
		} catch (Exception e) {
			Log.error(String.format(ErrorMsg.SAVE_LOGIN_ERR, e));
		}
		return jsonResult;
	}

	@Override
	//退出
	public JsonResult logout(HttpServletRequest request) {
		HttpSession session = SessionUtils.getSession(request);
		try {
			UserBean userBean=(UserBean) session.getAttribute(Constants.USER_SESSION);
			TenantsOperateRecordsEntity record = new TenantsOperateRecordsEntity();
			record.setOperateAccount(userBean.getLoginAccount());
			record.setOperateTime(new Date());
			record.setAction(Status.Actions.LOGOUT);
			record.setTenantId(userBean.getTenantId());
			operateRecorDao.insert(record);
		} catch (Exception e) {
			Log.error(String.format(ErrorMsg.SAVE_LOGOUT_ERR, e));
		}
		session.invalidate();
		return JsonResult.success(null);
	}

}
