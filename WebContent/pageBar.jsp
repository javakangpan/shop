<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<!-- 
		使用需知：
			1.哪个jsp页面需要进行分页处理，则在哪个页面中将pageBar.jsp包含到那个页面
			2.将以下的表单拷贝到使用分页处理的页面中，并根据需求改变表单提交的地址
	 -->
	<%-- 
	<form id="pageForm" action="back/McServlet" method="post">
		<input id="currentPage" name="currentPage" value="${model.currentPage }" type="hidden"/>
		<input id="pageSize" name="pageSize" value="${model.pageSize }" type="hidden"/>
	</form> 
	--%>

	<!-- 分页的栏目 -->
	共${model.totalCount }条记录
	第${model.currentPage }/${model.pageCount }页
	每页
	<%-- <select onchange="changePageSize(this.value)">
		<option value="5" ${model.pageSize eq 5?"selected":"" }>5</option>
		<option value="10" ${model.pageSize eq 10?"selected":"" }>10</option>
		<option value="15" ${model.pageSize eq 15?"selected":"" }>15</option>
		<option value="20" ${model.pageSize eq 20?"selected":"" }>20</option>
		<option value="50" ${model.pageSize eq 50?"selected":"" }>50</option>
	</select> --%>
		<select onchange="changePageSize(this.value)">
					<c:forEach var="p_size" begin="1" end="${model.totalCount }" step="1">

						<option value="${p_size }" ${model.pageSize eq p_size? "selected" : ""}>${p_size }</option>

					</c:forEach>

		</select>
	条记录
	到第
	<input id="currPage" type="number" 
	value="${model.currentPage eq model.pageCount?model.pageCount:model.currentPage+1 }" 
	min="1" max="${model.pageCount }"/>
	页
	<input type="button" value="确定" onclick="changeCurrentPage()"/>
	
	<a href="javascript:firstPage()">首页</a>
	<a href="javascript:backPage()">上一页</a>
	<a href="javascript:nextPage()">下一页</a>
	<a href="javascript:lastPage()">尾页</a>
	
	<!-- 分页栏目的功能 -->
	<script type="text/javascript">
		//根据id获取html中标签的对象
		function $(id){
			return document.getElementById(id);
		}
		/* 改变每页显示的总记录数  */
		function changePageSize(pageSize){
			//alert(pageSize);
			//location.href = "FenYeServlet?pageSize="+pageSize;
			$("pageSize").value = pageSize;
			$("currentPage").value = 1;
			$("pageForm").submit();
		}
		/* 跳转到指定的页面  */
		function changeCurrentPage(){
			//1.获取输入页码的输入框的对象，并通过对象获取它value属性值
			var currPage = document.getElementById("currPage").value;
			//解决输入框输入的值大于总页数出现数组越界问题
			currPage = currPage>${model.pageCount}?${model.pageCount}:currPage;
			//解决输入的值小于1时出现数组越界问题
			currPage = currPage<1?1:currPage;
			//2.将获取的修改的当前页面的值提交到服务器
			//location.href = "FenYeServlet?currentPage="+currPage+"&pageSize=${pageSize}";
			$("currentPage").value = currPage;
			//提交表单
			$("pageForm").submit();
		}
		/* 首页 */
		function firstPage(){
			//location.href = "FenYeServlet?currentPage=1&pageSize=${pageSize}";
			//获取到currentPage隐藏输入项的对象，并给其value进行赋值
			$("currentPage").value = 1;
			//获取到表单对象  执行提交的方法
			$("pageForm").submit();
		}
		/* 上一页 */
		function backPage(){
			//location.href = "FenYeServlet?currentPage=${currentPage eq 1?1:currentPage - 1}&pageSize=${pageSize}";
			$("currentPage").value = ${model.currentPage eq 1?1:model.currentPage - 1};
			$("pageForm").submit();
		}
		/* 下一页 */
		function nextPage(){
			
			//location.href = "FenYeServlet?currentPage=${currentPage eq pageCount?pageCount:currentPage+1}&pageSize=${pageSize}";
			$("currentPage").value = ${model.currentPage eq model.pageCount?model.pageCount:model.currentPage+1};
			$("pageForm").submit();
		}
		
		/* 尾页 */
		function lastPage(){
			//location.href = "FenYeServlet?currentPage=${pageCount}&pageSize=${pageSize}"
			$("currentPage").value = ${model.pageCount};
			$("pageForm").submit();
		}
	</script>

</div>