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
	<%
		//?
		String msg = (String)request.getAttribute("msg");
		String str = "top.jsp";
	%>
	
		<jsp:include page="<%=str %>">
		
		<jsp:param value="1" name="flag"/>
		
		</jsp:include>
		
		<div class="container" id="main">
			<div class="main_left">
				<div class="ml_font">
					<span>节日大促销</span>
				</div>
				<div class="main_left_top">

					<div class="mltop_01">
						<span>公告栏</span>
					</div>
				</div>
				<div class="main_left_down">
					<div class="mldown_01">
						<span>商品分类</span>
					</div>
					<div>
						
						<dl>
							<dt></dt>
							<dd>
								<ul class="nav nav-pills nav-stacked">
									<li>
										<a href="#">计算机</a>
									</li>
									<li>
										<a href="#"><img src="img/agree_item.gif" />笔记本</a>
									</li>
									<li>
										<a href="#"><img src="img/agree_item.gif" />台式机</a>
									</li>
									<li>
										<a href="#"><img src="img/agree_item.gif" />掌上电脑</a>
									</li>

								</ul>
							</dd>
						</dl>
						<dl>
							<dt></dt>
							<dd>
								<ul class="nav nav-pills nav-stacked">
									<li>
										<a href="#">计算机</a>
									</li>
									<li>
										<a href="#"><img src="img/agree_item.gif" />笔记本</a>
									</li>
									<li>
										<a href="#"><img src="img/agree_item.gif" />台式机</a>
									</li>
									<li>
										<a href="#"><img src="img/agree_item.gif" />掌上电脑</a>
									</li>

								</ul>
							</dd>
						</dl>

					</div>
				</div>
			</div>
			<div class="main_right">
				<div class="main_right_marquee">

					<marquee onMouseOut="this.start()" onMouseOver="this.stop()">好消息！全场5折！！！</marquee>
				</div>
				
				
				<c:forEach var="mc" items="${model.list }">
				<div class="main_right_c1">
					<div class="mrc_01"><img src="upload/${mc.pic }" height="120px" width="150px" /></div>
					
					
				
					
					
					<div class="mrc_02">
						
						<table class="table table-striped">
							<caption></caption>
							<thead>
								<tr>
									<th>${mc.mcname }</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>单价：<span style="color: red;">￥${mc.price }</span></td>
								</tr>
								<tr>
									<td>是否缺货：${mc.flag eq 0?'否':'是' }</td>
								</tr>
								<tr>
									<td>${mc.mcdecx }</td>
								</tr>

							</tbody>
						</table>
						
					</div>
					
					<div class="mrc_03">
						<div class="mrc_03_1"><img src="img/search.gif" />
							<a>查看大图</a>
						</div>
						<div class="mrc_03_2">
							<a href="#"><img src="img/detail.jpg" /></a>
						</div>
						<div class="mrc_03_3">
						 <a href="ShopCarServlet?task=add&mcid=${mc.mcid }">
                        	<img src="img/pay.jpg"/>
                        </a>
						</div>
					</div>
					
				</div>
				
				</c:forEach>
			</div>
		</div>
		<div class="container" id="footer">
			  	<form id="pageForm" action="HomeServlet" method="post">
				<input id="currentPage" name="currentPage" value="${model.currentPage }" type="hidden"/>
				<input id="pageSize" name="pageSize" value="${model.pageSize }" type="hidden"/>
			</form> 
         	<%@ include file="../pageBar.jsp" %>

		</div>
		
	</body>

</html>