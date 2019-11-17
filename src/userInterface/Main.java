package userInterface;

import java.io.IOException;
import java.util.Arrays;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.WeTrust;
import myCollections.AdjacencyListGraph;
//import myCollections.AdjacencyListGraph;
import myCollections.AdjacencyMatrixGraph;
import myExceptions.EmployeeAlreadyCreatedException;
import myExceptions.EmployeeNotRegisteredException;

public class Main extends Application{

	public static void main(String[] args){

		try {

			WeTrust w = new WeTrust();	

			

			int num = 3;
			
			w.chooseS(true,num);
			
			w.generateEmployees(num);
			
			try {
				w.loademployeesTrustM();
			} catch (EmployeeAlreadyCreatedException | EmployeeNotRegisteredException e) {
				
				e.printStackTrace();
			}
			w.recorrer(w.getemployeesTrustM());


		}catch(IOException e) {
			System.out.println("Not found");
		}
		
		AdjacencyListGraph<Integer> g = new AdjacencyListGraph<Integer>(false);
		g.addVertex(1);
		g.addVertex(2);
		g.addVertex(3);
		
		g.addEdge(1, 2, 5.0);
		//g.removeVertex(2);
		//g.removeEdge(1, 2);
		
		System.out.println(g.toString());
		System.out.println();
		System.out.println();
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
