package com.lin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lin.model.Course;
import com.lin.model.User;
import com.mysql.jdbc.Statement;

public class UserDao extends BaseDao {
	public User Login(User user) {
		String sql = "select * from User where name=? and password=?";
		User userRst = null;
		 try {
			 PreparedStatement prst = (PreparedStatement) con.prepareStatement(sql);
			 //把sql语句传给数据库操作对象
			 prst.setString(1, user.getName());
			 prst.setString(2, user.getPassword());
			 System.out.println("即将验证");
			 ResultSet rs = prst.executeQuery();
			 if(rs.next()){
				 System.out.println("ye 进入验证");
				 userRst = new User();
				 userRst.setId(rs.getInt("id"));
				 userRst.setRoleId(rs.getInt("roleId"));
				 userRst.setName(rs.getString("name"));
				 userRst.setPassword(rs.getString("password"));
				 userRst.setNickname(rs.getString("nickname"));
			 }
			 if(userRst==null) {
				 return null;
			 }else if(userRst.getRoleId() !=user.getRoleId()) {
				 System.out.println("身份不符，密码正确");
				 return null;
			 }
		 
		 } catch (Exception e) {
			// TODO: handle exception
			 /*存在问题，若连接失败？返回一个空值，但是这里会被异常捕获*/
			 /*这个异常不是因为查询为空造成的 因为下面这句不执行*/
			 System.out.println("查询失败");
			 e.printStackTrace();
			 
		}
		 return userRst;
		
	}

	
}	
