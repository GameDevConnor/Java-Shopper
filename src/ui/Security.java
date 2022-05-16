package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import shoppers.Account;
import shoppers.AccountManager;
public class Security {
	
	public IO io = new IO();
	public AccountManager manager = io.createFromCSV();
	public static Account oldAccount;
	public static Account account;
	
	public Security() {
		
		oldAccount = LoginScreen.account;
		account = manager.getAccount(oldAccount.getEmail());
		
		JFrame security = new JFrame("Security");
		
		JLabel question = new JLabel("Choose your security question. This will replace your previous one: ");
		
		String[] questions = {"What is your favourite colour?", "Your favourite subject in school?", "Who wrote your favourite book?"};
		JComboBox<String> dropDown = new JComboBox<String>(questions);
		
		JTextField answer = new JTextField();
		
		
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				manager.updateSecurity(account.getEmail(), dropDown.getSelectedItem() + "/" + answer.getText());
				manager.write();
				manager.buildAccounts();
				
				account = manager.getAccount(account.getEmail(), account.getPassword());
				
				JOptionPane.showMessageDialog(security, "You've established a security method. I feel safer already");
				security.dispose();
				AccountPage accountPage = new AccountPage();

			}
		});
		
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				account = manager.getAccount(account.getEmail(), account.getPassword());
				security.dispose();
				AccountPage accountPage = new AccountPage();

			}
		});
		
		
		security.add(question, BorderLayout.SOUTH);
		security.add(dropDown, BorderLayout.SOUTH);
		security.add(answer, BorderLayout.SOUTH);
		security.add(add, BorderLayout.SOUTH);
		security.add(exit, BorderLayout.SOUTH);
		
		
		security.setLayout(new GridLayout(4, 2));
		
		security.setSize(720, 480);
		security.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		security.setVisible(true);
	}

}
