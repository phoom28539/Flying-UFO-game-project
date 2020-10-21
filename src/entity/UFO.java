package entity;

import entity.base.Entity;
import entity.base.Hitable;
import entity.base.OutOfBorderException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import resloader.Resloader;
import ui.CharacterSelectionSubscene;
import view.Game;
import view.GameSetting;

public class UFO extends Entity implements Hitable {

	private double currentVelocity;
	private double timer;
	private boolean isJumping;
	
	public static Timeline UFO_ANI;
	private Group shipGroup;
	private ImageView ship1;
	private ImageView ship2;
	
	

	public static Timeline BOMB_ANI;
	private Group bombGroup;
	private ImageView bomb1;
	private ImageView bomb2;

	public UFO() {
		UFOAnimationHandle();
		setSize();
		isJumping = false;
	}
	public void checkIfOutOfBorder() throws OutOfBorderException {
		if ((shipGroup.getLayoutY() > GameSetting.GAME_HEIGHT || shipGroup.getLayoutY() < 0) && !Game.IS_GODMODE) {
			throw new OutOfBorderException("Out of bound");
		}
	}

	public void UFOAnimationHandle() {
		UFO_ANI = new Timeline();
		UFO_ANI.setCycleCount(Timeline.INDEFINITE);
		UFO_ANI.getKeyFrames().add(new KeyFrame(Duration.millis(100), (ActionEvent event) -> {
			shipGroup.getChildren().setAll(ship1);
		}));
		UFO_ANI.getKeyFrames().add(new KeyFrame(Duration.millis(200), (ActionEvent event) -> {
			shipGroup.getChildren().setAll(ship2);
		}));
		UFO_ANI.getKeyFrames().add(new KeyFrame(Duration.millis(300), (ActionEvent event) -> {
			shipGroup.getChildren().setAll(ship1);
		}));
		UFO_ANI.play();
	}
	
	

	public void bombAnimationHandle() {
		BOMB_ANI = new Timeline();
		BOMB_ANI.setCycleCount(Timeline.INDEFINITE);
		BOMB_ANI.getKeyFrames().add(new KeyFrame(Duration.millis(100), (ActionEvent event) -> {
			shipGroup.getChildren().setAll(bomb1);
		}));
		BOMB_ANI.getKeyFrames().add(new KeyFrame(Duration.millis(200), (ActionEvent event) -> {
			shipGroup.getChildren().setAll(bomb2);
		}));
		BOMB_ANI.getKeyFrames().add(new KeyFrame(Duration.millis(300), (ActionEvent event) -> {
			shipGroup.getChildren().setAll(bomb1);
		})); 
		BOMB_ANI.play();
	}

	public void setBombPosition() {
		bombAnimationHandle();
		bombGroup.setLayoutX(shipGroup.getLayoutX() - ship1.getFitWidth() / 2 );
		bombGroup.setLayoutY(shipGroup.getLayoutY() - ship1.getFitHeight() / 2);
	}

	@Override
	public void setPosition() {
		shipGroup.setLayoutX(150);
		shipGroup.setLayoutY(GameSetting.GAME_HEIGHT / 2);
	}

	@Override
	public void move() {
		if (shipGroup.getLayoutY() < GameSetting.GAME_HEIGHT * 2) {
			if(isJumping) {
				timer = 0;
				isJumping = false;
			}
			if(this.currentVelocity <= 10)
			this.currentVelocity = calVelocity(0.9);
			shipGroup.setLayoutY(shipGroup.getLayoutY() + this.currentVelocity);
			this.timer += 0.01;
		}
		try {
			checkIfOutOfBorder();
		} catch (OutOfBorderException e) {
			Game.IS_ALIVE = false;
		}

	}

	public void jump() {
		if (Game.IS_ALIVE) {
			if (shipGroup.getLayoutY() > -GameSetting.GAME_HEIGHT) {
				if(!isJumping) {
					timer = 0; 
					currentVelocity=0;
					isJumping = true;
				}
				if(this.currentVelocity >= -5)
				this.currentVelocity = calVelocity(-2);
				shipGroup.setLayoutY(shipGroup.getLayoutY() + this.currentVelocity);
				this.timer += 0.01;
			}
			
		}
		try {
			checkIfOutOfBorder();
		} catch (OutOfBorderException e) {
			Game.IS_ALIVE = false;
		}

	}

	public Group getShip() {
		return shipGroup;
	}

	public Group getBomb() {
		setBombPosition();
		return bombGroup;
	}

	private double calVelocity(double a) {
		return this.currentVelocity + a * this.timer;
	}

	@Override
	public void checkIfCollide(Hitable x) {

	}

	public void setSize() {
		ship1.setFitHeight(76.5);
		ship1.setFitWidth(64);
		ship2.setFitHeight(76.5);
		ship2.setFitWidth(64);
		bomb1.setFitHeight(80);
		bomb1.setFitWidth(80);
		bomb2.setFitHeight(120);
		bomb2.setFitWidth(120);
	}

	@Override
	public void setGc() {

		bomb1 = Resloader.bomb1;
		bomb2 = Resloader.bomb2;
		bombGroup = new Group(bomb1);
		
		if(CharacterSelectionSubscene.chaSel==0){
			ship1 = Resloader.blueShip;
			ship2 = Resloader.blueShip2;
			shipGroup = new Group(ship1);
		}else {
			ship1 = Resloader.pinkShip;
			ship2= Resloader.pinkShip2;
			shipGroup = new Group(ship1);
		}
		

	}

}
