package pers.kp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pers.kp.model.McBean;
import pers.kp.model.OrderBean;
import pers.kp.model.OrderDetailBean;
import pers.kp.model.PageBean;
import pers.kp.model.ShopCar;
import pers.kp.model.UserBean;
import pers.kp.service.IOderService;
import pers.kp.service.impl.OderServiceImpl;
import pers.kp.utils.WebUtils;


@WebServlet("/front/filter/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task=request.getParameter("task");
		HttpSession session=request.getSession();
		IOderService service=new OderServiceImpl();
		
		if("add".equals(task)){
			addOrder(session,request,response,service);
		}else{
			//获取客户端的分页的参数
			
			int currentPage = WebUtils.parseInt(request.getParameter("currentPage"), 1);
			int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), 3);
			//创建OrderBean对象实例(用于保存分页查询的条件)
			OrderBean ob = new OrderBean();
			//获取登录用户的信息
			UserBean user = (UserBean) session.getAttribute("FRONT_USER");
			if(user!=null){
				ob.setUserid(user.getUserid());
				//表示查询订单的信息  分页查询
				PageBean pageModel = service.queryPageModel(ob,currentPage,pageSize);
				//将查询的订单保存到作用域中
				request.setAttribute("Model", pageModel);
				//跳转页面
				request.getRequestDispatcher("/front/page/orderlist.jsp").forward(request, response);
			}else{
				//表示未登录
				request.getRequestDispatcher("/frontLogin.jsp").forward(request, response);
			}
		}
		
	
	}

	
	private void addOrder(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			IOderService service) {
		/****************1.获取数据*******************************/
		//1.从客户端的表单中获取订单的相关的数据
		String paytype =  request.getParameter("paytype");
		String receivedtype = request.getParameter("receivedtype");
		String username = request.getParameter("username");
		String address = request.getParameter("address");
		String postcode = request.getParameter("postcode");
		String phoneno = request.getParameter("phoneno");
		String email = request.getParameter("email");
		//2.从购物车中获取订单的信息
		ShopCar shopCar = ShopCar.getShopCarInstance(session);
		double totalPrice = shopCar.getTotalPrice();
		int quantity = shopCar.getTotalCount();
		int alltype = shopCar.getTotalType();
		//3.从购物车中获取订单详情的信息
		List<McBean> mcList = shopCar.getList();
		//4.从session获取下订单的用户信息
		UserBean user = (UserBean) session.getAttribute("FRONT_USER");
		
		 
		/****************2.保存数据*******************************/
		OrderBean ob = new OrderBean();
		//保存订单的详情信息
		for (McBean mc : mcList) {
			OrderDetailBean odb = new OrderDetailBean();
			odb.setBuynum(mc.getCount());
			odb.setMcid(mc.getMcid());
			//将订单中的每一个商品信息保存在订单中详细订单集合中
			ob.getOrderList().add(odb);
		}
		//保存订单信息
		ob.setUserid(user.getUserid());
		ob.setQuantity(quantity);
		ob.setAlltype(alltype);
		ob.setTotalprice(totalPrice);
		ob.setPaytype(paytype);
		ob.setReceivedtype(receivedtype);
		ob.setUsername(username);
		ob.setAddress(address);
		ob.setPostcode(postcode);
		ob.setPhoneno(phoneno);
		ob.setEmail(email);
		/****************3.将数据插入到数据库中*******************************/
		service.add(ob);
		//清空购物车
		shopCar.clear();
		/****************4.跳转页面*******************************/
		try {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
