package Controllers;

 
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Partie;
import models.Players;
import models.openNewWindowa;

public class IDentifyPlayers implements Initializable {

    @FXML
    private Button btn_nextPlayer;

    @FXML
    private Label labelNomrePLayer;

    @FXML
    private TextField textFieldPLayerName;
	public openNewWindowa opneNEwWindows ;
    public static int ki = 1;

    Partie partieJeux = Partie.getInstance();
     
	@FXML
	void nextPlayer(ActionEvent event) {
		System.out.println("cliki");
		Players p;
		opneNEwWindows= new openNewWindowa(); 
		System.out.println("ki = "+ki);
		
		labelNomrePLayer.setText("" + ki);
		
		System.out.println("nombre player : "+partieJeux.getNumberPlayer());
		if (ki == partieJeux.getNumberPlayer()) {

			

			p = new Players(textFieldPLayerName.getText());
			p.setName(textFieldPLayerName.getText());
			partieJeux.addPlayer(p);
			opneNEwWindows.open("/FichierXml/board.fxml");
			ki++;

		} else {
			ki++;

		 

			p = new Players(textFieldPLayerName.getText());
			p.setName(textFieldPLayerName.getText());
			partieJeux.addPlayer(p);

			opneNEwWindows.open("/FichierXml/playersName.fxml");
			btn_nextPlayer.getScene().getWindow().hide();

		}

	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
