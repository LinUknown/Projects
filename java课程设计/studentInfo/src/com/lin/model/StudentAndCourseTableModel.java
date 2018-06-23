package com.lin.model;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lin.dao.BaseDao;
import com.lin.model.*;


public class StudentAndCourseTableModel extends AbstractTableModel {
	String[] columnNames = new String[] {"ѧ�����","�γ̱��","�ɼ�"};
	private User localUser;
	public ArrayList<StudentAndCourse> courses = new ArrayList<StudentAndCourse>();
	
	public StudentAndCourseTableModel (User user) throws SQLException{
		localUser = user;
	}
	public void getList(String sql) throws SQLException{
		courses.clear();
		BaseDao dao = new BaseDao();
		 ResultSet rs = dao.query(sql);
		 int col = rs.getMetaData().getColumnCount();
		 while(rs.next()){
			 System.out.println("��ѯ�γ̳ɼ��ɹ�");
			 StudentAndCourse  sc = new StudentAndCourse();
			 sc.setCourseId(rs.getInt("courseid"));
			 sc.setStudentId(rs.getInt("userid"));
			 sc.setScore(rs.getInt("score"));
			 courses.add(sc);
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
    	StudentAndCourse c = courses.get(rowIndex);
        if (0 == columnIndex)
            return c.getStudentId();
        if (1 == columnIndex)
            return c.getCourseId();
        if (2 == columnIndex)
            return c.getScore();
        return null;
    }

}
