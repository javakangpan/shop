package pers.kp.dao;

import pers.kp.model.UserBean;

public interface IUserDao {
	UserBean login(String username,String password);
}
