package com.fbee.modules.mybatis.dao;

import java.util.Map;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsFundsEntity;
import com.fbee.modules.mybatis.model.TenantsFunds;

@MyBatisDao
public interface TenantsFundsMapper extends CrudDao<TenantsFundsEntity>{
	
	TenantsFundsEntity selectByPrimaryKey(Integer tenantId);

	int updateByPrimaryKeySelective(TenantsFunds tenantsFunds);
	
	
	
	/**
	 * 总控平台-财务总览
	 * 查询账户总额度
	 * @return
	 */
	public String getTenantsTotalAmount();
		
	/**
	 * 总控平台-财务总览
	 * 查询账户总冻结金额
	 * @return
	 */
	public String getTenantsFrozenAmount();
	
	
	
	
	
}