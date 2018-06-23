package com.lin.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.lin.dao.BaseDao;
import com.lin.model.Course;
import com.lin.model.CourseTableMoel;
import com.lin.model.StudentAndCourseTableModel;
import com.lin.model.User;

public class NewMainFrame {
	User locaUser = null;
	static public void main(String args[]){
		new NewMainFrame(new User());
	}
	public NewMainFrame(User user){
		locaUser = user;
		final JFrame f = new JFrame("学生信息管理系统");
		f.setSize(1200, 800);
		f.setLocation(40, 50);
		// 第一个界面
		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(null);
		JLabel weLabel = new JLabel(locaUser.getNickname()+"  Welcome to 学生信息管理系统");
		weLabel.setBounds(250,100 , 500, 200);
		welcomePanel.add(weLabel);
		f.add(welcomePanel);
		
		JMenuBar mb = new JMenuBar();
		
		JMenu mStudent = new JMenu("学生入口");
		JMenu mTeacher = new JMenu("教师入口");
		JMenu mAdmin = new JMenu("教务处工作");
		JMenu mSystem = new JMenu("系统");
		
		JMenuItem itemXuanke = new JMenuItem("学生选课");
		JMenuItem itemChaxun = new JMenuItem("查询成绩");
		JMenuItem itemKecheng = new JMenuItem("课程管理");
		JMenuItem itemLu = new JMenuItem("成绩录入");
		JMenuItem itemStudentguanli = new JMenuItem("学生管理");
		JMenuItem itemLogout= new JMenuItem("注销");
		// 给小菜单添加监听器
		
		itemXuanke.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(locaUser.getRoleId() != 1){
					JOptionPane.showMessageDialog(f, "你没有权限访问");
				}
				else{
					JPanel studentPanel = new JPanel();
					try {
						toXuanke(studentPanel);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//面板切换
	                f.remove(f.getContentPane());
				    f.setContentPane(studentPanel);
				    f.setVisible(true);
				}
			}
		});
		
	
		itemChaxun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(locaUser.getRoleId() != 1){
					JOptionPane.showMessageDialog(f, "你没有权限访问");
				}
				else{
					JPanel chaXunPanel = new JPanel();
					try {
						toChaxun(chaXunPanel);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					f.remove(f.getContentPane());
					f.setContentPane(chaXunPanel);
					f.setVisible(true);
				}
				
			}
		});
		
		itemKecheng.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(locaUser.getRoleId() != 2){
					JOptionPane.showMessageDialog(f, "你没有权限访问");
				}
				else{
					JPanel kechengPanel = new JPanel();
					try {
						toKechengPanel(kechengPanel);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					f.remove(f.getContentPane());
					f.setContentPane(kechengPanel);
					f.setVisible(true);
				}
			}
		});
		
		itemLu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(locaUser.getRoleId() != 2){
					JOptionPane.showMessageDialog(f, "你没有权限访问");
				}
				else{
					JPanel luPanel = new JPanel();
					try {
						toLuPanel(luPanel);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					f.remove(f.getContentPane());
					f.setContentPane(luPanel);
					f.setVisible(true);
				}
			}
		});
		
		itemStudentguanli.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(locaUser.getRoleId() != 2){
					JOptionPane.showMessageDialog(f, "你没有权限访问");
				}
				else{
					JPanel studentguanliPanel = new JPanel();
				toStudentguanli(studentguanliPanel);
				f.remove(f.getContentPane());
				f.setContentPane(studentguanliPanel);
				f.setVisible(true);
				}
				
			}
		});
		
		itemLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
				new LoginFrame();
			}
		});
		
		mStudent.add(itemXuanke);
		mStudent.add(itemChaxun);
		
		mTeacher.add(itemKecheng);
		mTeacher.add(itemLu);
		
		mAdmin.add(itemStudentguanli);
		
		mSystem.add(itemLogout);
		
		mb.add(mStudent);
		mb.add(mTeacher);
		mb.add(mAdmin);
		mb.add(mSystem);
		
		f.setJMenuBar(mb);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
        f.setVisible(true);
	}
	
	public void toXuanke(final JPanel xuankePanel) throws SQLException{
		/*
		 * 选课
		 * 
		 * */
		xuankePanel.setLayout(new BorderLayout());
		
		JLabel xuankeLabel = new JLabel("【-- 选课 --】");
		JPanel nPanel = new JPanel();
		//nPanel.setLayout(null);
		xuankeLabel.setBounds(120, 100, 200, 50);
		
		nPanel.add(xuankeLabel);
		xuankePanel.add(nPanel,BorderLayout.NORTH);
		
		// 得到表格
		JPanel tablePanel =new JPanel();
	 
		final JLabel chooseLabel= new JLabel("当前暂未选中条目");
		JButton btn = new JButton("选中");
		

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
		
		// 按钮 和按钮确认选课监
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
					String sqlInsert = "insert into StudentAndCourse values ('"+locaUser.getId()+"','"+
				cCourse.getId()+"','0');";
					System.out.println("选课的sql是 "+sqlInsert);
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
		tablePanel.add(chooseLabel);
		tablePanel.add(btn);
		tablePanel.add(sp);
		xuankePanel.add(tablePanel,BorderLayout.CENTER);	
	}
	
	public void toChaxun(final JPanel chaxunPanel) throws SQLException{
		String sql = "select * from studentandcourse where userid ="+locaUser.getId()+";";
		final StudentAndCourseTableModel ctm = new StudentAndCourseTableModel(locaUser);
		ctm.getList(sql);
		final JTable t = new JTable(ctm);
		JScrollPane sp = new JScrollPane(t);
		chaxunPanel.add(sp);
	}
	
	
	public void toKechengPanel(final JPanel kechengPanel) throws SQLException{
		
		kechengPanel.setLayout(new BorderLayout());
		
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
					JOptionPane.showMessageDialog(kechengPanel,"插入成功");

				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("插入失败");
					JOptionPane.showMessageDialog(kechengPanel, "插入失败，请检查输入的信息");
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
		
		kechengPanel.add(np, BorderLayout.NORTH);
		kechengPanel.add(sp,BorderLayout.CENTER);
	
	}

	public void toLuPanel(final JPanel luPanel) throws SQLException{

		luPanel.setLayout(new BorderLayout());
		
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
						JOptionPane.showMessageDialog(luPanel,"录入成绩成功");
					}
					else{
						System.out.println("录入成绩失败");
						JOptionPane.showMessageDialog(luPanel, "录入成绩失败，请检查输入的信息");
					}
				
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("录入成绩失败");
					JOptionPane.showMessageDialog(luPanel, "录入成绩失败，请检查输入的信息");
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
		
		luPanel.add(np, BorderLayout.NORTH);
		luPanel.add(sp,BorderLayout.CENTER);
		
	 
	}


	public void toStudentguanli(final JPanel studentPanel){
			
	}
}
