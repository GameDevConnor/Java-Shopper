package shoppers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ui.*;

public class StoreManager {

	public Map<String, String> stores;
	public ArrayList<Stores> storeList = new ArrayList<Stores>();
	public StoreIO io = new StoreIO();
	
	
	public StoreManager() {
		this.stores = new HashMap<String, String>();
	}
	
	
	public void addStore(Stores store) {
		storeList.add(store);
	}
	
	
	
	public void addItem(String key, Items item) {
		
		for (String storeKeys : stores.keySet()) {
			String[] storeElements = storeKeys.split("\\+");
			String storeName = storeElements[0];
			
			if (storeName.equals(key)) {
				
				
				boolean itemExists = false;
				for (Items checkItems : getStore(key).items) {
					if (checkItems.equals(item)) {
						itemExists = true;
						break;
					}
				}
				
				
				if (itemExists) {
					
					Items oldItems = null;
					for (Items checkItems : getStore(key).items) {
						if (checkItems.equals(item)) {
							oldItems = checkItems;
						}
					}
					
					
					this.updateAvailability(storeName, item.name, oldItems.availability + item.availability);
					
					
				}
				else {
					
				String value = stores.get(storeKeys).replace(", ", "");
				
				String newValue = "";
				
				
				if (value.isBlank()) {
					newValue += item.toString();

				}
				else {
					newValue += value + item.toString();
				}
								
				stores.put(storeKeys, newValue);
								
				break;
				
				
				}
			}
		}
	}
	
	
	
	public void updateItemName(String key, String itemName, String name) {
		
		for (String storeKeys : stores.keySet()) {
			String[] storeElements = storeKeys.split("\\+");
			String storeName = storeElements[0];
			
			
			if (storeName.equals(key)) {
				
				String[] items = stores.get(storeKeys).replace(", ", "").split("\\*");
				
				String newValue = "";

				
				for (int i = 0; i < items.length; i++) {
					String[] itemAttributes = items[i].split("_");
					
					
					if (itemAttributes[0].equals(itemName)) {
						String newAmount;
						newAmount = name + "_" + itemAttributes[1] + "_" + itemAttributes[2] + "_" + itemAttributes[3] + "_" + itemAttributes[4] + "_" + itemAttributes[5] + "*";
						newValue += newAmount;
					}
					else {
						String newAmount = itemAttributes[0] + "_" + itemAttributes[1] + "_" + itemAttributes[2] + "_" + itemAttributes[3] + "_" + itemAttributes[4] + "_" + itemAttributes[5] + "*";
						newValue += newAmount;
					}
					
					
					stores.put(storeKeys, newValue);

				}				
			}
		}
	}
	
	public void updateCategory(String key, String itemName, String category) {
		
		for (String storeKeys : stores.keySet()) {
			String[] storeElements = storeKeys.split("\\+");
			String storeName = storeElements[0];
			
			
			if (storeName.equals(key)) {
				
				String[] items = stores.get(storeKeys).replace(", ", "").split("\\*");
				
				String newValue = "";

				
				for (int i = 0; i < items.length; i++) {
					String[] itemAttributes = items[i].split("_");
					
					
					if (itemAttributes[0].equals(itemName)) {
						String newAmount;
						newAmount = itemAttributes[0] + "_" + category + "_" + itemAttributes[2] + "_" + itemAttributes[3] + "_" + itemAttributes[4] + "_" + itemAttributes[5] + "*";
						newValue += newAmount;
					}
					else {
						String newAmount = itemAttributes[0] + "_" + itemAttributes[1] + "_" + itemAttributes[2] + "_" + itemAttributes[3] + "_" + itemAttributes[4] + "_" + itemAttributes[5] + "*";
						newValue += newAmount;
					}
					
					
					stores.put(storeKeys, newValue);

				}				
			}
		}
	}
	
	public void updatePrice(String key, String itemName, float price) {
		
		for (String storeKeys : stores.keySet()) {
			String[] storeElements = storeKeys.split("\\+");
			String storeName = storeElements[0];
			
			
			if (storeName.equals(key)) {
				
				String[] items = stores.get(storeKeys).replace(", ", "").split("\\*");
				
				String newValue = "";

				
				for (int i = 0; i < items.length; i++) {
					String[] itemAttributes = items[i].split("_");
					
					
					if (itemAttributes[0].equals(itemName)) {
						String newAmount;
						newAmount = itemAttributes[0] + "_" + itemAttributes[1] + "_" + price + "_" + itemAttributes[3] + "_" + itemAttributes[4] + "_" + itemAttributes[5] + "*";
						newValue += newAmount;
					}
					else {
						String newAmount = itemAttributes[0] + "_" + itemAttributes[1] + "_" + itemAttributes[2] + "_" + itemAttributes[3] + "_" + itemAttributes[4] + "_" + itemAttributes[5] + "*";
						newValue += newAmount;
					}
					
					
					stores.put(storeKeys, newValue);

				}				
			}
		}
	}
	
	public void updateSize(String key, String itemName, int size) {
		
		for (String storeKeys : stores.keySet()) {
			String[] storeElements = storeKeys.split("\\+");
			String storeName = storeElements[0];
			
			
			if (storeName.equals(key)) {
				
				String[] items = stores.get(storeKeys).replace(", ", "").split("\\*");
				
				String newValue = "";

				
				for (int i = 0; i < items.length; i++) {
					String[] itemAttributes = items[i].split("_");
					
					
					if (itemAttributes[0].equals(itemName)) {
						String newAmount;
						newAmount = itemAttributes[0] + "_" + itemAttributes[1] + "_" + itemAttributes[2] + "_" + size + "_" + itemAttributes[4] + "_" + itemAttributes[5] + "*";
						newValue += newAmount;
					}
					else {
						String newAmount = itemAttributes[0] + "_" + itemAttributes[1] + "_" + itemAttributes[2] + "_" + itemAttributes[3] + "_" + itemAttributes[4] + "_" + itemAttributes[5] + "*";
						newValue += newAmount;
					}
					
					
					stores.put(storeKeys, newValue);

				}				
			}
		}
	}
	
	public void updateAvailability(String key, String itemName, int amount) {
		
		for (String storeKeys : stores.keySet()) {
			String[] storeElements = storeKeys.split("\\+");
			String storeName = storeElements[0];
			
			
			if (storeName.equals(key)) {
				
				String[] items = stores.get(storeKeys).replace(", ", "").split("\\*");
				
				String newValue = "";

				
				for (int i = 0; i < items.length; i++) {
					String[] itemAttributes = items[i].split("_");
					
					
					if (itemAttributes[0].equals(itemName)) {
						String newAmount;
						newAmount = itemAttributes[0] + "_" + itemAttributes[1] + "_" + itemAttributes[2] + "_" + itemAttributes[3] + "_" + amount + "_" + itemAttributes[5] + "*";
						newValue += newAmount;
					}
					else {
						String newAmount = itemAttributes[0] + "_" + itemAttributes[1] + "_" + itemAttributes[2] + "_" + itemAttributes[3] + "_" + itemAttributes[4] + "_" + itemAttributes[5] + "*";
						newValue += newAmount;
					}
					
					
					stores.put(storeKeys, newValue);

				}				
			}
		}
	}
	
	
	
	public void updateSale(String key, String itemName, boolean sale) {
		
		for (String storeKeys : stores.keySet()) {
			String[] storeElements = storeKeys.split("\\+");
			String storeName = storeElements[0];
			
			
			if (storeName.equals(key)) {
				
				String[] items = stores.get(storeKeys).replace(", ", "").split("\\*");
				
				String newValue = "";

				
				for (int i = 0; i < items.length; i++) {
					String[] itemAttributes = items[i].split("_");
					
					
					if (itemAttributes[0].equals(itemName)) {
						String newAmount;
						newAmount = itemAttributes[0] + "_" + itemAttributes[1] + "_" + itemAttributes[2] + "_" + itemAttributes[3] + "_" + itemAttributes[4] + "_" + sale + "*";
						newValue += newAmount;
					}
					else {
						String newAmount = itemAttributes[0] + "_" + itemAttributes[1] + "_" + itemAttributes[2] + "_" + itemAttributes[3] + "_" + itemAttributes[4] + "_" + itemAttributes[5] + "*";
						newValue += newAmount;
					}
					
					
					stores.put(storeKeys, newValue);

				}				
			}
		}
	}
	
	
	
	
	public void updateName(String key, String newName) {
		
		
		for (int j = 0; j < storeList.size(); j++) {
			if (storeList.get(j).getName().equals(key)) {
				Stores stores = storeList.get(j);
				storeList.remove(stores);
			}
		}
		
		for (String storeKeys : stores.keySet()) {
			String[] storeElements = storeKeys.split("\\+");
			String storeName = storeElements[0];
			
			if (storeName.equals(key)) {
				
				String newKey = "";
				
				newKey += newName + "+" + storeElements[1] + "+";
				
				stores.put(newKey, stores.get(storeKeys));
				
				stores.remove(storeKeys);
				
				break;
			}
		}

	}
	

	
	public void updateCoordinates(String key, int[] coordinates) {
		
		int newRow = coordinates[0];
		int newCol = coordinates[1];
		
		Stores currentStores = null;
		
		for (int j = 0; j < storeList.size(); j++) {
			if (storeList.get(j).getName().equals(key)) {
				Stores stores = storeList.get(j);
				storeList.remove(stores);
			}
		}
		
		for (String storeKeys : stores.keySet()) {
			String[] storeElements = storeKeys.split("\\+");
			String storeName = storeElements[0];
			String storeCoord = storeElements[1];
		
			if (storeName.equals(key)) {
				
				String newKey = "";
				
				newKey += storeName + "+" + newRow + "_" + newCol + "+";
				
				stores.put(newKey, stores.get(storeKeys));
				
				stores.remove(storeKeys);
				
				break;
			}
		}
	}
	
	
	
	public void buildStores() {
	
		
		for (String string : stores.keySet()) {
			
			String[] key = string.split("\\+");
			
			Stores store = new Store(key[0], new Coordinate());
			
			String[] coordinates = key[1].split("_");
			
			store.setCoordinate(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
			
			
			
			
			String value = stores.get(string).replace(", ", "");
			//This is adding a comma and space

			
			
			
			String[] valueWithoutStar = value.split("\\*");
//			String[] items = valueWithoutStar[0].split("_");
			
			if (!value.isBlank()) {
				
				for (int i = 0; i < valueWithoutStar.length; i++) {
					String[] items = valueWithoutStar[i].split("_");
					Items newItem = new Item(items[0], items[1], Float.parseFloat(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), Boolean.parseBoolean(items[5]));
					store.addItem(newItem);
				}
			}
			
			storeList.add(store);
		}
		
	}
	
	
	
	public String toString() {
		
		String accountString = "";
		
		for (String string : stores.keySet()) {
			accountString += string + ",";
			accountString += stores.get(string);
			accountString += "\n";
		}
		
		return accountString;
		
	}
	
	
	public void write() {
		io.writeCSVNew(this.toString());
		
	}
	
	
	
	public Stores getStore(String name) {
		
		for (Stores store : storeList) {
			if (name.equals(store.getName())) {
				return store;
			}
		}
				
		return null;
	}
	
	public boolean nameExists(String name) {
		
		boolean result = false;
		
		for (Stores stores : storeList) {
			if (name.equals(stores.getName())) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	
}
