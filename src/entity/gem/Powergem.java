package entity.gem;

import entity.Rock;
import entity.base.Gem;
import entity.base.ThreadTimer;
import javafx.scene.paint.ImagePattern;
import resloader.Resloader;
import view.Game;

public class Powergem extends Gem implements ThreadTimer {
	Thread Timer;

	public Powergem() {
		super();
		this.getGem().setFill(new ImagePattern(Resloader.powerGem));
	}

	@Override
	public void isSkillActivated() {
		if (isActivated) {
			Game.CURRENT_GEM_TIMER = this;
			isActivated = false;
			Rock.powerGemActivated = true;
			System.out.println("Powergem activated");
			int time = Gem.GEM_TIME;
			Game.timerLabel.showTimerPane();
			Resloader.gemSound.play();
			Timer = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Game.timerLabel.updateTimer();
						Thread.sleep(time);
						Rock.powerGemActivated = false;
						Game.CURRENT_GEM_TIMER = null;
						Game.timerLabel.hideTimerPane();
					} catch (InterruptedException e) {
						Rock.powerGemActivated = false;
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
