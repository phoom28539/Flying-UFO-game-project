package entity.gem;

import entity.base.Gem;
import entity.base.ThreadTimer;
import javafx.scene.paint.ImagePattern;
import resloader.Resloader;
import view.Game;
import view.GameSetting;

public class Speedgem extends Gem implements ThreadTimer {

	private Thread Timer;

	@Override
	public Thread getTimer() {
		return Timer;
	}

	public void setTimer(Thread timer) {
		Timer = timer;
	}

	public Speedgem() {
		super();
		this.getGem().setFill(new ImagePattern(Resloader.speedGem));
	}

	@Override
	public void isSkillActivated() {
		if (isActivated) {
			Game.CURRENT_GEM_TIMER = this;
			isActivated = false;
			GameSetting.GAME_SPEED = 10;
			System.out.println("Speedgem activated");
			int time = Gem.GEM_TIME;
			Game.timerLabel.showTimerPane();
			Resloader.gemSound.play();
			Timer = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Game.timerLabel.updateTimer();
						Thread.sleep(time);
						GameSetting.GAME_SPEED = 7;
						Game.CURRENT_GEM_TIMER = null;
						Game.timerLabel.hideTimerPane();
					} catch (InterruptedException e) {
						GameSetting.GAME_SPEED = 7;
						Game.timerLabel.hideTimerPane();
					}
				}
			});
			Timer.start();
		}
	}
}
