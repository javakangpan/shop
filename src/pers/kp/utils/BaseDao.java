package pers.kp.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import pers.kp.model.PageBean;

public class BaseDao {

	public static int baseUpdate(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbUtils.getConnection();
			ps = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					ps.setObject(i + 1, args[i]);
				}
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(conn, ps);
		}
		return -1;
	}

	public static <T> T queryForSingle(String sql, Class<T> cls, Object... args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = DbUtils.getConnection();

			ps = conn.prepareStatement(sql);

			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					ps.setObject(i + 1, args[i]);
				}
			}

			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			int columnCount = rsmd.getColumnCount();

			if (rs.next()) {

				T obj = cls.newInstance();

				for (int i = 1; i <= columnCount; i++) {
					Object value = rs.getObject(i);

					String columnName = rsmd.getColumnName(i).toLowerCase();
					if (value == null) {

						continue;
					}

					if (hasField(cls, columnName)) {

						Field f = cls.getDeclaredField(columnName);

						f.setAccessible(true);

						if (value instanceof BigDecimal) {
							BigDecimal val = (BigDecimal) value;

							if ("int".equals(f.getType().getName())) {
								f.set(obj, val.intValue());
							} else {
								f.set(obj, val.doubleValue());
							}
						} else if (value instanceof java.sql.Timestamp) {
							java.sql.Timestamp time = (Timestamp) value;
							java.sql.Date date = new java.sql.Date(time.getTime());
							f.set(obj, date);
						} else {
							f.set(obj, value);
						}
					}
				}

				return obj;
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			DbUtils.close(conn, ps, rs);
		}
		return null;
	}

	public static int queryForCount(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = DbUtils.getConnection();
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				try {
					ps.setObject(i + 1, args[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DbUtils.close(conn, ps, rs);
		}

		return 0;
	}

	public static <T> List<T> baseQuery(String sql, Class<T> cls, Object... args) {

		List<T> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData rm = null;

		T t = null;
		Field f = null;

		try {
			conn = DbUtils.getConnection();
			ps = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					ps.setObject(i + 1, args[i]);
				}
			}

			rs = ps.executeQuery();
			rm = rs.getMetaData();

			while (rs.next()) {

				t = cls.newInstance();

				for (int i = 1; i <= rm.getColumnCount(); i++) {

					String columnName = rm.getColumnName(i).toLowerCase();
					Object v = rs.getObject(i);

					if (v == null) {
						continue;
					}

					if (hasField(cls, columnName)) {

						f = cls.getDeclaredField(columnName);
						f.setAccessible(true);

						if (v instanceof BigDecimal) {

							BigDecimal val = (BigDecimal) v;

							if ("int".equals(f.getType().getName())) {
								f.set(t, val.intValue());
							} else if ("double".equals(f.getType().getName())) {
								f.set(t, val.doubleValue());
							}
						} else {
							f.set(t, v);

						}
					}
				}
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(conn, ps);
		}
		return null;
	}
	/**
	 * ��ѯ��������
	 * @param <T>
	 * @param sql  
	 * @param cls  Student.java   Student.class
	 * @param params  �ɱ����
	 * @return
	 * 		��ѯ�������ݵļ���
	 */
	/*public static <T> List<T> baseQuery(String sql,Class<T> cls,Object...params){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<>();//���ڱ����ѯ��������
		try {
			//1.��ȡ����ͨ��
			conn = DbUtils.getConnection();
			//2.��ȡִ��SQL���Ķ���ʵ��
			ps = conn.prepareStatement(sql);
			//3.��ȡ�ɱ�����ж�Ӧռλ�������ݣ�����ռλ�����и�ֵ
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			//4.ִ��SQL���,���ؽ��������
			rs = ps.executeQuery();
			//5.��ȡ�������Ӧ��Ԫ����ResultSetMetaData
			//ͨ��ResultSetMeteData���Եõ����������ص����ԣ������������Ƶȣ�
			ResultSetMetaData rsmd = rs.getMetaData();
			//6.��ȡ��������ж�����
			int columnCount = rsmd.getColumnCount();
			//7.rs.next()�жϵ�ǰָ��λ���Ƿ�����һ�У����У��򷵻�true,����ָ���ƶ�����һ��
			//����һ�е����ݱ��浽ResultSet������
			while(rs.next()){
				//�����������ݵĶ���ʵ��  ʹ�÷���
				T obj = cls.newInstance();   // Student student = new Student()  student.setSsid()
				//ѭ������ȡ���������ÿһ�е�����
				for(int i=1;i<=columnCount;i++){
					Object value = rs.getObject(i);  // ÿһ�ж�Ӧ��ֵ   1   ����   19  ��
					//��ȡ��Ӧ��ÿһ�е��е�����  ����ѯ�������ֶ�����ȫ��ת��ΪСд��
					String columnName = rsmd.getColumnName(i).toLowerCase(); //SID  
					if(value==null){
						//������ǰѭ����ִ����һ��ѭ��
						continue;
					}
					//System.out.println(columnName+":"+value);
					//�ж���������Ƿ��������ƶ�Ӧ�ĳ�Ա���� ���򷵻�true  ���򷵻�false
					if(hasField(cls,columnName)){
						//�����е����ƻ�ȡ������еĶ�Ӧ�ĳ�Ա���Զ���  java.lang.reflect.Field
						Field f = cls.getDeclaredField(columnName);
						//���ó�Ա���Եķ���Ȩ�ޣ��������˽�еĳ�Ա����
						f.setAccessible(true);
						//�ж�valueֵ�Ƿ���BigDecimal����
						if(value instanceof BigDecimal){
							BigDecimal val = (BigDecimal) value;
							//�жϳ�Ա���Ե����� 
							//f.getType().getName()  ��ȡ��Ա���Ե���������
							if("int".equals(f.getType().getName())){
								f.set(obj, val.intValue());  //������ת��Ϊint����
							}else{
								f.set(obj, val.doubleValue());//������ת��Ϊ��������
							}
						}else{
							//ͨ����Ա���ԵĶ�����øó�Ա���Զ�Ӧ��set��������Ա���Ը�ֵ  sid     setSsid()
							f.set(obj, value);  //student.setSsid(value)
						}
					}
				}
				//��ÿһ�е����ݱ��浽������
				list.add(obj);
			}
			//�����е�����ȫ����ȡ����֮�󣬽��������
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbUtils.close(conn, ps, rs);
		}
		return null;
	}*/

	private static boolean hasField(Class cls, String columnName) {
		Field[] f = cls.getDeclaredFields();
		for (int i = 0; i < f.length; i++) {
			if (f[i].getName().equals(columnName)) {
				return true;
			}
		}
		return false;
	}

	// SQL����ǰ׺����
	private static final String PAGE_START_SQL = " select t2.* from (select t1.*,rownum num from (";
	// SQL���Ľ�������
	private static final String PAGE_END_SQL = " ) t1 where rownum<=?) t2 where num>=?";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> PageBean queryPageModel
	(StringBuffer querySql, StringBuffer countSql, StringBuffer whereSql,
			StringBuffer otherSql, Class<T> cls,int currentPage, int pageSize, List params)
	{
		
		countSql.append(whereSql);
		
		int totalCount = queryForCount(countSql.toString(), params.toArray());
		int startIndex = (currentPage - 1) * pageSize + 1;
		int endIndex = currentPage * pageSize;
		
		
		querySql.append(whereSql).append(otherSql);
		querySql.insert(0, PAGE_START_SQL);
		querySql.append(PAGE_END_SQL); 

		params.add(endIndex);
		params.add(startIndex);
		
		List<T> list = baseQuery(querySql.toString(),cls, params.toArray());
	
		
		PageBean model= new PageBean();

		model.setCurrentPage(currentPage);
		model.setPageSize(pageSize);
		model.setTotalCount(totalCount);
		model.setList(list);
		return model;
	}
}
