package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import shoppers.Account;
import shoppers.AccountManager;
import shoppers.Builder;
import shoppers.Coordinate;
import shoppers.Store;
import shoppers.StoreBuilder;
import shoppers.StoreManager;
import shoppers.Stores;

public class StoresPage {

	public IO io = new IO();
	public AccountManager manager = io.createFromCSV();
	public static Account oldAccount;
	public static Account account;

	public Coordinate coordinateSystem = new Coordinate();
	
	public StoreIO storeIO = new StoreIO();
	public StoreManager storeManager = storeIO.createFromCSV();
	
	public StoresPage() {
		
		oldAccount = LoginScreen.account;
		account = manager.getAccount(oldAccount.getEmail());

		
		JFrame store = new JFrame("Change Store Information");
		
		JLabel newStore = new JLabel("Enter a name, a hyphen, and enter two INTEGERS in the format i,j to enter a new store");
		JTextField enterStore = new JTextField();
		JLabel changeName = new JLabel("Enter the old store name and new store name separated by a hyphen with no spaces");
		JTextField oldNewName = new JTextField();
		JLabel coordinate = new JLabel("Enter the store name, a hypen, and then Enter two INTEGERS in the format i,j to change your location");
		JTextField enterCoordinates = new JTextField();
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				store.dispose();
				account = manager.getAccount(account.getEmail(), account.getPassword());
				AccountPage accountPage = new AccountPage();
			}
		});
		
		JButton change = new JButton("Change Name");
		change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				Builder builder = new StoreBuilder();
				
				String[] textBox = oldNewName.getText().split("-");
				
				if (builder.storeManager.nameExists(textBox[1])) {
					JOptionPane.showMessageDialog(store, "There's already a store with that name, that's a lawsuit waiting to happen");
				}
				else {
				
				storeManager.updateName(textBox[0], textBox[1]);
				storeManager.write();
				storeManager.buildStores();
				
				JOptionPane.showMessageDialog(store, "You've changed the store name from " + textBox[0] + " to " + textBox[1] + ". I like it, really rolls off the tounge");
				store.dispose();
				AccountPage accountPage = new AccountPage();
				}

			}
		});
		
		JButton createStore = new JButton("Create Store");
		createStore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Pattern integerPattern = Pattern.compile("[a-zA-Z0-9 ]+-[0-9]+,[0-9]+");

				Matcher integerMatcher = integerPattern.matcher(enterStore.getText());
				boolean matched = integerMatcher.matches();
				
				if (matched) {
					String[] newStore = enterStore.getText().split("-");
					String[] coordateString = newStore[1].split(",");
					Coordinate coordinate = new Coordinate(Integer.parseInt(coordateString[0]), Integer.parseInt(coordateString[1]));

					Builder builder = new StoreBuilder();
				
					if (builder.storeManager.nameExists(newStore[0])) {
						JOptionPane.showMessageDialog(store, "There's already a store with that name, that's a lawsuit waiting to happen");
					}
					else {
						
						
						boolean contains = false;
						for (Stores store : storeManager.storeList) {
							if (coordinateSystem.equals(coordinate, store.coordinate)) {
								contains = true;
								break;
							}
						}
						
						for (Account account : manager.accounts) {
							if (coordinateSystem.equals(coordinate, account.coordinate)) {
								contains = true;
								break;
							}
						}
						
						if (contains) {
							JOptionPane.showMessageDialog(store, "Something already exists at this location, so you can't place yourself here... unless you're a ghost");
						}
						else {			
						builder.buildStore(newStore[0], newStore[1]);
						builder.storeManager.addStore(new Store(newStore[0], coordinate));
						builder.storeManager.write();
						builder.storeManager.buildStores();
						JOptionPane.showMessageDialog(store, "Have fun shopping at " + newStore[0]);
						store.dispose();
						AccountPage accountPage = new AccountPage();
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(store, "That's not the correct format. Remember, the format is 'Name-Row,Column'");
				}
			
			}
		});
		
		JButton changeCoordinate = new JButton("Change your coordiantes");
		changeCoordinate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Pattern integerPattern = Pattern.compile("[a-zA-Z0-9 ]+-[0-9]+,[0-9]+");

				Matcher integerMatcher = integerPattern.matcher(enterCoordinates.getText());
				boolean matched = integerMatcher.matches();
				
				if (matched) {
					String[] nameCoordinate = enterCoordinates.getText().split("-");
					String[] coordateString = nameCoordinate[1].split(",");
					Coordinate coordinate = new Coordinate(Integer.parseInt(coordateString[0]), Integer.parseInt(coordateString[1]));
					
					
					boolean contains = false;
					for (Stores store : storeManager.storeList) {
						if (coordinateSystem.equals(coordinate, store.coordinate)) {
							contains = true;
							break;
						}
					}
					
					for (Account account : manager.accounts) {
						if (coordinateSystem.equals(coordinate, account.coordinate)) {
							contains = true;
							break;
						}
					}
					
					if (contains) {
						JOptionPane.showMessageDialog(store, "Something already exists at this location, so you can't place yourself here... unless you're a ghost");
					}
					else {
						int[] newCoordinates = {Integer.parseInt(coordateString[0]), Integer.parseInt(coordateString[1])};
						
						String[] textBoxStrings = enterCoordinates.getText().split("-");
						String storeNameString = textBoxStrings[0];
						
						storeManager.updateCoordinates(storeNameString, newCoordinates);
						storeManager.write();
						storeManager.buildStores();
						
						JOptionPane.showMessageDialog(store, "You've changed the location of the store, I hope your customers know where to go");
						store.dispose();
						AccountPage accountPage = new AccountPage();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(store, "That's not the correct format. Remember, the format is 'Name-Row,Column'");
				}

			}
		});
		
		
		
		
		JButton addItem = new JButton("Add Item to a Store");
		addItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				store.dispose();
				AddItem addItem = new AddItem();
			}
		});
		
		

		store.add(newStore, BorderLayout.WEST);
		store.add(enterStore, BorderLayout.WEST);
		store.add(createStore, BorderLayout.WEST);
		
		store.add(changeName, BorderLayout.EAST);
		store.add(oldNewName, BorderLayout.EAST);
		store.add(change, BorderLayout.EAST);
		
		store.add(coordinate, BorderLayout.EAST);
		store.add(enterCoordinates, BorderLayout.EAST);
		store.add(changeCoordinate, BorderLayout.EAST);
		
		
		
		store.add(addItem, BorderLayout.SOUTH);
		

		store.add(exit, BorderLayout.SOUTH);
		
		store.setLayout(new GridLayout(5, 3));
		
		store.setSize(720, 480);
		store.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		store.setVisible(true);
	}
}
