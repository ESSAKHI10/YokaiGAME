package models;

import java.util.ArrayList;
import java.util.List;
public final class Partie {
	private static Partie partieJeux  ;
	
	
	public List<Players> players;
	public int numberPlayer;
	public YokaiplayingCard cards;
	public  CardIndicePlaying cardsIndice;
	public List<CardIndiceShowing> cardIndiceShowing;
	public int etape ;
    public boolean gameOver ;
    
 
	private Partie() {
		super();
		this.players = new ArrayList<Players>();
		this.cards = new YokaiplayingCard();
		this.cardsIndice = new CardIndicePlaying();
		this.cardIndiceShowing = new ArrayList<CardIndiceShowing>();
		this.etape=1;

	}

	public static synchronized Partie getInstance() {
		if(partieJeux == null) {
			System.out.println("creations");
			
			return partieJeux= new Partie();
		}
		System.out.println("already exits");
		return partieJeux;
	}
	
	
	public List<Players> getPlayers() {
		return players;
	}


	

	
 

	public int getEtape() {
		return etape;
	}

	public void setEtape(int etape) {
		this.etape = etape;
	}

	public CardIndicePlaying getCardsIndice() {
		return cardsIndice;
	}

	public void setCardsIndice(CardIndicePlaying cardsIndice) {
		this.cardsIndice = cardsIndice;
	}

	public void setPlayers(List<Players> players) {
		this.players = players;
	}

	public int getNumberPlayer() {
		return numberPlayer;
	}

	public void setNumberPlayer(int numberPlayer) {
		this.numberPlayer = numberPlayer;
	}

	public YokaiplayingCard getCards() {
		return cards;
	}

	public void setCards(List<YokaiplayingCard> cards) {
		this.cards = (YokaiplayingCard) cards;
	}

	public void addPlayer(Players p) {
		players.add(p);
	}

	public boolean CartIndiceReveled() {
		cardIndiceShowing.add(CardIndicePlaying.getCardindice().get(this.cardsIndice.getCardindice().size() - 1));
		/*
		 * System.out.println("cards releaved "); for (CardIndiceShowing c :
		 * cardIndiceShowing) { System.out.println("YokaiplayingCardIndice [ cards=" +
		 * c.getCartIndice() + "] "); }
		 */
		

		return cardsIndice.removedFrom();

	}
	public void NextStep() {
	 
		this.setEtape(this.getEtape()+1);
		if (this.getEtape()  == 4) {
			this.setEtape(1);
			System.out.println("next player---------------------------" + getEtape());
		
		}
			
			System.out.println("next step --------" + getEtape());
			
	}


}
