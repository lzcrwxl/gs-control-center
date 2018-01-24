<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单管理</title>
</head>
<body>
<form action="/fbzkConsole_web/api/funds/SelectClaerList" method="post">
订单编号<input type="text" id="orderNo"  />
订单状态<select id="order_status">
	   	<option>待处理</option>
	   	<option>已处理</option>
	   	<option>已完成</option>
	   	<option>已取消</option>
	   </select>
<button id="btn" onclick="submit1()">查询</button>
</form>
<form action="/fbzkConsole_web/api/orders/OrdersList" mehtod="post"></form>
</body>
</html>