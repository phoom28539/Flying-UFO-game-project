package entity;

import java.util.Random;

import entity.base.Entity;
import entity.base.Hitable;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import resloader.Resloader;
import ui.PointsPane;
import view.Game;
import view.GameSetting;

public class Star extends Entity implements Hitable {

	private Rectangle[] stars;
	private int random;
	private double starPositionChecker;
	private boolean slotAvailable = true;
	private static int STAR_HEIGHT = 50;
	private static int LASTSTARRELOCATED;
	public Random randomGenerator;

	public Star() {
		super();
		LASTSTARRELOCATED = GameSetting.ROCK_NUMBER - 1;
	}

	@Override
	public void setPosition() {
		for (int i = 0; i < GameSetting.ROCK_NUMBER; i++) {
			random = randomGenerator.nextInt(200) - 100;
			stars[i].setLayoutX(i * GameSetting.REST_GAB + GameSetting.GAME_WIDTH / 2 + GameSetting.REST_GAB / 2);
			stars[i].setLayoutY(random + GameSetting.GAME_HEIGHT / 2);
			System.out.println(i * GameSetting.REST_GAB + GameSetting.GAME_WIDTH / 2 + " :");
		}

	}

	public void checkIfOutOfBorder() {
		for (int i = 0; i < GameSetting.ROCK_NUMBER; i++) {
			if (stars[i].getLayoutX() < -stars[i].getWidth()) {
				reLocate(i);
			}
		}
	}

	public void isStarPositionCheckerOutOfBorder() {
		if (starPositionChecker <= 0 && slotAvailable == false) {
			slotAvailable = true;
		}
	}

	@Override
	public void move() {
		for (int i = 0; i < stars.length; i++) {
			stars[i].setLayoutX(stars[i].getLayoutX() - GameSetting.GAME_SPEED);
		}
		starPositionChecker -= GameSetting.GAME_SPEED;
		isStarPositionCheckerOutOfBorder();
		checkIfOutOfBorder();
		checkIfCollide(Game.UFO);
	}

	@Override
	public void checkIfCollide(Hitable ufoz) {
		UFO ufo = (UFO) ufoz;
		for (int i = 0; i < GameSetting.ROCK_NUMBER; i++) {
			if ((stars[i].getBoundsInParent().intersects(ufo.getShip().getBoundsInParent()))) {
				reLocate(i);
				PointsPane.points++;
				Resloader.collectSound.play();
				PointsPane.updateScore();
			}
		}
	}

	public void reLocate(int i) {
		int chance = randomGenerator.nextInt(1);
		random = randomGenerator.nextInt(200) - 100;
		stars[i].setLayoutX(stars[LASTSTARRELOCATED].getLayoutX() + GameSetting.ROCK_WIDTH + GameSetting.REST_GAB);
		if (chance == 0 && slotAvailable) {
			stars[i].setLayoutY(GameSetting.GAME_WIDTH * 2);
			slotAvailable = false;
			starPositionChecker = (int) stars[i].getLayoutX();
  			int xx = randomGenerator.nextInt(Game.gem.length);
			Game.gem[xx].calledAt((int) (stars[LASTSTARRELOCATED].getLayoutX() + GameSetting.ROCK_WIDTH + GameSetting.REST_GAB),
					random + GameSetting.GAME_HEIGHT / 2);
		} else {
			stars[i].setLayoutY((random + GameSetting.GAME_HEIGHT / 2));
		}
		LASTSTARRELOCATED = i;
	}

	@Override
	public void setGc() {
		randomGenerator = new Random();
		stars = new Rectangle[GameSetting.ROCK_NUMBER];
		for (int i = 0; i < GameSetting.ROCK_NUMBER; i++) {
			// System.out.println(Game.ROCK_NUMBER);
			stars[i] = new Rectangle(STAR_HEIGHT, STAR_HEIGHT, Color.BLACK);
			stars[i].setFill(new ImagePattern(Resloader.starImg));
		}
	}

	public Rectangle[] getStars() {
		return stars;
	}

}
