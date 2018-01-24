<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付订单</title>
<link rel="stylesheet" type="text/css" href="css/global.css"/>
		<link rel="stylesheet" type="text/css" href="css/orderManagement.css"/>
		<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jquery.pagination.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="time/lhgcore.min.js"></script>
    	<script type="text/javascript" src="time/lhgcalendar.min.js"></script>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
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
			    callback: OrdersList
			});
		};
		function OrdersList(page_index){
			var pageNumber = page_index+1;
			var orderNo = $(".FilterList-om input[name='orderNo']").val();
			var orderStatus = $(".FilterList-om select[name='orderStatus']").val();
			var orderStartTime = $(".FilterList-om input[id='orderStartTime']").val();
			var orderEndTime = $(".FilterList-om input[id='orderEndTime']").val();
			var orderSource = $(".FilterList-om select[name='orderSource']").val();
			var memberName = $(".FilterList-om input[name='memberName']").val();
			var memberMobile = $(".FilterList-om input[name='memberMobile']").val();
			var shareOrderNo = $(".FilterList-om input[name='shareOrderNo']").val();
			var serviceItemCode = $(".FilterList-om select[name='serviceItemCode']").val();
			$.ajax({
		        url:  publicPath+"/api/orders/OrdersList",
		        type: "post",
		        data: {	pageNumber:pageNumber,
		        		pageSize:pageSize,
		        		orderNo:orderNo,
		        		orderStatus:orderStatus,
		        		orderStartTime:orderStartTime,
		        		orderEndTime:orderEndTime,
		        		orderSource:orderSource,
		        		memberName:memberName,
		        		memberMobile:memberMobile,
		        		shareOrderNo:shareOrderNo,
		        		serviceItemCode:serviceItemCode},
		        dataType: "json",
		        success: function success(data) {
		            if(data.success){
		                if(data.code == 0){
		                	var dataJD = data.jsonData.rows;
		                	var html = "";
		                	var n = 0;
		                	$.each(dataJD, function(i,item) {
		                		n++;
		                		var  staffName="";
		                		if (item.staffName!= null) {
		                			staffName=item.staffName;
		                		} else{
		                			staffName="";
		                		}
		                		var  memberSex="";
		                		if (item.memberSex!= null) {
		                			memberSex=item.memberSex;
		                		} else{
		                			memberSex="";
		                		}
		                		html += "<tr>"+
									"<td>"+n+"</td>"+
									"<td >"+item.orderTime+"</td>"+
									"<td>"+item.orderNo+"</td>"+
									"<td>"+item.shareOrderNo+"</td>"+
									"<td>"+item.serviceItemCode+"</td>"+
									"<td>"+item.memberMobile+"</td>"+
									"<td>"+item.memberName+"</td>"+
									 "<td>"+memberSex+"</td>"+ 
									"<td>"+item.serviceProvice+item.serviceCity+item.serviceCounty+"</td>"+
									"<td>"+staffName+"</td>"+
									"<td>"+item.serviceStart+"~"+item.serviceEnd+"</td>"+
									"<td>"+RetainedDecimalPlaces(item.totalAmount)+"</td>"+
									"<td>"+item.tenantName+"</td>"+
									"<td>"+item.orderStatus+"</td>"+
								"</tr>"
		                	});
		                	$(".om-list tbody").html(html);
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
				//头部引入
				$(function(){
					$('#header').load('header.jsp',function(){
						$(".navigator ul li a").removeClass("navi-on");
						$(".navigator ul li").eq(1).children().addClass("navi-on");
					});
					J('#orderStartTime').calendar({maxDate: '#orderEndTime'});
					J('#orderEndTime').calendar({minDate: '#orderStartTime'});
					
					// 条件查询按钮
					$(".orderSearch button").click(function(){
						var orderNo = $(".FilterList-om input[name='orderNo']").val();
						var orderStatus = $(".FilterList-om select[name='orderStatus']").val();
						var orderStartTime = $(".FilterList-om input[id='orderStartTime']").val();
						var orderEndTime = $(".FilterList-om input[id='orderEndTime']").val();
						var orderSource = $(".FilterList-om select[name='orderSource']").val();
						var memberName = $(".FilterList-om input[name='memberName']").val();
						var memberMobile = $(".FilterList-om input[name='memberMobile']").val();
						var shareOrderNo = $(".FilterList-om input[name='shareOrderNo']").val();
						var serviceItemCode = $(".FilterList-om select[name='serviceItemCode']").val();
						$.ajax({
					        url: publicPath+"/api/orders/OrdersList",
					        type: "post",
					        data: { 
				        			orderNo:orderNo,
				        			orderStatus:orderStatus,
				        			orderStartTime:orderStartTime,
				        			orderEndTime:orderEndTime,
				        			orderSource:orderSource,
				        			memberName:memberName,
				        			memberMobile:memberMobile,
				        			shareOrderNo:shareOrderNo,
				        			serviceItemCode:serviceItemCode},
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
			
			
				
				
				//获取总页数
				$.ajax({
			        url: publicPath+"/api/orders/OrdersList",
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
				
				
			
				//服务工种列表
				serviceItemCode();
				function serviceItemCode(){
					$.ajax({
				        url:  publicPath+"/api/common/getDictionaryData/c004",
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
				                	$("select[name='serviceItemCode']").html("<option value=''>请选择</option>"+html);
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
				//订单来源
				orderSource();
				function orderSource(){
					$.ajax({
				        url:  publicPath+"/api/common/getDictionaryData/c019",
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
				                	$("select[name='orderSource']").html("<option value=''>请选择</option>"+html);
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
				//订单状态
				orderStatus();
				function orderStatus(){
					$.ajax({
				        url:  publicPath+"/api/common/getDictionaryData/c020",
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
				                	$("select[name='orderStatus']").html("<option value=''>请选择</option>"+html);
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
		
</head>
<body>
	<!-- 头部 -->
	<div id="header" class="clearfix"></div>
	<!-- 左侧导航栏 -->
	<div class="naviLeft">
		<ul>
			<li ><a class="ddgl" href="resOrderManagement.jsp"><em></em>预约订单</a></li>
			<li class="nl_on"><a class="yygl" href="orderManagement.jsp"><em></em>支付订单</a></li>
		</ul>
	</div>
		<!-- 页面开始 -->
		<div class="particulars">
			<div class="frame">
				<div class="orderSearch clearfix">
					<div class="FilterList-om">
						<p>
							<span>
								<label>订单编号</label>
								<input type="text" name="orderNo" value="" />
							</span>
							<!-- <span>
								<label>关联订单号</label>
								<input type="text" name="shareOrderNo" value="" />
							</span> -->
							</p>
							<P>
							<span> 
								<label>订单状态</label>
								<select name="orderStatus">
									<option value="">请选择</option>
								</select>
							</span>
							<span>
								<label>创建时间</label>
								<input type="text" class="runcode" id="orderStartTime"  value="" readonly="readonly" />~
								<input type="text" class="runcode" id="orderEndTime" value="" readonly="readonly" />
								
							</span>
							
						</p>
						<p>
							<span>
								<label>订单来源</label>
								<select name="orderSource">
									<option value="">请选择</option>
								</select>
							</span>
							<span>
								<label>客户姓名</label>
								<input type="text" name="memberName" value="" />
							</span>
							</P>
							<p>
							<span>
								<label>手机号码</label>
								<input type="text" name="memberMobile" value="" />
							</span>
							<span>
								<label>服务工种</label>
								<select name="serviceItemCode">
									<option value="">请选择</option>
								</select>
							</span>
							<button>查询</button>
						</p>
						<p>
							
						</p>
					</div>
				</div>
				<!-- 查询条件结束 -->
				<!-- 订单list -->
				<table class="om-list">
					<thead>
						<tr>
							<td>序号</td>
							<td>创建时间</td>
							<td>订单编号</td>
							<td>关联订单号</td>
							<td>服务工种</td>
							<td>手机号</td>
							<td>客户姓名</td>
							<td>性别</td> 
							<td>服务区域</td>
							<td>服务阿姨</td>
							<td>服务时间</td>
							<td>订单价格</td>
							<td>客户来源</td>
							<td>订单状态</td>
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
		</div>

</body>
</html>