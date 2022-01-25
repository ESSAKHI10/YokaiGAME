package Controllers;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import models.YokaiCart;
import models.openNewWindowa;
import models.Partie;
import models.BoardCase;
import models.CarteIndice;

import models.Players;

public class MyController implements Initializable {

	@FXML
	private Label labelNomrePLayer;
	@FXML
	private Button btn_nextPlayer;

	@FXML
	private TextField textFieldPLayerName;
	@FXML
	private Button position0_0;

	@FXML
	private GridPane boarad;

	@FXML
	private Button position0_01;

	@FXML
	private Button position0_010;

	@FXML
	private Button position0_011;

	@FXML
	private Button position0_012;

	@FXML
	private Button position0_013;

	@FXML
	private Button position0_014;

	@FXML
	private Button position0_015;

	@FXML
	private Button position0_02;

	@FXML
	private Button position0_03;

	@FXML
	private Button position0_04;

	@FXML
	private Button position0_05;
	Parent fxml;
	@FXML
	private Button position0_06;

	@FXML
	private Button position0_07;

	@FXML
	private Button position0_08;
	@FXML
	private Circle cercel_player;
	@FXML
	private Button position0_09;
	@FXML
	private Button buttonExtendRowTop;

	@FXML
	private Button ButtonExtendBottom;

	@FXML
	private Button ButtonExtendLeft;
	@FXML
	private AnchorPane boardPanScrol;

	@FXML
	private Button btn_dos_indice;
	public openNewWindowa opneNEwWindows;
	@FXML
	private Button ButtonExtendRight;
	@FXML
	private Button btn_carte_indice_reveald;

	/// counter to show only 2 picture
	int counter = 2;

	Partie partieJeux = Partie.getInstance();
	public static Button carteTomove = null;
	public static CarteIndice carteIndiceDispo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Image img = new Image("/images/icons/badlion_100px.png");
		cercel_player.setFill(new ImagePattern(img));

		btn_dos_indice.setDisable(true);
		partieJeux.getBoardYard().PreparBoard(boarad);

		for (int i = 0; i < boarad.getColumnCount(); i++) {

			for (int j = 0; j < boarad.getRowCount(); j++) {

				boardPane(i, j);
			}
		} 

		 partieJeux.cards.afficher();
	}

	// cette méthode est appellée en cliquant sur une carte pour l'afficher
	@FXML
	void showTheCarte(ActionEvent event) throws InterruptedException {

		// identifier quel est le boutton concerné par le click
		Button btn = (Button) event.getSource();
		int idBtn = getbtnId(btn);
		// vérifier si on est dans la première étape ( celle de regarder deux cartes)
		// le numero de cette étape est 1
		if ((partieJeux.getEtape() == 1) && !partieJeux.getCards().getCards().get(idBtn).getYokaiCart().isHasIndice()) {

			// la variable counter est initialisée a 2 lors de sa déclaration
			// si le joueur regarde une carte on dicrémente le counter
			// si le counter est égale à 0 cela veut dire que ke joueur a regardé les deux
			// cartes
			if (counter != 0) {

				Image img = new Image("images/dos_carte.jpg");

				ImageView view = new ImageView(img);

				view.setFitHeight(100);
				view.setFitWidth(100);
				setImage(btn, view);

				// cette Thread est pour afficher le Yokai pour une durée de 2 secondes
				Thread t = new Thread(() -> {
					try {
						// wait 2 seconds
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					Platform.runLater(() -> {

						view.setImage(img);
						btn.setGraphic(view);

					});
				});
				t.setDaemon(true);
				t.start();
				// fin thread
				counter--;
				System.out.println("card has been showed  :" + counter);
				if (counter == 0) {
					partieJeux.NextStep();
					counter = 2;

				}
			}

		} else if (partieJeux.getEtape() == 2) {
			// là on est dans l'étape 2 donc le joueur va déplacer une carte
			// la carte à déplacer est le bouton qui a reçu l'event click
			carteTomove = btn;
		} else if (partieJeux.getCards().getCards().get(idBtn).getYokaiCart().isHasIndice()) {
			System.out.println(
					partieJeux.getCards().getCards().get(idBtn).getYokaiCart().getName() + " taaaaaaaaaaaaaaf indice");
		} else {
			System.out.println("tu vas mettre " + carteIndiceDispo.getName());
			placerIndiceSurCarte(btn);
			System.out.println("Please shose an indice card ");
			System.out.println("not here " + partieJeux.getEtape());
		}
	}

	public void showCard(ImageView view, int index, Button btn) {

		Image img;
		if ((partieJeux.getEtape() == 1) && !partieJeux.getCards().getCards().get(index).getYokaiCart().isHasIndice()) {
			img = new Image(
					"/images/yokaiImage/" + partieJeux.getCards().getCards().get(index).getYokaiCart() + ".jpg");
			view.setImage(img);
			btn.setGraphic(view);
			partieJeux.getCards().getCards().get(index).showm();

		} else {
			System.out.println("sorry this card a deja un indice");

		}

	}

//additional method to work on //moving a card with  dragging
	public void dragged(MouseEvent event, YokaiCart p) {

	}

	public int getbtnId(Button btn) {

		int card_index = -8;
		switch (btn.getId()) {

		case "position0_0":
			card_index = 0;
			break;

		case "position0_01":
			card_index = 1;
			break;
		case "position0_02":
			card_index = 2;
			break;
		case "position0_03":
			card_index = 3;
			break;
		case "position0_04":
			card_index = 4;
			break;

		case "position0_05":
			card_index = 5;
			break;
		case "position0_06":
			card_index = 6;
			break;
		case "position0_07":
			card_index = 7;
			break;
		case "position0_08":
			card_index = 8;
			break;
		case "position0_09":
			card_index = 9;
			break;
		case "position0_010":
			card_index = 10;
			break;
		case "position0_011":
			card_index = 11;
			break;
		case "position0_012":
			card_index = 12;
			break;
		case "position0_013":
			card_index = 13;
			break;

		case "position0_014":
			card_index = 14;
			break;
		case "position0_015":
			card_index = 15;
			break;

		default:
			System.out.println("Choix incorrect");
			break;
		}
		return card_index;
	}

	public int setImage(Button btn, ImageView view) throws InterruptedException {

		Image img;
		int card_index = -8;
		switch (btn.getId()) {

		case "position0_0":
			//partieJeux.getCards().getCards().get(0).getYokaiCart().setX(8);
			//partieJeux.getCards().getCards().get(0).getYokaiCart().setY(8);
			showCard(view, 0, btn);

			card_index = 0;

			break;

		case "position0_01":

			//partieJeux.getCards().getCards().get(1).getYokaiCart().setX(8);
		//	partieJeux.getCards().getCards().get(1).getYokaiCart().setY(9);
			showCard(view, 1, btn);
			card_index = 1;
			break;
		case "position0_02":

		//	partieJeux.getCards().getCards().get(2).getYokaiCart().setX(8);
		//	partieJeux.getCards().getCards().get(2).getYokaiCart().setY(10);
			showCard(view, 2, btn);
			card_index = 2;
			break;

		case "position0_03":

		//	partieJeux.getCards().getCards().get(3).getYokaiCart().setX(8);
		//	partieJeux.getCards().getCards().get(3).getYokaiCart().setY(11);
			showCard(view, 3, btn);
			card_index = 3;
			break;
		case "position0_04":
		//	partieJeux.getCards().getCards().get(4).getYokaiCart().setX(9);
		//	partieJeux.getCards().getCards().get(4).getYokaiCart().setY(8);
			showCard(view, 4, btn);
			card_index = 4;
			break;

		case "position0_05":
		//	partieJeux.getCards().getCards().get(5).getYokaiCart().setX(9);
		//	partieJeux.getCards().getCards().get(5).getYokaiCart().setY(9);
			showCard(view, 5, btn);
			card_index = 5;
			break;
		case "position0_06":
			//partieJeux.getCards().getCards().get(6).getYokaiCart().setX(9);
			//partieJeux.getCards().getCards().get(6).getYokaiCart().setY(10);
			showCard(view, 6, btn);
			card_index = 6;
			break;
		case "position0_07":
		//	partieJeux.getCards().getCards().get(7).getYokaiCart().setX(9);
		//	partieJeux.getCards().getCards().get(7).getYokaiCart().setY(11);
			showCard(view, 7, btn);
			card_index = 7;
			break;
		case "position0_08":
		//	partieJeux.getCards().getCards().get(8).getYokaiCart().setX(10);
		//	partieJeux.getCards().getCards().get(8).getYokaiCart().setY(8);
			showCard(view, 8, btn);
			card_index = 8;
			break;
		case "position0_09":
		//	partieJeux.getCards().getCards().get(9).getYokaiCart().setX(10);
		//	partieJeux.getCards().getCards().get(9).getYokaiCart().setY(9);
			showCard(view, 9, btn);
			card_index = 9;
			break;
		case "position0_010":
		//	partieJeux.getCards().getCards().get(10).getYokaiCart().setX(10);
		//	partieJeux.getCards().getCards().get(10).getYokaiCart().setY(10);
			showCard(view, 10, btn);
			card_index = 10;
			break;
		case "position0_011":
		////	partieJeux.getCards().getCards().get(11).getYokaiCart().setX(10);
		///	partieJeux.getCards().getCards().get(11).getYokaiCart().setY(11);
			showCard(view, 11, btn);
			card_index = 11;
			break;
		case "position0_012":
		//	partieJeux.getCards().getCards().get(12).getYokaiCart().setX(11);
		//	partieJeux.getCards().getCards().get(12).getYokaiCart().setY(8);
			showCard(view, 12, btn);
			card_index = 12;
			break;
		case "position0_013":
		//	partieJeux.getCards().getCards().get(13).getYokaiCart().setX(11);
		//	partieJeux.getCards().getCards().get(13).getYokaiCart().setY(9);
			showCard(view, 13, btn);
			card_index = 13;
			break;

		case "position0_014":
		//	partieJeux.getCards().getCards().get(14).getYokaiCart().setX(11);
			//partieJeux.getCards().getCards().get(14).getYokaiCart().setY(10);
			showCard(view, 14, btn);
			card_index = 14;
			break;
		case "position0_015":
		//	partieJeux.getCards().getCards().get(15).getYokaiCart().setX(11);
		//	partieJeux.getCards().getCards().get(15).getYokaiCart().setY(11);
			showCard(view, 15, btn);

			card_index = 15;
			break;

		default:
			System.out.println("Choix incorrect");
			break;
		}
		return card_index;
	}

	public static int x = -1;
	public static int y = -1;
	 

	public void moveCard(Pane pane) {

		Pane pane2 = new Pane();

		boarad.getChildren().remove(pane);
		boarad.getChildren().remove(carteTomove);

		// get the actual position of the card to color her place after moving her
		int idCarte = getbtnId(carteTomove);
		System.out.println("moving " + carteTomove.getId() + " (" + idCarte + ") : ("
				+ partieJeux.getCards().getCards().get(3).getCord().getX() + ","
				+ partieJeux.getCards().getCards().get(3).getCord().getY() + ")");

		// search for the color of the case from board list :

		BoardCase casse = partieJeux.getBoardYard().chercheCase(
				partieJeux.getCards().getCards().get(idCarte).getCord().getX(),
				(int) partieJeux.getCards().getCards().get(idCarte).getCord().getY());
		pane2.setStyle(casse.getStyle());
		// add this event to the board place
		pane2.setOnMousePressed(event -> pressed(event, pane2));
		
		
        System.out.println("add panne to :" +partieJeux.getCards().getCards().get(idCarte).getCord().getX()+","+partieJeux.getCards().getCards().get(idCarte).getCord().getY());
        
        
		// color the palce lefting
        
        
		boarad.add(pane2, (int) partieJeux.getCards().getCards().get(idCarte).getCord().getY(),
				(int) partieJeux.getCards().getCards().get(idCarte).getCord().getX());

		System.out.println("carte from liste board : {" + casse.getX() + " ," + casse.getY() + " }");

		// set the new coordinate to the card
		partieJeux.getCards().getCards().get(idCarte).getCord().setX(x);
		partieJeux.getCards().getCards().get(idCarte).getCord().setY(y);

		boarad.add(carteTomove, y, x);
		System.out.println("carte have been moved to (" + x + "," + y + ")");
		carteTomove = null;
		x =  -1;
		y = -1;
		btn_dos_indice.setDisable(false);
	}

	public boolean possibleToMove(int x, int y) {

		if (getNodeByCoordinate(x, y + 1) || getNodeByCoordinate(x, y - 1) || getNodeByCoordinate(x + 1, y)
				|| getNodeByCoordinate(x - 1, y)) {
			System.out.println("case available to receive any card");
			return true;
		} else {
			System.out.println("this case are not  available to receive any card");
			return false;
		}
		 
	}

	boolean getNodeByCoordinate(Integer row, Integer column) {
		for (Node node : boarad.getChildren()) {
			if (boarad.getRowIndex(node) == row && boarad.getColumnIndex(node) == column) {
				if (node instanceof Button) {
					return true;
				}

			}
		}
		return false;
	}

	boolean boardPane(Integer row, Integer column) {
		for (Node node : boarad.getChildren()) {
			if (boarad.getRowIndex(node) == row && boarad.getColumnIndex(node) == column) {
				if (node instanceof Pane) {
					node.setOnMousePressed(event -> pressed(event, node));
				}

			}
		}
		return false;
	}

	// pressed the destination position to move a card
	public void pressed(MouseEvent event, Node node) {

		x = boarad.getRowIndex(node);
		y = boarad.getColumnIndex(node);
		System.out.println("move to (" + x + "," + y + ")");
		// deplacer une carte
		if (partieJeux.getEtape() == 2) {
			if (possibleToMove(x, y)==true) {
				System.out.println("you clicked mee (" + x + "," + y + ")");
				if (carteTomove != null) {
					moveCard((Pane) node);
					partieJeux.NextStep();
					carteTomove = null;
				} else {
					System.out.println("Oops no cart has been chosen");
				}

			}else {
				System.out.println("case error");
			}
		}
	}

	@FXML
	void ShowCartIndiceReveald(ActionEvent event) {
		carteIndiceDispo = partieJeux.getCardsIndice().getCardindice()
				.get(partieJeux.getCardsIndice().getCardindice().size() - 1).getCartIndice();

		// partieJeux.getCardsIndice().afficher();
		Image img = new Image("/images/indice/" + carteIndiceDispo + ".jpg");
		ImageView view = new ImageView(img);
		view.setFitHeight(125);
		view.setFitWidth(125);
		btn_carte_indice_reveald.setGraphic(view);
		if (!partieJeux.CartIndiceReveled()) {

			opneNEwWindows.open("/FichierXml/gameOver.fxml");
		}
		btn_dos_indice.setDisable(true);
	}

	@FXML
	void Use_cart_indice(ActionEvent event) {
		// identifier quel est l'indice concerné par le click
		System.out.println("----------" + carteIndiceDispo.getName());
	}

	public void placerIndiceSurCarte(Button btn) {
		Image img = new Image("/images/indice/" + carteIndiceDispo + ".jpg");
		ImageView view = new ImageView(img);
		view.setFitHeight(90);
		view.setFitWidth(90);
		btn.setGraphic(view);

		switch (btn.getId()) {

		case "position0_0":
			partieJeux.getCards().getCards().get(0).getYokaiCart().setHasIndice(true);
			break;

		case "position0_01":

			partieJeux.getCards().getCards().get(1).getYokaiCart().setHasIndice(true);
			break;
		case "position0_02":

			partieJeux.getCards().getCards().get(2).getYokaiCart().setHasIndice(true);
			break;
		case "position0_03":

			partieJeux.getCards().getCards().get(3).getYokaiCart().setHasIndice(true);
			break;
		case "position0_04":
			partieJeux.getCards().getCards().get(4).getYokaiCart().setHasIndice(true);
			break;

		case "position0_05":
			partieJeux.getCards().getCards().get(5).getYokaiCart().setHasIndice(true);
			break;
		case "position0_06":
			partieJeux.getCards().getCards().get(6).getYokaiCart().setHasIndice(true);
			break;
		case "position0_07":
			partieJeux.getCards().getCards().get(7).getYokaiCart().setHasIndice(true);
			break;
		case "position0_08":
			partieJeux.getCards().getCards().get(8).getYokaiCart().setHasIndice(true);
			break;
		case "position0_09":
			partieJeux.getCards().getCards().get(9).getYokaiCart().setHasIndice(true);
			break;
		case "position0_010":
			partieJeux.getCards().getCards().get(10).getYokaiCart().setHasIndice(true);
			break;
		case "position0_011":
			partieJeux.getCards().getCards().get(11).getYokaiCart().setHasIndice(true);
			break;
		case "position0_012":
			partieJeux.getCards().getCards().get(12).getYokaiCart().setHasIndice(true);
			break;
		case "position0_013":
			partieJeux.getCards().getCards().get(13).getYokaiCart().setHasIndice(true);
			break;

		case "position0_014":
			partieJeux.getCards().getCards().get(14).getYokaiCart().setHasIndice(true);
			break;
		case "position0_015":
			partieJeux.getCards().getCards().get(15).getYokaiCart().setHasIndice(true);
			break;

		default:
			System.out.println("Choix incorrect");
			break;
		}
		
		/*
		 * Image img2 = new Image("/images/dos_indice.jpg"); ImageView view2 = new
		 * ImageView(img2); view2.setFitHeight(200); view2.setFitWidth(200);
		 * btn_carte_indice_reveald.setGraphic(view2); partieJeux.NextStep();
		 */
	}
	/*
	 * public void resizeTheBoard() { boardPanScrol.setMinSize(boarad.getMaxWidth(),
	 * boarad.getMaxHeight()); boardPanScrol.setMaxSize(boarad.getMaxWidth(),
	 * boarad.getMaxHeight());
	 * 
	 * }
	 */
}
