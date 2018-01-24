package com.fbee.modules.mybatis.dao;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsAboutUsEntity;
import com.fbee.modules.mybatis.model.TenantsAboutUs;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface TenantsAboutUsMapper extends CrudDao<TenantsAboutUsEntity>{

	/**
	 * 保存关于我们信息
	 * @param tenantsAboutUs
	 */
	void insert(TenantsAboutUs tenantsAboutUs);
	/**
	 * 获取关于我们图文信息
	 * @param tenantId
	 * @return
	 */
	List<Map> getAboutUsInfo(Integer tenantId);
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	TenantsAboutUs selectByPrimaryKey(Integer id);
	/**
	 * 主键删除
	 * @param id
	 */
	void deleteByPrimaryKey(Integer id);
	/**
	 * 主键更新
	 * @param tenantsAboutUs
	 */
	void updateByPrimaryKey(TenantsAboutUs tenantsAboutUs);

	TenantsAboutUs getByTenantId(Integer tenantId);
}