package com.fbee.modules.mybatis.dao;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsBannersEntity;

@MyBatisDao
public interface TenantsBannersMapper extends CrudDao<TenantsBannersEntity>{

	/**
	 * @param tenantId
	 * @return
	 */
	TenantsBannersEntity getBannerByTenantId(Integer tenantId);
	
	int update(TenantsBannersEntity entity);
	
}