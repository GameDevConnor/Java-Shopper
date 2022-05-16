package shoppers;

import java.util.*;

import ui.*;

public class Coordinate {

	int row;
	int col;
		
	
	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Coordinate() {
		this.row = -1;
		this.col = -1;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public boolean equals(Coordinate firstCoordinate, Coordinate secondCoordinate) {
		if (firstCoordinate.getRow() == secondCoordinate.getRow() && firstCoordinate.getCol() == secondCoordinate.getCol()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int distance(Coordinate from, Coordinate to) {
		int y = Math.abs(to.getCol() - from.getCol());
		int x = Math.abs(to.getRow() - from.getRow());

		int cSquared = (y*y) + (x*x);
		
		int c = (int) Math.sqrt(cSquared);
		
		return c;
	}
	
	

}
