package com.lin.util;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.management.Query;

public class DbUtil {
	static final String JDBE_DRIVER ="com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/infodb2?useUnicode=true&amp;characterEncoding=utf-8";
	static final String username = "root";
	static final String password = "123";
	
	public static void main(String args[]) {
		getCon();
	}
	public static Connection getCon() {
		System.out.println("尝试连接数据库");
		Connection connection = null; // 用于创建链接
		try {
			Class.forName(JDBE_DRIVER);
			connection = DriverManager.getConnection(DB_URL, username, password);
			System.out.println("连接数据库成功");
		}
		catch (SQLException e) {
			// TODO: handle exception
			System.out.println("连接数据库失败");
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("连接数据库失败");
			e.printStackTrace();
		}
		
		return connection;
	}
	public void closeCon(Connection con) throws SQLException{
		if(con!=null)
			con.close();
	}
}
