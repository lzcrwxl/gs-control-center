package com.fbee.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsJobsEntity;
import com.fbee.modules.mybatis.model.TenantsJobs;

@MyBatisDao
public interface TenantsJobsMapper extends CrudDao<TenantsJobsEntity>{

	/**
	 * 招聘信息总数
	 * @param map
	 * @return
	 */
	Integer getTenantsJobsInfoCount(Map map);
	
	/**
	 * 招聘信息列表
	 * @param serviceType
	 * @return
	 */
	List<Map> getTenantsJobsInfoList(Map paramMap);

	/**
	 * 根据主键获取租户招聘信息
	 * @param id
	 * @return
	 */
	Map getTenantsJobsInfoById(Integer id);
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	TenantsJobs selectByPrimaryKey(Integer id);
	
	/**
	 * 主键更新
	 * @param tenantsJobs
	 */
	void updateByPrimaryKey(TenantsJobs tenantsJobs);
	/**
	 * 数据入库
	 * @param entity
	 */
	void insert(TenantsJobs entity);
	
   
}