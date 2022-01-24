package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.shape.Rectangle;
import models.Partie;
import models.openNewWindowa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;

public class PlayController implements Initializable {

	@FXML
	private Rectangle kappa;

	@FXML
	private Rectangle kitsuni;

	@FXML
	private Rectangle oni;
	@FXML
	private Button threePlayer;

	@FXML
	private Button twoPlayer;
	@FXML
	private Button play;
	@FXML
	private Label label_choose_player;
	@FXML
	private Button foorlayer1;
	@FXML
	private Button btn_exite;
	@FXML
	private Rectangle rokurokubi;
	public openNewWindowa opneNEwWindows;
	Partie partieJeux = Partie.getInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// set a clip to apply rounded border to the original image.
		Image img = new Image("/images/yokaiImage/carte_rouge.jpg");
		kitsuni.setFill(new ImagePattern(img));

		Image img1 = new Image("/images/yokaiImage/carte_vert.jpg");
		kappa.setFill(new ImagePattern(img1));

		Image img2 = new Image("/images/yokaiImage/carte_bleu.jpg");
		oni.setFill(new ImagePattern(img2));

		Image img3 = new Image("/images/yokaiImage/carte_violet.jpg");
		rokurokubi.setFill(new ImagePattern(img3));
		twoPlayer.setVisible(false);
		threePlayer.setVisible(false);
		label_choose_player.setVisible(false);
		foorlayer1.setVisible(false);

	}

	@FXML
	void play(ActionEvent event) {
		System.out.println("wi");
		play.setVisible(false);
		twoPlayer.setVisible(true);
		threePlayer.setVisible(true);
		label_choose_player.setVisible(true);
		foorlayer1.setVisible(true);
		btn_exite.setVisible(false);

	}

	@FXML
	void ShoseNumberPLayers(ActionEvent event) {
		opneNEwWindows = new openNewWindowa();
		Button btnn = (Button) event.getSource();
		System.out.println(btnn.getId());
		if (btnn.getId().equals("twoPlayer")) {
			System.out.println("coco");
			partieJeux.setNumberPlayer(2);
			System.out.println("oooooooooooch :" + partieJeux.getNumberPlayer());
			// labelNomrePLayer.setText("1");
			opneNEwWindows.open("/FichierXml/playersName.fxml");

		} else if (btnn.getId() == "") {
			System.out.println("im inside  if 2 ");

		} else if (btnn.getId() == "") {
			System.out.println("im inside  if 3");
		} else {
			System.out.println("im inside  else  ");
		}
	}
}
