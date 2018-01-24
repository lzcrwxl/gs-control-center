<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务管理</title>
<link rel="stylesheet" type="text/css" href="css/global.css"/>
<link rel="stylesheet" type="text/css" href="css/fundsMangement.css"/>
</head>
<body>
<div id="header" class="clearfix">
		</div>
		<!-- 左侧导航栏 -->
	<div class="naviLeft">
		<ul>
			
			<li  class="nl_on"><a class="ddgl" href="fundsManagement.jsp"><em></em>财务流水</a></li>
			<li><a class="yygl" href="clearManagement.jsp"><em></em>清算管理</a></li>
		</ul>
	</div>
		<div class="particulars">
		<div class="frame">
			<p class="title"><i>财务总览</i></p>
			<div class="financialPandect">
				<p class="totalAmountMoney">总余额(元)
					<span></span>
				</p>
				<p class="frozenAmountMoney">总冻结金额
					<span></span>
				</p>
				<p class="CtradeAmountMoney">历史充值总额(元)
					<span></span>
				</p>
				<p class="TtradeAmountMoney">历史提现总额(元)
					<span></span>
				</p>
				<p class="DTtradeAmountMoney">历史退款总额(元)
					<span></span>
				</p>
				<p class="StradeAmountMoney">手续费总额(元)
					<span></span>
				</p>
				 <p class="grossChargeMoney">续费总额(元)
					<span></span>
				</p> 
			</div>
		</div>
		<div class="frame">
			<p class="title"><i>财务流水</i></p>
			<div class="financialQuery clearfix">
				<p>
					<span>
						<label>门店名称</label>
						<input type="text" name="tenantName" value="" />
					</span>
					<span>
						<label>创建时间</label>
						<input type="text" class="runcode" id="tradeTimeLeft"  value="" readonly="readonly"  placeholder="选择日期" />~
						<input type="text" class="runcode" id="tradeTimeRight"   value="" readonly="readonly"  placeholder="选择日期" />
					</span>
					
				</p>
				<p>
					<span>
						<label>订单编号</label>
						<input type="text" name="orderNo" value="" />
					</span>
					<span>
						<label>支付流水号</label>
						<input type="text" name="tradeFlowNo" value="" />
					</span>
				</p>
				<p>
					<span>
						<label>支付类型</label>
						<select name="tradeType">
								<option value="">请选择</option>
						</select>
					</span>
					<span>
						<label>支付渠道</label>
						<select name="tradeChannel">
								<option value="">请选择</option>
						</select>
					</span>
					<a style="cursor:pointer">查询</a>
				</p>
			</div>
			<table class="financialFlow">
				<thead>
					<tr>
						<td>序号</td>
						<td>创建时间</td>
						<td>财务流水号</td>
						<td>订单编号</td>
						<td>门店/用户名称</td>
						<td>支付类型</td>
						<td>支付渠道</td>
						<td>支付流水号</td>
						<td>收/支金额</td>
						
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
		<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="time/lhgcore.min.js"></script>
    	<script type="text/javascript" src="time/lhgcalendar.min.js"></script>
    	<script src="js/jquery.pagination.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
//			财务流水
//			分页
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
				    callback: SelectFundsList
				});
			};
			
			function SelectFundsList(page_index){
				var pageNumber = page_index+1;
				var tenantName = $(".financialQuery input[name='tenantName']").val();
				var orderNo = $(".financialQuery input[name='orderNo']").val();
				var tradeFlowNo = $(".financialQuery input[name='tradeFlowNo']").val();
				var tradeTimeLeft = $(".financialQuery input[id='tradeTimeLeft']").val();
				var tradeTimeRight = $(".financialQuery input[id='tradeTimeRight']").val();
				var tradeType = $(".financialQuery select[name='tradeType']").val();
				var tradeChannel = $(".financialQuery select[name='tradeChannel']").val();
				$.ajax({
			        url: publicPath+"/api/funds/SelectFundsList",
			        type: "post",
			        data: { pageNumber:pageNumber,
			        		pageSize:pageSize,
			        		tenantName:tenantName,
			        		tradeFlowNo:tradeFlowNo,
			        		orderNo:orderNo,
			        		tradeTimeLeft:tradeTimeLeft,
			        		tradeTimeRight:tradeTimeRight,
			        		tradeType:tradeType,
			        		tradeChannel:tradeChannel},
			        dataType: "json",
			        success: function success(data) {
			            if(data.success){
			                if(data.code == 0){
			                	var dataJD = data.jsonData.rows;
			                	var html = "";
			                	var n = 0;
			                	$.each(dataJD, function(i,item) {
			                		n++;
			                		var status ="";
			                		if(item.status!=null&&item.status=="01"){
			                			status="待处理";
			                		}
			                		if(item.status!=null&&item.status=="02"){
			                			status="已处理";
			                		}
			                		if(item.status!=null&&item.status=="03"){
			                			status="已取消";
			                		}
			                		html += "<tr data-financeType='"+item.financeType+"'>"+
										"<td>"+n+"</td>"+
										"<td>"+item.addTime+"</td>"+
										"<td>"+item.IN_OUT_NO+"</td>"+
										"<td>"+item.ORDER_NO+"</td>"+
										"<td>"+item.WEBSITE_NAME+"</td>"+
										"<td>"+item.payType+"</td>"+
										"<td>"+item.tradeChannel+"</td>"+
										"<td>"+item.TRADE_FLOW_NO+"</td>"+
										"<td class='amount'>"+RetainedDecimalPlaces(item.IN_OUT_AMOUNT)+"</td>"+
										
									"</tr>"
			                	});
			                	$(".financialFlow tbody").html(html);
			                	
			                	/* $(".financialFlow tbody tr .amount").each(function(){
			                		if($(this).data("financeType")=="01"){
			                			
			                			$(this).find(".amount").before("+");
				                	}
			                		if($(this).data("financeType")=="02"){
			                			$(this).find(".amount").before("-");
				                	}
			                	}); */
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
			};

			$(function(){
//				头部引入
				$('#header').load('header.jsp',function(){
					$(".navigator ul li a").removeClass("navi-on");
					$(".navigator ul li").eq(4).children().addClass("navi-on");
				});
				
//				获取总条数
				$.ajax({
			        url: publicPath+"/api/funds/SelectFundsList",
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
			            }else if(data.code==999010){
			                console.log(data.msg);
			                window.location.href='login.jsp';
			            }
			        }
			    });
				// 条件查询按钮
				$(".financialQuery a").click(function(){
					var tenantName = $(".financialQuery input[name='tenantName']").val();
					var tradeTimeLeft = $(".financialQuery input[id='tradeTimeLeft']").val();
					var tradeTimeRight = $(".financialQuery input[id='tradeTimeRight']").val();
					var tradeType = $(".financialQuery select[name='tradeType']").val();
					var tradeChannel = $(".financialQuery select[name='tradeChannel']").val();
					var orderNo = $(".financialQuery input[name='orderNo']").val();
					var tradeFlowNo = $(".financialQuery input[name='tradeFlowNo']").val();
					//alert(tradeTimeLeft);
					$.ajax({
				        url: publicPath+"/api/funds/SelectFundsList",
				        type: "post",
				        data: { tenantName:tenantName,
				        	tradeTimeLeft:tradeTimeLeft,
				        	tradeTimeRight:tradeTimeRight,
				        	tradeType:tradeType,
				        	tradeFlowNo:tradeFlowNo,
			        		orderNo:orderNo,
				        	tradeChannel:tradeChannel},
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
				J('#tradeTimeLeft').calendar({maxDate:'#tradeTimeRight'});
				J('#tradeTimeRight').calendar({minDate:'#tradeTimeLeft'});
				
			
				
//				财务总览
				$.ajax({
			        url: publicPath+"/api/funds/QueryFunds",
			        type: "post",
			        data: {},
			        dataType: "json",
			        success: function success(data) {
			            if(data.success){
			                if(data.code == 0){
			                	var dataJD = data.jsonData;
			                	$(".totalAmountMoney span").text(RetainedDecimalPlaces(dataJD.totalAmount));
			                	$(".frozenAmountMoney span").text(RetainedDecimalPlaces(dataJD.forzenAmount));
			                	$(".CtradeAmountMoney span").text(RetainedDecimalPlaces(dataJD.CtradeAmount));
			                	$(".TtradeAmountMoney span").text(RetainedDecimalPlaces(dataJD.TtradeAmount));
			                	$(".DTtradeAmountMoney span").text(RetainedDecimalPlaces(dataJD.DtradeAmount));
			                	$(".StradeAmountMoney span").text(RetainedDecimalPlaces(dataJD.StradeAmount));
			                	console.log(data.msg);
			                }else{
			                    console.log(data.msg);
			                }
			            }else if(data.code==999010){
			                console.log(data.msg);
			                window.location.href='login.jsp';
			            }else if(data.code==999999){
			            	alert("系统错误");
			            }
			        }
			    });
				//清算渠道列表
				tradeChannel();
				function tradeChannel(){
					$.ajax({
				        url: publicPath+"/api/common/getDictionaryData/c021",
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
				                	$("select[name='tradeChannel']").html("<option value=''>请选择</option>"+html);
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
				//交易类型列表
				tradeType();
				function tradeType(){
					$.ajax({
				        url: publicPath+"/api/common/getDictionaryData/c024",
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
				                	$("select[name='tradeType']").html("<option value=''>请选择</option>"+html);
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
			})
		</script>
</body>
</html>