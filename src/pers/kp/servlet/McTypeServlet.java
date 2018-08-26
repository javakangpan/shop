package pers.kp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.kp.model.McBean;
import pers.kp.model.McTypeBean;
import pers.kp.service.IMcTypeService;
import pers.kp.service.impl.McTypeServiceImpl;
import pers.kp.utils.WebUtils;

@WebServlet("/back/McTypeServlet")
public class McTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		
		String task = request.getParameter("task");
		IMcTypeService service = new McTypeServiceImpl();
		if ("query".equals(task)) {
			query(request, response, service);
			
		} else if ("queryForFather".equals(task)) {
			
			McTypeBean mt = new McTypeBean();
			List<McTypeBean> fatherlist = service.queryForFather();
			request.setAttribute("fatherlist", fatherlist);
			request.getRequestDispatcher("/back/mctype/mtadd.jsp").forward(request, response);
		} else if ("add".equals(task)) {
			
			String typename = request.getParameter("typename");
			String fatherid = request.getParameter("fatherid");
			
			
			int id=WebUtils.parseInt(fatherid, 0);
			
			
			McTypeBean mt = new McTypeBean();
			mt.setTypename(typename);
			mt.setFatherid(id);
			int i = service.add(mt);
			
			if (i != -1) {
				
				query(request, response, service);
			

			} else {
				

				PrintWriter out = response.getWriter();
				out.write("<script>alert('添加失败!');</script>");
				out.flush();
				out.close();
			}

		}else if("delete".equals(task)){
			String typeid=request.getParameter("typeid");
			int i=service.delete(WebUtils.parseInt(typeid, -1));
			if(i!=-1){
				query(request, response, service);
				
			}else{
				WebUtils.showMsg("删除失败", response);
				
			}
		}else if("queryForUpdate".equals(task)){
			
			String typeid=request.getParameter("typeid");
			
			McTypeBean mt = new McTypeBean();
			
			request.setAttribute("McType", service.queryForSingle(WebUtils.parseInt(typeid, -1)));
			//下拉大类
			List<McTypeBean> fatherlist = service.queryForFather();
			request.setAttribute("fatherlist", fatherlist);
			
			request.getRequestDispatcher("/back/mctype/mtupdate.jsp").forward(request, response);
			
		}else if("update".equals(task)){
			
			String typeid=request.getParameter("typeid");
			String typename = request.getParameter("typename");
			String fatherid = request.getParameter("fatherid");
			McTypeBean mt = new McTypeBean();
			
			mt.setTypeid(WebUtils.parseInt(typeid, -1));
			mt.setTypename(typename);
			mt.setFatherid(WebUtils.parseInt(fatherid, 0));
			int i=service.update(mt);
			
			if(i!=-1){
				query(request, response, service);
			}else{
				WebUtils.showMsg("修改失败", response);
			}
		}

	}


	private void query(HttpServletRequest request, HttpServletResponse response, IMcTypeService service)
			throws ServletException, IOException {
//		//每页显示的总数据
//		int pageSize=4;
//		//如果转化整型失败则默认给1
//		int currentPage=WebUtils.parseInt(request.getParameter("currentPage"), 1);
		
//		List<McTypeBean> listall = service.query(null);
//		//总记录数
//		int totalCount=listall.size();
//		//总页数(totalCount-1)/pageSize + 1
//		int pageCount=(totalCount-1)/pageSize+1;
//		
//		//开始下标
//		int startIndex=(currentPage-1)*pageSize;
//		//结束下标
//		int endIndex=currentPage*pageSize;
//		//10 3 3 3 1  endIndex=4*3=12  
//		
//		endIndex=endIndex>totalCount?totalCount:endIndex;
//			
//		
//		//截取分页后的数据
//		List<McTypeBean> list = listall.subList(startIndex, endIndex);
		List<McTypeBean> list = service.query(null);
		int totalCount=list.size();
		
//		request.setAttribute("list", list);
//		request.setAttribute("currentPage", currentPage);
//		request.setAttribute("pageCount", pageCount);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/back/mctype/mtlist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
