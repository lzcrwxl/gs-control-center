package com.fbee.modules.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fbee.modules.bean.ErrorMsg;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.page.Page;
import com.fbee.modules.core.utils.DateUtils;
import com.fbee.modules.form.ResOrdersForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.jsonData.extend.ReserveOrdersJson;
import com.fbee.modules.mybatis.dao.ReserveOrdersMapper;
import com.fbee.modules.mybatis.entity.ReserveOrdersEntity;
import com.fbee.modules.service.ResOrderService;
import com.fbee.modules.service.basic.BaseService;
import com.fbee.modules.utils.DictionarysCacheUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;





public class ResOrderServiceImpl extends BaseService implements ResOrderService{

	
	

@Autowired
ReserveOrdersMapper reserveOrdersMapper;
	
	@Override
	public JsonResult getResOrdersList(ResOrdersForm resOrdersForm, Integer pageNumber,
			Integer pageSize) {
		try {
			
			//查询全部时，orderStatus 
			
			if("04".equals(resOrdersForm.getOrderStatus())){
				resOrdersForm.setOrderStatus(null);
			}
			
			// 获取总条数
			Map<Object, Object> map = Maps.newHashMap();
			//map.put("tenantId", tenantId);
			map.put("orderStatus", resOrdersForm.getOrderStatus());
			map.put("orderNo", resOrdersForm.getOrderNo());
			map.put("memberMobile", resOrdersForm.getMemberMobile());
			map.put("memberName", resOrdersForm.getMemberName());
			map.put("serviceItemCode", resOrdersForm.getServiceItemCode());
			map.put("orderTimeLeft", resOrdersForm.getOrderTimeLeft());
			map.put("orderTimeRight", resOrdersForm.getOrderTimeRight());
			Integer totalCount = reserveOrdersMapper.getResOrdersCount(map);
			// 分页实体
			Page<Map<String, Object>> page = new Page<Map<String, Object>>();
			page.setPage(pageNumber);
			page.setRowNum(pageSize);
			if (totalCount == null) {
				return JsonResult.success(page);
			}
			// 最大页数判断
			int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
			if (pageM > 0) {
				page.setPage(pageM);
			}
			if (totalCount > 0) {
				map.put("offset", page.getOffset());
				map.put("pageSize", page.getRowNum());
				List<Map<String, Object>> list = reserveOrdersMapper.getResOrdersList(map);

				for (Map<String, Object> map1 : list) {
					// code转中文
					/*if ((Date) map1.get("orderTime") != null) {
						map1.put("orderTime", DateUtils.formatDateTime((Date) map1.get("orderTime")));
					}*/
					if (!StringUtils.isBlank((String) map1.get("serviceItemCode"))) {
						map1.put("serviceItemCode",
								DictionarysCacheUtils.getServiceTypeName((String) map1.get("serviceItemCode")));
					}
					if (!StringUtils.isBlank((String) map1.get("serviceProvice"))) {
						map1.put("serviceProvice",
								DictionarysCacheUtils.getProviceName((String) map1.get("serviceProvice")));
					}
					if (!StringUtils.isBlank((String) map1.get("serviceCity"))) {
						map1.put("serviceCity", DictionarysCacheUtils.getCityName((String) map1.get("serviceCity")));
					}
					if (!StringUtils.isBlank((String) map1.get("serviceCounty"))) {
						map1.put("serviceCounty",
								DictionarysCacheUtils.getCountyName((String) map1.get("serviceCounty")));
					}
					if(StringUtils.isBlank((String) map1.get("memberMobile"))){
						map1.put("memberMobile","");
					}
					if(StringUtils.isBlank((String) map1.get("memberName"))){
						map1.put("memberName","");
					}
				}
				page.setRows(list);
				page.setRecords(totalCount.longValue());
			}
			return JsonResult.success(page);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
			return JsonResult.failure(ResultCode.DATA_ERROR);
		}
	}

}
