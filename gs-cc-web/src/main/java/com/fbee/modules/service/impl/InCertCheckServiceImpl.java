package com.fbee.modules.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.fbee.modules.bean.ErrorMsg;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.utils.DateUtils;
import com.fbee.modules.core.utils.StringUtils;
import com.fbee.modules.form.CertCheckForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.mybatis.dao.CertsInfoMapper;
import com.fbee.modules.mybatis.entity.CertsInfoEntity;
import com.fbee.modules.mybatis.model.CertsInfo;
import com.fbee.modules.mybatis.model.ShCert;
import com.fbee.modules.service.InCertCkeckService;
import com.fbee.modules.service.basic.BaseService;
import com.fbee.modules.utils.ObjectUtils;
import com.fbee.modules.utils.ZSCXQuery;

public class InCertCheckServiceImpl extends BaseService implements InCertCkeckService {

	@Autowired
	CertsInfoMapper certsInfoMapper;

	// 证书数据爬取
	@Override
	public JsonResult checkCertInfo(CertCheckForm certCheckForm ,Object clientkey) {
		ZSCXQuery tZSCXQuery = new ZSCXQuery();
		try {

			CertsInfoEntity certsInfoEntity = new CertsInfoEntity();
			certsInfoEntity.setIdcard(certCheckForm.getIdcard());// 身份证号
			certsInfoEntity.setName(certCheckForm.getName());// 姓名
			// 查询自己数据库是否有数据
			List<CertsInfoEntity> list = certsInfoMapper.checkCertById(certsInfoEntity);
				// 调用工具类，查询人社数据
				String str = tZSCXQuery.getHTMLResult(certCheckForm , clientkey);
				
				if (null==str||"".equals(str)) {
					return JsonResult.failure(ResultCode.ERROR);
				}
				
				// 解析返回数据
				List<ShCert> shCerts = h5ToObj(str);
					// 返回数据不为空，则查询成功
					if (shCerts != null) {
						//遍历数据
						for (ShCert shCert : shCerts) {
						// 查询成功有数据
						if (StringUtils.isEmpty(shCert.getNo())) {
							// 检查返回数据身份证号是否本人
							if (shCert.getIdcard().equals(certCheckForm
									.getIdcard())) {
								if (list.size()==0) {
									// 插入本地数据库
									CertsInfo record = new CertsInfo();
									record.setIdcard(shCert.getIdcard());
									record.setName(shCert.getName());
									record.setSex(shCert.getSex());
									record.setIdentificationName(shCert.getIdentification());
									record.setCertificationDate(DateUtils.parseDate(shCert.getCertification_date()));
									record.setCertno(shCert.getCertNo());
									record.setRate(shCert.getRate());
									record.setTgrade(shCert.getTgrade());
									record.setSkillgrade(shCert.getSkillGrade());
									record.setCompgrade(shCert.getCompGrade());
									record.setAbliygrade(shCert.getAbliyGrade());
									record.setResult(shCert.getResult());
									record.setAddTime(new Date());
									certsInfoMapper.insert(record);
								}
							}
						} // 查询成功没有数据
						else if (shCert.getNo().equals("暂时没有信息！")) {
							return JsonResult.success(ResultCode.DATA_IS_NULL);
						}
					}
				}
				// 验证码错误，返回数据为空，查询失败
				else if(shCerts == null) {
						return JsonResult.failure(ResultCode.ERROR);
					}
				
			//}

			return JsonResult.success(ResultCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
			return JsonResult.failure(ResultCode.DATA_ERROR);
		}
	}

	// 证书列表查询
	@Override
	public JsonResult queryCertInfo(String idcard, String name) {
		CertsInfo certsInfo = new CertsInfo();
		certsInfo.setIdcard(idcard);// 身份证号
		certsInfo.setName(name);// 姓名
		// 查询数据
		List<CertsInfo> list = certsInfoMapper.selectCertInfo(certsInfo);
		if (list.size() == 0) {
			// 查询成功没有数据
			return JsonResult.failure(ResultCode.DATA_IS_NULL);
		} else if (!list.get(0).getIdcard().equals(idcard) || !list.get(0).getName().equals(name)) {
			// 比较身份证号和名称是否一致
			return JsonResult.failure(ResultCode.DATA_ERROR);
		}
		return JsonResult.success(list);
	}
	
	/**
	 * @param 解析返回页面数据
	 * @param str
	 * @return listStr1
	 */
	private List<String> getdata(String str) {
		Document doc = Jsoup.parse(str);
		Elements element = doc.select("table[width=390]");
		Elements td = element.select("td");
		// System.out.println(str);
		//List<ShCert> list = new ArrayList<ShCert>();
		List<String> listStr1 = new ArrayList<String>();
		for (int j = 0; j < td.size(); j++) {
			String text = td.get(j).text();
			if (text.equals("暂时没有信息！")) {
				listStr1.add(text);
				break;
			} else if (j % 2 != 0) {
				listStr1.add(text);
			}
		}
		return listStr1;
	}

	/**
	 * @param 封装数据
	 * @param str
	 * @return List<ShCert>
	 */
	private List<ShCert> h5ToObj(String str) {
		ShCert sh = new ShCert();
		List<ShCert> objlist = new ArrayList<>();
		List<String> listStr1 = getdata(str);
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
		List<Map<String, String>> dataall = new ArrayList<>();
		//System.out.println(listStr1);
		//System.out.println(listStr1.size());
		Log.info("----------------"+listStr1.size());
		int count = 0;
		if (listStr1.size() > 1) {
			// list 换成map
			for (int n = 0; n < listStr1.size() / 12; n++) {
				Map<String, String> map = new HashMap<String, String>();
				for (int j = 0; j < listStr2.size(); j++) {
					map.put(listStr2.get(j), listStr1.get(count));
					count = count + 1;
				}
				String st = map.get("certification_date");
				String s = st + "-";
				map.put("certification_date", s.substring(0, 4) + "-" + s.substring(4, 6) + "-" + s.substring(6, 8));
				dataall.add(map);
			}
			for (int i = 0; i < dataall.size(); i++) {
				// 调用工具类，map转object
				ShCert shCert = (ShCert) ObjectUtils.mapToObject(dataall.get(i), sh);
				objlist.add(shCert);
			}
		} else if (listStr1.size() == 1) {
			ShCert shCert = new ShCert();
			shCert.setNo("暂时没有信息！");
			objlist.add(shCert);
			return objlist;
		}else if (listStr1.size()==0){
			return null;
		}
		return objlist;
	}
	
	
	
	
	
}
