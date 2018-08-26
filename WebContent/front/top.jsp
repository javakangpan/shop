<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
	
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+
			":"+request.getServerPort()+path+"/";
	
	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	
	<head>
	<base href="<%=basePath %>"/>
		<meta charset="utf-8" />
		<title></title>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/html.css" />
		
	</head>

	<body>
<div class="container" id="header">

			<div id="top">
				<div class="top_header">
					<div class="top_left">
						您好！欢迎来到
						<b style="color:blue;">${FRONT_USER.truename }</b>
						店铺！
						
					${empty FRONT_USER?'<a href="frontLogin.jsp">请登录</a> ':'<a href="LogoutServlet?task=front">注销</a>'}	|
						<a href="front/regist.jsp">免费注册</a>
					</div>
					<div class="top_right">
						<ul class="nav nav-tabs">
							<li>
								<a>我的订单</a>
							</li>
							<li>
								<a>我的一号店</a>
							</li>
							<li>
								<a>帮助中心</a>
							</li>
							<li>
								<a>联系客服</a>
							</li>
							<li>
								<a>加入收藏</a>
							</li>

							<li class="li_style">
								<a>联系热线：<span>400-8888-666</span></a>
							</li>
						</ul>

					</div>
				</div>
			</div>
			<div id="logo">
				<div class="logo_left"></div>
				<div class="logo_right">
					<ul class="nav nav-pills">
						
						<li class="${param.flag eq 1?'active':'' }">
							<a href="index.jsp">首页</a>
						</li>
						<li>
							<a href="index.jsp">积分兑换</a>
						</li>
						<li>
							<li  class="${param.flag eq 2?'active':'' }"><a href="front/filter/member.jsp">会员中心</a>
						</li>
						<li>
							<a href="#">查看订单</a>
						</li>
					</ul>
				</div>
			</div>
			<div id="search">
				<div class="search_01">
					<span>所有商品分类</span> &nbsp;&nbsp;&nbsp;
					<img src="img/agree_item.gif" />
				</div>
				<div class="search_02">
					<form class="navbar-form " role="search" action="HomeServlet" method="post">
						<div class="form-group">
							<input value="${mcname }" name="mcname" type="text" class="form-control" placeholder="Search">
						</div>
						<button type="submit" onclick="alert('进行搜索')" class="btn btn-default"  >搜索</button>
					</form>
				</div>
				<div class="search_03">
					<span><a href="front/shopcar.jsp">购物车：${SHOP_CAR eq null?0:SHOP_CAR.totalCount }</a>件</span>

				</div>
				<div class="search_04"><button type="button" class="btn btn-default">去结算>></button></div>

			</div>
		</div>
			</body>

</html>