package com.fbee.modules.bean;

public class Constants {
	//用户缓存
	public static final String USER_SESSION = SessionKeys.USER_SESSION;
	//banner默认的系统图片
	public static final String DEFAULT_BANNER_IMAGE_KEY="D_BANNER_IMAGE";
	//服务管理系统默认的图片地址
	public static final String DEFAULT_SERVICE_IMAGE_KEY="D_SERVICE_IMAGE";
	//关于我们系统默认图片地址
	public static final String DEFAULT_ABOUNT_US_IMAGE_KEY="D_ABOUNT_US_IMAGE";
	//关于我们系统最大上传图片个数
	public static final String MAX_ABOUNT_US_IMAGE_NUM="NUM_ABOUNT_US_IMAGE";
	
	public static final Integer PAGE_SIZE=10;
	
	public static final Integer CONTRACT_NUM=6;

	public static final String IMAGE_TYPE=".JPG.PNG.BMP.GIF.JPEG";

	public static final String STATUS="00";
	
	public class SHELF_TYPE {
		public static final String NOW_TIME = "01";
		public static final String APPOINT_TIME = "02";
	}
	
	public class ISUSABLE {
		public static final String YES = "1";
		public static final String NO = "0";
	}
	
	public static final String DEFAULT_PAGE_NO="1";
	
	public static final String DEFAULT_PAGE_SIZE="10";
	
	public static final String DAYTON="、";
	
	public static final String EMPTY="";

	public static final String COMMA=",";
	
	public static final String GET="+";//收入

	public static final String PAY="-";//支出

	/*门店入驻图片路径*/
	public static final String TEN_ANTS_IMGAE_BASE_PATH = "tenants";
	public static final String LO_GO_IMGAE_BASE_PATH = "logo";//门店logo
	public static final String LIE_NCE_IMGAE_BASE_PATH = "lience";//营业执照图片
	public static final String FR_ONT_IMGAE_BASE_PATH = "front";//身份证正面图片
	public static final String BA_CK_IMGAE_BASE_PATH = "back";//身份证反面图片
	
	/*网站管理图片路径*/
	public static final String WEB_SITE_IMGAE_BASE_PATH = "website";
	public static final String ABOUT_US_IMGAE_BASE_PATH = "aboutus";
	public static final String CONTACT_IMGAE_BASE_PATH = "contact";
	public static final String BANNER_IMGAE_BASE_PATH = "banner";
	
	public static final String IMGAE_BASE_PATH="";
	public static final String YTX_DOMAIN = "sms.ytx.domain";
	public static final String YTX_PORT = "sms.ytx.port";
	public static final String YTX_ACCOUNT_SID = "sms.ytx.accountSid";
	public static final String YTX_AUTH_TOKEN = "sms.ytx.authToken";
	public static final String YTX_APP_ID = "sms.ytx.appId";
	
	/*合同信息图片路径*/
	public static final String ORDER_IMAGE_BASE_PATH = "order";
	
	public static final String CONTRACT_IMAGE_BASE_PATH = "contract";
	
}
