package com.fbee.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsServiceItemsEntity;
import com.fbee.modules.mybatis.model.TenantsServiceItems;

@MyBatisDao
public interface TenantsServiceItemsMapper extends CrudDao<TenantsServiceItemsEntity>{
	
	/**
	 * 获取服务工种列表
	 * @param staffSerItems
	 * @return
	 */
	List<Map<String, String>> getStaffServiceItemList(Integer tenantId);
	
	/**
	 * 获取服务工种
	 * @param serviceItemCode
	 * @param tenantId
	 * @return
	 */
	Map<String, String> selectByPrimaryKey(Integer tenantId, String serviceItemCode);
	
	/**
	 * 更新服务工种
	 * @param tenantsServiceItems
	 * @return
	 */
	int updateByPrimaryKeySelective(TenantsServiceItems tenantsServiceItems);
	
	/**
	 * 获取租户服务工种serviceItemCode
	 * @param tenantId
	 */
	List<Map<String,String>> getStaffServiceItemCodes(Integer tenantId);
	
	
	
	/**
	 * 门店入驻插入服务工种
	 * @param tenantsServiceItems
	 * @return
	 */
	int insertByTenantId(Integer tenantId);
	
	
}