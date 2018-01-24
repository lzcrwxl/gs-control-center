package com.fbee.modules.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fbee.modules.bean.Constants;
import com.fbee.modules.bean.ErrorMsg;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.config.Global;
import com.fbee.modules.core.page.Page;
import com.fbee.modules.core.utils.DateUtils;
import com.fbee.modules.core.utils.StringUtils;
import com.fbee.modules.form.TenantsAppForm;
import com.fbee.modules.form.TenantsManagementForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.mybatis.dao.TenantsAppsMapper;
import com.fbee.modules.mybatis.dao.TenantsBankCardsMapper;
import com.fbee.modules.mybatis.dao.TenantsUsersMapper;
import com.fbee.modules.mybatis.dao.BankMapper;
import com.fbee.modules.mybatis.entity.TenantsBankCardsEntity;
import com.fbee.modules.mybatis.entity.TenantsUsersEntity;
import com.fbee.modules.mybatis.model.TenantsApps;
import com.fbee.modules.mybatis.model.TenantsBankCards;
import com.fbee.modules.mybatis.model.TenantsUsers;
import com.fbee.modules.service.SmsService;
import com.fbee.modules.service.TenantsAppService;
import com.fbee.modules.service.basic.BaseService;
import com.fbee.modules.service.basic.ServiceException;
import com.fbee.modules.utils.DictionarysCacheUtils;
import com.fbee.modules.utils.EntryptUtils;
import com.google.common.collect.Maps;

/**
 *@Description: 
 *
 *@author: zhangsq
 *@date:   2017年4月19日 下午6:24:12
 * 
 */
@Service
public class TenantsAppServiceImpl extends BaseService implements TenantsAppService {
	
	@Autowired
	TenantsAppsMapper tenantsAppsMapper;
	@Autowired
	TenantsUsersMapper tenantsUsersDao;
	@Autowired
	SmsService smsService;
	@Autowired
	TenantsBankCardsMapper tenantsBankCardsMapper;
	@Autowired
	BankMapper bankMapper;
	
	@SuppressWarnings("unchecked")
	public JsonResult getTenantsAppInfo(TenantsAppForm tenantsAppForm,Integer pageNumber,
			Integer pageSize) {
		try {
			// 获取总条数
			Map<Object, Object> map = Maps.newHashMap();
			map.put("tenantName", tenantsAppForm.getTenantName());
			map.put("registerPhone", tenantsAppForm.getRegisterPhone());
			map.put("recommendId", tenantsAppForm.getRecommendId());
			map.put("memberType", tenantsAppForm.getMemberType());
			map.put("tenantStatus", tenantsAppForm.getTenantStatus());
			map.put("applyTimeLeft", tenantsAppForm.getApplyTimeLeft());
			map.put("applyTimeRight", tenantsAppForm.getApplyTimeRight());
			map.put("dueDateLeft", tenantsAppForm.getDueDateLeft());
			map.put("dueDateRight", tenantsAppForm.getDueDateRight());
			map.put("provice", tenantsAppForm.getProvice());
			map.put("city", tenantsAppForm.getCity());
			Integer totalCount = tenantsAppsMapper.getTenantsAppInfoCount(map);
			// 分页实体
			Page<Map> page=new Page<Map>();
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
	        map.put("pageNumber", page.getOffset());
	        map.put("pageSize", page.getRowNum());
			if (totalCount > 0) {
				
				@SuppressWarnings("rawtypes")
				List<Map>  list = tenantsAppsMapper.getTenantsAppInfoList(map);
				String str = "";
				for (Map<Object, Object> map2 : list) {
					//省code转中文
					str = (String)map2.get("privince");
            		if(!StringUtils.isBlank(str)){
            			map2.put("privince", DictionarysCacheUtils.getProviceName(str));
            		}
            		//市code转中文
            		str = (String)map2.get("city");
            		if(!StringUtils.isBlank(str)){
            			map2.put("city", DictionarysCacheUtils.getCityName(str));
            		}
            		//区code转中文
            		str = (String) map2.get("area");
            		if (!StringUtils.isBlank(str)) {
						map2.put("area", DictionarysCacheUtils.getCountyName(str));
					}else {
						map2.put("area", "");
					}
            		//门店状态转中文
            		str = (String) map2.get("tenantStatus");
            		if (!StringUtils.isBlank(str)) {
						map2.put("tenantStatus", DictionarysCacheUtils.getStoreStatus(str));
					}else {
						map2.put("tenantStatus", "");
					}
            		
            		//会员类型转中文
            		str = (String) map2.get("memberType");
            		if (!StringUtils.isBlank(str)) {
						map2.put("memberType", DictionarysCacheUtils.getMemeberType(str));
					}else {
						map2.put("memberType", "");
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
	//	已入住门店详细
	public JsonResult getTenantsAppById(Integer tenantId) {
		try {
		//TenantsDetailJsonData tenantsDetailJsonData = new TenantsDetailJsonData();
		List<Map<String, Object>>  list =tenantsAppsMapper.selectTenantsInfoById(tenantId);
		System.out.println(list);
    	for(Map<String, Object> map : list){
    		
    		if(map.get("applyTime") != null){
    			map.put("applyTime", DateUtils.formatDateTime((Date)map.get("applyTime")));
    		}else {
				map.put("applyTime", "");
			}
    		if(map.get("privince") != null){
				map.put("privince",map.get("privince")+","+DictionarysCacheUtils.getProviceName((String)map.get("privince")));
			}else {
				map.put("privince", "");
			}
    		if(map.get("city") != null){
				map.put("city",map.get("city")+","+DictionarysCacheUtils.getCityName((String)map.get("city")));
			}else {
				map.put("city", "");
			}
    		if(null !=map.get("area") && !"".equals(map.get("area"))){
				map.put("area",map.get("area")+","+DictionarysCacheUtils.getCountyName((String)map.get("area")));
			}else {
				map.put("area", "");
			}
    		if(map.get("memberType") != null){
				map.put("memberType",DictionarysCacheUtils.getMemeberType((String)map.get("memberType")));
			}else {
				map.put("memberType", "");
			}
    		
    		if(map.get("tenantStatus") != null){
				map.put("tenantStatus",map.get("tenantStatus")+","+DictionarysCacheUtils.getStoreStatus((String)map.get("tenantStatus")));
			}else {
				map.put("tenantStatus", "");
			}
    		
    		if (map.get("domain")!= null) {
				map.put("domain",Global.getConfig("hostUrl")+"/"+map.get("domain"));
			}else {
				map.put("domain", "");
			}
    		
    		
    	}
		
		return JsonResult.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
			return JsonResult.failure(ResultCode.DATA_ERROR);
		}
     }
	//已入住门店修改
	public JsonResult updateTenantsById(Integer tenantId,TenantsManagementForm tenantsManagementForm) {
		try {
			//修改门店信息
			TenantsApps tenantsApps = new TenantsApps();
			
			TenantsApps  tenants  =  tenantsAppsMapper.selectByPrimaryKey(tenantId);
			
			//检查二级域名
			
			tenantsApps.setTenantId(tenantId);
			tenantsApps.setWebsiteName(tenantsManagementForm.getTenantName());
			tenantsApps.setContactAddress(tenantsManagementForm.getContactAddress());
			tenantsApps.setRegisterPhone(tenantsManagementForm.getRegisterPhone());
			tenantsApps.setContactName(tenantsManagementForm.getContactName());
			tenantsApps.setContactPhone(tenantsManagementForm.getContactPhone());
			tenantsApps.setTenantsPhone(tenantsManagementForm.getTenantsPhone());
			tenantsApps.setDomain(tenants.getDomain());
			tenantsApps.setDueDate(tenants.getDueDate());
			tenantsApps.setIsUsable("1");
			tenantsApps.setPrivince(tenantsManagementForm.getServiceProvice());
			tenantsApps.setCity(tenantsManagementForm.getServiceCity());
			tenantsApps.setArea(tenantsManagementForm.getServiceCounty());
			tenantsApps.setTenantStatus(tenants.getTenantStatus());
			tenantsApps.setSalesPerson(tenantsManagementForm.getSalesPerson());
			tenantsApps.setRemarks(tenants.getRemarks());
			tenantsApps.setApplyTime(tenants.getApplyTime());
			tenantsApps.setCompanyName(tenants.getCompanyName());
			tenantsApps.setLicenceNo(tenants.getLicenceNo());
			tenantsApps.setLicenceImg(tenantsApps.getLicenceImg());
			tenantsApps.setAgentName(tenants.getAgentName());
			tenantsApps.setIdNumber(tenants.getIdNumber());
			tenantsApps.setIdcardFrontImg(tenants.getIdcardFrontImg());
			tenantsApps.setIdcardBackImg(tenants.getIdcardBackImg());
			tenantsApps.setMemberType(tenants.getMemberType());
			tenantsApps.setRecommendId(tenantsManagementForm.getRecommendId());
			tenantsApps.setAddTime(tenants.getAddTime());
			tenantsApps.setModifyTime(new Date());
			tenantsApps.setWebsiteLogo(tenantsManagementForm.getFileLogo());
			tenantsAppsMapper.updateByPrimaryKeySelective(tenantsApps);
			//更新账户绑卡表
			TenantsBankCards  tenantsBankCards  =  tenantsBankCardsMapper.selectByPrimaryKey(tenantId);
			TenantsBankCardsEntity tenantsBankCardsEntity = new TenantsBankCardsEntity();
			tenantsBankCardsEntity.setTenantId(tenantId);
			tenantsBankCardsEntity.setCardNo(tenantsManagementForm.getCardNo());
			tenantsBankCardsEntity.setAccountType(tenantsManagementForm.getAccountType());
			tenantsBankCardsEntity.setBankName(tenantsManagementForm.getBankName());
			tenantsBankCardsEntity.setBankCode(tenantsManagementForm.getBankCode());
			tenantsBankCardsEntity.setBankSecName(tenantsManagementForm.getBankSecName());
			tenantsBankCardsEntity.setRemarks(tenantsBankCards.getRemarks());
			tenantsBankCardsEntity.setIsUsable("1");
			tenantsBankCardsEntity.setAddTime(tenantsBankCards.getAddTime());
			tenantsBankCardsEntity.setAddAccount(tenantsBankCards.getAddAccount());
			tenantsBankCardsEntity.setModifyTime(new Date());
			tenantsBankCardsMapper.updateByPrimaryKeySelective(tenantsBankCardsEntity);

		return JsonResult.success(null);
	} catch (Exception e) {
			e.printStackTrace();
			Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
			return JsonResult.failure(ResultCode.DATA_ERROR);
		}
     }
	
	  //logo图片保存
		@Override
		@Transactional
		public Map<String,String> uploadLogoImg(MultipartFile fileLogo){
			String uploadFileName=fileLogo.getOriginalFilename();
			String serverPath = Global.getConfig("website_base_path");
			String basePath = Constants.TEN_ANTS_IMGAE_BASE_PATH;
			String logoPath = Constants.LO_GO_IMGAE_BASE_PATH;
			String fileSavePath = serverPath + "/" + basePath + "/" + logoPath + "/";
			String imgName = "/" + basePath + "/" + logoPath + "/" ;
			if (!fileLogo.isEmpty()) {
				try {				
					// 获取文件后缀
					String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
					checkFile(suffix);
		
					if (suffix == null || "".equals(suffix)) {
						throw new ServiceException("未知文件类型，上传失败！");
					}
					// 校验文件类型
					if (!verifyImageType(suffix)) {
						throw new ServiceException("文件类型不符合要求，上传失败！");
					}
					// 重命名上传后的文件名
					String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + suffix;
					
					File targetFile = new File(fileSavePath, fileName); 
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        }  			  
			        //保存  
			        try {  
			        	fileLogo.transferTo(targetFile);  
			        } catch (Exception e) {  
			            e.printStackTrace();  
			        }
			        Map<String,String> map = new HashMap<String,String>();
			        map.put("img", fileSavePath + fileName );
			        map.put("imgUrl", imgName + fileName );
			        return map;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	
		
		//检验上传图片
		private void checkFile(String suffix) {
			
			//校验文件后缀
			if(StringUtils.isBlank(suffix)){
				throw new ServiceException("未知文件类型，上传失败！");
			}
			//校验文件类型
			if(!verifyImageType(suffix)){
				throw new ServiceException("文件类型不符合要求，上传失败！");
			}
		}
	/**
	 * 校验文件类型 过滤合法的文件类型
	 * 
	 * @param suffix
	 * @return
	 */
	private boolean verifyImageType(String suffix) {
		suffix = suffix.substring(1);
		String allowSuffixs = "gif,jpg,jpeg,bmp,png,ico";
		if (allowSuffixs.indexOf(suffix) == -1) {
			return false;
		}
		return true;
	}
	
	public boolean deletePhoto(String path) {
		//删除图片
		String serverPath = Global.getConfig("website_base_path");//服务器路径 
		String imgPath = serverPath + File.separator + path;
		File file = new File(imgPath);
		boolean flag = file.delete();
		return flag;
	}
	
	/*//二级域名是否重复
		@Override
		public JsonResult tenantsCheck(String domain){
			try{
			List<TenantsApps> tenantsApps=tenantsAppsMapper.selectByDomain(domain);
			System.out.println(tenantsApps.size());
				if (tenantsApps.size()>0) {
					return JsonResult.failure(ResultCode.Tenants.TENANTS_DOMAIN_IS_EXIST);
				}
			return JsonResult.success(ResultCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
			return JsonResult.failure(ResultCode.DATA_ERROR);
		}
		}*/
	//根据门店ID修改密码
	@Override
	public JsonResult resetPassword(Integer tenantId, String loginAccount ,String newpassword) {
		try{
			
		TenantsUsers tenantsUsers=tenantsUsersDao.getByLoginAccount(loginAccount);
			
		JsonResult jsonResult = JsonResult.success(null);
		TenantsUsersEntity tenantsUsersEntity = new TenantsUsersEntity();
		tenantsUsersEntity.setTenantId(tenantsUsers.getTenantId());
		tenantsUsersEntity.setId(tenantsUsers.getId());
		tenantsUsersEntity.setLoginAccount(tenantsUsers.getLoginAccount());
		tenantsUsersEntity.setPassword(EntryptUtils.entryptUserPassword(newpassword, tenantsUsers.getTelephone()));
		tenantsUsersEntity.setModifyTime(new Date());
		tenantsUsersDao.update(tenantsUsersEntity);
		
		TenantsApps  tenants  =  tenantsAppsMapper.selectByPrimaryKey(tenantId);
		//重置密码成功发送短信
		smsService.sendTenantResetPWD(tenants.getContactPhone(), tenants.getWebsiteName(), newpassword);
			return jsonResult;
	
		}catch (Exception e) {
			Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
			return JsonResult.failure(ResultCode.ERROR);
		}
		
	}
	
		//冻结解冻
		@Override
		public JsonResult isblock(Integer tenantId, String tenantStatusCode){
			try{
				if (tenantId==null) {
					return JsonResult.failure(ResultCode.DATA_IS_NULL);
				}
				
			JsonResult jsonResult = JsonResult.success(null);
			TenantsApps tenantsApps = new TenantsApps();
			TenantsApps  tenants  =  tenantsAppsMapper.selectByPrimaryKey(tenantId);
			//正常或者已过期门店变为冻结门店
			if (tenantStatusCode.equals("01")||tenantStatusCode.equals("03")) {
				tenantsApps.setTenantId(tenantId);
				tenantsApps.setWebsiteName(tenants.getWebsiteName());
				tenantsApps.setContactName(tenants.getContactName());
				tenantsApps.setContactPhone(tenants.getContactPhone());
				tenantsApps.setDomain(tenants.getDomain());
				tenantsApps.setTenantStatus("02");
				tenantsApps.setModifyTime(new Date());
				tenantsApps.setIsUsable("0");//冻结不可用
				tenantsAppsMapper.updateByPrimaryKeySelective(tenantsApps);
			}
			//冻结门店变为正常门店
			if (tenantStatusCode.equals("02")) {
				tenantsApps.setTenantId(tenantId);
				tenantsApps.setWebsiteName(tenants.getWebsiteName());
				tenantsApps.setContactName(tenants.getContactName());
				tenantsApps.setContactPhone(tenants.getContactPhone());
				tenantsApps.setDomain(tenants.getDomain());
				tenantsApps.setTenantStatus("01");
				tenantsApps.setModifyTime(new Date());
				tenantsApps.setIsUsable("1");//可用
				tenantsAppsMapper.updateByPrimaryKeySelective(tenantsApps);
			}
			
				return jsonResult;
		
			}catch (Exception e) {
				Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
				return JsonResult.failure(ResultCode.ERROR);
			}
			
		}
		
		
				//银行列表
				@Override
				public JsonResult getBankCode(){
					try{
						List<Map<Object, String>> map = bankMapper.selectBankCode();
				
					
						return JsonResult.success(map);
				
					}catch (Exception e) {
						Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
						return JsonResult.failure(ResultCode.ERROR);
					}
					
				}
}