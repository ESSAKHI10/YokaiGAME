package models;

public enum YokaiCart implements Carte {
	carte_bleu("Oni", 1), carte_rouge("Kitsune", 2), carte_vert("Kappa", 3), carte_violet("Rokurokubi", 4);

	String name;
	int number;
	private boolean hasIndice;
 

	 

	private YokaiCart(String name, int number) {
		this.name = name;
		this.number = number;
		this.hasIndice=false;
	}

	public boolean isHasIndice() {
		return hasIndice;
	}

	public void setHasIndice(boolean hasIndice) {
		this.hasIndice = hasIndice;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String typeCart(String Nom) {
		// TODO Auto-generated method stub
		return null;
	}

}
