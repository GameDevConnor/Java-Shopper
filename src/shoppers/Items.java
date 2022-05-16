package shoppers;

public abstract class Items {
	
	public String name;
	public String category;
	public float price;
	public int size;
	public int availability;
	public boolean onSale;
	
	public Stores stores;
	
	public Items(String name, String category, float price, int size, int availability, boolean sale) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.size = size;
		this.availability = availability;
		this.onSale = sale;
	}
	
	public int getAvailability() {
		return this.availability;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public String toString() {
		return this.getName() + "_" + this.getCategory() + "_" + this.getPrice() + "_" + this.getSize() + "_" + this.getAvailability() + "_" + this.onSale + "*";
	}
	
	@Override
	public boolean equals(Object theOtherItem) {
		
		Items otherItem = (Items) theOtherItem;
		
		if (this.name.equals(otherItem.name) && this.size == otherItem.size) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Stores getStores() {
		return this.stores;
	}
	
	

}
