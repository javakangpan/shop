package pers.kp.service.impl;

import java.util.List;

import pers.kp.dao.IOrderDetailDao;
import pers.kp.dao.impl.OrderDetailDaoImpl;
import pers.kp.model.OrderDetailBean;
import pers.kp.service.IOrderDetailService;



public class OrderDetailServiceImpl implements IOrderDetailService {
	IOrderDetailDao dao = new OrderDetailDaoImpl();
	@Override
	public int add(OrderDetailBean odb) {
	
		return dao.add(odb);
	}

	@Override
	public int delete(String orderid) {
		
		return dao.delete(orderid);
	}

	@Override
	public int update(OrderDetailBean odb) {
	
		return dao.update(odb);
	}

	@Override
	public List<OrderDetailBean> query(OrderDetailBean odb) {
		
		return dao.query(odb);
	}

	@Override
	public OrderDetailBean queryById(int detailid) {
	
		return dao.queryById(detailid);
	}

	@Override
	public List<OrderDetailBean> queryById(String orderid) {
		
		return dao.queryById(orderid);
	}

}
