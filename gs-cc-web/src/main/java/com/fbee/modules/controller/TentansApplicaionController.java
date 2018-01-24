package com.fbee.modules.controller;

import com.fbee.modules.basic.RequestMappingURL;
import com.fbee.modules.bean.Constants;
import com.fbee.modules.bean.UserBean;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.utils.SessionUtils;
import com.fbee.modules.form.MendianForm;
import com.fbee.modules.form.TenantsAppForm;
import com.fbee.modules.form.TenantsManagementForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.jsonData.json.TenantsBaseData;
import com.fbee.modules.service.TenantsAppService;
import com.fbee.modules.service.TentansApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
* @Description:总控平台-门店管理控制层
* @author zhangsq
* @date 2017/2/21
* */

@Controller
@RequestMapping(RequestMappingURL.TENANTS_BASE_URL)
public class TentansApplicaionController extends BaseController {

    @Autowired
    TentansApplicationService tentansApplicationService;
    @Autowired
    TenantsAppService tenantsAppService;

    /**
     * 申请入驻门店列表查询
     *
     * @param request
     * @param response
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = RequestMappingURL.TENANTS_INFO_QUERY, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult queryTentans(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(value = "pageNumber", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNumber,
                                   @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize,
                                   MendianForm mendianForm) {
        try {

            HttpSession session = SessionUtils.getSession(request);
            UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);//用户缓存
            if (userBean == null) {
                return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
            }

            //System.out.println(mendianForm);
            Log.info(mendianForm.toString());//门店管理租户查询条件

            return tentansApplicationService.queryTentansInfoList(mendianForm, pageNumber, pageSize);// 门店管理—查询列表
        } catch (Exception e) {
            Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
            return JsonResult.failure(ResultCode.ERROR);
        }
    }


    /**
     * 门店审核-基本信息查询
     *
     * @param request
     * @param response
     * @param applicationId
     * @return
     */
    @RequestMapping(value = RequestMappingURL.GET_TENANTS_BASE, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult getTentansInById(HttpServletRequest request, HttpServletResponse response,
                                       Integer applicationId) {

        try {

            HttpSession session = SessionUtils.getSession(request);
            UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);//获取用户缓存
            if (userBean == null) {
                return JsonResult.failure(ResultCode.SESSION_TIMEOUT);//登录超时
            }
            TenantsBaseData tenantsBaseData = tentansApplicationService.getTentansBaseById(applicationId);//门店管理-门店详细
            return JsonResult.success(tenantsBaseData);
        } catch (Exception e) {
            Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
            return JsonResult.failure(ResultCode.ERROR);
        }
    }


    /**
     * 门店审核通过
     *
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = RequestMappingURL.TENANTS_PASS, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView tenantsPassById(HttpServletRequest request, HttpServletResponse response,
                                        TenantsManagementForm tenantsManagementForm) {
        ModelAndView mode = new ModelAndView();
        String callbackUrl = request.getParameter("callbackUrl");
        mode.setViewName("redirect:" + callbackUrl);
        try {

            HttpSession session = SessionUtils.getSession(request);
            UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
            if (userBean == null) {
                mode.addObject("message", ResultCode.SESSION_TIMEOUT);
                return mode;
            }
            tentansApplicationService.tenantsPassById(tenantsManagementForm);//门店管理-审核通过
            return mode;
        } catch (Exception e) {
            Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
            return mode;
        }

    }

    /**
     * 门店审核不通过
     *
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = RequestMappingURL.NO_PASS, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JsonResult nopassById(HttpServletRequest request, HttpServletResponse response,
                                 Integer applicationId, String remarks) {
        try {

            HttpSession session = SessionUtils.getSession(request);
            UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
            if (userBean == null) {
                return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
            }
            return tentansApplicationService.nopassById(applicationId, remarks);//门店审核不通过
        } catch (Exception e) {
            Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
            return JsonResult.failure(ResultCode.ERROR);
        }

    }

    /**
     * 门店审核Check
     *
     * @param request
     * @param response
     * @param domain
     * @param loginAccount
     * @param
     */
    @RequestMapping(value = RequestMappingURL.TENANTS_CHECK, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JsonResult tenantsCheck(HttpServletRequest request, HttpServletResponse response,
                                   String domain, String loginAccount) {
        try {

            HttpSession session = SessionUtils.getSession(request);
            UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
            if (userBean == null) {
                return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
            }

            return tentansApplicationService.tenantsCheck(domain, loginAccount);
        } catch (Exception e) {
            Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
            return JsonResult.failure(ResultCode.ERROR);
        }

    }

    /**
     * 已入驻门店列表查询
     *
     * @param request
     * @param response
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = RequestMappingURL.GET_TENANTS_APP, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult getTentansApp(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "pageNumber", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNumber,
                                    @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize,
                                    TenantsAppForm tenantsAppForm) {
        try {

            HttpSession session = SessionUtils.getSession(request);
            UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
            if (userBean == null) {
                return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
            }
            Log.info(tenantsAppForm.toString());

            return tenantsAppService.getTenantsAppInfo(tenantsAppForm, pageNumber, pageSize);
        } catch (Exception e) {
            Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
            return JsonResult.failure(ResultCode.ERROR);
        }
    }

    /**
     * 已入驻门店详细
     *
     * @param request
     * @param response
     * @param tenantId
     * @return
     */
    @RequestMapping(value = RequestMappingURL.TENANTS_APP_INFO, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult getTentansAppInfo(HttpServletRequest request, HttpServletResponse response,
                                        Integer tenantId) {
        try {

            HttpSession session = SessionUtils.getSession(request);
            UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
            if (userBean == null) {
                return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
            }
            return tenantsAppService.getTenantsAppById(tenantId);
        } catch (Exception e) {
            Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
            return JsonResult.failure(ResultCode.ERROR);
        }
    }

    /**
     * 修改已入驻门店
     *
     * @param request
     * @param response
     * @param tenantId
     * @return
     */
    @RequestMapping(value = RequestMappingURL.UPDATE_TENANTS_INFO, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView updateTentansInfo(HttpServletRequest request, HttpServletResponse response,
                                          Integer tenantId, TenantsManagementForm tenantsManagementForm) {

        ModelAndView mode = new ModelAndView();
        String callbackUrl = request.getParameter("callbackUrl");
        mode.setViewName("redirect:" + callbackUrl);
        try {

            HttpSession session = SessionUtils.getSession(request);
            UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
            if (userBean == null) {
                mode.addObject("message", ResultCode.SESSION_TIMEOUT);
                return mode;
            }
            tenantsAppService.updateTenantsById(tenantId, tenantsManagementForm);
            return mode;
        } catch (Exception e) {
            Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
            return mode;
        }
    }

    /**
     * 修改密码
     *
     * @param request
     * @param response
     * @param loginAccount
     * @param newpassword
     * @return
     */
    @RequestMapping(value = RequestMappingURL.RESET_PASSWORD, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult resetPassword(HttpServletRequest request, HttpServletResponse response,
                                    Integer tenantId, String loginAccount, String newpassword) {

        try {

            HttpSession session = SessionUtils.getSession(request);
            UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
            if (userBean == null) {
                return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
            }

            return tenantsAppService.resetPassword(tenantId, loginAccount, newpassword);
        } catch (Exception e) {
            Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
            return JsonResult.failure(ResultCode.ERROR);
        }
    }

    /**
     * 冻结解冻
     *
     * @param request
     * @param response
     * @param tenantId
     * @param tenantStatusCode
     * @return
     */
    @RequestMapping(value = RequestMappingURL.IS_BLOCK, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult isblock(HttpServletRequest request, HttpServletResponse response,
                              Integer tenantId, String tenantStatusCode) {

        try {

            HttpSession session = SessionUtils.getSession(request);
            UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
            if (userBean == null) {
                return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
            }

            return tenantsAppService.isblock(tenantId, tenantStatusCode);
        } catch (Exception e) {
            Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
            return JsonResult.failure(ResultCode.ERROR);
        }
    }


    /**
     * 银行code
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = RequestMappingURL.BANK_CODE, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResult bankCode(HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = SessionUtils.getSession(request);
            UserBean userBean = (UserBean) session.getAttribute(Constants.USER_SESSION);
            if (userBean == null) {
                return JsonResult.failure(ResultCode.SESSION_TIMEOUT);
            }

            return tenantsAppService.getBankCode();
        } catch (Exception e) {
            Log.error(ResultCode.getMsg(ResultCode.ERROR), e);
            return JsonResult.failure(ResultCode.ERROR);
        }
    }

}
