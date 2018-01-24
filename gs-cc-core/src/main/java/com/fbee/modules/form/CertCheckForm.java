package com.fbee.modules.form;

public class CertCheckForm {
	
	private String idcard;//身份证号
	
	private String name;//姓名
	
	private String qtzjhm;//其他证件  (暂无)
	
	private String zshm;//职业资格证件  (暂无)
	
	private String sj_mima1;//图形验证码

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getQtzjhm() {
		return qtzjhm;
	}

	public void setQtzjhm(String qtzjhm) {
		this.qtzjhm = qtzjhm;
	}

	public String getZshm() {
		return zshm;
	}

	public void setZshm(String zshm) {
		this.zshm = zshm;
	}

	public String getSj_mima1() {
		return sj_mima1;
	}

	public void setSj_mima1(String sj_mima1) {
		this.sj_mima1 = sj_mima1;
	}

//	public static void main(String[] args) {
//		List list1 = new ArrayList<>();
//		list1.add("name");
//		list1.add("age");
//		list1.add("sex");
//		List list2 = new ArrayList<>();
//		list2.add("zs");
//		list2.add("18");
//		list2.add("m");
//		Map map = new HashMap<>();
//		
//		for (int i=0 ;i<list1.size() ;i++) {
//			for (int j=i ;j<list1.size() ;j++) {
//				map.put(list1.get(i), list2.get(j));
//				break;
//			}
//		}
//		System.out.println(map);
//		
//	}
//	public static void main(String[] args) {
//		
//		Map<String,Integer> map2 = new HashMap<String,Integer>();
//		map2.put("南海a", 168);
//		map2.put("南海b", 197);
//		map2.put("南海c", 181);
//		
//		Map<String,String> map = new HashMap<String,String>();
//		
//		for (String key : map2.keySet()) {
//				map.put("name:"+key,"value:"+map2.get(key));
//		}
//		System.out.println(map);
//		
//	}
}
