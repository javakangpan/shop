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
	 * 查询多条数据
	 * @param <T>
	 * @param sql  
	 * @param cls  Student.java   Student.class
	 * @param params  可变参数
	 * @return
	 * 		查询出的数据的集合
	 */
	/*public static <T> List<T> baseQuery(String sql,Class<T> cls,Object...params){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<>();//用于保存查询出的数据
		try {
			//1.获取连接通道
			conn = DbUtils.getConnection();
			//2.获取执行SQL语句的对象实例
			ps = conn.prepareStatement(sql);
			//3.获取可变参数中对应占位符的数据，并对占位符进行赋值
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			//4.执行SQL语句,返回结果集对象
			rs = ps.executeQuery();
			//5.获取结果集对应的元数据ResultSetMetaData
			//通过ResultSetMeteData可以得到结果集的相关的属性（列数，列名称等）
			ResultSetMetaData rsmd = rs.getMetaData();
			//6.获取结果集中有多少列
			int columnCount = rsmd.getColumnCount();
			//7.rs.next()判断当前指针位置是否有下一行，若有，则返回true,并且指针移动到下一行
			//将下一行的数据保存到ResultSet对象中
			while(rs.next()){
				//创建保存数据的对象实例  使用反射
				T obj = cls.newInstance();   // Student student = new Student()  student.setSsid()
				//循环遍历取出结果集中每一列的数据
				for(int i=1;i<=columnCount;i++){
					Object value = rs.getObject(i);  // 每一列对应的值   1   张三   19  男
					//获取对应的每一列的列的名称  将查询出来的字段名称全部转化为小写的
					String columnName = rsmd.getColumnName(i).toLowerCase(); //SID  
					if(value==null){
						//跳出当前循环，执行下一次循环
						continue;
					}
					//System.out.println(columnName+":"+value);
					//判断类对象中是否有列名称对应的成员属性 有则返回true  否则返回false
					if(hasField(cls,columnName)){
						//根据列的名称获取类对象中的对应的成员属性对象  java.lang.reflect.Field
						Field f = cls.getDeclaredField(columnName);
						//设置成员属性的访问权限，允许访问私有的成员属性
						f.setAccessible(true);
						//判断value值是否是BigDecimal类型
						if(value instanceof BigDecimal){
							BigDecimal val = (BigDecimal) value;
							//判断成员属性的类型 
							//f.getType().getName()  获取成员属性的类型名称
							if("int".equals(f.getType().getName())){
								f.set(obj, val.intValue());  //将数据转化为int类型
							}else{
								f.set(obj, val.doubleValue());//将数据转化为浮点类型
							}
						}else{
							//通过成员属性的对象调用该成员属性对应的set方法给成员属性赋值  sid     setSsid()
							f.set(obj, value);  //student.setSsid(value)
						}
					}
				}
				//将每一行的数据保存到集合中
				list.add(obj);
			}
			//将所有的数据全部获取出来之后，将结果返回
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

	// SQL语句的前缀部分
	private static final String PAGE_START_SQL = " select t2.* from (select t1.*,rownum num from (";
	// SQL语句的结束部分
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
