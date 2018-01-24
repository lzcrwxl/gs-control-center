package com.fbee.modules.jsonData.json;

import java.util.List;

import com.fbee.modules.jsonData.extend.WebsiteRecommendJson;

/** 
* @ClassName: WebsiteIndexJsonData 
* @Description: 网站管理--首页管理
* @author 贺章鹏
* @date 2017年1月6日 下午2:40:06 
*  
*/
public class WebsiteIndexJsonData {
	
	private String defaultImage;//默认图片
	
	private String bannnerImage;//banner图片
	
	private List<WebsiteRecommendJson> recommendList;//推荐管理

	public String getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	public String getBannnerImage() {
		return bannnerImage;
	}

	public void setBannnerImage(String bannnerImage) {
		this.bannnerImage = bannnerImage;
	}

	public List<WebsiteRecommendJson> getRecommendList() {
		return recommendList;
	}

	public void setRecommendList(List<WebsiteRecommendJson> recommendList) {
		this.recommendList = recommendList;
	}

}
