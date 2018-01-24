package com.fbee.modules.mybatis.entity;

import com.fbee.modules.mybatis.model.TenantsStaffCertsInfo;

/** 
* @ClassName: TenantsStaffCertsInfoEntity 
* @Description: TODO
* @author 贺章鹏
* @date 2016年12月30日 下午3:11:29 
*  
*/
public class TenantsStaffCertsInfoEntity extends TenantsStaffCertsInfo{

	private static final long serialVersionUID = 1L;
	
	private String certifiedStatus;// 审核状态
	
	private String certType;// 证书名称
	
	private String level;//等级
	
	private String certNo;// 证书编号
	
	private String addTimeLeft;// 提交时间-左
	
	private String addTimeRight;// 提交时间-右
	
	private Integer id;//证件id
	
	private Integer staffId;//员工id
	
	private Integer tenantId;//员工id
	
	private int pageNumber;// 当前页数
	
	private int pageSize;// 每页显示几条


	public String getCertifiedStatus() {
		return certifiedStatus;
	}

	public void setCertifiedStatus(String certifiedStatus) {
		this.certifiedStatus = certifiedStatus;
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

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
