package pers.kp.service.impl;

import java.util.List;

import pers.kp.dao.IMcDao;
import pers.kp.dao.impl.McDaoImpl;
import pers.kp.model.McBean;
import pers.kp.model.McTypeBean;
import pers.kp.model.PageBean;
import pers.kp.service.IMcService;

public class McServiceImpl implements IMcService {
	IMcDao dao=new McDaoImpl();
	@Override
	public int add(McBean mc) {
	
		return dao.add(mc);
	}

	@Override
	public int delete(int mcid) {
		
		return dao.delete(mcid);
	}

	@Override
	public int update(McBean mc) {
	
		return dao.update(mc);
	}

	@Override
	public McBean queryForSingle(int mcid) {
	
		return dao.queryForSingle(mcid);
	}

	@Override
	public List<McBean> query(McBean mc) {
		
		return dao.query(mc);
	}

	@Override
	public List<McTypeBean> queryForFather(int id) {
		
		return dao.queryForFather(id);
	}

	@Override
	public PageBean<McBean> queryPageModel(McBean mc,int currentPage, int pageSize) {
		
		return dao.queryPageModel(mc,currentPage, pageSize);
	}

}
