package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class InfoLabel extends Label {

	public InfoLabel(String text) {
		setPrefWidth(600);
		setPrefHeight(49);
		setPadding(new Insets(40, 40, 40, 40));
		setText(text);
		setWrapText(true);
		setAlignment(Pos.CENTER);
		setLabelFont();
	}

	private void setLabelFont() {
		setFont(Font.loadFont(ClassLoader.getSystemResource("kenvector_future.ttf").toString(), 20));
	}

}
