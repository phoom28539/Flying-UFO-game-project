package entity.gem;

import entity.base.Gem;
import entity.base.ThreadTimer;
import javafx.scene.paint.ImagePattern;
import resloader.Resloader;
import view.Game;
import view.GameSetting;

public class Slowgem extends Gem implements ThreadTimer {

	private Thread Timer;

	public void setTimer(Thread timer) {
		Timer = timer;
	}

	public Slowgem() {
		super();
		this.getGem().setFill(new ImagePattern(Resloader.slowGem));
	}

	@Override
	public void isSkillActivated() {
		if (isActivated) {
			Game.CURRENT_GEM_TIMER = this;
			isActivated = false;
			GameSetting.GAME_SPEED = 2;
			System.out.println("Slowgem activated");
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

	@Override
	public Thread getTimer() {
		return Timer;
	}

}