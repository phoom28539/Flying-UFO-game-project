package entity;

import java.util.Random;

import entity.base.Entity;
import entity.base.Hitable;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import resloader.Resloader;
import ui.PointsPane;
import view.Game;
import view.GameSetting;

public class Rock extends Entity implements Hitable {

	private Rectangle[] rocksUp;
	private Rectangle[] rocksDown;
	public static int LASTROCKRELOCATED;
	private int random;
	private Random randomGenerator;
	public static boolean powerGemActivated = false;

	public Rock() {
		super();
		LASTROCKRELOCATED = GameSetting.ROCK_NUMBER - 1;
	}

	@Override
	public void setPosition() {
		for (int i = 0; i < GameSetting.ROCK_NUMBER; i++) {
			random = randomGenerator.nextInt(20) * 20;
			rocksUp[i].setLayoutX(i * GameSetting.REST_GAB + GameSetting.GAME_WIDTH / 2);
			rocksUp[i].setLayoutY(-random + GameSetting.ROCK_HEIGHT + GameSetting.ROCK_GAB);
			rocksDown[i].setLayoutX(i * GameSetting.REST_GAB + GameSetting.GAME_WIDTH / 2); 
			rocksDown[i].setLayoutY(-random);
			System.out.println(i * GameSetting.REST_GAB + GameSetting.GAME_WIDTH / 2 + " :");
		}

	}

	public void checkIfOutOfBorder() {
		for (int i = 0; i < GameSetting.ROCK_NUMBER; i++) {
			random = randomGenerator.nextInt(20) * 20;
			if (rocksUp[i].getLayoutX() < -rocksUp[i].getWidth()) {
				reLocate(i);
			}
		}
	}

	public void reLocate(int i) {
		rocksUp[i].setLayoutX(rocksUp[LASTROCKRELOCATED].getLayoutX() + GameSetting.REST_GAB + rocksUp[i].getWidth());
		rocksDown[i].setLayoutX(rocksUp[LASTROCKRELOCATED].getLayoutX() + GameSetting.REST_GAB + rocksUp[i].getWidth());
		rocksUp[i].setLayoutY(-random + GameSetting.ROCK_HEIGHT + GameSetting.ROCK_GAB);
		rocksDown[i].setLayoutY(-random);
		LASTROCKRELOCATED = i;
	}

	@Override
	public void checkIfCollide(Hitable ufoz) {
		UFO ufo = (UFO) ufoz;
		for (int i = 0; i < GameSetting.ROCK_NUMBER; i++) {
			if ((rocksUp[i].getBoundsInParent().intersects(ufo.getShip().getBoundsInParent())
					|| rocksDown[i].getBoundsInParent().intersects(ufo.getShip().getBoundsInParent()))
					&& !Game.IS_GODMODE) {
				Game.IS_ALIVE=false;
			}
		}
	}

	@Override
	public void move() {
		for (int i = 0; i < rocksUp.length; i++) {
			rocksUp[i].setLayoutX(rocksUp[i].getLayoutX() - GameSetting.GAME_SPEED);
			rocksDown[i].setLayoutX(rocksDown[i].getLayoutX() - GameSetting.GAME_SPEED);
		}
		checkIfOutOfBorder();
		hitByPowerGem(Game.UFO, powerGemActivated);
		checkIfCollide(Game.UFO);
	}

	public void hitByPowerGem(Hitable ufoz, boolean x) {
		UFO ufo = (UFO) ufoz;
		if (x) {
			for (int i = 0; i < GameSetting.ROCK_NUMBER; i++) {
				if ((rocksUp[i].getBoundsInParent().intersects(ufo.getShip().getBoundsInParent()))) {
					rocksUp[i].setLayoutY(GameSetting.ROCK_HEIGHT * 2);
					PointsPane.points++;
					PointsPane.updateScore();
				}
				if ((rocksDown[i].getBoundsInParent().intersects(ufo.getShip().getBoundsInParent()))) {
					rocksDown[i].setLayoutY(GameSetting.ROCK_HEIGHT * 2);
					PointsPane.points++;
					PointsPane.updateScore();
				}
			}
		}

	}

	@Override
	public void setGc() {
		randomGenerator = new Random();
		rocksUp = new Rectangle[GameSetting.ROCK_NUMBER];
		rocksDown = new Rectangle[GameSetting.ROCK_NUMBER];
		for (int i = 0; i < GameSetting.ROCK_NUMBER; i++) {
			rocksUp[i] = new Rectangle(GameSetting.ROCK_WIDTH, GameSetting.ROCK_HEIGHT);
			rocksDown[i] = new Rectangle(GameSetting.ROCK_WIDTH, GameSetting.ROCK_HEIGHT);
			rocksUp[i].setFill(new ImagePattern(Resloader.YellowRockUp));
			rocksDown[i].setFill(new ImagePattern(Resloader.YellowRockDown));
		}
	}
	public Rectangle[] getRocksUp() {
		return rocksUp;
	}

	public Rectangle[] getRocksDown() {
		return rocksDown;
	}

}
