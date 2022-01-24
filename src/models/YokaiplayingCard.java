package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class YokaiplayingCard {
	private static List<YokaiCartShowing> cards;

	public YokaiplayingCard() {
		cards = new ArrayList<YokaiCartShowing>();
		for (YokaiCart card : YokaiCart.values()) {
			 System.out.println("Creating card [" + card + "]   ");
			 for (int i = 0; i < 4; i++) {
		     	System.out.println("  [" + card + "]    ");
        	    cards.add(new YokaiCartShowing(card, false));
			 
		 }
			 
		}
		 shuffle();
	}

	public void shuffle() {
		Random random = new Random();
		for (int i = 0; i < cards.size(); ++i) {
			Collections.swap(cards, i, random.nextInt(cards.size()));
		}
	}

	public List<YokaiCartShowing> getCards() {
		return cards;
	}

	 
	public void afficher() {
		for (YokaiCartShowing c:cards)
			System.out.println("YokaiplayingCard [ cards=" + c.getYokaiCart()+ "] " );
		 
	}
	

}
