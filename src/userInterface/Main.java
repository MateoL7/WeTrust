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
import myCollections.AdjacencyMatrixGraph;
import myExceptions.EmployeeAlreadyCreatedException;
import myExceptions.EmployeeNotRegisteredException;


public class Main extends Application{
//public class Main{

		@Override
		public void start(Stage stage) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("WeTrustGUI.fxml")); 
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("WeTrust");
			stage.show();
		}

//		public static void main(String[] args) {
//			launch(args);
//		}
	/*
     	AdjacencyListGraph<Integer> g = new AdjacencyListGraph<Integer>(false);
		g.addVertex(1);
	    g.addVertex(2);
	    g.addVertex(3);
	    g.addVertex(4);
	    g.addVertex(5);
	//		
	    g.addEdge(1, 2, 17.0);
	    g.addEdge(3, 4, 23.0);

	    System.out.println(g.toString());

	    g.makeWeightsMatrix();
	    double[][] w = g.getWeightsMatrix();
	    double[][] ww = g.Kruskal(w, Integer.MAX_VALUE);

	    for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w.length; j++) {
				System.out.print(""+w[i][j]+"   ");
			}
			System.out.println();
		}

	    for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w.length; j++) {
				System.out.print(""+ww[i][j]+"   ");
			}
			System.out.println();
		}

	}

	 */
	public static void main(String[] args){
		try {

			



			int num = 5;
			WeTrust w = new WeTrust(true, num);	

			w.loadEmployees();
			w.generateEmployees(num);
			w.generateEmployeesTrust();
			w.loademployeesTrust();

			double[][] test = 
						{{0.0,100.0,900.0,110.0,120.0},
						{100.0,0.0,1.0,900.0,1.0},
						{900.0,1.0,900.0,900.0,900.0},
						{110.0,900.0,900.0,0.0,1.0},
						{120.0,1.0,900.0,1.0,0.0}};

			w.setMatrix(test);
			
			System.out.println(Arrays.toString((w.adyacents(w.getEmployees().get(0)).toArray())));
			
			System.out.println("Graph");
			w.showMatrix(w.getemployeesTrust());
			System.out.println("-------------------------------------------------");
			System.out.println("BFS");
			ArrayList<Employee> BFS = w.getBFS(w.getEmployees().get(0));
			System.out.println(Arrays.toString(BFS.toArray()));
			System.out.println("-------------------------------------------------");
			System.out.println("DFS");
			ArrayList<Employee> DFS = w.getDFS(w.getEmployees().get(0));
			System.out.println(Arrays.toString(DFS.toArray()));	
			System.out.println("-------------------------------------------------");
			System.out.println("Floyd-Warshall");
			w.showMatrix(w.FloydWarshall());
			System.out.println("-------------------------------------------------");
			System.out.println("Best option for " + w.getEmployees().get(0));
			System.out.println(w.getBestOption(w.getEmployees().get(0)));
			
			System.out.println("-------------------------------------------------");
			System.out.println("Best employees com");
			System.out.println(w.getBestCommunication());
			System.out.println("-------------------------------------------------");
			System.out.println("Kruskal");
			w.showMatrix(w.Kruskal());

		}catch(IOException e) {
			System.out.println("Not found");
		} catch (EmployeeAlreadyCreatedException e1) {
			e1.printStackTrace();
		}
		catch (EmployeeNotRegisteredException e) {
			e.printStackTrace();
		}
//
//				AdjacencyListGraph<Integer> g = new AdjacencyListGraph<Integer>(false);
//				g.addVertex(1);
//				g.addVertex(2);
//				g.addVertex(3);
//				g.addVertex(4);
//				g.addVertex(5);
//				
//				g.addEdge(1, 3, 4.0);
//				g.addEdge(1, 5, 3.0);
//				g.addEdge(3, 5, 2.0);
//				g.addEdge(4, 3, 7.0);
//				g.addEdge(4, 5, 8.0);
//				g.addEdge(4, 2, 3.0);
//				g.addEdge(2, 5, 4.0);
//				System.out.println(g.toString());
//				System.out.println();
//				System.out.println();
			launch(args);
//		
//		
	}
}




















/*
 * for(Map.Entry<key,value> obj: mapName.entrySet()){
 * }
 * 
 * 
 * */
