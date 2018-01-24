package com.fbee.modules.jsonData.extend;

import com.fbee.modules.core.persistence.ModelSerializable;

/** 
* @ClassName: StaffServiceInfoJson 
* @Description: 服务认证
* @author 贺章鹏
* @date 2017年1月5日 下午4:02:51 
*  
*/
public class StaffServiceInfoJson implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;//证书主键
	
	private String certType;//证书类型
	
	private String certName;//证书名称
	
	private String certifiedStatus;//认证状态 
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getCertifiedStatus() {
		return certifiedStatus;
	}

	public void setCertifiedStatus(String certifiedStatus) {
		this.certifiedStatus = certifiedStatus;
	}
}
