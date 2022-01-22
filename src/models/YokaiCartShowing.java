package models;

public class YokaiCartShowing {
	private YokaiCart yokaiCart;
	private Boolean shown;
	
	
	 //constructor
	public YokaiCartShowing(YokaiCart yokaiCart, Boolean shown) {
		super();
		this.yokaiCart = yokaiCart;
		this.shown = shown;
	}
	//getters setters
	public YokaiCart getYokaiCart() {
		return yokaiCart;
	}
	public void setYokaiCart(YokaiCart yokaiCart) {
		this.yokaiCart = yokaiCart;
	}
	public Boolean getShown() {
		return shown;
	}
	public void setShown(Boolean shown) {
		this.shown = shown;
	}
	
	public boolean showm() {
		if (!shown) shown=!shown;
		return shown;
	}
}
