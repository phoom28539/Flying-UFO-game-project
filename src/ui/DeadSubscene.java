package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import resloader.Resloader;
import ui.base.HeaderLabel;
import ui.base.MenuButtons;
import ui.base.MenuSubscene;
import view.Game;
import view.Menu;

public class DeadSubscene extends MenuSubscene {

	public DeadSubscene() {
		super(600, 600);
		setGc();
	}

	private void setGc() {
		// set GameOverText position.
		Resloader.gameOver.setLayoutX(120);
		Resloader.gameOver.setLayoutY(52);
		createHighestScoreLabel();
		createCurrentScoreLabel();
		this.getPane().getChildren().addAll(Resloader.gameOver, createRetrybutton(), createBackbutton());
	}

	private MenuButtons createBackbutton() {
		MenuButtons backButton = new MenuButtons("BACK");
		backButton.setLayoutX(336.6);
		backButton.setLayoutY(470);
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Game.gameStage.close();
				Menu.characterSelectionSubscene.moveSubScene();
			
				Menu.mainStage.show();
			}
		});
		return backButton;
	}

	private MenuButtons createRetrybutton() {
		MenuButtons retryButton = new MenuButtons("TRY AGAIN!!!");
		retryButton.setLayoutX(73.3);
		retryButton.setLayoutY(470);
		retryButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Game.gameStage.close();
				Game game = new Game();
				System.out.println("Retry.");
				game.createNewGame();
			}
		});
		return retryButton;
	}

	private void createHighestScoreLabel() {
		HeaderLabel highestScoreLabel = new HeaderLabel("BEST");
		highestScoreLabel.setLayoutX(202);
		highestScoreLabel.setLayoutY(160);
		InfoLabel highestScore = new InfoLabel(String.valueOf(PointsPane.highestPoints));
		highestScore.setLayoutY(200);
		this.getPane().getChildren().addAll(highestScoreLabel, highestScore);
	}

	private void createCurrentScoreLabel() {
		HeaderLabel scoreLabel = new HeaderLabel("SCORE");
		scoreLabel.setLayoutX(202);
		scoreLabel.setLayoutY(310);
		InfoLabel score = new InfoLabel(String.valueOf(PointsPane.points));
		score.setLayoutY(350);
		this.getPane().getChildren().addAll(scoreLabel, score);
	}

}
