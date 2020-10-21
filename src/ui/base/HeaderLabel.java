package ui.base;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class HeaderLabel extends Label {

	private static final int LABEL_HEIGHT = 70;
	private static final int LABEL_WIDTH = 196;

	private final String LABEL_STYLE = "-fx-background-color: transparent; -fx-background-image: url('"
			+ ClassLoader.getSystemResource("buttonLarge.png").toString() + "');";

	public HeaderLabel(String text) {
		setPrefWidth(LABEL_WIDTH);
		setPrefHeight(LABEL_HEIGHT);
		setPadding(new Insets(10, 10, 10, 10));
		setText(text);
		setWrapText(true);
		setAlignment(Pos.CENTER);
		setLabelFont();
		setStyle(LABEL_STYLE);
	}

	private void setLabelFont() {
		setFont(Font.loadFont(ClassLoader.getSystemResource("kenvector_future.ttf").toString(), 22));
	}

}
