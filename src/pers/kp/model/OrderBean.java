package pers.kp.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderBean {
	private String orderid;   //订单编号
	private int userid;    //下订单的用户编号
	private int quantity;  //订单中所有商品的总件数
	private int alltype;   //订单中商品的种类数
	private double totalprice; //订单中的商品总价格
	private String paytype;  //付款方式
	private String receivedtype; //收货方式
	private String username;  //收货人姓名
	private String address;  //收货人地址
	private String postcode; //邮编
	private String phoneno;  //电话
	private String email;  //邮箱
	private Date orderdate;  //下订单的日期
	private String status;  //订单状态  通过   未通过    未审核
	private String approveduser;  //审核人
	private Date approveddate;//审核日期
	private String msg;  //审核信息
	/**
	 * 表示一个订单中有多个订单详情信息
	 * 1对多关系
	 */
	private List<OrderDetailBean> orderList;
	
	public OrderBean() {
		super();
		//初始化集合
		this.orderList = new ArrayList<OrderDetailBean>();
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAlltype() {
		return alltype;
	}

	public void setAlltype(int alltype) {
		this.alltype = alltype;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getReceivedtype() {
		return receivedtype;
	}

	public void setReceivedtype(String receivedtype) {
		this.receivedtype = receivedtype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApproveduser() {
		return approveduser;
	}

	public void setApproveduser(String approveduser) {
		this.approveduser = approveduser;
	}

	public Date getApproveddate() {
		return approveddate;
	}

	public void setApproveddate(Date approveddate) {
		this.approveddate = approveddate;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<OrderDetailBean> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderDetailBean> orderList) {
		this.orderList = orderList;
	}
	
}
