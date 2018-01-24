<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="css/global.css"/>
		<link rel="stylesheet" type="text/css" href="css/staffCertDetil.css"/>
<title>证书详细页面</title>
<script type="text/javascript">
	function GetRequest(){
	   var url = location.search; //获取url中"?"符后的字串   
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
	$(function(){
		//头部引入
		$('#header').load('header.jsp',function(){
					$(".navigator ul li a").removeClass("navi-on");
					$(".navigator ul li").eq(2).children().addClass("navi-on");
				});
		var obj = GetRequest();
		var id = obj.id;
		//alert(id);
		//alert(obj.Id);
		  $.ajax({
	        url:  publicPath+"/api/certs/CertsInfo",
	        type: "post",
	        data: {id:id},
	        dataType: "json",
	        success: function success(data) {
	            if(data.success){
	                if(data.code == 0){
	                	var dataJD = data.jsonData;
	                	console.info(dataJD.certifiedStatus);
	                	var certifiedStatus = dataJD.certifiedStatus;
	                	 if(certifiedStatus =="未认证"){
	                		$(".bottom-navi1").css("display", "block");
	                		$(".bottom-navi2").css("display", "none");
	                	}
	                	$(".certStatus a").text(dataJD.certifiedStatus);
	                	$(".certNo span").text(dataJD.certNo);
	                	$(".levelCertType span").text(dataJD.CertType);
	                	$(".staffName span").text(dataJD.staffName);
	                	$(".cardNo span").text(dataJD.cardNo);
	                	$(".certificationBody span").text(dataJD.certificationBody);
	                	$(".authenticateGrade span").text(dataJD.authenticateGrade);
	                	$(".certificationDate span").text(dataJD.certificationDate);
	                	$(".certExpireDate span").text(dataJD.certExpireDate);
	                	$(".addTime span").text(dataJD.addTime);
	                	if(dataJD.certImage!= null&&dataJD.certImage!=""){
	                	$("#certImage").attr("src","/images"+dataJD.certImage);
	                	}else{
	                	$("#certImage").hide();
	                	}
	                	console.log(data.msg);
	                }else{
	                    console.log(data.msg);
	                }
	            }else if(data.code == 999010){
	                console.log(data.msg);
	                window.location.href='login.jsp';
	            }
	        }
	    });  
		 //审核通过
		 $(".pass").click(function(){
			 	var obj = GetRequest();
				var id = obj.id;
				//alert(id);
					 $.ajax({
				        url: publicPath+"/api/certs/PassCert",
				        type: "post",
				        data: {id:id},
				        dataType: "json",
				        success: function success(data) {
				        	if(data.success){
				                if(data.code == 0){
				                	alert("审核成功！");
				                	location.reload();
				                	history.back();
				                	console.log(data.msg);
				                }else{
				                    console.log(data.msg);
				                }
				            }else if(data.code == 999010){
				                console.log(data.msg);
				                window.location.href='login.jsp';
				            }else if(data.code == 999999){
				            	console.log(data.msg);
				            	//弹出错误窗口
				            }
				        }
				    });
				});
		 //审核不通过
		 $(".nopass").click(function(){
			 	var obj = GetRequest();
				var id = obj.id;
				//alert(id);
					 $.ajax({
				        url:  publicPath+"/api/certs/NopassCert",
				        type: "post",
				        data: {id:id},
				        dataType: "json",
				        success: function success(data) {
				        	if(data.success){
				                if(data.code == 0){
				                	alert("驳回成功！");
				                	location.reload();
				                	history.back();
				                	console.log(data.msg);
				                }else{
				                    console.log(data.msg);
				                }
				            }else if(data.code == 999010){
				                console.log(data.msg);
				                window.location.href='login.jsp';
				            }
				        }
				    });
				});
	});
</script>
</head>
<body>
<!-- 头部 -->
<div id="header" class="clearfix"></div>
<div class="orderDetails">
			<div class="infoCard" style="height:550px;">
			<div class="frame">
				<div id="client-info" >
					<div class="clearfix">
						<p class="title"><i>证书信息</i></p>
						<p class="certStatus">
						<a style="float:right;color:red;font-size: 25px;"></a>
						</p>
					</div>
					<div class="client-info" >
						<p>
							<i class="staffName">
								<label>家政员姓名</label>
								<span></span>
							</i>
							<i class="cardNo">
								<label>身份证号</label>
								<span></span>
							</i>
							
						</p>
						<p>
							<i class="levelCertType">
								<label>证书名称</label>
								<span></span>
							</i>
							<i class="authenticateGrade">
								<label>鉴定等级</label>
								<span></span>
							</i>
							
							<i class="certNo">
								<label>证书编号</label>
								<span></span>
							</i>
						</p>
						<p>
							<i class="certificationBody">
								<label>发证机构</label>
								<span></span>
							</i>
							<i class="certificationDate">
								<label>发证日期</label>
								<span></span>
							</i>
							<i class="addTime">
								<label>提交日期</label>
								<span></span>
							</i>
						</p>
						<p>
							<i class="certImage">
								<label>证书图片</label>
								<span><img id="certImage" alt="" src="" style="width: 215px;height: 100px;border:none;"></span>
							</i>
						</p>
						
					</div>
					<div class="bottom-navi1" style="display:none;margin-top: 300px;">
						<ul class="clearfix">
							<li>
								<a class="pass">审核通过</a>
							</li>
							<li>
								<a class="nopass">驳回</a>
							</li>
							<li>
								<a class="return" onclick="history.back();">返回</a>
							</li>
						</ul>
					</div>
					<div class="bottom-navi2" style="margin-top:300px;">
						<ul class="clearfix">
							<li>
								<a class="return" onclick="history.back();">返回</a>
							</li>
						</ul>
					</div>
				</div>
				</div>
			</div>
		</div>
</body>
</html>