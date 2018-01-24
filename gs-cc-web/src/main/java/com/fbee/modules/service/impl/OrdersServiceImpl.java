package com.fbee.modules.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbee.modules.bean.ErrorMsg;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.page.Page;
import com.fbee.modules.core.utils.StringUtils;
import com.fbee.modules.form.OrdersForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.mybatis.dao.OrdersMapper;
import com.fbee.modules.mybatis.entity.OrdersEntity;
import com.fbee.modules.service.OrderService;
import com.fbee.modules.service.basic.BaseService;
import com.fbee.modules.utils.DictionarysCacheUtils;
@Service
public class OrdersServiceImpl extends BaseService implements OrderService{
	
	@Autowired
	OrdersMapper orderDao;
	
	/**
	 * 订单列表查询
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public JsonResult queryOrdersList(OrdersForm form) {
		try {
			OrdersEntity entity = new OrdersEntity();
			
			entity.setStartTime(form.getOrderStartTime());
			entity.setEndTime(form.getOrderEndTime());
			entity.setMemberName(form.getMemberName());
			entity.setMemberMobile(form.getMemberMobile());
			entity.setOrderNo(form.getOrderNo());
			entity.setOrderStatus(form.getOrderStatus());
			entity.setServiceItemCode(form.getServiceItemCode());
			entity.setOrderSource(form.getOrderSource());
			entity.setPageNum(form.getPageNum()-1);
			entity.setPageSize(form.getPageSize());
			//entity.setShareOrderNo(form.getShareOrderNo());
			//entity.setTenantId(form.getTenantId());
			Integer totalCount =  this.orderDao.getzkCount(entity);
            //分页实体
            Page<Map> page=new Page<Map>();
        	page.setPage(form.getPageNum());
        	page.setRowNum(form.getPageSize());
            //最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            if (totalCount > 0) {
            	entity.setPageNum(page.getOffset());
				entity.setPageSize(form.getPageSize());
				List<Map>  resultList1 = orderDao.queryOrdersList(entity);
				String str = "";
            	for(Map<String, Object> map : resultList1){
            		str = (String)map.get("serviceProvice");
            		if(!StringUtils.isBlank(str)){
            			map.put("serviceProvice", DictionarysCacheUtils.getProviceName(str));
            		}
            		str = (String)map.get("serviceCity");
            		if(!StringUtils.isBlank(str)){
            			map.put("serviceCity", DictionarysCacheUtils.getCityName(str));
            		}
            		str = (String)map.get("serviceCounty");
            		if(!StringUtils.isBlank(str)){
            			map.put("serviceCounty", DictionarysCacheUtils.getCountyName(str));
            		}
            		if (map.get("price")==null) {
						map.put("price", new BigDecimal(0));
					}
            		BigDecimal prices = (BigDecimal) map.get("price");
            		//System.out.println("+++++++++福价格"+prices);
            		if (map.get("serviceCharge")==null) {
            			map.put("serviceCharge", new BigDecimal(0));
					}
            		BigDecimal serviceCharges =(BigDecimal) map.get("serviceCharge");
            		//System.out.println("+++++++++服务费"+serviceCharges);
            		map.put("totalAmount", prices.add(serviceCharges));
            		//System.out.println("+++++++++总金额"+map.get("totalAmount"));
            		
            	}
				
                page.setRows(resultList1);
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
