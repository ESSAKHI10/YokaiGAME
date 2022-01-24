package models;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class openNewWindowa {
	public String FxmlChemin;
	public Parent fxml;
	public Stage info;

	public String getFxmlChemin() {
		return FxmlChemin;
	}

	public void setFxmlChemin(String fxmlChemin) {
		FxmlChemin = fxmlChemin;
	}

	public openNewWindowa() {
		super();

	}

	public void open(String str) {
		System.out.println("openening a new windows ");
		info = new Stage();
	    info.initStyle(StageStyle.UNDECORATED);
		try {

			fxml = FXMLLoader.load(getClass().getResource(str));

			Scene scene = new Scene(fxml);
			info.setScene(scene);
			info.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
