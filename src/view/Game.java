package view;

import entity.UFO;
import entity.Rock;
import entity.Star;
import entity.base.Gem;
import entity.base.ThreadTimer;
import entity.gem.Powergem;
import entity.gem.Speedgem;
import entity.gem.Slowgem;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import resloader.Resloader;
import ui.TimerPane;
import ui.BackgroudPane;
import ui.DeadSubscene;
import ui.PointsPane;

public class Game {
	private AnchorPane gamePane;
	private Scene gameScene;
	private Rock rocks;
	private Star stars;
	private BackgroudPane bg;
	public static Stage gameStage;
	public static TimerPane timerLabel;
	public static PointsPane pointsLabel;
	public static AnimationTimer GAMETIMER;
	public static UFO UFO;
	public static Gem[] gem;
	public static boolean IS_PRESS_JUMP, IS_GODMODE, IS_ALIVE;
	public static boolean TRIGGER;
	public static ThreadTimer CURRENT_GEM_TIMER;


	public Game() {
		InitializeStage();
		createKeyListener();
	}

	
	private void InitializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GameSetting.GAME_WIDTH, GameSetting.GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setTitle("Flying UFO");
		gameStage.setScene(gameScene);
	}

	
	public void createNewGame() {
		Menu.mainStage.hide();
		Resloader.Load();
		createGameEntities();
		createGameUi();
		createGameLoop();
		IS_ALIVE = true;
		IS_PRESS_JUMP = false;
		IS_GODMODE = false;
		TRIGGER = false;
		gameStage.show();
	}

	
	private void createGameUi() {
		
		// Initialize pointsPane
		pointsLabel = new PointsPane();
		gamePane.getChildren().add(pointsLabel.getPointsLabel());
		
		
		// Initialize timerPane
		timerLabel = new TimerPane();
		gamePane.getChildren().add(timerLabel.getTimerLabel());
		timerLabel.hideTimerPane();
		
	}
	

	private void createGameEntities() {
		
		// Initialize backGrounds
		bg = new BackgroudPane();
		gamePane.getChildren().add(bg.getRect()[0]);
		gamePane.getChildren().add(bg.getRect()[1]);
		
		// Initialize star
		stars = new Star();
		for (int i = 0; i < GameSetting.ROCK_NUMBER; i++) {
			gamePane.getChildren().add(stars.getStars()[i]);
		}

		// Initialize rocks
		rocks = new Rock();
		for (int i = 0; i < GameSetting.ROCK_NUMBER; i++) {
			gamePane.getChildren().addAll(rocks.getRocksUp()[i], rocks.getRocksDown()[i]);
		}
		
		// Initialize UFO
		UFO = new UFO();
		gamePane.getChildren().add(UFO.getShip());
		
		// Initialize Gems
		gem = new Gem[3];
		gem[0] = (Gem) new Slowgem(); 
		gem[1] = (Gem) new Powergem();
		gem[2] = (Gem) new Speedgem();
		for (Gem x : gem) {
			gamePane.getChildren().add(x.getGem());
		}
	}

	
	private void createKeyListener() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.SPACE && TRIGGER == false ) {
						TRIGGER = true;
						IS_PRESS_JUMP = true;
						UFO.jump();		
				}
				if (event.getCode() == KeyCode.G) {
					IS_GODMODE = true;
					System.out.println("Godmode");
				}
				if (event.getCode() == KeyCode.N) {
					IS_GODMODE = false;
					System.out.println("Normalmode");
				}
			}
		});
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				IS_PRESS_JUMP = false;
				TRIGGER = false;
			}
		});
	}
	

	private void createGameLoop() {
		GAMETIMER = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if(IS_ALIVE == false) {
					Dead();
				}else {
					createBackgroundSound();
					playBackgroundSound();
					rocks.move();
					if(IS_PRESS_JUMP) {
						UFO.jump();
					} else {
						UFO.move();
					}
					bg.move();
					stars.move();
					for (Gem x : gem) {
						x.move();
						x.isSkillActivated();
					}
					
				}
			}
		};
		GAMETIMER.start();
	}
	
	
	private void Dead() {
		Resloader.bombSound.play();
		stopBackgroundSound();
		Game.GAMETIMER.stop();
		UFO.UFO_ANI.stop();
		PointsPane.updateHighScore();
		gamePane.getChildren().removeAll(pointsLabel.getPointsLabel(), timerLabel.getTimerLabel());
		DeadSubscene deadSubscene = new DeadSubscene();
		deadSubscene.moveSubScene();
		gamePane.getChildren().addAll(deadSubscene,UFO.getBomb());
		try {
			if (CURRENT_GEM_TIMER != null) {
				CURRENT_GEM_TIMER.getTimer().interrupt();
				timerLabel.getTimer().interrupt();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	private void createBackgroundSound() {
		Resloader.backgroundSound.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				Resloader.backgroundSound.seek(javafx.util.Duration.ZERO);
			}
		});
	}

	
	public static void playBackgroundSound() {
		Resloader.backgroundSound.play();
	}

	
	public static void stopBackgroundSound() {
		Resloader.backgroundSound.stop();
	}

}

