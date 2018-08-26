package pers.kp.dao.impl;

import java.util.List;

import pers.kp.dao.IOrderDetailDao;
import pers.kp.model.OrderDetailBean;
import pers.kp.utils.BaseDao;



public class OrderDetailDaoImpl extends BaseDao implements IOrderDetailDao {
	private String sql = "";
	@Override
	public int add(OrderDetailBean odb) {
		sql = "insert into t_orderdetail(detailid,orderid,mcid,buynum) "
				+ " values(seq_t_orderdetail.nextval,?,?,?)";
		return super.baseUpdate(sql, odb.getOrderid(),odb.getMcid(),odb.getBuynum());
	}

	@Override
	public int delete(String orderid) {
		sql = "delete from t_orderdetail where orderid=?";
		return super.baseUpdate(sql, orderid);
	}

	@Override
	public int update(OrderDetailBean odb) {
		return 0;
	}

	@Override
	public List<OrderDetailBean> query(OrderDetailBean odb) {
		sql = " select * from t_orderdetail where 1=1 ";
		return super.baseQuery(sql, OrderDetailBean.class);
	}

	@Override
	public OrderDetailBean queryById(int detailid) {
		sql = "select * from t_orderdetail where detailid=?";
		return super.queryForSingle(sql, OrderDetailBean.class, detailid);
	}

	@Override
	public List<OrderDetailBean> queryById(String orderid) {
		sql = "select * from t_orderdetail where orderid=?";
		return super.baseQuery(sql, OrderDetailBean.class, orderid);
	}

}
