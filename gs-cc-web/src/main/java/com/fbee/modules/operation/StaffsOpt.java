package com.fbee.modules.operation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fbee.modules.bean.Constants;
import com.fbee.modules.bean.Status;
import com.fbee.modules.form.StaffBankForm;
import com.fbee.modules.form.StaffBaseInfoForm;
import com.fbee.modules.form.StaffBrushCardForm;
import com.fbee.modules.form.StaffCertForm;
import com.fbee.modules.form.StaffJobForm;
import com.fbee.modules.form.StaffPolicyForm;
import com.fbee.modules.form.extend.SelfFeatureForm;
import com.fbee.modules.form.extend.StaffServiceItemform;
import com.fbee.modules.jsonData.extend.StaffBankInfoJson;
import com.fbee.modules.jsonData.extend.StaffBaseInfoJson;
import com.fbee.modules.jsonData.extend.StaffFinanceInfoJson;
import com.fbee.modules.jsonData.extend.StaffJobInfoJson;
import com.fbee.modules.jsonData.extend.StaffMediaPictureJson;
import com.fbee.modules.jsonData.extend.StaffMediaVideoJson;
import com.fbee.modules.jsonData.extend.StaffPolicyInfoJson;
import com.fbee.modules.jsonData.extend.StaffServiceInfoJson;
import com.fbee.modules.jsonData.extend.StaffServiceItemJson;
import com.fbee.modules.jsonData.extend.StaffWorkInfoJson;
import com.fbee.modules.mybatis.entity.OrderCustomersInfoEntity;
import com.fbee.modules.mybatis.entity.OrdersEntity;
import com.fbee.modules.mybatis.entity.TenantsFinanceRecordsEntity;
import com.fbee.modules.mybatis.entity.TenantsStaffBankEntity;
import com.fbee.modules.mybatis.entity.TenantsStaffCertsInfoEntity;
import com.fbee.modules.mybatis.entity.TenantsStaffJobInfoEntity;
import com.fbee.modules.mybatis.entity.TenantsStaffPolicyEntity;
import com.fbee.modules.mybatis.entity.TenantsStaffSerItemsEntity;
import com.fbee.modules.mybatis.entity.TenantsStaffsFeaturesEntity;
import com.fbee.modules.mybatis.entity.TenantsStaffsInfoEntity;
import com.fbee.modules.mybatis.entity.TenantsStaffsMediaEntity;
import com.fbee.modules.utils.DictionarysCacheUtils;
import com.fbee.modules.utils.IdCardInfoUtils;
import com.google.common.collect.Maps;

/** 
* @ClassName: StaffsOpt 
* @Description: 员工（阿姨）管理操作
* @author 贺章鹏
* @date 2017年1月3日 上午11:00:27 
*  
*/
public class StaffsOpt {
	

	/**
	 * 构建刷身份证接口保存信息
	 * @param obj
	 * @param staffBrushCardForm
	 */
	public static TenantsStaffsInfoEntity buildAddBrushCardInfo(TenantsStaffsInfoEntity obj, StaffBrushCardForm form) {
		IdCardInfoUtils  idCard=new IdCardInfoUtils(form.getCertNo());
		obj.setStaffName(form.getStaffName());
		obj.setAge(idCard.getAge());
		obj.setCertNo(form.getCertNo());
		obj.setNation(form.getNation());
		obj.setZodiac(idCard.getZodiac());
		obj.setConstellation(idCard.getConstellation());
		obj.setSex(idCard.getGender());
		obj.setNativePlace(form.getNativePlace());
		obj.setHouseAddress(form.getHouseAddress());
		obj.setCertImage(form.getCertImage());
		obj.setHeadImage(form.getCertImage());
		obj.setAddTime(new Date());
		return obj;
	}
	
	/**
	 * 构建新增阿姨基础信息实体
	 * @param obj
	 * @return
	 */
	public static TenantsStaffsInfoEntity buildAddBaseInfo(TenantsStaffsInfoEntity obj,StaffBaseInfoForm form){
		obj.setStaffId(form.getStaffId());
		obj.setEducarion(form.getEducation());
		obj.setSpecialty(form.getSpecialty());
		obj.setBloodType(form.getBloodType());
		obj.setMaritalStatus(form.getMaritalStatus());
		obj.setFertilitySituation(form.getFertilitySituation());
		obj.setMobile(form.getMobile());
		obj.setContactPhone(form.getContactPhone());
		obj.setExpectedSalary(form.getExpectedSalary());
		obj.setLiveAddress(form.getLiveAddress());
		obj.setWorkStatus(form.getWorkStatus());
		obj.setHeight(form.getHeight());
		obj.setWeight(form.getWeight());
		obj.setOnOffShelf(form.getOnOffShelf());
		return obj;
	}

	/**
	 * 构建阿姨银行卡新增修改实体
	 * @param obj
	 * @param staffBankForm
	 */
	public static TenantsStaffBankEntity buildBankInfo(TenantsStaffBankEntity obj, StaffBankForm staffBankForm) {
		obj.setBankCode(staffBankForm.getBankCode());
		obj.setStaffId(staffBankForm.getStaffId());
		obj.setCardNo(staffBankForm.getCardNo());
		return obj;
	}

	/**
	 *  构建阿姨保单新增修改实体
	 * @param obj
	 * @param staffPolicyForm
	 */
	public static TenantsStaffPolicyEntity buildPolicyInfo(TenantsStaffPolicyEntity obj, StaffPolicyForm staffPolicyForm) {
		obj.setPolicyAgency(staffPolicyForm.getPolicyAgency());
		obj.setPolicyAmount(staffPolicyForm.getPolicyAmount());
		obj.setPolicyName(staffPolicyForm.getPolicyName());
		obj.setPolicyNo(staffPolicyForm.getPolicyNo());
		obj.setStaffId(staffPolicyForm.getStaffId());
		return obj;
	}

	/**
	 * 构建阿姨求职基本新增修改实体
	 * @param staffJob
	 * @param staffJobForm
	 */
	public static TenantsStaffJobInfoEntity buildStaffJobInfo(TenantsStaffJobInfoEntity staffJob, StaffJobForm staffJobForm) {
		staffJob.setManageWay(staffJobForm.getManageWay());
		staffJob.setStaffId(staffJobForm.getStaffId());
		staffJob.setSelfEvaluation(staffJobForm.getSelfEvaluation());
		staffJob.setTeacherEvaluation(staffJobForm.getTeacherEvaluation());
		staffJob.setWorkExperience(staffJobForm.getWorkExperience());
		staffJob.setServiceProvice(staffJobForm.getServiceProvice());
		staffJob.setServiceCity(staffJobForm.getServiceCity());
		staffJob.setServiceCounty(staffJobForm.getServiceCounty());
		staffJob.setLanguageFeature(staffJobForm.getLanguageFeature());
		staffJob.setCharacerFeature(staffJobForm.getCharacerFeature());
		staffJob.setCookingFeature(staffJobForm.getCookingFeature());
		staffJob.setOther(staffJobForm.getOther());
		staffJob.setPetFeeding(staffJobForm.getPetFeeding());
		staffJob.setElderlySupport(staffJobForm.getElderlySupport());
		return staffJob;
	}

	/**
	 * 构建阿姨求职-个人特点新增修改实体
	 * @param featuresObj
	 * @param selfFeature
	 */
	public static TenantsStaffsFeaturesEntity buildStaffFeaturesInfo(TenantsStaffsFeaturesEntity featuresObj, SelfFeatureForm selfFeature) {
		featuresObj.setFeatureCode(selfFeature.getFeatureKey());
		featuresObj.setFeatureValue(selfFeature.getFeatureValue());
		return featuresObj;
	}

	/**
	 * 构建阿姨求职-服务工种特点新增修改实体
	 * @param staffsFeaturesEntity
	 * @param staffServiceItemBean
	 */
	public static TenantsStaffSerItemsEntity buildStaffServiceItemInfo(TenantsStaffSerItemsEntity staffSerItemsObj,
			StaffServiceItemform staffServiceItemBean) {
		staffSerItemsObj.setServiceItemCode(staffServiceItemBean.getServiceItemCode());
		staffSerItemsObj.setSkills(staffServiceItemBean.getSkills());
		staffSerItemsObj.setExperience(staffServiceItemBean.getExperience());
		staffSerItemsObj.setPrice(staffServiceItemBean.getPrice());
		return staffSerItemsObj;
	}

	/**
	 * 构建阿姨查询参数
	 * @param tenantId 
	 * @param staffQueryForm
	 * @return
	 */
	public static Map<Object, Object> buildQueryMap(Integer tenantId) {
		Map<Object, Object> params=Maps.newHashMap();
		params.put("tenantId", tenantId);
		return params;
	}
	
	/**
	 * 构建返回阿姨详情jsonData中的阿姨基本信息json
	 * @param baseInfoJson
	 * @param staffsInfo
	 * @return
	 */
	public static StaffBaseInfoJson buildStaffDetaillBaseInfo(StaffBaseInfoJson baseInfoJson,
			TenantsStaffsInfoEntity staffsInfo) {
		baseInfoJson.setStaffId(staffsInfo.getStaffId());
		baseInfoJson.setAge(staffsInfo.getAge());
		baseInfoJson.setCertNo(staffsInfo.getCertNo());
		baseInfoJson.setStaffName(staffsInfo.getStaffName());
		baseInfoJson.setNation(staffsInfo.getNation());
		baseInfoJson.setZodiac(staffsInfo.getZodiac());
		baseInfoJson.setSex(staffsInfo.getSex());
		baseInfoJson.setNativePlace(staffsInfo.getNativePlace());
		baseInfoJson.setHouseAddress(staffsInfo.getHouseAddress());
		baseInfoJson.setHeadImage(staffsInfo.getHeadImage());
		baseInfoJson.setConstellation(staffsInfo.getConstellation());
		baseInfoJson.setEducation(staffsInfo.getEducarion());
		baseInfoJson.setSpecialty(staffsInfo.getSpecialty());
		baseInfoJson.setMaritalStatus(staffsInfo.getMaritalStatus());
		baseInfoJson.setFertilitySituation(staffsInfo.getFertilitySituation());
		baseInfoJson.setBloodType(staffsInfo.getBloodType());
		baseInfoJson.setMobile(staffsInfo.getMobile());
		baseInfoJson.setContactPhone(staffsInfo.getContactPhone());
		baseInfoJson.setLiveAddress(staffsInfo.getLiveAddress());
		baseInfoJson.setWorkStatus(staffsInfo.getWorkStatus());
		baseInfoJson.setExpectedSalary(staffsInfo.getExpectedSalary());
		return baseInfoJson;
	}
	
	/**
	 * 构建返回阿姨详情jsonData中的阿姨银行信息json
	 * @param bankJson
	 * @param bankInfo
	 * @return
	 */
	public static StaffBankInfoJson buildStaffDetaillBankInfo(StaffBankInfoJson bankJson,
			TenantsStaffBankEntity bankInfo) {
		bankJson.setBankName(bankInfo.getBankCode());
		bankJson.setCardNo(bankInfo.getCardNo());
		return bankJson;
	}

	/**
	 * 构建返回阿姨详情jsonData中的阿姨保单信息json
	 * @param policyListJson
	 * @param policyList
	 */
	public static List<StaffPolicyInfoJson> buildStaffDetaillPolicyList(List<StaffPolicyInfoJson> policyListJson,
			List<TenantsStaffPolicyEntity> policyList) {
		StaffPolicyInfoJson staffPolicyInfoJson=null;
		for(TenantsStaffPolicyEntity policyEntity:policyList){
			staffPolicyInfoJson=new StaffPolicyInfoJson();
			staffPolicyInfoJson.setPolicyNo(policyEntity.getPolicyNo());
			staffPolicyInfoJson.setPolicyName(policyEntity.getPolicyName());
			staffPolicyInfoJson.setAmount(policyEntity.getPolicyAmount());
			policyListJson.add(staffPolicyInfoJson);
		}
		return policyListJson;
	}
	
	/**
	 * 构建返回阿姨详情jsonData中的阿姨媒体video信息json
	 * @param videoMedia
	 * @param media
	 * @return
	 */
	public static StaffMediaVideoJson buildStaffDetaillVideo(StaffMediaVideoJson videoMedia,
			TenantsStaffsMediaEntity media) {
		videoMedia.setMediaId(media.getId());
		videoMedia.setPath(media.getPath());
		videoMedia.setStaffId(media.getStaffId());
		return videoMedia;
	}

	/**
	 * 构建返回阿姨详情jsonData中的阿姨媒体图片信息json
	 * @param imageMedia
	 * @param media
	 */
	public static List<StaffMediaPictureJson> buildStaffDetaillPicture(List<StaffMediaPictureJson> imageMedia, TenantsStaffsMediaEntity media) {
		StaffMediaPictureJson picture=new StaffMediaPictureJson();
		picture.setIdDefault(media.getIsDefault());
		picture.setMediaId(media.getId());
		picture.setPath(media.getPath());
		picture.setStaffId(media.getStaffId());
		imageMedia.add(picture);
		return imageMedia;
	}

	/**
	 * 构建返回阿姨详情jsonData中的阿姨证书信息json
	 * @param serviceList
	 * @param cert
	 */
	public static List<StaffServiceInfoJson> buildStaffDetaillCerts(List<StaffServiceInfoJson> serviceList,
			TenantsStaffCertsInfoEntity cert) {
		StaffServiceInfoJson serviceInfo=new StaffServiceInfoJson();
		serviceInfo.setId(cert.getId());
		serviceInfo.setCertifiedStatus(cert.getCertifiedStatus());
		serviceInfo.setCertName(cert.getCertType());
		serviceList.add(serviceInfo);
		return serviceList;
	}

	/**
	 * 构建返回阿姨详情jsonData中的阿姨财务信息json
	 * @param financeJson
	 * @param financeRecord
	 */
	public static List<StaffFinanceInfoJson> buildStaffDetaillFinance(List<StaffFinanceInfoJson> financeList,
			TenantsFinanceRecordsEntity financeRecord) {
		StaffFinanceInfoJson staffFinance=new StaffFinanceInfoJson();
		staffFinance.setInOutNo(financeRecord.getInOutNo());
		staffFinance.setOperator(financeRecord.getAddAccount());
		staffFinance.setAmount(financeRecord.getInOutAmount());
		staffFinance.setRemark(financeRecord.getRemarks());
		staffFinance.setAddTime(financeRecord.getAddTime());
		financeList.add(staffFinance);
		return financeList;
	}

	/**
	 * 构建返回阿姨详情jsonData中的阿姨派工信息json
	 * @param workList
	 * @param orderEntity
	 * @param orderCustomers
	 */
	public static List<StaffWorkInfoJson> buildStaffDetaillOrders(List<StaffWorkInfoJson> workList, OrdersEntity orderEntity,
			OrderCustomersInfoEntity orderCustomers) {
		StaffWorkInfoJson staffWorkInfoJson=new StaffWorkInfoJson();
		staffWorkInfoJson.setBeginWorkTime(orderCustomers.getServiceStart());
		staffWorkInfoJson.setEndWorkTime(orderCustomers.getServiceEnd());
		staffWorkInfoJson.setOrderStatus(orderEntity.getOrderStatus());
		staffWorkInfoJson.setServiceItem(orderCustomers.getServiceItemCode());
		staffWorkInfoJson.setSalary(orderEntity.getAmount());
		staffWorkInfoJson.setCustomer(orderCustomers.getMemberName());
		workList.add(staffWorkInfoJson);
		return workList;
	}

	/**
	 * 构建返回阿姨详情jsonData中的阿姨求职信息json--基本信息
	 * @param jobInfo
	 * @param tenantsStaffJob
	 */
	public static StaffJobInfoJson buildStaffDetaillJobsInfo(StaffJobInfoJson jobInfo, TenantsStaffJobInfoEntity tenantsStaffJob) {
		jobInfo.setManageWay(tenantsStaffJob.getManageWay());
		jobInfo.setSelfEvaluation(tenantsStaffJob.getSelfEvaluation());
		jobInfo.setServiceCity(tenantsStaffJob.getServiceCity());
		jobInfo.setServiceCounty(tenantsStaffJob.getServiceCounty());
		jobInfo.setServiceProvice(tenantsStaffJob.getServiceProvice());
		jobInfo.setTeacherEvaluation(tenantsStaffJob.getTeacherEvaluation());
		jobInfo.setWorkExperience(tenantsStaffJob.getWorkExperience());
		jobInfo.setElderlySupport(tenantsStaffJob.getElderlySupport());
		jobInfo.setPetFeeding(tenantsStaffJob.getPetFeeding());
		StringBuffer sb=new StringBuffer();
		sb.append(DictionarysCacheUtils.getFeaturesStr("01", tenantsStaffJob.getLanguageFeature())).append(Constants.COMMA)
		.append(DictionarysCacheUtils.getFeaturesStr("02", tenantsStaffJob.getCookingFeature())).append(Constants.COMMA)
		.append(DictionarysCacheUtils.getFeaturesStr("03", tenantsStaffJob.getCharacerFeature()));
		jobInfo.setFeatureValue(sb.toString());
		return jobInfo;
	}

	/**
	 * 构建返回阿姨详情jsonData中的阿姨求职信息json--基本服务工种
	 * @param serviceItemList
	 * @param serItems
	 */
	public static List<StaffServiceItemJson> buildStaffDetaillJobsSerItems(List<StaffServiceItemJson> serviceItemList,
			TenantsStaffSerItemsEntity serItems) {
		StaffServiceItemJson staffServiceItemJson=new StaffServiceItemJson();
		staffServiceItemJson.setExperience(serItems.getExperience());
		staffServiceItemJson.setPrice(serItems.getPrice());
		staffServiceItemJson.setServiceItemCode(DictionarysCacheUtils.getServiceTypeName(serItems.getServiceItemCode()));
		staffServiceItemJson.setServiceNature(DictionarysCacheUtils.getServiceNatureStr(serItems.getServiceItemCode(),serItems.getServiceNature()));
		staffServiceItemJson.setSkills(DictionarysCacheUtils.getSkillsStr(serItems.getServiceItemCode(), serItems.getSkills()));
		serviceItemList.add(staffServiceItemJson);
		return serviceItemList;
	}

	/**
	 * 构建返回阿姨详情jsonData中的阿姨个人特点信息json--基本服务工种
	 * @param featureList
	 * @param features
	 */
	public static String buildStaffDetaillJobsFeatures(StaffJobInfoJson staffJobInfoJson,
			TenantsStaffsFeaturesEntity features) {
		String s=staffJobInfoJson.getFeatureValue();
		return s;
	}

	/**
	 * @param obj
	 * @param staffCertForm
	 */
	public static TenantsStaffCertsInfoEntity buildCertInfo(TenantsStaffCertsInfoEntity obj, StaffCertForm staffCertForm) {
		obj.setCertNo(staffCertForm.getCertNo());
		obj.setCertType(staffCertForm.getCertType());
		obj.setStaffId(staffCertForm.getStaffId());
		obj.setCertificationBody(staffCertForm.getCertificationBody());
		obj.setCertificationDate(staffCertForm.getCertificationDate());
		obj.setCertExpireDate(staffCertForm.getCertExpireDate());
		obj.setCertifiedStatus(Status.CertifiedStatus.UN_CERTIFIED);
		return obj;
	}	

}
