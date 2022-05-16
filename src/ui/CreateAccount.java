package ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import shoppers.*;

import ui.*;

public class CreateAccount {
	
	public CreateAccount() {
		JFrame createAccount = new JFrame("Create Account");
		
		JLabel enterE = new JLabel("Enter your email: ");
		JLabel enterP = new JLabel("Enter your password: ");
		
		JTextField email = new JTextField();
		JPasswordField pass = new JPasswordField();
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createAccount.dispose();
			}
		});
		
		JButton create = new JButton("Create");
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Builder builder = new AccountBuilder();
				
				if (builder.manager.nameExists(email.getText())) {
					JOptionPane.showMessageDialog(createAccount, "That name already exists, pick a new one");
				}
				else {
				builder.buildAccount(email.getText(), pass.getText());
				builder.manager.write();
				builder.manager.buildAccounts();
				JOptionPane.showMessageDialog(createAccount, "Congratulations" + " " + email.getText() + ", you now have an account... aren't you special");
				createAccount.dispose();
				}
			}
		});
		
		enterE.setBounds(50,50, 100,30);
		enterP.setBounds(50,60, 100,30);

		email.setBounds(100,enterE.getHorizontalAlignment(), 100,30);
		pass.setBounds(100,enterP.getVerticalAlignment(), 100,30);
		
		createAccount.add(enterE, BorderLayout.WEST);
		createAccount.add(enterP, BorderLayout.WEST);
		createAccount.add(email, BorderLayout.EAST);
		createAccount.add(pass, BorderLayout.EAST);
		createAccount.add(exit, BorderLayout.SOUTH);
		createAccount.add(create, BorderLayout.SOUTH);
		
		createAccount.setLayout(new GridLayout(3, 2));
		
		createAccount.setSize(720, 480);
		createAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createAccount.setVisible(true);
	}
	
}
