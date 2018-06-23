package com.lin.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.lin.dao.BaseDao;
import com.lin.dao.UserDao;
import com.lin.model.Course;
import com.lin.model.CourseTableMoel;
import com.lin.model.StudentAndCourseTableModel;
import com.lin.model.TeacherAndCourse;
import com.lin.model.User;

public class InsertScore {
	static public void main(String args[]) throws SQLException{
		new InsertScore(new User());
	}
	
	public InsertScore(final User locaUser) throws SQLException {
		final JFrame f = new JFrame("录入成绩");
		f.setSize(1200,800);
		f.setLocation(50, 100);
		f.setLayout(new BorderLayout());
		
		JPanel np = new JPanel();
		np.setLayout(new FlowLayout());
		JLabel l = new JLabel("录入成绩: ");
		final JLabel l1 = new JLabel("输入课程编号");
		final JTextField t1 = new JTextField("");
		t1.setPreferredSize(new Dimension(80, 20));
		
		final JLabel l2 = new JLabel("输入学生学号");
		final JTextField t2 = new JTextField();
		t2.setPreferredSize(new Dimension(80, 20));
		
		final JLabel l3 = new JLabel("输入成绩");
		final JTextField t3 = new JTextField();
		t3.setPreferredSize(new Dimension(80, 20));
		
		final JButton bt = new JButton();
		bt.setText("添加");
		bt.setPreferredSize(new Dimension(80, 20));
		
		
		// 底下显示 StudentAndCourse 的各项信息 
		final String sqlSelect = "select * from StudentAndCourse,course "
				+ " where studentandcourse.courseid = course.id and course.teacherid = "+locaUser.getId()+";";
		System.out.println("查询你开的课程的sql --->" + sqlSelect);
		
		final StudentAndCourseTableModel ctm = new StudentAndCourseTableModel(locaUser);
		ctm.getList(sqlSelect);
		final JTable t = new JTable(ctm);
		JScrollPane sp = new JScrollPane(t);
		
		// 录入成绩
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("录入成绩");
				String updateSql = "update StudentAndCourse set score ="+t3.getText()+" where userid = "+ t2.getText() +" and courseid = " +t1.getText() +";";
		
				System.out.println("sql语句为" + updateSql);
				BaseDao dao = new BaseDao();
				try {
					if(dao.insertCourse(updateSql)){
						JOptionPane.showMessageDialog(f,"录入成绩成功");
					}
					else{
						System.out.println("录入成绩失败");
						JOptionPane.showMessageDialog(f, "录入成绩失败，请检查输入的信息");
					}
				
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("录入成绩失败");
					JOptionPane.showMessageDialog(f, "录入成绩失败，请检查输入的信息");
					e1.printStackTrace();
				}
				try {
					ctm.getList(sqlSelect);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				t.updateUI();
			}
		});
		np.add(l);
		np.add(l1);
		np.add(t1);
		np.add(l2);
		np.add(t2);
		np.add(l3);
		np.add(t3);
		np.add(bt);
		
		f.add(np, BorderLayout.NORTH);
		f.add(sp,BorderLayout.CENTER);
		
		f.setVisible(true);
	}
}

