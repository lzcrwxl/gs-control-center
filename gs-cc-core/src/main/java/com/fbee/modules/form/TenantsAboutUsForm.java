package com.fbee.modules.form;

import java.util.List;

import com.fbee.modules.core.persistence.ModelSerializable;

/** 
* @ClassName: TenantsJobsForm 
* @Description: 关于我们信息新增修改
* @author 张杰
*/
public class TenantsAboutUsForm implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
    private Integer id; //P
    private Integer tenantId; //租户id
    private List<String> content;//内容
    
    
    public List<String> getContent() {
		return content;
	}
	public void setContent(List<String> content) {
		this.content = content;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTenantId() {
		return tenantId;
	}
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
}
