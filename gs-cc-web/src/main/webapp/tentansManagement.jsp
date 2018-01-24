<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/echarts.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.pagination.js" type="text/javascript" charset="utf-8"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="time/lhgcore.min.js"></script>
<script type="text/javascript" src="time/lhgcalendar.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/global.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<link rel="stylesheet" type="text/css" href="css/tentansManagement.css"/>
<title>已入驻门店</title>
</head>
<body>
<div id="header" class="clearfix"></div>
<!-- 左侧导航栏 -->
	<div class="naviLeft">
		<ul>
			<li ><a class="ddgl" href="index.jsp"><em></em>申请入驻门店</a></li>
			<li class="nl_on"><a class="yygl" href="tentansManagement.jsp"><em></em>已入驻门店</a></li>
		</ul>
	</div>
	<div class="particulars">	
		<div class="frame">
				<div class="financialQuery clearfix">
				<p>
					<span>
						<label>门店名称</label>
						<input name="tenantName" maxlength="30"/>
					</span>
					<span>
						<label>申请人手机号</label>
						<input name="registerPhone" maxlength="11" value="" />
					</span>
					<span>
						<lable>推荐ID</lable>
						<input name="recommendId"  maxlength="8" value=""  />
					</span>
				</p>
				<p>
					<span>
						<label>会员类型</label>
						<select name="memberType">
							<option value="">请选择类型</option>
						</select>
					</span>	
					<span>
						<label>门店状态</label>
						<select name="tenantStatus">
							<option value="">请选择类型</option>
						</select>
					</span>
				</p>
				<p>
					<span>
						<label>所在地区</label>
						<select name="provice">
							<option value="">请选择省</option>
						</select>
						<select name="city">
							<option value="">请选择市</option>
						</select>
					</span>
				</p>
				<p>
					<span>
						<label>创建日期</label>
						<input type="text" class="runcode" id="applyTimeLeft" value="" readonly="readonly" />
						<label>~</label>
						<input type="text" class="runcode" id="applyTimeRight" value="" readonly="readonly" />
					</span>
					<span>
						<label>有效日期</label>
						<input type="text" class="runcode" id="dueDateLeft" value="" readonly="readonly" />
						<label>~</label>
						<input type="text" class="runcode" id="dueDateRight" value="" readonly="readonly" />
					</span>
					<a>查询</a>
				</p>
				
			</div>
			<div class="addfix">
					<a href="tentansDetil.jsp">+ 创建新门店</a>
				</div>
			<table class="financialFlow">
				<thead>
					<tr>
						<td>序号</td>
						<td>创建时间</td>
						<td>门店名称</td>
						<td>联系人</td>
						<td>手机号</td>
						<td>所在区域</td>
						<td>销售人</td>
						<td>账户余额</td>
						<td>会员类型</td>
						<td>有效期至</td>
						<td>门店状态</td>
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
		var tenantName = $(".financialQuery input[name='tenantName']").val();
		var registerPhone = $(".financialQuery input[name='registerPhone']").val();
		var tenantStatus = $(".financialQuery select[name='tenantStatus']").val();
		//推荐人ID
		var recommendId = $(".financialQuery input[name='recommendId']").val();
		//会员类型
		var memberType = $(".financialQuery select[name='memberType']").val();
		var provice = $(".financialQuery select[name='provice']").val();
		var city = $(".financialQuery select[name='city']").val();
		var applyTimeLeft = $(".financialQuery input[id='applyTimeLeft']").val();
		var applyTimeRight = $(".financialQuery input[id='applyTimeRight']").val();
		var dueDateLeft = $(".financialQuery input[id='dueDateLeft']").val();
		var dueDateRight = $(".financialQuery input[id='dueDateRight']").val();
		$.ajax({
	        url: publicPath+"/api/tenants/getTenantsApp",
	        type: "post",
	        data: { pageNumber:pageNumber,
	        		pageSize:pageSize,
	        	    tenantName:tenantName,
	        	    registerPhone:registerPhone,
	        	    tenantStatus:tenantStatus,
	        	    recommendId:recommendId,
	        	    memberType:memberType,
	        	    provice:provice,
	        	    city:city,
	        	    applyTimeLeft:applyTimeLeft,
	        	    applyTimeRight:applyTimeRight,
	        	    dueDateLeft:dueDateLeft,
	        	    dueDateRight:dueDateRight
	        	    },
	        dataType: "json",
	        success: function success(data) {
	            if(data.success){
	                if(data.code == 0){
	                	var dataJD = data.jsonData.rows;
	                	var html = "";
	                	var n = 0;
	                	$.each(dataJD, function(i,item) {
	                		var salesPerson = "";
	                		if(item.salesPerson!=null){
	                			salesPerson=item.salesPerson;
	                		}else{
	                			salesPerson="";
	                		}
	                		var memberType="";
	                		if(item.memberType!=null){
	                			memberType=item.memberType;
	                		}else{
	                			memberType="";
	                		}
	                		if(item.dueDate==""){
	                			dueDate="--";
	                		}else{
	                			dueDate=item.dueDate;
	                		}
	                		n++;
	                		html += "<tr data-tenantId='"+item.tenantId+"'>"+
								"<td>"+n+"</td>"+
								"<td>"+item.applyTime+"</td>"+
								"<td>"+item.tenantName+"</td>"+
								"<td>"+item.contactName+"</td>"+
								"<td>"+item.contactPhone+"</td>"+
								"<td>"+item.privince+item.city+"</td>"+
								"<td>"+salesPerson+"</td>"+
								"<td>"+item.availableAmount+"</td>"+
								"<td>"+memberType+"</td>"+
								"<td>"+dueDate+"</td>"+
								"<td>"+item.tenantStatus+"</td>"+
								"<td><a class='revise'></a></td>"+
							"</tr>"
	                	});
	                	$(".financialFlow tbody").html(html);
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
//		头部引入
		$('#header').load('header.jsp');
		//获取总条数
		$.ajax({
	        url: publicPath+"/api/tenants/getTenantsApp",
	        type: "post",
	        data: {
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
		// 条件查询按钮
		$(".financialQuery a").click(function(){
			var tenantName = $(".financialQuery input[name='tenantName']").val();
			var registerPhone = $(".financialQuery input[name='registerPhone']").val();
			var tenantStatus = $(".financialQuery select[name='tenantStatus']").val();
			//推荐人ID
			var recommendId = $(".financialQuery input[name='recommendId']").val();
			//会员类型
			var memberType = $(".financialQuery select[name='memberType']").val();
			var provice = $(".financialQuery select[name='provice']").val();
			var city = $(".financialQuery select[name='city']").val();
			var applyTimeLeft = $(".financialQuery input[id='applyTimeLeft']").val();
			var applyTimeRight = $(".financialQuery input[id='applyTimeRight']").val();
			var dueDateLeft = $(".financialQuery input[id='dueDateLeft']").val();
			var dueDateRight = $(".financialQuery input[id='dueDateRight']").val();
			$.ajax({
		        url: publicPath+"/api/tenants/getTenantsApp",
		        type: "post",
		        data: { 
		        	tenantName:tenantName,
	        	    registerPhone:registerPhone,
	        	    tenantStatus:tenantStatus,
	        	    recommendId:recommendId,
	        	    memberType:memberType,
	        	    provice:provice,
	        	    city:city,
	        	    applyTimeLeft:applyTimeLeft,
	        	    applyTimeRight:applyTimeRight,
	        	    dueDateLeft:dueDateLeft,
	        	    dueDateRight:dueDateRight
	        	    },
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
		            }else if(data.code==999010){
		                console.log(data.msg);
		                window.location.href='login.jsp';
		            }
		        }
		    });
		});
		J('#applyTimeLeft').calendar({maxDate:'#applyTimeRight'});
		J('#applyTimeRight').calendar({minDate:'#applyTimeLeft'});
		J('#dueDateLeft').calendar({maxDate:'#dueDateRight'});
		J('#dueDateRight').calendar({minDate:'#dueDateLeft'});
		//会员类型code
		memberType();
		function memberType(){
			$.ajax({
		        url: publicPath+"/api/common/getDictionaryData/c023",
		        type: "post",
		        data: {},
		        dataType: "json",
		        success: function success(data) {
		            if(data.success){
		                if(data.code == 0){
		                	var dataJD = data.jsonData;
		                	var html = "";
		                	$.each(dataJD, function(i,item) {
		                		html += "<option value='"+item.code+"'>"+item.name+"</option>"; 
		                	});
		                	$("select[name='memberType']").html("<option value=''>请选择</option>"+html);
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
		//门店状态code
		tenantStatus();
		function tenantStatus(){
			$.ajax({
		        url: publicPath+"/api/common/getDictionaryData/c017",
		        type: "post",
		        data: {},
		        dataType: "json",
		        success: function success(data) {
		            if(data.success){
		                if(data.code == 0){
		                	var dataJD = data.jsonData;
		                	var html = "";
		                	$.each(dataJD, function(i,item) {
		                		html += "<option value='"+item.code+"'>"+item.name+"</option>"; 
		                	});
		                	$("select[name='tenantStatus']").html("<option value=''>请选择</option>"+html);
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
		//地区code
		serviceProvice();
		$("select[name='provice']").change(function(){
			var pcode = $(this).val();
			serviceCity(pcode);
		});
		function serviceProvice(){
			$.ajax({
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
		//详细跳转
		$(".frame").on("click",".financialFlow tbody .revise",function(){
			var tenantId = $(this).parents('tr').attr("data-tenantId");
			location.href="tenantManagementDetil.jsp?tenantId="+tenantId;
		})
	});
	
	</script>
</body>
</html>