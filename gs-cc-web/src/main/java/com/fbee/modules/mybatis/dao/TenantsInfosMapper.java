package com.fbee.modules.mybatis.dao;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsFundsEntity;
import com.fbee.modules.mybatis.entity.TenantsInfosEntity;

@MyBatisDao
public interface TenantsInfosMapper extends CrudDao<TenantsInfosEntity>{
	

	
}