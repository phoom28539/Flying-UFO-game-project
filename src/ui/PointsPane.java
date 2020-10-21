package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class PointsPane {

	private static Label pointsLabel;
	public static int points;
	public static int highestPoints = 0;

	public PointsPane() {
		pointsLabel = new Label();
		points = 0;
		pointsLabel.setPrefHeight(800);
		pointsLabel.setPrefWidth(400);
		pointsLabel.setStyle("-fx-background-color: transparent;");
		pointsLabel.setAlignment(Pos.TOP_LEFT);
		pointsLabel.setPadding(new Insets(10, 10, 10, 10));
		pointsLabel.setText("POINTS : 00");
		setFont();
	}

	private void setFont() {
		pointsLabel.setFont(Font.loadFont(ClassLoader.getSystemResource("Kenney_Blocks.ttf").toString(),30));
	}

	public static void updateScore() {
		String textToSet = "POINTS : ";
		if (points < 10) {
			textToSet = textToSet + "0";
		}
		pointsLabel.setText(textToSet + points);
	}

	public static void updateHighScore() {
		if (points > highestPoints) {
			highestPoints = points;
		}
	}

	public Label getPointsLabel() {
		return pointsLabel;
	}

	public static void setPoints(int points) {
		PointsPane.points = points;
	}

}
