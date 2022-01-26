package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class YokaiplayingCard {
	private List<YokaiCardShowing> cards;
	
	public YokaiplayingCard() {
		cards = new ArrayList<YokaiCardShowing>();
		for (YokaiCart card : YokaiCart.values()) {
			for (int i = 0; i < 4; i++) {
				cards.add(new YokaiCardShowing(card, false));
			}

		}
		shuffle();
		positon();
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
			y = y + 1;
			if (y == 12) {
				x++;
				y = 8;
			}

		}
	}

	public List<YokaiCardShowing> getCards() {
		return cards;
	}
}
