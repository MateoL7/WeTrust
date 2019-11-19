package userInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Employee;
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



			int num = 5;

			w.chooseS(true,num);
			w.loadEmployees();
			w.generateEmployees(num);
			w.generateEmployeesTrust();
			w.loademployeesTrust();


			System.out.println("Graph");
			w.showMatrix(w.getemployeesTrust());
			System.out.println("-------------------------------------------------");
			System.out.println("Floyd-Warshall");
			w.showMatrix(w.FloydWarshall());
			System.out.println("-------------------------------------------------");
			System.out.println("BFS");
			ArrayList<Employee> BFS = w.getBFS(w.getEmployees().get(0));
			
			System.out.println(Arrays.toString(BFS.toArray()));
			
			
		}catch(IOException e) {
			System.out.println("Not found");
		} catch (EmployeeAlreadyCreatedException e1) {
			e1.printStackTrace();
		}
			catch (EmployeeNotRegisteredException e) {
			e.printStackTrace();
		}

		AdjacencyListGraph<Integer> g = new AdjacencyListGraph<Integer>(false);
		g.addVertex(1);
		g.addVertex(2);
		g.addVertex(3);
		g.addVertex(4);
		g.addVertex(5);
		
		g.addEdge(1, 3, 4.0);
		g.addEdge(1, 5, 3.0);
		g.addEdge(3, 5, 2.0);
		g.addEdge(4, 3, 7.0);
		g.addEdge(4, 5, 8.0);
		g.addEdge(4, 2, 3.0);
		g.addEdge(2, 5, 4.0);
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
