package pers.kp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String task=request.getParameter("task");
	  HttpSession session=request.getSession();
	  if("back".equals(task)){
		  session.invalidate();
		  request.getRequestDispatcher("/backLogin.jsp").forward(request, response);
	  }else{
		  session.invalidate();
		  request.getRequestDispatcher("/index.jsp").forward(request, response);
	  }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
