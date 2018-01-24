package com.fbee.modules.mybatis.dao;

import java.util.List;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsStaffPolicyEntity;
import com.fbee.modules.mybatis.model.TenantsStaffPolicyKey;

@MyBatisDao
public interface TenantsStaffPolicyMapper extends CrudDao<TenantsStaffPolicyEntity>{

	/**
	 * 根据唯一键值获取阿姨银保单信息
	 * @param staffPolicyKey
	 * @return
	 */
	TenantsStaffPolicyEntity getPolicyInfoByKey(TenantsStaffPolicyKey staffPolicyKey);

	/**
	 * @param staffPolicyParams
	 * @return
	 */
	List<TenantsStaffPolicyEntity> getPolicyList(TenantsStaffPolicyEntity staffPolicyParams);
   
}