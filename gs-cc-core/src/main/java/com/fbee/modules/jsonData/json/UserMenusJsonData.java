package com.fbee.modules.jsonData.json;

import java.util.List;

import com.fbee.modules.core.persistence.ModelSerializable;
import com.fbee.modules.jsonData.extend.UserMenusJson;

/** 
* @ClassName: UserMenusJsonData 
* @Description: 用户菜单jsonData
* @author zhangsq
* @date   2017/2/21 
*  
*/
public class UserMenusJsonData implements ModelSerializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<UserMenusJson> userMenus;

	public List<UserMenusJson> getUserMenus() {
		return userMenus;
	}

	public void setUserMenus(List<UserMenusJson> userMenus) {
		this.userMenus = userMenus;
	}
}
