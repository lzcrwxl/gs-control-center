package com.fbee.modules.form;

import java.io.Serializable;
import java.util.Date;

/** 
* @ClassName: StaffCertForm 
* @Description: 员工证件表单
* @author 贺章鹏
* @date 2017年1月12日 下午12:18:26 
*  
*/
public class StaffCertForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;//证件id
	
	private Integer staffId;//员工id
	
	private String certType;//证书类型
	
	private String certNo;//证书编号
	
	private String certImage;//证书图片
	
	private Date certificationDate;//发证日期
	
	private String certificationBody;//发证机构

	private Date certExpireDate;//到期时间

	//总控平台-证书管理
	private String certifiedStatus;//证书状态   01待审核  02审核通过  03审核不通过
	
	private String addTimeLeft;//提交时间-左
	
	private String addTimeRight;//提交时间-右
	
	public String getCertifiedStatus() {
		return certifiedStatus;
	}

	public void setCertifiedStatus(String certifiedStatus) {
		this.certifiedStatus = certifiedStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCertImage() {
		return certImage;
	}

	public void setCertImage(String certImage) {
		this.certImage = certImage;
	}

	public Date getCertificationDate() {
		return certificationDate;
	}

	public void setCertificationDate(Date certificationDate) {
		this.certificationDate = certificationDate;
	}

	public String getCertificationBody() {
		return certificationBody;
	}

	public void setCertificationBody(String certificationBody) {
		this.certificationBody = certificationBody;
	}

	public Date getCertExpireDate() {
		return certExpireDate;
	}

	public void setCertExpireDate(Date certExpireDate) {
		this.certExpireDate = certExpireDate;
	}

	public String getAddTimeLeft() {
		return addTimeLeft;
	}

	public void setAddTimeLeft(String addTimeLeft) {
		this.addTimeLeft = addTimeLeft;
	}

	public String getAddTimeRight() {
		return addTimeRight;
	}

	public void setAddTimeRight(String addTimeRight) {
		this.addTimeRight = addTimeRight;
	}

	@Override
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder();
		stringBuilder.append("staff cert info,{staffId:").append(staffId)
			.append(",certType:").append(certType).append(",certNo:").append(certNo);
		return stringBuilder.toString();
	}
}
