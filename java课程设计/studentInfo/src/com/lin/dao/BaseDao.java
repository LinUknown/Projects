package com.lin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lin.model.StudentAndCourse;
import com.lin.util.*;
import com.mysql.jdbc.Statement;

/*用于和数据库互交*/

public class BaseDao {
	public Connection con = new DbUtil().getCon();
	
	public ResultSet query(String sql){
		PreparedStatement pstmt;
		ResultSet rs = null;
		System.out.println("尝试查询课程成绩--- 1");
		try {
			System.out.println("尝试查询课程成绩--- 2");
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			rs= pstmt.executeQuery(); 
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	public boolean insertCourse(String sql) throws SQLException {
		// TODO Auto-generated method stub
		Statement stm = (Statement) con.createStatement();
		try{
			System.out.println("输出的语句是: "+sql);
			stm.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
		      //finally block used to close resources
		      try{
		         if(stm!=null)
		            con.close();
		      }catch(SQLException se){
		    	  se.printStackTrace();
		      }
		      try{
		         if(con!=null)
		            con.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		
		return true;
	}
}
