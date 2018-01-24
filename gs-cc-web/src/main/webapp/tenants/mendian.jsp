<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>门店管理</title>
</head>
<body>
<form action="/fbzkConsole_web/api/tenants/tenantsInfoquery" method="post">
门店名称<input type="text" id="tenants_name" />
门店状态<select id="tenants_status">
	   	<option>正常</option>
	   </select>
开通时间<input type="" id="" value="2017/2/21" />
<button id="btn" onclick="submit1()">查询</button>	
</form>   
</body>
</html>