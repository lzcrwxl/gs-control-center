<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>清算管理</title>
<link rel="stylesheet" type="text/css" href="css/global.css"/>
<link rel="stylesheet" type="text/css" href="css/clearManagement.css"/>
		<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jquery.pagination.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="time/lhgcore.min.js"></script>
    	<script type="text/javascript" src="time/lhgcalendar.min.js"></script>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
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
			    callback: QueryClearList
			});
		};
		function QueryClearList(page_index){
			var pageNumber = page_index+1;
			var tradeTimeLeft = $(".FilterList-om input[id='tradeTimeLefts']").val();
			var tradeTimeRight = $(".FilterList-om input[id='tradeTimeRights']").val();
			var tradeType = $(".FilterList-om select[name='tradeType']").val();
			var tradeChannel = $(".FilterList-om select[name='tradeChannel']").val();
			var status = $(".FilterList-om select[name='status']").val();
			$.ajax({
		        url: publicPath+"/api/clear/QueryClearList",
		        type: "post",
		        data: {    pageNumber:pageNumber,
		        	       pageSize:pageSize,
		        	       tradeTimeLeft:tradeTimeLeft,
		        	       tradeTimeRight:tradeTimeRight,
		        	       tradeType:tradeType,
		        	       tradeChannel:tradeChannel,
		        	       status:status},
		        dataType: "json",
		        success: function success(data) {
		            if(data.success){
		                if(data.code == 0){
		                	var dataJD = data.jsonData.rows;
		                	var html = "";
		                	var n = 0;
		                	$.each(dataJD, function(i,item) {
		                		n++;
		                		html += "<tr data-tradeType='"+item.tradeType+"' data-tradeNo='"+item.tradeNo+"'  data-inOutNo='"+item.inOutNo+"' data-tenantId='"+item.tenantId+"' data-orderNo='"+item.orderNo+"'   data-tradeFlowNo='"+item.tradeFlowNo+"'     data-status='"+item.status+"'>"+
									"<td><input type='checkbox'/></td>"+
									"<td>"+item.tradeTime+"</td>"+
								    "<td>"+item.tradeFlowNo+"</td>"+
									"<td>"+item.orderNo+"</td>"+
									"<td>"+item.tenantName+"</td>"+
									"<td>"+RetainedDecimalPlaces(item.tradeAmount)+"</td>"+
									"<td>"+item.tradeType+"</td>"+
									"<td>"+item.tradeChannel+"</td>"+
									"<td>"+item.modifyTime+"</td>"+
									"<td class='status'></td>"+
									"<td>"+item.tradeFlowNoBt+"</td>"+
									"<td class='clear' style='color:red;'><a></a></td>"+
								"</tr>"
		                	});
		                	$(".om-list tbody").html(html);
		                	$(".om-list tbody tr").each(function(){
		                		if($(this).data("status")=="交易处理中"){
		                			$(this).find(".clear a").text("强制清算").addClass("cl");
		                			$(this).find(".status").text("待清算");
			                	}
		                		if($(this).data("status")=="交易成功"){
		                			$(this).find(".status").text("已清算");
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
		 
				//头部引入
				$(function(){
					$('#header').load('header.jsp',function(){
						$(".navigator ul li a").removeClass("navi-on");
						$(".navigator ul li").eq(4).children().addClass("navi-on");
					});
					J('#tradeTimeLefts').calendar({maxDate:'#tradeTimeRights'});
					J('#tradeTimeRights').calendar({minDate:'#tradeTimeLefts'});
					//获取总页数
					$.ajax({
				        url: publicPath+"/api/clear/QueryClearList",
				        type: "post",
				        data: {},
				        dataType: "json",
				        success: function success(data) {
				            if(data.success){
				                if(data.code == 0){
				                	var dataJD = data.jsonData;
				                	console.info(dataJD);
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
					 $(".FilterList-om button").click(function(){
						var tradeTimeLeft = $(".FilterList-om input[id='tradeTimeLefts']").val();
						var tradeTimeRight = $(".FilterList-om input[id='tradeTimeRights']").val();
						var tradeType = $(".FilterList-om select[name='tradeType']").val();
						var tradeChannel = $(".FilterList-om select[name='tradeChannel']").val();
						var status = $(".FilterList-om select[name='status']").val();
						
							$.ajax({
						        url: publicPath+"/api/clear/QueryClearList",
						        type: "post",
						        data: { tradeTimeLeft:tradeTimeLeft,
						        		tradeTimeRight:tradeTimeRight,
						        		tradeType:tradeType,
						        		tradeChannel:tradeChannel,
						        		status:status},
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
					//提现清算
					 $(".frame").on("click",".om-list tbody .clear",function(){
								var tradeType= $(this).parents('tr').attr("data-tradeType");
								var inOutNo= $(this).parents('tr').attr("data-inOutNo");
								var tradeNo= $(this).parents('tr').attr("data-tradeNo");
								var tenantId= $(this).parents('tr').attr("data-tenantId");
								var tenantId= $(this).parents('tr').attr("data-tenantId");
								var tradeFlowNo= $(this).parents('tr').attr("data-tradeFlowNo");
								 if(window.confirm('你确定要清算吗？')){
									if(tradeType == '提现' ){
										if(window.confirm('清算后不可回滚，确认清算吗？')){
											alert(tradeFlowNo);
										$.ajax({
									        url: publicPath+"/api/clear/tiXianClear",
									        type: "post",
									        data: { inOutNo:inOutNo,
									        		tradeNo:tradeNo,
									        	    tenantId:tenantId,
									        	    tradeFlowNo:tradeFlowNo},
									        dataType: "json",
									        success: function success(data) {
									        	 console.log(data);
									            if(data.success){
									                if(data.code == 0){
									                	alert("提现清算成功！");
									                	var dataJD = data.jsonData;
									                	location.reload();
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
									}
									if(tradeType == '手续费'){
										if(window.confirm('清算后不可回滚，确认清算吗？')){
											$.ajax({
										        url: publicPath+"/api/clear/tiXianClear",
										        type: "post",
										        data: { inOutNo:inOutNo,
										        		tradeNo:tradeNo,
										        	    tenantId:tenantId,
										        	    tradeFlowNo:tradeFlowNo},
										        dataType: "json",
										        success: function success(data) {
										        	 console.log(data);
										            if(data.success){
										                if(data.code == 0){
										                	alert("手续费清算成功！");
										                	var dataJD = data.jsonData;
										                	location.reload();
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
									}
									if(tradeType=="退款"){
										if(window.confirm('清算后不可回滚，确认清算吗？')){
											var orderNo= $(this).parents('tr').attr("data-orderNo");
											//alert(orderNo);
											$.ajax({
										        url: publicPath+"/api/clear/tuiKuanClear",
										        type: "post",
										        data: { orderNo:orderNo,
										        		inOutNo:inOutNo,
										        		tradeNo:tradeNo,
										        	    tenantId:tenantId,
										        	    tradeFlowNo:tradeFlowNo},
										        dataType: "json",
										        success: function success(data) {
										            if(data.success){
										                if(data.code == 0){
										                	alert("退款清算成功！");
										                	var dataJD = data.jsonData;
										                	pagination(dataJD.records);
										                	location.reload();
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
										
									}
									return true;
								}else{
									return false;
								} 
							}); 
					//导出excel
					 $(".FilterList-om a").click(function(){
							var tradeTimeLeft = $(".FilterList-om input[id='tradeTimeLefts']").val();
							var tradeTimeRight = $(".FilterList-om input[id='tradeTimeRights']").val();
							var tradeType = $(".FilterList-om select[name='tradeType']").val();
							var tradeChannel = $(".FilterList-om select[name='tradeChannel']").val();
							var status = $(".FilterList-om select[name='status']").val();
					window.open(publicPath+"/api/clear/ExportExcel?tradeTimeLeft=" + tradeTimeLeft);
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
						//支付类型列表
						
			});
				
		</script>
</head>
<body>
	<!-- 头部 -->
	<div id="header" class="clearfix"></div>
	<!-- 左侧导航栏 -->
	<div class="naviLeft">
		<ul>
			
			<li ><a class="ddgl" href="fundsManagement.jsp"><em></em>财务流水</a></li>
			<li class="nl_on"><a class="yygl" href="clearManagement.jsp"><em></em>清算管理</a></li>
		</ul>
	</div>
		<!-- 页面开始 -->
		
		<div class="particulars">
			<div class="frame">
				<div class="orderSearch clearfix">
					<div class="FilterList-om">
						<p>
							<span>
						<label>支付类型</label>
						<select name="tradeType">
							<option value="">请选择</option>
							<option value="02" >提现</option>
							<option value="05" >手续费</option>
							<option value="09" >退款</option>
						</select>
					</span>
							<span>
								<label>发起时间</label>
								<input type="text" class="runcode" id="tradeTimeLefts"  value="" readonly="readonly" placeholder="选择日期"/>~
								<input type="text" class="runcode" id="tradeTimeRights"  value="" readonly="readonly" placeholder="选择日期" />
							</span>
					</p>
					<p>
						<span>
							   <label>支付渠道</label>
							   <select name="tradeChannel">
							      <option value="">请选择</option>
							   </select>
					   </span>
					   <span >
								<label>状态</label>
								<select name="status">
									<option></option>
									<option value="01">待清算</option>
									<option value="02">已清算</option>
							    </select>
					  </span>
					  	<span>
							<button>查询</button>
						</span>
						</p>
							<span style="float:right;">下载清算明细：<a style="cursor: pointer;text-decoration:underline;color:blue;">导出Excel</a></span>
					</div>
				</div>
				<!-- 查询条件结束 -->
				<table class="om-list">
					<thead>
						<tr>
							<td>选择</td>
							<td>发起时间</td>
						    <td>支付流水号</td> 
							<td>订单编号</td>
							<td>门店/用户名称</td>
							<td>金额</td>
							<td>支付类型</td>
							<td>清算渠道</td>
							<td>处理时间</td>
							<td>状态</td>
							<td>原支付流水号</td>
							<td>操作</td>
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