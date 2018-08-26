<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script language="javascript">
	$(function() {
		//导航切换
		$(".imglist li").click(function() {
			$(".imglist li.selected").removeClass("selected")
			$(this).addClass("selected");
		})
	})
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});

	});
	
	function $(id){
		return document.getElementById(id);
	}
	function del(id){
		//确认执行 true
		if(confirm("你确认删除吗?")){
			location.href="back/McServlet?task=delete&mcid="+id;
		}
		
	}
	
	
	
	
	function firstPage(){
		
		
		$("currentPage").value = 1;
		$("pageForm").submit();
		/* location.href="back/McServlet?task=query&currentPage=1&pageSize=${pageSize}"; */
	}
	
 	function backPage(){
		${"currentPage"}.value=${currentPage eq 1?1:currentPage-1};
		$("pageForm").submit();
		
	/* location.href = "back/McServlet?task=query&currentPage=${currentPage eq 1?1:currentPage-1}&pageSize=${pageSize}"; */
	}  

 	function nextPage() {
		
		${"currentPage"}.value=${currentPage eq  pageCount?pageCount:currentPage + 1};
		$("pageForm").submit();
		/* location.href = "back/McServlet?task=query&currentPage=${currentPage eq  pageCount?pageCount:currentPage + 1}&pageSize=${pageSize}"; */
			
		

	} 

 	function lastPage() {
		${"currentPage"}.value=${pageCount};
		$("pageForm").submit();
		/* location.href = "back/McServlet?task=query&currentPage=${pageCount}&pageSize=${pageSize}"; */
	} 
	

	
	
 	function changeCurrentPage(){
		
		var currPage = document.getElementById("currPage").value;
		
		currPage= currPage> ${pageCount} ?${pageCount} :currPage;
		currPage=currPage<1?1:currPage;
		
		${"currentPage"}.value=currPage;
		$("pageForm").submit();
		
		/* location.href = "back/McServlet?task=query&currentPage="+currPage+"&pageSize=${pageSize}"; */
	} 
	

	
	 function changePageSize(pageSize){
		$("pageSize").value=pageSize;
		$("currentPage").value = 1;
		$("pageForm").submit();
		
	
		/* location.href = "back/McServlet?pageSize="+pageSize; */
		/* location.href = "back/McServlet?pageSize="+pageSize+"&currentPage=${currentPage}"; */
	} 

	
</script>
</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">图片列表</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<li><span><img src="images/t01.png" /> 
				</span><a href="back/mc/form.jsp">添加</a></li>
				<li class="click"><span><img src="images/t02.png" /></span>修改</li>
				<li><span><img src="images/t03.png" /></span>删除</li>
				<li><span><img src="images/t04.png" /></span>统计</li>
			</ul>


			<ul class="toolbar1">
				<li><span><img src="images/t05.png" /></span>设置</li>
			</ul>

		</div>


		<table class="imgtable">

			<thead>
				<tr>
					<th>编号</th>
					<th width="100px;">图片</th>

					<th>商品名称</th>
					<th>商品单价</th>
					<!-- 	<th>商品数量</th> -->
					<th>是否缺货</th>

					<th>操作</th>
				</tr>
			</thead>

			<tbody>



				<c:forEach var="mc" items="${list }">
					<tr>
						<td style="text-align: center;" width=80px>${mc.mcid }</td>
						<td height="120px">${mc.pic }</td>
						<%-- <td style="text-align:left;" width=400px>${mc.mcdecx }</td> --%>
						<td>${mc.mcname }<p>${mc.createdate }</p></td>
						<td>${mc.price }</td>
						<%-- <td>${mc.quantity }</td>  --%>
						<td>${mc.flag eq 0?'否':'是' }</td>
						<%-- 	<td>${mc.smalltypeid }</td>
						<td>${mc.createdate }</td> --%>

						<td><a
							href="back/McServlet?task=queryForUpdate&typeid=${mc.mcid }"
							class="tablelink">修改</a> <a href="javascript:del(${mc.mcid })"
							class="tablelink"> 删除</a></td>
					</tr>
				</c:forEach>






			</tbody>

		</table>






		<div class="pagin">
			<div class="message">
				共<i class="blue">${totalCount }</i>条记录，当前显示第&nbsp;<i class="blue">${currentPage }/${pageCount }</i>页
				
				
				
				<input id="currPage" type="number"
					value=${currentPage eq pageCount?pageCount:currentPage+1 } min="1"
					max="${pageCount }" /> 
				<input type="button" value="确定" onclick="changeCurrentPage()" />
				
			<!--  -->
				<select onchange="changePageSize(this.value)">
					<c:forEach var="p_size" begin="1" end="${totalCount }" step="1">

						<option value="${p_size }" ${pageSize eq p_size? "selected" : ""}>${p_size }</option>

					</c:forEach>

				</select>
		    <!--  -->

			</div>
			<form id="pageForm" action="back/McServlet?task=query" method="post">
				<input id="currentPage" name="currentPage" value="${currentPage }" type="hidden" /> 
				<input id="pageSize" name="pageSize" value="${pageSize }" type="hidden" />
			</form>

			<ul class="paginList">


				<li class="paginItem"><a href="javascript:firstPage();">首页</a></li>
				<li class="paginItem current"><a href="javascript:backPage();">上页</a></li>
				<li class="paginItem"><a href="javascript:nextPage();">下页</a></li>
				<li class="paginItem"><a href="javascript:lastPage();">尾页</a></li>

			</ul>
		</div>


		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>

			<div class="tipinfo">
				<span><img src="images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />&nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>

		</div>




	</div>

	<div class="tip">
		<div class="tiptop">
			<span>提示信息</span><a></a>
		</div>

		<div class="tipinfo">
			<span><img src="images/ticon.png" /></span>
			<div class="tipright">
				<p>是否确认对信息的修改 ？</p>
				<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
			</div>
		</div>

		<div class="tipbtn">
			<input name="" type="button" class="sure" value="确定" />&nbsp; <input
				name="" type="button" class="cancel" value="取消" />
		</div>

	</div>

	<script type="text/javascript">
		$('.imgtable tbody tr:odd').addClass('odd');
	</script>

	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>
