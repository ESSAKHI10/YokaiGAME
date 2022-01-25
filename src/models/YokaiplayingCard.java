package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class YokaiplayingCard {
	private List<YokaiCartShowing> cards;

	public YokaiplayingCard() {
		cards = new ArrayList<YokaiCartShowing>();
		for (YokaiCart card : YokaiCart.values()) {
			System.out.println("Creating card [" + card.getName() + "]   ");
			for (int i = 0; i < 4; i++) {
				System.out.println("  [" + card + "]    ");
				cards.add(new YokaiCartShowing(card, false));

			}

		}
		shuffle();
		positon();
		System.out.println("----------------");
		afficher();
	}

	public void shuffle() {
		Random random = new Random();
		for (int i = 0; i < cards.size(); ++i) {
			Collections.swap(cards, i, random.nextInt(cards.size()));
		}
	}

	public void positon() {
		int x = 8;
		int y = 8;
		for (int i = 0; i < 16; i++) {
            coordinate cor =new coordinate(x,y);
			this.cards.get(i).setCord(cor);
			 

			System.out.println(cards.get(i).getYokaiCart().getName() + " : " + cards.get(i).getCord().getX() + ","+ cards.get(i).getCord().getY());
		 
			y = y + 1;
			if (y == 12) {
				x++;
				y = 8;
			}

		}
	}

	public List<YokaiCartShowing> getCards() {
		return cards;
	}

	public void afficher() {

		for (YokaiCartShowing c : cards)
			System.out.println(
					c.getYokaiCart().getName() + " : " + c.getCord().getX() + "," + c.getCord().getY());

	}
}
