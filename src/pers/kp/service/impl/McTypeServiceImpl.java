package pers.kp.service.impl;

import java.util.List;

import pers.kp.dao.IMcTypeDao;
import pers.kp.dao.impl.McTypeDaoImpl;
import pers.kp.model.McTypeBean;
import pers.kp.service.IMcTypeService;

public class McTypeServiceImpl implements IMcTypeService {
	IMcTypeDao dao=new McTypeDaoImpl();
	@Override
	public int add(McTypeBean mt) {
		
		return dao.add(mt);
	}

	@Override
	public int delete(int typeid) {
		
		return dao.delete(typeid);
	}

	@Override
	public int update(McTypeBean mt) {
		
		return dao.update(mt);
	}

	@Override
	public McTypeBean queryForSingle(int typeid) {
		
		return dao.queryForSingle(typeid);
	}

	@Override
	public List<McTypeBean> query(McTypeBean mt) {
	
		return dao.query(mt);
	}

	@Override
	public List<McTypeBean> queryForFather() {
		
		return dao.queryForFather();
	}

}
