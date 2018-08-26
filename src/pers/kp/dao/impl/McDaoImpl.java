package pers.kp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pers.kp.dao.IMcDao;
import pers.kp.model.McBean;
import pers.kp.model.McTypeBean;
import pers.kp.model.PageBean;
import pers.kp.utils.BaseDao;
import pers.kp.utils.WebUtils;

public class McDaoImpl extends BaseDao implements IMcDao {
	private String sql=null;
	@Override
	public int add(McBean mc) {
		sql="INSERT INTO T_MC(MCID,MCNAME,MCDECX,PRICE,PIC,FLAG,SMALLTYPEID,CREATEDATE,QUANTITY)"
				+"VALUES(SEQ_T_MC.NEXTVAL,?,?,?,?,?,?,SYSDATE,?)";
		return super.baseUpdate(sql,mc.getMcname(),mc.getMcdecx(),mc.getPrice(),mc.getPic(),mc.getFlag(),
				mc.getSmalltypeid(),mc.getQuantity());
	}

	@Override
	public int delete(int mcid) {
		sql="DELETE FROM T_MC WHERE MCID=?";
		return super.baseUpdate(sql, mcid);
	}

	@Override
	public int update(McBean mc) {
		
		return 0;
	}

	@Override
	public McBean queryForSingle(int mcid) {
		sql="SELECT * FROM T_MC WHERE MCID=?";
		return super.queryForSingle(sql, McBean.class, mcid);
		
	}

	@Override
	public List<McBean> query(McBean mc) {
		sql="SELECT * FROM T_MC WHERE 1=1";
		if(mc!=null&&mc.getMcname()!=null&&!"".equals(mc.getMcname())){
			sql+="and mcname=?";
			return super.baseQuery(sql, McBean.class, mc.getMcname());
		}
		return super.baseQuery(sql, McBean.class);
	}

	@Override
	public List<McTypeBean> queryForFather(int id) {
		sql="SELECT * FROM T_MCTYPE WHERE FATHERID=?";
		return super.baseQuery(sql, McTypeBean.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageBean<McBean> queryPageModel(McBean mc,int currentPage, int pageSize) {
		
		StringBuffer countSql=new StringBuffer(" SELECT  COUNT(1) FROM T_MC WHERE 1=1 ");
		StringBuffer querySql=new StringBuffer(" SELECT * FROM T_MC WHERE 1=1 ");
		StringBuffer whereSql=new StringBuffer();
		StringBuffer otherSql=new StringBuffer(" ORDER BY MCID ASC ");
		
		List<Object> params= new ArrayList<>();
		if(!WebUtils.isEmpty(mc.getMcname())){
			whereSql.append(" and mcname like ?");
			params.add("%"+mc.getMcname()+"%");
		}
		return super.queryPageModel(querySql, countSql, whereSql, otherSql, McBean.class, currentPage, pageSize, params);
	}
			
}
