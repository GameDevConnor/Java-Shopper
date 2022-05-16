package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import shoppers.*;

import java.util.regex.*;

public class ChangePassword {
	
	public IO io = new IO();
	public AccountManager manager = io.createFromCSV();
	public static Account oldAccount;
	public static Account account;

	public Coordinate coordinateSystem = new Coordinate();
	
	public StoreIO storeIO = new StoreIO();
	public StoreManager storeManager = storeIO.createFromCSV();
	
	public ChangePassword() {
		
		
		oldAccount = LoginScreen.account;
		account = manager.getAccount(oldAccount.getEmail());

		
		JFrame password = new JFrame("Change Your Account Information");
		
		JLabel changePass = new JLabel("Enter your new password");
		JPasswordField pass = new JPasswordField();
		JLabel range = new JLabel("Enter an INTEGER value for how far you want to search for stores");
		JTextField enterRange = new JTextField();
		JLabel coordinate = new JLabel("Enter two INTEGERS in the format i,j to change your location");
		JTextField enterCoordinates = new JTextField();
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				password.dispose();
				account = manager.getAccount(account.getEmail(), account.getPassword());
				AccountPage accountPage = new AccountPage();
			}
		});
		
		JButton change = new JButton("Change Password");
		change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				manager.updatePassword(account.getEmail(), pass.getText());
				manager.write();
				manager.buildAccounts();
				JOptionPane.showMessageDialog(password, "Remember this password, we don't have a forget password option... we're not being graded on that");
				password.dispose();
				AccountPage accountPage = new AccountPage();

			}
		});
		
		JButton changeRange = new JButton("Enter Range");
		changeRange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Pattern integerPattern = Pattern.compile("[0-9]+");
				Matcher integerMatcher = integerPattern.matcher(enterRange.getText());
				boolean matched = integerMatcher.matches();
				
				if (matched) {
					manager.updateRadius(account.getEmail(), Integer.parseInt(enterRange.getText()));
					manager.write();
					manager.buildAccounts();
					account = manager.getAccount(account.getEmail());
					JOptionPane.showMessageDialog(password, "Happy Shopping!");
					password.dispose();
					AccountPage accountPage = new AccountPage();
				}
				else {
					JOptionPane.showMessageDialog(password, "That's not an integer");
				}

			}
		});
		
		JButton changeCoordinate = new JButton("Change your coordiantes");
		changeCoordinate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Pattern integerPattern = Pattern.compile("\\d+,\\d+");
				Matcher integerMatcher = integerPattern.matcher(enterCoordinates.getText());
				boolean matched = integerMatcher.matches();
				
				if (matched) {
					String[] coordateString = enterCoordinates.getText().split(",");
					Coordinate coordinate = new Coordinate(Integer.parseInt(coordateString[0]), Integer.parseInt(coordateString[1]));
					
					
					boolean contains = false;
					for (Account account : manager.accounts) {
						if (coordinateSystem.equals(coordinate, account.coordinate)) {
							contains = true;
							break;
						}
					}
					
					for (Stores store : storeManager.storeList) {
						if (coordinateSystem.equals(coordinate, store.coordinate)) {
							contains = true;
							break;
						}
					}
					
					if (contains) {
						JOptionPane.showMessageDialog(password, "Something already exists at this location, so you can't place yourself here... unless you're a ghost");
					}
					else {
						int[] newCoordinates = {Integer.parseInt(coordateString[0]), Integer.parseInt(coordateString[1])};
						manager.updateCoordinates(account.getEmail(), newCoordinates);
						manager.write();
						manager.buildAccounts();
						account = manager.getAccount(account.getEmail());

						JOptionPane.showMessageDialog(password, "You've changed your location");
						password.dispose();
						AccountPage accountPage = new AccountPage();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(password, "That's not the correct format");
				}

			}
		});

		password.add(changePass, BorderLayout.WEST);
		password.add(pass, BorderLayout.WEST);
		password.add(change, BorderLayout.WEST);
		
		password.add(range, BorderLayout.EAST);
		password.add(enterRange, BorderLayout.EAST);
		password.add(changeRange, BorderLayout.EAST);
		
		password.add(coordinate, BorderLayout.EAST);
		password.add(enterCoordinates, BorderLayout.EAST);
		password.add(changeCoordinate, BorderLayout.EAST);

		password.add(exit, BorderLayout.SOUTH);
		
		password.setLayout(new GridLayout(4, 3));
		
		password.setSize(720, 480);
		password.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		password.setVisible(true);
		
		
		
	}
}
