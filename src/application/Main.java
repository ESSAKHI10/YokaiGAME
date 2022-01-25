package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// ouvrir le fichier board.fxml
			//Parent root = FXMLLoader.load(getClass().getResource("/FichierXml/YokaiScreen.fxml"));
			
			Parent root = FXMLLoader.load(getClass().getResource("/FichierXml/board.fxml"));
			primaryStage.setTitle("Yokai Game");
		//	primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
