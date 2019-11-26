package userInterface;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class Main extends Application{
	@Override
	public void start(Stage stage) throws IOException {


		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		Parent root = fxmlLoader.load();



		Scene scene = new Scene(root);
		stage.setTitle("Login");
		stage.setScene(scene);
		stage.show();
		LoginGUI lc = fxmlLoader.getController();
		lc.setScene(scene);
	}

	public static void main(String[] args){
		
		launch(args);	
	}
}

