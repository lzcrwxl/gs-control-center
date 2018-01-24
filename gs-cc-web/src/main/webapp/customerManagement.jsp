<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户管理</title>

		<link rel="stylesheet" type="text/css" href="css/global.css"/>
		<link rel="stylesheet" type="text/css" href="css/customerManagement.css"/>
	</head>
	<body>
		<div id="layer"></div>
		
		
		<div id="header" class="clearfix">
		</div>
		
		<div class="frame">
			<div class="cm-filter">
				<p>
					<label>客户姓名</label>
					<input type="text" name="customerName" value="" placeholder="请输入姓名" />
				</p>
				<p>
					<label>联系方式</label>
					<input type="text" name="customerMobile" value="" placeholder="请输入手机号" />
				</p>
				<p>
					<label>服务区域</label>
					<select name="serviceProvice">
						<option value="">请选择</option>
					</select>
					<select name="serviceCity">
						<option value="">请选择</option>
					</select>
					<select name="serviceCounty">
						<option value="">请选择</option>
					</select>
				</p>
				<a>查询</a>
			</div>
			
			
			<table class="clientFile">
				<thead>
					<tr>
						<td>序号</td>
						<td>注册时间</td>
						<td>手机号码</td>
						<td>客户姓名</td>
						<td>性别</td>
						<td>服务区域</td>
						<td>订客户来源</td>
						<td>最后登陆时间</td>
					</tr>
				</thead>
				<tbody>
				
						
				</tbody>
			</table>
			<div class="clearfix">
				<div id="pagination">	
				</div>
			</div>
		</div>
		
		
		<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jquery.pagination.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jquery.form.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		//分页
		var pageNumber = 1;
		var pageSize = 10;
		function pagination(records){
			$("#pagination").pagination(records, {
			    num_edge_entries: 1,
			    num_display_entries: 4,
			    current_page:pageNumber-1,
			    items_per_page:pageSize,
			    prev_text:"上一页",
			    next_text:"下一页",
			    callback: querycustomers
			});
		};
		//页面传值
		function querycustomers(page_index){
			var pageNumber = page_index+1;
			var customerName = $(".cm-filter input[name='customerName']").val();
			var customerMobile = $(".cm-filter input[name='customerMobile']").val();
			var serviceProvice = $(".cm-filter select[name='serviceProvice']").val();
			var serviceCity = $(".cm-filter select[name='serviceCity']").val();
			var serviceCounty = $(".cm-filter select[name='serviceCounty']").val();
			$.ajax({
		        url: publicPath+"/api/customers/QueryCustomersList",
		        type: "post",
		        data: {  pageNumber:pageNumber,
		        	     pageSize:pageSize,
		        	     customerName:customerName,
		        	     customerMobile:customerMobile,
		        	     serviceProvice:serviceProvice,
		        	     serviceCity:serviceCity,
		        	     serviceCounty:serviceCounty},
		        dataType: "json",
		        success: function success(data) {
		            if(data.success){
		                if(data.code == 0){
		                	var dataJD = data.jsonData.rows;
		                	console.info(dataJD);
		                	var html = "";
		                	var n = 0;
		                	$.each(dataJD, function(i,item) {
		                		n++;
		                		html += "<tr data-customerid='"+item.customerId+"'>"+
									"<td>"+n+"</td>"+
									"<td>"+item.registerTime+"</td>"+
									"<td>"+item.mobile+"</td>"+
									"<td>"+item.name+"</td>"+
									"<td>"+item.sex+"</td>"+
									/* "<tr>"+
									"<td>"+item.provice+"</td>"+
									"<td>"+item.city+"</td>"+
									"<td>"+item.county+"</td>"+
									"<tr>"+ */
									 "<td>"+item.provice+item.city+item.county+"</td>"+ 
									"<td>"+item.name+"</td>"+
									"<td>"+item.lastLoginTime+"</td>"+
								"</tr>"
		                	});
		                	$(".clientFile tbody").html(html);
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
				$('#header').load('header.jsp',function(){
					$(".navigator ul li a").removeClass("navi-on");
					$(".navigator ul li").eq(3).children().addClass("navi-on");
				});
				//获取总页数
				$.ajax({
			        url: publicPath+"/api/customers/QueryCustomersList",
			        type: "post",
			        data: {},
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
				$(".cm-filter a").click(function(){
					var customerName = $(".cm-filter input[name='customerName']").val();
					var customerMobile = $(".cm-filter input[name='customerMobile']").val();
					var serviceProvice = $(".cm-filter select[name='serviceProvice']").val();
					var serviceCity = $(".cm-filter select[name='serviceCity']").val();
					var serviceCounty = $(".cm-filter select[name='serviceCounty']").val();
					$.ajax({
				        url: publicPath+"/api/customers/QueryCustomersList",
				        type: "post",
				        data: { customerName:customerName,
				        	    customerMobile:customerMobile,
				        	    serviceProvice:serviceProvice,
				        	    serviceCity:serviceCity,
				        	    serviceCounty:serviceCounty},
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
				
				$("select[name='serviceProvice']").change(function(){
					var pcode = $(this).val();
					serviceCity(pcode);
				});
				$("select[name='serviceCity']").change(function(){
					var pcode = $(this).val();
					serviceCounty(pcode);
				});
//				区域选择
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
			});
		</script>
	

</body>
</html> 