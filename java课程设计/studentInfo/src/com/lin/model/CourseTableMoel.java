package com.lin.model;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lin.dao.BaseDao;
import com.lin.model.*;


public class CourseTableMoel extends AbstractTableModel {
	String[] columnNames = new String[] {"id","name","ѧ��","ѧʱ","state"};
	private User localUser;
	public ArrayList<Course> courses = new ArrayList<Course>();
	
	public CourseTableMoel (User user) throws SQLException{
		 localUser = user;
	}
	public void getList(String sql) throws SQLException{
		courses.clear();
		BaseDao dao  = new BaseDao();
		ResultSet rs = dao.query(sql);
		 int col = rs.getMetaData().getColumnCount();
		 while(rs.next()){
			 System.out.println("��ѯ�γ̳ɹ�");
			 Course isC = new Course();
			 isC.setId(rs.getInt("id"));
			 isC.setName(rs.getString("name"));
			 isC.setCredit(rs.getInt("credit"));
			 isC.setTime(rs.getInt("time"));
			 isC.setState(rs.getInt("state"));
			 courses.add(isC);
		 }
	}
	@Override
	public int getRowCount() {
        // TODO Auto-generated method stub
        return courses.size();
    }
 
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.length;
    }
 
    public String getColumnName(int columnIndex) {
        // TODO Auto-generated method stub
        return columnNames[columnIndex];
    }
 
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
 
    // ��ͨ��heros.get(rowIndex)��ȡ�ж�Ӧ��Hero����
    // Ȼ�����columnIndex���ض�Ӧ������
    public Object getValueAt(int rowIndex, int columnIndex) {
        Course c = courses.get(rowIndex);
        if (0 == columnIndex)
            return c.getId();
        if (1 == columnIndex)
            return c.getName();
        if (2 == columnIndex)
            return c.getCredit();
        if (3 == columnIndex)
            return c.getTime();
        if(4 == columnIndex)
        	return c.getTime();
        return null;
    }

}
