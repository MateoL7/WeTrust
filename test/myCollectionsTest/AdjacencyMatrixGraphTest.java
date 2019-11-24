package myCollectionsTest;

import static org.junit.Assert.assertTrue;


import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Employee;
import model.WeTrust;
import myExceptions.EmployeeAlreadyCreatedException;
import myExceptions.EmployeeNotRegisteredException;

class AdjacencyMatrixGraphTest {

	double[][] test;
	ArrayList<Employee> BFSExpected;
	ArrayList<Employee> DFSExpected;
	private WeTrust w;

	public void scenary1() {
		w = new WeTrust();
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

}
