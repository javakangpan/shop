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
			//��ȡ�ͻ��˵ķ�ҳ�Ĳ���
			
			int currentPage = WebUtils.parseInt(request.getParameter("currentPage"), 1);
			int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), 3);
			//����OrderBean����ʵ��(���ڱ����ҳ��ѯ������)
			OrderBean ob = new OrderBean();
			//��ȡ��¼�û�����Ϣ
			UserBean user = (UserBean) session.getAttribute("FRONT_USER");
			if(user!=null){
				ob.setUserid(user.getUserid());
				//��ʾ��ѯ��������Ϣ  ��ҳ��ѯ
				PageBean pageModel = service.queryPageModel(ob,currentPage,pageSize);
				//����ѯ�Ķ������浽��������
				request.setAttribute("Model", pageModel);
				//��תҳ��
				request.getRequestDispatcher("/front/page/orderlist.jsp").forward(request, response);
			}else{
				//��ʾδ��¼
				request.getRequestDispatcher("/frontLogin.jsp").forward(request, response);
			}
		}
		
	
	}

	
	private void addOrder(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			IOderService service) {
		/****************1.��ȡ����*******************************/
		//1.�ӿͻ��˵ı��л�ȡ��������ص�����
		String paytype =  request.getParameter("paytype");
		String receivedtype = request.getParameter("receivedtype");
		String username = request.getParameter("username");
		String address = request.getParameter("address");
		String postcode = request.getParameter("postcode");
		String phoneno = request.getParameter("phoneno");
		String email = request.getParameter("email");
		//2.�ӹ��ﳵ�л�ȡ��������Ϣ
		ShopCar shopCar = ShopCar.getShopCarInstance(session);
		double totalPrice = shopCar.getTotalPrice();
		int quantity = shopCar.getTotalCount();
		int alltype = shopCar.getTotalType();
		//3.�ӹ��ﳵ�л�ȡ�����������Ϣ
		List<McBean> mcList = shopCar.getList();
		//4.��session��ȡ�¶������û���Ϣ
		UserBean user = (UserBean) session.getAttribute("FRONT_USER");
		
		 
		/****************2.��������*******************************/
		OrderBean ob = new OrderBean();
		//���涩����������Ϣ
		for (McBean mc : mcList) {
			OrderDetailBean odb = new OrderDetailBean();
			odb.setBuynum(mc.getCount());
			odb.setMcid(mc.getMcid());
			//�������е�ÿһ����Ʒ��Ϣ�����ڶ�������ϸ����������
			ob.getOrderList().add(odb);
		}
		//���涩����Ϣ
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
		/****************3.�����ݲ��뵽���ݿ���*******************************/
		service.add(ob);
		//��չ��ﳵ
		shopCar.clear();
		/****************4.��תҳ��*******************************/
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
