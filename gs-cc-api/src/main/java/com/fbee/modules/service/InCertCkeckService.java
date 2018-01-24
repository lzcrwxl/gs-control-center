package com.fbee.modules.service;

import com.fbee.modules.form.CertCheckForm;
import com.fbee.modules.jsonData.basic.JsonResult;

public interface InCertCkeckService {
	
	
	
	/**
	 * 证书数据爬取
	 * @param Idcard
	 * @return
	 */
	
	JsonResult checkCertInfo(CertCheckForm certCheckForm ,Object clientkey );
	
	/**
	 * 证书列表查询
	 * @param Id
	 * @return
	 */
	
	JsonResult queryCertInfo(String idcard,String name);
	
}
