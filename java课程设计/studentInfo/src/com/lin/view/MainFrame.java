package com.lin.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.lin.dao.BaseDao;
import com.lin.model.Course;
import com.lin.model.CourseTableMoel;
import com.lin.model.User;

public class MainFrame {
	User locaUser = new User();
	
	static public void main(String args[]) throws SQLException{
		new MainFrame(new User());
	}
	public MainFrame(User user) throws SQLException{
		locaUser = user;
		JFrame f = new JFrame("ѧ����Ϣ����ϵͳ");
		f.setLayout(null);
		f.setSize(800, 600);
		f.setLocation(200, 250);
		 
		//�������µ��ĸ�����
		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(null);
		JLabel weLabel = new JLabel(locaUser.getNickname()+"  Welcome to ѧ����Ϣ����ϵͳ");
		weLabel.setBounds(250,100 , 500, 200);
		welcomePanel.add(weLabel);
		
		JPanel studentPanel = new JPanel();
		studentPanel.setSize(f.size());
		studentPanel.setLayout(null);
		toStudent(studentPanel);
		
		JPanel teacherPanel = new JPanel();
		teacherPanel.setSize(f.size());
		toTeacher(teacherPanel);
		
		JPanel adminPanel = new JPanel();
		adminPanel.setSize(f.size());
		toAdmin(adminPanel);
		
		JTabbedPane tp = new JTabbedPane();
		tp.add("��ҳ", welcomePanel);
		tp.add("ѧ�����", studentPanel);
		tp.add("��ʦ���", teacherPanel);
		tp.add("����Ա���", adminPanel);
		
		f.setContentPane(tp);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}
	public void toStudent(JPanel studentPanel) throws SQLException{
		/*
		 * ѧ�����棬����ѡ�Σ��Ͳ�ѯ�Լ��ĳɼ�
		 * 
		 * */
		studentPanel.setLayout(null);
		
		JLabel locaLabel = new JLabel("");
		locaLabel.setText("ѧ�� :"+locaLabel.getName()+"  ��ã� ����Ҫ����ʲô��(300,0)");
		locaLabel.setSize(250,50);
		locaLabel.setLocation(300,0);
		//ѡ�ΰ���
				
		final JPanel xuankePanel = new JPanel();
		xuankePanel.setLayout(null);
		xuankePanel.setBounds(0, 20, 300, 600);
		
		JLabel xuankeLabel = new JLabel("��-- ѡ�� --��(50,100)");
		xuankeLabel.setBounds(50,0,250,100);
		
		// �õ����
		JPanel tablePanel =new JPanel();
		tablePanel.setLayout(null);
		tablePanel.setBounds(0, 80, 450	, 600);
		
		final JLabel chooseLabel= new JLabel("��ǰ��δѡ����Ŀ");
		JButton btn = new JButton("ѡ��");
		
		btn.setBounds(200, 15, 60, 20);
		chooseLabel.setBounds(60,0,150,50);
		
		// ��� �ͱ���ѡ�м���
		// �Ѿ�ѡ�еĲ��ٳ���
		//String sql = "select * from course;";
		final String sql = "select * from course where course.id not in ("
				+ "select course.id "
				+ "from course,StudentAndCourse "
				+ "where StudentAndCourse.userid ="+locaUser.getId()+" and StudentAndCourse.courseid = course.id"
						+ ");";
		System.out.println("sql����� ----"+sql);
		
		final CourseTableMoel ctm = new CourseTableMoel(locaUser);
		ctm.getList(sql);
		final JTable t = new JTable(ctm);
		 
		final JLabel zhancunLabel = new JLabel("");
		t.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int row = t.getSelectedRow();
				Course c = ctm.courses.get(row);
				chooseLabel.setText("��ǰѡ��: " + c.getName());
				zhancunLabel.setText(String.valueOf(row));
			}
		});
		
		// ��ť �Ͱ�ťȷ��ѡ�μ���

		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cntString = zhancunLabel.getText();
				
				if(cntString.equals("")){
					JOptionPane.showMessageDialog(xuankePanel, "�����ѡ��һ�ſ�");
				}
				else {
					int row = Integer.valueOf(zhancunLabel.getText());
					final Course cCourse = ctm.courses.get(row);
					String sqlInsert = "insert into StudentAndCourse values ('"+cCourse.getId()+"','"+
				locaUser.getId()+"','0');";
					BaseDao bd = new BaseDao();
					try {
						System.out.println("ѡ�γɹ�");
						bd.insertCourse(sqlInsert);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("ѡ�β������ݿ�ʱ����");
					}
					
					
					try {
						ctm.getList(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					t.updateUI();
					 
				}
			}
		});
	
		JScrollPane sp = new JScrollPane(t);
		sp.setBounds(0, 50, 300, 350);
		
		xuankePanel.add(xuankeLabel);
		tablePanel.add(chooseLabel);
		tablePanel.add(btn);
		tablePanel.add(sp);
		xuankePanel.add(tablePanel);
		 
		studentPanel.add(xuankePanel);
		// ��ɼ�����
		
		/*JPanel queryPanel = new JPanel();
		queryPanel.setLayout(null);
		
		JLabel queryLabel = new JLabel("��-- �ɼ���ѯ --��(500,100)");
		queryLabel.setBounds(550, 0, 250, 100);
		
		// �õ���� Panel
		JPanel queryTablePanel =new JPanel();
		queryTablePanel.setLayout(null);
		queryTablePanel.setBounds(550, 80, 450, 600);
		
		
		final String sqlQuery = "select * from course where course.id not in ("
				+ "select course.id "
				+ "from course,StudentAndCourse "
				+ "where StudentAndCourse.userid ="+locaUser.getId()+" and StudentAndCourse.courseid = course.id"
						+ ");";
		System.out.println("sql����� ----"+sql);
		
		final CourseTableMoel ctmQuery = new CourseTableMoel(locaUser);
		ctmQuery.getList(sql);
		final JTable tQ = new JTable(ctmQuery);
		JScrollPane spQ = new JScrollPane(tQ);
		spQ.setBounds(0, 50, 300, 350);
		
		queryTablePanel.add(spQ);
		queryTablePanel.add(queryLabel);
		queryPanel.add(queryTablePanel);
		 
		studentPanel.add(queryPanel);*/
		
	}
	public void toTeacher(JPanel teacherPanel){
		/*��ʦ�Ľ��� ��������
		 * 			  ���ӿγ�
		 * 1.���ÿγ� --ɾ���γ�
		 * 			  �޸Ŀγ�
		 * 
		 * 			 ѧԱ���� - �����ɼ�
		 * 2.�γ̹��� --����ɼ�
		 * 
		 * 
		 * */
		// teacherP �Ǵ��     ������ cPanelռ�м�  nPanelռ���� 
		
		teacherPanel.setLayout(new BorderLayout());
		JPanel cPanel = new JPanel();
		cPanel.setLayout(null);
		JPanel nPanel = new JPanel();
	
		// nPanel 
		JLabel locaLabel = new JLabel("");
		locaLabel.setText("��ʦ :"+locaLabel.getName()+"  ��ã� ����Ҫ����ʲô��");
		locaLabel.setSize(250,50);
		
		nPanel.add(locaLabel);
		
		// cPanel
			// �����γ�
		JLabel queryCourseLabel = new JLabel("");
		queryCourseLabel.setText("������������鿴���޸����Ŀγ�");
		queryCourseLabel.setBounds(200,30,300,80);
		cPanel.add(queryCourseLabel);
		
		JButton queryCourseButton = new JButton("�������");
		queryCourseButton.setBounds(400,50,100,30);
		queryCourseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//�����ѯ������һ�����ڣ�
				// �������ݿ��У���ʦ�����Ŀγ̱� ���ر�����
				try {
					new QueryCourseFrame(locaUser);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cPanel.add(queryCourseButton);
		
		    // ¼��ɼ�
		JLabel buildCourseLabel = new JLabel("");
		buildCourseLabel.setText("¼��ɼ�");
		buildCourseLabel.setBounds(250,80,150,80);
		cPanel.add(buildCourseLabel);
		
		JButton buildCourseButton = new JButton("��ѯ");
		buildCourseButton.setBounds(400,110,100,30);
		buildCourseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//�����ѯ������һ�����ڣ�
				// �������ݿ��У���ʦ�����Ŀγ̱� ���ر�����
				try {
					new InsertScore(locaUser);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cPanel.add(buildCourseButton);
		
		  
		teacherPanel.add(nPanel, BorderLayout.NORTH);
		teacherPanel.add(cPanel, BorderLayout.CENTER);
	}
	
	
	public void toAdmin(JPanel adminPanel){
		
	}
}
