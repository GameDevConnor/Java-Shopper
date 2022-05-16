package testing;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import shoppers.*;


public class Testing {
	
	StoreManager storeManager = new StoreManager();
	AccountManager accountManager = new AccountManager();
	
	HashMap<String, String> storeMap = new HashMap<>();
	HashMap<String, String> accountMap = new HashMap<>();
	
	HashMap<String, String> managers = new HashMap<>();

	
	@Test
	public void buildAccountTest() {
		Builder builder = new AccountBuilder();
		accountMap.clear();

		String email = "Malcolm";
		String password = "Williams";
		
		Account built = builder.buildAccount(email, password);
		Account custom = new Account(email, password);
		
		assertTrue(built.getEmail().equals(custom.getEmail()) && built.getPassword().equals(custom.getPassword()) && built.radius == custom.radius);
	}
	
	@Test
	public void buildStoreTest() {
		Builder builder = new StoreBuilder();
		accountMap.clear();

		String name = "Metropolis";
		String coordinates = "5,7";
		Coordinate coordinate = new Coordinate(5, 7);
		
		Stores built = builder.buildStore(name, coordinates);
		Stores custom = new Store(name, coordinate);
		
		assertTrue(built.getName().equals(custom.getName()) && built.coordinate.getRow() == custom.coordinate.getRow() && built.coordinate.getCol() == custom.coordinate.getCol());
	}
	
	@Test
	public void updatePasswordTest() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&");
		
		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		accountManager.updatePassword("Malcolm", "Pusher");
		
		assertTrue(accountManager.accountInfo.get("Malcolm").equals("Pusher+4_5+&20&What is your favourite colour?/green&"));
	}
	
	@Test
	public void updateCoordTest() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&");
		
		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		int[] newCoords = {2,9};
		
		accountManager.updateCoordinates("Malcolm", newCoords);
		
		assertTrue(accountManager.accountInfo.get("Malcolm").equals("Williams+2_9+&20&What is your favourite colour?/green&"));
	}
	
	
	@Test
	public void updateRadiusTest() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&Walmart:Blue_Colour_3.0_4_1_true*");
		
		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
				
		accountManager.updateRadius("Malcolm", 37);
		
		assertEquals(accountManager.accountInfo.get("Malcolm"), "Williams+4_5+&37&What is your favourite colour?/green&Walmart:Blue_Colour_3.0_4_1_true*");
	}
	
	@Test
	public void addItemTestWithSecurityMethods() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&");
		
		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		Items item = new Item("Blue", "Colour", (float) 3.0, 4, 1, true);
		Coordinate coordinate = new Coordinate(5, 7);
		Stores store = new Store("Walmart", coordinate);
		
		accountManager.addItem("Malcolm", item, store);
		
		assertEquals(accountManager.accountInfo.get("Malcolm"), "Williams+4_5+&20&What is your favourite colour?/green&Walmart:Blue_Colour_3.0_4_1_true*");
	}
	
	@Test
	public void addItemTestWithoutSecurityMethods() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&");
		
		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		Items item = new Item("Blue", "Colour", (float) 3.0, 4, 1, true);
		Coordinate coordinate = new Coordinate(5, 7);
		Stores store = new Store("Walmart", coordinate);
		
		accountManager.addItem("Malcolm", item, store);
		
		assertEquals(accountManager.accountInfo.get("Malcolm"), "Williams+4_5+&20&Walmart:Blue_Colour_3.0_4_1_true*");
	}
	
	
	
	
	
	
	
	
	
	@Test
	public void removeItemTestWithoutSecurityMethods() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&");
		
		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		Items item = new Item("Blue", "Colour", (float) 3.0, 4, 1, true);
		Coordinate coordinate = new Coordinate(5, 7);
		Stores store = new Store("Walmart", coordinate);
		
		accountManager.addItem("Malcolm", item, store);
		accountManager.removeItem("Malcolm", item, store);
		
		assertEquals(accountManager.accountInfo.get("Malcolm"), "Williams+4_5+&20&Walmart:Blue_Colour_3.0_4_1_true*");
	}
	
	
	
	
	
	
	
	
	
	
	@Test
	public void removeItemTestWithSecurityMethods() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&");
		
		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		Items item = new Item("Blue", "Colour", (float) 3.0, 4, 1, true);
		Coordinate coordinate = new Coordinate(5, 7);
		Stores store = new Store("Walmart", coordinate);
		
		accountManager.addItem("Malcolm", item, store);
		accountManager.removeItem("Malcolm", item, store);
		
		assertEquals(accountManager.accountInfo.get("Malcolm"), "Williams+4_5+&20&What is your favourite colour?/green&");
	}
	
	@Test
	public void removeDuplicateItem() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&Walmart:Blue_Colour_3.0_4_1_true*Walmart:Blue_Colour_3.0_4_1_true*");
		
		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		Items item = new Item("Blue", "Colour", (float) 3.0, 4, 1, true);
		Coordinate coordinate = new Coordinate(5, 7);
		Stores store = new Store("Walmart", coordinate);
		
		accountManager.removeItem("Malcolm", item, store);
		
		assertEquals(accountManager.accountInfo.get("Malcolm"), "Williams+4_5+&20&What is your favourite colour?/green&Walmart:Blue_Colour_3.0_4_1_true*");
	}
	
	@Test
	public void removeTwoSeparateItems() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&Walmart:Blue_Colour_3.0_4_1_true*Walmart:Red_Colour_3.0_4_1_true*");
		
		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		Items blueItem = new Item("Blue", "Colour", (float) 3.0, 4, 1, true);
		Items redItem = new Item("Red", "Colour", (float) 3.0, 4, 1, true);
		Coordinate coordinate = new Coordinate(5, 7);
		Stores store = new Store("Walmart", coordinate);
		
		accountManager.removeItem("Malcolm", redItem, store);
		
		assertEquals(accountManager.accountInfo.get("Malcolm"), "Williams+4_5+&20&What is your favourite colour?/green&Walmart:Blue_Colour_3.0_4_1_true*");
	}
	
	
	@Test
	public void updateSecurityTestWithItems() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&Walmart:Blue_Colour_3.0_4_1_true*");
		
		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		accountManager.updateSecurity("Malcolm", "Who wrote your favourite book?/yann martel");
		
		assertEquals(accountManager.accountInfo.get("Malcolm"), "Williams+4_5+&20&Who wrote your favourite book?/yann martel&Walmart:Blue_Colour_3.0_4_1_true*");
	}
	
	
	
	@Test
	public void updateSecurityTestWithoutItems() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&");
		
		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		accountManager.updateSecurity("Malcolm", "Who wrote your favourite book?/yann martel");
		
		assertEquals(accountManager.accountInfo.get("Malcolm"), "Williams+4_5+&20&Who wrote your favourite book?/yann martel&");
	}
	
	
	@Test
	public void toStringTest() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&");
		accountMap.put("Jackie", "Moon+8_1+&29&What is your favourite colour?/red&");
		
		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		
		assertEquals(accountManager.toString(), "Malcolm,Williams+4_5+&20&What is your favourite colour?/green&\nJackie,Moon+8_1+&29&What is your favourite colour?/red&\n");
	}
	
	
	
	@Test
	public void getAccountPassTest() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&");
		accountMap.put("Jackie", "Moon+8_1+&29&What is your favourite colour?/red&");

		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		Account accountGet = accountManager.getAccount("Jackie", "Moon");
		Account accountNew = new Account("Jackie", "Moon");
		accountNew.setCoordinate(8, 1);
		accountNew.setRadius(29);
		
		boolean sameName = accountGet.getEmail().equals(accountNew.getEmail());
		boolean samePass = accountGet.getPassword().equals(accountNew.getPassword());
		
		Coordinate coordinate1 = accountGet.coordinate;
		Coordinate coordinate2 = accountNew.coordinate;
		boolean sameCoord = coordinate1.equals(coordinate1, coordinate2);
		boolean sameRadius = accountGet.radius == accountNew.radius;
		
		assertTrue(sameCoord && sameName && samePass && sameRadius);
	}
	
	@Test
	public void getAccountTest() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&");
		accountMap.put("Jackie", "Moon+8_1+&29&What is your favourite colour?/red&");

		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		Account accountGet = accountManager.getAccount("Jackie");
		Account accountNew = new Account("Jackie", "Moon");
		accountNew.setCoordinate(8, 1);
		accountNew.setRadius(29);
		
		boolean sameName = accountGet.getEmail().equals(accountNew.getEmail());
		boolean samePass = accountGet.getPassword().equals(accountNew.getPassword());
		
		Coordinate coordinate1 = accountGet.coordinate;
		Coordinate coordinate2 = accountNew.coordinate;
		boolean sameCoord = coordinate1.equals(coordinate1, coordinate2);
		boolean sameRadius = accountGet.radius == accountNew.radius;
		
		assertTrue(sameCoord && sameName && samePass && sameRadius);
	}

	
	
	@Test
	public void getAccountPassNull() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&");
		accountMap.put("Jackie", "Moon+8_1+&29&What is your favourite colour?/red&");

		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		Account accountGet = accountManager.getAccount("Jacob", "Moon");
		Account accountNew = new Account("Jackie", "Moon");
		accountNew.setCoordinate(8, 1);
		accountNew.setRadius(29);
		
		assertEquals(accountGet, null);
	}
	
	@Test
	public void getAccountNull() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&");
		accountMap.put("Jackie", "Moon+8_1+&29&What is your favourite colour?/red&");

		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		Account accountGet = accountManager.getAccount("Jacob");
		Account accountNew = new Account("Jackie", "Moon");
		accountNew.setCoordinate(8, 1);
		accountNew.setRadius(29);
		
		
		assertEquals(accountGet, null);
	}
	
	
	@Test
	public void deleteAccountTest() {

		accountMap.clear();

		accountMap.put("Malcolm", "Williams+4_5+&20&What is your favourite colour?/green&");
		accountMap.put("Jackie", "Moon+8_1+&29&What is your favourite colour?/red&");
		accountMap.put("Des", "Barker+8_1+&29&What is your favourite colour?/red&");

		accountManager.accountInfo = accountMap;
		
		accountManager.buildAccounts();
		
		accountManager.deleteAccount(accountManager.getAccount("Malcolm"));
		
		Account accountGet = accountManager.getAccount("Jacob");
		Account accountNew = new Account("Jackie", "Moon");
		accountNew.setCoordinate(8, 1);
		accountNew.setRadius(29);
		
		
		assertEquals(accountGet, null);
	}
	
	
	
	@Test
	public void addStoreTest() {

		storeMap.clear();

		storeManager.stores = storeMap;
				
		Coordinate coordinate = new Coordinate(4, 6);
		storeManager.addStore(new Store("Shoppers", coordinate));
		storeManager.buildStores();
				
		assertTrue(storeManager.getStore("Shoppers").name.equals("Shoppers") && storeManager.getStore("Shoppers").coordinate.getRow() == 4 && storeManager.getStore("Shoppers").coordinate.getCol() == 6);
	}
	
	@Test
	public void addStoreTest2() {

		storeMap.clear();

		storeManager.stores = storeMap;
				
		Coordinate coordinate = new Coordinate(4, 6);
		Coordinate coordinate2 = new Coordinate(5, 5);
		storeManager.addStore(new Store("Shoppers", coordinate));
		storeManager.addStore(new Store("Walmart", coordinate2));
		storeManager.buildStores();
				
		assertTrue((storeManager.getStore("Shoppers").name.equals("Shoppers") && storeManager.getStore("Shoppers").coordinate.getRow() == 4 && storeManager.getStore("Shoppers").coordinate.getCol() == 6) && storeManager.getStore("Walmart").name.equals("Walmart") && storeManager.getStore("Walmart").coordinate.getRow() == 5 && storeManager.getStore("Walmart").coordinate.getCol() == 5);
	}
	
	@Test
	public void addItemNotExists() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Mint", "Food", (float) 3.99, 5, 8, true);
		storeManager.addItem("Carrefour", item);
		
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_6_false*Mint_Food_3.99_5_8_true*");
	}
	
	@Test
	public void addItemExists() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Cucumber", "Food", (float) 2.99, 5, 8, false);
		storeManager.addItem("Carrefour", item);
		
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_14_false*");
	}
	
	@Test
	public void updateStoreName() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		storeManager.updateName("Carrefour", "Lulu");
	
		assertTrue((storeManager.stores.get("Lulu+9_7+")) != null);
	}
	
	@Test
	public void updateStoreName2() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		storeManager.updateName("Carrefour", "Souq");
	
		assertTrue((storeManager.stores.get("Souq+9_7+")) != null);
	}
	
	@Test
	public void updateItemName() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Mint", "Food", (float) 3.99, 5, 8, true);
		storeManager.addItem("Carrefour", item);
		
		storeManager.updateItemName("Carrefour", "Mint", "Pepper");
		
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_6_false*Pepper_Food_3.99_5_8_true*");
	}
	
	@Test
	public void updateItemNameNotExists() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Mint", "Food", (float) 3.99, 5, 8, true);
		storeManager.addItem("Carrefour", item);
		
		storeManager.updateItemName("Carrefour", "Banana", "Pepper");
		
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_6_false*Mint_Food_3.99_5_8_true*");
	}
	
	@Test
	public void updateCategory() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Pepper", "Food", (float) 3.99, 5, 8, true);
		storeManager.addItem("Carrefour", item);
		
		storeManager.updateCategory("Carrefour", "Pepper", "Hygiene");
		
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_6_false*Pepper_Hygiene_3.99_5_8_true*");
	}
	
	@Test
	public void updateCategoryNotExist() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Pepper", "Food", (float) 3.99, 5, 8, true);
		storeManager.addItem("Carrefour", item);
		
		storeManager.updateCategory("Carrefour", "Banana", "Hygiene");
		
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_6_false*Pepper_Food_3.99_5_8_true*");
	}
	
	@Test
	public void updatePrice() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Pepper", "Food", (float) 3.99, 5, 8, true);
		storeManager.addItem("Carrefour", item);
		
		storeManager.updatePrice("Carrefour", "Pepper", (float) 1.80);
				
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_6_false*Pepper_Food_1.8_5_8_true*");
	}
	
	@Test
	public void updatePriceNotExist() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Pepper", "Food", (float) 3.99, 5, 8, true);
		storeManager.addItem("Carrefour", item);
		
		storeManager.updatePrice("Carrefour", "Banana", (float) 1.80);
				
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_6_false*Pepper_Food_3.99_5_8_true*");
	}
	
	
	@Test
	public void updateSize() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Pepper", "Food", (float) 3.99, 5, 8, true);
		storeManager.addItem("Carrefour", item);
		
		storeManager.updateSize("Carrefour", "Pepper", 1);
				
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_6_false*Pepper_Food_3.99_1_8_true*");
	}
	
	@Test
	public void updateSizeNotExist() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Pepper", "Food", (float) 3.99, 5, 8, true);
		storeManager.addItem("Carrefour", item);
		
		storeManager.updateSize("Carrefour", "Banana", 2);
				
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_6_false*Pepper_Food_3.99_5_8_true*");
	}
	
	@Test
	public void updateAvailability() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Pepper", "Food", (float) 3.99, 5, 8, true);
		storeManager.addItem("Carrefour", item);
		
		storeManager.updateAvailability("Carrefour", "Pepper", 1);
				
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_6_false*Pepper_Food_3.99_5_1_true*");
	}
	
	@Test
	public void updateAvailabilityNotExist() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Pepper", "Food", (float) 3.99, 5, 8, true);
		storeManager.addItem("Carrefour", item);
		
		storeManager.updateAvailability("Carrefour", "Banana", 2);
				
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_6_false*Pepper_Food_3.99_5_8_true*");
	}
	
	@Test
	public void updateSale() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Pepper", "Food", (float) 3.99, 5, 8, true);
		storeManager.addItem("Carrefour", item);
		
		storeManager.updateSale("Carrefour", "Pepper", false);
				
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_6_false*Pepper_Food_3.99_5_8_false*");
	}
	
	@Test
	public void updateSaleNotExist() {

		storeMap.clear();
		
		storeMap.put("Carrefour+9_7+", "Cucumber_Food_2.99_5_6_false*");
		
		storeManager.stores = storeMap;

		storeManager.buildStores();
		
		
		Items item = new Item("Pepper", "Food", (float) 3.99, 5, 8, true);
		storeManager.addItem("Carrefour", item);
		
		storeManager.updateSale("Carrefour", "Banana", false);
				
		assertEquals(storeManager.stores.get("Carrefour+9_7+"), "Cucumber_Food_2.99_5_6_false*Pepper_Food_3.99_5_8_true*");
	}
	

}
