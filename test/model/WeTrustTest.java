package model;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


import model.Employee;
import model.WeTrust;
import myExceptions.EmployeeAlreadyCreatedException;
import myExceptions.EmployeeNotRegisteredException;

public class WeTrustTest {

	//Attributes
	private WeTrust w;



	//Variables
	double[][] test;
	ArrayList<Employee> BFSExpected;
	ArrayList<Employee> DFSExpected;
	double[][] floydExpected;
	double[][] kruskalExpected;


	//Scenarios
	public void scenary1() {
		w = new WeTrust(true, 0);
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
	public void testWeTrust() {
		scenary1();
		assertNotNull("The object is not created", w);
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
	@Test 
	public void testBestOption() {
		scenary1();
		scenary2();
		scenary5();
		Employee bestExpected = new Employee(1, "Kirbee", "Reeman");
		Employee result = w.getBestOption(w.getEmployees().get(0));
		assertTrue("Not the right value", result.getId() == bestExpected.getId());
		assertTrue("Not the right value", result.getName().equalsIgnoreCase(bestExpected.getName()));
		assertTrue("Not the right value", result.getLastName().equalsIgnoreCase(bestExpected.getLastName()));
	}
	@Test 
	public void testWorstOption() {
		scenary1();
		scenary2();
		scenary5();
		Employee worstExpected = new Employee(3, "Brynna", "Edwinson");
		Employee result = w.getWorstOption(w.getEmployees().get(0));
		assertTrue("Not the right value", result.getId() == worstExpected.getId());
		assertTrue("Not the right value", result.getName().equalsIgnoreCase(worstExpected.getName()));
		assertTrue("Not the right value", result.getLastName().equalsIgnoreCase(worstExpected.getLastName()));
	}
	@Test
	public void testGetBestCommunication() {
		scenary1();
		scenary2();
		scenary5();
		String msgExpected = "\n1) Id: 0 Name: Juan Reyes --------- Id: 1 Name: Kirbee Reeman\n"+
				"2) Id: 1 Name: Kirbee Reeman --------- Id: 2 Name: Brandi Lepard\n" + 
				"3) Id: 1 Name: Kirbee Reeman --------- Id: 4 Name: Pennie Sandeson\n" + 
				"4) Id: 3 Name: Brynna Edwinson --------- Id: 4 Name: Pennie Sandeson";
		String result = w.getBestCommunication();
		assertTrue("Not the right message", result.equalsIgnoreCase(msgExpected));
	}

}
