package shoppers;

import java.util.*;
import ui.*;

public class AccountManager {
	
	public Map<String, String> accountInfo;
	public List<Account> accounts = new ArrayList<Account>();
	
	IO io = new IO();
	
	StoreIO sIO = new StoreIO();
	StoreManager storeManager = sIO.createFromCSV();
	
	
	
	public Map<String, String> managerStore = new HashMap<>();

	
	
	public AccountManager() {
		this.accountInfo = new HashMap<String, String>();
	}
	
	public void updatePassword(String key, String newPassword) {
		
		
		for (int j = 0; j < accounts.size(); j++) {
			if (accounts.get(j).getEmail().equals(key)) {
				Account account = accounts.get(j);
				accounts.remove(account);
			}
		}
		
		String value = accountInfo.get(key);
		String values[] = value.split("\\+");
		values[0] = newPassword;
		String newValue = "";
		
		for (int i = 0; i < values.length; i++) {
			
			if (i == values.length - 1) {
				newValue += values[i];
			}
			else {
				newValue += values[i];
				newValue += "+";
			}
			
		}
		
		accountInfo.put(key, newValue);
	}
	
	
	
	
	public void updateCoordinates(String key, int[] coordinates) {
		
		int newRow = coordinates[0];
		int newCol = coordinates[1];
		
		for (int j = 0; j < accounts.size(); j++) {
			if (accounts.get(j).getEmail().equals(key)) {
				Account account = accounts.get(j);
				accounts.remove(account);
			}
		}
		
		String value = accountInfo.get(key);
		String values[] = value.split("\\+");
		String[] coords = values[1].split("_");
		
		coords[0] = Integer.toString(newRow);
		coords[1] = Integer.toString(newCol);

		String newValue = "";
		
		for (int i = 0; i < values.length; i++) {
			
			if (i == 0) {
				newValue += values[i] + "+";
			}
			else if (i == 1) {
				newValue += coords[0] + "_" + coords[1];
				newValue += "+";
			}
			else {
				newValue += values[i];
			}
			
		}
		
		accountInfo.put(key, newValue);
	}
	
	
	
	public void updateRadius(String key, int radius) {
		

		for (int j = 0; j < accounts.size(); j++) {
			if (accounts.get(j).getEmail().equals(key)) {
				Account account = accounts.get(j);
				accounts.remove(account);
			}
		}
		
		String value = accountInfo.get(key);
		String values[] = value.split("&");

		String newValue = "";
		
		for (int i = 0; i < values.length; i++) {
			
			if (i == 0) {
				newValue += values[i] + "&";
			}
			else if (i == 1) {
				newValue += radius;
				newValue += "&";
			}
			else if (i == 2) {
				newValue += values[i];
				newValue += "&";
			}
			
			else {
				newValue += values[i];
			}
			
		}
		
		accountInfo.put(key, newValue);
	}
	
	
	
	
	
	public void addItem(String key, Items item, Stores store) {
		
		for (int j = 0; j < accounts.size(); j++) {
			if (accounts.get(j).getEmail().equals(key)) {
				Account account = accounts.get(j);
				accounts.remove(account);
			}
		}
		
		
		String value = accountInfo.get(key);
		String values[] = value.split("&");

		String newValue = "";
		
		if (values.length > 2) {
			
			for (int i = 0; i < values.length; i++) {
				
				if (i == 0) {
					newValue += values[i] + "&";
				}
				else if (i == 1) {
					newValue += values[i];
					newValue += "&";
				}
				else if (i == 2) {
					newValue += values[i];
					newValue += "&";
				}
				else {
					newValue += values[i];
				}
				
			}
//			newValue += store.getName() + ":" + item.toString().replace(", ", "");
			Items newItem = new Item(item.getName(), item.getCategory(), item.getPrice(), item.getSize(), 1, item.onSale);
			newValue += store.getName() + ":" + newItem.toString().replace(", ", "");

		}
		else {
			
			newValue += values[0] + "&";
			newValue += values[1];
			newValue += "&";
			newValue += store.getName() + ":" + item.toString().replace(", ", "");
			
		}
		
		accountInfo.put(key, newValue);
	}
	
	
	
	
	public void removeItem(String key, Items item, Stores store) {
		
		for (int j = 0; j < accounts.size(); j++) {
			if (accounts.get(j).getEmail().equals(key)) {
				Account account = accounts.get(j);
				accounts.remove(account);
			}
		}
		
		
		String value = accountInfo.get(key);
		String values[] = value.split("&");

		String newValue = "";
		
		if (values.length > 3) {
			
			for (int i = 0; i < values.length; i++) {
				
				if (i == 0) {
					newValue += values[i] + "&";
				}
				else if (i == 1) {
					newValue += values[i];
					newValue += "&";
				}
				else if (i == 2) {
					newValue += values[i];
					newValue += "&";
				}
				else {
					
					boolean alreadyCompleted = false;
					
					String newItemString = "";
					
					String[] currentItems = values[3].split("\\*");
					
					for (int j = 0; j < currentItems.length; j++) {
						
						String[] storeAndItemName = currentItems[j].split(":");
						String[] storeAttributes = storeAndItemName[1].split("_");
						
						if (!(store.getName().equals(storeAndItemName[0]) && item.getName().equals(storeAttributes[0]))) {
							newItemString += currentItems[j] + "*";
						}
						else if (store.getName().equals(storeAndItemName[0]) && item.getName().equals(storeAttributes[0]) && alreadyCompleted == true) {
							newItemString += currentItems[j] + "*";
						}
						else {
							alreadyCompleted = true;
						}
					}
					
					newValue += newItemString;
				}
				
			}

		}
		else {
			
			newValue += values[0] + "&";
			newValue += values[1];
			newValue += "&";
			newValue += store.getName() + ":" + item.toString().replace(", ", "");
			
		}
		
		accountInfo.put(key, newValue);
	}
	
	
	
	
	
	
	
	
	
	public void updateSecurity(String key, String security) {
							
			String value = accountInfo.get(key);
					
			String[] radiusString = value.split("&");												
				
			String newValue = "";
			
			if (radiusString.length > 2) {
				
				for (int i = 0; i < radiusString.length; i++) {
					
					if (i == 0 || i == 1) {
						newValue += radiusString[i] + "&";
					}
					
					else if (i == 2) {
						newValue += security + "&";
					}
					
					else {
						newValue += radiusString[i];
					}
				}
				
			}
			else {
				
				for (int i = 0; i < radiusString.length; i++) {
					
					if (i == 0 || i == 1) {
						newValue += radiusString[i] + "&";
					}
					
				}
				newValue += security + "&";
				
			}
					
		accountInfo.put(key, newValue);

	}
	
	
	public void buildAccounts() {
	
		
		for (String string : accountInfo.keySet()) {
			
			String[] value = accountInfo.get(string).split("\\+");
			
			String[] radiusString = accountInfo.get(string).split("&");
						
			Account account = new Account(string, value[0]);			
			
			String[] coordinates = value[1].split("_");
			
			account.setCoordinate(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
			
			account.radius = Integer.parseInt(radiusString[1]);
			
			
			
			if (radiusString.length > 2) {
				account.addSecurity(radiusString[2]);
			}
			
			if (radiusString.length > 3) {
				String[] items = radiusString[3].replace(", ", "").split("\\*");
				
				for (int i = 0; i < items.length; i++) {
					
					String[] storeAndAttributes = items[i].replace(", ", "").split(":");
//					String[] itemAttributes = items[i].replace(", ", "").split("_");
					String[] itemAttributes = storeAndAttributes[1].replace(", ", "").split("_");
					
					Items addItems = new Item(itemAttributes[0], itemAttributes[1], Float.parseFloat(itemAttributes[2]), Integer.parseInt(itemAttributes[3]), Integer.parseInt(itemAttributes[4]), Boolean.parseBoolean(itemAttributes[5]));
					addItems.stores = storeManager.getStore(storeAndAttributes[0]);
					account.shoppingCart.add(addItems);
				}
			}
			
			
			managerStore = io.createManagerFromCSV();
			if (isManager(account.getEmail()) != null) {
				account.isManager = true;
				account.store = isManager(account.getEmail());
			}
			
			accounts.add(account);
		}
		
	}
	
	public String toString() {
		
		String accountString = "";
		
		for (String string : accountInfo.keySet()) {
			accountString += string + ",";
			accountString += accountInfo.get(string);
			accountString += "\n";
		}
		
		return accountString;
		
	}
	
	public void write() {
		io.writeCSVNew(this.toString());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void writeManager() {
		io.writeCSVManager(managerStore.toString().replace("{", "").replace("}", "").replace(", ", "\n"));
	}
	
	public Stores isManager(String accountName) {
		Stores result = null;
		for (String account : managerStore.keySet()) {
			if (accountName.equals(account)) {
				result = storeManager.getStore(managerStore.get(accountName));
				break;
			}
		}
		
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	

	
		
	
	public Account getAccount(String email, String password) {
		Account checkAccount = new Account(email, password);
		
		for (Account account : accounts) {
			if (checkAccount.getEmail().equals(account.getEmail()) && checkAccount.getPassword().equals(account.getPassword())) {
				return account;
			}
		}
		
		return null;
	}
	
	public Account getAccount(String email) {
		
		for (Account account : accounts) {
			if (email.equals(account.getEmail())) {
				return account;
			}
		}
				
		return null;
	}
	
	
	public void deleteAccount(Account account) {
		account = getAccount(account.getEmail(), account.getPassword());
		accountInfo.remove(account.getEmail());
		
		for (int i = 0; i < accounts.size(); i++) {
			
			if (account.getEmail().equals(accounts.get(i).getEmail())) {
				accounts.remove(i);
			}
				
		}
		
		write();
		
	}
	
	public boolean nameExists(String name) {
		
		boolean result = false;
		
		for (Account account : accounts) {
			if (name.equals(account.getEmail())) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	

}
