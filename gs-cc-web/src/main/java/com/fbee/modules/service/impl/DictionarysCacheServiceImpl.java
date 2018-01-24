package com.fbee.modules.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fbee.modules.bean.AreaCacheBean;
import com.fbee.modules.bean.CacheKeys;
import com.fbee.modules.bean.DictionarysCacheBean;
import com.fbee.modules.bean.ServiceKillsCacheBean;
import com.fbee.modules.bean.Status;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.utils.StringUtils;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.mybatis.dao.AreasMapper;
import com.fbee.modules.mybatis.dao.DictionarysMapper;
import com.fbee.modules.mybatis.dao.ServiceSkillsMapper;
import com.fbee.modules.mybatis.dao.ServiceSkillsMatchMapper;
import com.fbee.modules.mybatis.entity.AreasEntity;
import com.fbee.modules.mybatis.entity.DictionarysEntity;
import com.fbee.modules.mybatis.entity.ServiceSkillsEntity;
import com.fbee.modules.mybatis.entity.ServiceSkillsMatchEntity;
import com.fbee.modules.redis.utils.Encode;
import com.fbee.modules.service.DictionarysCacheService;
import com.fbee.modules.utils.DictionarysCacheUtils;
import com.fbee.modules.utils.JedisUtils;

/** 
* @ClassName: DictionarysCacheServiceImpl 
* @Description: 字典形参数和常量参数缓存方法
* 主要用户保存字典参数参数和常量参数，和更新缓存
* @author 贺章鹏
* @date 2017年1月17日 下午1:35:23 
* 
*/
@Service
public class DictionarysCacheServiceImpl implements DictionarysCacheService{
	
	@Autowired
	DictionarysMapper dictionarysDao;
	
	@Autowired
	ServiceSkillsMapper serviceSkillsDao;
	
	@Autowired
	AreasMapper areasDao;
	
	@Autowired
	ServiceSkillsMatchMapper serviceSkillsMatchDao;

	@Override
	public JsonResult initCacheData() {
		JedisUtils jedisUtils = JedisUtils.getInstance();
		JedisUtils.Hash hash = jedisUtils.new Hash();
		//一级字典型参数
		List<DictionarysEntity> list= dictionarysDao.getServiceTypeByLevel(Status.Level.FIRST_LEVEL);
		DictionarysCacheBean cacheBean=null;
		for(DictionarysEntity dictionary:list){
			cacheBean=new DictionarysCacheBean();
			cacheBean.setPcode(dictionary.getParentId());
			cacheBean.setName(dictionary.getValue());
			cacheBean.setCode(dictionary.getCode());
			hash.hset(dictionary.getTypeCode(), dictionary.getCode(), Encode.encode(cacheBean));
		}
		Log.info("一级字典型参数--->redis缓存完成...........");
		//个人特点
		List<ServiceSkillsEntity> personFeatures = serviceSkillsDao.getServiceSkills(CacheKeys.PERSON_FEATURES);
		//服务类型
		List<ServiceSkillsEntity> serviceNatures = serviceSkillsDao.getServiceSkills(CacheKeys.SERVICE_NATURES);
		//服务技能
		List<ServiceSkillsEntity> serviceSkills = serviceSkillsDao.getServiceSkills(CacheKeys.SERVICE_SKILLS);
		//区域
		List<AreasEntity>  proviceList = areasDao.getAreaListByLevel(Status.AreaLevel.PROVICE_LEVEL);
		List<AreasEntity>  cityList = areasDao.getAreaListByLevel(Status.AreaLevel.CITY_LEVEL);
		List<AreasEntity>  countyList = areasDao.getAreaListByLevel(Status.AreaLevel.COUNTY_LEVEL);
		
		AreaCacheBean areaCacheBean=null;
		for(AreasEntity area:proviceList){
			areaCacheBean=new AreaCacheBean();
			areaCacheBean.setAreaCode(area.getCode());
			areaCacheBean.setAreaName(area.getAreaName());
			areaCacheBean.setParentCode(area.getParentCode());
			areaCacheBean.setAreaLevel(area.getAreaLevel());
			hash.hset(CacheKeys.PROVICE_CACHE, area.getCode(), Encode.encode(areaCacheBean));
		}
		for(AreasEntity area:cityList){
			areaCacheBean=new AreaCacheBean();
			areaCacheBean.setAreaCode(area.getCode());
			areaCacheBean.setAreaName(area.getAreaName());
			areaCacheBean.setParentCode(area.getParentCode());
			areaCacheBean.setAreaLevel(area.getAreaLevel());
			hash.hset(CacheKeys.CITY_CACHE, area.getCode(), Encode.encode(areaCacheBean));
		}
		for(AreasEntity area:countyList){
			areaCacheBean=new AreaCacheBean();
			areaCacheBean.setAreaCode(area.getCode());
			areaCacheBean.setAreaName(area.getAreaName());
			areaCacheBean.setParentCode(area.getParentCode());
			areaCacheBean.setAreaLevel(area.getAreaLevel());
			hash.hset(CacheKeys.COUNTY_CACHE, area.getCode(), Encode.encode(areaCacheBean));
		}
		
		Log.info("省市区--->redis缓存完成...........");
		ServiceKillsCacheBean serviceCacheBean=null;
		StringBuffer cacheKey=new StringBuffer();
		for(ServiceSkillsEntity skillsEntity:personFeatures){
			cacheKey.setLength(0);
			cacheKey.append(CacheKeys.PERSON_FEATURES).append(skillsEntity.getItemCode())
			.append(skillsEntity.getLevel());
			serviceCacheBean=new ServiceKillsCacheBean();
			serviceCacheBean.setAttrbute(skillsEntity.getAttrbute());
			serviceCacheBean.setItemCode(skillsEntity.getItemCode());
			serviceCacheBean.setSkillsKey(skillsEntity.getSkillsKey());
			serviceCacheBean.setSkillsValue(skillsEntity.getSkillsValue());
			serviceCacheBean.setParSkillsKey(skillsEntity.getParSkillsKey());
			serviceCacheBean.setSort(skillsEntity.getSort());
			hash.hset(cacheKey.toString(), skillsEntity.getSkillsKey(), Encode.encode(serviceCacheBean));
		}
		Log.info("各人特点--->redis缓存完成...........");
		for(ServiceSkillsEntity skillsEntity:serviceNatures){
			cacheKey.setLength(0);
			cacheKey.append(CacheKeys.SERVICE_NATURES).append(skillsEntity.getItemCode())
			.append(skillsEntity.getLevel());
			serviceCacheBean=new ServiceKillsCacheBean();
			serviceCacheBean.setAttrbute(skillsEntity.getAttrbute());
			serviceCacheBean.setItemCode(skillsEntity.getItemCode());
			serviceCacheBean.setSkillsKey(skillsEntity.getSkillsKey());
			serviceCacheBean.setSkillsValue(skillsEntity.getSkillsValue());
			serviceCacheBean.setParSkillsKey(skillsEntity.getParSkillsKey());
			serviceCacheBean.setSort(skillsEntity.getSort());
			hash.hset(cacheKey.toString(), skillsEntity.getSkillsKey(), Encode.encode(serviceCacheBean));
		}
		Log.info("服务类型--->redis缓存完成...........");
		for(ServiceSkillsEntity skillsEntity:serviceSkills){
			cacheKey.setLength(0);
			cacheKey.append(CacheKeys.SERVICE_SKILLS).append(skillsEntity.getItemCode())
			.append(skillsEntity.getLevel());
			serviceCacheBean=new ServiceKillsCacheBean();
			serviceCacheBean.setAttrbute(skillsEntity.getAttrbute());
			serviceCacheBean.setItemCode(skillsEntity.getItemCode());
			serviceCacheBean.setSkillsKey(skillsEntity.getSkillsKey());
			serviceCacheBean.setSkillsValue(skillsEntity.getSkillsValue());
			serviceCacheBean.setParSkillsKey(skillsEntity.getParSkillsKey());
			serviceCacheBean.setSort(skillsEntity.getSort());
			hash.hset(cacheKey.toString(),skillsEntity.getSkillsKey(), Encode.encode(serviceCacheBean));
		}
		Log.info("服务技能--->redis缓存完成...........");
		List<ServiceSkillsMatchEntity> matchSkills=serviceSkillsMatchDao.findAllList(null);
		for(ServiceSkillsMatchEntity bean:matchSkills){
			cacheKey.setLength(0);
			cacheKey.append(bean.getMatchType()).append(bean.getFieldPro())
			.append(bean.getCode()).append(bean.getScoreType());
			hash.hset(CacheKeys.MATCH_SCORES,cacheKey.toString(), Encode.encode(bean.getScore()));
		}
		Log.info("全部--->redis缓存完成...........");
		return JsonResult.success(null);
	}



	@Override
	public JsonResult getDictionaryData(String typeCode) {
		return JsonResult.success(DictionarysCacheUtils.getList(typeCode.toUpperCase()));
	}


	@Override
	public JsonResult getAreaData(String typeCode, String pcode) {
		if(Status.DictionaryTypeCode.CITY_CACHE.equals(typeCode) 
				|| Status.DictionaryTypeCode.COUNTY_CACHE.equals(typeCode)){
			if(StringUtils.isBlank(pcode)){
				return JsonResult.failure(ResultCode.PARAMS_ERROR);
			}
			
		}
		return JsonResult.success(DictionarysCacheUtils.getAreaList(typeCode,pcode));
	}



	@Override
	public JsonResult getSKillsData(String typeCode, String itemCode,String level) {
		StringBuffer cacheKey=new StringBuffer().append(typeCode).append(itemCode).append(level);
		return JsonResult.success(DictionarysCacheUtils.getSkillList(cacheKey.toString()));
	}



	@Override
	public JsonResult clearCacheData() {
		JedisUtils jedisUtils = JedisUtils.getInstance();
		jedisUtils.getJedis().flushDB(); 
		return JsonResult.success(null);
	}
	
}
