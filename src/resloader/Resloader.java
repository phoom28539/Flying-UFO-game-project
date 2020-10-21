package resloader;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import view.Menu;

public class Resloader {

	public static ImageView logoImg;
	public static Image bgMenuImg;

	public static Image bgImg;
	
	public static ImageView howtoplayImg;

	public static Image YellowRockUp;
	public static Image YellowRockDown;

	public static Image starImg;

	public static ImageView blueShip;
	public static ImageView blueShip2;
	public static ImageView pinkShip;
	public static ImageView pinkShip2;

	public static ImageView gameOver;
;

	public static ImageView bomb1;
	public static ImageView bomb2;

	public static Image powerGem;
	public static Image speedGem;
	public static Image slowGem;
	
	public static AudioClip collectSound;
	public static AudioClip gemSound;
	public static AudioClip powerSound;
	public static AudioClip bombSound;
	public static MediaPlayer backgroundSound;

	public static void Load() {

		logoImg = new ImageView(ClassLoader.getSystemResource("logo.png").toString());
		bgMenuImg = new Image(ClassLoader.getSystemResource("cityskyline.png").toString(), Menu.WIDTH,
				Menu.HEIGHT, false, false);

		bgImg = new Image(ClassLoader.getSystemResource("snowymountains.png").toString());
		
		howtoplayImg = new ImageView(ClassLoader.getSystemResource("howTo.png").toString());

		YellowRockUp = new Image(ClassLoader.getSystemResource("yellowRockUp.png").toString());
		YellowRockDown = new Image(ClassLoader.getSystemResource("yellowRockDown.png").toString());

		starImg = new Image(ClassLoader.getSystemResource("starGold.png").toString());

		blueShip = new ImageView(ClassLoader.getSystemResource("shipBlue_manned.png").toString());
		blueShip2 = new ImageView(ClassLoader.getSystemResource("shipBlue.png").toString());
		pinkShip = new ImageView(ClassLoader.getSystemResource("shipPink_manned.png").toString());
		pinkShip2 = new ImageView(ClassLoader.getSystemResource("shipPink.png").toString());

		gameOver = new ImageView(ClassLoader.getSystemResource("gameOver.png").toString());

		bomb1 = new ImageView(ClassLoader.getSystemResource("bomb1.png").toString());
		bomb2 = new ImageView(ClassLoader.getSystemResource("bomb2.png").toString());

		powerGem = new Image(ClassLoader.getSystemResource("tileRed.png").toString());
		speedGem = new Image(ClassLoader.getSystemResource("tileBlue.png").toString());
		slowGem = new Image(ClassLoader.getSystemResource("tileGreen.png").toString());
		
		collectSound = new AudioClip(ClassLoader.getSystemResource("phaserUp4.wav").toString());
		gemSound = new AudioClip(ClassLoader.getSystemResource("phaseJump1.wav").toString());
		bombSound = new AudioClip(ClassLoader.getSystemResource("bombEx.wav").toString());
		
		backgroundSound = new MediaPlayer(new Media(ClassLoader.getSystemResource("backgroundSound.mp3").toString()));
	}

}
