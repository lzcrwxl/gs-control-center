package com.fbee.modules.basic;

/**
 * 系统请求URL路径
 * 
 * @ClassName: RequestMappingURL
 * @Description: TODO
 * @author zhangsq
 * @date 2017/2/21
 *
 */

public interface RequestMappingURL {
	
	/**
	 * 基础接口：用户、我
	 */
	public static final String USER_BASE_URL = "/api/login"; //用户登陆基础地址
	public static final String USERS_BASE_URL = "/api/user";// 用户接口基础地址
	public static final String TENANTS_BASE_URL = "/api/tenants"; //门店管理基础地址
	public static final String ORDERS_BASE_URL = "/api/orders";// 订单管理
	public static final String CUSTOMERS_BASE_URL = "/api/customers";// 客户管理
	public static final String FUNDS_BASE_URL = "/api/funds";// 财务管理
	public static final String CLEAR_BASE_URL = "/api/clear";// 清算管理
	public static final String CERTS_BASE_URL = "/api/certs";// 证书管理
	public static final String CACHE_BASE_URL = "/api/cache";// cache接口
	public static final String COMMON_BASE_URL = "/api/common";// 公用接口基础地址
	
	/********************************* 页面字典参数和下拉框模块 *****************************/
	public static final String GET_DICTIONARY_DATA = "/getDictionaryData/{typeCode}";// 获取对应的字典参数参数List
	public static final String GET_AREA_DATA = "/getAreaData/{typeCode}";// 获取区域参数list
	public static final String GET_SKILLS_DATA = "/getSKillsData/{typeCode}";// 获取技能特点（对应服务工种）、个人特点、服务类型
	public static final String CUSTOMER_UPDATE_URL = "/updatecustomer";// 用户修改

	
	/********************************* 缓存管理模块 *****************************************/
	public static final String INIT_CACHE_DATA = "/initCahce";// 初始化系统缓存数据
	
	public static final String CLEAR_CACHE_DATA="clearCache";//清除缓存
	
	
	/********************************* 子级接口  *****************************************/

	public static final String LOGIN_URL = "/login";// 登陆接口
	public static final String LOGOUT_URL = "/logout"; // 退出
	public static final String CAPTCHA_URL = "/getCaptcha";    //图形验证码
	public static final String USER_MENUS_URL = "/userMenus";  //用户菜单
	

	
	/********************************* 门店管理模块 *****************************************/
	public static final String TENANTS_INFO_QUERY = "/tenantsInfoquery";  //申请入驻门店列表
	public static final String GET_TENANTS_BASE = "/getTenantBase";  //申请入驻门店详情
	public static final String NO_PASS = "/nopass";  //申请入驻门店审核不通过
	public static final String TENANTS_PASS = "/tenantsPass";  //申请入驻门店审核通过
	public static final String TENANTS_CHECK = "/tenantsCheck";  //申请入驻门店check
	public static final String GET_TENANTS_APP = "/getTenantsApp";  //已入驻门店列表
	public static final String TENANTS_APP_INFO = "/tenantsAppInfo";  //已入驻门店详细
	public static final String UPDATE_TENANTS_INFO = "/updateTenantsInfo";  //已入驻门店修改
	public static final String RESET_PASSWORD = "/resetPassword";  //已入驻门店密码重置
	public static final String IS_BLOCK = "/isblock";  //已入驻门店冻结解冻
	public static final String BANK_CODE = "/bankCode";  //银行列表code
	
	
	/********************************* 订单管理模块 *****************************************/
	public static final String	RESERVE_ORDERS_LIST_URL = "/reserveOrdersList";//预约管理订单列表获取
	public static final String	ORDERS_LIST_URL = "/OrdersList";//订单管理订单列表获取
	
	
	
	/********************************* 客户管理模块 *****************************************/
	public static final String	QUERY_CUSTOMER_LIST_URL = "/QueryCustomersList";//客户列表获取
	
	
	
	/********************************* 财务管理模块 *****************************************/
	public static final String	QUERY_FUNDS_URL = "/QueryFunds";//财务总览获取
	public static final String	SELECT_FUNDS_LIST_URL = "/SelectFundsList";//财务流水列表获取
	
	
	/********************************* 清算管理模块 *****************************************/
	public static final String	QUERY_CLEAR_LIST_URL = "/QueryClearList";//清算列表获取
	public static final String	TUIKUAN_CLEAR = "/tuiKuanClear";//退款清算
	public static final String	TIXIAN_CLEAR = "/tiXianClear";//提现清算
	public static final String	EXPORT_EXCEL = "/ExportExcel";//导出excel
	
	
	/********************************* 证书管理模块 *****************************************/
	public static final String	QUERY_CERTS_LIST_URL = "/QueryCertsList";//证书列表获取
	public static final String	CERTS_INFO_URL = "/CertsInfo";//证书详细
	public static final String	PASS_CERT_URL = "/PassCert";//证书审核-通过
	public static final String	Nopass_CERT_URL = "/NopassCert";//证书审核-通过
	
	/********************************* 职业资格证书查询Test *****************************************/
	public static final String ZSCX_BASE_URL = "/api/zscx";// 职业资格证书接口基础地址
	public static final String	ZSCX_CKECK_URL = "/zscxCheck";//证书数据爬取
	public static final String	ZSCX_CAPTCHA_URL = "/zscxcaptcha";//职业资格证书验证码
	public static final String	QUERY_CERT_INFO = "/queryCertInfo";//证书查询
	
	
}
