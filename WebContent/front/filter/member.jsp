<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath(); 
	String basePath = request.getScheme() + "://" + request.getServerName() 
	+ ":" + request.getServerPort() + path + "/";
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

 <link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="stylesheet" href="css/html.css" />
		

</head>
<body>



<jsp:include page="../top.jsp">
	<jsp:param value="2" name="flag"/>
</jsp:include>
<div class="container" id="main">
	<div class="main_left">
				
				
				<div class="main_left_down">
					<div class="mldown_01">
						<span>商品分类</span>
					</div>
					<div>
						
						<dl>
							
							<dd>
							<ul class="list-group">
								
                   			<li class="list-group-item"><a href="#" target="myiframe">基本资料显示</a></li>
                   			<li class="list-group-item"><a href="#" target="myiframe">用户资料修改</a></li>
                   			<li class="list-group-item"><a href="#" target="myiframe">密码修改</a></li>
                  			 <li class="list-group-item"><a href="front/page/OrderServlet" target="myiframe">我的订单</a></li>
                 			</ul>

							
							</dd>
						</dl>
					

					</div>
				</div>
			</div>
   
   <div id="right">
     <iframe id="myiframe" name="myiframe" frameborder="0" width="700" height="500">
     </iframe>
   </div>
</div>
</body>
</html>