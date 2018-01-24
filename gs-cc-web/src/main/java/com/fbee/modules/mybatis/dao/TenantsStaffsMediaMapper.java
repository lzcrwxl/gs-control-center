package com.fbee.modules.mybatis.dao;

import java.util.List;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsStaffsMediaEntity;

@MyBatisDao
public interface TenantsStaffsMediaMapper extends CrudDao<TenantsStaffsMediaEntity>{

	/**
	 * @param staffId
	 */
	List<TenantsStaffsMediaEntity> getAllMedias(Integer staffId);

	/**
	 * 清除阿姨默认图片
	 * @param staffId
	 */
	void clearStaffImageDefault(Integer staffId);
}