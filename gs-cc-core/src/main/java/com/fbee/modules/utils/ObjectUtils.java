package com.fbee.modules.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;
import javax.xml.soap.Text;

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
import org.dom4j.Element;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

public class ObjectUtils {
	
	
	/**将map转换成object对象
	 * @param map
	 * @return
	 */
	public static Object mapToObject(Map<?, ?> sourceMap,Object targetObject){
		Gson gson = new Gson();
		String jsonStr = gson.toJson(sourceMap);
		targetObject = gson.fromJson(jsonStr, targetObject.getClass());
		return targetObject;
	}
	
	/**将实体对象转换成Map
	 * @param sourceObject
	 * @param targetMap
	 * @return
	 */
	public static Map<?, ?> objectToMap(Object sourceObject,Map<?, ?> targetMap){
		Gson gson = new Gson();
		String jsonStr = gson.toJson(sourceObject);
		targetMap = gson.fromJson(jsonStr, targetMap.getClass());
		return targetMap;
	}
	
	
}