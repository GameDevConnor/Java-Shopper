package shoppers;

import java.util.ArrayList;

public class Account {

	
	public String email;
	public String password;
	public String security;
	
	public int radius;
	
	public final int defaultRadius = 15;
	
	public Coordinate coordinate = new Coordinate();
	
	public ArrayList<Items> shoppingCart = new ArrayList<>();
	
	
	public boolean isManager = false;
	public Stores store;
	
	
	public Account(String email, String password) {
		// TODO Auto-generated constructor stub
		this.email = email;
		this.password = password;
		this.radius = defaultRadius;
	}
	
	public Account(Account account) {
		// TODO Auto-generated constructor stub
		this.email = account.email;
		this.password = account.password;
		this.security = account.security;

		this.radius = defaultRadius;
	}
	


	
	public void addSecurity(String security) {
		// TODO Auto-generated method stub
		this.security = security;
	}
	
	public String getSecurity() {
		// TODO Auto-generated method stub
		return this.security;
	}


	
	public String toString() {
		
		String accounts = "";
		accounts += this.email + this.password/* + this.security*/;
		return accounts;
	}
	
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public void setCoordinate(int row, int col) {
		
		this.coordinate = new Coordinate(row, col);
	}



}
