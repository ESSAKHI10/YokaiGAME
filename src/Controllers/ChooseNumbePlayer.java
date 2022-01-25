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
		System.out.println(btnn.getId());
		if (btnn.getId().equals("twoPlayer")) {
			System.out.println("coco");
			partieJeux.setNumberPlayer(2);
			System.out.println("oooooooooooch :"+partieJeux.getNumberPlayer());
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
