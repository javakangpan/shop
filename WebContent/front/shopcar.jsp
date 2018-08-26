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
<title>购物车</title>
<!-- <link rel="stylesheet" type="text/css" href="css/main.css"/> -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<style>


table,tr,td,th{
		border:0px solid #337AB7;
		border-collapse: collapse;
		width:700px;
		color:#31629D;
		
		
		
	}
	tr{height: 25px;}
	th{background:#337AB7;color:#ffffff;}
	/* td{text-align: center;vertical-align:middle} */
	td{text-align: center;}
	td input{
		width:100px;
	}
	table caption{
	
		font-size:20px;
		font-weight: bold;
		margin-top:30px;
		margin-bottom: 20px;
		text-align:center;
		
	}




/* 	#shopcar{
		width: 900px;
		margin:0 auto;
		font-size:12px;
		color:#337AB7;
		text-algin:center;}
	#shopcar table{
		margin:0 auto;
	}
	table,tr,td,th{
		
		height:30px;
		width:600px;
		border:1px solid #337AB7;
		border-collapse: collapse;
	}
	#shopcar table tr td{
		text-algin:center;
		color:#337AB7;
	}
	#shopcar table th{
		text-algin:center;
		background:#337AB7;
		color:#ffffff;
	}
	#shopcar table td input{
		width: 60px;
	}
	#shopcar table caption{
		
		font-size:18px;
		color:#337AB7;
		font-weight: bold;
		margin-bottom: 20px;
	} */
</style>

	<script type="text/javascript">
	function updateCount(count,mcid){
		location.href = "ShopCarServlet?"
				+"task=update&count="+count+"&mcid="+mcid;
	}
	</script>
	
</head>
<body>
   <jsp:include page="top.jsp">
		<jsp:param value="5" name="flag"/>
	</jsp:include>
	
	<div  class="container">
	
		<table class="table table-hover table-striped ">
			<caption></caption>
			  
			<tr>
				<th style="text-align:center;">缩略图</th>
				<th style="text-align:center;">商品名称</th>
				<th style="text-align:center;">商品单价</th>
				<th style="text-align:center;">商品数量</th>
				<th style="text-align:center;">商品小计</th>
				<th style="text-align:center;">操作</th>
			</tr>
			<c:forEach var="mc" items="${SHOP_CAR.list }">
			<tr>
				<td style="text-align:center;">
					<img src="upload/${mc.pic }" width="60" height="60"/>
				</td>
				<td style="vertical-align:middle;">
					${mc.mcname }
				</td>
				<td style="vertical-align:middle;">
					${mc.price }
				</td>
				<td style="vertical-align:middle;">
					<input onclick="updateCount(this.value,${mc.mcid})" value="${mc.count }" type="number" min="1" name="count"/>
				</td >
				<td style="vertical-align:middle;">${mc.totalPrice }</td>
				<td style="vertical-align:middle;"><input onclick="location.href='ShopCarServlet?task=delete&mcid=${mc.mcid}'" type="button" class="btn btn-primary" value="删除"/></td>
			</tr>
			</c:forEach>
		</table>
		<!-- <div  style="width:600px;margin:20px auto;height: 30px; line-height:30px; border: 0px solid #337AB7; color:#337AB7;"> -->
		<div>
			<div style="float:left; color:#337AB7;">
			商品总类数：${SHOP_CAR.totalType }
			|
			商品总件数：${SHOP_CAR.totalCount }
			|
			商品总价格：${SHOP_CAR.totalPrice }
			</div>
			<div style="float:right;">
				<input  type="button" class="btn btn-primary" value="清空购物车" 
				onclick="location.href='ShopCarServlet?task=clear'"/>
				<input  type="button" class="btn btn-primary" value="继续购物" onclick="location.href='index.jsp'"/>
				<input  type="button" class="btn btn-primary" value="结算中心" onclick="location.href='front/filter/order.jsp'"/>
			</div>
		</div>
		
	</div>
	
</body>
</html>