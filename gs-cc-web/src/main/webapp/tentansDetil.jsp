<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jquery.form.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="css/global.css"/>
		<link rel="stylesheet" type="text/css" href="css/tenantsDetil.css"/>
<title>门店审核页面</title>
<script type="text/javascript">

$(function(){
	//头部引入
	$('#header').load('header.jsp');
	var obj = GetRequest();
	var applicationId = obj.applicationId;
	var tenantStatus =  obj.tenantStatus;
	//审核不通过状态页面查看
	if(tenantStatus=="03"){
		$(".frame,.check").hide();
		$(".frame,.nopass").show();
		
		//信息显示
		 $.ajax({
		        url: publicPath + "/api/tenants/getTenantBase",
		        type: "post",
		        data: {
		        	applicationId:applicationId
		        	},
		        dataType: "json",
		        async:false,
		        success: function success(data) {
		            if(data.success){
		                if(data.code == 0){
		                	var dataJD = data.jsonData;
		                	
		                	if(dataJD.recommendId!=null && dataJD.recommendId!=""){
		                		var recommendId = dataJD.recommendId;
		                	}else{
		                		recommendId="无";
		                	}
		                	if(dataJD.remarks!=null && dataJD.remarks!=""){
		                		var remarks = dataJD.remarks;
		                	}else{
		                		remarks="";
		                	}
		                	
		                	$(".nopassTime span").text(dataJD.modifyTime);
		                	$(".nopassAdvice span").text(remarks);
		                	$(".nopassaddTime span").text(dataJD.addTime);
		                	$(".nopassRegisterPhone span").text(dataJD.registerPhone);
		                	$(".nopassTenantsName span").text(dataJD.tenantName);
		                	$(".nopassprivace span").text(dataJD.privince+dataJD.city);
		                	$(".nopassRecommendId span").text(recommendId);
		                	
		                	console.log(data.msg);
		                }else {
		                	console.log(data.msg);
		                }
		            }else if(data.code == 999010){
		                console.log(data.msg);
		                window.location.href='login.jsp';
		            }
		        }
		    }); 
	}
	//待审核状态页面查看和审核操作
	if(tenantStatus=="01"){
		$("#recommendId").hide();
		//银行code
			 $.ajax({
				 //publicPath+
	          url: publicPath+"/api/tenants/bankCode",
	          type: "post",
	          data: {},
	          dataType: "json",
	          async:false,
	          success: function success(data) {
	              if(data.success){
	                  if(data.code == 0){
	                  	var dataJD = data.jsonData;
	                  	var html = "";
	                  	$.each(dataJD, function(i,item) {
	                  		html += "<option  data-SYS_CODE='"+item.SYS_CODE+"' value='"+item.BANK_CODE+"'>"+item.BANK_NAME+"</option>"; 
	                  	});
	                  	$("select[name='bankCode']").html("<option value=''>请选择</option>"+html);
	                  	console.log(data.msg);
	                  }else{
	                      console.log(data.msg);
	                  }
	              }else{
	                  console.log(data.msg);
	              }
	          }
	      }); 
		
//		地区
		serviceProvice();
		$("select[name='serviceProvice']").change(function(){
			var pcode = $(this).val();
			serviceCity(pcode);
		});
		$("select[name='serviceCity']").change(function(){
			var pcode = $(this).val();
			serviceCounty(pcode);
		});
		
	  $.ajax({
		  //publicPath+
        url:  publicPath+"/api/tenants/getTenantBase",
        type: "post",
        data: {
        	applicationId:applicationId
        	},
        dataType: "json",
        async:false,
        success: function success(data) {
            if(data.success){
                if(data.code == 0){
                	var dataJD = data.jsonData;
                	console.log(dataJD);
                	if(dataJD.recommendId!=null && dataJD.recommendId!=""){
                		var recommendId = dataJD.recommendId;
                	}else{
                		recommendId="无";
                	}
                	$("input[name='addTime']").val(dataJD.addTime);
                	$("input[name='registerPhone']").val(dataJD.registerPhone);
                	$("input[name='applicationId']").val(dataJD.applicationId);
                	$("input[name='recommendId']").val(dataJD.recommendId);
                	$(".time span").text(dataJD.addTime);
                	$(".phone span").text(dataJD.registerPhone);
                	$(".sale-info span").text(recommendId);
                	$("input[name='tenantName']").val(dataJD.tenantName);
                	$("select[name='serviceProvice']").val(dataJD.privince);
   //             	以下为新增
                	$("input[name ='contactAddress']").val(dataJD.contactAddress);//详细地址
                	$("input[name = 'tenantsPhone']").val(dataJD.telephonenumber);//门店联系电话
                	$("input[name = 'contactName']").val(dataJD.agentName);//联系人姓名
                	$("input[name = 'contactPhone']").val(dataJD.registerPhone);//联系人手机号
                	//少账号性质
                	var accountProperties = dataJD.accountProperties
                	//alert(accountProperties);
                	$(function(){
                		if (accountProperties=="01"){
                			$("#personalAccount").prop('checked',true);
                			$("#companyAccount").removeAttr("checked");
                		}else if (accountProperties=="02"){
                			$("#companyAccount").prop('checked',true);
                			$("#personalAccount").removeAttr("checked");
                		}
                	});
                	//$("input[name = 'accountType1']")
                	$("input[name = 'bankName']").val(dataJD.accountName);//账号名称
                	$("input[name = 'cardNo']").val(dataJD.bankAccount);//银行账号
                	//选择银行
                	$("select[name='bankCode']").val(dataJD.openingBand);
                	var openingBand = dataJD.openingBand;
                	$.ajax({
                		//publicPath+
        		        url: publicPath+"/api/tenants/bankCode",
        		        type: "post",
        		        data: {pcode:openingBand},
        		        dataType: "json",
        		        async:false,
        		        success: function success(data) {
        		            if(data.success){
        		                if(data.code == 0){
        		                	var dataJD = data.jsonData;
        		                	var html = "";
        		                	var html = "";
        		                  	$.each(dataJD, function(i,item) {
        		                  		html += "<option  data-SYS_CODE='"+item.SYS_CODE+"' value='"+item.BANK_CODE+"'>"+item.BANK_NAME+"</option>"; 
        		                  	});
        		                  	$("select[name='bankCode']").html("<option value=''>请选择</option>"+html);
        		                	$("select[name='bankCode']").val(openingBand);
        		                	console.log(data.msg);
        		                }else{
        		                    console.log(data.msg);
        		                }
        		            }else{
        		                console.log(data.msg);
        		            }
        		        }
        		    }); 
                	$("input[name = 'bankSecName']").val(dataJD.openingBandBranch);//支行
                	$("input[name = 'companyName']").val(dataJD.businessName);//工商名称
                	$("input[name = 'licenceNo']").val(dataJD.socialInformationCode); //营业执照注册号
                	//营业执照
                	var licenceImg = dataJD.licence_img;
                	var piclicence = document.getElementById("licenceImg");
                	piclicence.src = licenceImg;
                	$(piclicence).siblings().attr("value",licenceImg)
                	$("#licenceImg").click(function(){ 
                		window.open(licenceImg);
                	});
                	
                	
                	$("input[name = 'agentName']").val(dataJD.agentName);//代理人姓名
                	$("input[name = 'idNumber']").val(dataJD.id_number);//代理人身份证号
                	//$("img[name = 'idPhotopositive']").val(dataJD.idPhotopositive);//身份证正面
                	//正面
                	var idPhotopositive = dataJD.idPhotopositive;
                	var picpositive = document.getElementById("idPhotopositive");
                	picpositive.src = idPhotopositive;
                	$(picpositive).next().attr("value",idPhotopositive);
                	
                	$("#idPhotopositive").click(function(){ 
                		window.open(idPhotopositive);
                	});
                	//反面
                	var idPhotonegative = dataJD.idPhotonegative;
                	var picnegative = document.getElementById("idPhotonegative");
                	picnegative.src = idPhotonegative;
                	$(picnegative).next().attr("value",idPhotonegative)
                	$("#idPhotonegative").click(function(){ 
                		window.open(idPhotonegative);
                	});
                	
                	var privince = dataJD.privince;
                	var city = dataJD.city;
                	var area = dataJD.area;
                	console.log(area)
                	$.ajax({
                		//publicPath+
        		        url: publicPath+"/api/common/getAreaData/c002",
        		        type: "post",
        		        data: {pcode:privince},
        		        dataType: "json",
        		        async:false,
        		        success: function success(data) {
        		            if(data.success){
        		                if(data.code == 0){
        		                	var dataJD = data.jsonData;
        		                	var html = "";
        		                	$.each(dataJD, function(i,item) {
        		                		html += "<option data-parentCode='"+item.parentCode+"' data-areaLevel='"+item.areaLevel+"' value='"+item.areaCode+"'>"+item.areaName+"</option>"; 
        		                	});
        		                	$("select[name='serviceCity']").html("<option value=''>请选择</option>"+html);
        		                	$("select[name='serviceCity']").val(city);
        		                	console.log(data.msg);
        		                }else{
        		                    console.log(data.msg);
        		                }
        		            }else{
        		                console.log(data.msg);
        		            }
        		        }
        		    }); 
                	 $.ajax({
                		 //publicPath+
        		        url: publicPath+"/api/common/getAreaData/c003",
        		        type: "post",
        		        data: {pcode:city},
        		        dataType: "json",
        		        async:false,
        		        success: function success(data) {
        		            if(data.success){
        		                if(data.code == 0){
        		                	var dataJD = data.jsonData;
        		                	var html = "";
        		                	$.each(dataJD, function(i,item) {
        		                		html += "<option data-parentCode='"+item.parentCode+"' data-areaLevel='"+item.areaLevel+"' value='"+item.areaCode+"'>"+item.areaName+"</option>"; 
        		                	});
        		                	$("select[name='serviceCounty']").html("<option value=''>请选择</option>"+html);
        		                	$("select[name='serviceCounty']").val(area);
        		                	console.log(data.msg);
        		                }else{
        		                    console.log(data.msg);
        		                }
        		            }else{
        		                console.log(data.msg);
        		            }
        		        }
        		    }); 
                	
                	console.log(data.msg);
                }else {
                	console.log(data.msg);
                }
            }else if(data.code == 999010){
                console.log(data.msg);
                window.location.href='login.jsp';
            }
        }
    }); 
//		区域选择
	  function serviceProvice(){
	
	  	$.ajax({
	  		//publicPath+
	          url: publicPath+"/api/common/getAreaData/c001",
	          type: "post",
	          data: {},
	          dataType: "json",
	          async:false,
	          success: function success(data) {
	              if(data.success){
	                  if(data.code == 0){
	                  	var dataJD = data.jsonData;
	                  	var html = "";
	                  	$.each(dataJD, function(i,item) {
	                  		html += "<option data-parentCode='"+item.parentCode+"' data-areaLevel='"+item.areaLevel+"' value='"+item.areaCode+"'>"+item.areaName+"</option>"; 
	                  	});
	                  	$("select[name='serviceProvice']").html("<option value=''>请选择</option>"+html);
	                  	console.log(data.msg);
	                  }else{
	                      console.log(data.msg);
	                  }
	              }else{
	                  console.log(data.msg);
	              }
	          }
	      });
	  }

	  function serviceCity(pcode){
	  	$.ajax({
	  		//publicPath+
	          url: publicPath+"/api/common/getAreaData/c002",
	          type: "post",
	          data: {pcode:pcode},
	          dataType: "json",
	          success: function success(data) {
	              if(data.success){
	                  if(data.code == 0){
	                  	var dataJD = data.jsonData;
	                  	var html = "";
	                  	$.each(dataJD, function(i,item) {
	                  		html += "<option data-parentCode='"+item.parentCode+"' data-areaLevel='"+item.areaLevel+"' value='"+item.areaCode+"'>"+item.areaName+"</option>"; 
	                  	});
	                  	$("select[name='serviceCity']").html("<option value=''>请选择</option>"+html);
	                  	console.log(data.msg);
	                  }else{
	                      console.log(data.msg);
	                  }
	              }else{
	                  console.log(data.msg);
	              }
	          }
	      });
	  }

	  function serviceCounty(pcode){
	  	$.ajax({
	  		//publicPath+
	          url: publicPath+"/api/common/getAreaData/c003",
	          type: "post",
	          data: {pcode:pcode},
	          dataType: "json",
	          success: function success(data) {
	              if(data.success){
	                  if(data.code == 0){
	                  	var dataJD = data.jsonData;
	                  	var html = "";
	                  	$.each(dataJD, function(i,item) {
	                  		html += "<option data-parentCode='"+item.parentCode+"' data-areaLevel='"+item.areaLevel+"' value='"+item.areaCode+"'>"+item.areaName+"</option>"; 
	                  	});
	                  	$("select[name='serviceCounty']").html("<option value=''>请选择</option>"+html);
	                  	console.log(data.msg);
	                  }else{
	                      console.log(data.msg);
	                  }
	              }else{
	                  console.log(data.msg);
	              }
	          }
	      });
	  }
	  
	 /*   $("input[type='file']").on("change",function(){
				var fileSize = this.files[0].size;
				var size = fileSize/1024;
				var filepath = $(this).val();
				var extStart = filepath.lastIndexOf(".");
				var ext = filepath.substring(extStart, filepath.length).toUpperCase();
				$(this).parents(".upload-sel").nextAll('.prompt_sp').remove();
				if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
                  alert("图片限于bmp,png,gif,jpeg,jpg格式");
                  $(this).val("");
                  return;
             	};
             	if (size > 500){
             		alert("上传的图片大小不能超过500K！");
             		$(this).val("");
             		return;
             	}else{
             	alert("上传成功！");
             	return;
             	}
			}); */
	   
	  
		//审核通过
		$(".pass").click(function(){
			//alert(bankCode);
			 $("i>input[type='text']").trigger("blur");
			// 下拉
			/* if($("#bankCode option:first-child").is(":selected")){
			onError('*请选择',$("#bankCode"));
			}else{
				onSuccess($("#bankCode"));
			}; */
			/* $("#fileLience,#fileFront,#fileBack").each(function(){
				var self= $(this).parents(".upload-sel");
				if($(this).val()==""){
					onError('*请上传',self);
				}else{
					onSuccess(self);
				}
			});  */
			if ($(".prompt_sp").length>0) {
				return;
			}; 
				 var domain = $(".client-info input[name='domain']").val();
				 var loginAccount = $(".client-info input[name='loginAccount']").val();
				 $.ajax({
				//	 publicPath+
				        url: publicPath+"/api/tenants/tenantsCheck",
				        type: "post",
				        data: {
				        	domain:domain,
				        	loginAccount:loginAccount
				        },
				        dataType: "json",
				        async:false,
				        success: function success(data) {
				            if(data.success){
				                if(data.code == 0){
				                	//alert(000);
						           // var callbackUrl = 'http://'+location.host+'/fbzkConsole_web/index.jsp';
						            var callbackUrl = 'http://'+location.host+'/fbzkConsole_web/index.jsp';
						            
						        	$("input[name='callbackUrl']").val(callbackUrl); 
						        	$("#check_m").submit();
				                	console.log(data.msg);
				                }
				            }
				            else if(data.code == '900001'){
				            	alert("二级域名已存在");
				            	return false;
				                console.log(data.msg);
				            }else if(data.code == '900002'){
				            	alert("管理员账号已存在");
				            	return false;
				                console.log(data.msg);
				            }
				        }
				    });  
			
			
		});
		// 表单验证
		 var regName = /^(\s)*([\u4E00-\u9FA5]{2,20})(\s)*$/;//姓名
		var regPhone = /^1[3|4|5|7|8]\d{9}$/;//手机号码
		var tensPhone = /^[0-9\-]+$/;//固定电话  数字和横杠
		var regURL = /^[A-Za-z0-9]+$/;//网址
		var regZi = /^[\u4e00-\u9fa5]/;//汉字
		var regNum = /^[0-9]*$/;//银行账号
		//var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;//身份证
        var reg2 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;//身份证
		$("input[type='text']").blur(function(){
			var self = $(this);
			var name = $(this).attr("name");
			switch (name){
				case "contactAddress":
					if (self.val()==""){
						onError('*请填写地址',self);
					} else if(self.val().length>50){
						onError('*不能超过50字',self);
					}else{
						onSuccess(self);
					}
					break;
				case "contactName":
					if (self.val() == ""){
						onError('*请填写姓名',self);
					} else if(!regName.test(self.val())) {
						onError('*请填写格式正确的姓名',self);
					}else {
						onSuccess(self);
					}
					break;
				case "contactPhone":
					if (self.val() == "") {
						onError('*请填写手机号码',self);
					} else if(self.val().length>11){
						onError('*不能超过11位',self);
					} else if(!regPhone.test(self.val())){
						onError('*请输入格式正确的手机号码',self);
					}else{
						onSuccess(self);
					}
					break;
				case "tenantsPhone":
					if (self.val() == "") {
						onError('*请填写门店联系电话',self);
					} else if(self.val().length>13){
						onError('*不能超过11位',self);
					} else if(!tensPhone.test(self.val())){
						onError('*请输入格式正确的门店联系电话',self);
					}else{
						onSuccess(self);
					}
					break;
				case "domain":
					if (self.val() == "") {
						onError('*请填写网站地址',self);
					} else if(self.val().length>20){
						onError('*不能超过20字',self);
					} else if(!regURL.test(self.val())){
						onError('*请输入格式正确的网址',self);
					}else{
						onSuccess(self);
					}
					break;
				case "loginAccount":
					if (self.val() == "") {
						onError('*请填写管理员账号',self);
					} else if(self.val().length>16||self.val().length<6){
						onError('*不能少于6字，超过16字',self);
					} else if(!regURL.test(self.val())){
						onError('*请输入格式正确的管理员账号',self);
					}else{
						onSuccess(self);
					}
					break;
				case "password":
					if (self.val() == "") {
						onError('*请填写密码',self);
					} else if(self.val().length>16||self.val().length<6){
						onError('*不能少于6字，超过16字',self);
					} else if(!regURL.test(self.val())){
						onError('*请输入格式正确的密码',self);
					}else{
						onSuccess(self);
					}
					break;
				case "bankName":
					if (self.val() == "") {
						onError('*请填写账号名称',self);
					} else if(self.val().length>50){
						onError('*不能超过50字',self);
					} else if(!regZi.test(self.val())){
						onError('*请输入格式正确的名称',self);
					}else{
						onSuccess(self);
					}
					break;
				case "cardNo":
					if (self.val() == "") {
						onError('*请填写银行账号',self);
					} else if(self.val().length>20){
						onError('*不能超过20字',self);
					} else if(!regNum.test(self.val())){
						onError('*请输入格式正确的账号',self);
					}else{
						onSuccess(self);
					}
					break;
				case "bankZ":
					if (self.val() == "") {
						onError('*请填写支行',self);
					} else if(self.val().length>30){
						onError('*不能超过30字',self);
					}else{
						onSuccess(self);
					}
					break;
				case "companyName":
					if (self.val() == "") {
						onError('*请填写工商名称',self);
					} else if(self.val().length>50){
						onError('*不能超过50字',self);
					} else if(!regZi.test(self.val())){
						onError('*请输入格式正确的名称',self);
					}else{
						onSuccess(self);
					}
					break;
				case "licenceNo":
					if (self.val() == "") {
						onError('*请填写营业执照注册号',self);
					} else if(self.val().length>18){
						onError('*不能超过18字',self);
					}else{
						onSuccess(self);
					}
					break;
				case "agentName":
					if (self.val() == ""){
						onError('*请填写姓名',self);
					} else if(!regName.test(self.val())) {
						onError('*请填写格式正确的姓名',self);
					}else {
						onSuccess(self);
					}
					break;
				case "idNumber":
					if (self.val() == ""){
						onError('*请填写身份证',self);
					} else if(!reg2.test(self.val())) {
						onError('*请填写格式正确的身份证',self);
					}else {
						onSuccess(self);
					}
					break;
				
				default:
					break;
			}
		});
        
		 function onError (msg,self) {
			if(self.nextAll().hasClass('prompt_sp')){
				self.nextAll('.prompt_sp').text(msg);
				return;
			}
			self.parent().append("<span class='prompt_sp'>"+msg+"</span>");
		};

		function onSuccess(self) {
			self.nextAll('.prompt_sp').remove();	
		}; 
		
		//审核不通过
		 $(".nopass").click(function(){
				$("#layer,.cancel").show();
			});
			$(".cancel_bt").click(function(){
				$("#layer,.cancel").hide();
			});
			$(".ok_bt").click(function(){
				var remarks = $("#reason").val();
				if(remarks==""){
					$(".promt").text("请输入原因");
					return;
				}else if(remarks.length<5){
					$(".promt").text("原因不少于5个字");
					return;
				}else{
					$(".promt").text("");
					 $.ajax({
						 //publicPath+
						url: publicPath+"/api/tenants/nopass",
						type: "post",
						data: {
						remarks:remarks,
						applicationId:applicationId
						},
						dataType: "json",
						success: function success(data) {
							if(data.success){
								if(data.code == 0){
									window.location.href="index.jsp";
								}else{
									console.log(data.msg);
								}
							}else{
								console.log(data.msg);
							}
						}
					}); 
				}
			});
	}
		//创建新门店
		if(tenantStatus==null&&applicationId==null){
			$(".reservation-info .rember").hide();
			$(".bottom-navi .pass").hide();
			$(".bottom-navi .nopass").hide();
			$(".bottom-navi .cancels").show();
			$(".bottom-navi .setUp").show();
			$(".check .paths").hide();
			$(".check .newpaths").show();
			$(".sale-info span").hide();
			$(".photoshow").hide();
			$(".uploadphoto").show();
			
			//银行code
			 $.ajax({
				 //publicPath+
	          url: publicPath+"/api/tenants/bankCode",
	          type: "post",
	          data: {},
	          dataType: "json",
	          async:false,
	          success: function success(data) {
	              if(data.success){
	                  if(data.code == 0){
	                  	var dataJD = data.jsonData;
	                  	var html = "";
	                  	$.each(dataJD, function(i,item) {
	                  		html += "<option  data-SYS_CODE='"+item.SYS_CODE+"' value='"+item.BANK_CODE+"'>"+item.BANK_NAME+"</option>"; 
	                  	});
	                  	$("select[name='bankCode']").html("<option value=''>请选择</option>"+html);
	                  	console.log(data.msg);
	                  }else{
	                      console.log(data.msg);
	                  }
	              }else{
	                  console.log(data.msg);
	              }
	          }
	      }); 
			//地区
			serviceProvice();
			$("select[name='serviceProvice']").change(function(){
				var pcode = $(this).val();
				serviceCity(pcode);
			});
			$("select[name='serviceCity']").change(function(){
				var pcode = $(this).val();
				serviceCounty(pcode);
			});
//			区域选择
			function serviceProvice(){
				$.ajax({
					//publicPath+
			        url: publicPath+"/api/common/getAreaData/c001",
			        type: "post",
			        data: {},
			        dataType: "json",
			        async:false,
			        success: function success(data) {
			            if(data.success){
			                if(data.code == 0){
			                	var dataJD = data.jsonData;
			                	var html = "";
			                	$.each(dataJD, function(i,item) {
			                		html += "<option data-parentCode='"+item.parentCode+"' data-areaLevel='"+item.areaLevel+"' value='"+item.areaCode+"'>"+item.areaName+"</option>"; 
			                	});
			                	$("select[name='serviceProvice']").html("<option value=''>请选择</option>"+html);
			                	console.log(data.msg);
			                }else{
			                    console.log(data.msg);
			                }
			            }else{
			                console.log(data.msg);
			            }
			        }
			    });
			}

			function serviceCity(pcode){
				$.ajax({
					//publicPath+
			        url: publicPath+"/api/common/getAreaData/c002",
			        type: "post",
			        data: {pcode:pcode},
			        dataType: "json",
			        success: function success(data) {
			            if(data.success){
			                if(data.code == 0){
			                	var dataJD = data.jsonData;
			                	var html = "";
			                	$.each(dataJD, function(i,item) {
			                		html += "<option data-parentCode='"+item.parentCode+"' data-areaLevel='"+item.areaLevel+"' value='"+item.areaCode+"'>"+item.areaName+"</option>"; 
			                	});
			                	$("select[name='serviceCity']").html("<option value=''>请选择</option>"+html);
			                	console.log(data.msg);
			                }else{
			                    console.log(data.msg);
			                }
			            }else{
			                console.log(data.msg);
			            }
			        }
			    });
			}

			function serviceCounty(pcode){
				$.ajax({
					//publicPath+
			        url: publicPath+"/api/common/getAreaData/c003",
			        type: "post",
			        data: {pcode:pcode},
			        dataType: "json",
			        success: function success(data) {
			            if(data.success){
			                if(data.code == 0){
			                	var dataJD = data.jsonData;
			                	var html = "";
			                	$.each(dataJD, function(i,item) {
			                		html += "<option data-parentCode='"+item.parentCode+"' data-areaLevel='"+item.areaLevel+"' value='"+item.areaCode+"'>"+item.areaName+"</option>"; 
			                	});
			                	$("select[name='serviceCounty']").html("<option value=''>请选择</option>"+html);
			                	console.log(data.msg);
			                }else{
			                    console.log(data.msg);
			                }
			            }else{
			                console.log(data.msg);
			            }
			        }
					});
				}
			
			//取消返回
			$(".cancels").click(function(){
				window.location.href='tentansManagement.jsp';
			});
			//创建提交表单
			$(".setUp").click(function(){
				 $("i>input[type='text']").trigger("blur");
				// 下拉
				/* if($("#bankCode option:first-child").is(":selected")){
				onError('*请选择',$("#bankCode"));
				}else{
					onSuccess($("#bankCode"));
				}; */
				$("#fileLience,#fileFront,#fileBack").each(function(){
					var self= $(this).parents(".upload-sel");
					if($(this).val()==""){
						onError('*请上传',self);
					}else{
						onSuccess(self);
					}
				});
				if ($(".prompt_sp").length>0) {
					return;
				}; 
					 var domain = $(".client-info input[name='domain']").val();
					 var loginAccount = $(".client-info input[name='loginAccount']").val();
					 $.ajax({
						 //publicPath+
					        url: publicPath+"/api/tenants/tenantsCheck",
					        type: "post",
					        data: {
					        	domain:domain,
					        	loginAccount:loginAccount
					        },
					        dataType: "json",
					        async:false,
					        success: function success(data) {
					            if(data.success){
					                if(data.code == 0){
							         //   var callbackUrl = 'http://'+location.host+'/fbzkConsole_web/index.jsp';
							            var callbackUrl = 'http://'+location.host+'/fbzkConsole_web/index.jsp';
							        	$("input[name='callbackUrl']").val(callbackUrl); 
							        	$("#check_m").submit();
					                	console.log(data.msg);
					                }
					            }
					            else if(data.code == '900001'){
					            	alert("二级域名已存在");
					            	return false;
					                console.log(data.msg);
					            }else if(data.code == '900002'){
					            	alert("管理员账号已存在");
					            	return false;
					                console.log(data.msg);
					            }
					        }
					    });  
				
				
			});
			 
			 
			   /* $("input[type='file']").on("change",function(){
						var fileSize = this.files[0].size;
						var size = fileSize/1024;
						var filepath = $(this).val();
						var extStart = filepath.lastIndexOf(".");
						var ext = filepath.substring(extStart, filepath.length).toUpperCase();
						$(this).parents(".upload-sel").nextAll('.prompt_sp').remove();
						if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
		                  alert("图片限于bmp,png,gif,jpeg,jpg格式");
		                  $(this).val("");
		                  return;
		             	};
		             	if (size > 500){
		             		alert("上传的图片大小不能超过500K！");
		             		$(this).val("");
		             		return;
		             	}else{
		                 	alert("上传成功！");
		                 	return;
		                 	};
					}); */
			   
			   function onError (msg,self) {
					if(self.nextAll().hasClass('prompt_sp')){
						self.nextAll('.prompt_sp').text(msg);
						return;
					}
					self.parent().append("<span class='prompt_sp'>"+msg+"</span>");
				};

				function onSuccess(self) {
					self.nextAll('.prompt_sp').remove();	
				}; 
				
				// 表单验证
				 var regName = /^(\s)*([\u4E00-\u9FA5]{2,20})(\s)*$/;//姓名
				var regPhone = /^1[3|4|5|7|8]\d{9}$/;//手机号码
				var tensPhone = /^[0-9\-]+$/;//固定电话
				var regURL = /^[A-Za-z0-9]+$/;//网址
				var regZi = /^[\u4e00-\u9fa5]/;//汉字
				var regNum = /^[0-9]*$/;//银行账号
				//var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;//身份证
		        var reg2 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;//身份证
				$("input[type='text']").blur(function(){
					var self = $(this);
					var name = $(this).attr("name");
					switch (name){
						case "contactAddress":
							if (self.val()==""){
								onError('*请填写地址',self);
							} else if(self.val().length>50){
								onError('*不能超过50字',self);
							}else{
								onSuccess(self);
							}
							break;
						case "tenantsPhone":
							if (self.val() == "") {
								onError('*请填写门店联系电话',self);
							} else if(self.val().length>13){
								onError('*不能超过13位',self);
							} else if(!tensPhone.test(self.val())){
								onError('*请输入格式正确的门店联系电话',self);
							}else{
								onSuccess(self);
							}
							break;
						case "contactName":
							if (self.val() == ""){
								onError('*请填写姓名',self);
							} else if(!regName.test(self.val())) {
								onError('*请填写格式正确的姓名',self);
							}else {
								onSuccess(self);
							}
							break;
						case "contactPhone":
							if (self.val() == "") {
								onError('*请填写手机号码',self);
							} else if(self.val().length>11){
								onError('*不能超过11位',self);
							} else if(!regPhone.test(self.val())){
								onError('*请输入格式正确的手机号码',self);
							}else{
								onSuccess(self);
							}
							break;
						case "domain":
							if (self.val() == "") {
								onError('*请填写网站地址',self);
							} else if(self.val().length>20){
								onError('*不能超过20字',self);
							} else if(!regURL.test(self.val())){
								onError('*请输入格式正确的网址',self);
							}else{
								onSuccess(self);
							}
							break;
						case "loginAccount":
							if (self.val() == "") {
								onError('*请填写管理员账号',self);
							} else if(self.val().length>16||self.val().length<6){
								onError('*不能少于6字，超过16字',self);
							} else if(!regURL.test(self.val())){
								onError('*请输入格式正确的管理员账号',self);
							}else{
								onSuccess(self);
							}
							break;
						case "password":
							if (self.val() == "") {
								onError('*请填写密码',self);
							} else if(self.val().length>16||self.val().length<6){
								onError('*不能少于6字，超过16字',self);
							} else if(!regURL.test(self.val())){
								onError('*请输入格式正确的密码',self);
							}else{
								onSuccess(self);
							}
							break;
						case "bankName":
							if (self.val() == "") {
								onError('*请填写账号名称',self);
							} else if(self.val().length>50){
								onError('*不能超过50字',self);
							} else if(!regZi.test(self.val())){
								onError('*请输入格式正确的名称',self);
							}else{
								onSuccess(self);
							}
							break;
						case "cardNo":
							if (self.val() == "") {
								onError('*请填写银行账号',self);
							} else if(self.val().length>20){
								onError('*不能超过20字',self);
							} else if(!regNum.test(self.val())){
								onError('*请输入格式正确的账号',self);
							}else{
								onSuccess(self);
							}
							break;
						case "bankZ":
							if (self.val() == "") {
								onError('*请填写支行',self);
							} else if(self.val().length>30){
								onError('*不能超过30字',self);
							}else{
								onSuccess(self);
							}
							break;
						case "companyName":
							if (self.val() == "") {
								onError('*请填写工商名称',self);
							} else if(self.val().length>50){
								onError('*不能超过50字',self);
							} else if(!regZi.test(self.val())){
								onError('*请输入格式正确的名称',self);
							}else{
								onSuccess(self);
							}
							break;
						case "licenceNo":
							if (self.val() == "") {
								onError('*请填写营业执照注册号',self);
							} else if(self.val().length>18){
								onError('*不能超过18字',self);
							}else{
								onSuccess(self);
							}
							break;
						case "agentName":
							if (self.val() == ""){
								onError('*请填写姓名',self);
							} else if(!regName.test(self.val())) {
								onError('*请填写格式正确的姓名',self);
							}else {
								onSuccess(self);
							}
							break;
						case "idNumber":
							if (self.val() == ""){
								onError('*请填写身份证',self);
							} else if(!reg2.test(self.val())) {
								onError('*请填写格式正确的身份证',self);
							}else {
								onSuccess(self);
							}
							break;
						
						default:
							break;
					}
				});
		       
		}
});
	//截取参数
	function GetRequest(){
	   var url = location.search; 
	   var theRequest = new Object();   
	   if (url.indexOf("?") != -1) {   
	      var str = url.substr(1);   
	      strs = str.split("&");   
	      for(var i = 0; i < strs.length; i ++) {   
	         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);   
	      }   
	   }   
	   return theRequest; 
	}
	
	function bigger(e){
		console.log(e);
		$(e).addClass("big").removeClass("small");
	}
	function smaller(e){
		console.log(e);
		$(e).addClass("small").removeClass("big");
	}
	
	//function change(e){
			
	//}
	function upLoad(e){
		  console.log("suc");
		    var file = e.files[0];
		    var objUrl = getObjectURL(e.files[0]);
		    var fileSize = e.files[0].size;
		    var size = fileSize / (1024 * 1024);
		    var filepath = $(e).val();
		    if (size > 2) {
		      alert("上传的图片大小不能超过2M！");
		      $(e).val("");
		      return;
		    }
		    ;
		    if (objUrl) {
		      var that = $(e).parents().siblings('img');
		      var formData = new FormData();
		      formData.append('file', file);
		      uploadImage(formData, that);
		    };
	}
	
	
	//获取路径
	function getObjectURL(file) {
	  var url = null;
	  if (window.createObjectURL != undefined) { // basic
	    url = window.createObjectURL(file);
	  } else if (window.URL != undefined) { // mozilla(firefox)
	    url = window.URL.createObjectURL(file);
	  } else if (window.webkitURL != undefined) { // webkit or chrome
	    url = window.webkitURL.createObjectURL(file);
	  }
	  return url;
	}
	//上传图片url: "http://test.jiacersxy.com:8000/image/upload"
	function uploadImage(formData, that) {
	  $.ajax({
	    url: "http://common.jiacedu.com/image/upload",
	    type: "post",
	    data: formData,
	    async: false,
	    cache: false,
	    contentType: false,
	    processData: false,
	    success: function success(data) {
	      if (data.status == 0) {
	        console.log(data)
	        var url = data.result.url;
	        that.attr('src', url)
	        that.addClass("small");
	      	console.log(that);
	      	that.siblings("input").attr("value",url)
	      	//$("#imgValue").attr("value",url)
	      } else {
	        console.log(data.message);
	      }
	    },
	    error: function (err) {
	      console.log(err);
	    }
	  });
	}

	
	
	
</script>
</head>
<body>
<!-- 头部 -->
<div id="header" class="clearfix"></div>
	<!-- 左侧导航栏 -->
	<div class="naviLeft">
		<ul>
			<li class="nl_on"><a class="ddgl" href="index.jsp"><em></em>申请入驻门店</a></li>
			<li ><a class="yygl" href="tentansManagement.jsp"><em></em>已入驻门店</a></li>
		</ul>
	</div>
<div class="orderDetails">
	<div class="infoCard">
		<div class="frame">
			<!-- 待审核页面显示 -->
			<div class="check">
				<p class="paths">当前页面：申请入驻门店 > 门店审核</p>
				<p class="newpaths" style="display:none">当前页面：已入驻门店 > 创建新门店</p>
				<!-- 基本信息开始 -->
				<form id="check_m" action="/fbzkConsole_web/api/tenants/tenantsPass"  method="post">
				<!--        /api/tenants/tenantsPass -->
				<!-- 隐藏 -->
				<input name="addTime"       type="hidden" value=""/>
				<input name="registerPhone" type="hidden" value=""/>
				<input name="applicationId" type="hidden" value=""/>
				<!--  <input name="recom"   type="" value=""/>  -->
				<input name="callbackUrl"   type="hidden" value="" />
				<div id="reservation-info">
					<div class="clearfix">
						<p class="title"><i>基本信息</i></p>
					</div>
					<div class="reservation-info">
						<p class="rember">
							<i class="time">
								<label>申请时间</label>
								<span style="" class="addTime"></span>
							</i>
							<i class="phone">
								<label>申请人手机号</label>
								<span style="" class="registerPhone"></span>
							</i>
						</p>
						<p>
							<i class="">
								<label>门店名称</label>
								<input name="tenantName" value=""/>
							</i>
						</p>
						<p>
							<i class="source">
								<label>所在区域</label>
								<select name="serviceProvice">
									<option value="">请选择省</option>
								</select>
								<select name="serviceCity">
									<option value="">请选择市</option>
								</select>
								<select name="serviceCounty">
									<option value="">请选择区</option>
								</select>
							</i>
						</p>	
						<p>
							<i class="">
								<label>详细地址</label>
								<input type="text" name="contactAddress" />
							</i>
							<i class="">
								<label>门店联系电话</label>
								<input type="text" name="tenantsPhone" />
							</i>
						</p>
						<p>
							<i class="">
								<label>联系人姓名</label>
								<input type="text" name="contactName" />
							</i>
							<i class="">
								<label>联系人手机号</label>
								<input type="text" name="contactPhone" />
							</i>
						</p>
				</div>
				</div>
				<!-- 账号信息开始 -->
				<div id="client-info">
					<div class="clearfix">
						<p class="title"><i>账号信息</i></p>
					</div>
					<div class="client-info">
						<p>
							<i class="">
								<label>网站地址</label>
								home.jiacer.com<input type="text" name="domain" />
							</i>
						</P>
						<p>
							<i class="">
								<label>管理员账号</label>
								<input type="text" name="loginAccount" />
							</i>
						</p>
						<p>
							<i class="">
								<label>登陆密码</label>
								<input type="text" name="password" />
							</i>
						</p>
						<p>
								<label>Logo:</label>
								<span class="limit">(尺寸不能超过<i style="color:#FF411B;">200*200</i>,大小<i style="color: #FF411B;">500K</i>以内)</span>
								<span class="upload-sel">
									<a style="width: 64px;">选择上传<input type="file"  id="fileLogo" name="fileLogo02" value="" onchange="upLoad(this)" /></a>
									<img  src="">
									<input type="hidden" value="" id="imgValue" name="fileLogo"/>
								</span>
						</p>
					</div>
					</div>
				<!-- 银行结算信息 -->
				  <div id="bank-info">
					<div class="clearfix">
						<p class="title"><i>银行结算信息:</i></p>
					</div>
					<div class="bank-info">
						<p>
							<i class="">
								<label>账号性质</label>
								<label><input type="radio" name="accountType1"  value="01"  id="personalAccount"/>个人账号</label>
								<label><input type="radio" name="accountType1" value="02"  id="companyAccount"/>企业账号</label>
							</i>
						</P>
						<p>
							<i class="">
								<label>账号名称</label>
								<input type="text" name="bankName" style="width: 180px;" />
							</i>
						</p>
						<p>
							<i class="">
								<label>银行账号</label>
								<input  type="text" name="cardNo" style="width: 180px;" />
							</i>
						</p>
						<p>
							<i class="">
								<label>所属银行</label>
								<select name="bankCode">
									<option value="">请选择银行</option>
								</select>
								<input type="text" name="bankSecName" />
							</i>
						</p>
					</div>
				</div>
			<!-- 主体信息 -->
				<div id="base-info">
					<div class="clearfix">
						<p class="title"><i>主体信息登记</i></p>
					</div>
					<div class="base-info">
						<p>
							<i class="">
								<label>工商名称</label>
								<input type="text"  name="companyName" style="width: 180px;" />
							</i>
						</P>
						<p>
							<i class="">
								<label>营业执照注册号</label>
								<input type="text" name="licenceNo" style="width: 180px;" />
							</i>
						</p>
						<p>
							<label>上传营业执照</label>
							<i>
							<span class = "photoshow">
							<img name="licenceImg" id = "licenceImg" class="small" />
							<input type="hidden" value="" class="imgValue" name="fileLience"/>
								</span>
								<span class = "uploadphoto" style="display:none"><span class="limit">(大小<i style="color: #FF411B;">500K</i>以内)</span>
								<span class="upload-sel">
									<a style="width: 64px;">选择上传<input type="file" id="fileLience" name="fileLience" value="" onchange="upLoad(this)" /></a>
									<img  src="">
									<input type="hidden" value="" id="imgValue" name="fileLience"/>
								</span></span>
								</i>
						</p>
						<p>
							<i class="tenantStatus">
								<label>代理人姓名</label>
								<input type="text" name="agentName" style="width: 180px;" />
							</i>
						</p>
						<p>
							<i class="tenantStatus">
								<label>代理人身份证号</label>
								<input type="text" name="idNumber" style="width: 180px;" />
							</i>
						</p>
						<p>
							<label>代理人身份证照片</label>
							<i>
							<span class = "photoshow">
							<label>正面照</label>
							<img  name="idPhotopositive" class="small" id = "idPhotopositive"  />
							<input type="hidden" value="" class="imgValue" name="fileFront"/>
							<label>背面照</label>
							<img  name="idPhotonegative" id ="idPhotonegative"  class="small" />
							<input type="hidden" value="" class="imgValue" name="fileBack"/>
							</span>
							
							<span class = "uploadphoto" style="display:none"><span class="limit">(正面,大小<i style="color: #FF411B;">500K</i>以内)</span>
								<span class="upload-sel">
									<a style="width: 64px;">选择上传<input type="file" id="fileFront" name="fileFront" value="" onchange="upLoad(this)" /></a>
									<img  src="">
									<input type="hidden" value="" id="imgValue" name="fileFront"/>
								</span>
								<span class="limit">(反面,大小<i style="color: #FF411B;">500K</i>以内)</span>
								<span class="upload-sel">
									<a style="width: 64px;">选择上传<input type="file" id="fileBack" name="fileBack" value="" onchange="upLoad(this)" /></a>
									<img  src="">
									<input type="hidden" value="" id="imgValue" name="fileBack"/>
								</span>
								</span>
								</i>
						</p>
					</div>
				</div>
				<!-- 销售信息 -->
				<div id="sale-info">
					<div class="clearfix">
						<p class="title"><i>销售信息:</i></p>
					</div>
					<div class="sale-info">
						<p>
							<i class="">
								<label>推荐人ID</label>
								<span>无</span>
								<input type="text" id="recommendId" name="recommendId" />
							</i>
						</P>
						<p>
							<i class="">
								<label>销售人姓名</label>
								<input type="text" name="salesPerson" />
							</i>
						</p>
					</div>
				</div>
				<!-- 按钮 -->
					<div class="bottom-navi">
						<ul class="clearfix">
							<li>
								<input type="button" value="审核通过" class="pass" style="border:none;background-color: initial"/>
								<a class="cancels" style="display:none">取消</a>
							</li>
							<li>
								<input type="button" value="创建" class="setUp" style="border:none;background-color: initial;display:none"/> 
								<a class="nopass" onclick=";">审核不通过</a>
							</li>
						</ul>
					</div>
					</form>
				</div>
			<!-- 审核不通过显示 -->
			<div class="nopass" style="display:none">
			<p class="nopaths">当前页面：申请入驻门店 > 不通过门店</p>
			<!-- 审核信息 -->
				<div id="nopassInfo">
					<div class="clearfix">
						<p class="title"><i>审核信息:</i></p>
					</div>
					<div class="nopass-info">
						<p>
							<i class="nopassTime">
								<label>审核时间</label>
								<span></span>
							</i>
						</P>
						<p>
							<i class="nopassAdvice">
								<label>审核意见</label>
								<span></span>
							</i>
						</p>
					</div>
				</div>
				<!-- 基本信息 -->
				<div id="nopassBase">
					<div class="clearfix">
						<p class="title"><i>基本信息:</i></p>
					</div>
					<div class="nopassBase-info">
						<p>
							<i class="nopassaddTime">
								<label>申请时间</label>
								<span></span>
							</i>
							<i class="nopassRegisterPhone">
								<label>申请人手机号</label>
								<span></span>
							</i>
						</P>
						<p>
							<i class="nopassTenantsName">
								<label>门店名称</label>
								<span></span>
							</i>
							<i class="nopassprivace">
								<label>所在区域</label>
								<span></span>
							</i>
						</p>
						<p>
							<i class="nopassRecommendId">
								<label>推荐人ID</label>
								<span></span>
							</i>
						</p>
					</div>
				</div>
				<!-- 按钮 -->
				<div class="bottom-navi" style="height: 222px;">
						<ul class="clearfix">
							<li>
								<a class="back" onclick="history.back();">返回</a>
							</li>
						</ul>
					</div>
				
			</div>
		</div>
	</div>
</div>
		<div id="layer">
		</div>
		<div class="cancel">
			<h1>不通过理由</h1>
			<textarea id="reason" placeholder="不通过理由不少于5个字"></textarea>
			<p class="promt"></p>
			<div class="submit"><span class="ok_bt">确认</span><span class="cancel_bt">取消</span></div>
		</div>
</body>
</html>