package pers.kp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import pers.kp.model.UserBean;
import pers.kp.service.IUserService;

import pers.kp.service.impl.UserServiceImpl;
import pers.kp.utils.WebUtils;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("username").trim();
		String pwd = request.getParameter("pwd").trim();
		IUserService service = new UserServiceImpl();

		UserBean user = service.login(name, pwd);

		HttpSession session = request.getSession();
		String rand = (String) session.getAttribute("rand");
		String code = request.getParameter("code");

		if (!WebUtils.isEmpty(code) && code.equals(rand)) {
			if (user != null) {

				session.setAttribute("FRONT_USER", user);
				session.setMaxInactiveInterval(60 * 15);
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			} else {

				session.setAttribute("FRONT_MSG", "用户名或密码错误！");
				response.sendRedirect(request.getContextPath()+"/frontLogin.jsp");
			}

		}else{
			session.setAttribute("FRONT_MSG", "输入有误！");
			request.getRequestDispatcher("/frontLogin.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
