package pers.kp.service;

import java.util.List;

import pers.kp.model.McTypeBean;

public interface IMcTypeService {
	int add(McTypeBean mt);
	int delete(int typeid);
	int update(McTypeBean mt);
	McTypeBean queryForSingle(int typeid);
	List<McTypeBean> query(McTypeBean mt);
	List<McTypeBean> queryForFather();
	
	
}
