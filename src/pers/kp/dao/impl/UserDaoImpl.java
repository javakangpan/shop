package pers.kp.dao.impl;

import pers.kp.dao.IUserDao;
import pers.kp.model.UserBean;
import pers.kp.utils.BaseDao;

public class UserDaoImpl extends BaseDao implements IUserDao {
	private String sql="";
	@Override
	public UserBean login(String username, String password) {
		sql="SELECT * FROM T_USER WHERE USERNAME=? AND PASSWORD=?";
		return super.queryForSingle(sql, UserBean.class, username,password);
	}

}
