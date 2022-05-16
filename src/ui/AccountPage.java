package ui;

import javax.swing.*;

import shoppers.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AccountPage {
	
	public static Account oldAccount;
	public static Account account;
	
	public IO io = new IO();
	
	public AccountManager manager = io.createFromCSV();
	
	public AccountPage() {
				
		oldAccount = LoginScreen.account;
		account = manager.getAccount(oldAccount.getEmail());
		
		JFrame accountFrame = new JFrame("Account");
		
		JLabel accountLabel;
		if (account.coordinate.getRow() < 0 || account.coordinate.getCol() < 0) {
			accountLabel = new JLabel("Account: " + account.getEmail() + "\n " + "Your Address: " + "No Coordinates Set");
		}
		else {
			accountLabel = new JLabel("Account: " + account.getEmail() + "\n " + "Your Address: " + "(" + account.coordinate.getRow() + "," + account.coordinate.getCol() + ")");
		}
		
		
		JButton change = new JButton("Change Account Info");
		change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChangePassword changePassword = new ChangePassword();

				accountFrame.dispose();
								
			}
		});
		JButton security = new JButton("Establish Security Methods");
		security.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Security security = new Security();
				accountFrame.dispose();
			}
		});

		JButton delete = new JButton("Account Deletion");
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(accountFrame, "Wait just a minute there. This is a big decision and I need to make sure this is really you");
				if (account.getSecurity() == null) {
					JOptionPane.showMessageDialog(accountFrame, "Look, this is awkward, but you have no security measures. I'm not really comfortable letting you delete your account without an extra layer of security, come back when you have Established a Security Method. It's not you, it's me");
				}
				else {
					String[] dropdownOptions = {"What is your favourite colour?", "Your favourite subject in school?", "Who wrote your favourite book?"};
					String chosenQuestion = (String) JOptionPane.showInputDialog(accountFrame, "Choose your security question?", "Security Question", JOptionPane.QUESTION_MESSAGE, null, dropdownOptions, dropdownOptions[0]);
					String answer = JOptionPane.showInputDialog(accountFrame, "Answer the question");
					
					String checkAnswer = chosenQuestion + "/" + answer;
					
					if (checkAnswer.equals(account.getSecurity())) {
						int option = JOptionPane.showConfirmDialog(accountFrame, "Ok it is you, you sure about this?");
						
						if (option == JOptionPane.YES_OPTION) {
							manager.deleteAccount(account);
							manager.write();
							manager.buildAccounts();
							accountFrame.dispose();
						}
					}
					else {
						JOptionPane.showMessageDialog(accountFrame, "This doesn't match! NICE TRY HACKER!!!");
					}
					
				}
			}
		});
		
		
		
		JButton stores = new JButton("Store Information");
		stores.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StoresPage storesPage = new StoresPage();

				accountFrame.dispose();
								
			}
		});
		
		
		JButton shop = new JButton("Shop");
		shop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (account.getSecurity() == null) {
					JOptionPane.showMessageDialog(accountFrame, "Woah there my friend, you're entering dangerous waters here. I don't feel comfortable letting you shop without some security features. Establish them and then we'll talk");
				}
				else {
				Shop shopPage = new Shop();
				accountFrame.dispose();
				}			
			}
		});
		
		
		
		
		
		
		JButton managerButton = new JButton("Add Manager");
		managerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (!(account.getEmail().equals("Admin") && account.getPassword().equals("Admin"))) {
					JOptionPane.showMessageDialog(accountFrame, "You're not the admin, only the admin has these privileges");
				}
				else {
					Manager managerAccounts = new Manager();
					accountFrame.dispose();
				}			
			}
		});
		
		
		
		
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				accountFrame.dispose();
			}
		});

		accountFrame.add(accountLabel, BorderLayout.NORTH);
		accountFrame.add(change, BorderLayout.SOUTH);
		accountFrame.add(security, BorderLayout.SOUTH);
		accountFrame.add(delete, BorderLayout.SOUTH);
		
		if (account.isManager == true || (account.getEmail().equals("Admin") && account.getPassword().equals("Admin"))) {
			accountFrame.add(stores, BorderLayout.SOUTH);
			accountFrame.add(managerButton, BorderLayout.SOUTH);
		}
		accountFrame.add(shop, BorderLayout.SOUTH);
		accountFrame.add(exit, BorderLayout.SOUTH);
		
		
		accountFrame.setLayout(new GridLayout(4, 2));
		
		accountFrame.setSize(720, 480);
		accountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accountFrame.setVisible(true);
	}

}
