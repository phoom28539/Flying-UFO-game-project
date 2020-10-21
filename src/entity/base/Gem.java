package entity.base;

import entity.UFO;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Game;
import view.GameSetting;

public abstract class Gem extends Entity implements Hitable {

	private static final int GEM_HEIGHT = 50;
	private Rectangle gem;
	private boolean isCalled;
	public boolean isActivated;
	public static final int GEM_TIME = 5000;

	public Gem() {
		super();
		isCalled = false;
		isActivated = false;
	}

	public Rectangle getGem() {
		return gem;
	}

	@Override
	public void setPosition() {
		gem.setLayoutX(-GameSetting.GAME_HEIGHT);
		gem.setLayoutY(-GameSetting.GAME_WIDTH);
	}

	public void checkIfOutOfBorder() {
		if (gem.getLayoutX() < -gem.getWidth()) {
			reLocate();
		}
	}

	public void reLocate() {
		this.setPosition();
	}

	@Override
	public void move() {
		if (isCalled) {
			gem.setLayoutX(gem.getLayoutX() - GameSetting.GAME_SPEED);
			checkIfCollide(Game.UFO);
		}
	}

	@Override
	public void setGc() {
		gem = new Rectangle();
		gem = new Rectangle(GEM_HEIGHT, GEM_HEIGHT);
	}

	public void calledAt(int posx, int posy) {
		gem.setLayoutX(posx);
		gem.setLayoutY(posy);
		isCalled = true;
	}

	@Override
	public void checkIfCollide(Hitable x) {
		UFO ufo = (UFO) x;
		if ((gem.getBoundsInParent().intersects(ufo.getShip().getBoundsInParent()))) {
			reLocate();
			isActivated = true;
		}
	}

	public boolean isActivated() {
		return isActivated;
	}

	public abstract void isSkillActivated();

}
