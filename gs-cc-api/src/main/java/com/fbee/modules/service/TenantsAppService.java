package com.fbee.modules.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fbee.modules.form.TenantsAppForm;
import com.fbee.modules.form.TenantsManagementForm;
import com.fbee.modules.jsonData.basic.JsonResult;

/**
 *@Description: 
 *
 *@author: zhangsq
 *@date:   2017年4月19日 下午6:19:33
 * 
 */
public interface TenantsAppService {

	/**
	 *  已入住门店列表
	 * @param pageSize 
	 * @param pageNumber 
	 * @param tenantsAppForm 
	 * @return
	 */
	JsonResult getTenantsAppInfo(TenantsAppForm tenantsAppForm, Integer pageNumber, Integer pageSize);

	/**
	 *  已入驻门店详情
	 * @param pageSize 
	 * @param pageNumber 
	 * @param tenantId 
	 * @return
	 */
	JsonResult getTenantsAppById(Integer tenantId);
	
	
	/**
	 *  已入驻门店修改
	 * @param tenantId 
	 * @return
	 */
	JsonResult updateTenantsById(Integer tenantId , TenantsManagementForm tenantsManagementForm);
	
	/**
	 * logo图片保存
	 * @param fileLogo
	 * @return
	 */
	Map<String,String> uploadLogoImg(MultipartFile fileLogo);
	
	/**
	 * 根据门店id修改密码
	 * @param tenantId
	 * @param loginAccount,newpassword
	 * @return
	 */
	public JsonResult resetPassword(Integer tenantId,String loginAccount,String newpassword);
	
	/**
	 * 冻结解冻
	 * @param tenantId
	 * @param tenantStatusCode
	 * @return
	 */
	public JsonResult isblock(Integer tenantId,String tenantStatusCode);
	
	
	/**
	 * 银行列表
	 * @return
	 */
	public JsonResult getBankCode();
	
}
