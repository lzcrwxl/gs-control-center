package com.fbee.modules.validate;

import com.fbee.modules.core.utils.StringUtils;
import com.fbee.modules.form.StaffBankForm;
import com.fbee.modules.form.StaffJobForm;
import com.fbee.modules.form.TenantsJobsForm;
import com.fbee.modules.jsonData.basic.JsonResult;
import com.fbee.modules.jsonData.basic.ResultCode;

/** 
* @ClassName: TenantsJobsFormValidate 
* @Description: TODO
* @author 张杰
*/
public class TenantsJobsFormValidate {

	/**
	 * 校验新增or修改租户招聘信息
	 * @param staffBankForm
	 * @return
	 */
	public static JsonResult validFormInfo(TenantsJobsForm tenantsJobsForm) {
		
		if(StringUtils.isBlank(tenantsJobsForm.getPositionName())){
			return JsonResult.failure(ResultCode.Job.JOB_POSITION_NAME_IS_NULL);
		}
		if(StringUtils.isBlank(tenantsJobsForm.getServerType())){
			return JsonResult.failure(ResultCode.Job.JOB_SERVICE_TYPE_IS_NULL);
		}
		if(StringUtils.isBlank(tenantsJobsForm.getAge()+"")){
			return JsonResult.failure(ResultCode.Job.JOB_AGE_IS_NULL);
		}
		if(StringUtils.isBlank(tenantsJobsForm.getSalary())){
			return JsonResult.failure(ResultCode.Job.JOB_SLALRY_IS_NULL);
		}
		if(StringUtils.isBlank(tenantsJobsForm.getDemand())){
			return JsonResult.failure(ResultCode.Job.JOB_DEMAND_IS_NULL);
		}
		if(StringUtils.isBlank(tenantsJobsForm.getIsUsable())){
			return JsonResult.failure(ResultCode.Job.JOB_IS_USABLE_IS_NULL);
		}
		return JsonResult.success(null);
	}

}
