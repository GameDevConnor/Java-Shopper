package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore.Entry;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import shoppers.Account;
import shoppers.AccountManager;
import shoppers.Coordinate;
import shoppers.Items;
import shoppers.StoreManager;
import shoppers.Stores;

public class Reccomendations {
	
	
	public static Account oldAccount;
	public static Account account;
	
	public IO io = new IO();
	
	public AccountManager manager = io.createFromCSV();
	
	public Coordinate coordinateSystem = new Coordinate();

	public StoreIO storeIO = new StoreIO();
	public StoreManager storeManager = storeIO.createFromCSV();
	
	public static Stores currentStores;

	
	public Reccomendations() {
		
		oldAccount = LoginScreen.account;
		account = manager.getAccount(oldAccount.getEmail());
		
		currentStores = storeManager.getStore(Shop.selectedStores.name);
		
		JLabel recommendLabel = new JLabel("Based on your shopping cart, we would like to recommend");
		
		DefaultListModel<Items> model = new DefaultListModel<>();
	
		for (Items shoppingList : currentStores.items) {
			
			if (!(account.shoppingCart.contains(shoppingList))) {
				model.addElement(shoppingList);
			}
		}
		
		HashSet<Items> toRemove = new HashSet<Items>();
		
		for (int i = 0; i < model.size(); i++) {
			boolean needsToBeRemoved = true;
			for (int j = 0; j < account.shoppingCart.size(); j++) {
				if ((model.get(i).getCategory().equals(account.shoppingCart.get(j).getCategory()) || model.get(i).onSale == true)) {
					needsToBeRemoved = false;
				}
			}
			
			if (needsToBeRemoved == true) {
				model.remove(i);
			}
		}
		
		for (Items items : toRemove) {
			if (model.contains(items)) {
				model.removeElement(items);
			}
		}
		
		JLabel itemInfo = new JLabel();
	//	JPanel panel = new JPanel();
		
		JList<Items> listOfItems = new JList<>(model);
		
		listOfItems.getSelectionModel().addListSelectionListener(e ->  {
			Items selectedItem = listOfItems.getSelectedValue();
			if (selectedItem.onSale == true) {
				itemInfo.setText(selectedItem.getName() + " \n" + "Price: " + "$" + selectedItem.getPrice() + " \nSize: " + selectedItem.getSize() + " \n" + selectedItem.getAvailability() + " currently in stock" + " THIS ITEM IS ON SALE");

			}
			else {
				itemInfo.setText(selectedItem.getName() + " \n" + "Price: " + "$" + selectedItem.getPrice() + " \nSize: " + selectedItem.getSize() + " \n" + selectedItem.getAvailability() + " currently in stock");
			}
		});
		

				
		JFrame recommendFrame = new JFrame("Check out and Reccomendations");
		
		
		
		ArrayList<String> categories = new ArrayList<String>();
		for (Items items : account.shoppingCart) {
			if (!categories.contains(items.getCategory())) {
				categories.add(items.getCategory());
			}
		}
		
		ArrayList<String> categoriesTotal = new ArrayList<String>();
		for (Items items : account.shoppingCart) {
			categoriesTotal.add(items.getCategory());
		}
		
		HashMap<String, Integer> frequency = new HashMap<>();
		
		for (String category : categories) {
			int frequencyOfEach = Collections.frequency(categoriesTotal, category);
			frequency.put(category, frequencyOfEach);
		}
		
		HashMap<String, Integer> sortedMap = new HashMap<>();
		
		frequency.entrySet()
		  .stream()
		  .sorted(Map.Entry.comparingByValue())
		  .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		
	
	    LinkedHashMap<String, Integer> descending = new LinkedHashMap<>();
	    sortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
	        .forEachOrdered(x -> descending.put(x.getKey(), x.getValue()));

		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				recommendFrame.dispose();
				ShoppingList shoppingList = new ShoppingList();
			}
		});
		
		JLabel orderHeader = new JLabel("We recommend you get the items in this order based on category: \n");
		
		String categoriesInOrder = "";
		for (String keyCategories : descending.keySet()) {
			
			categoriesInOrder += (keyCategories + ", ");
		}
		
		JLabel order = new JLabel(categoriesInOrder);
		
		
		recommendFrame.add(recommendLabel, BorderLayout.NORTH);
		recommendFrame.add(listOfItems, BorderLayout.NORTH);
		recommendFrame.add(orderHeader, BorderLayout.SOUTH);
		recommendFrame.add(order, BorderLayout.SOUTH);
		recommendFrame.add(exit, BorderLayout.SOUTH);

		
		recommendFrame.setLayout(new GridLayout(4, 2));
		
		recommendFrame.setSize(720, 480);
		recommendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		recommendFrame.setVisible(true);
	}
	

}
