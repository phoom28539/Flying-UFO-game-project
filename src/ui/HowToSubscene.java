package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import resloader.Resloader;
import ui.base.MenuButtons;
import ui.base.MenuSubscene;

public class HowToSubscene extends MenuSubscene {

	public HowToSubscene(int h, int w) {
		super(h, w);
		InfoLabel label = new InfoLabel("");
		label.setLayoutX(0);
		label.setLayoutY(25);
		MenuButtons backButton = createBackButton();
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				moveSubScene();
			}
		});
		Resloader.howtoplayImg.setLayoutY(100);
		this.getPane().getChildren().addAll(label, backButton, Resloader.howtoplayImg);
	}

	private MenuButtons createBackButton() {
		MenuButtons backButton = new MenuButtons(" X");
		backButton.setPrefSize(50, 50);
		backButton.setAlignment(Pos.CENTER);
		backButton.setLayoutX(550);
		return backButton;
	}

}

