package pers.kp.service.impl;

import pers.kp.dao.IManagerDao;
import pers.kp.dao.impl.ManagerDaoImpl;
import pers.kp.model.ManagerBean;
import pers.kp.service.IManagerService;

public class ManagerServiceImpl implements IManagerService {
	IManagerDao dao=new ManagerDaoImpl();
	@Override
	public ManagerBean login(String username, String password) {
		
		return dao.login(username, password);
	}

}
