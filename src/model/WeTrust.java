/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import myCollections.MatrixGraphEmployees;

/**
 * Class WeTrust
 * @author Mateo Loaiza
 * @author Nicolas Penagos
 * @author Juan Jose Calderon
 * 
 *
 */
public class WeTrust {

	public final static int ZONE1 = 1;
	public final static int ZONE2 = 2;
	public final static int ZONE3 = 3;
	public final static int ZONE4 = 4;
	public final static int ZONE5 = 5;
	
	private MatrixGraphEmployees employeesTrust; 
	private ArrayList<Employee> employees;
	
	public WeTrust() throws IOException {
		loadEmployees();
	}
	
	public void loadEmployees() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("data/employees.txt")));
		employeesTrust = new MatrixGraphEmployees<Employee>(25);
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
	public ArrayList<Employee> getEmployees(){
		return employees;
	}
	
}
