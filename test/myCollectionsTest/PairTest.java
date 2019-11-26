package myCollectionsTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import myCollections.Pair;

class PairTest {
	
	private Pair<String, Integer> pair1;
	private Pair<String, Integer> pair2;
	private Pair<String, Integer> pair3;

	
	public void setUpScenary1() {
		
	}
	
	public void setUpScenary2() {
		
		pair1 = new Pair<String, Integer>("Nicolas",21);
		
	}
	
	public void setUpScenary3() {
		
		pair1 = new Pair<String, Integer>("A",10002);
		pair2 = new Pair<String, Integer>("Z",10002);
		pair3 = new Pair<String, Integer>("Z",2324);
		
		
	}

	@Test
	void pairTest() {
		
		setUpScenary1();
		Pair<String, Integer> p = new Pair<String, Integer>("Maria",15);
		
		assertNotNull("The constructor is not working properly", p);
		
	}
	
	@Test
	void getFirstTest() {
		
		setUpScenary2();
		assertTrue("The getter is not working properly", pair1.getFirst().equals("Nicolas"));
		
	}
	
	@Test
	void getSecondTest() {
		
		setUpScenary2();
		assertTrue("The getter is not working properly", pair1.getSecond().equals(21));
		
	}
	
	@Test
	void setFirstTest() {
		
		setUpScenary2();
		pair1.setFirst("Jhon");
		assertTrue("The setter is not working properly", pair1.getFirst().equals("Jhon"));
		
	}
	
	@Test
	void setSecondTest() {
		
		setUpScenary2();
		pair1.setSecond(20);
		assertTrue("The setter is not working properly", pair1.getSecond().equals(20));
		
	}
	
	@Test
	void compareToTest() {
		
		setUpScenary3();
	
		assertTrue("The comparation is not correct", pair1.compareTo(pair2)<0);
		assertTrue("The comparation is not correct", pair2.compareTo(pair1)>0);
		assertTrue("The comparation is not correct", pair3.compareTo(pair2)==0);
		
	}

}



