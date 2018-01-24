package com.fbee.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.ReserveOrdersEntity;
import com.fbee.modules.mybatis.model.ReserveOrders;

@MyBatisDao
public interface ReserveOrdersMapper extends CrudDao<ReserveOrdersEntity> {

	
	/**
	 * 总控平台-预约订单列表总数查询
	 * @param map
	 * @return
	 */
	Integer getResOrdersCount(Map<Object, Object> map);
	/**
	 * 总控平台-预约订单列表查询
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getResOrdersList(Map<Object, Object> map);
	
	
	
	
	int deleteByPrimaryKey(String orderNo);

	int insert(ReserveOrders record);

	int insertSelective(ReserveOrders record);
	
	
	
	/**
	 * 预约查询-预约信息
	 * @param orderNo
	 * @return
	 */
	Map<String,String> selectReserveByOrderNo(String orderNo);
	

	
	/**
	 * 预约查询-阿姨信息
	 * @param orderNo
	 * @return
	 */
	Map<String,String> selectStaffByOrderNo(String orderNo);

	/**
	 * 预约订单列表总数查询
	 * @param map
	 * @return
	 */
	Integer getReserveOrdersCount(Map<Object, Object> map);

	/**
	 * 预约订单列表查询
	 * @param map
	 * @return
	 */
	List<ReserveOrdersEntity> getReserveOrdersList(Map<Object, Object> map);
	
	
	
	/**
	 * 预约订单-完成处理更新
	 * @param reserveOrderDetailsForm
	 * @return
	 */
	int updateReserveByOrderNo(ReserveOrdersEntity reserveOrdersEntity);
	/**
	 * 预约订单-更换阿姨
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(ReserveOrders record);

}