package com.fbee.modules.mybatis.dao;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.MembersInfoEntity;
import com.fbee.modules.mybatis.model.MembersInfo;
import com.fbee.modules.mybatis.model.MembersInfoExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface MembersInfoMapper extends CrudDao<MembersInfoEntity> {
	
	
	int countByExample(MembersInfoExample example);

	
	int deleteByExample(MembersInfoExample example);

	
	int deleteByPrimaryKey(Integer id);

	int insert(MembersInfo record);

	int insertSelective(MembersInfo record);

	
	List<MembersInfo> selectByExample(MembersInfoExample example);

	MembersInfo selectByPrimaryKey(Integer id);

	
	int updateByExampleSelective(@Param("record") MembersInfo record, @Param("example") MembersInfoExample example);

	
	int updateByExample(@Param("record") MembersInfo record, @Param("example") MembersInfoExample example);

	
	int updateByPrimaryKeySelective(MembersInfo record);

	
	int updateByPrimaryKey(MembersInfo record);

	/**
	 * 根据号码获取会员信息
	 * 
	 */
	MembersInfo getByMobile(String mobile);
	
	
	Integer insertMember(MembersInfoEntity membersInfoEntity);
	
		//总控平台开始
		/** 
		 * 总控平台-客户管理数量查询
	     * @param map
	     * @return
	     */
	    Integer getzkCustomerQueryCount(Map<String, Object> map);

	    /**
	     * 总控平台-返回用户集合
	     * @param map
	     * @return
	     */
	    List<MembersInfoEntity> getzkCustomerQueryList(Map<String, Object> map);
	    //总控平台结束
	
}