package com.lin.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Insets;
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
import javax.swing.border.Border;

import com.lin.dao.BaseDao;
import com.lin.dao.UserDao;
import com.lin.model.Course;
import com.lin.model.CourseTableMoel;
import com.lin.model.User;

public class QueryCourseFrame {
	static public void main(String args[]) throws SQLException{
		new QueryCourseFrame(new User());
	}

	public QueryCourseFrame(final User locaUser) throws SQLException {
		// TODO Auto-generated constructor stub
		// 点击查询后弹出的界面， 包含一个表格，每项加一个 详情，返回课程具体内容体
		
		final JFrame f = new JFrame("课程查询");
		f.setSize(1200,800);
		f.setLocation(50, 100);
		f.setLayout(new BorderLayout());
		
		JPanel np = new JPanel();
		np.setLayout(new FlowLayout());
		JLabel l = new JLabel("新增课程: ");
		final JLabel l1 = new JLabel("输入课程名");
		final JTextField t1 = new JTextField("");
		t1.setPreferredSize(new Dimension(80, 20));
		
		final JLabel l2 = new JLabel("请输入课程编号(可能已经存在)");
		final JTextField t2 = new JTextField();
		t2.setPreferredSize(new Dimension(80, 20));
		
		final JLabel l3 = new JLabel("请输入学分");
		final JTextField t3 = new JTextField();
		t3.setPreferredSize(new Dimension(80, 20));
		
		final JLabel l4 = new JLabel("请输入学时");
		final JTextField t4 = new JTextField();
		t4.setPreferredSize(new Dimension(80, 20));
		
		final JLabel l5 = new JLabel("请输入任课教师");
		final JTextField t5 = new JTextField();
		t5.setPreferredSize(new Dimension(80, 20));
		
		final JButton bt = new JButton();
		bt.setText("确认");
		bt.setPreferredSize(new Dimension(80, 20));
		
		// 表格部分
		
		final String sql = "select * from course"
				+ " where teacherid ="+locaUser.getId()+";";
		System.out.println("查询的sql是  " + sql);
		final CourseTableMoel ctm = new CourseTableMoel(locaUser);
		ctm.getList(sql);
		final JTable t = new JTable(ctm);
		JScrollPane sp = new JScrollPane(t);
		
		// 增加成分
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("增加一个课程");
				String sqlInsert = "insert into course values ('"+t2.getText()+"', '"+t1.getText()+"','"+
							t4.getText() + "','" +t3.getText() +"' ,'0','"+locaUser.getId()+"','"+
							t5.getText() +"');" ;
				System.out.println("inserCourse is  "+sqlInsert);
				
				BaseDao dao = new BaseDao();
				System.out.println("增加一个课程2");
				try {
					dao.insertCourse(sqlInsert);
					JOptionPane.showMessageDialog(f,"插入成功");

				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("插入失败");
					JOptionPane.showMessageDialog(f, "插入失败，请检查输入的信息");
					e1.printStackTrace();
				}
				 
				try {
					ctm.getList(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		np.add(l4);
		np.add(t4);
		np.add(l5);
		np.add(t5);

		np.add(bt);
		
		f.add(np, BorderLayout.NORTH);
		f.add(sp,BorderLayout.CENTER);
		
		f.setVisible(true);
	}
}
