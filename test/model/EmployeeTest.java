package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class EmployeeTest {

	private Employee e;
	private Employee e2;
	
	public void  scenary1() {
		e = new Employee(0, "Gabriela", "Arango");
		e2 = new Employee(1, "Gabriela", "Nunez");
	}
	@Test
	public void testEmployee() {
		e = new Employee(0, "Gabriela", "Arango");
		assertNotNull("Is null", e);
		assertTrue("Not the right value attribute", e.getId() == 0);
		assertTrue("Not the right value attribute", e.getName().equalsIgnoreCase("gabriela"));
		assertTrue("Not the right value attribute", e.getLastName().equalsIgnoreCase("arango"));
	}
	@Test
	public void testCompareTo() {
		scenary1();
		assertTrue("Not comparing correctly", e.compareTo(e2) < 0);
	}
	
	@Test
	public void testToString() {
		scenary1();
		assertTrue("Not the right String", e.toString().equalsIgnoreCase("Id: 0 Name: Gabriela Arango"));
	}

}
