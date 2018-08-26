package pers.kp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.kp.model.McBean;
import pers.kp.model.PageBean;
import pers.kp.service.IMcService;
import pers.kp.service.impl.McServiceImpl;
import pers.kp.utils.WebUtils;


@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IMcService service=new McServiceImpl();
		int currentPage=WebUtils.getCurrentPage(request);
		int pageSize=WebUtils.getPageSize(request);
		String mcname=request.getParameter("mcname");
		McBean mc=new McBean();
		if(!WebUtils.isEmpty(mcname)){
			mc.setMcname(mcname);
		}
		PageBean<McBean> list=service.queryPageModel(mc, currentPage, pageSize);
		
		request.setAttribute("model", list);
		request.setAttribute("mcname", mcname);

		request.getRequestDispatcher("/front/home.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
