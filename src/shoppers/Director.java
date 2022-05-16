package shoppers;

import ui.IO;
import ui.LoginScreen;

public class Director {
	
	public Builder builder;
	
	
	public IO io = new IO();
	
	public LoginScreen login;
	
	public AccountManager manager = io.createFromCSV();
	
	
	public boolean checkUser(String user, String password) {
//		return io.readCSV(user, password);
		boolean result = false;
		
		for (int i = 0; i < manager.accounts.size(); i++) {
			if (manager.accounts.get(i).getEmail().equals(user) && manager.accounts.get(i).getPassword().equals(password)) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	public void construct(Builder builder) {
		
		// Get data from text boxes
		builder.buildAccount(login.getEmail(), login.getPassowrd());
	}

}
