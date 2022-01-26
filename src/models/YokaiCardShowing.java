package models;

public class YokaiCardShowing {
	private YokaiCart yokaiCart;
	private coordinate cord;
	private Boolean hasIndice;
	private CarteIndice carteIndice;
	public YokaiCardShowing(YokaiCart yokaiCart,   Boolean hasIndice) {
		super();
		this.yokaiCart = yokaiCart;
		this.hasIndice = hasIndice;
	}
	public coordinate getCord() {
		return cord;
	}
	public void setCord(coordinate cord) {
		this.cord = cord;
	}
	
	public CarteIndice getCarteIndice() {
		return carteIndice;
	}
	public void setCarteIndice(CarteIndice carteIndice) {
		this.carteIndice = carteIndice;
	}
	//getters setters
	public YokaiCart getYokaiCart() {
		return yokaiCart;
	}
	public void setYokaiCart(YokaiCart yokaiCart) {
		this.yokaiCart = yokaiCart;
	}
	
	
	public Boolean getHasIndice() {
		return hasIndice;
	}
	public void setHasIndice(Boolean hasIndice) {
		this.hasIndice = hasIndice;
	}
}
