package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ShoppingList {

	public static Account oldAccount;
	public static Account account;
	
	public IO io = new IO();
	
	public AccountManager manager = io.createFromCSV();
	
	public Coordinate coordinateSystem = new Coordinate();

	public StoreIO storeIO = new StoreIO();
	public StoreManager storeManager = storeIO.createFromCSV();
	
	public static Stores currentStores;
	
	public ShoppingList() {
		
		oldAccount = LoginScreen.account;
		account = manager.getAccount(oldAccount.getEmail());
		
		currentStores = storeManager.getStore(Shop.selectedStores.name);

				
		
		DefaultListModel<Items> model = new DefaultListModel<>();
	
		for (Items itemsInStore : currentStores.items) {
			model.addElement(itemsInStore);
		}
		
		for (int i = 0; i < model.size(); i++) {
			if (model.get(i).getAvailability() < 1) {
				model.remove(i);
			}
		}
		
		JLabel itemInfo = new JLabel();
	//	JPanel panel = new JPanel();
		
		JList<Items> listOfItems = new JList<>(model);
		
		listOfItems.getSelectionModel().addListSelectionListener(e ->  {
			Items selectedItem = listOfItems.getSelectedValue();
			itemInfo.setText(selectedItem.getName() + " \n" + "Price: " + "$" + selectedItem.getPrice() + " \nSize: " + selectedItem.getSize() + " \n" + selectedItem.getAvailability() + " currently in stock");
		});
		
		
		
		
		DefaultListModel<Items> listModel = new DefaultListModel<>();
		
		for (Items userItems : account.shoppingCart) {
			
			if (userItems.stores == null) {
				JOptionPane option = new JOptionPane("You don't have any items in your cart");
			}
			else {
				
				if (userItems.stores.getName().equals(currentStores.getName())) {
					listModel.addElement(userItems);
				}
			}
			
		}
		
		
		for (int i = 0; i < listModel.size(); i++) {
			if (!listModel.get(i).getStores().getName().equals(currentStores.name)) {
				listModel.remove(i);
			}
		}
		
		JLabel itemInfoinList = new JLabel();
	//	JPanel panel = new JPanel();
		
		JList<Items> shoppingList = new JList<>(listModel);
		
		shoppingList.getSelectionModel().addListSelectionListener(e ->  {
			Items selectedItem = shoppingList.getSelectedValue();
			itemInfo.setText(selectedItem.getName() + " \n" + "Price: " + "$" + selectedItem.getPrice() + " \nSize: " + selectedItem.getSize() + " \n" + selectedItem.getAvailability() + " currently in stock");
		});
		

		JFrame shoppingListFrame = new JFrame("Shopping in " + currentStores.getName());
		
		JButton addToList = new JButton("Add to Shopping List");
		addToList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Items selectedItems = listOfItems.getSelectedValue();
				
				if (listOfItems.getSelectedValue() == null) {
					JOptionPane noItem = new JOptionPane("You haven't selected an item");
				}
				else {
					manager.addItem(account.getEmail(), selectedItems, currentStores);
					manager.write();
					manager.buildAccounts();
					storeManager.updateAvailability(currentStores.getName(), selectedItems.getName(), selectedItems.getAvailability() - 1);
					storeManager.write();
					storeManager.buildStores();
					shoppingListFrame.dispose();
					ShoppingList shoppingList = new ShoppingList();
				}
			}
		});
		
		
		
		
		JButton removeFromList = new JButton("Remove from Shopping List");
		removeFromList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Items selectedItems = shoppingList.getSelectedValue();
				
				if (shoppingList.getSelectedValue() == null) {
					JOptionPane noItem = new JOptionPane("You haven't selected an item");
				}
				else {
					manager.removeItem(account.getEmail(), selectedItems, currentStores);
					manager.write();
					manager.buildAccounts();
					
					if (getItem(model, selectedItems) == null) {
						storeManager.addItem(currentStores.getName(), selectedItems);
						storeManager.updateAvailability(currentStores.getName(), selectedItems.getName(), 1);
					}
					else {
						
						storeManager.updateAvailability(currentStores.getName(), selectedItems.getName(), getItem(model, selectedItems).getAvailability() + 1);
					}
					storeManager.write();
					storeManager.buildStores();
					shoppingListFrame.dispose();
					ShoppingList shoppingList = new ShoppingList();
				}
			}
		});
		
				
		
		
		JButton checkOut = new JButton("Check Out");
		checkOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Reccomendations reccomendations = new Reccomendations();
				shoppingListFrame.dispose();
			}
		});
		
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shoppingListFrame.dispose();
				AccountPage accountPage = new AccountPage();
			}
		});



		
//		panel.add(storeInfo, BorderLayout.EAST);
//		panel.add(listOfStores, BorderLayout.CENTER);
//		shoppingListFrame.add(panel, BorderLayout.CENTER);
		shoppingListFrame.add(itemInfo, BorderLayout.CENTER);
		shoppingListFrame.add(listOfItems, BorderLayout.CENTER);
		shoppingListFrame.add(addToList, BorderLayout.SOUTH);
		shoppingListFrame.add(removeFromList, BorderLayout.SOUTH);
		shoppingListFrame.add(itemInfoinList, BorderLayout.CENTER);
		shoppingListFrame.add(shoppingList, BorderLayout.CENTER);
		shoppingListFrame.add(checkOut, BorderLayout.CENTER);
		shoppingListFrame.add(exit, BorderLayout.SOUTH);

		
		
		shoppingListFrame.setLayout(new GridLayout(4, 2));
		
		shoppingListFrame.setSize(720, 480);
		shoppingListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shoppingListFrame.setVisible(true);
		
	}
	
	
	
	public Items getItem(DefaultListModel<Items> storeShoppingList, Items item) {
		
		Items chosenItems = null;
		
		for (int i = 0; i < storeShoppingList.size(); i++) {
			if (item.equals(storeShoppingList.get(i))) {
				chosenItems = storeShoppingList.get(i);
			}
		}
		
		return chosenItems;
	}
	
}

