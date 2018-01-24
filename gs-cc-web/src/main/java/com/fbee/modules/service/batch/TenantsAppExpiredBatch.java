package com.fbee.modules.service.batch;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import com.fbee.modules.mybatis.dao.TenantsApplicationMapper;
import com.fbee.modules.mybatis.dao.TenantsAppsMapper;
import com.fbee.modules.mybatis.model.TenantsApps;

public class TenantsAppExpiredBatch extends BaseJob{

	@Autowired
	TenantsAppsMapper tenantsAppsMapper;
	@Autowired
	TenantsApplicationMapper tenantsApplicationMapper;
	
	@Override
	public void executeJob() throws IOException, DocumentException {
		// TODO 
		//logger.info("总控平台系统自动失效门店批处理开始...");
		/*List<TenantsApps> tenants = tenantsAppsMapper.selectCancleTenants();
		if(tenants.size()>0){
			Date time = new Date();
			for (TenantsApps tenantsApps : tenants) {
				Date time2 = tenantsApps.getDueDate();
				
				if(time.after(time2)){
					//门店不可用
					tenantsApps.setIsUsable("0");
					tenantsApps.setModifyTime(new Date());
					//门店状态已到期
					tenantsApps.setTenantStatus("03");
					tenantsAppsMapper.updateByPrimaryKeySelective(tenantsApps);
				}
			}
		}*/
	}
	
	/*@Override
	public void executeJob() throws IOException, DocumentException {
		// TODO 
		logger.info("系统自动拒绝阿姨信息批处理开始...");
		rejectStaff();
		
	}*/
}
