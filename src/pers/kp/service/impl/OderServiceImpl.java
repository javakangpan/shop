package pers.kp.service.impl;

import java.util.List;


import pers.kp.dao.IOrderDao;
import pers.kp.dao.impl.OrderDaoImpl;
import pers.kp.model.OrderBean;
import pers.kp.model.PageBean;
import pers.kp.service.IOderService;



public class OderServiceImpl implements IOderService {
	IOrderDao dao = new OrderDaoImpl();
	@Override
	public int add(OrderBean ob) {
		
		return dao.add(ob);
	}

	@Override
	public int delete(String orderid) {
		
		return dao.delete(orderid);
	}

	@Override
	public int update(OrderBean ob) {
	
		return dao.update(ob);
	}

	@Override
	public List<OrderBean> query(OrderBean ob) {
		
		return dao.query(ob);
	}

	@Override
	public OrderBean queryById(String orderid) {
		
		return dao.queryById(orderid);
	}

	@Override
	public PageBean queryPageModel(OrderBean ob, int currentPage, int pageSize) {
		
		return dao.queryPageModel(ob,currentPage,pageSize);
	}

}
