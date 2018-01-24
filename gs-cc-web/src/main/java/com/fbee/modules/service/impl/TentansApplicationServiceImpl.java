package com.fbee.modules.service.impl;

import com.fbee.modules.bean.Constants;
import com.fbee.modules.bean.ErrorMsg;
import com.fbee.modules.core.Log;
import com.fbee.modules.core.config.Global;
import com.fbee.modules.core.page.Page;
import com.fbee.modules.core.utils.DateUtils;
import com.fbee.modules.core.utils.StringUtils;
import com.fbee.modules.form.MendianForm;
import com.fbee.modules.form.TenantsManagementForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;
import com.fbee.modules.jsonData.json.TenantsBaseData;
import com.fbee.modules.mybatis.dao.*;
import com.fbee.modules.mybatis.entity.*;
import com.fbee.modules.mybatis.model.*;
import com.fbee.modules.service.SmsService;
import com.fbee.modules.service.TentansApplicationService;
import com.fbee.modules.service.basic.BaseService;
import com.fbee.modules.service.basic.ServiceException;
import com.fbee.modules.utils.DictionarysCacheUtils;
import com.fbee.modules.utils.EntryptUtils;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 申请入驻门店列表查询
 * @author: zhangsq
 * @date: 2017年2月22日 下午4:56:47
 */
@Service
public class TentansApplicationServiceImpl extends BaseService implements TentansApplicationService {

    @Autowired
    SmsService smsService;
    @Autowired
    TenantsAppsMapper tenantsAppsMapper;
    @Autowired
    TenantsApplicationMapper tenatsApplicationMapper;
    @Autowired
    TenantsUsersMapper TenantsUsersMapper;
    @Autowired
    TenantsBankCardsMapper TenantsBankCardsMapper;
    @Autowired
    TenantsServiceItemsMapper tenantsServiceItemsDao;
    @Autowired
    TenantsBannersMapper tenantsBannersMapper;

    @Autowired
    TenantsFundsMapper tenantsFundsMapper;

    @Autowired
    TenantsInfosMapper tenantsInfosMapper;

    @Autowired
    TenantsContactBarMapper tenantsContactBarMapper;

    @Autowired
    TenantsAboutUsMapper tenantsAboutUsMapper;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public JsonResult queryTentansInfoList(MendianForm mendianForm, Integer pageNumber,
                                           Integer pageSize) {

        try {
            // 获取总条数
            Map<Object, Object> map = Maps.newHashMap();
            map.put("tenantStatus", mendianForm.getTenantStatus());
            map.put("tenantName", mendianForm.getTenantName());
            map.put("registerPhone", mendianForm.getRegisterPhone());
            map.put("tenantStatus", mendianForm.getTenantStatus());
            map.put("addTimeLeft", mendianForm.getAddTimeLeft());
            map.put("addTimeRight", mendianForm.getAddTimeRight());
            map.put("provice", mendianForm.getProvice());
            map.put("city", mendianForm.getCity());
            Integer totalCount = tenatsApplicationMapper.getTenantsInfoCount(map);//申请入驻门店总数查询
            // 分页实体
            Page<Map> page = new Page<Map>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);
            if (totalCount == null) {
                return JsonResult.success(page);
            }
            //最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            map.put("pageNumber", page.getOffset());
            map.put("pageSize", page.getRowNum());
            if (totalCount > 0) {

                List<Map> list = tenatsApplicationMapper.getTenantsInfoList(map);
                String str = "";
                for (Map<String, Object> map2 : list) {
                    //省code转中文
                    str = (String) map2.get("privince");
                    if (!StringUtils.isBlank(str)) {
                        map2.put("privince", DictionarysCacheUtils.getProviceName(str));
                    }
                    //市code转中文
                    str = (String) map2.get("city");
                    if (!StringUtils.isBlank(str)) {
                        map2.put("city", DictionarysCacheUtils.getCityName(str));
                    }
                    //区code转中文
                    str = (String) map2.get("area");
                    if (!StringUtils.isBlank(str)) {
                        map2.put("area", DictionarysCacheUtils.getCountyName(str));
                    } else {
                        map2.put("area", "");
                    }

                }
                page.setRows(list);
                page.setRecords(totalCount.longValue());
            }
            return JsonResult.success(page);
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
            return JsonResult.failure(ResultCode.DATA_ERROR);
        }
    }

    //基本信息查询
    @Override
    public TenantsBaseData getTentansBaseById(Integer applicationId) {

        TenantsBaseData tenantsBaseData = new TenantsBaseData();
        //查询账户基本信息
        TenantsApplication tenansList = tenatsApplicationMapper.selectByPrimaryKey(applicationId);
        if (tenansList == null) {
            return null;
        }
        if (tenansList != null) {
            if (tenansList.getTenantStatus().equals("03")) {
                tenantsBaseData.setApplicationId(applicationId);
                tenantsBaseData.setTenantName(tenansList.getTenantName());
                tenantsBaseData.setRegisterPhone(tenansList.getRegisterPhone());
                tenantsBaseData.setPrivince(DictionarysCacheUtils.getProviceName(tenansList.getPrivince()));
                tenantsBaseData.setCity(DictionarysCacheUtils.getCityName(tenansList.getCity()));
                tenantsBaseData.setRemarks(tenansList.getRemarks());//备注
                tenantsBaseData.setRecommendId(tenansList.getRecommendId());//
                tenantsBaseData.setModifyTime(DateUtils.dateToStr(tenansList.getModifyTime(), "yyyy-MM-dd HH:mm:ss"));//修改时间
                tenantsBaseData.setAddTime(DateUtils.dateToStr(tenansList.getAddTime(), "yyyy-MM-dd HH:mm:ss"));
            } else if (tenansList.getTenantStatus().equals("01")) {
                tenantsBaseData.setApplicationId(applicationId);
                tenantsBaseData.setTenantName(tenansList.getTenantName());
                tenantsBaseData.setRegisterPhone(tenansList.getRegisterPhone());
                tenantsBaseData.setPrivince(tenansList.getPrivince());
                tenantsBaseData.setCity(tenansList.getCity());
                tenantsBaseData.setArea(tenansList.getArea());
                tenantsBaseData.setRemarks(tenansList.getRemarks());
                tenantsBaseData.setRecommendId(tenansList.getRecommendId());
                tenantsBaseData.setAddTime(DateUtils.dateToStr(tenansList.getAddTime(), "yyyy-MM-dd HH:mm:ss"));
                tenantsBaseData.setContactAddress(tenansList.getContactAddress());//详细地址
                tenantsBaseData.setTelephonenumber(tenansList.getTelephonenumber());//门店电话
                tenantsBaseData.setAgentName(tenansList.getAgentName());//代理人姓名    //申请人名称
                tenantsBaseData.setAccountProperties(tenansList.getAccountProperties());//账号性质
                tenantsBaseData.setAccountName(tenansList.getAccountName());//账号名称
                tenantsBaseData.setBankAccount(tenansList.getBankAccount());//银行账号
                tenantsBaseData.setOpeningBand(tenansList.getOpeningBand());//开户行
                tenantsBaseData.setOpeningBandBranch(tenansList.getOpeningBandBranch());//开户支行
                tenantsBaseData.setBusinessName(tenansList.getBusinessName());//工商名称
                tenantsBaseData.setSocialInformationCode(tenansList.getSocialInformationCode());//社会信息代码
                tenantsBaseData.setId_number(tenansList.getIdNumber());//代理人身份证号
                tenantsBaseData.setIdPhotopositive(tenansList.getIdPhotopositive());//正面照片
                tenantsBaseData.setIdPhotonegative(tenansList.getIdPhotonegative());//背面照片
                tenantsBaseData.setLicence_img(tenansList.getLicenceImg());//营业执照
            }
        }
        return tenantsBaseData;
    }

    //审核通过
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult tenantsPassById(TenantsManagementForm tenantsManagementForm) {
        try {
            TenantsAppsEntity tenantsAppsEntity = new TenantsAppsEntity();
            //审核申请门店
            if (tenantsManagementForm.getApplicationId() != null
                    && StringUtils.isNotEmpty(tenantsManagementForm.getRegisterPhone())) {
                // 更新入驻列表为已审核通过
                TenantsApplication tenantsApplication = new TenantsApplication();
                tenantsApplication.setApplicationId(tenantsManagementForm.getApplicationId());
                tenantsApplication.setTenantStatus("02");
                tenantsApplication.setTenantName(tenantsManagementForm.getTenantName());
                tenantsApplication.setPrivince(tenantsManagementForm.getServiceProvice());
                tenantsApplication.setCity(tenantsManagementForm.getServiceCity());
                tenantsApplication.setAddTime(tenantsManagementForm.getAddTime());
                tenantsApplication.setModifyTime(new Date());
                tenantsApplication.setRegisterPhone(tenantsManagementForm.getRegisterPhone());
                tenatsApplicationMapper.updateByPrimaryKey(tenantsApplication);
                // 更新门店详细表
                tenantsAppsEntity.setIdcardFrontImg(tenantsManagementForm.getFileFront().split(",")[0]);//身份证正面
                tenantsAppsEntity.setIdcardBackImg(tenantsManagementForm.getFileBack().split(",")[0]);//身份证背面
                tenantsAppsEntity.setLicenceImg(tenantsManagementForm.getFileLience().split(",")[0]);//营业执照照片
                tenantsAppsEntity.setWebsiteLogo(tenantsManagementForm.getFileLogo().split(",")[0]);//logo
                tenantsAppsEntity.setRegisterPhone(tenantsManagementForm.getRegisterPhone());
                tenantsAppsEntity.setApplyTime(tenantsManagementForm.getAddTime());
                tenantsAppsEntity.setWebsiteName(tenantsManagementForm.getTenantName());
                tenantsAppsEntity.setPrivince(tenantsManagementForm.getServiceProvice());
                tenantsAppsEntity.setCity(tenantsManagementForm.getServiceCity());
                tenantsAppsEntity.setArea(tenantsManagementForm.getServiceCounty());
                tenantsAppsEntity.setContactAddress(tenantsManagementForm.getContactAddress());
                tenantsAppsEntity.setTenantsPhone(tenantsManagementForm.getTenantsPhone());
                tenantsAppsEntity.setContactName(tenantsManagementForm.getContactName());
                tenantsAppsEntity.setContactPhone(tenantsManagementForm.getContactPhone());
                tenantsAppsEntity.setDomain(tenantsManagementForm.getDomain());
                tenantsAppsEntity.setCompanyName(tenantsManagementForm.getCompanyName());
                tenantsAppsEntity.setLicenceNo(tenantsManagementForm.getLicenceNo());
                tenantsAppsEntity.setAgentName(tenantsManagementForm.getAgentName());
                String hostUrl = (String) Global.getConfig("hostUrl");
                tenantsAppsEntity.setWebsiteUrl(hostUrl + "/" + tenantsManagementForm.getDomain());
                //证件类型01身份证
                tenantsAppsEntity.setIdType("01");
                tenantsAppsEntity.setIdNumber(tenantsManagementForm.getIdNumber());
                tenantsAppsEntity.setSalesPerson(tenantsManagementForm.getSalesPerson());
                // 门店状态01正常02锁定03已到期
                tenantsAppsEntity.setTenantStatus("01");
                // 申请门店为01免费试用版 02标准版 03尊享版
                tenantsAppsEntity.setMemberType("01");
                //申请门店为可用
                tenantsAppsEntity.setIsUsable("1");
                tenantsAppsEntity.setAddTime(new Date());
                //推荐ID
                tenantsAppsMapper.insert(tenantsAppsEntity);

            }
            //创建新门店
            if (null == tenantsManagementForm.getApplicationId()) {
                //更新门店详细表
                tenantsAppsEntity.setApplyTime(new Date());
                tenantsAppsEntity.setWebsiteName(tenantsManagementForm.getTenantName());
                tenantsAppsEntity.setPrivince(tenantsManagementForm.getServiceProvice());
                tenantsAppsEntity.setCity(tenantsManagementForm.getServiceCity());
                tenantsAppsEntity.setArea(tenantsManagementForm.getServiceCounty());
                tenantsAppsEntity.setContactAddress(tenantsManagementForm.getContactAddress());
                tenantsAppsEntity.setTenantsPhone(tenantsManagementForm.getTenantsPhone());
                tenantsAppsEntity.setContactName(tenantsManagementForm.getContactName());
                tenantsAppsEntity.setContactPhone(tenantsManagementForm.getContactPhone());
                tenantsAppsEntity.setDomain(tenantsManagementForm.getDomain());
                tenantsAppsEntity.setCompanyName(tenantsManagementForm.getCompanyName());
                tenantsAppsEntity.setLicenceNo(tenantsManagementForm.getLicenceNo());
                tenantsAppsEntity.setAgentName(tenantsManagementForm.getAgentName());
                tenantsAppsEntity.setIdcardFrontImg(tenantsManagementForm.getFileFront().split(",")[2]);//身份证正面
                tenantsAppsEntity.setIdcardBackImg(tenantsManagementForm.getFileBack().split(",")[2]);//身份证背面
                tenantsAppsEntity.setLicenceImg(tenantsManagementForm.getFileLience().split(",")[2]);//营业执照照片
                tenantsAppsEntity.setWebsiteLogo(tenantsManagementForm.getFileLogo());//logo
                String hostUrl = (String) Global.getConfig("hostUrl");
                tenantsAppsEntity.setWebsiteUrl(hostUrl + "/" + tenantsManagementForm.getDomain());
                //证件类型01身份证
                tenantsAppsEntity.setIdType("01");
                tenantsAppsEntity.setIdNumber(tenantsManagementForm.getIdNumber());
                tenantsAppsEntity.setRecommendId(tenantsManagementForm.getRecommendId());
                tenantsAppsEntity.setSalesPerson(tenantsManagementForm.getSalesPerson());
                // 门店状态01正常02锁定03已到期
                tenantsAppsEntity.setTenantStatus("01");
                //创建门店为01免费试用版 02标准版 03尊享版
                tenantsAppsEntity.setMemberType("01");
                //创建门店为可用
                tenantsAppsEntity.setIsUsable("1");
                tenantsAppsEntity.setAddTime(new Date());
                tenantsAppsMapper.insert(tenantsAppsEntity);
            }
            // 插入门店超级管理员账户信息
            System.out.println("++++++++++++++++++++插入用户表+++++++++++++开始");
            TenantsUsersEntity tenantsUsers = new TenantsUsersEntity();
            tenantsUsers.setLoginAccount(tenantsManagementForm.getLoginAccount());
            tenantsUsers.setLoginName("超级管理员");
            tenantsUsers.setPassword(EntryptUtils.entryptUserPassword(tenantsManagementForm.getPassword(),
                    tenantsManagementForm.getRegisterPhone()));
            tenantsUsers.setSalt(tenantsManagementForm.getRegisterPhone());
            tenantsUsers.setTelephone(tenantsManagementForm.getRegisterPhone());
            tenantsUsers.setLoginFlag("1");
            tenantsUsers.setIsUsable("1");
            // 用户类型01超级管理员02管理员03业务员
            tenantsUsers.setUserType("01");
            tenantsUsers.setAddTime(new Date());
            tenantsUsers.setTenantId(tenantsAppsEntity.getTenantId());
            TenantsUsersMapper.insert(tenantsUsers);
            System.out.println("++++++++++++++++++++插入用户表+++++++++++++成功");
            // 插入门店银行卡信息
            System.out.println("++++++++++++++++++++插入门店银行卡+++++++++++++开始");
            TenantsBankCardsEntity tenantsBankCardsEntity = new TenantsBankCardsEntity();
            tenantsBankCardsEntity.setTenantId(tenantsAppsEntity.getTenantId());
            tenantsBankCardsEntity.setCardNo(tenantsManagementForm.getCardNo());
            tenantsBankCardsEntity.setAccountType(tenantsManagementForm.getAccountType());
            tenantsBankCardsEntity.setBankName(tenantsManagementForm.getBankName());
            tenantsBankCardsEntity.setBankCode(tenantsManagementForm.getBankCode());
            tenantsBankCardsEntity.setBankSecName(tenantsManagementForm.getBankSecName());
            tenantsBankCardsEntity.setIsUsable("1");
            tenantsBankCardsEntity.setAddTime(new Date());
            TenantsBankCardsMapper.insert(tenantsBankCardsEntity);
            System.out.println("+++++++++++++++++++++++++++++++插入门店银行卡信息++++++++++++++++=成功");
            //插入服务工种
            System.out.println("++++++++++++++++++++++插入服务工作+++开始");
            Integer tenantId = tenantsAppsEntity.getTenantId();
            tenantsServiceItemsDao.insertByTenantId(tenantId);
            System.out.println("+++++++++++++++++++++++++=插入服务工种+++++++++成功");
            //BANNER管理插入默认图片
            System.out.println("+++++++++++++++++++++++++=插入bannner++++++++++++开始");
            TenantsBannersEntity bna = tenantsBannersMapper.getBannerByTenantId(0);
            System.out.println("+++++++++++++++++++++++++++++++++++++new   BANNER++++++++++++++++++++++++++++");
            bna.setId(null);
            bna.setTenantId(tenantsAppsEntity.getTenantId());
            bna.setAddTime(new Date());
            System.out.println("+++++++++++++++++banners插入数据insert++++++开始" + bna.getTenantId());
            tenantsBannersMapper.insert(bna);
            System.out.println("+++++++++++++++++++++++++=插入bannner++++++++++++成功");

            TenantsAboutUs au = tenantsAboutUsMapper.getByTenantId(0);
            au.setId(null);
            au.setTenantId(tenantsAppsEntity.getTenantId());
            au.setAddTime(new Date());
            tenantsAboutUsMapper.insert(au);

            //系统默认租户联系方式
            System.out.println("+++++++++++++++++++++++++=插入联系方式++++++++++++开始");
            TenantsContactBarEntity tenantsContactBarEntity = new TenantsContactBarEntity();
            tenantsContactBarEntity.setTenantId(tenantsAppsEntity.getTenantId());
            tenantsContactBarEntity.setContactPhone("000-0000 0000");//租户默认联系电话
            tenantsContactBarEntity.setQqOne("000000");//租户默认QQ号码
            tenantsContactBarEntity.setQqTwo("0000000");
            tenantsContactBarEntity.setQqThree("0000000");
            tenantsContactBarEntity.setIsOpenMobile("0");//是否开启电话0不开启
            tenantsContactBarEntity.setIsOpenQq("0");//是否开启qq 0：不开启
            tenantsContactBarEntity.setIsOpenQrCode("0");//是否开启二维码 0：不开启
            tenantsContactBarEntity.setAddTime(new Date());
            tenantsContactBarEntity.setIsUsable("1");//是否可用1：可用
            tenantsContactBarEntity.setIsOpenQqOne("0");//是否开启第一个qq 0 不开启
            tenantsContactBarEntity.setIsOpenQqTwo("0");//是否开启第二个qq 0不开启
            tenantsContactBarEntity.setIsOpenQqThree("0");//是否开启第三个qq 0不开启
            tenantsContactBarMapper.insert(tenantsContactBarEntity);
            System.out.println("+++++++++++++++++++++++++=插入联系方式++++++++++++结束");

            TenantsFundsEntity fund = new TenantsFundsEntity();
            fund.setTenantId(tenantsAppsEntity.getTenantId());
            fund.setAvailableAmount(new BigDecimal(0));
            fund.setFrozenAmount(new BigDecimal(0));
            fund.setGrandTotalAmount(new BigDecimal(0));
            tenantsFundsMapper.insert(fund);

            TenantsInfosEntity info = new TenantsInfosEntity();
            info.setTenantId(tenantsAppsEntity.getTenantId());
            info.setJobCount(0);
            info.setResumeCount(0);
            info.setIsUsable("1");
            tenantsInfosMapper.insert(info);
            //发送短信
            System.out.println("注册手机号" + tenantsAppsEntity.getContactPhone() + "网站名称" + tenantsAppsEntity.getWebsiteName());
            System.out.println("账号" + tenantsManagementForm.getLoginAccount() + "密码" + tenantsManagementForm.getPassword());
            smsService.sendTenantPass(tenantsAppsEntity.getContactPhone(), tenantsAppsEntity.getWebsiteName(), tenantsManagementForm.getLoginAccount(), tenantsManagementForm.getPassword());
            return JsonResult.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
            throw e;
        }
    }


    //logo图片保存
    @Override
    @Transactional
    public Map<String, String> uploadLogoImg(MultipartFile fileLogo) {
        String uploadFileName = fileLogo.getOriginalFilename();
        String serverPath = Global.getConfig("website_base_path");
        String basePath = Constants.TEN_ANTS_IMGAE_BASE_PATH;
        String logoPath = Constants.LO_GO_IMGAE_BASE_PATH;
        String fileSavePath = serverPath + "/" + basePath + "/" + logoPath + "/";
        String imgName = "/" + basePath + "/" + logoPath + "/";
        if (!fileLogo.isEmpty()) {
            try {
                // 获取文件后缀
                String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
                checkFile(suffix);

                if (suffix == null || "".equals(suffix)) {
                    throw new ServiceException("未知文件类型，上传失败！");
                }
                // 校验文件类型
                if (!verifyImageType(suffix)) {
                    throw new ServiceException("文件类型不符合要求，上传失败！");
                }
                // 重命名上传后的文件名
                String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + suffix;

                File targetFile = new File(fileSavePath, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                //保存
                try {
                    fileLogo.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("img", fileSavePath + fileName);
                map.put("imgUrl", imgName + fileName);
                return map;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //营业执照图片保存
    @Override
    @Transactional
    public Map<String, String> uploadLienceImg(MultipartFile fileLience) {
        String uploadFileName = fileLience.getOriginalFilename();
        String serverPath = Global.getConfig("website_base_path");
        String basePath = Constants.TEN_ANTS_IMGAE_BASE_PATH;
        String liencePath = Constants.LIE_NCE_IMGAE_BASE_PATH;
        String fileSavePath = serverPath + "/" + basePath + "/" + liencePath + "/";
        String imgName = "/" + basePath + "/" + liencePath + "/";
        if (!fileLience.isEmpty()) {
            try {
                // 获取文件后缀
                String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
                checkFile(suffix);

                if (suffix == null || "".equals(suffix)) {
                    throw new ServiceException("未知文件类型，上传失败！");
                }
                // 校验文件类型
                if (!verifyImageType(suffix)) {
                    throw new ServiceException("文件类型不符合要求，上传失败！");
                }
                // 重命名上传后的文件名
                String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + suffix;

                File targetFile = new File(fileSavePath, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                //保存
                try {
                    fileLience.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("img", fileSavePath + fileName);
                map.put("imgUrl", imgName + fileName);
                return map;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //身份证正面图片保存
    @Override
    @Transactional
    public Map<String, String> uploadFrontImg(MultipartFile fileFront) {
        String uploadFileName = fileFront.getOriginalFilename();
        String serverPath = Global.getConfig("website_base_path");
        String basePath = Constants.TEN_ANTS_IMGAE_BASE_PATH;
        String frontPath = Constants.FR_ONT_IMGAE_BASE_PATH;
        String fileSavePath = serverPath + "/" + basePath + "/" + frontPath + "/";
        String imgName = "/" + basePath + "/" + frontPath + "/";
        if (!fileFront.isEmpty()) {
            try {
                // 获取文件后缀
                String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
                checkFile(suffix);

                if (suffix == null || "".equals(suffix)) {
                    throw new ServiceException("未知文件类型，上传失败！");
                }
                // 校验文件类型
                if (!verifyImageType(suffix)) {
                    throw new ServiceException("文件类型不符合要求，上传失败！");
                }
                // 重命名上传后的文件名
                String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + suffix;

                File targetFile = new File(fileSavePath, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                //保存
                try {
                    fileFront.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("img", fileSavePath + fileName);
                map.put("imgUrl", imgName + fileName);
                return map;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //身份证反面图片保存
    @Override
    @Transactional
    public Map<String, String> uploadBackImg(MultipartFile fileBack) {
        String uploadFileName = fileBack.getOriginalFilename();
        String serverPath = Global.getConfig("website_base_path");
        String basePath = Constants.TEN_ANTS_IMGAE_BASE_PATH;
        String backtPath = Constants.BA_CK_IMGAE_BASE_PATH;
        String fileSavePath = serverPath + "/" + basePath + "/" + backtPath + "/";
        String imgName = "/" + basePath + "/" + backtPath + "/";
        if (!fileBack.isEmpty()) {
            try {
                // 获取文件后缀
                String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
                checkFile(suffix);

                if (suffix == null || "".equals(suffix)) {
                    throw new ServiceException("未知文件类型，上传失败！");
                }
                // 校验文件类型
                if (!verifyImageType(suffix)) {
                    throw new ServiceException("文件类型不符合要求，上传失败！");
                }
                // 重命名上传后的文件名
                String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + suffix;

                File targetFile = new File(fileSavePath, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                //保存
                try {
                    fileBack.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("img", fileSavePath + fileName);
                map.put("imgUrl", imgName + fileName);
                return map;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //检验上传图片
    private void checkFile(String suffix) {

        //校验文件后缀
        if (StringUtils.isBlank(suffix)) {
            throw new ServiceException("未知文件类型，上传失败！");
        }
        //校验文件类型
        if (!verifyImageType(suffix)) {
            throw new ServiceException("文件类型不符合要求，上传失败！");
        }
    }

    /**
     * 校验文件类型 过滤合法的文件类型
     *
     * @param suffix
     * @return
     */
    private boolean verifyImageType(String suffix) {
        suffix = suffix.substring(1);
        String allowSuffixs = "gif,jpg,jpeg,bmp,png,ico";
        if (allowSuffixs.indexOf(suffix) == -1) {
            return false;
        }
        return true;
    }

    //审核不通过
    @Override
    public JsonResult nopassById(Integer applicationId, String remarks) {
        try {
            TenantsApplication tenants = tenatsApplicationMapper.selectByPrimaryKey(applicationId);
            TenantsApplication tenantsApplication = new TenantsApplication();
            tenantsApplication.setApplicationId(applicationId);
            tenantsApplication.setRemarks(remarks);
            tenantsApplication.setTenantStatus("03");
            tenantsApplication.setRegisterPhone(tenants.getRegisterPhone());
            tenantsApplication.setTenantName(tenants.getTenantName());
            tenantsApplication.setPrivince(tenants.getPrivince());
            tenantsApplication.setCity(tenants.getCity());
            tenantsApplication.setContactAddress(tenants.getContactAddress());
            tenantsApplication.setModifyTime(new Date());
            tenantsApplication.setAddTime(tenants.getAddTime());
            tenatsApplicationMapper.updateByPrimaryKey(tenantsApplication);
            return JsonResult.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
            return JsonResult.failure(ResultCode.DATA_ERROR);
        }
    }

    //二级域名和账户是否重复
    @Override
    public JsonResult tenantsCheck(String domain, String loginAccount) {
        try {
            List<TenantsApps> tenantsApps = tenantsAppsMapper.selectByDomain(domain);
//		System.out.println(tenantsApps.size());
            if (tenantsApps.size() > 0) {
                return JsonResult.failure(ResultCode.Tenants.TENANTS_DOMAIN_IS_EXIST);
            } else {
                List<TenantsUsers> tenantsUsers = TenantsUsersMapper.selectByLoginAccount(loginAccount);
                if (tenantsUsers.size() > 0) {
                    return JsonResult.failure(ResultCode.Tenants.LOGIN_ACCOUNT_IS_EXIST);
                }

            }
            return JsonResult.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(ErrorMsg.STAFF_QUERY_ERR, e);
            return JsonResult.failure(ResultCode.DATA_ERROR);
        }
    }

    public static void main(String[] args) {
        String str = "http://jiacer.oss-cn-shanghai.aliyuncs.com/2017090312005287619.jpg,";
        System.out.println(str.split(",")[0]);
    }
}
