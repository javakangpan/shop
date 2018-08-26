package pers.kp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pers.kp.model.McBean;
import pers.kp.model.ShopCar;
import pers.kp.service.IMcService;
import pers.kp.service.impl.McServiceImpl;
import pers.kp.utils.WebUtils;


@WebServlet("/ShopCarServlet")
public class ShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task=request.getParameter("task");
		HttpSession session=request.getSession();
		
		ShopCar shopCar=ShopCar.getShopCarInstance(session);
		
		
		IMcService service =new McServiceImpl();
		
		if("add".equals(task)){
			String  mcid=request.getParameter("mcid");
			McBean mc=service.queryForSingle(WebUtils.parseInt(mcid, -1));
			shopCar.addMc(mc);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			
		}else if("clear".equals(task)){
			shopCar.clear();
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else if("update".equals(task)){
				
				String count = request.getParameter("count");
				String mcid = request.getParameter("mcid");
				McBean mc = new McBean();
				mc.setCount(WebUtils.parseInt(count, 0));
				mc.setMcid(WebUtils.parseInt(mcid, -1));
			
				shopCar.updateMc(mc);
				
				response.sendRedirect(request.getContextPath()+"/front/shopcar.jsp");
			}else if("delete".equals(task)){
			
				String mcid = request.getParameter("mcid");
				
				shopCar.deleteMc(WebUtils.parseInt(mcid, -1));
				
				response.sendRedirect(request.getContextPath()+"/front/shopcar.jsp");
			}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
