package com.fbee.modules.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fbee.modules.bean.Constants;
import com.fbee.modules.bean.ErrorMsg;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.page.Page;
import com.fbee.modules.form.FundsForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.mybatis.dao.TenantsFinanceRecordsMapper;
import com.fbee.modules.mybatis.dao.TenantsFundsMapper;
import com.fbee.modules.mybatis.dao.TenantsTradeRecordsMapper;
import com.fbee.modules.mybatis.entity.TenantsFinanceRecordsEntity;
import com.fbee.modules.mybatis.entity.TenantsTradeRecordsEntity;
import com.fbee.modules.mybatis.model.TenantsFinanceRecords;
import com.fbee.modules.service.FundService;
import com.fbee.modules.service.basic.BaseService;



@Service
public class FundServiceImpl extends BaseService implements FundService{

	
	@Autowired
	TenantsFundsMapper tenantsFundsDao;
	@Autowired
	TenantsTradeRecordsMapper tenantsTradeRecordsMapper;
	@Autowired
	TenantsFinanceRecordsMapper tenantsFinanceRecordsMapper;
	@Override
	public JsonResult queryFunds() {
		// 查询账户总览信息
		try{
		double totalAmount = 0;//账户总额
		double forzenAmount = 0;//账户总冻结金额
		double CtradeAmount = 0;//总充值金额
		double TtradeAmount = 0;//总充值金额
		double DtradeAmount = 0;//总退款金额
		double StradeAmount = 0;//总手续费金额
		//double XtradeAmount = 0;//总续费金额
		String totalAmountStr = tenantsFundsDao.getTenantsTotalAmount();// 获取账户总额
		String forzenAmountStr = tenantsFundsDao.getTenantsFrozenAmount();// 获取账户总冻结金额
		String CtradeAmountStr = tenantsTradeRecordsMapper.getTenantsCTradeAmount();// 获取总充值金额
		String TtradeAmountStr = tenantsTradeRecordsMapper.getTenantsTTradeAmount();// 获取总提现金额
		String DtradeAmountStr = tenantsTradeRecordsMapper.getTenantsDTradeAmount();// 获取总退款金额
		String StradeAmountStr = tenantsTradeRecordsMapper.getTenantsSTradeAmount();// 获取总退款金额
		//String XtradeAmountStr = tenantsTradeRecordsMapper.getTenantsXTradeAmount();// 获取总续费金额
		if (totalAmountStr != null) {
			totalAmount = Double.parseDouble(totalAmountStr);
		}
		if (forzenAmountStr != null) {
			forzenAmount = Double.parseDouble(forzenAmountStr);
		}
		if (CtradeAmountStr != null) {
			CtradeAmount = Double.parseDouble(CtradeAmountStr);
		}
		if (TtradeAmountStr != null) {
			TtradeAmount = Double.parseDouble(TtradeAmountStr);
		}
		if (DtradeAmountStr != null) {
			DtradeAmount = Double.parseDouble(DtradeAmountStr);
		}
		if (StradeAmountStr != null) {
			StradeAmount = Double.parseDouble(StradeAmountStr);
		}
		//if (XtradeAmountStr != null) {
		//	XtradeAmount = Double.parseDouble(XtradeAmountStr);
		//}
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("totalAmount", totalAmount);
		map.put("forzenAmount", forzenAmount);
		map.put("CtradeAmount", CtradeAmount);
		map.put("TtradeAmount", TtradeAmount);
		map.put("DtradeAmount", DtradeAmount);
		map.put("StradeAmount", StradeAmount);
		//map.put("XtradeAmount", XtradeAmount);
		return JsonResult.success(map);
		}catch (Exception e) {
			e.printStackTrace();
			Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
			return JsonResult.failure(ResultCode.DATA_ERROR);
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public JsonResult SelectFundsList(FundsForm fundsForm, int pageNumber,
			int pageSize) {
		// 查询财务流水信息
		try {
			TenantsFinanceRecordsEntity tenantsFinanceRecords = new TenantsFinanceRecordsEntity();
			tenantsFinanceRecords.setTradeTimeLeft(fundsForm.getTradeTimeLeft());
			tenantsFinanceRecords.setTradeTimeLeft(fundsForm.getTradeTimeLeft());//创建时间- 左
			tenantsFinanceRecords.setTradeTimeRight(fundsForm.getTradeTimeRight());//创建时间- 右
			tenantsFinanceRecords.setTenantName(fundsForm.getTenantName());//门店名称
			tenantsFinanceRecords.setTradeType(fundsForm.getTradeType());//支付类型
			tenantsFinanceRecords.setTradeChannel(fundsForm.getTradeChannel());//支付渠道
			tenantsFinanceRecords.setOrderNo(fundsForm.getOrderNo());
			tenantsFinanceRecords.setTradeFlowNo(fundsForm.getTradeFlowNo());
			//Integer totalCount = tenantsTradeRecordsMapper.getCount(tenantsTradeRecordsEntity);
			Integer totalCount = tenantsFinanceRecordsMapper.getCount(tenantsFinanceRecords);
			// 分页实体
			Page<Map> page = new Page<Map>();
			page.setPage(pageNumber);
			page.setRowNum(pageSize);
			// 最大页数判断
			int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
			if (pageM > 0) {
				page.setPage(pageM);
			}
			if (totalCount > 0) {
				
				tenantsFinanceRecords.setPageNumber(page.getOffset());
				tenantsFinanceRecords.setPageSize(page.getRowNum());
				//List<Map> resultList = tenantsTradeRecordsMapper.getTenantsRecordList(tenantsFinanceRecords);
				List<Map> resultList = tenantsFinanceRecordsMapper.getTenantsRecordList(tenantsFinanceRecords);
				for(Map<String, Object> map1 : resultList){
					if(map1.get("IN_OUT_TYPE").equals("01")){
						//收入金额+
            			map1.put("IN_OUT_AMOUNT", Constants.GET+map1.get("IN_OUT_AMOUNT"));
            		}
					if(map1.get("IN_OUT_TYPE").equals("02")){
						//支出金额-
            			map1.put("IN_OUT_AMOUNT", Constants.PAY+map1.get("IN_OUT_AMOUNT"));
            		}
					if (map1.get("TRADE_FLOW_NO")!=null&&!map1.get("TRADE_FLOW_NO").equals("")) {
						System.out.println("+++++++++++++++"+map1.get("TRADE_FLOW_NO"));
						map1.put("TRADE_FLOW_NO", map1.get("TRADE_FLOW_NO"));
					}else {
						map1.put("TRADE_FLOW_NO", "--");
						System.out.println("+++++++++"+map1.get("TRADE_FLOW_NO"));
					}
					if (map1.get("tradeChannel")!=null&&!map1.get("tradeChannel").equals("")) {
						map1.put("tradeChannel", map1.get("tradeChannel"));
					}else {
						map1.put("tradeChannel", "--");
					}
					if (map1.get("ORDER_NO")!=null&&!map1.get("ORDER_NO").equals("")) {
						map1.put("ORDER_NO", map1.get("ORDER_NO"));
					}else {
						map1.put("ORDER_NO", "--");
					}
				}
				page.setRows(resultList);
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
