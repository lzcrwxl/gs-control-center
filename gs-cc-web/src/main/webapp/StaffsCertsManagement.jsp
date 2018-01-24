<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>证书管理页面</title>
<link rel="stylesheet" type="text/css" href="css/global.css"/>
<link rel="stylesheet" type="text/css" href="css/staffCertManagenment.css"/>
		<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jquery.pagination.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="time/lhgcore.min.js"></script>
    	<script type="text/javascript" src="time/lhgcalendar.min.js"></script>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
		
</head>
<body>
	<!-- 头部 -->
	<div id="header" class="clearfix"></div>
	
	<div class="particulars">
			<div class="frame">
			<div class="certifiedStatus">
					<ul class="clearfix">
						<li><a class="os_on" data-code="01">待审核</a></li>
						<li><a data-code="02">已认证</a></li>
						<li><a data-code="03">审核不通过</a></li>
					</ul>
				</div>
				<div class="certifiedStatus clearfix">
					<div class="moreFilterList-om">
						<p>
							<span>
								<label>证书名称</label>
								<select name="certType">
									<option value="">请选择</option>
								</select>
							</span>
							<span>
								<label>证书编号</label>
								<input type="text" name="certNo" value="" />
							</span>
							<span>
								<label>提交日期</label>
								<input type="text" class="runcode" id="addTimeLeft" value="" readonly="readonly" />
								~
								<input type="text" class="runcode"  id="addTimeRight" value="" readonly="readonly" />
							</span>
							<button>查询</button>
						</p>
					</div>
				</div>
				<!-- 查询条件结束 -->
				<!-- 证书list -->
				<table class="om-list">
					<thead>
						<tr>
							<td>序号</td>
							<td>提交时间</td>
							<td>家政员姓名</td>
							<td>证书名称</td>
							<td>证书等级</td>
							<td>发证机构</td>
							<td>证书编号</td>
							<td>发证日期</td>
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
			    callback: page_index
			});
		};
		function page_index(page_index){
			var certifiedStatus = $(".clearfix  li a.os_on").attr("data-code");
			var certType = $(".moreFilterList-om select[name='certType']").val();
			var certNo = $(".moreFilterList-om input[name='certNo']").val();
			var addTimeLeft = $(".moreFilterList-om input[name='addTimeLeft']").val();
			var addTimeRight = $(".moreFilterList-om input[name='addTimeRight']").val();
			var pageNumber = page_index+1;
			queryCertsList(pageNumber,pageSize,certifiedStatus,certType,certNo,addTimeLeft,addTimeRight);
		};
		//页面传值
		function queryCertsList(pageNumber,pageSize,certifiedStatus,certType,certNo,addTimeLeft,addTimeRight){
			$.ajax({
		        url:  publicPath+"/api/certs/QueryCertsList",
		        type: "post",
		        data: {  pageNumber:pageNumber,
		        	     pageSize:pageSize,
		        	     certType:certType,
		        	     certNo:certNo,
		        	     addTimeLeft:addTimeLeft,
		        	     addTimeRight:addTimeRight,
		        	     certifiedStatus:certifiedStatus},
		        dataType: "json",
		        ayscn:"false",
		        success: function success(data) {
		            if(data.success){
		                if(data.code == 0){
		                	var dataJD = data.jsonData.rows;
		                	var html = "";
		                	var n = 0;
		                	$.each(dataJD, function(i,item) {
		                		
		                		n++;
		                		html += "<tr data-id='"+item.id+"'>"+
									"<td>"+n+"</td>"+
									"<td>"+item.addTime+"</td>"+
									"<td>"+item.staffName+"</td>"+
									"<td>"+item.certType+"</td>"+
									"<td>"+item.authenticateGrade+"</td>"+
									"<td>"+item.certificationBody+"</td>"+
									"<td>"+item.certNo+"</td>"+
									"<td>"+item.certificationDate+"</td>"+
								"</tr>"
		                	});
		                	$(".om-list tbody").html(html);
		                	console.log(data.msg);
		                }else{
		                    console.log(data.msg);
		                }
		            }
		        }
		    });
		};
		
		
		$(function(){
				//头部引入
				$('#header').load('header.jsp',function(){
					$(".navigator ul li a").removeClass("navi-on");
					$(".navigator ul li").eq(2).children().addClass("navi-on");
				});
				J('#addTimeLeft').calendar({maxDate:'#addTimeRight'});
				J('#addTimeRight').calendar({minDate:'#addTimeLeft'});
				//获取总页数
				$.ajax({	
			        url:  publicPath+"/api/certs/QueryCertsList",
			        type: "post",
			        data: {
			        	certifiedStatus:$(".clearfix  li a.os_on").attr("data-code"),
			        	pageNumber:pageNumber,
			        	pageSize:pageSize},
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
				
				//证书状态
				$(".certifiedStatus ul li").click(function(){
					$(".certifiedStatus ul li a").removeClass("os_on");
					$(this).children().addClass("os_on");
					var os = $(this).index();
					var certifiedStatus="";
					switch (os){
						case 0:
							certifiedStatus="01";
							break;
						case 1:
							certifiedStatus="02";
							break;
						case 2:
							certifiedStatus="03";
							break;
						default:
							break;
					};
					//获取总页数
					$.ajax({	
				        url:  publicPath+"/api/certs/QueryCertsList",
				        type: "post",
				        data: {
				        	pageNumber:pageNumber,
				        	pageSize:pageSize,
				        	certifiedStatus:certifiedStatus},
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
				// 条件查询按钮
				$(".moreFilterList-om button").click(function(){
					var certType = $(".moreFilterList-om select[name='certType']").val();
					var certNo = $(".moreFilterList-om input[name='certNo']").val();
					var addTimeLeft = $(".moreFilterList-om input[name='addTimeLeft']").val();
					var addTimeRight = $(".moreFilterList-om input[name='addTimeRight']").val();
					var certifiedStatus = $(".clearfix  li a.os_on").attr("data-code");
					$.ajax({
				        url:  publicPath+"/api/certs/QueryCertsList",
				        type: "post",
				        data: { pageNumber:pageNumber,
				        		pageSize:pageSize,
				        		certType:certType,
			        	     	certNo:certNo,
			        	     	addTimeLeft:addTimeLeft,
			        	     	addTimeRight:addTimeRight,
			        	     	certifiedStatus:certifiedStatus},
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
				//证书详细
				$(".frame").on("click",".om-list tbody tr",function(){
					var id= $(this).attr("data-id");
					location.href="staffCertDetil.jsp?id="+id; 
				});
				//证书名称下拉列表
				certType();
				function certType(){
					$.ajax({
				        url:  publicPath+"/api/common/getDictionaryData/c016",
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
				                	$("select[name='certType']").html("<option value=''>请选择</option>"+html);
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
</html>