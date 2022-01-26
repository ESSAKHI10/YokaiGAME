package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardIndicePlaying {
	private static List<CardIndiceShowing> cardindice;

	public CardIndicePlaying() {
		cardindice = new ArrayList<CardIndiceShowing>();
		for (CarteIndice card : CarteIndice.values()) {

			System.out.println("  [" + card + "]    ");
			cardindice.add(new CardIndiceShowing(card, false));

		}
		shuffle();

	}

	public void shuffle() {
		Random random = new Random();
		for (int i = 0; i < cardindice.size(); ++i) {
			Collections.swap(cardindice, i, random.nextInt(cardindice.size()));
		}
	}

	// getters
	public static List<CardIndiceShowing> getCardindice() {
		return cardindice;
	}

	public void afficher() {
		for (CardIndiceShowing c : cardindice)
			System.out.println("YokaiplayingCardIndice [ cards=" + c.getCartIndice() + "] ");

	}

	public boolean removedFrom() {
		if (this.cardindice.size()!= 1) {
			System.out.println(this.cardindice.size());
			 
			cardindice.remove(this.cardindice.size() - 1);
			return true;
		}
		else {
			return false;
		}		
	}

	public CardIndiceShowing getElentFrom(int index) {
	System.err.println("carte removed ");
	return cardindice.get(index);

	}
}
