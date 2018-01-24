package com.fbee.modules.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fbee.modules.core.Log;
import com.fbee.modules.core.config.Global;
import com.fbee.modules.form.CertCheckForm;

public class ZSCXQuery {
	
	//获取验证码URL
	private static String verfifyCodeUrl = "http://www.12333sh.gov.cn/wsbs/zypxjd/jnjd/jdcx/zscx/Bmblist2.jsp?dt="+Math.random();
	//查询提交URL
	private static String getResultPostUrl = "http://www.12333sh.gov.cn/wsbs/zypxjd/jnjd/jdcx/zscx/zscx_2016.jsp";
	private static Map<Long,Object> map = new HashMap<Long,Object>();
	//下载图形码到页面
	public static void getVerifyingCode(HttpClient client) {
		HttpGet getVerifyCode = new HttpGet(verfifyCodeUrl);
		FileOutputStream fileOutputStream = null;
		HttpResponse response;
		try {
			response = client.execute(getVerifyCode);// 获取验证码
			
			String config = Global.getConfig("website_base_path");
			String purl = "/captcha";
			
			 //验证码写入文件,当前工程的根目录,保存为verifyCode.jped  
			//File file = new File("D:/workspaces/MavenEclipse/fbzkConsole_web/src/main/webapp/image/verifyCode.jpeg");
			
			File file = new File(config+purl+"/verifyCode.jpg");
			 if(!file.exists()){  
				 file.mkdirs();  
		        }  		  
			fileOutputStream = new FileOutputStream(file);
			response.getEntity().writeTo(fileOutputStream);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static  String getImageRequest(){
		HttpGet getLoginPage = new HttpGet(getResultPostUrl);
		HttpClient client = HttpClients.createDefault();
        try {
			client.execute(getLoginPage);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		getVerifyingCode(client);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		String clientKey = df.format(new Date());
		 long clientKeys = Long.parseLong(clientKey);
		map.put(clientKeys, client);
		clearMap();
        return clientKey;
	}
	
	//清map
		public static void clearMap() {
			//获取当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
			String claerKey = df.format(new Date());
			long claerKeys = Long.parseLong(claerKey);
			if(map !=null &&map.size()>0) {
				/*for (long key : map.keySet()) {
					//时间大于1个小时清掉map
					if (claerKeys - key > 120000) {
						map.remove(key);
						rmitemsList.add(map.get(key));
					}
				}*/
				synchronized (map) {
					Iterator<Map.Entry<Long, Object>> t = map.entrySet().iterator();
					List rmlist = new ArrayList();
					while (t.hasNext()) {
						Map.Entry<Long, Object> entry = t.next();
						Long key = entry.getKey();
						if (claerKeys - key > 300) {
							rmlist.add(key);
						}
					}

					for (int i=0;i<rmlist.size();i++){
						map.remove(rmlist.get(i));
						System.out.println(rmlist.get(i)+"======clearMap============");
					}
				}
			}
			 System.out.println(map);
		}
	
	//总控平台证书查询真伪，模拟请求返回html
	public  String getHTMLResult(CertCheckForm certCheckForm, Object clientkey) throws Exception {
        //设定post参数
		if(null==clientkey||"".equals(clientkey)){
			return null;
		}
       HttpClient client = (HttpClient) map.get(Long.valueOf(clientkey.toString()));
        Log.info("++++++++++++"+client);
        List<NameValuePair> postData = new ArrayList<NameValuePair>();
        postData.add(new BasicNameValuePair("idcard", certCheckForm.getQtzjhm()));//身份证号
        postData.add(new BasicNameValuePair("qtzjhm", certCheckForm.getIdcard()));//其他证件号
        postData.add(new BasicNameValuePair("zshm", certCheckForm.getZshm()));//职业资格证号码
        postData.add(new BasicNameValuePair("action","query"));//查询
		postData.add(new BasicNameValuePair("sj_mima1", certCheckForm.getSj_mima1()));//入校验码
          
        HttpPost post = new HttpPost(getResultPostUrl);
        post.setEntity(new UrlEncodedFormEntity(postData));
        HttpResponse response = null;  
        String rawHtml;
        response = client.execute(post);
        rawHtml = EntityUtils.toString(response.getEntity(), "utf-8");  
        //System.out.println(rawHtml);
        map.remove(Long.valueOf(clientkey.toString()));
        Log.info(map);
        return rawHtml; 
	}
	
	
	
}