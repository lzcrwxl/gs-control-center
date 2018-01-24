package com.fbee.modules.service;


import com.fbee.modules.form.StaffCertForm;
import com.fbee.modules.jsonData.basic.JsonResult;

/**
 *@Description: 总控平台-证书管理-证书列表查询
 *
 *@author: zhangsq
 *@date:   2017年3月3日 上午10:13:17
 * 
 */
public interface StaffCertService {
	
	/**
	 * 获取证书列表
	 * @param staffCertFrom
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	JsonResult getStaffCertsList(StaffCertForm staffCertForm ,Integer pageNumber, Integer pageSize);

	
	/**
	 * 查询证书详情
	 * @param Id
	 * @return
	 */
	JsonResult selectCertInfoById(Integer id);
	
	
	/**
	 * 证书审核-通过
	 * @param Id
	 * @return
	 */
	JsonResult passStaffCertById(Integer id);
	
	/**
	 * 证书审核-不通过
	 * @param Id
	 * @return
	 */
	JsonResult nopassStaffCertById(Integer id);

}
