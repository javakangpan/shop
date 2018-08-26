<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
	
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+
			":"+request.getServerPort()+path+"/";
	
	%>
		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
		<link rel="stylesheet" href="css/htmlregist.css" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/html.css" />
<body>
	<div class="container" id="main">
		
		<div id="web_right">
		<h3 align = center>请填写注册信息</h3>
				<div id="wr_table1">
				
					<form  action="#" method="get" onSubmit="return form()" >
						<table id="my_table1" class="table table-hover table-striped  table-bordered">
							<tr>
								<td class="td1">用户名:</td>
								<td><input class="form-control" type="text" name="usernam" id="userName" value="" onkeyup="fun31()" onblur="fun32()" onfocus="fun33()" onmouseout="fun34()"/></td>
								<td id="tdwidth"><span id="userNameSpan">必须是英文字母或数字，长度3-15</span></td>
							</tr>
							<tr>
								<td class="td1">密码:</td>
								<td><input class="form-control" type="password" name="password" id="pwd1" value="" onkeyup="fun41()" onblur="fun42()" onmouseout="fun43()" onfocus="fun44()" onmouseover="fun45()"/></td>
								<td><span id="pwdSpan1">长度3-15</span></td>
							</tr>
							<tr>
								<td class="td1">确认密码:</td>
								<td><input class="form-control" type="password" name="certifierPassword" id="pwd2" value="" onfocus="fun51()" onblur="fun52()" onkeyup="fun53()" onmouseout="fun54()"/></td>
								<td><span id="pwdSpan2">值要和密码框的值相同</span></td>
							</tr>
							<tr>
								<td class="td1">性别:</td>
								<td><input type="radio" name="sex" id="sex1" value="男" onclick="fun61()"  checked="checked"/>男
									<input type="radio" name="sex" id="sex2" value="女" onclick="fun61()" />女
								</td>

								<td><span id="sexSpan">只能是男或女</span></td>
							</tr>
							<tr>
								<td  class="td1">真实姓名:</td>
								<td><input class="form-control" type="text" name="trueName" id="trueName" value="" onkeyup="fun71()" onblur="fun72()" onfocus="fun73()" onmouseout="fun74()"/></td>
								<td><span id="trueNameSpan">中文,2-10个字符</span></td>
							</tr>
							<tr>
								<td class="td1">出生日期:</td>
								<td><input class="form-control" type="text" name="birthday" id="birthDay" value="" onkeyup="fun81()" onfocus="fun82()" onblur="fun83()" onmouseout="fun84()"/></td>
								<td><span id="birthDaySpan">格式yyyy-dd,按此日期算出的年龄应大于等于10岁</span></td>
							</tr>
							<tr>
								<td class="td1">电子邮箱:</td>
								<td><input class="form-control" type="text" name="email" id="email" value="" onkeyup="fun91()" onfocus="fun92()" onblur="fun93()" onmouseout="fun94()"/></td>
								<td><span id="emailSpan">格式要正确</span></td>
							</tr>
							<tr>
								<td class="td1">电话号码:</td>
								<td><input class="form-control" type="text" name="phonenumber" id="phone" value="" onkeyup="fun101()" onfocus="fun102()" onblur="fun103()" onmouseout="fun104()"/></td>
								<td><span id="phoneSpan">必须是数字</span></td>
							</tr>
							<tr>
								<td class="td1">地址:</td>
								<td><input class="form-control" type="text" name="adresse" id="adresse" value="" onkeyup="fun201()" onfocus="fun202()" onblur="fun203()" onmouseout="fun204()"/></td>
								<td><span id="adresseSpan">长度不能大于100</span></td>
							</tr>
							<tr>
								<td class="td1">邮编:</td>
								<td><input class="form-control" type="text" name="postal" id="postal" value="" onkeyup="fun301()" onfocus="fun302()" onblur="fun303()" onmouseout="fun304()"/></td>
								<td><span id="postalSpan">6位数字</span></td>
							</tr>
						</table>
						<br />
					
						<input type="submit" class="btn_style" name="" id="" value="提交" />
						<input type="reset"  class="btn_style" name="" id="" value="重置" />
					</form>
				</div>
			</div>
		
	</div>
</body>
	<script type="text/javascript" src="js/htmlregist.js"></script>
</html>