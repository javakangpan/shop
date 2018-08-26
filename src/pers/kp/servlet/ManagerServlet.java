package pers.kp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pers.kp.model.ManagerBean;
import pers.kp.service.IManagerService;
import pers.kp.service.impl.ManagerServiceImpl;


@WebServlet("/ManagerServlet")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		
		
		
		String name=request.getParameter("username");
		String pwd=request.getParameter("pwd");
		
		IManagerService service=new ManagerServiceImpl();
		ManagerBean manager=service.login(name, pwd);
		
		if(manager!=null){
			
			session.setAttribute("BACK_USER", manager);
			session.setMaxInactiveInterval(60*15);
			request.getRequestDispatcher("/back/main.jsp").forward(request, response);
			
		}else{
			
			session.setAttribute("MSG", "用户名或者密码错误！");
			request.getRequestDispatcher("/backLogin.jsp").forward(request, response);
		}
	   
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
