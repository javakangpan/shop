package pers.kp.utils;


import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0连接池
 * 数据库连接信息配置类，该类中设置全局的静态变量用于存放数据库连接信息
 * @author Administrator
 *
 */
public class DataSourceConfig {
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String user = "kangpan";
	static String password = "123";
	
	
	/**
	 * 数据源
	 */
	private static DataSource dataSource; 
	
	/**
	 * 获取数据源，即连接池对象
	 * @return Java.sql.DataSource
	 */
	public static synchronized DataSource getDataSource(){
		if(dataSource == null){
			initialization();
		}
		return dataSource;
	}
	
	/**
	 * 初始化数据库连接池
	 */
	public static void initialization(){
		if(dataSource != null){
			( (ComboPooledDataSource)dataSource).close();
		}
		
		ComboPooledDataSource tempDataSource = new ComboPooledDataSource();
		try {
			tempDataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");
			tempDataSource.setJdbcUrl(url);
			tempDataSource.setUser(user);
			tempDataSource.setPassword(password);
			tempDataSource.setCheckoutTimeout(1000);//设置超时时间 
			tempDataSource.setMaxAdministrativeTaskTime(100);
			dataSource = tempDataSource;
			
		} catch (PropertyVetoException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		DataSource dataSource = DataSourceConfig.getDataSource();
		Connection conn;
		try {
			conn = dataSource.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
