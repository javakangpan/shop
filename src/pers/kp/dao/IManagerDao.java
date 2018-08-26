package pers.kp.dao;

import pers.kp.model.ManagerBean;

public interface IManagerDao {
	ManagerBean login(String username,String password);
}
