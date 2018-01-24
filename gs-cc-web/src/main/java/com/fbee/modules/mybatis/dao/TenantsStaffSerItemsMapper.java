package com.fbee.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsStaffSerItemsEntity;
import com.fbee.modules.mybatis.model.TenantsStaffSerItemsKey;

@MyBatisDao
public interface TenantsStaffSerItemsMapper extends CrudDao<TenantsStaffSerItemsEntity>{

	/**
	 * 根据唯一键值获取员工（阿姨）服务工种对象
	 * @param staffSerItemsKey
	 * @return
	 */
	TenantsStaffSerItemsEntity getStaffServiceItemsByKey(TenantsStaffSerItemsKey staffSerItemsKey);

	/**
	 * 获取阿姨服务工种
	 * @param staffSerItems
	 * @return
	 */
	List<TenantsStaffSerItemsEntity> getStaffServiceItems(TenantsStaffSerItemsEntity staffSerItems);

	/**
	 * @param staffId
	 */
	List<TenantsStaffSerItemsEntity> getServiceItemsByStaffId(Integer staffId);

}