package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import shoppers.Account;
import shoppers.AccountManager;
import shoppers.Coordinate;
import shoppers.Items;
import shoppers.StoreManager;
import shoppers.Stores;


public class Manager {
	
	public static Account oldAccount;
	public static Account account;

	public IO io = new IO();

	public AccountManager manager = io.createFromCSV();

	public Coordinate coordinateSystem = new Coordinate();

	public StoreIO storeIO = new StoreIO();
	public StoreManager storeManager = storeIO.createFromCSV();

	public static Stores selectedStores;
	
	public Manager() {
		
		oldAccount = LoginScreen.account;
		account = manager.getAccount(oldAccount.getEmail());
		
		
		DefaultListModel<Account> model = new DefaultListModel<>();
		
		for (Account accounts : manager.accounts) {
			model.addElement(accounts);
		}
		
		JLabel itemInfo = new JLabel();
	//	JPanel panel = new JPanel();
		
		JList<Account> listOfItems = new JList<>(model);
		
		listOfItems.getSelectionModel().addListSelectionListener(e ->  {
			Account selectedItem = listOfItems.getSelectedValue();
			itemInfo.setText(selectedItem.toString());
		});
		
		
		JFrame managerFrame = new JFrame("Manager");
		
		
		JButton managerButton = new JButton("Make Manager");
		managerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (listOfItems.getSelectedValue() == null) {
					JOptionPane noStore = new JOptionPane("You haven't selected an account");
				}
				else {
					String storeToManage = JOptionPane.showInputDialog(managerFrame, "What store should this person manage");
					Account selectedItem = listOfItems.getSelectedValue();
					manager.managerStore.put(selectedItem.getEmail(), storeToManage);
					manager.writeManager();
					manager.buildAccounts();
					managerFrame.dispose();
					Manager managerRefreshed = new Manager();
				}
			}
		});
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				managerFrame.dispose();
				AccountPage accountPage = new AccountPage();
			}
		});
		
		
		
		
		
		managerFrame.add(itemInfo, BorderLayout.CENTER);
		managerFrame.add(listOfItems, BorderLayout.CENTER);
		managerFrame.add(managerButton, BorderLayout.SOUTH);
		managerFrame.add(exit, BorderLayout.SOUTH);
		
		
		managerFrame.setLayout(new GridLayout(4, 2));
		
		managerFrame.setSize(720, 480);
		managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		managerFrame.setVisible(true);
	}

}
