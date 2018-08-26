package pers.kp.dao.impl;

import java.util.List;

import pers.kp.dao.IMcTypeDao;
import pers.kp.model.McTypeBean;
import pers.kp.utils.BaseDao;

public class McTypeDaoImpl extends BaseDao implements IMcTypeDao {
	private String sql=null;
	@Override
	public int add(McTypeBean mt) {
		sql="INSERT INTO T_MCTYPE(TYPEID,TYPENAME,FATHERID)"
				+"VALUES(SEQ_T_MCTYPE.NEXTVAL,?,?)";
		return super.baseUpdate(sql, mt.getTypename(),mt.getFatherid());
	}

	@Override
	public int delete(int typeid) {
		sql="DELETE FROM T_MCTYPE WHERE TYPEID=? OR FATHERID=?";
		return super.baseUpdate(sql, typeid,typeid);
	}

	@Override
	public int update(McTypeBean mt) {
		sql="UPDATE T_MCTYPE SET TYPENAME=?,FATHERID=? WHERE TYPEID=? ";
		return super.baseUpdate(sql, mt.getTypename(),mt.getFatherid(),mt.getTypeid());
	}

	@Override
	public McTypeBean queryForSingle(int typeid) {
		sql="SELECT * FROM T_MCTYPE WHERE TYPEID=?";
		return super.queryForSingle(sql, McTypeBean.class,typeid);
	}

	@Override
	public List<McTypeBean> query(McTypeBean mt) {
		sql="SELECT * FROM T_MCTYPE";
		return super.baseQuery(sql, McTypeBean.class);
	}
	@Override
	public List<McTypeBean> queryForFather(){
		sql="SELECT * FROM T_MCTYPE WHERE FATHERID=?";
		return super.baseQuery(sql, McTypeBean.class, 0);
		
	}

}
