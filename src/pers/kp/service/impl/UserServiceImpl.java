package pers.kp.service.impl;

import pers.kp.dao.IUserDao;
import pers.kp.dao.impl.UserDaoImpl;
import pers.kp.model.UserBean;
import pers.kp.service.IUserService;

public class UserServiceImpl implements IUserService {
	IUserDao dao=new UserDaoImpl();
	@Override
	public UserBean login(String username, String password) {
		
		return dao.login(username, password);
	}

}
