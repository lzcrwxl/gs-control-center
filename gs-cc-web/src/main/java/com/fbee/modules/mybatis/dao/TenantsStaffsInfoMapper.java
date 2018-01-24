package com.fbee.modules.mybatis.dao;

import java.util.List;
import java.util.Map;
import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsStaffsInfoEntity;

@MyBatisDao
public interface TenantsStaffsInfoMapper extends CrudDao<TenantsStaffsInfoEntity>{

	/**
	 * @param map
	 * @return
	 */
	Integer getStaffQueryCount(Map<Object, Object> map);

	/**
	 * @param map
	 * @return
	 */
	List<TenantsStaffsInfoEntity> getStaffQueryList(Map<Object, Object> map);

	/**
	 * @param param
	 * @return
	 */
	TenantsStaffsInfoEntity getStaffInfo(TenantsStaffsInfoEntity param);
	
	/**
	 * 获取推荐阿姨列表
	 * @param tenantId
	 * @return
	 */
	List<Map<String, String>> selectRecommendList(Integer tenantId);
	
	/**
	 * 获取阿姨列表
	 * @param tenantId
	 * @return
	 */
	List<Map> selectStaffInfoList(Map<String, Object> map);
	
	/**
	 * 获取阿姨个人信息
	 * @param tenantId
	 * @param staffId
	 * @return
	 */
	Map<String, String> getStaffInfoByStaffId(Integer tenantId, Integer staffId);
	
	/**
	 * 模糊查询获取阿姨列表
	 * @param tenantId
	 * @param staffName
	 * @return
	 */
	List<Map<String, String>> getStaffInfoLikeStaffName(Integer tenantId, String staffName);
	
	/**
	 * @param param
	 * @return
	 */
	Integer getOrdersQueryCount(int tenantId,String membermobile);
	
	/**
	 * 获取总条数
	 * @param tenantId
	 */
	int getCount(Integer tenantId);
	
}