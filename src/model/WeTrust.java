/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import myCollections.AdjacencyMatrixGraph;
import myCollections.IGraph;

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
			String name = info[0];
			String lastName = info[1];
			int id = Integer.parseInt(info[2]);
			int zone = Integer.parseInt(info[3]);

			employees.add((new Employee(name, lastName, id, zone)));
			line = br.readLine();
		}
		br.close();
	}
	
	public void chooseS(boolean which) {
		if(which) {
			employeesTrust = new AdjacencyMatrixGraph<Employee>();
		}
	}
	
	public void loademployeesTrustM() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("data/trust.txt")));
		String line = br.readLine();
		line = br.readLine();
		while(line != null) {
			String[] info = line.split(",");
			int x = Integer.parseInt(info[0]);
			int y = Integer.parseInt(info[1]);
			int trust = Integer.parseInt(info[2]);
			Employee e1 = employees.get(x);
			Employee e2 = employees.get(y);
			
			employeesTrust.addEdge(e1, e2, trust);
			
			line = br.readLine();
		}
		br.close();
	}
	
	public void recorrer(double[][] m) {
		for (int x=0; x < m.length; x++) {
			System.out.print("|");
			for (int y=0; y < m[x].length; y++) {
				if(m[x][y] == Integer.MAX_VALUE) {
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
//	public double[][] getemployeesTrustM(){
//		return employeesTrustM.weightMatrix();
//	}
	
}
