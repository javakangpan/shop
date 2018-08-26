<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath(); 
	String basePath = request.getScheme() + "://" + request.getServerName() 
	+ ":" + request.getServerPort() + path + "/";
	
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	#orderlist{
		width: 650px;
		margin:0 auto;
		font-size:12px;
		color:#205595;
		text-algin:center;}
	#orderlist table{
		margin:0 auto;
	}
	table,tr,td,th{
		
		height:30px;
		width:600px;
		border:1px solid #33659F;
		border-collapse: collapse;
	}
	#orderlist table tr td{
		text-algin:center;
		color:#205595;
	}
	#orderlist table th{
		background:#33659F;
		color:white;
	}
	#orderlist table caption{
		font-size:18px;
		color:#33659F;
		font-weight: bold;
		margin-bottom: 20px;
	}
</style>
</head>
<body>
	<div id="orderlist">
		<table>
			<caption>我的订单</caption>
			<tr>
				<th>订单编号</th>
				<th>下单时间</th>
				<th>种类数</th>
				<th>总件数</th>
				<th>总价格</th>
				<th>审核状态</th>
				<th>操作</th>
			</tr>
			<c:forEach var="order" items="${model.list }">
				<tr>
					<td>${order.orderid }</td>
					<td>${order.orderdate }</td>
					<td>${order.alltype }</td>
					<td>${order.quantity }</td>
					<td>${order.totalprice }</td>
					<td>${order.status eq 0?"未审核":order.status eq 1?"通过":"未通过" }</td>
					<td>
						<a href="#">查看订单</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div id="pagebar">
			<form id="PageForm" action="OrderServlet" method="post">
				<input id="currentPage" type="hidden" name="currentPage" value="${model.currentPage }"/>
				<input id="pageSize" type="hidden" name="pageSize" value="${model.pageSize }"/>
			</form> 
			<%@ include file="../../pageBar.jsp" %>
		</div>
	</div>
</body>
</html>