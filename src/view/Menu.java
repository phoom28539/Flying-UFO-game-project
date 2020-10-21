package view;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import resloader.Resloader;
import ui.CharacterSelectionSubscene;
import ui.HowToSubscene;
import ui.base.MenuButtons;
import ui.base.MenuSubscene;

public class Menu {

	public static final int HEIGHT = 900;
	public static final int WIDTH = 1600;
	private AnchorPane mainPane;
	private Scene mainScene;
	public static Stage mainStage;

	private final static int MENU_BUTTONS_START_X = 200;
	private final static int MENU_BUTTONS_START_Y = 650;
	private List<MenuButtons> menuButtons;

	public static MenuSubscene howtoplaySubscene;
	public static CharacterSelectionSubscene characterSelectionSubscene;

	public Menu() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setTitle("Flying UFO");
		mainStage.setScene(mainScene);
		Resloader.Load();
		addGameLogo();
		createButton();
		createHowtoplaySubscene();
		createCharacterSubscene();
		createBackground();
	}

	private void addGameLogo() {
		Resloader.logoImg.setLayoutX(340);
		Resloader.logoImg.setLayoutY(80);
		Resloader.logoImg.setFitHeight(350);
		Resloader.logoImg.setFitWidth(650);
		
		
		mainPane.getChildren().add(Resloader.logoImg);
	}

	private void addMenuButton(MenuButtons button) {
		button.setLayoutX(MENU_BUTTONS_START_X + menuButtons.size() * 500);
		button.setLayoutY(MENU_BUTTONS_START_Y );
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}

	private void createButton() {
		createHowtoplayButton();
		createStartButton();
		createExitButton();
	}

	private void createStartButton() {
		MenuButtons startButton = new MenuButtons("PLAY");
		addMenuButton(startButton);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				characterSelectionSubscene.moveSubScene();
				
				System.out.println("Game Start!");
				
				if(!howtoplaySubscene.isHidden()) {
					howtoplaySubscene.moveSubScene();
				}
				
			}
		});
	}

	private void createHowtoplayButton() {
		MenuButtons htp = new MenuButtons("TIPS");
		addMenuButton(htp);
		
		htp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				howtoplaySubscene.moveSubScene();
				if(!characterSelectionSubscene.isHidden()) {
					characterSelectionSubscene.moveSubScene();
				}
			}
		});
		
	}

	private void createExitButton() {
		MenuButtons exitButton = new MenuButtons("EXIT!!");
		addMenuButton(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Exit");
				System.exit(0);
			}
		});
	}

	

	private void createHowtoplaySubscene() {
		howtoplaySubscene = new HowToSubscene(600, 600);
		mainPane.getChildren().add(howtoplaySubscene);
	}
	
	private void createCharacterSubscene() {
		characterSelectionSubscene = new CharacterSelectionSubscene();
		mainPane.getChildren().add(characterSelectionSubscene);
	}

	private void createBackground() {
		Image backgroundImage = Resloader.bgMenuImg;
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
		mainPane.setBackground(new Background(background));
	}
	

}
