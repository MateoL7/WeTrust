/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
public class WeTrust {
	
	private IGraph<Employee> employeesTrust; 
//	private AdjacencyListGraph<Employee> employeesTrustL; 
	private ArrayList<Employee> employees;
	
	public WeTrust() throws IOException {
		loadEmployees();
	}
	
	public void loadEmployees() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("data/employees.txt")));
		
		employees = new ArrayList<Employee>();
		String line = br.readLine();
		line = br.readLine();
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
			employeesTrust = new AdjacencyMatrixGraph<Employee>();
		}else {
//			employeesTrust = new AdjacencyListGraph<Employee>();
		}
	}
	
	public void generateEmployees(int amount) throws IOException {
		int times = 0;
		PrintWriter pr = new PrintWriter(new File("data/generated.txt"));
		PrintWriter pr1 = new PrintWriter(new File("data/trust.txt"));
		
		int first = (int) Math.random()*amount;
		int second = (int) Math.random()*amount;
		double weight = Math.random()*900;
		
		while(times < amount) {
			String employee = employees.get(times).toString();
			pr.write(employee + "\n");
			
			pr1.write(first + "," + second + "," + weight + "\n");
			
			first = (int) Math.random()*amount + first+1;
			second = (int) Math.random()*amount + second+1;
			weight = Math.random()*900;
			
			times++;
		}
		pr.close();
		pr1.close();
		
	}
	
	public void loademployeesTrustM() throws IOException, EmployeeAlreadyCreatedException, EmployeeNotRegisteredException {
		BufferedReader br = new BufferedReader(new FileReader(new File("data/trust.txt")));
		String line = br.readLine();
		line = br.readLine();
		while(line != null) {
			String[] info = line.split(",");
			int x = Integer.parseInt(info[0]);
			int y = Integer.parseInt(info[1]);
			double trust = Math.round((Double.parseDouble((info[2])))*(10^2));
			Employee e1 = employees.get(x);
			Employee e2 = employees.get(y);
			
			
			employeesTrust.addVertex(e1);
			employeesTrust.addVertex(e2);
			employeesTrust.addEdge(e1, e2, trust);
			
			line = br.readLine();
		}
		br.close();
	}
	
	public void recorrer(double[][] m) {
		for (int x=0; x < m.length; x++) {
			System.out.print("|");
			for (int y=0; y < m[x].length; y++) {
				if(m[x][y] == 900) {
					System.out.print("$");
				}else {
					System.out.print (m[x][y]);	
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
    public double[][] getemployeesTrustM(){
		return ((AdjacencyMatrixGraph<Employee>) employeesTrust).getMatrix();	
	}
	

}
