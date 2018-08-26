<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() 
	+ ":" + request.getServerPort() + path + "/";
	
	
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下订单</title>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<style type="text/css">
	#order{
		width: 900px;
		margin:0 auto;
	}
	#order table{
		width:600px;
		margin:0 auto;
		}
	#order table,tr,td,th{
		border:0px solid #D0F3FF;
		border-collapse: collapse;
	}
	tr,td,th{
		height:30px;
		line-height:30px;
		
	}
	#order table tr td{width: 200px;color:black;}
	#order table tr td.left{
		background:#fffff;
		
		text-align: right;
		padding-right:50px;
	}
	table caption{
		font-size:18px;
		color:#33659F;
		font-weight: bold;
		text-align:center;
		margin-bottom: 20px;
	}
</style>
</head>
<body>
	<jsp:include page="../top.jsp"></jsp:include>
	
	<div id="order">
		<form action="front/page/OrderServlet?task=add" method="post">
		<table>
			<caption>下订单</caption>
			<tr>
				<td class="left">所购商品的种类数</td>
				<td  colspan="2">${SHOP_CAR.totalType }</td>
			</tr>
			<tr>
				<td class="left">所购商品的总件数</td>
				<td  colspan="2">${SHOP_CAR.totalCount }</td>
			</tr>
			<tr>
				<td class="left">所购商品的总价格</td>
				<td  colspan="2">${SHOP_CAR.totalPrice }</td>
			</tr>
			<tr>
				<td class="left">订单日期</td>
				<td  colspan="2">
					<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<td class="left">付款方式</td>
				<td  colspan="2">
					<select name="paytype">
						<option value="在线支付">在线支付</option>
						<option value="货到付款">货到付款</option>
						<option value="网银支付">网银支付</option>
						<option value="分期支付">分期支付</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="left">收货方式</td>
				<td  colspan="2">
					<select name="receivedtype">
						<option value="平邮">平邮</option>
						<option value="快递">快递</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<hr style="border:0.2px dotted #ccc;"/>
				</td>
			</tr>
			<tr>
				<td class="left">收货人姓名</td>
				<td>
					<input value="${FRONT_USER.username }" type="text" name="username"/>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td class="left">收货人地址</td>
				<td>
					<input value="${FRONT_USER.address }" type="text" name="address"/>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td class="left">收货人邮编</td>
				<td>
				<input value="${FRONT_USER.postcade }" type="text" name="postcode"/>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td class="left">收货人电话</td>
				<td>
				<input value="${FRONT_USER.phoneno }" type="text" name="phoneno"/>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td class="left">收货人邮箱</td>
				<td>
				<input value="${FRONT_USER.email }" type="text" name="email"/>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="submit" value="下订单"/> 
					<input style="margin-left:30px;margin-top:10px;" type="reset" value="重置"/>
				</td>
			</tr>
		</table>
		</form>
	</div>
	
</body>
</html>