package com.fbee.modules.mybatis.model;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fbee.modules.core.utils.DateUtils;

public class ShCert {
   
   private String no;  					//暂时没有信息
	
   private String idcard;				//身份证号
   
   private String name;					//姓名
   
   private String sex;					//性别
   
   private String certification_date; 	//发证日期
   
   private String Identification;		//鉴定职业 工种名称
   
   private String rate;					//鉴定等级
   
   private String tgrade;				//理论成绩
   
   private String skillGrade;			//技能成绩
   
   private String compGrade;			//综合成绩
   
   private String abliyGrade;			//能力成绩
   
   private String result;				//评定结果
   
   private String certNo;				//证书编号
   
	public String getNo() {
		return no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}

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
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getCertification_date() {
		return certification_date;
	}

	public void setCertification_date(String certification_date) {
		this.certification_date = certification_date;
	}

	public String getIdentification() {
		return Identification;
	}
	
	public void setIdentification(String identification) {
		Identification = identification;
	}
	
	public String getRate() {
		return rate;
	}
	
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public String getTgrade() {
		return tgrade;
	}
	
	public void setTgrade(String tgrade) {
		this.tgrade = tgrade;
	}
	
	public String getSkillGrade() {
		return skillGrade;
	}
	
	public void setSkillGrade(String skillGrade) {
		this.skillGrade = skillGrade;
	}
	
	public String getCompGrade() {
		return compGrade;
	}
	
	public void setCompGrade(String compGrade) {
		this.compGrade = compGrade;
	}
	
	public String getAbliyGrade() {
		return abliyGrade;
	}
	public void setAbliyGrade(String abliyGrade) {
		this.abliyGrade = abliyGrade;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getCertNo() {
		return certNo;
	}
	
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
  /* public static void main(String[] args) {
	   List<String> listStr1 = new ArrayList<String>(); 
	   String[]s={"342225197110056241", "丁小梅", "女", "20161122", "母婴护理", "专项职业能力", "-", "-", "68","-" , "合格", "1603000327900756", 
			   "342225197110056241", "丁小梅", "女", "20170210", "育婴员", "初级/五级", "-", "-", "65","-" , "合格", "1603000327900756"};
	   listStr1=Arrays.asList(s);
	   System.out.println(listStr1);
	   List<String> listStr2 = new ArrayList<String>(); 
      
       listStr2.add("idcard");
       listStr2.add("name");
       listStr2.add("sex");
       listStr2.add("certification_date");
       listStr2.add("Identification");
       listStr2.add("rate");
       listStr2.add("tgrade");
       listStr2.add("skillGrade");
       listStr2.add("compGrade");
       listStr2.add("abliyGrade");
       listStr2.add("result");
       listStr2.add("certNo");
       //Map<String,String> map = new  HashMap<String,String>();
       List<Map<String, String>> dataall =new ArrayList<>();
       int count=0;
       
       	//list 换成map
       	for(int n=0;n<listStr1.size()/12;n++){
       	 Map<String,String> map = new  HashMap<String,String>();
       			for (int j=0 ;j<listStr2.size() ;j++) {
       				map.put(listStr2.get(j),listStr1.get(count));
       				count=count+1;
       				
       			}
       			String st= map.get("certification_date");
        		String ss = st+"-";
        		map.put("certification_date", ss.substring(0,4)+"-"+ss.substring(4,6)+"-"+ss.substring(6,8));
       			System.out.println(map.toString());
       			dataall.add(map);
       	}
       	
       System.out.println(dataall.toString());
       
	 
   }*/
	private static Map<Long,Object> map = new HashMap<Long,Object>();
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmsss");//设置日期格式
		  String ss = df.format(new Date());
		  System.out.println(ss);
		  long lon = Long.parseLong(ss);
		  System.out.println(lon);
		  map.put(lon, 111);
		
		  
		  for (long key : map.keySet()) {  
		    if (lon==key) {
				map.remove(key);
			}
		  }  
		System.out.println(map);
	}
	
}
