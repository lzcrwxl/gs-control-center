package com.fbee.modules.mybatis.entity;

import com.fbee.modules.mybatis.model.CertsInfo;

/**
 *@Description: 
 *
 *@author: zhangsq
 *@date:   2017年3月27日 下午3:48:52
 * 
 */
public class CertsInfoEntity extends CertsInfo{
	
	private static final long serialVersionUID = 1L;
	
	private String idcard;//身份证号
	
	private String name;//姓名
	
	
	
	

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
