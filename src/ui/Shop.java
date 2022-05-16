package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import shoppers.*;

public class Shop {

	public static Account oldAccount;
	public static Account account;
	
	public IO io = new IO();
	
	public AccountManager manager = io.createFromCSV();
	
	public Coordinate coordinateSystem = new Coordinate();
	
	public StoreIO storeIO = new StoreIO();
	public StoreManager storeManager = storeIO.createFromCSV();
	
	public static Stores selectedStores;
	
	
	public Shop() {
		
		oldAccount = LoginScreen.account;
		account = manager.getAccount(oldAccount.getEmail());
		
		
		List<Stores> stores = new ArrayList<Stores>();
		stores = generateStores();
		
		
		
		
		DefaultListModel<Stores> model = new DefaultListModel<>();
		
	
		for (Stores storesInRange : stores) {
			model.addElement(storesInRange);
		}
		
		JLabel storeInfo = new JLabel();
		JPanel panel = new JPanel();
		
		JList<Stores> listOfStores = new JList<>(model);
		
		listOfStores.getSelectionModel().addListSelectionListener(e ->  {
			Stores store = listOfStores.getSelectedValue();
			storeInfo.setText(store.getName() + " Items: \n" + store.items.toString().replace("[", "").replace("]", ""));
		});
		
		
		
		
		
		

		JFrame shopFrame = new JFrame("Shop");
		
		
		JButton goShopping = new JButton("Shop in this Store");
		goShopping.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shopFrame.dispose();
				
				if (listOfStores.getSelectedValue() == null) {
					JOptionPane noStore = new JOptionPane("You haven't selected a store");
				}
				else {
					selectedStores = storeManager.getStore(listOfStores.getSelectedValue().getName());
					ShoppingList shoppingList = new ShoppingList();
				}
				

			}
		});
		
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shopFrame.dispose();
				AccountPage accountPage = new AccountPage();
			}
		});



		
//		panel.add(storeInfo, BorderLayout.EAST);
//		panel.add(listOfStores, BorderLayout.CENTER);
		shopFrame.add(panel, BorderLayout.CENTER);
		shopFrame.add(storeInfo, BorderLayout.WEST);
		shopFrame.add(listOfStores, BorderLayout.CENTER);
		shopFrame.add(goShopping, BorderLayout.SOUTH);
		shopFrame.add(exit, BorderLayout.SOUTH);

		
		
		shopFrame.setLayout(new GridLayout(4, 2));
		
		shopFrame.setSize(720, 480);
		shopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shopFrame.setVisible(true);
		
	}
	
	
	public List<Stores> generateStores() {
		
		List<Stores> storesWithinRadius = new ArrayList<Stores>();
		
		for (Stores store : storeManager.storeList) {
			if (coordinateSystem.distance(account.coordinate, store.coordinate) <= account.radius) {
				storesWithinRadius.add(store);
			}
		}
		
		
		
		return storesWithinRadius;
	}
	
}

