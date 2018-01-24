<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	 $.ajax({
		// publicPath+ "
        url: publicPath+ "/api/user/getSession.do",
        type: "post",
        data: {},
        dataType: "json",
        success: function success(data) {
            if(data.success){
                if(data.code == 0){
                	var dataJD = data.jsonData;
                	if (dataJD == null) {
                		$(".account .login").html("<a href='login.jsp'>登录</a>");
                	} else{
                		$(".account .login").html("<a data-userid='"+dataJD.userId+"'>欢迎您,"+dataJD.userName+"</a><input type='hidden' name='' id='userId' value='"+dataJD.userId+"' />");
                	}
                	console.log(data.msg);
                }else{
                    console.log(data.msg);
                }
            }else{
                console.log(data.msg);
            }
        }
    }); 
    //退出
	$(".account ul li:last").click(function(){
		$.ajax({
			//publicPath+ 
	        url: publicPath+ "/api/user/logout",
	        type: "post",
	        data: {},
	        dataType: "json",
	        success: function success(data) {
	            if(data.success){
	                if(data.code == 0){
	                	location.href="login.jsp"
	                	console.log(data.msg);
	                }else{
	                    console.log(data.msg);
	                }
	            }else{
	                console.log(data.msg);
	            }
	        }
	    });
	});
})

</script>
</head>
<body>
<div class="logo"><img src="image/logo.png"/></div>
<div class="h-right">
	<div class="account">
		<ul class="clearfix">
			<li class="login"></li>
			<li><a>退出</a></li>
		</ul>
	</div>
	<div class="navigator">
		<ul class="clearfix">
			<li><a class="navi-on" href="index.jsp">门店管理</a></li>
			<li><a href="resOrderManagement.jsp">订单管理</a></li>
			<li><a href="StaffsCertsManagement.jsp">证书管理</a></li>
			<li><a href="customerManagement.jsp">客户管理</a></li>
			<li><a href="fundsManagement.jsp">财务管理</a></li>
		</ul>
	</div>
</div>
</body>
</html>