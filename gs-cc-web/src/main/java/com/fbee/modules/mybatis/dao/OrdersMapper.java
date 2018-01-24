package com.fbee.modules.mybatis.dao;

import java.util.List;
import java.util.Map;
import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.OrdersEntity;
import com.fbee.modules.mybatis.model.Orders;

@MyBatisDao
public interface OrdersMapper extends CrudDao<OrdersEntity>{
	
	/**
	 * 总控平台
	 * 订单列表查询
	 * @param entity
	 * @return
	 */
	List<Map> queryOrdersList(OrdersEntity entity);
	/**
	 * 总控平台
	 * 获取订单条数
	 */
	int getzkCount(OrdersEntity entity);
	
	
	/**
	 * 获取订单条数
	 */
	int getCount(OrdersEntity entity);
	
	/**
	 * 订单列表查询
	 * @param entity
	 * @return
	 */
	List<Map> selectOrdersList(OrdersEntity entity);
	
	/**
	 * 根据订单流水号查询订单信息
	 * @param orderNo
	 * @return
	 */
	Map<String,String> selectByOrderNo(String orderNo);
	
	/**
	 * 根据订单流水号查询支付信息
	 * @param orderNo
	 * @return
	 */
	Map<String,String> selectPayInfoByOrderNo(String orderNo);

	int deleteByPrimaryKey(String orderNo);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
	/**
	 * @param staffId
	 * @return
	 */
	List<OrdersEntity> getStaffOrders(Integer staffId);

}