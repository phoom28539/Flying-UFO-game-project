package ui.base;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class MenuButtons extends Button {

	private static final int BUTTON_HEIGHT = 50;
	private static final int BUTTON_WIDTH = 190;

	private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('"
			+ ClassLoader.getSystemResource("yellow_button01.png").toString() + "');";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('"
			+ ClassLoader.getSystemResource("yellow_button00.png").toString() + "');";

	public MenuButtons(String text) {
		setText(text);
		setPrefWidth(BUTTON_WIDTH);
		setPrefHeight(BUTTON_HEIGHT);
		setButtonsFont();
		setStyle(BUTTON_FREE_STYLE);
		initializeButton();
	}

	private void setButtonsFont() {
		setFont(Font.loadFont(ClassLoader.getSystemResource("kenvector_future.ttf").toString(), 17));
	}

	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}

	private void setButtonReleasedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 4);
	}

	private void initializeButton() {

		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
			}
		});

		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
			}
		});

		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
	}
}
