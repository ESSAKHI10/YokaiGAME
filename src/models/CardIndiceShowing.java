package models;

public class CardIndiceShowing {
	public CarteIndice cartIndice;
	public Boolean shown;
	
	public CardIndiceShowing(CarteIndice cartIndice, Boolean shown) {
		super();
		this.cartIndice = cartIndice;
		this.shown = shown;
	}
	public CarteIndice getCartIndice() {
		return cartIndice;
	}
	public void setCartIndice(CarteIndice cartIndice) {
		this.cartIndice = cartIndice;
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
