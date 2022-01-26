package models;

public enum YokaiCart implements Carte {
	carte_bleu("Oni", 1), 
	carte_rouge("Kitsune", 2), 
	carte_vert("Kappa", 3), 
	carte_violet("Rokurokubi", 4);

	String name;
	int number;
	
	private YokaiCart(String name, int number) {
		this.name = name;
		this.number = number;
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
