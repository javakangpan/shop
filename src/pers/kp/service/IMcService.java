package pers.kp.service;

import java.util.List;

import pers.kp.model.McBean;
import pers.kp.model.McTypeBean;
import pers.kp.model.PageBean;


public interface IMcService {
	int add(McBean mc);
	int delete(int mcid);
	int update(McBean mc);
	McBean queryForSingle(int mcid);
	List<McBean> query(McBean mc);
	List<McTypeBean> queryForFather(int id);
	PageBean<McBean> queryPageModel(McBean mc,int currentPage,int pageSize);
}
