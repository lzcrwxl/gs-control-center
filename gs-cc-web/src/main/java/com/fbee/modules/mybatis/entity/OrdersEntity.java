package com.fbee.modules.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fbee.modules.mybatis.model.Orders;

public class OrdersEntity extends Orders{
    
	private static final long serialVersionUID = 1L;


    /**
     * 注释：条件查询开始时间
     *
     * @mbggenerated
     */
    private String startTime;
    
    /**
     * 注释：条件查询结束时间
     *
     * @mbggenerated
     */
    private String endTime;
    
    /**
     * 注释：条件查询客户姓名
     *
     * @mbggenerated
     */
    private String memberName;
    
    /**
     * 注释：条件查询手机号
     *
     * @mbggenerated
     */
    private String memberMobile;
    
    /**
     * 页数
     */
    private Integer pageNum;
    
    /**
     * 页面大小
     */
    private Integer pageSize;
    
    private String reserveOrderNo;
    
    private String membermobile;
    
    private String shareOrderNo;//关联订单编号

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getReserveOrderNo() {
		return reserveOrderNo;
	}

	public void setReserveOrderNo(String reserveOrderNo) {
		this.reserveOrderNo = reserveOrderNo;
	}

	public String getMembermobile() {
		return membermobile;
	}

	public void setMembermobile(String membermobile) {
		this.membermobile = membermobile;
	}

	public String getShareOrderNo() {
		return shareOrderNo;
	}

	public void setShareOrderNo(String shareOrderNo) {
		this.shareOrderNo = shareOrderNo;
	}
	
}