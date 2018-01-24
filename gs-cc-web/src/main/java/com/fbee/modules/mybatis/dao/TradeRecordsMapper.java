package com.fbee.modules.mybatis.dao;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TradeRecordsEntity;
import com.fbee.modules.mybatis.model.TenantsFinanceRecords;
import com.fbee.modules.mybatis.model.TenantsTradeRecords;
import com.fbee.modules.mybatis.model.TradeRecords;

@MyBatisDao
public interface TradeRecordsMapper extends CrudDao<TradeRecordsEntity>{
	
	
	 TradeRecords selectByPrimaryKey(String tradeFlowNo);
	 
	 int updateByPrimaryKeySelective(TradeRecords tradeFlowNo);
}