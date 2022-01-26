package models;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Board {
	public List<BoardCase> listeCase = new ArrayList<BoardCase>();
	public boolean switchColor = false;

	public Board() {

	}

	public BoardCase chercheCase(int row, int column) {
		for (BoardCase casse : listeCase) {
			if (casse.getX() == row && casse.getY() == column) {
				return casse;
			}
		}
		return null;

	}

	public void PreparBoard(GridPane boarad) {
		for (int i = 0; i < boarad.getColumnCount(); i++) {
			switchColor = !switchColor;
			for (int j = 0; j < boarad.getRowCount(); j++) {
				addPane(i, j, boarad);
			}
		}
	}

	public void addPane(int colIndex, int rowIndex, GridPane boarad) {
		Pane pane = new Pane();
		BoardCase casee = new BoardCase();
		pane.setMinSize(125, 125);
		pane.setMaxSize(125, 125);
		if ((colIndex == 8 && rowIndex == 8) || (colIndex == 8 && rowIndex == 9) || (colIndex == 8 && rowIndex == 10)
				|| (colIndex == 8 && rowIndex == 11) || (colIndex == 9 && rowIndex == 8)
				|| (colIndex == 9 && rowIndex == 9) || (colIndex == 9 && rowIndex == 10)
				|| (colIndex == 9 && rowIndex == 11) || (colIndex == 10 && rowIndex == 8)
				|| (colIndex == 10 && rowIndex == 9) || (colIndex == 10 && rowIndex == 10)
				|| (colIndex == 10 && rowIndex == 11) || (colIndex == 11 && rowIndex == 8)
				|| (colIndex == 11 && rowIndex == 9) || (colIndex == 11 && rowIndex == 10)
				|| (colIndex == 11 && rowIndex == 11)) {
			casee.setY(colIndex);
			casee.setX(rowIndex);
			listeCase.add(casee);
			if (switchColor) {
				casee.setStyle("-fx-background-color: #F0D9B5;");
				switchColor = !switchColor;
				listeCase.add(casee);
			} else {
				casee.setStyle("-fx-background-color: #B58863;");
				switchColor = !switchColor;
				listeCase.add(casee);
			}

		} else {
			if (switchColor) {
				pane.setStyle("-fx-background-color: #F0D9B5;");
				casee.setStyle("-fx-background-color: #F0D9B5;");
				casee.setY(colIndex);
				casee.setX(rowIndex);
				switchColor = false;
				listeCase.add(casee);
			} else {
				pane.setStyle("-fx-background-color: #B58863;");
				casee.setStyle("-fx-background-color: #B58863;");
				casee.setY(colIndex);
				casee.setX(rowIndex);
				switchColor = true;
				listeCase.add(casee);
			}
			boarad.add(pane, colIndex, rowIndex);
		}
	}

	public List<BoardCase> getListeCase() {
		return listeCase;
	}

	public void setListeCase(List<BoardCase> listeCase) {
		this.listeCase = listeCase;
	}

	

}
