package models;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.GridPane;

public final class Partie {
	private static Partie partieJeux;
	public List<Players> players;
	public int numberPlayer;
	public YokaiplayingCard cards;
	public CardIndicePlaying cardsIndice;
	public List<CardIndiceShowing> cardIndiceShowing;
	public int etape;
	private Board boardYard;
	private boolean gameOver;

	public Board getBoardYard() {
		return boardYard;
	}

	public void setBoardYard(Board boardYard) {
		this.boardYard = boardYard;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	private Partie() {
		super();
		this.players = new ArrayList<Players>();
		this.cards = new YokaiplayingCard();
		this.cardsIndice = new CardIndicePlaying();
		this.cardIndiceShowing = new ArrayList<CardIndiceShowing>();
		this.etape = 1;
		this.boardYard = new Board();

	}

	public static synchronized Partie getInstance() {
		if (partieJeux == null) {
			System.out.println("creations");

			return partieJeux = new Partie();
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
		afficherINdice();
		return cardsIndice.removedFrom();

	}

	public void afficher() {
		for (YokaiCardShowing car : cards.getCards()) {
			System.out.println("------------------");
			System.out.println(car.getYokaiCart().getName() + " :" + car.getCord().getX() + "," + car.getCord().getY());
		}
	}

	public void afficherINdice() {
		for (CardIndiceShowing car : cardsIndice.getCardindice()) {
			System.out.println("------------------");
			System.out.println(car.getCartIndice().getName());
		}
	}

	public void NextStep() {

		this.setEtape(this.getEtape() + 1);
		if (this.getEtape() == 4) {
			this.setEtape(1);
			// System.out.println("next player---------------------------" + getEtape());

		}

		// System.out.println("next step --------" + getEtape());

	}

	public YokaiCardShowing chrecheCartyokai(int x, int y) {
		for (YokaiCardShowing card : cards.getCards()) {
			if (card.getCord().getX() == x && card.getCord().getY() == y) {

				return card;
			}
		}
		return null;

	}

	public int kappa = 0, oni = 0, kitsune = 0, rukorukobi = 0;

	public void Score() {
		boolean win = true;
		afficher();
		for (YokaiCardShowing car : cards.getCards()) {
			if (!sisterWithMe(car)) {
				System.out.println("partie perdue");
				win = false;
				break;
			}
		}
		if (win == true && kappa >= 2 && kitsune >= 2 && rukorukobi >= 2 && oni >= 2) {
			System.out.println("win");
			System.out.println(kappa);
			System.out.println(oni);

			System.out.println(kitsune);
			System.out.println(rukorukobi);
		}else {
			System.out.println("partie perdue");
		 
			System.out.println(kappa);
			System.out.println(oni);

			System.out.println(kitsune);
			System.out.println(rukorukobi);
		}

	}

	public boolean sisterWithMe(YokaiCardShowing car) {
		System.out.println(
				" vreify :" + car.getYokaiCart().name + "(" + car.getCord().getX() + "," + car.getCord().getY() + ")");
		int top = -1, right = -1, left = -1, down = -1;
		int count = 0;
		if (chrecheCartyokai(car.getCord().getX() + 1, car.getCord().getY()) != null) {
			if (chrecheCartyokai(car.getCord().getX() + 1, car.getCord().getY()).getYokaiCart().getName() == car
					.getYokaiCart().getName()) {
				System.out.println("tru");
				top = 1;
				count = count + 1;
			}
		}
		if (chrecheCartyokai(car.getCord().getX() - 1, car.getCord().getY()) != null) {
			if (chrecheCartyokai(car.getCord().getX() - 1, car.getCord().getY()).getYokaiCart().getName() == car
					.getYokaiCart().getName()) {
				System.out.println("tru");
				down = 1;
				count = count + 1;
			}
		}
		if (chrecheCartyokai(car.getCord().getX(), car.getCord().getY() + 1) != null) {
			if (chrecheCartyokai(car.getCord().getX(), car.getCord().getY() + 1).getYokaiCart().getName() == car
					.getYokaiCart().getName()) {
				System.out.println("tru");
				right = 1;
				count = count + 1;
			}
		}
		if (chrecheCartyokai(car.getCord().getX(), car.getCord().getY() - 1) != null) {
			if (chrecheCartyokai(car.getCord().getX(), car.getCord().getY() - 1).getYokaiCart().getName() == car
					.getYokaiCart().getName()) {
				System.out.println("tru");
				left = 1;
				count = count + 1;
			}
		}

		if (car.getYokaiCart().getName() == "Oni") {
			if (count > oni) {
				oni = count;
			}

		} else if (car.getYokaiCart().getName() == "Kitsune") {
			if (count > kitsune) {
				kitsune = count;
			}

		} else if (car.getYokaiCart().getName() == "Kappa") {

			if (count > kappa) {
				kappa = count;
			}
		} else if (car.getYokaiCart().getName() == "Rokurokubi") {

			if (count > rukorukobi) {
				rukorukobi = count;
			}
		} else {

		}

		if ((right == 1 || left == 1 || top == 1 || down == 1)) {
			return true;
		} else {
			return false;
		}

	}

}
