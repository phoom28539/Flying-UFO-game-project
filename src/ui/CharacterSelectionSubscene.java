package ui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import resloader.Resloader;
import ui.base.MenuButtons;
import ui.base.MenuSubscene;
import view.Game;


public class CharacterSelectionSubscene extends MenuSubscene{
	
	public static int chaSel;
	private ImageView blue;
	private ImageView pink;
	
	public CharacterSelectionSubscene() {
		super(600, 600);
		Label label = new Label("Select Character");
		label.setFont(Font.loadFont(ClassLoader.getSystemResource("kenvector_future.ttf").toString(),30));
		label.setLayoutX(122);
		label.setLayoutY(75);

		
		MenuButtons bluebutton = new MenuButtons("BLUE");
		bluebutton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Game game = new Game();
				chaSel=0;
				System.out.println("Play");
				game.createNewGame();
			}
		});
		bluebutton.setLayoutX(90);
		bluebutton.setLayoutY(400);
		
		blue = Resloader.blueShip;
		blue.setLayoutX(125);
		blue.setLayoutY(200);
		
		
		MenuButtons pinkbutton = new MenuButtons("PINK");
		pinkbutton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Game game = new Game();
				chaSel=1;
				System.out.println("Play");
				game.createNewGame();
			}
		});
		pinkbutton.setLayoutX(320);
		pinkbutton.setLayoutY(400);
		
		pink = Resloader.pinkShip;
		pink.setLayoutX(350);
		pink.setLayoutY(210);
		
		createBackButton();
		
		this.getPane().getChildren().addAll(blue,bluebutton,pink,pinkbutton,label);
	}
	
	private MenuButtons createBackButton() {
		MenuButtons backButton = new MenuButtons(" X");
		backButton.setPrefSize(50, 50);
		backButton.setAlignment(Pos.CENTER);
		backButton.setLayoutX(550);
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				moveSubScene();
			}
		});
		this.getPane().getChildren().add(backButton);
		return backButton;
	}
	
	
	
}