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

	double minBoard = 708;
	double minBoardRowAdd = 708;
	double colomnSizeBoard = 118;
	double RowSizeBoard = 118;

	int colomnAfter = 13;
	int colomnBefore = 6;
	int RowAfter = 13;
	int RowBefore = 6;

	// counter to show only 2 picture
	int counter = 2;

	Partie partieJeux = Partie.getInstance();
	public static Button carteTomove = null;
	public static CarteIndice carteIndiceDispo;

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
				if (counter == 0) {
					partieJeux.NextStep();
					counter = 2;

				}
			}

			System.out.println("card has been showed  :" + counter);
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

	@FXML
	void ExtendRowTop(ActionEvent event) {
		System.err.println("extends TOP");
		if (RowBefore >= 0) {
			// extend(colomnAfter, "col");
			extend(RowBefore, "row");

			RowBefore--;
		} else {
			System.err.println("we can't extends the board anymore");
		}
		resizeTheBoard();
	}

	@FXML
	void ExtendBotton(ActionEvent event) {
		System.err.println("extends BOTTOM");
		if (RowAfter < 20) {
			// extend(colomnAfter, "col");
			extend(RowAfter, "row");

			RowAfter++;
		} else {
			System.err.println("we can't extends the board anymore");
		}
		resizeTheBoard();
	}

	@FXML
	void ExtendLEft(ActionEvent event) {
		System.err.println("extends TLEFT");
		if (colomnBefore >= 0) {
			// extend(colomnAfter, "col");
			extend(colomnBefore, "col");

			colomnBefore--;
		} else {
			System.err.println("we can't extends the board anymore");
		}
		resizeTheBoard();
	}

	@FXML
	void ExtendRight(ActionEvent event) {
		System.err.println("extends RIGHT");
		if (colomnAfter < 20) {
			// extend(colomnAfter, "col");
			extend(colomnAfter, "col");
			colomnAfter++;

		} else {
			System.err.println("we can't extends the board anymore");
		}

	}

	public void BoaredPrepared() {

		// cards = partieJeux.getCards();

	}

	public void extend(int index, String ROW_COLOMN) {
		if (ROW_COLOMN == "col") {

			boarad.getColumnConstraints().get(index).setMinWidth(colomnSizeBoard);
			boarad.getColumnConstraints().get(index).setMaxWidth(colomnSizeBoard);
			boarad.getColumnConstraints().get(index).setPercentWidth(-1);
			boarad.getColumnConstraints().get(index).setPrefWidth(100);;
			 
			minBoard = minBoard + colomnSizeBoard;
			boarad.setMinWidth(minBoard);
		 
			boarad.setMaxHeight(minBoard);
		} else if (ROW_COLOMN == "row") {
			boarad.getRowConstraints().get(index).setMinHeight(colomnSizeBoard);
			boarad.getRowConstraints().get(index).setMaxHeight(colomnSizeBoard);
			boarad.getRowConstraints().get(index).setPercentHeight(-1);
			System.out.println("row");
			minBoardRowAdd = minBoardRowAdd + RowSizeBoard;
			boarad.setMinHeight(minBoardRowAdd);
			// boarad.setMinWidth(minBoard);
			boarad.setMaxHeight(minBoardRowAdd);
			;

		}
		resizeTheBoard();

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
			// openNewWindowa("/FichierXml/CartHAsbeenRevealed.fxml");
		}

	}

//additional method to work on //moving a card with  dragging
	public void dragged(MouseEvent event, YokaiCart p) {
		/*
		 * System.out.println("dragged!!"); p.setX(p.getX() + event.getX());
		 * p.setY(p.getY() + event.getY()); System.out.println(p.getX());
		 * System.out.println(p.getY());
		 */
		// buttonExtendRowTop.setTranslateX(p.getX());
		// buttonExtendRowTop.setTranslateX(p.getY());

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
			card_index = 4;
			break;
		case "position0_04":
			card_index = 5;
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
			partieJeux.getCards().getCards().get(0).getYokaiCart().setX(8);
			partieJeux.getCards().getCards().get(0).getYokaiCart().setY(8);
			showCard(view, 0, btn);

			card_index = 0;

			break;

		case "position0_01":

			partieJeux.getCards().getCards().get(1).getYokaiCart().setX(8);
			partieJeux.getCards().getCards().get(1).getYokaiCart().setY(9);
			showCard(view, 1, btn);
			card_index = 1;
			break;
		case "position0_02":

			partieJeux.getCards().getCards().get(2).getYokaiCart().setX(8);
			partieJeux.getCards().getCards().get(2).getYokaiCart().setY(10);
			showCard(view, 2, btn);
			card_index = 2;
			break;
		case "position0_03":

			partieJeux.getCards().getCards().get(3).getYokaiCart().setX(8);
			partieJeux.getCards().getCards().get(3).getYokaiCart().setY(11);
			showCard(view, 3, btn);
			card_index = 4;
			break;
		case "position0_04":
			partieJeux.getCards().getCards().get(4).getYokaiCart().setX(9);
			partieJeux.getCards().getCards().get(4).getYokaiCart().setY(8);
			showCard(view, 4, btn);
			card_index = 5;
			break;

		case "position0_05":
			partieJeux.getCards().getCards().get(5).getYokaiCart().setX(9);
			partieJeux.getCards().getCards().get(5).getYokaiCart().setY(9);
			showCard(view, 5, btn);
			card_index = 5;
			break;
		case "position0_06":
			partieJeux.getCards().getCards().get(6).getYokaiCart().setX(9);
			partieJeux.getCards().getCards().get(6).getYokaiCart().setY(10);
			showCard(view, 6, btn);
			card_index = 6;
			break;
		case "position0_07":
			partieJeux.getCards().getCards().get(7).getYokaiCart().setX(9);
			partieJeux.getCards().getCards().get(7).getYokaiCart().setY(11);
			showCard(view, 7, btn);
			card_index = 7;
			break;
		case "position0_08":
			partieJeux.getCards().getCards().get(8).getYokaiCart().setX(10);
			partieJeux.getCards().getCards().get(8).getYokaiCart().setY(8);
			showCard(view, 8, btn);
			card_index = 8;
			break;
		case "position0_09":
			partieJeux.getCards().getCards().get(9).getYokaiCart().setX(10);
			partieJeux.getCards().getCards().get(9).getYokaiCart().setY(9);
			showCard(view, 9, btn);
			card_index = 9;
			break;
		case "position0_010":
			partieJeux.getCards().getCards().get(10).getYokaiCart().setX(10);
			partieJeux.getCards().getCards().get(10).getYokaiCart().setY(10);
			showCard(view, 10, btn);
			card_index = 10;
			break;
		case "position0_011":
			partieJeux.getCards().getCards().get(11).getYokaiCart().setX(10);
			partieJeux.getCards().getCards().get(11).getYokaiCart().setY(11);
			showCard(view, 11, btn);
			card_index = 11;
			break;
		case "position0_012":
			partieJeux.getCards().getCards().get(12).getYokaiCart().setX(11);
			partieJeux.getCards().getCards().get(12).getYokaiCart().setY(8);
			showCard(view, 12, btn);
			card_index = 12;
			break;
		case "position0_013":
			partieJeux.getCards().getCards().get(13).getYokaiCart().setX(11);
			partieJeux.getCards().getCards().get(13).getYokaiCart().setY(9);
			showCard(view, 13, btn);
			card_index = 13;
			break;

		case "position0_014":
			partieJeux.getCards().getCards().get(14).getYokaiCart().setX(11);
			partieJeux.getCards().getCards().get(14).getYokaiCart().setY(10);
			showCard(view, 14, btn);
			card_index = 14;
			break;
		case "position0_015":
			partieJeux.getCards().getCards().get(15).getYokaiCart().setX(11);
			partieJeux.getCards().getCards().get(15).getYokaiCart().setY(11);
			showCard(view, 15, btn);

			card_index = 15;
			break;

		default:
			System.out.println("Choix incorrect");
			break;
		}
		return card_index;
	}

	public static int x = 0;
	public static int y = 0;
	public static int xx = 0;

	public void moveCard(Pane pane) {

		boarad.getChildren().remove(pane);
		boarad.getChildren().remove(carteTomove);
		Pane pane2 = new Pane();

		boarad.add(pane2, (int) partieJeux.getCards().getCards().get(0).getYokaiCart().getX(),
				(int) partieJeux.getCards().getCards().get(0).getYokaiCart().getY());

		pane2.setOnMousePressed(event -> pressed(event, pane2));

		partieJeux.getCards().getCards().get(0).getYokaiCart().setX(x);
		partieJeux.getCards().getCards().get(0).getYokaiCart().setY(y);

		boarad.add(carteTomove, x, y);
		carteTomove = null;
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

	// pressed the destination position to move a card
	public void pressed(MouseEvent event, Pane pan) {

		y = boarad.getRowIndex(pan);
		x = boarad.getColumnIndex(pan);

		// deplacer une carte
		if (partieJeux.getEtape() == 2) {
			if (possibleToMove(x, y)) {
				System.out.println("you clicked mee");
				if (carteTomove != null) {
					moveCard(pan);
					partieJeux.NextStep();
				} else {
					System.out.println("Oops no cart has been chosen");
				}

			}
		}
	}

	
	
	public static boolean coloreSwitch = false;
	private void addPane(int colIndex, int rowIndex) {
		

		if ((colIndex == 8 && rowIndex == 8) || (colIndex == 8 && rowIndex == 9) || (colIndex == 8 && rowIndex == 10)
				|| (colIndex == 8 && rowIndex == 11) || (colIndex == 9 && rowIndex == 8)
				|| (colIndex == 9 && rowIndex == 9) || (colIndex == 9 && rowIndex == 10)
				|| (colIndex == 9 && rowIndex == 11) || (colIndex == 10 && rowIndex == 8)
				|| (colIndex == 10 && rowIndex == 9) || (colIndex == 10 && rowIndex == 10)
				|| (colIndex == 10 && rowIndex == 11) || (colIndex == 11 && rowIndex == 8)
				|| (colIndex == 11 && rowIndex == 9) || (colIndex == 11 && rowIndex == 10)
				|| (colIndex == 11 && rowIndex == 11)) {
			System.out.println(colIndex + " : " + rowIndex);
		} else {
			Pane pane = new Pane();
			pane.setMinSize(100, 100);
			pane.setMaxSize(100, 100);
			if (coloreSwitch) {
				//pane.setStyle("-fx-background-color: #F0D9B5;");
				coloreSwitch=false;
				
				
				System.out.println("me");
			} else {
				//first
				//pane.setStyle("-fx-background-color: #B58863;");
				coloreSwitch=true;
				System.out.println("you");
			}

			pane.setOnMousePressed(event -> pressed(event, pane));
			boarad.add(pane, colIndex, rowIndex);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Image img = new Image("/images/icons/badlion_100px.png");
		cercel_player.setFill(new ImagePattern(img));

		btn_dos_indice.setDisable(true);
		for (int i = 0; i < boarad.getColumnCount(); i++) {
			coloreSwitch=!coloreSwitch;
			for (int j = 0; j < boarad.getRowCount(); j++) {

				addPane(i, j);
			}
		}
		for (int i = 0; i < partieJeux.getPlayers().size(); i++) {

			System.out.println(partieJeux.getPlayers().get(i).getName());
		}

	}

	public void openNewWindowa(String st) {
		Stage info = new Stage();
		// info.initStyle(StageStyle.UNDECORATED);
		try {
			fxml = FXMLLoader.load(getClass().getResource(st));
			Scene scene = new Scene(fxml);
			info.setScene(scene);
			info.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void ShowCartIndiceReveald(ActionEvent event) {
		carteIndiceDispo = partieJeux.getCardsIndice().getCardindice()
				.get(partieJeux.getCardsIndice().getCardindice().size() - 1).getCartIndice();

		partieJeux.getCardsIndice().afficher();
		Image img = new Image("/images/indice/" + carteIndiceDispo + ".jpg");
		ImageView view = new ImageView(img);
		view.setFitHeight(200);
		view.setFitWidth(200);
		btn_carte_indice_reveald.setGraphic(view);
		if (!partieJeux.CartIndiceReveled()) {
			openNewWindowa("/FichierXml/gameOver.fxml");
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
		Image img2 = new Image("/images/dos_indice.jpg");
		ImageView view2 = new ImageView(img2);
		view2.setFitHeight(200);
		view2.setFitWidth(200);
		btn_carte_indice_reveald.setGraphic(view2);
		partieJeux.NextStep();
	}

	public void resizeTheBoard() {
		boardPanScrol.setMinSize(boarad.getMaxWidth(), boarad.getMaxHeight());
		boardPanScrol.setMaxSize(boarad.getMaxWidth(), boarad.getMaxHeight());

	}
}
