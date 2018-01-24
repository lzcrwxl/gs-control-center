package com.fbee.modules.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fbee.modules.bean.Status;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.bean.SmsCode;
import com.fbee.modules.core.sms.SmsSendResult;
import com.fbee.modules.core.sms.SmsTemplates;
import com.fbee.modules.core.utils.StringUtils;
import com.fbee.modules.mybatis.dao.SmsRecordsMapper;
import com.fbee.modules.mybatis.entity.SmsRecordsEntity;
import com.fbee.modules.service.SendSmsService;
import com.fbee.modules.service.SmsService;
import com.fbee.modules.service.basic.BaseService;



@Service
public class SmsServiceImpl extends BaseService implements SmsService {
	
	@Autowired
	SendSmsService sendSms;
	@Autowired
	SmsRecordsMapper smsRecordDao;
	
	
	//门店审核成功发送短信验证码
	@Override
	public  SmsCode sendTenantPass(String contactPhone,String websiteName,String loginAccount,String password) {
		String[] datas = new String[] {websiteName,loginAccount,password};
		String templateId = SmsTemplates.TENANT_PASS_SMS_CODE; // 模板ID
		String content = generateSmsContent(templateId, datas); // 短信内容
		SmsRecordsEntity entity = new SmsRecordsEntity();
		entity.setMobile(contactPhone);
		entity.setContentType(Status.SmsContentType.TENANT_PASS_SMS_CODE);
		entity.setContent(content);

		System.out.println("sendPaySuccess->mobile:" + contactPhone);
		System.out.println("sendPaySuccess->content:" + content);
		// 发送短信
		SmsSendResult sendResult = sendSms.send(contactPhone, templateId, datas);
		if (SmsSendResult.SUCCESS.equals(sendResult.getResultCode())) { // 发送成功
			entity.setResult(Status.SmsResult.SUBMIT_SUCCESS);
			entity.setSmsId(sendResult.getSmsMessageSid());
		} else { // 发送失败
			entity.setResult(Status.SmsResult.SUBMIT_FAILURE);
			entity.setFailedReason(sendResult.getResultMsg());
		}

		try {
			// 保存发送短信记录
			entity.setAddTime(new Date());
			smsRecordDao.insert(entity);
		} catch (Exception e) {
			Log.error("保存发送短信记录异常", e);
		}

		return null;
	}

	//总控平台密码重置   通知类短信
	@Override
	public  SmsCode sendTenantResetPWD(String contactPhone,String tenantsName,String newpassword) {
		String[] datas = new String[] {tenantsName,newpassword};
		String templateId = SmsTemplates.TENANT_RESET_PWD_SMS_CODE; // 模板ID
		String content = generateSmsContent(templateId, datas); // 短信内容
		SmsRecordsEntity entity = new SmsRecordsEntity();
		entity.setMobile(contactPhone);
		entity.setContentType(Status.SmsContentType.TENANT_RESET_PWD_SMS_CODE);
		entity.setContent(content);

		// 发送短信
		SmsSendResult sendResult = sendSms.send(contactPhone, templateId, datas);
		if (SmsSendResult.SUCCESS.equals(sendResult.getResultCode())) { // 发送成功
			entity.setResult(Status.SmsResult.SUBMIT_SUCCESS);
			entity.setSmsId(sendResult.getSmsMessageSid());
		} else { // 发送失败
			entity.setResult(Status.SmsResult.SUBMIT_FAILURE);
			entity.setFailedReason(sendResult.getResultMsg());
		}

		try {
			// 保存发送短信记录
			entity.setAddTime(new Date());
			smsRecordDao.insert(entity);
		} catch (Exception e) {
			Log.error("保存发送短信记录异常", e);
		}

		return null;
	}

	
	/**
	 * 生成短信内容
	 * @param templateId 短信模板ID
	 * @param datas 参数
	 * @return
	 */
	private String generateSmsContent(String templateId, String[] datas) {
		String content = SmsTemplates.getContent(templateId); // 模板内容
		if (StringUtils.isNotBlank(content)) {
			int len = datas.length;
			String key = null;
			String value = null;
			for (int i = 0; i < len; i ++) {
				key = "{" + (i + 1) + "}";
				value = datas[i] == null ? "" : datas[i];
				try {
					content = content.replace(key, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return content;
	}

	
}
