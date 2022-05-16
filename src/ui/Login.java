package ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import shoppers.*;

public class Login {
	
	IO io = new IO();
	
	
	Director director = new Director();
	
	AccountManager manager = io.createFromCSV();
	
	public Login() {
		// TODO Auto-generated constructor stub
		
		
		JFrame login = new JFrame("Login");
		
		JButton signIn = new JButton("Sign In");
		signIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				LoginScreen loginScreen = new LoginScreen();
			}
		});
		
		
		JButton createAccount = new JButton("Create Account");
		createAccount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CreateAccount createAccount = new CreateAccount();
			}
		});
		
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login.dispose();
			}
		});


		login.add(signIn);
		login.add(createAccount);
		login.add(exit);
		
		login.setLayout(new GridLayout(3, 1));
		
		login.setSize(720, 480);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Login login = new Login();
	}

}
