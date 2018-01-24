package com.fbee.modules.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fbee.modules.form.MendianForm;
import com.fbee.modules.form.TenantsManagementForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.json.TenantsBaseData;

/**
 *@Description: 门店管理服务层接口
 *
 *@author: zhangsq
 *@date:   2017年2月22日 下午4:59:29
 * 
 */
public interface TentansApplicationService {

	
	
	/**
	 * 
	 *  门店管理—查询列表
	 * @param pageSize 
	 * @param pageNumber 
	 * @return
	 */
	JsonResult queryTentansInfoList(MendianForm mendianForm, Integer pageNumber, Integer pageSize);
	
	
	/**
	 * 门店管理-门店详细
	 * @param applicationId
	 * @return
	 */
	TenantsBaseData getTentansBaseById(Integer applicationId);
	
	/**
	 * 门店管理-审核通过
	 * @param applicationId
	 * @param tenantsManagementForm
	 * @param fileLogo
	 * @param fileLience
	 * @param fileFront
	 * @param fileBack
	 * @return
	 */
	JsonResult tenantsPassById(TenantsManagementForm tenantsManagementForm);
	
	
	
	
	
	/**
	 * logo图片保存
	 * @param fileLogo
	 * @return
	 */
	Map<String,String> uploadLogoImg(MultipartFile fileLogo);
	
	/**
	 * 营业执照图片保存
	 * @param fileLience
	 * @return
	 */
	Map<String,String> uploadLienceImg(MultipartFile fileLience);
	
	/**
	 * 身份证正面图片保存
	 * @param fileFront
	 * @return
	 */
	Map<String,String> uploadFrontImg(MultipartFile fileFront);
	
	/**
	 * 身份证正面图片保存
	 * @param fileBack
	 * @return
	 */
	Map<String,String> uploadBackImg(MultipartFile fileBack);
	
	/**
	 * 门店管理-审核不通过
	 * @param applicationId
	 * @param remarks
	 * @return
	 */
	JsonResult nopassById(Integer applicationId,String remarks );
	
	/**
	 * 门店审核check
	 * @param domain
	 * @param loginAccount
	 * @return
	 */
	JsonResult tenantsCheck(String domain,String loginAccount );
}
