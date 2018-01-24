package com.fbee.modules.mybatis.dao;

import com.fbee.modules.mybatis.model.OrderShareStaffInfo;

public interface OrderShareStaffInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderShareStaffInfo record);

    int insertSelective(OrderShareStaffInfo record);

    OrderShareStaffInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderShareStaffInfo record);

    int updateByPrimaryKey(OrderShareStaffInfo record);
    
}