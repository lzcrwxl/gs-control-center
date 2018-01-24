package com.fbee.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsTradeRecordsEntity;
import com.fbee.modules.mybatis.model.TenantsTradeRecords;

@MyBatisDao
public interface TenantsTradeRecordsMapper extends CrudDao<TenantsTradeRecordsEntity>{
	
	
	TenantsTradeRecords selectByPrimaryKey(String tradeNo);
	
	int updateByPrimaryKeySelective(TenantsTradeRecords tradeNo);
	
	/**
	 * 总控平台-财务总览
	 * 查询历史充值总额
	 * @return
	 */
	public String getTenantsCTradeAmount();
	
	
	/**
	 * 总控平台-财务总览
	 * 查询历史提现总额
	 * @return
	 */
	public String getTenantsTTradeAmount();
	
	
	/**
	 * 总控平台-财务总览
	 * 查询历史退款总额
	 * @return
	 */
	public String getTenantsDTradeAmount();
	
	
	/**
	 * 总控平台-财务总览
	 * 查询手续费总额
	 * @return
	 */
	public String getTenantsSTradeAmount();
	
	
	/**
	 * 总控平台-财务总览
	 * 查询续费总额
	 * 暂时没有展开
	 * @return
	 */
	//public String getTenantsXTradeAmount();
	
	
	
	/**
	 * 总控平台-财务流水总条数
	 * @param 
	 * @return
	 */
	public int getCount(TenantsTradeRecordsEntity tenantsTradeRecordsEntity);
	
	
	/**
	 * 总控平台-查询财务流水信息
	 * @param 
	 * @return
	 */
	public List<Map> getTenantsRecordList(TenantsTradeRecordsEntity tenantsTradeRecordsEntity);

	
	
	
	/**
	 * 总控平台-提现总条数
	 * @param 
	 * @return
	 */
	public int getTixianCount(Map<Object, Object> map);
	
	
	/**
	 * 总控平台-查询提现列表
	 * @param 
	 * @return
	 */
	public List<Map> getTixianList(Map<Object, Object> map);
	
	
	
	/**
	 * 总控平台-退款总条数
	 * @param 
	 * @return
	 */
	public int getTuikuanCount(Map<Object, Object> map);
	
	
	/**
	 * 总控平台-查询退款列表
	 * @param 
	 * @return
	 */
	public List<Map> getTuikuanList(Map<Object, Object> map);
	
	

	List<Map> getTixianListExport(Map<Object, Object> map);

	List<Map> getTuikuanListExport(Map<Object, Object> map);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		//清算总数
		public int getClearCount(Map<Object, Object> map);

		//清算列表
		public List<Map> getClearList(Map<Object, Object> map);

}