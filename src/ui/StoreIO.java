package ui;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shoppers.*;

public class StoreIO {
	
	BufferedReader reader;
	
	String row;
	
	FileWriter writer;

	
	File file = new File("Stores.txt");

	
	public StoreManager createFromCSV() {
		
		StoreManager manager = new StoreManager();
		
		if (file.exists()) {
			try {
				writer = new FileWriter(file, true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else {
			try {
				writer = new FileWriter(file, false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("File does not exist");
		}
		
		try {
			while ((row = reader.readLine()) != null) {
				
				String[] data = row.split("\\+");
				
				String[] listOfItems = row.split(",");
				
				String[] items = listOfItems[1].split("\\*");
				
				
				String[] coordinatesStrings = data[1].split("_");
												
				Stores store = new Store(data[0], new Coordinate(Integer.parseInt(coordinatesStrings[0]), Integer.parseInt(coordinatesStrings[1])));
				
				
				if (listOfItems[1].length() > 2) {
					
					for (int i = 0; i < items.length; i++) {
						String[] itemAttributes = items[i].split("_");
						Items newItem = new Item(itemAttributes[0], itemAttributes[1], Float.parseFloat(itemAttributes[2]), Integer.parseInt(itemAttributes[3]), Integer.parseInt(itemAttributes[4]), Boolean.parseBoolean(itemAttributes[5]));
						store.addItem(newItem);
					}
					
					manager.stores.put(store.getName() + "+" + store.coordinate.getRow() + "_" + store.coordinate.getCol() + "+", store.items.toString().replace("[", "").replace("]", ""));
				}
				else {
					manager.stores.put(store.getName() + "+" + store.coordinate.getRow() + "_" + store.coordinate.getCol() + "+", " ");

				}
				
//				manager.stores.put(store.getName() + "+" + store.coordinate.getRow() + "_" + store.coordinate.getCol() + "+", store.items.toString().replace("[", "").replace("]", ""));
				
			}
			
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occurred");
		}
			
		manager.buildStores();
		
		return manager;
		
	}
		
	
	public void writeCSVNew(String account) {
		
		try {
			//writer = new FileWriter(file, true);
			writer = new FileWriter(file, false);
			writer.append(account);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot write file");
		}
			
	}
	
}
