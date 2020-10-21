package ui;

import entity.base.Gem;
import entity.base.ThreadTimer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import view.Game;

public class TimerPane implements ThreadTimer {
	private Thread Timer;
	private Label timerLabel;
	private int times;

	public TimerPane() {
		timerLabel = new Label();
		times = 0;
		timerLabel.setAlignment(Pos.TOP_CENTER);
		timerLabel.setLayoutX(550);
		timerLabel.setPrefHeight(800);
		timerLabel.setPrefWidth(400);
		timerLabel.setStyle("-fx-background-color: transparent;");
		timerLabel.setPadding(new Insets(10, 10, 10, 10));
		timerLabel.setText("00");
		setFont();
	}

	private void setFont() {
		timerLabel.setFont(Font.loadFont(ClassLoader.getSystemResource("Kenney_Blocks.ttf").toString(), 70));
	}

	public void updateTimer() {
		times = Gem.GEM_TIME / 1000;
		updateTimeGUI();
		int time = 1000;
		Timer = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < (Gem.GEM_TIME / 1000); i++) {
						if (Game.IS_ALIVE) {
							Thread.sleep(time);
							times--;
							updateTimeGUI();
							System.out.println(i);
						}
					}
					System.out.println("Gem times out!");
				} catch (InterruptedException e) {}
			}
		});
		Timer.start();
	}

	private void updateTimeGUI() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				String textToSet = "0";
				timerLabel.setText(textToSet + times);
			}
		});
	}

	public void showTimerPane() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				timerLabel.setLayoutY(0);
			}
		});
	}
	
	
	
	
	public void hideTimerPane() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				timerLabel.setLayoutY(1000);
			}
		});
	}

	public Label getTimerLabel() {
		return timerLabel;
	}

	@Override
	public Thread getTimer() {
		return Timer;
	}

}
