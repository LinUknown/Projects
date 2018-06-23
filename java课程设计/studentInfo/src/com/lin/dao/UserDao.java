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
			 //��sql��䴫�����ݿ��������
			 prst.setString(1, user.getName());
			 prst.setString(2, user.getPassword());
			 System.out.println("������֤");
			 ResultSet rs = prst.executeQuery();
			 if(rs.next()){
				 System.out.println("ye ������֤");
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
				 System.out.println("��ݲ�����������ȷ");
				 return null;
			 }
		 
		 } catch (Exception e) {
			// TODO: handle exception
			 /*�������⣬������ʧ�ܣ�����һ����ֵ����������ᱻ�쳣����*/
			 /*����쳣������Ϊ��ѯΪ����ɵ� ��Ϊ������䲻ִ��*/
			 System.out.println("��ѯʧ��");
			 e.printStackTrace();
			 
		}
		 return userRst;
		
	}

	
}	
