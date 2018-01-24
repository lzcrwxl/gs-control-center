package com.fbee.modules.jsonData.extend;

import java.math.BigDecimal;
import java.util.Date;

import com.fbee.modules.core.persistence.ModelSerializable;

public class TenantsCustomerJson implements ModelSerializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 总控平台用
	 * 客户管理-客户查询返回结果
	 */
	
	
	
	/**
	 * 客户管理-客户查询返回结果
	 */
	private Integer id;
	/**
     * 表：members_info
     * 字段：NAME
     * 注释：客户姓名
     *
     * @mbggenerated
     */
    private String name;
    
    /**
     * 表：members_info
     * 字段：MOBILE
     * 注释：紧急联系电话
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * 表：members_info
     * 字段：SERVICE_PROVICE
     * 注释：
     *
     * @mbggenerated
     */
    private String provice;

    /**
     * 表：members_info
     * 字段：SERVICE_CITY
     * 注释：
     *
     * @mbggenerated
     */
    private String city;

    /**
     * 表：members_info
     * 字段：SERVICE_COUNTY
     * 注释：
     *
     * @mbggenerated
     */
    private String county;
    private String address;
    private String weixin;
    private String qq;
  

   
    /**
     * 表：members_info
     * 字段：SEX
     * 注释：性别
     *
     * @mbggenerated
     */
    private String sex;

    private String salt;
    
    private String login_password;
    
    private String user_status;
    
    private String open_id;
    
    private String pwd_try_count;
    
    private String is_locked;
    
    private String lock_time;
    
    private String first_wrong_time;
    
    private String is_usable;

    /**
     * 表：tenants_customers_base
     * 字段：add_time
     * 注释：添加时间
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * 表：tenants_customers_base
     * 字段：add_account
     * 注释：添加人
     *
     * @mbggenerated
     */
    private String addAccount;

    /**
     * 表：tenants_customers_base
     * 字段：modify_time
     * 注释：修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 表：tenants_customers_base
     * 字段：modify_account
     * 注释：修改人
     *
     * @mbggenerated
     */
    private String modifyAccount;

  
    /**
     * 表：members_info
     * 字段：register_time
     * 注释：注册时间
     *
     * @mbggenerated
     */
	private  String registerTime;
	
	/**
     * 表：members_info
     * 字段：last_login_time
     * 注释：最后登陆时间
     *
     * @mbggenerated
     */
	private  String lastLoginTime;
    
   

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

   
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
        return sex;
    }
  
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddAccount() {
        return addAccount;
    }

    public void setAddAccount(String addAccount) {
        this.addAccount = addAccount == null ? null : addAccount.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyAccount() {
        return modifyAccount;
    }

    public void setModifyAccount(String modifyAccount) {
        this.modifyAccount = modifyAccount == null ? null : modifyAccount.trim();
    }

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getLogin_password() {
		return login_password;
	}

	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getPwd_try_count() {
		return pwd_try_count;
	}

	public void setPwd_try_count(String pwd_try_count) {
		this.pwd_try_count = pwd_try_count;
	}

	public String getIs_locked() {
		return is_locked;
	}

	public void setIs_locked(String is_locked) {
		this.is_locked = is_locked;
	}

	public String getLock_time() {
		return lock_time;
	}

	public void setLock_time(String lock_time) {
		this.lock_time = lock_time;
	}

	public String getFirst_wrong_time() {
		return first_wrong_time;
	}

	public void setFirst_wrong_time(String first_wrong_time) {
		this.first_wrong_time = first_wrong_time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIs_usable() {
		return is_usable;
	}

	public void setIs_usable(String is_usable) {
		this.is_usable = is_usable;
	}

	

	
}
