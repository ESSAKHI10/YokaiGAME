package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import models.openNewWindowa;

public class ScreenController implements Initializable {
	public openNewWindowa opneNEwWindows ;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Spalsh();
	}

	public void Spalsh() {

		new Thread() {
			public void run() {
				try {
				Thread.sleep(3000);
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						opneNEwWindows = new openNewWindowa() ;
						opneNEwWindows.open("/FichierXml/play_exit.fxml");
					}
				});
			}
		
		
		}.start();
}
}
