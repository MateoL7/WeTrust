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

		AdjacencyMatrixGraph<Character> g = new AdjacencyMatrixGraph<Character>();
		
		/*
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addVertex('E');
		g.addEdge('B', 'D', 3.0);
		g.addEdge('B', 'E', 4.0);
		g.addEdge('D', 'E', 8.0);
		g.addEdge('D', 'C', 7.0);
		g.addEdge('C', 'E', 2.0);
		g.addEdge('C', 'A', 4.0);
		g.addEdge('A', 'E', 3.0);
		
		double[][] w = g.FloydWarshall(g.getWeightMatrix()); 
		
		for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w.length; j++) {
				System.out.print(w[i][j]+" "); //RESPUESTA CORRECTA YA LA VERIFIQUE imprimir respuesta
			}
			System.out.println();
		}
		
		*/
		

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

