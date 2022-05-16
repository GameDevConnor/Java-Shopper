package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import shoppers.*;

public class AddItem {

	public IO io = new IO();
	public AccountManager manager = io.createFromCSV();
	public static Account oldAccount;
	public static Account account;

	public Coordinate coordinateSystem = new Coordinate();
	
	public StoreIO storeIO = new StoreIO();
	public StoreManager storeManager = storeIO.createFromCSV();
	
	
	public AddItem () {
		
		oldAccount = LoginScreen.account;
		account = manager.getAccount(oldAccount.getEmail());
		
		JFrame itemFrame = new JFrame("Add Item");
		
		
		JLabel enterStoreName = new JLabel("Enter The Store Name");
		JTextField storeName = new JTextField();
		
		
		JLabel enterNewItem = new JLabel("Enter New Item");
		JButton addItem = new JButton("Add Item");
		addItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				
				if (storeManager.getStore(storeName.getText()) != null && ((account.getEmail().equals("Admin") && account.getPassword().equals("Admin")) || account.store.getName().equals(storeName.getText()))) {
					
					
					String itemName = JOptionPane.showInputDialog(itemFrame, "Enter the item name");
					String itemCat = JOptionPane.showInputDialog(itemFrame, "Enter the item category");
					String itemPrice = JOptionPane.showInputDialog(itemFrame, "Enter the item price");
					String itemSize = JOptionPane.showInputDialog(itemFrame, "Enter the item size");
					String itemAvailability = JOptionPane.showInputDialog(itemFrame, "Enter the item availability");
					String itemSale = JOptionPane.showInputDialog(itemFrame, "Is this item on sale (Type in true or false, case insensitive)");
					
					storeManager.addItem(storeName.getText(), new Item(itemName, itemCat, Float.parseFloat(itemPrice), Integer.parseInt(itemSize), Integer.parseInt(itemAvailability), Boolean.parseBoolean(itemSale)));
					storeManager.write();
					storeManager.buildStores();
				
					JOptionPane.showMessageDialog(itemFrame, "You just added " + itemName + " to " + storeName.getText());
				
					itemFrame.dispose();
					AccountPage accountPage = new AccountPage();
					
					
				}

				else {
					JOptionPane.showMessageDialog(itemFrame, "That store doesn't exist OR you lack the privileges to access it");
				}
				


			}
		});
		

		JButton changeItemName = new JButton("Change Item Name");
		changeItemName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				if (storeManager.getStore(storeName.getText()) != null) {
					
					String oldName = JOptionPane.showInputDialog(itemFrame, "Enter the item's name");
					String newName = JOptionPane.showInputDialog(itemFrame, "Enter the new item");
					
					storeManager.updateItemName(storeName.getText(), oldName, newName);
					storeManager.write();
					storeManager.buildStores();
				
					JOptionPane.showMessageDialog(itemFrame, "You changed " + oldName + " to " + newName);
				
					itemFrame.dispose();
					AccountPage accountPage = new AccountPage();
				}
				
				else {
					JOptionPane.showMessageDialog(itemFrame, "That store doesn't exist");
				}
				
				

			}
		});
		


		JButton changeItemCat = new JButton("Change Item Category");
		changeItemCat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (storeManager.getStore(storeName.getText()) != null) {
					
					String nameCat = JOptionPane.showInputDialog(itemFrame, "Enter the item's name");
					String newCat = JOptionPane.showInputDialog(itemFrame, "Enter the item's new category");
					
					storeManager.updateCategory(storeName.getText(), nameCat, newCat);
					storeManager.write();
					storeManager.buildStores();
				
					JOptionPane.showMessageDialog(itemFrame, "You changed " + nameCat + "'s category to " + newCat);
				
					itemFrame.dispose();
					AccountPage accountPage = new AccountPage();
				}
				
				else {
					JOptionPane.showMessageDialog(itemFrame, "That store doesn't exist");
				}
				
				
			}
		});
		

		JButton changeItemPrice = new JButton("Change Item Price");
		changeItemPrice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				if (storeManager.getStore(storeName.getText()) != null) {
					
					String namePrice = JOptionPane.showInputDialog(itemFrame, "Enter the item's name");
					String newPrice = JOptionPane.showInputDialog(itemFrame, "Enter the new item price");
					
					storeManager.updatePrice(storeName.getText(), namePrice, Float.parseFloat(newPrice));
					storeManager.write();
					storeManager.buildStores();
				
					JOptionPane.showMessageDialog(itemFrame, "You changed " + namePrice + "'s price to " + newPrice);
				
					itemFrame.dispose();
					AccountPage accountPage = new AccountPage();
				}
				
				else {
					JOptionPane.showMessageDialog(itemFrame, "That store doesn't exist");
				}
				
				

			}
		});
		


		JButton changeItemSize = new JButton("Change Item Size");
		changeItemSize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (storeManager.getStore(storeName.getText()) != null) {
					
					String enterNameSize = JOptionPane.showInputDialog(itemFrame, "Enter the item's name");
					String newSize = JOptionPane.showInputDialog(itemFrame, "Enter the new item");
					
					storeManager.updateSize(storeName.getText(), enterNameSize, Integer.parseInt(newSize));
					storeManager.write();
					storeManager.buildStores();
				
					JOptionPane.showMessageDialog(itemFrame, "You changed " + enterNameSize + "'s size to " + newSize);
				
					itemFrame.dispose();
					AccountPage accountPage = new AccountPage();
				}
				
				else {
					JOptionPane.showMessageDialog(itemFrame, "That store doesn't exist");
				}
				

			}
		});
		

		JButton changeItemAvailability = new JButton("Change Item Availability");
		changeItemAvailability.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (storeManager.getStore(storeName.getText()) != null) {
					
					String enterNameAvailability = JOptionPane.showInputDialog(itemFrame, "Enter the item's name");
					String newAvailability = JOptionPane.showInputDialog(itemFrame, "Enter the new item");
					
					storeManager.updateAvailability(storeName.getText(), enterNameAvailability, Integer.parseInt(newAvailability));
					storeManager.write();
					storeManager.buildStores();
				
					JOptionPane.showMessageDialog(itemFrame, "You changed " + enterNameAvailability + "'s availability to " + newAvailability);
				
					itemFrame.dispose();
					AccountPage accountPage = new AccountPage();
				}
				
				else {
					JOptionPane.showMessageDialog(itemFrame, "That store doesn't exist");
				}
				

			}
		});
		
		
		
		
		JButton changeSale = new JButton("Change Sale");
		changeSale.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (storeManager.getStore(storeName.getText()) != null) {
					
					String enterNameAvailability = JOptionPane.showInputDialog(itemFrame, "Enter the item's name");
					String newSale = JOptionPane.showInputDialog(itemFrame, "Enter true or false (Case Insensitive)");
					
					storeManager.updateSale(storeName.getText(), enterNameAvailability, Boolean.parseBoolean(newSale));
					storeManager.write();
					storeManager.buildStores();
				
					JOptionPane.showMessageDialog(itemFrame, "You changed " + enterNameAvailability + "'s sale value to " + newSale);
				
					itemFrame.dispose();
					AccountPage accountPage = new AccountPage();
				}
				
				else {
					JOptionPane.showMessageDialog(itemFrame, "That store doesn't exist");
				}
				

			}
		});
		
		
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				itemFrame.dispose();
				account = manager.getAccount(account.getEmail(), account.getPassword());
				AccountPage accountPage = new AccountPage();
			}
		});
		
		
		
		
		itemFrame.add(enterStoreName, BorderLayout.WEST);
		itemFrame.add(storeName, BorderLayout.WEST);
		
		itemFrame.add(enterNewItem, BorderLayout.WEST);
		itemFrame.add(addItem, BorderLayout.WEST);
		
		itemFrame.add(changeItemName, BorderLayout.WEST);
		
		itemFrame.add(changeItemCat, BorderLayout.EAST);
		
		itemFrame.add(changeItemPrice, BorderLayout.EAST);
		
		itemFrame.add(changeItemPrice, BorderLayout.EAST);

		itemFrame.add(changeItemAvailability, BorderLayout.EAST);
		
		itemFrame.add(changeSale, BorderLayout.EAST);


		itemFrame.add(exit, BorderLayout.SOUTH);
		
		itemFrame.setLayout(new GridLayout(7, 5));
		
		itemFrame.setSize(720, 480);
		itemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		itemFrame.setVisible(true);
	}
}
