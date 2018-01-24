package com.fbee.modules.controller;

import com.fbee.modules.core.utils.MD5;

/** 
* @ClassName: TestController 
* @Description: TODO
* @author 贺章鹏
* @date 2016年12月9日 下午1:41:23 
*  
*/
//@Controller
public class TestController {
	
	/*@Autowired
	TestService testService;
	
	@Autowired
	RedisService redisService;

	@RequestMapping("/api/test/login")
	@ResponseBody
	public JsonResult testLogin(String userId,String loginPwd){
		testService.test();
		TestJson testJson=new TestJson();
		testJson.setCode( userId);
		testJson.setName(loginPwd);
		return JsonResult.success(testJson);
	}
	
	@RequestMapping("/api/test/views")
	public ModelAndView views(ModelAndView mv){
		StringBuilder redirectUrl = new StringBuilder("/html/index.html");
		mv.setView(new RedirectView(redirectUrl.toString(), false));
		return mv;
	}
	
	@RequestMapping("/api/test/redis")
	@ResponseBody
	public JsonResult testRedis(){
		redisService.setStrValue("testName", "张三");
		return JsonResult.success(redisService.getStrValue("testName"));
	}*/
	public static void main(String[] args) {
		String str = "123456";
		//System.out.println(EntryptUtils.entryptUserPassword(str, str));
		 String ENTRY_STRING="{[(%s)]}";
		 StringBuffer sb=new StringBuffer();
		StringBuffer str1=sb.append(str.toUpperCase()).append(String.format(ENTRY_STRING, str));
		System.out.println(str1);
		System.out.println(MD5.getStrMD5(str1.toString()));
	}
}
