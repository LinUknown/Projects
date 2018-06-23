package com.lin.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lin.dao.UserDao;
import com.lin.model.User;

public class LoginFrame {
	public User user = null;
	private boolean canToMain = false;
	JFrame f = new JFrame("���½");
	
	static public void main(String []args){
		new LoginFrame();
	}
	public LoginFrame(){
		
		f.setSize(400, 300);
		f.setLocation(200, 250);
	
		JPanel loginPanel = new JPanel();
		toLoginPanel(loginPanel);
		f.add(loginPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public void toLoginPanel(final JPanel loginPanel){

		/**��¼����*/
		JPanel loginPanelNorth = new JPanel();
		loginPanelNorth.setLayout(new FlowLayout());
		JPanel loginPanelCenter = new JPanel();
		 
		JLabel nameLabel = new JLabel("�û���: ");
		JLabel passLabel = new JLabel("����:  ");
		
		final JTextField nameTextField = new JTextField("");
		nameTextField.setPreferredSize(new Dimension(80, 30));
		nameTextField.setText("");
		
		final JPasswordField passwordField = new JPasswordField("");
		passwordField.setPreferredSize(new Dimension(80, 30));
		passwordField.setText("");
		
		loginPanelNorth.add(nameLabel);
		loginPanelNorth.add(nameTextField);;	
		loginPanelNorth.add(passLabel);
		loginPanelNorth.add(passwordField);
		
		JButton loginButton = new JButton("��¼");
		String loginItems[] = {"ѧ��","��ʦ","����Ա"};
		final JComboBox whichLogin = new JComboBox(loginItems);
		
		loginButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*��¼��ť��������*/
				User locaUser = new User();
				locaUser.setName(nameTextField.getText());
				locaUser.setPassword(passwordField.getText());
				locaUser.setRoleId(whichLogin.getSelectedIndex()+1);
				
				UserDao userDao  = new UserDao();
				user = userDao.Login(locaUser);
				 
				if(user!= null){
					f.dispose();
					new NewMainFrame(user);
				}
				else{
					String talkTo = "�˺Ż��������";
					JOptionPane.showMessageDialog(loginPanel, talkTo);
				}
				
			}
		});
		
		loginPanelCenter.add(loginButton);
		loginPanelCenter.add(whichLogin);
		loginPanel.setLayout(new BorderLayout());
		
		loginPanel.add(loginPanelNorth,BorderLayout.NORTH);
		loginPanel.add(loginPanelCenter, BorderLayout.CENTER);
	}

	
}
