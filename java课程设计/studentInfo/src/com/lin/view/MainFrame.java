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
		JFrame f = new JFrame("学生信息管理系统");
		f.setLayout(null);
		f.setSize(800, 600);
		f.setLocation(200, 250);
		 
		//主界面下的四个界面
		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(null);
		JLabel weLabel = new JLabel(locaUser.getNickname()+"  Welcome to 学生信息管理系统");
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
		tp.add("主页", welcomePanel);
		tp.add("学生入口", studentPanel);
		tp.add("教师入口", teacherPanel);
		tp.add("管理员入口", adminPanel);
		
		f.setContentPane(tp);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}
	public void toStudent(JPanel studentPanel) throws SQLException{
		/*
		 * 学生界面，可以选课，和查询自己的成绩
		 * 
		 * */
		studentPanel.setLayout(null);
		
		JLabel locaLabel = new JLabel("");
		locaLabel.setText("学生 :"+locaLabel.getName()+"  你好！ 今天要做点什么？(300,0)");
		locaLabel.setSize(250,50);
		locaLabel.setLocation(300,0);
		//选课半栏
				
		final JPanel xuankePanel = new JPanel();
		xuankePanel.setLayout(null);
		xuankePanel.setBounds(0, 20, 300, 600);
		
		JLabel xuankeLabel = new JLabel("【-- 选课 --】(50,100)");
		xuankeLabel.setBounds(50,0,250,100);
		
		// 得到表格
		JPanel tablePanel =new JPanel();
		tablePanel.setLayout(null);
		tablePanel.setBounds(0, 80, 450	, 600);
		
		final JLabel chooseLabel= new JLabel("当前暂未选中条目");
		JButton btn = new JButton("选中");
		
		btn.setBounds(200, 15, 60, 20);
		chooseLabel.setBounds(60,0,150,50);
		
		// 表格 和表格加选中监听
		// 已经选中的不再出现
		//String sql = "select * from course;";
		final String sql = "select * from course where course.id not in ("
				+ "select course.id "
				+ "from course,StudentAndCourse "
				+ "where StudentAndCourse.userid ="+locaUser.getId()+" and StudentAndCourse.courseid = course.id"
						+ ");";
		System.out.println("sql语句是 ----"+sql);
		
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
				chooseLabel.setText("当前选中: " + c.getName());
				zhancunLabel.setText(String.valueOf(row));
			}
		});
		
		// 按钮 和按钮确认选课监听

		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cntString = zhancunLabel.getText();
				
				if(cntString.equals("")){
					JOptionPane.showMessageDialog(xuankePanel, "你必须选中一门课");
				}
				else {
					int row = Integer.valueOf(zhancunLabel.getText());
					final Course cCourse = ctm.courses.get(row);
					String sqlInsert = "insert into StudentAndCourse values ('"+cCourse.getId()+"','"+
				locaUser.getId()+"','0');";
					BaseDao bd = new BaseDao();
					try {
						System.out.println("选课成功");
						bd.insertCourse(sqlInsert);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("选课插入数据库时出错");
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
		// 查成绩半区
		
		/*JPanel queryPanel = new JPanel();
		queryPanel.setLayout(null);
		
		JLabel queryLabel = new JLabel("【-- 成绩查询 --】(500,100)");
		queryLabel.setBounds(550, 0, 250, 100);
		
		// 得到表格 Panel
		JPanel queryTablePanel =new JPanel();
		queryTablePanel.setLayout(null);
		queryTablePanel.setBounds(550, 80, 450, 600);
		
		
		final String sqlQuery = "select * from course where course.id not in ("
				+ "select course.id "
				+ "from course,StudentAndCourse "
				+ "where StudentAndCourse.userid ="+locaUser.getId()+" and StudentAndCourse.courseid = course.id"
						+ ");";
		System.out.println("sql语句是 ----"+sql);
		
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
		/*教师的界面 功能如下
		 * 			  增加课程
		 * 1.设置课程 --删除课程
		 * 			  修改课程
		 * 
		 * 			 学员名单 - 包括成绩
		 * 2.课程管理 --输入成绩
		 * 
		 * 
		 * */
		// teacherP 是大框     里面由 cPanel占中间  nPanel占上面 
		
		teacherPanel.setLayout(new BorderLayout());
		JPanel cPanel = new JPanel();
		cPanel.setLayout(null);
		JPanel nPanel = new JPanel();
	
		// nPanel 
		JLabel locaLabel = new JLabel("");
		locaLabel.setText("教师 :"+locaLabel.getName()+"  你好！ 今天要做点什么？");
		locaLabel.setSize(250,50);
		
		nPanel.add(locaLabel);
		
		// cPanel
			// 所开课程
		JLabel queryCourseLabel = new JLabel("");
		queryCourseLabel.setText("您可以在这里查看和修改您的课程");
		queryCourseLabel.setBounds(200,30,300,80);
		cPanel.add(queryCourseLabel);
		
		JButton queryCourseButton = new JButton("点击这里");
		queryCourseButton.setBounds(400,50,100,30);
		queryCourseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//点击查询，弹出一个窗口，
				// 访问数据库中，教师所开的课程表 返回表内容
				try {
					new QueryCourseFrame(locaUser);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cPanel.add(queryCourseButton);
		
		    // 录入成绩
		JLabel buildCourseLabel = new JLabel("");
		buildCourseLabel.setText("录入成绩");
		buildCourseLabel.setBounds(250,80,150,80);
		cPanel.add(buildCourseLabel);
		
		JButton buildCourseButton = new JButton("查询");
		buildCourseButton.setBounds(400,110,100,30);
		buildCourseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//点击查询，弹出一个窗口，
				// 访问数据库中，教师所开的课程表 返回表内容
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
