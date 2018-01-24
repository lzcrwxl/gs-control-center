package com.fbee.modules.mybatis.dao;

import java.util.List;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsUsersEntity;
import com.fbee.modules.mybatis.model.TenantsUsers;

@MyBatisDao
public interface TenantsUsersMapper extends CrudDao<TenantsUsersEntity>{

	/**
	 * 根据账号获取登陆用户
	 * @param loginAccount
	 * @return
	 */
	TenantsUsersEntity getByLoginAccount(String loginAccount);
	/**
	 * 根据用户id重置密码
	 * @param tenantsUsersEntity
	 * @return
	 */
	//int update(TenantsUsersEntity tenantsUsersEntity);
	/**
	 * 根据用户id获取绑定手机号
	 * @param id
	 * @return
	 */
	//String getTelephoneByUserId(int id);
	/**
	 * 账号查询是否重复
	 * @param loginAccount
	 * @return
	 */
	List<TenantsUsers> selectByLoginAccount(String loginAccount);
	
}