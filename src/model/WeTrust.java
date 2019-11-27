/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import myCollections.AdjacencyListGraph;
//import myCollections.AdjacencyListGraph;
import myCollections.AdjacencyMatrixGraph;
import myCollections.IGraph;
import myExceptions.EmployeeAlreadyCreatedException;
import myExceptions.EmployeeNotRegisteredException;

/**
 * Class WeTrust
 * @author Mateo Loaiza
 * @author Nicolas Penagos
 * @author Juan Jose Calderon
 * 
 *
 */
public class WeTrust{

	private IGraph<Employee> employeesTrust; 
	//	private AdjacencyListGraph<Employee> employeesTrustL; 
	private ArrayList<Employee> employees;
	private boolean whichStructure; 


	public WeTrust(boolean which, int tam) {
		chooseS(which,tam);
	}

	public void loadEmployees() throws IOException, EmployeeAlreadyCreatedException {
		BufferedReader br = new BufferedReader(new FileReader(new File("data/employees.txt")));

		employees = new ArrayList<Employee>();
		String line = br.readLine();
		while(line != null) {
			String[] info = line.split(",");
			String name = info[1];
			String lastName = info[2];
			int id = Integer.parseInt(info[0]);

			employees.add((new Employee(id, name, lastName)));
			line = br.readLine();
		}
		br.close();
	}

	public void chooseS(boolean which, int tam) {
		if(which) {
			whichStructure = true;
			employeesTrust = new AdjacencyMatrixGraph<Employee>(tam);
		}else {
			whichStructure = false;
			employeesTrust = new AdjacencyListGraph<Employee>();
		}
	}

	public void generateEmployees(int amount) throws IOException, EmployeeAlreadyCreatedException {
		int times = 0;
		PrintWriter pr = new PrintWriter(new File("data/generated.txt"));

		while(times < amount) {
			String employee = employees.get(times).toString();
			Employee e1 = employees.get(times);
			employeesTrust.addVertex(e1);
			pr.write(employee + "\n");

			times++;
		}
		pr.close();
	}

	public void generateEmployeesTrust() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("data/generated.txt")));
		PrintWriter pr1 = new PrintWriter(new File("data/trust.txt"));
		double weight = Math.random()*900;
		String line = br.readLine();
		int counter = 0;
		while(line != null) {
			counter++;
			line = br.readLine();
		}
		Random ran = new Random();
		int id1 = ran.nextInt(counter+1);
		int id2 = ran.nextInt(id1+1);
		for(int i = 0; i < counter*10; i++) {
			if(id1 != id2) {
				if(weight > 500) {
					weight = Integer.MAX_VALUE;
				}
				pr1.write(id1 + "," + id2 + "," + weight +"\n");
				weight = Math.random()*900+100;	
			}else {

			}
			id1 = ran.nextInt(counter);
			id2 = ran.nextInt(id1+1);
		}
		pr1.close();
		br.close();
	}

	public void loademployeesTrust() throws IOException, EmployeeNotRegisteredException {
		BufferedReader br = new BufferedReader(new FileReader(new File("data/trust.txt")));
		String line = br.readLine();
		line = br.readLine();
		while(line != null) {
			String[] info = line.split(",");
			int x = Integer.parseInt(info[0]);
			int y = Integer.parseInt(info[1]);
			double trust = Math.round((Double.parseDouble((info[2]))));


			Employee e1 = employees.get(x);
			Employee e2 = employees.get(y);


			employeesTrust.addEdge(e1, e2, trust);

			line = br.readLine();
		}
		br.close();
	}

	public void showMatrix(double[][] m) {
		for (int x=0; x < m.length; x++) {
			System.out.print("|");
			for (int y=0; y < m[x].length; y++) {
				if(m[x][y] == Integer.MAX_VALUE) {
					System.out.print("MAX");
				}
				else {
					System.out.print(m[x][y]);
				}
				if (y!=m[x].length-1) System.out.print("\t");
			}
			System.out.println("|");
		}
	} 

	public ArrayList<Employee> getEmployees(){
		return employees;
	}

	public Employee searchEmployee(int id) {
		return searchEmployee(id, 0, employees.size());
	}

	public Employee searchEmployee(int id, int i, int j) {
		if(j>=i) {
			int mid = i + (j-i)/2;
			Employee currentE = employees.get(mid);

			if(currentE.getId()==id) {
				return currentE;
			}else if(currentE.getId()>id) {
				return searchEmployee(id, i, mid-1);
			}else {
				return searchEmployee(id, mid+1, j);
			}
		}else {
			return null;
		}
	}

	public void setMatrix(double[][] m) {
		((AdjacencyMatrixGraph<Employee>) employeesTrust).setMatrix(m);
	}
	public double[][] getemployeesTrust(){
		if(whichStructure)
			return ((AdjacencyMatrixGraph<Employee>) employeesTrust).getMatrix();
		else
			((AdjacencyListGraph<Employee>)employeesTrust).makeWeightsMatrix();
		return ((AdjacencyListGraph<Employee>)employeesTrust).getWeightsMatrix();
	}
	public String getEmployeesTrustL() {
		return ((AdjacencyListGraph<Employee>) employeesTrust).toString();
	}


	public ArrayList<Employee> getBFS(Employee e) {
		if(whichStructure)
			return ((AdjacencyMatrixGraph<Employee>) employeesTrust).BFS(e);
		else
			return ((AdjacencyListGraph<Employee>)employeesTrust).BFS(e);
	}

	public AdjacencyMatrixGraph<Employee> getGraphMatrix(){
		return (AdjacencyMatrixGraph<Employee>) employeesTrust;
	}

	public ArrayList<Employee> getDFS(Employee e) {
		if(whichStructure)
			return ((AdjacencyMatrixGraph<Employee>) employeesTrust).DFS(e);
		else
			return ((AdjacencyListGraph<Employee>)employeesTrust).DFS(e);
	}	

	public double[][] FloydWarshall(){
		double[][] matrix = getemployeesTrust();
		if(whichStructure)
			return (((AdjacencyMatrixGraph<Employee>) employeesTrust).FloydWarshall(matrix));
		else
			return ((AdjacencyListGraph<Employee>)employeesTrust).FloydWarshall(matrix);
	}
	public double[][] Kruskal(){
		if(whichStructure)
			return (((AdjacencyMatrixGraph<Employee>) employeesTrust).Kruskal(getemployeesTrust()));
		else
			return ((AdjacencyListGraph<Employee>)employeesTrust).Kruskal(getemployeesTrust());
	}

	public ArrayList<Employee> adyacents(Employee employee) {
		return (ArrayList<Employee>) ((AdjacencyMatrixGraph<Employee>) employeesTrust).adjacents(employee);
	}

	public Employee getBestOption(Employee des) {
		Employee best = null;
		double[][] matrix;
		int desId = des.getId();
		matrix = FloydWarshall();
		

		double min = Integer.MAX_VALUE;
		int minId = Integer.MAX_VALUE;

		for(int i = 0; i < matrix[desId].length;i++) {
			if(min > matrix[desId][i] && i != desId) {
				min = matrix[desId][i];
				minId = i;
			}
		}
		if(minId == Integer.MAX_VALUE) return null;
		best = searchEmployee(minId);
		return best;
	}

	public Employee getWorstOption(Employee des) {
		Employee worst = null;
		double[][] matrix;
		int desId = des.getId();
	
		matrix = FloydWarshall();


		double max = 0.0;
		int maxId = Integer.MAX_VALUE;

		for(int i = 0; i < matrix[desId].length;i++) {
			if(max < matrix[desId][i] && i != desId) {
				max = matrix[desId][i];
				maxId = i;
			}
		}
		if(maxId == Integer.MAX_VALUE) return null;
		worst = searchEmployee(maxId);
		return worst;
	}
	public String getBestCommunication() {
		String msg = "";
		double[][] matrix = Kruskal();
		int counter = 0;
		for(int i = 0; i<matrix.length;i++) {
			for(int j = i; j < matrix[0].length;j++) {
				if(matrix[i][j] != 0 && matrix[i][j] < Integer.MAX_VALUE) {
					counter++;
					msg += "\n" + counter+ ") " + searchEmployee(i) + " --------- " + searchEmployee(j);
				}
			}
		}
		return msg;
	}

}
