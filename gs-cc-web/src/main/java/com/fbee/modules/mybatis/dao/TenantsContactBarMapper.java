package com.fbee.modules.mybatis.dao;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsContactBarEntity;

@MyBatisDao
public interface TenantsContactBarMapper extends CrudDao<TenantsContactBarEntity>{
	/**
	 * @param tenantId
	 * @return
	 
	TenantsContactBarEntity getContactBarByTenantId(Integer tenantId);
	*/
	/**
	 * 
	 * @param tenantId
	 * @param tenantsContactBarEntity
	 * @return
	 
	int updateTenantsContactBar(TenantsContactBarEntity tenantsContactBarEntity);
	*/
}