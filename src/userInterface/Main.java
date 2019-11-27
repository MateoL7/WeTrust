package userInterface;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import myCollections.AdjacencyListGraph;
import myCollections.AdjacencyMatrixGraph;
import myExceptions.EmployeeAlreadyCreatedException;
import myExceptions.EmployeeNotRegisteredException;


public class Main extends Application{
	@Override
	public void start(Stage stage) throws IOException, EmployeeAlreadyCreatedException, EmployeeNotRegisteredException {


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

