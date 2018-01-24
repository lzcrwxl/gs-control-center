package com.fbee.modules.service;

		import java.util.List;
		import java.util.Map;

		import com.fbee.modules.form.ClearForm;
		import com.fbee.modules.jsonData.basic.JsonResult;

public interface ClearService {

	/**
	 * 清算查询列表
	 * @param fundsform
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	JsonResult SelectClearList(ClearForm clearForm, int pageNumber, int pageSize);

	List<Map> SelectClearList(ClearForm clearForm);
	/**
	 * 提现清算
	 * @param inOutNo
	 * @param tenantId
	 * @return
	 */
	JsonResult tiXianClearById( String inOutNo,String tradeNo, Integer tenantId,String tradeFlowNo);
	/**
	 * 退款清算
	 * @param inOutNo
	 * @param tenantId
	 * @return
	 */
	JsonResult tuiKuanClearById(String orderNo ,String inOutNo, String tradeNo, Integer tenantId,String tradeFlowNo);
}
