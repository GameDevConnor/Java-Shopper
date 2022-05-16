package shoppers;

import javax.swing.JOptionPane;

import ui.*;

public abstract class Builder {
	
	public IO io = new IO();
	
	public AccountManager manager = io.createFromCSV();
	
	
	public StoreIO storeIO = new StoreIO();
	
	public StoreManager storeManager = storeIO.createFromCSV();
	
	
	
	public Account buildAccount(String email, String password) {
		Account account = new Account(email, password);		
		manager.accountInfo.put(account.getEmail(), account.getPassword() + "+" + account.coordinate.row + "_" + account.coordinate.col + "+" + "&" + account.radius + "&");
		return account;
	}
	
	public Stores buildStore(String name, String coordinates) {
		String[] coordString = coordinates.split(",");
		Stores stores = new Store(name, new Coordinate(Integer.parseInt(coordString[0]), Integer.parseInt(coordString[1])));
		storeManager.stores.put(stores.getName() + "+" + stores.coordinate.getRow() + "_" + stores.coordinate.getCol() + "+", " ");
		return stores;
	}
	
	public void writeAccount(String email, String password) {
		io.writeCSV(email, password);
	}
	

}
