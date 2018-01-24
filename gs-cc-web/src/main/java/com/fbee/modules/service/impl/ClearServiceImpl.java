package com.fbee.modules.service.impl;

import com.fbee.modules.bean.ErrorMsg;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.page.Page;
import com.fbee.modules.core.utils.DateUtils;
import com.fbee.modules.form.ClearForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.mybatis.dao.*;
import com.fbee.modules.mybatis.entity.TenantsFinanceRecordsEntity;
import com.fbee.modules.mybatis.model.*;
import com.fbee.modules.service.ClearService;
import com.fbee.modules.service.basic.BaseService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ClearServiceImpl extends BaseService implements ClearService {

    @Autowired
    TenantsTradeRecordsMapper tenantsTradeRecordsMapper;
    @Autowired
    OrdersMapper order;
    @Autowired
    TenantsFundsMapper TenantsFunds;
    @Autowired
    TenantsFinanceRecordsMapper tenantsFinanceRecordsMapper;

    @Autowired
    TradeRecordsMapper tradeRecordsMapper;
    @Autowired
    tenantsFlowMapper tenantsFlowMapper;


    @SuppressWarnings({"rawtypes"})
    @Override
    public JsonResult SelectClearList(ClearForm clearForm, int pageNumber,
                                      int pageSize) {
        // 查询清算列表
        try {
            Map<Object, Object> map = Maps.newHashMap();
            map.put("tradeTimeLeft", clearForm.getTradeTimeLeft());//发起时间- 左
            map.put("tradeTimeRight", clearForm.getTradeTimeRight());//发起时间- 右
            map.put("tradeType", clearForm.getTradeType());//支付类型
            map.put("tradeChannel", clearForm.getTradeChannel());//清算渠道
            map.put("status", clearForm.getStatus());//状态

            Integer totalCount = tenantsFinanceRecordsMapper.getClearCount(map);


            // 分页实体
            Page<Map> page = new Page<Map>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);

            //最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            map.put("pageNumber", page.getOffset());
            map.put("pageSize", page.getRowNum());
            if (totalCount > 0) {
                //提现/手续费
                List<Map> resultList1 = tenantsFinanceRecordsMapper.getClearList(map);

                for (Map<String, Object> map1 : resultList1) {

                    System.out.println(map1.get("modifyTime"));

                    if (map1.get("modifyTime") != null) {
                        map1.put("modifyTime", DateUtils.formatDateTime((Date) map1.get("modifyTime")));
                    } else {
                        map1.put("modifyTime", "");
                    }
                    System.out.println(map1.get("orderNo"));


                    if (map1.get("orderNo") != null && !map1.get("orderNo").toString().equals("")) {
                        tenantsFlow tenantsFlow = tenantsFlowMapper.getByOrderNo((String) map1.get("orderNo"));
                        map1.put("tradeFlowNoBt", tenantsFlow.getTradeFlowNo());
                    } else {
                        map1.put("orderNo", "--");
                        map1.put("tradeFlowNoBt", "--");
                    }

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

    //提现清算
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult tiXianClearById(String inOutNo, String tradeNo, Integer tenantId, String tradeFlowNo) {
        try {

            //提现更新系统支付流水表trade_records
            TradeRecords tradeRecords = tradeRecordsMapper.selectByPrimaryKey(tradeFlowNo);
            tradeRecords.setTradeStatis("02");
            tradeRecords.setModifyTime(new Date());
            tradeRecords.setTradeFlowNo(tradeFlowNo);
            tradeRecordsMapper.updateByPrimaryKeySelective(tradeRecords);

            //提现更新tenants_finance_records交易表状态,交易成功
            TenantsFinanceRecords financeRecords = tenantsFinanceRecordsMapper.selectByPrimaryKey(inOutNo);
            financeRecords.setInOutNo(inOutNo);
            financeRecords.setStatus("03");
            financeRecords.setModifyTime(new Date());
            tenantsFinanceRecordsMapper.updateByPrimaryKeySelective(financeRecords);
            //提现更新tenants_trade_records表状态，交易成功
            TenantsTradeRecords tenantsTradeRecords = tenantsTradeRecordsMapper.selectByPrimaryKey(tradeNo);
            tenantsTradeRecords.setTradeNo(tradeNo);
            tenantsTradeRecords.setStatus("02");
            tenantsTradeRecords.setModifyTime(new Date());
            tenantsTradeRecords.setAddTime(new Date());
            tenantsTradeRecordsMapper.updateByPrimaryKeySelective(tenantsTradeRecords);

            //查询提现收入续费
            TenantsFinanceRecordsEntity re = tenantsFinanceRecordsMapper.selectByRelatedTrans(tenantsTradeRecords.getInOutNo());
            BigDecimal fee = new BigDecimal(0);
            if(re != null && re.getInOutAmount() != null){
                fee = re.getInOutAmount();
            }
            //更新租户资金表tenants_funds 总金额和可用余额
            TenantsFunds tenantsfunds = TenantsFunds.selectByPrimaryKey(tenantId);
            //租户资金总额/冻结金额 减 提现金额 减 手续费
            tenantsfunds.setTenantId(tenantId);
            tenantsfunds.setTotalAmount(tenantsfunds.getTotalAmount().subtract(tenantsTradeRecords.getTradeAmount()).subtract(fee));
            tenantsfunds.setFrozenAmount(tenantsfunds.getFrozenAmount().subtract(tenantsTradeRecords.getTradeAmount()).subtract(fee));
            TenantsFunds.updateByPrimaryKeySelective(tenantsfunds);
            return JsonResult.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
            return JsonResult.failure(ResultCode.DATA_ERROR);
        }
    }

    //退款清算
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult tuiKuanClearById(String orderNo, String inOutNo, String tradeNo, Integer tenantId, String tradeFlowNo) {
        try {

            //提现更新系统支付流水表trade_records
            TradeRecords tradeRecords = tradeRecordsMapper.selectByPrimaryKey(tradeFlowNo);
            tradeRecords.setTradeStatis("02");
            tradeRecords.setModifyTime(new Date());
            tradeRecords.setTradeFlowNo(tradeFlowNo);
            tradeRecordsMapper.updateByPrimaryKeySelective(tradeRecords);


            //退款更新order表状态,交易完成
            Orders orders = order.selectByPrimaryKey(orderNo);
            orders.setOrderNo(orderNo);
            orders.setOrderStatus("05");
            orders.setModifyTime(new Date());
            order.updateByPrimaryKey(orders);
            //更新tenants_trade_records表状态 交易成功
            TenantsTradeRecords tenantsTradeRecords = tenantsTradeRecordsMapper.selectByPrimaryKey(tradeNo);
            tenantsTradeRecords.setTradeNo(tradeNo);
            tenantsTradeRecords.setStatus("02");
            tenantsTradeRecords.setAddTime(new Date());
            tenantsTradeRecords.setModifyTime(new Date());
            tenantsTradeRecordsMapper.updateByPrimaryKeySelective(tenantsTradeRecords);

            //退款更新tenants_finance_records交易表状态
            System.out.println("++++++++++++++++++++退款清算开始+++++++++++++++++++++");
            TenantsFinanceRecords financeRecords = tenantsFinanceRecordsMapper.selectByPrimaryKey(inOutNo);
            financeRecords.setStatus("03");//02处理中03处理完成
            financeRecords.setModifyTime(new Date());
            tenantsFinanceRecordsMapper.updateByPrimaryKeySelective(financeRecords);
            System.out.println("+++++++++++++++++++++退款清算完成++++++++++++");
            //更新租户资金表tenants_funds 总金额和可用余额
            TenantsFunds tenantsfunds = TenantsFunds.selectByPrimaryKey(tenantId);
            //判断退款金额和冻结金额是否一致
            //int compareTo = tenantsfunds.getFrozenAmount().compareTo(tenantsTradeRecords.getTradeAmount());
            //if(compareTo == 0){
            Log.info("++++++++++++退款金额+++" + tradeRecords.getTradeAmount());
            //租户资金总额减提现金额
            tenantsfunds.setTenantId(tenantId);
            //总额-
            tenantsfunds.setTotalAmount(tenantsfunds.getTotalAmount().subtract(tradeRecords.getTradeAmount()));
            //冻结金额-
            tenantsfunds.setFrozenAmount(tenantsfunds.getFrozenAmount().subtract(tradeRecords.getTradeAmount()));
            tenantsfunds.setModifyTime(new Date());
            TenantsFunds.updateByPrimaryKeySelective(tenantsfunds);
            //}else{
            //	return JsonResult.failure(ResultCode.DATA_ERROR);
            //}
            return JsonResult.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
            return JsonResult.failure(ResultCode.DATA_ERROR);
        }
    }

    @Override
    public List<Map> SelectClearList(ClearForm clearForm) {
        Map<Object, Object> map = Maps.newHashMap();
        map.put("tradeTimeLeft", clearForm.getTradeTimeLeft());//发起时间- 左
        map.put("tradeTimeRight", clearForm.getTradeTimeRight());//发起时间- 右
        map.put("tradeType", clearForm.getTradeType());//支付类型
        map.put("tradeChannel", clearForm.getTradeChannel());//清算渠道
        map.put("status", clearForm.getStatus());//状态
        List<Map> resultList1 = tenantsFinanceRecordsMapper.getClearList(map);

        for (Map<String, Object> map1 : resultList1) {

            System.out.println(map1.get("modifyTime"));

            if (map1.get("modifyTime") != null) {
                map1.put("modifyTime", DateUtils.formatDateTime((Date) map1.get("modifyTime")));
            } else {
                map1.put("modifyTime", "");
            }
            System.out.println(map1.get("orderNo"));


            if (map1.get("orderNo") != null && !map1.get("orderNo").toString().equals("")) {
                tenantsFlow tenantsFlow = tenantsFlowMapper.getByOrderNo((String) map1.get("orderNo"));
                map1.put("tradeFlowNoBt", tenantsFlow.getTradeFlowNo());
            } else {
                map1.put("orderNo", "--");
                map1.put("tradeFlowNoBt", "--");
            }

        }
        return resultList1;
    }
}
