<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/echarts.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.pagination.js" type="text/javascript" charset="utf-8"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="time/lhgcore.min.js"></script>
<script type="text/javascript" src="time/lhgcalendar.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/global.css"/>
<link rel="stylesheet" type="text/css" href="css/indexm.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css"/>
</head>
<body>
	<div id="header" class="clearfix"></div>
	<!-- 左侧导航栏 -->
	<div class="naviLeft">
		<ul>
			<li class="nl_on"><a class="ddgl" href="index.jsp"><em></em>申请入驻门店</a></li>
			<li ><a class="yygl" href="tentansManagement.jsp"><em></em>已入驻门店</a></li>
		</ul>
	</div>
	<div class="particulars">
	<div class="frame">
				<div class="tenantStatus">
					<ul class="clearfix">
						<li><a class="os_on" data-code="01">待审核</a></li>
						<li><a data-code="03">审核不通过</a></li>
					</ul>
				</div>
			<div class="financialQuery clearfix">
				<p>
					<span>
						<label>门店名称</label>
						<input name="tenantName" maxlength="30"/>
					</span>
					
					<span>
						<label style="margin-left: 177px;">申请人手机号</label>
						<input name="registerPhone" maxlength="11" value="" />
					</span>
					</p>
					<p>
					<span>
						<label>申请日期</label>
						<input type="text" class="runcode" id="addTimeLeft" value="" readonly="readonly" />
						<label>~</label>
						<input type="text" class="runcode" id="addTimeRight" value="" readonly="readonly" />
					</span>
					<span>
						<label>所在地区</label>
						<select name="provice">
							<option value="">请选择省</option>
						</select>
						<select name="city">
							<option value="">请选择市</option>
						</select>
					</span>
					<a>查询</a>
				</p>
			</div>
			<table class="financialFlow">
				<thead>
					<tr>
						<td>序号</td>
						<td>申请时间</td>
						<td>门店名称</td>
						<td>申请人手机号</td>
						<td>所在区域</td>
						<td class="modifyTime" style="display:none;">审核时间</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
				
				</tbody>
			</table>
		</div>
			<div class="clearfix">
				<div id="pagination">	
				</div>
			</div>
		</div>
		<script type="text/javascript">
		//alert(publicPath);
			//分页
			var pageNumber = 1;
			var pageSize = 10;
			//翻页
			function pagination(records){
				$("#pagination").pagination(records, {
				    num_edge_entries: 1,
				    num_display_entries: 4,
				    current_page:pageNumber-1,
				    items_per_page:pageSize,
				    prev_text:"上一页",
				    next_text:"下一页",
				    callback: tenantsInfoquery
				});
			};
			//查询列表
			function tenantsInfoquery(page_index){
				var pageNumber = page_index+1;
				var tenantName = $(".financialQuery input[name='tenantName']").val();//门店名称
				var registerPhone = $(".financialQuery input[name='registerPhone']").val();//申请人手机号
				var tenantStatus = $(".clearfix  li a.os_on").attr("data-code");//01
				var addTimeLeft = $(".financialQuery input[id='addTimeLeft']").val();
				var addTimeRight = $(".financialQuery input[id='addTimeRight']").val();
				var provice = $(".financialQuery select[name='provice']").val();//省
				var city = $(".financialQuery select[name='city']").val();//市
				$.ajax({
					//publicPath+
			        url: publicPath+"/api/tenants/tenantsInfoquery",
			        type: "post",
			        data: { pageNumber:pageNumber,
			        		pageSize:pageSize,
			        	    tenantName:tenantName,
			        	    registerPhone:registerPhone,
			        	    tenantStatus:tenantStatus,
			        	    addTimeLeft:addTimeLeft,
			        	    addTimeRight:addTimeRight,
			        	    provice:provice,
			        	    city:city},
			        dataType: "json",
			        success: function success(data) {
			            if(data.success){
			                if(data.code == 0){
			                	var dataJD = data.jsonData.rows;
			                	var html = "";
			                	var n = 0;
			                	$.each(dataJD, function(i,item) {
			                		
			                		n++;
			                		html += "<tr data-applicationId='"+item.applicationId+"' data-tenantStatus='"+item.tenantStatus+"'>"+
										"<td>"+n+"</td>"+
										"<td>"+item.addTime+"</td>"+
										"<td>"+item.tenantName+"</td>"+
										"<td>"+item.registerPhone+"</td>"+
										"<td>"+item.privince+item.city+"</td>"+
										"<td class='modify'>"+item.modifyTime+"</td>"+
										"<td><a class='revise'></a></td>"+
									"</tr>"
			                	});
			                	$(".financialFlow tbody").html(html);
			                	$(".financialFlow tbody tr").each(function(){
			                		
			                		if($(this).attr("data-tenantstatus")=="03"){
			                			$(".modifyTime").show();
				                	}if($(this).attr("data-tenantstatus")=="01"){
				                		$(this).find(".modify").hide();
				                	}
			                	});
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
			};

			$(function(){
				//头部引入
				$('#header').load('header.jsp');
				
				//获取总条数
				$.ajax({
					//publicPath+
			        url: publicPath+"/api/tenants/tenantsInfoquery",
			        type: "post",
			        data: {tenantStatus:$(".clearfix  li a.os_on").attr("data-code"),
			        	pageNumber:pageNumber,
			        	pageSize:pageSize},
			        dataType: "json",
			        success: function success(data) {
			            if(data.success){
			                if(data.code == 0){
			                	var dataJD = data.jsonData;
			                	pagination(dataJD.records);
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
				//加载日期
				J('#addTimeLeft').calendar({maxDate:'#addTimeRight'});
				J('#addTimeRight').calendar({minDate:'#addTimeLeft'});
				
				//证书状态
				$(".tenantStatus ul li").click(function(){
					$(".tenantStatus ul li a").removeClass("os_on");
					$(this).children().addClass("os_on");
					var os = $(this).index();
					var tenantStatus="";
					switch (os){
						case 0:
							tenantStatus="01";
							break;
						case 1:
							tenantStatus="03";
							break;
						default:
							break;
					};
					//获取总页数
					$.ajax({	
						//publicPath+
				        url: publicPath+"/api/tenants/tenantsInfoquery",
				        type: "post",
				        data: {
				        	pageNumber:pageNumber,
				        	pageSize:pageSize,
				        	tenantStatus:tenantStatus},
				        dataType: "json",
				        ayscn:"false",
				        success: function success(data) {
				            if(data.success){
				                if(data.code == 0){
				                	var dataJD = data.jsonData;
				                	pagination(dataJD.records);
				                	console.log(data.msg);
				                }else{
				                    console.log(data.msg);
				                }
				            }
				        }
				    });
				});
				
			   // 按条件查询
				$(".financialQuery a").click(function(){
					var tenantName = $(".financialQuery input[name='tenantName']").val();
					var registerPhone = $(".financialQuery input[name='registerPhone']").val();
					var tenantStatus = $(".clearfix  li a.os_on").attr("data-code");
					var addTimeLeft = $(".financialQuery input[id='addTimeLeft']").val();
					var addTimeRight = $(".financialQuery input[id='addTimeRight']").val();
					var provice = $(".financialQuery select[name='provice']").val();
					var city = $(".financialQuery select[name='city']").val();
					$.ajax({
						//publicPath+
				        url: publicPath+"/api/tenants/tenantsInfoquery",
				        type: "post",
				        data: { tenantName:tenantName,
				        		registerPhone:registerPhone,
				        	    tenantStatus:tenantStatus,
				        	    addTimeLeft:addTimeLeft,
				        	    addTimeRight:addTimeRight,
				        	    provice:provice,
				        	    city:city},
				        dataType: "json",
				        success: function success(data) {
				            if(data.success){
				                if(data.code == 0){
				                	var dataJD = data.jsonData;
				                	pagination(dataJD.records);
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
				//地区
				serviceProvice();
				$("select[name='provice']").change(function(){
					var pcode = $(this).val();
					serviceCity(pcode);
				});
				$("select[name='city']").change(function(){
					var pcode = $(this).val();
				});
				function serviceProvice(){
					$.ajax({
						//publicPath+
				        url: publicPath+"/api/common/getAreaData/c001",
				        type: "post",
				        data: {},
				        dataType: "json",
				        success: function success(data) {
				            if(data.success){
				                if(data.code == 0){
				                	var dataJD = data.jsonData;
				                	var html = "";
				                	$.each(dataJD, function(i,item) {
				                		html += "<option data-parentCode='"+item.parentCode+"' data-areaLevel='"+item.areaLevel+"' value='"+item.areaCode+"'>"+item.areaName+"</option>"; 
				                	});
				                	$("select[name='provice']").html("<option value=''>请选择</option>"+html);
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
					var pcode = pcode;
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
				                	$("select[name='city']").html("<option value=''>请选择</option>"+html);
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
				
				//门店详细				
				$(".frame").on("click",".financialFlow tbody .revise",function(){
					var applicationId= $(this).parents('tr').attr("data-applicationId");
					var tenantStatus = $(".clearfix  li a.os_on").attr("data-code");
					//alert(applicationId);
					//alert(tenantStatus);
					location.href="tentansDetil.jsp?applicationId="+applicationId+"&tenantStatus="+tenantStatus; 
				});
				
			});
		</script>
</body>
</html>