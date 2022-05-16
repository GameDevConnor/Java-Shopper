package ui;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import shoppers.*;

public class IO {
	
	BufferedReader reader;
	
	String row;
	
	FileWriter writer;

	
	File file = new File("Accounts.txt");
	
	File managerFile = new File("Managers.txt");
		
	public boolean readCSV(String email, String password) {
		
		boolean result = false;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("File does not exist");
		}
		
		try {
		
		if (reader != null) {
			
		
			while ((row = reader.readLine()) != null) {
				
				String[] data = row.split(",");
				
				if (data[0].equals(email)) {
					if (data[1].equals(password)) {
						result = true;
						break;
					}
				}
				
				
			}
			
			reader.close();
		}
		else {
			result = false;
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occurred");
		}
		
		
		return result;
	}
	
	
	
	public AccountManager createFromCSV() {
		
		AccountManager manager = new AccountManager();
		
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
				
				String[] data = row.split(",");
												
				Account account = new Account(data[0], data[1]);
				
				manager.accountInfo.put(account.getEmail(), account.getPassword());
				
			}
			
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occurred");
		}
			
		manager.buildAccounts();
		
		return manager;
		
	}
	
	
	
	
	public void writeCSV(String email, String password) {
		
		try {
			writer = new FileWriter(file, true);
			writer.append(email);
			writer.append(",");
			writer.append(password);
			writer.append("\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot write file");
		}
		
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	public Map<String, String> createManagerFromCSV() {
		
		Map<String, String> managersMap = new HashMap<>();
		
		if (file.exists()) {
			try {
				writer = new FileWriter(managerFile, true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else {
			try {
				writer = new FileWriter(managerFile, false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		try {
			reader = new BufferedReader(new FileReader(managerFile));
			
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("File does not exist");
		}
		
		try {
			while ((row = reader.readLine()) != null) {
				
				String[] data = row.split("=");
																
				managersMap.put(data[0], data[1]);
				
			}
			
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occurred");
		}
			
		
		return managersMap;
		
	}
	
	public void writeCSVManager(String account) {
		
		try {
			//writer = new FileWriter(file, true);
			writer = new FileWriter(managerFile, false);
			writer.append(account);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot write file");
		}
			
	}
	
}
