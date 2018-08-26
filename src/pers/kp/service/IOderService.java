package pers.kp.service;

import java.util.List;

import pers.kp.model.OrderBean;
import pers.kp.model.PageBean;



public interface IOderService {
	/**
	 * 添加订单的信息
	 * @param ob 要添加的订单信息
	 * @return
	 * 		-1  表示添加失败
	 * 		其他表示添加成功
	 */
	int add(OrderBean ob);
	
	/**
	 * 删除订单   删除订单的同时也要删除订单详情表中的相关数据
	 * @param orderid  订单的编号
	 * @return
	 */
	int delete(String orderid);
	
	/**
	 * 修改订单
	 * @param ob
	 * @return
	 */
	int update(OrderBean ob);
	
	/**
	 * 查询多个订单信息
	 * @param ob
	 * @return
	 */
	List<OrderBean> query(OrderBean ob);
	
	/**
	 * 查询单个订单信息
	 * @param orderid
	 * @return
	 */
	OrderBean queryById(String orderid);

	/**
	 * 分页查询
	 * @param ob
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageBean queryPageModel(OrderBean ob, int currentPage, int pageSize);
}
