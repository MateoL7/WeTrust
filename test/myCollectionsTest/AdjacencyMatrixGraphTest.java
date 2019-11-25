package myCollectionsTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Employee;
import model.WeTrust;
import myCollections.AdjacencyMatrixGraph;
import myExceptions.EmployeeAlreadyCreatedException;
import myExceptions.EmployeeNotRegisteredException;

public class AdjacencyMatrixGraphTest {

	//Attributes
	private AdjacencyMatrixGraph<Employee> adjMatrix;
	private WeTrust w;



	//Variables
	double[][] test;
	ArrayList<Employee> BFSExpected;
	ArrayList<Employee> DFSExpected;
	double[][] floydExpected;
	double[][] kruskalExpected;


	//Scenarios
	public void scenary1() {
		w = new WeTrust(true, 3);
		adjMatrix = new AdjacencyMatrixGraph<Employee>();
	}

	public void scenary2() {
		w.chooseS(true, 0);
	}
	public void scenary3() {
		try {


			int num = 5;

			w.chooseS(true,num);
			w.loadEmployees();
			w.generateEmployees(num);
			w.generateEmployeesTrust();
			w.loademployeesTrust();

			double [][] test2 =
				{{0.0,1.0,900.0,1.0,1.0},
						{1.0,0.0,1.0,900.0,1.0},
						{900.0,1.0,900.0,900.0,900.0},
						{1.0,900.0,900.0,0.0,1.0},
						{1.0,1.0,900.0,1.0,0.0}};
			test = test2;
			w.setMatrix(test);


			BFSExpected = new ArrayList<Employee>();
			BFSExpected.add(new Employee(0, "Juan", "Reyes"));
			BFSExpected.add(new Employee(1, "Kirbee", "Reeman"));
			BFSExpected.add(new Employee(3, "Brynna", "Edwinson"));
			BFSExpected.add(new Employee(4, "Pennie", "Sandeson"));
			BFSExpected.add(new Employee(2, "Brandi", "Lepard"));
		}catch(IOException | EmployeeAlreadyCreatedException e1) {

		} catch (EmployeeNotRegisteredException e) {
			e.printStackTrace();
		}

	}
	public void scenary4() {
		try {


			int num = 5;

			w.chooseS(true,num);
			w.loadEmployees();
			w.generateEmployees(num);
			w.generateEmployeesTrust();
			w.loademployeesTrust();

			double [][] test2 =
				{{0.0,1.0,900.0,1.0,1.0},
						{1.0,0.0,1.0,900.0,1.0},
						{900.0,1.0,900.0,900.0,900.0},
						{1.0,900.0,900.0,0.0,1.0},
						{1.0,1.0,900.0,1.0,0.0}};
			test = test2;
			w.setMatrix(test);


			DFSExpected = new ArrayList<Employee>();
			DFSExpected.add(new Employee(0, "Juan", "Reyes"));
			DFSExpected.add(new Employee(1, "Kirbee", "Reeman"));
			DFSExpected.add(new Employee(2, "Brandi", "Lepard"));
			DFSExpected.add(new Employee(4, "Pennie", "Sandeson"));
			DFSExpected.add(new Employee(3, "Brynna", "Edwinson"));

		}catch(IOException | EmployeeAlreadyCreatedException e1) {

		} catch (EmployeeNotRegisteredException e) {
			e.printStackTrace();
		}
	}
	public void scenary5() {
		try {


			int num = 5;

			w.chooseS(true,num);
			w.loadEmployees();
			w.generateEmployees(num);
			w.generateEmployeesTrust();
			w.loademployeesTrust();

			double[][] test2 = 
				{{0.0,100.0,900.0,110.0,120.0},
						{100.0,0.0,1.0,900.0,1.0},
						{900.0,1.0,900.0,900.0,900.0},
						{110.0,900.0,900.0,0.0,1.0},
						{120.0,1.0,900.0,1.0,0.0}};
			test = test2;
			w.setMatrix(test);

			double[][] expected = 
				{{0.0,100.0,101.0,102.0,101.0},
						{100.0,0.0,1.0,2.0,1.0},
						{101.0,1.0,0.0,3.0,2.0},
						{102.0,2.0,3.0,0.0,1.0},
						{101.0,1.0,2.0,1.0,0.0}};
			double[][] expected2 = 
				{{0.0,100.0,0.0,0.0,0.0},
						{100.0,0.0,1.0,0.0,1.0},
						{0.0,1.0,0.0,0.0,0.0},
						{0.0,0.0,0.0,0.0,1.0},
						{0.0,1.0,0.0,1.0,0.0}};
			floydExpected = expected;
			kruskalExpected = expected2;
			
		}catch(IOException | EmployeeAlreadyCreatedException e1) {

		} catch (EmployeeNotRegisteredException e) {
			e.printStackTrace();
		}
	}

	//Tests
	
	@Test
	public void testAdjacencyMatrixGraph() {
		scenary1();
		scenary2();
		assertNotNull("The object is not created", adjMatrix);
	}
	@Test
	public void testBFS() {
		scenary1();
		scenary2();
		scenary3();
		ArrayList<Employee> result = w.getBFS(w.getEmployees().get(0));
		for(int i = 0; i < BFSExpected.size();i++) {
			assertTrue("Not the same result",result.get(i).getId() == BFSExpected.get(i).getId());
		}
	}
	@Test
	public void testDFS() {
		scenary1();
		scenary2();
		scenary4();
		ArrayList<Employee> result = w.getDFS(w.getEmployees().get(0));
		for(int i = 0; i < DFSExpected.size();i++) {
			assertTrue("Not the same result",result.get(i).getId() == DFSExpected.get(i).getId());
		}
	}
	@Test
	public void testFloydWarshall() {
		scenary1();
		scenary2();
		scenary5();
		double[][] result = w.FloydWarshall();
		for(int i =0; i < result.length;i++) {
			for(int j = 0; j < result[0].length;j++) {
				assertTrue("Not the right value", result[i][j] == floydExpected[i][j]);
			}
		}
	}
	@Test
	public void testKruskal() {
		scenary1();
		scenary2();
		scenary5();
		double[][] result = w.Kruskal();
		for(int i =0; i < result.length;i++) {
			for(int j = 0; j < result[0].length;j++) {
				assertTrue("Not the right value", result[i][j] == kruskalExpected[i][j]);
			}
		}
	}

}
