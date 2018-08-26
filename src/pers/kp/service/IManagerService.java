package pers.kp.service;

import pers.kp.model.ManagerBean;

public interface IManagerService {
	ManagerBean login(String username,String password);
}
