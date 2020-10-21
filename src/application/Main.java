package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Menu;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			new Menu();
			primaryStage = Menu.mainStage;
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
