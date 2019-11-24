package model;
/**
 * Class Employee
 * @author Mateo Loaiza
 * @author Nicolas Penagos
 * @author Juan Jose Calderon
 * 
 *
 */
public class Employee implements Comparable<Employee>{
	
	private String name;
	private String lastName;
	private int id;
	
	public Employee(int id, String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return id + "," + name + "," + lastName;
	}

	@Override
	public int compareTo(Employee o) {
		if(id > o.getId()) {
			return 1;
		}
		if(id < o.getId()) {
			return -1;
		}
		return 0;
	}
	
}
