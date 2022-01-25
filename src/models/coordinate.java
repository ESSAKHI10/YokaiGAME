package models;

public class coordinate {
	private int y;

	private int x;
	
	

	public coordinate() {
		super();
	}

	public coordinate(int x, int y) {
		super();
		this.y = y;
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setX(int d) {
		this.x = (int) d;
	}

	public int getY() {
		return y;
	}
	public void setY(int d) {
		this.y = d;
	}
}
