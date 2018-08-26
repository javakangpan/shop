package pers.kp.service;

import java.util.List;

import pers.kp.model.OrderDetailBean;


public interface IOrderDetailService {
	/**
	 * 添加订单的详情信息
	 * @param odb
	 * @return
	 */
	int  add(OrderDetailBean odb);
	
	/**
	 * 删除订单详情
	 * @param orderid  订单的编号
	 * @return
	 */
	int delete(String orderid);
	/**
	 * 修改订单详情
	 * @param odb
	 * @return
	 */
	int update(OrderDetailBean odb);
	
	/**
	 * 查询多个订单详情信息
	 * @param odb
	 * @return
	 */
	List<OrderDetailBean> query(OrderDetailBean odb);
	
	/**
	 * 查询订单详情
	 * @return
	 */
	OrderDetailBean queryById(int detailid);
	
	/**
	 * 根据订单编号查询订单详情信息
	 * @param orderid
	 * @return
	 */
	List<OrderDetailBean> queryById(String orderid);
}
