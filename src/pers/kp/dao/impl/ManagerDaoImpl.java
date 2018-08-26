package pers.kp.dao.impl;

import pers.kp.dao.IManagerDao;
import pers.kp.model.ManagerBean;
import pers.kp.utils.BaseDao;

public class ManagerDaoImpl extends BaseDao implements IManagerDao {
	private String sql="";
	@Override
	public ManagerBean login(String username, String password) {
		sql = "SELECT * FROM T_MANAGER WHERE MUSERNAME=? AND MPASSWORD=?";
		return super.queryForSingle(sql, ManagerBean.class, username,password);
	}

}
