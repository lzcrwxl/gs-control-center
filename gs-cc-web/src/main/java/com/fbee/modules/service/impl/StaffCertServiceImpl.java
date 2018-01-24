package com.fbee.modules.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fbee.modules.bean.ErrorMsg;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.page.Page;
import com.fbee.modules.core.utils.DateUtils;
import com.fbee.modules.form.StaffCertForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.mybatis.dao.CertsInfoMapper;
import com.fbee.modules.mybatis.dao.TenantsStaffCertsInfoMapper;
import com.fbee.modules.mybatis.entity.TenantsStaffCertsInfoEntity;
import com.fbee.modules.service.StaffCertService;
import com.fbee.modules.service.basic.BaseService;
import com.fbee.modules.utils.DictionarysCacheUtils;

public class StaffCertServiceImpl extends BaseService implements StaffCertService {
	
	@Autowired
	TenantsStaffCertsInfoMapper tenantsStaffCertsInfoMapper;
	@Autowired
	CertsInfoMapper certsInfoMapper;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public JsonResult getStaffCertsList(StaffCertForm staffCertForm, Integer pageNumber,
			Integer pageSize) {
		
		try {
			TenantsStaffCertsInfoEntity tenantsStaffCertsInfoEntity = new TenantsStaffCertsInfoEntity();
			tenantsStaffCertsInfoEntity.setCertifiedStatus(staffCertForm.getCertifiedStatus());//认证状态
			tenantsStaffCertsInfoEntity.setCertType(staffCertForm.getCertType());//证书名称
			tenantsStaffCertsInfoEntity.setCertNo(staffCertForm.getCertNo());//证书编号
			tenantsStaffCertsInfoEntity.setAddTimeLeft(staffCertForm.getAddTimeLeft());//提交时间- 左
			tenantsStaffCertsInfoEntity.setAddTimeRight(staffCertForm.getAddTimeRight());//提交时间-右
			Integer totalCount = tenantsStaffCertsInfoMapper.getstaffCertCount(tenantsStaffCertsInfoEntity);
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
				
				tenantsStaffCertsInfoEntity.setPageNumber(page.getOffset());
				tenantsStaffCertsInfoEntity.setPageSize(page.getRowNum());
				List<Map> resultList = tenantsStaffCertsInfoMapper.getstaffCertList(tenantsStaffCertsInfoEntity);
				//String str = "";
				for(Map<String, Object> map : resultList){
					if(map.get("addTime") != null){
            			map.put("addTime", DateUtils.formatDateTime((Date)map.get("addTime")));
            		}else {
						map.put("addTime", "");
					}
					
					if(map.get("certificationDate") != null){
            			map.put("certificationDate", DateUtils.formatDate((Date)map.get("certificationDate")));
            		}else {
						map.put("certificationDate", "");
					}
					
					if(map.get("certExpireDate") != null){
						map.put("certExpireDate", DateUtils.formatDate((Date)map.get("certExpireDate")));
					}else {
						map.put("certExpireDate", "");
					}
					
					if(map.get("certType") != null){
						map.put("certType",DictionarysCacheUtils.getCertType((String)map.get("certType")));
					}else {
						map.put("certType", "");
					}
					//发证机构
					if(map.get("certificationBody") != null&&!map.get("certificationBody").equals("")){
						if(map.get("certificationBody").equals("03")){
							map.put("certificationBody", map.get("otherCertificationBody"));
						}else {
							map.put("certificationBody",DictionarysCacheUtils.getCetAuthority((String)map.get("certificationBody")));
						}
					}else {
						map.put("certificationBody", "");
					}
					//鉴定等级
					if(map.get("authenticateGrade") != null && !map.get("authenticateGrade").equals("")){
						map.put("authenticateGrade",DictionarysCacheUtils.getAuthenticatGrade((String)map.get("authenticateGrade")));
					}else {
						map.put("authenticateGrade", "");
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
	//证书详情
	@Override
	public JsonResult selectCertInfoById(Integer id) {
		// TODO Auto-generated method stub

		try {
			Map<String, String> map = tenantsStaffCertsInfoMapper.selectCertInfoById(id);
			if (map.containsKey("CertType")) {
				map.put("CertType",DictionarysCacheUtils.getCertType((String)map.get("CertType")));
			}
			//发证机构
			if(map.get("certificationBody") != null&&!map.get("certificationBody").equals("")){
				if(map.get("certificationBody").equals("03")){
					map.put("certificationBody", map.get("otherCertificationBody"));
				}else {
					map.put("certificationBody",DictionarysCacheUtils.getCetAuthority((String)map.get("certificationBody")));
				}
			}else {
				map.put("certificationBody", "");
			}
			//鉴定等级
			if(map.get("authenticateGrade") != null && !map.get("authenticateGrade").equals("")){
				map.put("authenticateGrade",DictionarysCacheUtils.getAuthenticatGrade((String)map.get("authenticateGrade")));
			}else {
				map.put("authenticateGrade", "");
			}
			return JsonResult.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
			return JsonResult.failure(ResultCode.DATA_ERROR);
		}
	}
	
	//证书审核-通过
	@Override
	public JsonResult passStaffCertById(Integer id) {

		try {
			TenantsStaffCertsInfoEntity tenantsStaffCertsInfoEntity = new TenantsStaffCertsInfoEntity();
			tenantsStaffCertsInfoEntity.setCertifiedStatus("02");
			tenantsStaffCertsInfoEntity.setId(id);
			//审核通过更新证书表
			tenantsStaffCertsInfoMapper.update(tenantsStaffCertsInfoEntity);
			return JsonResult.success(ResultCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
			return JsonResult.failure(ResultCode.DATA_ERROR);
		}
	}
	
	//证书审核-不通过
		@Override
		public JsonResult nopassStaffCertById(Integer id) {

			try {
				TenantsStaffCertsInfoEntity tenantsStaffCertsInfoEntity = new TenantsStaffCertsInfoEntity();
				tenantsStaffCertsInfoEntity.setCertifiedStatus("03");
				tenantsStaffCertsInfoEntity.setId(id);
				//审核不通过更新证书表
				tenantsStaffCertsInfoMapper.update(tenantsStaffCertsInfoEntity);
				return JsonResult.success(ResultCode.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
				return JsonResult.failure(ResultCode.DATA_ERROR);
			}
		}
		
		
		
}
