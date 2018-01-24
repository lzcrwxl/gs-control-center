<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jquery.qrcode.min.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="css/global.css"/>
		<link rel="stylesheet" type="text/css" href="css/tenantsManagementDetil.css"/>
<title>门店详情</title>
<script type="text/javascript">
$(function(){
	var width = $(".frame_right").outerWidth();
	var left = $(".frame_right").offset().left;
	$(".bottom_menu").css({left:left,width:width});
	
	//头部引入
	$('#header').load('header.jsp');
	var obj = GetRequest();
	var tenantId = obj.tenantId;
	$("#tenantId").val(obj.tenantId);
	
		//银行code
		 $.ajax({
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
	
	 //省
	  serviceProvice();
		$("select[name='serviceProvice']").change(function(){
			var pcode = $(this).val();
			serviceCity(pcode);
		});
		
		$("select[name='serviceCity']").change(function(){
			var pcode = $(this).val();
			serviceCounty(pcode);
		});
	//信息显示
		 $.ajax({
		        url:  publicPath+"/api/tenants/tenantsAppInfo",
		        type: "post",
		        data: {
		        	tenantId:tenantId
		        	},
		        dataType: "json",
		        async:false,
		        success: function success(data) {
		            if(data.success){
		                if(data.code == 0){
		                	var dataJD = data.jsonData[0];
		                	
		                	
		                	if(dataJD.privince!=null){
		                		var serviceProviceCode=dataJD.privince.split(",")[0];
		                		var serviceProvice=dataJD.privince.split(",")[1];
		                	}
		                	if(dataJD.city!=null){
		                		var serviceCityCode=dataJD.city.split(",")[0];
		                		var serviceCity=dataJD.city.split(",")[1];
		                	}
		                	if(dataJD.area!=null&&dataJD.area!=""){
		                		var serviceCountyCode=dataJD.area.split(",")[0];
		                		var serviceCounty=dataJD.area.split(",")[1];
		                	} else {
		                		serviceCountyCode="";
		                		serviceCounty="";
		                	}
		                	if(dataJD.dueDate!=null&&dataJD.dueDate!=""){
		                		var dueDate=dataJD.dueDate;
		                	}else{
		                		dueDate="永久";
		                	}
		                	if(dataJD.accountType=="02"){
		                		$("input[name='accountType'][value=02]").attr("checked",true); 
		                		$("#accountType").text("企业账户");
		                	}else{
		                		$("#accountType").text("个人账户");
		                	}
		                	if(dataJD.tenantStatus!=null&&dataJD.tenantStatus!=""){
		                		var tenantStatusCode = dataJD.tenantStatus.split(",")[0];
		                		var tenantStatus = dataJD.tenantStatus.split(",")[1];
		                	}
		                	if(tenantStatusCode=="02"){
		                		//alert(11);
		                		var tenantStatus="冻结";
		                		$(".unlock").show();
		                		$(".blocked").hide();
		                	}
		                	$("#tenantName .beforeEditing").html(dataJD.tenantName+"&nbsp;&nbsp;(<i class='grey'>有效期至</i>&nbsp;&nbsp;"+dueDate+")");
		                	$("#tenantName .afterEditing input").val(dataJD.tenantName);
		                	
		                	$("#contactAddress .beforeEditing").text(serviceProvice+serviceCity+serviceCounty+dataJD.contactAddress);
		                	$("#contactAddress input").val(dataJD.contactAddress);
		                	
		                	$("#contactAddress .afterEditing select[name='serviceProvice']").val(serviceProviceCode);
		                	$("#contactAddress .afterEditing select[name='serviceCounty']").val(serviceCountyCode);
		                	
		                	$("#contactName .beforeEditing").text(dataJD.contactName);
		                	$("#contactName .afterEditing input").val(dataJD.contactName);
		                	
		                	$("#tenantsPhone .beforeEditing").text(dataJD.tenantsPhone);
		                	$("#tenantsPhone .afterEditing input").val(dataJD.tenantsPhone);
		                	
		                	
		                	if(dataJD.refereeId!=null&&dataJD.tenantStatus!=""){
		                		$("#refereeId .beforeEditing").text(dataJD.refereeId);
		                	}else{
		                		$("#refereeId .beforeEditing").text("无");
		                		$("#refereeId .afterEditing").text("无");
		                	}
		                	
		                	$("#contactPhone .beforeEditing").text(dataJD.contactPhone);
		                	$("#contactPhone .afterEditing input").val(dataJD.contactPhone);
		                	
		                	$("#domain .do").html(dataJD.domain);
		                	/* $("#domain .afterEditing input").val(dataJD.domain); */
		                	
		                	$("#recommendId .beforeEditing").text(dataJD.recommendId);
		                	$("#recommendId .afterEditing input").val(dataJD.recommendId);
		                	
		                	$(".totalAmount").text(dataJD.totalAmount);
		                	$(".frozenAmount").text(dataJD.frozenAmount);
		                	$(".availableAmount").text(dataJD.availableAmount);
		                	
		                	$("#bankName .beforeEditing").text(dataJD.bankName);
		                	$("#bankName .afterEditing input").val(dataJD.bankName);
		                	
		                	$("#cardNo .beforeEditing").text(dataJD.cardNo);
		                	$("#cardNo .afterEditing input").val(dataJD.cardNo);
		                	$("#bankCode").text(dataJD.bankCode+dataJD.bankSecName);
		                	$(".applyTime").text(dataJD.applyTime);
		                	$(".companyName").text(dataJD.companyName);
		                	$(".applyTime").text(dataJD.applyTime);
		                	$(".licenceNo").text(dataJD.licenceNo);
		                	$(".agentName").text(dataJD.agentName);
		                	$(".idNumber").text(dataJD.idNumber);
		                	$(".recommendId").text(dataJD.recommendId);
		                	
		                	$("#salesPerson .beforeEditing").text(dataJD.salesPerson);
		                	$("#salesPerson .afterEditing input").val(dataJD.salesPerson);
		                	
		                	$("#memberType").text(dataJD.memberType);
		                	
		                	$("#tenantStatus").text(tenantStatus);
		                	$("#tenantStatusCode").val(tenantStatusCode);
		                	
		                	$("#dueDate").text(dueDate);
		                	
		                	$("#loginAccount").text(dataJD.loginAccount);
		                	$(".reset_lay #loginAccount").text(dataJD.loginAccount);
		                	
		                	$("#bank input").val(dataJD.bankSecName);
		                	$("select[name='bankCode']").val(dataJD.bankCodes);
		                	
		                	$("#logo").attr("src",dataJD.websiteLogo);
		                	$("#licence").attr("src",dataJD.licenceImg);
		                	$("#idcardFront").attr("src",dataJD.idcardFrontImg);
		                	$("#idcardBack").attr("src",dataJD.idcardBackImg);
		                	var websiteUrl = "http://"+dataJD.domain;
		                	//alert(websiteUrl);
		               	  	$(".wap").qrcode({ 
		               		    render: "canvas", //table方式 
		               		    width: 180, //宽度 
		               		    height:180, //高度 
		               		    text: websiteUrl //任意内容 
		               		});  
		               	  	
		               	 var privince = serviceProviceCode;
		                	var city = serviceCityCode;
		                	$.ajax({
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
	
		
		 $("input[type='file']").on("change",function(){
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
          	};
			});
	
		 function onError (msg,self) {
				if(self.nextAll().hasClass('prompt_sp')){
					self.nextAll('.prompt_sp').text(msg);
					return;
				}
				self.parent().append("<span class='prompt_sp' style='color:red;margin-left:10px'>"+msg+"</span>");
			};

			function onSuccess(self) {
				self.nextAll('.prompt_sp').remove();	
			}; 
			 var regName = /^(\s)*([\u4E00-\u9FA5]{2,20})(\s)*$/;//姓名
			 var regPhone = /^1[3|4|5|7|8]\d{9}$/;//手机号码
			 var regZi = /^[\u4e00-\u9fa5]/;//汉字
			 var regNum = /^[0-9]*$/;//银行账号
			 var tensPhone = /^[0-9\-]+$/;//固定电话  数字和横杠
			 $("input[type='text']").blur(function(){
					var self = $(this);
					var name = $(this).attr("name");
					switch (name){
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
								onError('*请正确输入手机号',self);
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
						case "salesPerson":
							if (self.val() == ""){
								onError('*请填写姓名',self);
							} else if(!regName.test(self.val())) {
								onError('*请填写格式正确的姓名',self);
							}else {
								onSuccess(self);
							}
							break;
						default:
							break;
					}
				});
		//冻结解冻
		 $(".blocked").click(function(){
				$("#layer,.blocked_lay").show();
			});
		 $(".unlock").click(function(){
				if($("#tenantStatus").text()=="冻结"){
					$("#layer,.blocked_lay").show();
					$(".blocked_lay h2").text("解冻账号");
					$(".blocked_lay p").text("您确认要解冻该家政公司吗？解冻后该家政门店下所有账号将恢复正常登陆家策好服务门店助手。");
				}
			});
		//取消按钮
		 $(".ok,.cancle,#layer").click(function(){
				$("#layer,.blocked_lay").hide();
			});
		//确认按钮
		$(".blocked_lay .ok").click(function(){
			 var tenantStatusCode = $("#tenantStatusCode").val();
			 var tenantId = $("#tenantId").val();
			 //alert(tenantStatusCode);
			  $.ajax({
			        url:  publicPath+"/api/tenants/isblock",
			        type: "post",
			        data: {
			        	tenantStatusCode:tenantStatusCode,
			        	tenantId:tenantId
			        	},
			        dataType: "json",
			        async:false,
			        success: function success(data) {
			            if(data.success){
			                if(data.code == 0){
			                	var dataJD = data.jsonData;
			                	
								$("#layer,.blocked_lay").hide();
								location.reload();
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
		});
		 //重置密码
	$(".reset").click(function(){
		$("#layer,.reset_lay").show();
	});
	//取消按钮
	$(".ok,.cancle,#layer").click(function(){
		//alert(11);
		$("#layer,.reset_lay").hide();
	});
	//确认按钮
	$(".reset_lay .ok").click(function(){
		var newpassword = $(".password").val();
		var loginAccount = $("#loginAccount").text();
		var tenantId = $("#tenantId").val();
		//alert("dahj");
		//修改密码
		 $.ajax({
		        url:  publicPath+"/api/tenants/resetPassword",
		        type: "post",
		        data: {
		        	newpassword:newpassword,
		        	loginAccount:loginAccount,
		        	tenantId:tenantId
		        	},
		        dataType: "json",
		        async:false,
		        success: function success(data) {
		            if(data.success){
		                if(data.code == 0){
		                	var dataJD = data.jsonData;
		                	
							$("#layer,.reset_lay").hide();
							location.reload();
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
		 
	});
	
	$("#revise").click(function(){
		$(".beforeEditing").hide();
		$(".afterEditing").show();
	})
	//修改按钮
	$(".ok_bt").click(function(){
		 $("i>input[type='text']").trigger("blur");
		 var callbackUrl = location.href;
		$("input[name='callbackUrl']").val(callbackUrl);
		//alert(callbackUrl);
		$("#check_m").submit();
		$(".beforeEditing").show();
		$(".afterEditing").hide();
	})
	//取消按钮
	$(".cancle_bt").click(function(){
		$(".beforeEditing").show();
		$(".afterEditing").hide();
	})
})

//			区域选择
function serviceProvice(){
	$.ajax({
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
</script>
</head>
<body>
<div id="header" class="clearfix"></div>

<!-- 左侧导航栏 -->
	<div class="naviLeft" >
		<ul>
			<li ><a class="ddgl" href="index.jsp"><em></em>申请入驻门店</a></li>
			<li class="nl_on"><a class="yygl" href="tentansManagement.jsp"><em></em>已入驻门店</a></li>
		</ul>
	</div>


	<div class="tenantManagement">
	<div class="frame frame_right">
		<form  id="check_m"  action="/fbzkConsole_web/api/tenants/updateTenantsInfo" method="post">
		<!-- 隐藏域 -->
		<input name="callbackUrl"   type="hidden" value="" />
		<input name="tenantId" id="tenantId" type="hidden" value=""/>
		<input  id="tenantStatusCode" type="hidden" value=""/>
		<div class="info_box">
			<div class="base-info">
				<p class="title info_title"><i>基本信息</i><a class="blocked">冻结账号</a><a class="unlock" style="display:none;">解冻账号</a> </p>
				<div class="base_info_box">
					<div class="mendian_logo">
						<span class="beforeEditing"><img id="logo" src=""/></span>
						<span class="afterEditing"><i class="logo_input">
						<input type="file"/><br/>请点击上传logo</i></span>
						<input type="hidden" name="fileLogo" />
					</div>
					<div class="base_info">
						<p>
							<i class="maritalStatus" id="tenantName">
								<label>门店名称</label>
								<span class="beforeEditing">（<i class="grey">有效期至</i>）</span>
								<span class="afterEditing"><input class="name" name="tenantName" type="text"/></span>
							</i>
						</p>
						<p>
							<i class="maritalStatus" id="contactAddress">
								<label>详细地址</label>
								<span class="beforeEditing"></span>
								<span class="afterEditing">
									<select name="serviceProvice">
										<option value="">请选择</option>
									</select>
									<select name="serviceCity">
										<option value="">请选择</option>
									</select>
									<select name="serviceCounty">
										<option value="">请选择</option>
									</select>
									<input placeholder="请填写详细地址" id="contactAddress" name="contactAddress" type="text"/>
								</span>
							</i>
						</p>
						<p>
							<i class="maritalStatus" id="contactName">
								<label>联系人姓名</label>
								<span class="beforeEditing"></span>
								<span class="afterEditing"><input type="text" name="contactName"/></span>
							</i>
							<i class="maritalStatus_sp" id="contactPhone">
								<label>联系人手机号</label>
								<span class="beforeEditing" ></span>
								<span class="afterEditing"><input type="text" name="contactPhone"/></span>
							</i>
						</p>
						<p>
							<i class="maritalStatus">
								<label>管理员账号</label>
								<span class="" id="loginAccount"></span><a class="reset">重置密码</a>
							</i>
						</p>
						<p>
							<i class="maritalStatus" id="domain">
								<label>门店的网址</label>
								<span class="do"></span>
							</i>
						</p>
						<p>
							<i class="maritalStatus" id="refereeId">
								<label>推荐ID</label>
								<span class="beforeEditing"></span>
								<span class="afterEditing"></span>
							</i>
							<i class="maritalStatus_sp" id="tenantsPhone">
								<label>门店联系电话</label>
								<span class="beforeEditing" ></span>
								<span class="afterEditing"><input type="text" name="tenantsPhone"/></span>
							</i>
						</p>
					</div>
					<div class="wap">
						<p>门店二维码</p>
					</div>
				</div>
			</div>
				
			<div class="account-info">
				<p class="title info_title"><i>账户信息</i></p>
				<div class="account_box">
					<table class="account_info">
						<tr>
							<th><p>账户余额<span class="totalAmount"></span></p></th>
							<th><p>冻结余额<span class="frozenAmount"></span></p></th>
							<th><p>可提余额<span class="availableAmount"></span></p></th>
						</tr>
						<tr>
							<td>
								<p>
									账号性质
									<span class="beforeEditing" id="accountType"></span>
									<span class="afterEditing">
										<input name="accountType" id="personal" checked="checked" value="01" type="radio"/><label for="personal">个人账户</label>
										<input name="accountType" id="qiye" value="02" type="radio"/><label for="qiye">企业账户</label>
									</span>
								</p>
							</td>
							<td colspan="2">
								<p id="bankName">
								账号名称
									<span class="beforeEditing" ></span>
									<span class="afterEditing">
										<input name="bankName" type="text"/>
									</span>
								</p>
							</td>
						</tr>
						<tr>
							<td><p id="cardNo">
								银行账号
								<span class="beforeEditing" ></span>
								<span class="afterEditing">
									<input name="cardNo" type="text"/>
								</span>
							</p></td>
							<td colspan="2"><p>
								所属银行
								<span class="beforeEditing" id="bankCode"></span>
								<span class="afterEditing" id="bank">
									<select name="bankCode">
										<option value="">请选择</option>
									</select>
									<input placeholder="请填写支行" type="text" name="bankSecName"/>
								</span>
							</p></td>
						</tr>
					</table>
				</div>
			</div>		
			
			<div class="member-info">
				<p class="title info_title"><i>会员信息</i></p>
				<h2>
					会员类型&nbsp;&nbsp;<span class="" id="memberType"></span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门店状态&nbsp;&nbsp;<span class="" id="tenantStatus"></span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有效期至&nbsp;&nbsp;<span class="" id="dueDate"></span><a class="recharge">代理充值</a>
				</h2>
				<div class="member_info_list">
					<span class="info_tt">剩余权益</span>
					<div class="quanyi">
						<table>
							<tr>
								<td>权益项目</td>
								<td>权益内容</td>
								<td>剩余次数</td>
								<td>有效期至</td>
							</tr>
							<tr>
								<td>营销方案贴身指导落地</td>
								<td>资深运营上门指导</td>
								<td>1</td>
								<td>2018-03-03</td>
							</tr>
							<tr>
								<td>首页推广banner</td>
								<td>7天套餐</td>
								<td>1</td>
								<td>2018-03-03</td>
							</tr>
							<tr>
								<td>推荐置顶</td>
								<td>省市月套餐</td>
								<td>1</td>
								<td>2018-03-03</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="member_info_list">
					<span class="info_tt">购买记录</span>
					<div class="jilu">
					<table>
							<tr>
								<td>购买套餐</td>
								<td>购买金额</td>
								<td>购买时间</td>
								<td>购买期限</td>
							</tr>
							<tr>
								<td>尊享版1年期</td>
								<td>6,880</td>
								<td>2018-03-03&nbsp;12:12:10</td>
								<td>1年</td>
							</tr>
							<tr>
								<td>尊享版1年期</td>
								<td>6,880</td>
								<td>2018-03-03&nbsp;12:12:10</td>
								<td>1年</td>
							</tr>
						</table>
					</div>
				</div>
			</div>		
			<div class="main-info">
				<p class="title info_title"><i>主体信息</i></p>
				
				<div class="main_info_box">
					<table>
						<tr>
							<td colspan="2"><p>创建时间&nbsp;&nbsp;&nbsp;&nbsp;<span class="applyTime"></span></p></td>
						</tr>
						<tr>
							<td><p>工商名称&nbsp;&nbsp;&nbsp;&nbsp;<span class="companyName"></span></p></td>
							<td><p>营业执照注册号&nbsp;&nbsp;<span class="licenceNo"></span></p></td>
						</tr>
						<tr>
							<td><p>代理人姓名&nbsp;&nbsp;<span class="agentName"></span></p></td>
							<td><p>代理人身份证号&nbsp;&nbsp;<span class="idNumber"></span></p></td>
						</tr>
						<tr>
							<td colspan="2"><p><span>证件照片</span><img id="licence" src="" /><img id="idcardFront" src="" /><img id="idcardBack" src="" /></p></td>
						</tr>
					</table>
				</div>
				<div class="last_info">
					<p>
						推荐人ID<span class="recommendId"></span>
					</p>
					<p id="salesPerson">
						销售人姓名
						<span class="beforeEditing" ></span>
						<span class="afterEditing">
							<input placeholder="请填写姓名" name="salesPerson" type="text"/>
						</span>
					</p>
				</div>
			</div>	
			<!-- 底部固定 -->
			<div class="bottom_menu">
				<span class="beforeEditing" id="revise">修改</span>
				<input class="afterEditing ok_bt"  type="button" value="保存" style="border:none"/>
				<span class="afterEditing cancle_bt">取消</span>
			</div>
		</div>
		</form>
	</div>
</div>

	<div id="layer"></div>
	<!--重置密码 -->
	<div class="reset_lay">
	
	<h2>重置密码</h2>
	<p> 管理员账号&nbsp;&nbsp;&nbsp;&nbsp;<span id="loginAccount"></span></p>
	<p><span>新密码</span><input class="password" placeholder="6~16位字母或数字组合" type="text"/></p>
	<div class="bt_box">
		<input class="ok" type="button" value="确认"/>
		<input class="cancle" type="button" value="取消"/>
	</div>
</div>
<!-- 冻结账号 -->
<div class="blocked_lay">
	<h2>冻结账号</h2>
	<p> 您确认要冻结该家政公司吗？冻结后该家政门店下所有账户均无法登陆家策好服务门店助手。</p>
	<div class="bt_box">
		<input class="ok" type="button" value="确认"/>
		<input class="cancle" type="button" value="取消"/>
	</div>
</div>

</body>
</html>