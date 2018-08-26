
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="pers.kp.model.McBean"%>
<%
	//项目的根目录   /hdshop
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//   http://localhost:8888/hdshop/
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<base href="<%=basePath%>" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body onload="getMcTypeFather()">

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

		 
		<% McBean mc=(McBean)request.getAttribute("mc"); %>
	
		<%--  ${requestScope.mc};  --%>
		

		<form method="post" enctype="multipart/form-data"
			action="back/McServlet?task=add">


			

				<ul class="forminfo">
				
					
				
					<li>
						<label>商品名称</label> 
						<input name="mcname" type="text" class="dfinput" value=<%=(mc.getMcname()) %> / >
					</li>
					<li>
						<label>所属大类</label> 
						<select class="dfinput" id="fatherid"name="fatherid" onchange="getMctypeSmall(this.value)">
							<option>--请选择--</option>
						</select>
					</li>
					<li>
						<label>所属小类</label> 
						<select class="dfinput" id="smallid" name="smallid">
							<option>--请选择--</option>
						</select>
					</li>
					<li>
						<label>商品价格</label> 
						<input name="price" type="text" class="dfinput" value=<%=(mc.getPrice()) %> />
					</li>

					<li>
						<label>商品图片</label>
						<cite> <input name="pic" type="file" value=<%=(mc.getPic()) %> /></cite>
					</li>
					<li>
						<label>商品描述</label> 
						<textarea name="mcdecx" cols="" rows="" class="textinput"><%=mc.getMcdecx()%>
						</textarea>
					</li>
					<li>
						<label>商品数量</label> 
						<input name="quantity" type="text"  class="dfinput"  value=<%=mc.getQuantity() %> />
					</li>
					<li>
						<label>是否缺货</label>
						<cite> 
						<%
							if("1".equals(mc.getFlag())){
						%>
							<input name="flag" type="radio" value="1" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="flag" type="radio" value="0"  />否
						<%
							}else if("0".equals(mc.getFlag())){
						%>
							<input name="flag" type="radio" value="1"  />是&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="flag" type="radio" value="0"  checked="checked"/>否
						<%
							}
						%>
					    </cite>
					</li>
					<li>
					<input name="" type="submit" class="btn" value="提交" /> 
					<label>&nbsp;</label>
					<input name="" type="reset" class="btn" value="重置" />
					</li>
				</ul>

			


		</form>


	</div>


	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
<script>
	function $(id) {
		return document.getElementById(id);
	}
	function $$(note) {
		return document.createElement(note);
	}

	function getMctypeSmall(typeid) {
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("POST", "back/McServlet?task=querySun&typeid=" + typeid,
				true);
		xmlhttp.send();

		xmlhttp.onreadystatechange = function() {

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				var data = xmlhttp.responseText;
				var small = eval(data);
				var s = $("smallid");
				s.length = 1;
				for (var i = 0; i < small.length; i++) {

					var o = $$("option");
					o.innerHTML = small[i].typename;
					o.value = small[i].typeid;
					s.appendChild(o);
				}
			}

		};
	}

	function getMcTypeFather() {

		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("POST", "back/McServlet?task=queryFather", true);
		xmlhttp.send();

		xmlhttp.onreadystatechange = function() {

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				var data = xmlhttp.responseText;
				var big = eval(data);
				var f = $("fatherid");

				for (var i = 0; i < big.length; i++) {

					var o = $$("option");
					o.innerHTML = big[i].typename;
					o.value = big[i].typeid;
					f.appendChild(o);
				}
			}

		};
	}
</script>
</html>
