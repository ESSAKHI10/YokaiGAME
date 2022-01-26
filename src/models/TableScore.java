package models;

public class TableScore {
	
	private static TableScore tableScore;
	private int score;
	private String victoire;
	
	private TableScore(int score, String victoire) {
		super();
		this.score = score;
		this.victoire = victoire;
	}
	
	public static synchronized TableScore getInstance(int score, String victoire) {
		if(tableScore == null) {
			System.out.println("creations");
			
			return tableScore= new TableScore(score, victoire);
		}
		System.out.println("already exits");
		return tableScore;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getVictoire() {
		return victoire;
	}

	public void setVictoire(String victoire) {
		this.victoire = victoire;
	}

	@Override
	public String toString() {
		return "TableScore [score=" + score + ", victoire=" + victoire + "]";
	}
	
	

}
