package com.fbee.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsStaffCertsInfoEntity;

@MyBatisDao
public interface TenantsStaffCertsInfoMapper extends CrudDao<TenantsStaffCertsInfoEntity>{

	/**
	 * 获取阿姨的所有证书信息
	 * @param staffId
	 * @return
	 */
	List<TenantsStaffCertsInfoEntity> getSatffAllCerts(Integer staffId);
	
	
	/**
	 * 总控平台-证书管理-查询总条数
	 * @param tenantsStaffCertsInfoEntity
	 * @return
	 */
	public int getstaffCertCount(TenantsStaffCertsInfoEntity tenantsStaffCertsInfoEntity);
	
	/**
	 * 总控平台-证书管理-查询所有证书
	 * @param tenantsStaffCertsInfoEntity
	 * @return
	 */
	public List<Map> getstaffCertList(TenantsStaffCertsInfoEntity tenantsStaffCertsInfoEntity);
	
	
	/**
	 * 总控平台-证书详情
	 * @param Id
	 * @return
	 */
	Map<String,String>  selectCertInfoById(Integer id);
	
	
	
	/*Map<String,String>  checkCertById(CertCheckForm certCheckForm);*/
	
	
	
	int update(TenantsStaffCertsInfoEntity tenantsStaffCertsInfoEntity);
	
}