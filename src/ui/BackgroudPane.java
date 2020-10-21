package ui;

import entity.base.Entity;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import resloader.Resloader;
import view.GameSetting;

public class BackgroudPane extends Entity {

	Rectangle rect;
	Rectangle[] rects;

	public BackgroudPane() {
	}

	@Override
	public void setPosition() {
		rects[0].setLayoutX(0 * GameSetting.GAME_WIDTH);
		rects[1].setLayoutX(1 * GameSetting.GAME_WIDTH);
	}


	@Override
	public void move() {
		rects[0].setLayoutX(rects[0].getLayoutX() - 2);
		rects[1].setLayoutX(rects[1].getLayoutX() - 2);
		for (int i = 0; i <= 1; i++) {
			if (rects[i].getLayoutX() <= -GameSetting.GAME_WIDTH) {
				rects[i].setLayoutX(GameSetting.GAME_WIDTH);
			}
		}
	}

	@Override
	public void setGc() {
		rects = new Rectangle[2];
		rects[0] = new Rectangle(GameSetting.GAME_WIDTH, GameSetting.GAME_HEIGHT);
		rects[1] = new Rectangle(GameSetting.GAME_WIDTH, GameSetting.GAME_HEIGHT);
		rects[0].setFill(new ImagePattern(Resloader.bgImg));
		rects[1].setFill(new ImagePattern(Resloader.bgImg));
	}

	public Rectangle[] getRect() {
		return rects;
	}

}
