package pers.kp.service;

import pers.kp.model.UserBean;

public interface IUserService {
	UserBean login(String username,String password);
}
