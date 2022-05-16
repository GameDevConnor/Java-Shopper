package ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import shoppers.*;


public class LoginScreen {

	JTextField email;
	JPasswordField password;
	
	IO io = new IO();
	
	
	Director director = new Director();
	
	AccountManager manager = io.createFromCSV();
	
	
	public static Account account;
	
	
	public LoginScreen() {
		
		
		manager.buildAccounts();
		
		
		JFrame loginScreen = new JFrame("Sign In");
		
	//	System.out.println("Opening login screen: "+manager.toString() + manager.accounts);

		email = new JTextField();
		password = new JPasswordField();
		
		JLabel enterE = new JLabel("Enter your email: ");
		JLabel enterP = new JLabel("Enter your password: ");
		
		
		loginScreen.add(enterE, BorderLayout.WEST);
		loginScreen.add(enterP, BorderLayout.WEST);
		loginScreen.add(email, BorderLayout.EAST);
		loginScreen.add(password, BorderLayout.EAST);
		
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				loginScreen.dispose();
			}
		});
		
		
		
		JButton login = new JButton("Log In");
		login.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (director.checkUser(email.getText(), password.getText())) {
					
					account = manager.getAccount(email.getText(), password.getText());					
					AccountPage accountPage = new AccountPage();
					loginScreen.dispose();
				}
				else {
					JOptionPane.showMessageDialog(loginScreen, "Login Failed");
				}
				
			}
		});
		
		
		
		
		loginScreen.add(exit, BorderLayout.SOUTH);
		loginScreen.add(login, BorderLayout.SOUTH);
		
		loginScreen.setLayout(new GridLayout(3, 2));
		
		loginScreen.setSize(720, 480);
		loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginScreen.setVisible(true);
		
	}
	
	public String getEmail() {
		return email.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getPassowrd() {
		return password.getText();
	}
	
}
