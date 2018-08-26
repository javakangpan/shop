package pers.kp.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pers.kp.dao.IOrderDao;
import pers.kp.dao.IOrderDetailDao;
import pers.kp.model.OrderBean;
import pers.kp.model.OrderDetailBean;
import pers.kp.model.PageBean;
import pers.kp.utils.BaseDao;

public class OrderDaoImpl extends BaseDao implements IOrderDao {
	private String sql = "";
	IOrderDetailDao dao = new OrderDetailDaoImpl();
	@Override
	public int add(OrderBean ob) {
		//����һ���������  Ψһ��
		String orderid = new Date().getTime() + "";
		sql = "insert into t_orders(orderid,userid,quantity,"
				+ "alltype,totalprice,paytype,receivedtype,"
				+ "username,address,postcode,phoneno,email,"
				+ "orderdate,status) "
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate,'0')";
		//��Ӷ�����Ϣ
		int i = super.baseUpdate(sql, orderid,ob.getUserid(),ob.getQuantity(),
				ob.getAlltype(),ob.getTotalprice(),ob.getPaytype(),ob.getReceivedtype(),
				ob.getUsername(),ob.getAddress(),ob.getPostcode(),
				ob.getPhoneno(),ob.getEmail());
		
		
		
		
		//��Ӷ�������  �������
		
		
		
		List<OrderDetailBean> list = ob.getOrderList();
		for (OrderDetailBean odb : list) {
			//���涩���ı��
			odb.setOrderid(orderid);
			//��Ӷ���������Ϣ
			dao.add(odb);
		}
		return i;
	}

	@Override
	public int delete(String orderid) {
		sql = "delete from t_orders where orderid=?";
		//ɾ��������Ϣ
		int i = super.baseUpdate(sql, orderid);
		//��������Ϣɾ���ɹ���  ɾ����������ϸ��Ϣ
		//��������Ҫ��һ���������  ���ݿ����Ҫͬʱ�ɹ���ͬʱʧ��
		//ɾ������������Ϣ
		dao.delete(orderid);
		return i;
	}

	@Override
	public int update(OrderBean ob) {
		sql = "update t_orders set paytype=?,receviedtype=?,"
				+ "username=?,address=?,phoneno=?,postcode=?,email=? "
				+ " where orderid=?";
		return super.baseUpdate(sql, ob.getPaytype(),ob.getReceivedtype(),ob.getUsername(),
				ob.getAddress(),ob.getPhoneno(),ob.getPostcode(),ob.getEmail(),ob.getOrderid());
	}

	@Override
	public List<OrderBean> query(OrderBean ob) {
		sql = "select * from t_orders where 1=1 ";
		return super.baseQuery(sql, OrderBean.class);
	}

	@Override
	public OrderBean queryById(String orderid) {
		sql = "select * from t_orders where orderid=?";
		return super.queryForSingle(sql, OrderBean.class, orderid);
	}

	@Override
	public PageBean queryPageModel(OrderBean ob, int currentPage, int pageSize) {
		StringBuffer querySql = new StringBuffer(" select * from t_orders ");
		StringBuffer countSql = new StringBuffer(" select count(1) from t_orders ");
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		StringBuffer otherSql = new StringBuffer(" order by orderid asc");
		
		List<Object> params = new ArrayList<Object>();
		if(ob.getUserid()!=0){
			whereSql.append(" and userid=? ");
			params.add(ob.getUserid());
		}
		return super.queryPageModel(querySql, countSql, whereSql, otherSql, OrderBean.class, currentPage, pageSize, params);
	}




}
