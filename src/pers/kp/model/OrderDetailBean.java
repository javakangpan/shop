package pers.kp.model;

/**
 * 订单的详情信息
 * @author
 *
 */
public class OrderDetailBean {
	
	private int detailid;  //订单详情的编号
	private String orderid;  //订单编号 
	private int mcid;  //商品编号
	private int buynum;  //购买的商品数量
	/**
	 * 表示当前的订单详情是属于哪个订单
	 * 可以获取到了订单详情信息  就可以获取到所属订单的信息
	 * 多对1关系
	 */
	private OrderBean orderBean;//订单详情表-->订单表
	public OrderDetailBean(int detailid, String orderid, int mcid, int buynum, OrderBean orderBean) {
		super();
		this.detailid = detailid;
		this.orderid = orderid;
		this.mcid = mcid;
		this.buynum = buynum;
		this.orderBean = orderBean;
	}
	public OrderDetailBean() {
		super();
	}
	public int getDetailid() {
		return detailid;
	}
	public void setDetailid(int detailid) {
		this.detailid = detailid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getMcid() {
		return mcid;
	}
	public void setMcid(int mcid) {
		this.mcid = mcid;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	public OrderBean getOrderBean() {
		return orderBean;
	}
	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}
	
}
