package com.fbee.modules.mybatis.dao;

import java.util.Map;

import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.model.OrderShareInfo;

@MyBatisDao
public interface OrderShareInfoMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(OrderShareInfo record);

    int insertSelective(OrderShareInfo record);

    OrderShareInfo selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(OrderShareInfo record);

    int updateByPrimaryKey(OrderShareInfo record);
    
    /**
     * 根据订单流水号获取分享信息
     */
    Map<String,String> selectShareInfoByOrderNo(String orderNo);
    
    /**
     * 根据订单流水号获取淘蜂享信息
     */
    Map<String,String> selectTaoShareInfoByOrderNo(String orderNo);
    
}