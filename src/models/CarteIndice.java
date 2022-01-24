package models;

public enum CarteIndice implements Carte {
	indice_bleu("indice_bleu"), 
	indice_bleu_rouge("indice_bleu_rouge"),
	indice_bleu_vert_rouge("indice_bleu_vert_rouge"),
	indice_bleu_vert_violet("idice_bleu_vert_violet"),
	indice_bleu_violet("indice_bleu_violet"),
	indice_bleu_violet_rouge("indcice_bleu_violet_rouge"),
	indice_rouge("indice_rouge"), 
	indice_rouge_violet("indice_rouge_violet"), 
	indice_vert_bleu("indice_vert_bleu"),
	indice_vert_rouge("indice_vert_rouge"),
	indice_vert_violet("indice_vert_violet"),
	indice_vert_violet_rouge("indice_vert_violet_rouge"),
	indice_vert("indice_vert"),
	indice_violet("indice_violet"),;

	String name;

	private CarteIndice(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String typeCart(String Nom) {
		// TODO Auto-generated method stub
		return Nom;
	}

	public String getName() {
		return name;
	}
	

}
