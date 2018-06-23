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
		// �����ѯ�󵯳��Ľ��棬 ����һ�����ÿ���һ�� ���飬���ؿγ̾���������
		
		final JFrame f = new JFrame("�γ̲�ѯ");
		f.setSize(1200,800);
		f.setLocation(50, 100);
		f.setLayout(new BorderLayout());
		
		JPanel np = new JPanel();
		np.setLayout(new FlowLayout());
		JLabel l = new JLabel("�����γ�: ");
		final JLabel l1 = new JLabel("����γ���");
		final JTextField t1 = new JTextField("");
		t1.setPreferredSize(new Dimension(80, 20));
		
		final JLabel l2 = new JLabel("������γ̱��(�����Ѿ�����)");
		final JTextField t2 = new JTextField();
		t2.setPreferredSize(new Dimension(80, 20));
		
		final JLabel l3 = new JLabel("������ѧ��");
		final JTextField t3 = new JTextField();
		t3.setPreferredSize(new Dimension(80, 20));
		
		final JLabel l4 = new JLabel("������ѧʱ");
		final JTextField t4 = new JTextField();
		t4.setPreferredSize(new Dimension(80, 20));
		
		final JLabel l5 = new JLabel("�������ον�ʦ");
		final JTextField t5 = new JTextField();
		t5.setPreferredSize(new Dimension(80, 20));
		
		final JButton bt = new JButton();
		bt.setText("ȷ��");
		bt.setPreferredSize(new Dimension(80, 20));
		
		// ��񲿷�
		
		final String sql = "select * from course"
				+ " where teacherid ="+locaUser.getId()+";";
		System.out.println("��ѯ��sql��  " + sql);
		final CourseTableMoel ctm = new CourseTableMoel(locaUser);
		ctm.getList(sql);
		final JTable t = new JTable(ctm);
		JScrollPane sp = new JScrollPane(t);
		
		// ���ӳɷ�
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("����һ���γ�");
				String sqlInsert = "insert into course values ('"+t2.getText()+"', '"+t1.getText()+"','"+
							t4.getText() + "','" +t3.getText() +"' ,'0','"+locaUser.getId()+"','"+
							t5.getText() +"');" ;
				System.out.println("inserCourse is  "+sqlInsert);
				
				BaseDao dao = new BaseDao();
				System.out.println("����һ���γ�2");
				try {
					dao.insertCourse(sqlInsert);
					JOptionPane.showMessageDialog(f,"����ɹ�");

				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("����ʧ��");
					JOptionPane.showMessageDialog(f, "����ʧ�ܣ������������Ϣ");
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
