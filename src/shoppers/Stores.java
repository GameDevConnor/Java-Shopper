package shoppers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Stores {

	public Coordinate coordinate = new Coordinate();
	
	public String name;
	
	public List<Items> items;
	
	public Stores(String name, Coordinate coordinate) {
		this.name = name;
		this.coordinate = coordinate;
		items = new ArrayList<Items>();

	}
	
	
	public void setCoordinate(int row, int col) {
		this.coordinate.row = row;
		this.coordinate.col = col;
	}
	
	
	public void addItem(Items item) {
		items.add(item);
	}
	
	public void removeItem(Items item) {
		items.remove(item);
	}
	
	public List<Items> returnItems() {
		return this.items;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.getName() + "+" + this.coordinate.row + "," + this.coordinate.col + "+";
	}
	
	
}
