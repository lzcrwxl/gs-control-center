package com.fbee.modules.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fbee.modules.bean.ErrorMsg;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.page.Page;
import com.fbee.modules.form.CustomerQueryForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.jsonData.extend.TenantsCustomerJson;
import com.fbee.modules.mybatis.dao.MembersInfoMapper;
import com.fbee.modules.mybatis.entity.MembersInfoEntity;
import com.fbee.modules.operation.CustomerOpt;
import com.fbee.modules.service.CustomerService;
import com.fbee.modules.service.basic.BaseService;
import com.google.common.collect.Lists;

public class CustomerServiceImpl extends BaseService implements CustomerService {

	
	@Autowired
	MembersInfoMapper membersInfo;
	
	CustomerOpt customeropt = new CustomerOpt();
	
	/**
	 * 客户管理-客户查询
	 */
	@Override
	public JsonResult queryCustomerList( CustomerQueryForm customerqueryform, int pageNumber,
			int pageSize) {
		try{
			//获取总条数
			Map<String, Object>  map =new HashMap<String, Object>();
			//map.put("tenantId", tenantId);
			map.put("customerName",customerqueryform.getCustomerName());
			map.put("customerMobile",customerqueryform.getCustomerMobile());
			map.put("serviceProvice",customerqueryform.getServiceProvice());
			map.put("serviceCity",customerqueryform.getServiceCity());
			map.put("serviceCounty",customerqueryform.getServiceCounty());
			Integer totalCount = membersInfo.getzkCustomerQueryCount(map);
			//分页实体
			Page<TenantsCustomerJson> page = new Page<TenantsCustomerJson>();
			page.setPage(pageNumber);
	        page.setRowNum(pageSize);
	        if(totalCount==null){
	            return JsonResult.success(page);
	        }
	        //最大页数判断
	        int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
	        if (pageM > 0) {
	            page.setPage(pageM);
	        }
	        
	        if (totalCount > 0) {
	        	map.put("pageNumber", page.getOffset());
		        map.put("pageSize", page.getRowNum());
	        	List<MembersInfoEntity> list=membersInfo.getzkCustomerQueryList(map);
	        	List<TenantsCustomerJson> resultList=Lists.newArrayList();
	        	
	        	for(MembersInfoEntity entity:list){
	        		//添加返回结果集
	        		resultList.add(customeropt.zkbuildtenantscustomerJsons(entity));
	        	}
	        	page.setRows(resultList);
	            page.setRecords(totalCount.longValue());
	        }
	        return JsonResult.success(page);
		}catch (Exception e) {
	        	e.printStackTrace();
	        	Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
	            return JsonResult.failure(ResultCode.DATA_ERROR);
	    }
	}
	
}
