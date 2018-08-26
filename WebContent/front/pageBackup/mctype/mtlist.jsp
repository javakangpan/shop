<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//项目的根目录   /hdshop
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+
			":"+request.getServerPort()+path+"/";
	//   http://localhost:8888/hdshop/
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});

//执行删除操作
function del(id){
	//确认执行 true
	if(confirm("你确认删除吗?")){
		location.href="back/McTypeServlet?task=delete&typeid="+id;
	}
	
}
function firstPage(){
	
	location.href="back/McTypeServlet?task=query&currentPage=1";
}

function backPage(){
	
	
location.href = "back/McTypeServlet?task=query&currentPage=${currentPage eq 1?1:currentPage-1}";
}
function nextPage() {

	location.href = "back/McTypeServlet?task=query&currentPage=${currentPage eq  pageCount?pageCount:currentPage + 1}";
		
	

}
function lastPage() {
	location.href = "back/McTypeServlet?task=query&currentPage=${pageCount}";
}

</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">数据表</a></li>
    <li><a href="#">基本内容</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <!-- <li class="click"><span><img src="images/t01.png" /></span> -->
        <li ><span><img src="images/t01.png" /></span>
        <a href="back/McTypeServlet?task=queryForFather">添加</a></li>
        <li class="click"><span><img src="images/t02.png" /></span>
        <a href="#">修改</a></li>
        <li><span><img src="images/t03.png" /></span>删除</li>
        <li><span><img src="images/t04.png" /></span>统计</li>
        </ul>
        
        
        <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>类别名称</th>
        <th>父类编号</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="mt" items="${list }">
        	<!-- 将所有的大类信息查询处理 
        		当fatherid为0时 表示是大类
        	-->
        	<c:if test="${mt.fatherid eq 0 }">
		        <tr>
		        <td><input name="" type="checkbox" value="" /></td>
		        <td>${mt.typeid }</td>
		        <td>${mt.typename }</td>
		        <td>${mt.fatherid }</td>
		        <td><a href="back/McTypeServlet?task=queryForUpdate&typeid=${mt.typeid }" class="tablelink">修改</a>    
		         <a href="javascript:del(${mt.typeid })" class="tablelink"> 删除</a></td>
		        </tr>  
		        <!-- 根据大类查询所属的小类
		        	如果类别的fatherid等于大类的typeid表示属于该大类下的小类
		         -->
		         <c:forEach var="small" items="${list }">
		         	<c:if test="${small.fatherid eq mt.typeid }">
		         		<tr>
				        <td><input name="" type="checkbox" value="" /></td>
				        <td>${small.typeid }</td>
				        <td align="center">${small.typename }</td>
				        <td>${small.fatherid }</td>
				        <td><a href="back/McTypeServlet?task=queryForUpdate&typeid=${small.typeid }" class="tablelink">修改</a>     
				        <a href="javascript:del(${small.typeid })" class="tablelink"> 删除</a></td>
				        </tr>  
		         	</c:if>
		         </c:forEach>
	        </c:if>
         </c:forEach>
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue">${totalCount }</i>条记录，当前显示第&nbsp;<i class="blue">1</i>页
    	
    	
    <%-- 	<input id="currPage" type="number" 
				value=${currentPage eq pageCount?pageCount:currentPage+1 } min="1" max="${pageCount }"/>
		<input  type="button" value="确定" onclick="changeCurrentPage()"/> --%>
    	
    	
    	</div>
        
        
        
        <ul class="paginList">
        <li class="paginItem"><a href="javascript:firstPage();">首页</a></li>
		<li class="paginItem current"><a href="javascript:backPage();">上页</a></li>
		<li class="paginItem"><a href="javascript:nextPage();">下页</a></li>
		<li class="paginItem"><a href="javascript:lastPage();">尾页</a></li>
      
        </ul>
    </div>
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
