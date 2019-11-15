package userInterface;

import java.io.IOException;
import java.util.Arrays;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.WeTrust;
//import myCollections.AdjacencyListGraph;
import myCollections.AdjacencyMatrixGraph;

public class Main extends Application{

	public static void main(String[] args){

		try {

			WeTrust w = new WeTrust();	

			//		    AdjacencyMatrixGraph<Integer> g1 = new AdjacencyMatrixGraph<>();
			//			g1.addVertex(1);
			//			g1.addVertex(2);
			//			g1.addVertex(3);
			//			g1.addEdge(1, 1, 5);


			w.chooseS(true);
			
			w.generateEmployees(3);
			
			w.loademployeesTrustM();
			w.recorrer(w.getemployeesTrustM());


		}catch(IOException e) {
			System.out.println("Not found");
		}
		//		
		//		AdjacencyListGraph<Integer> g = new AdjacencyListGraph<Integer>(false);
		//		g.addVertex(1);
		//		g.addVertex(2);
		//		g.addVertex(3);
		//		
		//		g.addEdge(1, 2, 5);
		//		
		//		
		//		
		//		
		//	
		//		System.out.println(g.printGrahp());
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



















/*
 * for(Map.Entry<key,value> obj: mapName.entrySet()){
 * }
 * 
 * 
 * */
