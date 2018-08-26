<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//项目的根目录   /hdshop
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//   http://localhost:8888/hdshop/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>




<script type="text/javascript">
	$(document).ready(function(e) {
		$(".select1").uedSelect({
			width : 345
		});
		$(".select2").uedSelect({
			width : 167
		});
		$(".select3").uedSelect({
			width : 100
		});
	});
</script>

</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">表单</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="back/McTypeServlet?task=update" method="post">
			<ul class="forminfo">
				<li><label>类别名称</label> 
				
				
				<input name="typeid" value="${McType.typeid }" type="hidden"></input>
				
				
				<input name="typename" 
				
				value="${McType.typename }"  type="text" class="dfinput" />
				
				 <i>标题不能超过30个字符</i></li>


				<li><label>父类别名<b>*</b></label>
					<div class="vocation" >
						<select class="select1" name="fatherid">
						<option value="0">无</option>
							<c:forEach var="big" items="${fatherlist }">
							
								<option value="${big.typeid }"
								${big.typeid eq McType.fatherid ?"selected":""}
								
								>
								${big.typename }</option>
								
							</c:forEach>

						</select>
					</div></li>
				<li><label>&nbsp;</label> <!--   <input name="" type="button" class="btn" value="确认保存"/></li> -->
					<input name="" type="submit" class="btn" value="确认保存" /></li>
			</ul>
		</form>

	</div>


	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>
