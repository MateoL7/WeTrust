package userInterface;

import java.io.IOException;
import java.util.Arrays;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.WeTrust;

public class Main extends Application{
	
	public static void main(String[] args){
		try {
			WeTrust w = new WeTrust();	
			System.out.println(Arrays.toString((w.getEmployees()).toArray()));
		}catch(IOException e) {
			System.out.println("Not found");
		}
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("WeTrustGUI.fxml")); 
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("WeTrust");
		stage.show();
		
	}
	
}
