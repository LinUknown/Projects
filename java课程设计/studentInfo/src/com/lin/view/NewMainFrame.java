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
		final JFrame f = new JFrame("ѧ����Ϣ����ϵͳ");
		f.setSize(1200, 800);
		f.setLocation(40, 50);
		// ��һ������
		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(null);
		JLabel weLabel = new JLabel(locaUser.getNickname()+"  Welcome to ѧ����Ϣ����ϵͳ");
		weLabel.setBounds(250,100 , 500, 200);
		welcomePanel.add(weLabel);
		f.add(welcomePanel);
		
		JMenuBar mb = new JMenuBar();
		
		JMenu mStudent = new JMenu("ѧ�����");
		JMenu mTeacher = new JMenu("��ʦ���");
		JMenu mAdmin = new JMenu("���񴦹���");
		JMenu mSystem = new JMenu("ϵͳ");
		
		JMenuItem itemXuanke = new JMenuItem("ѧ��ѡ��");
		JMenuItem itemChaxun = new JMenuItem("��ѯ�ɼ�");
		JMenuItem itemKecheng = new JMenuItem("�γ̹���");
		JMenuItem itemLu = new JMenuItem("�ɼ�¼��");
		JMenuItem itemStudentguanli = new JMenuItem("ѧ������");
		JMenuItem itemLogout= new JMenuItem("ע��");
		// ��С�˵���Ӽ�����
		
		itemXuanke.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(locaUser.getRoleId() != 1){
					JOptionPane.showMessageDialog(f, "��û��Ȩ�޷���");
				}
				else{
					JPanel studentPanel = new JPanel();
					try {
						toXuanke(studentPanel);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//����л�
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
					JOptionPane.showMessageDialog(f, "��û��Ȩ�޷���");
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
					JOptionPane.showMessageDialog(f, "��û��Ȩ�޷���");
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
					JOptionPane.showMessageDialog(f, "��û��Ȩ�޷���");
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
					JOptionPane.showMessageDialog(f, "��û��Ȩ�޷���");
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
		 * ѡ��
		 * 
		 * */
		xuankePanel.setLayout(new BorderLayout());
		
		JLabel xuankeLabel = new JLabel("��-- ѡ�� --��");
		JPanel nPanel = new JPanel();
		//nPanel.setLayout(null);
		xuankeLabel.setBounds(120, 100, 200, 50);
		
		nPanel.add(xuankeLabel);
		xuankePanel.add(nPanel,BorderLayout.NORTH);
		
		// �õ����
		JPanel tablePanel =new JPanel();
	 
		final JLabel chooseLabel= new JLabel("��ǰ��δѡ����Ŀ");
		JButton btn = new JButton("ѡ��");
		

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
		
		// ��ť �Ͱ�ťȷ��ѡ�μ�
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
					String sqlInsert = "insert into StudentAndCourse values ('"+locaUser.getId()+"','"+
				cCourse.getId()+"','0');";
					System.out.println("ѡ�ε�sql�� "+sqlInsert);
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
					JOptionPane.showMessageDialog(kechengPanel,"����ɹ�");

				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("����ʧ��");
					JOptionPane.showMessageDialog(kechengPanel, "����ʧ�ܣ������������Ϣ");
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
		JLabel l = new JLabel("¼��ɼ�: ");
		final JLabel l1 = new JLabel("����γ̱��");
		final JTextField t1 = new JTextField("");
		t1.setPreferredSize(new Dimension(80, 20));
		
		final JLabel l2 = new JLabel("����ѧ��ѧ��");
		final JTextField t2 = new JTextField();
		t2.setPreferredSize(new Dimension(80, 20));
		
		final JLabel l3 = new JLabel("����ɼ�");
		final JTextField t3 = new JTextField();
		t3.setPreferredSize(new Dimension(80, 20));
		
		final JButton bt = new JButton();
		bt.setText("���");
		bt.setPreferredSize(new Dimension(80, 20));
		
		
		// ������ʾ StudentAndCourse �ĸ�����Ϣ 
		final String sqlSelect = "select * from StudentAndCourse,course "
				+ " where studentandcourse.courseid = course.id and course.teacherid = "+locaUser.getId()+";";
		System.out.println("��ѯ�㿪�Ŀγ̵�sql --->" + sqlSelect);
		
		final StudentAndCourseTableModel ctm = new StudentAndCourseTableModel(locaUser);
		ctm.getList(sqlSelect);
		final JTable t = new JTable(ctm);
		JScrollPane sp = new JScrollPane(t);
		
		// ¼��ɼ�
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("¼��ɼ�");
				String updateSql = "update StudentAndCourse set score ="+t3.getText()+" where userid = "+ t2.getText() +" and courseid = " +t1.getText() +";";
		
				System.out.println("sql���Ϊ" + updateSql);
				BaseDao dao = new BaseDao();
				try {
					if(dao.insertCourse(updateSql)){
						JOptionPane.showMessageDialog(luPanel,"¼��ɼ��ɹ�");
					}
					else{
						System.out.println("¼��ɼ�ʧ��");
						JOptionPane.showMessageDialog(luPanel, "¼��ɼ�ʧ�ܣ������������Ϣ");
					}
				
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("¼��ɼ�ʧ��");
					JOptionPane.showMessageDialog(luPanel, "¼��ɼ�ʧ�ܣ������������Ϣ");
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
