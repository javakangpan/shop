<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+
			":"+request.getServerPort()+path+"/";
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
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

function $(id){
	return document.getElementById(id);
}
function del(id){
	
	if(confirm("你确认删除吗?")){
		location.href="back/McServlet?task=delete&mcid="+id;
	}
	
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
        <li class="click"><a href="back/mc/mcadd.jsp"><span><img src="images/t01.png" /></span>添加</a></li>
        <li>
       
        
        	<form action="back/McServlet?task=query" method="post">
        	<input  type="text" name="mcname" value="${mcname }"/>
        	<input type="submit" value="搜索" />
        	</form>
        
        
        
        
        </li>
  
        </ul>
        
        
        <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    
    
    <table class="imgtable">
    
    <thead>
    <tr>
    	<th>编号</th>
	    <th width="100px;">商品图片</th>
	    
	    <th>名称</th>
	    <th>单价</th>
	    <th>是否缺货</th>
	    <th>操作</th>
    </tr>
    </thead>
    
    <tbody>
    <c:forEach var="mc" items="${model.list }">
	    <tr>
	    <td>${mc.mcid }</td>
		    <td class="imgtd"><img src="upload/${mc.pic }" /></td>
		    <td>
			    <a href="#">${mc.mcname }</a>
			    <p>发布时间：${mc.createdate }</p>
		    </td>
		    <td>${mc.price }</td>
		    <td>${mc.flag eq 0?'否':'是' }</td>
		    <td><a href="back/McServlet?task=queryForUpdate&mcid=${mc.mcid }" class="tablelink">修改</a> 
				<a href="javascript:del(${mc.mcid })"class="tablelink"> 删除</a>
			</td>
	    </tr>
	</c:forEach>
    </tbody>
    
    </table>
    
    
    <div class="pagin">
    	<form id="pageForm" action="back/McServlet?task=query" method="post">
			<input id="currentPage" name="currentPage" value="${model.currentPage }" type="hidden"/>
			<input id="pageSize" name="pageSize" value="${model.pageSize }" type="hidden"/>
		</form> 
    	<%@ include file="../../pageBar2.jsp" %>
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
    
<script type="text/javascript">
	$('.imgtable tbody tr:odd').addClass('odd');
	</script>
    
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
