package models;

import java.util.ArrayList;
import java.util.List;
public class Partie {
	public List<Players> players;
	public int numberPlayer;
	public YokaiplayingCard cards;
	public static CardIndicePlaying cardsIndice;
	public List<CardIndiceShowing> cardIndiceShowing;
    public boolean gameOver ;
    
	public Partie() {
		super();
		this.players = new ArrayList<Players>();
		this.cards = new YokaiplayingCard();
		this.cardsIndice = new CardIndicePlaying();
		this.cardIndiceShowing = new ArrayList<CardIndiceShowing>();

	}
	
	public List<Players> getPlayers() {
		return players;
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
        System.out.println("cards releaved ");
		for (CardIndiceShowing c : cardIndiceShowing) {
			System.out.println("YokaiplayingCardIndice [ cards=" + c.getCartIndice() + "] ");
		}

		return cardsIndice.removedFrom();

	}

}
