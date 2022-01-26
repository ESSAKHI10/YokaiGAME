package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import models.Partie;
import models.openNewWindowa;

public class ChooseNumbePlayer implements Initializable {

    @FXML
    private Button foorlayer;

    @FXML
    private Button threePlayer;

    @FXML
    private Button twoPlayer;
    
    
    Partie partieJeux = Partie.getInstance( );
	public openNewWindowa opneNEwWindows ;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	void ShoseNumberPLayers(ActionEvent event) {
		opneNEwWindows= new openNewWindowa(); 
		Button btnn = (Button) event.getSource();
		if (btnn.getId().equals("twoPlayer")) {
			partieJeux.setNumberPlayer(2);
			twoPlayer.getScene().getWindow().hide();
			opneNEwWindows.open("/FichierXml/playersName.fxml");

		} else if (btnn.getId() == "") {
		} else if (btnn.getId() == "") {
		} else {
		}
	}


}
