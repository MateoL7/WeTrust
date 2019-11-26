package myCollectionsTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import myCollections.AdjacencyListGraph;

class AdjacencyListGraphTest {
	
	AdjacencyListGraph<Character> g;
	
	public void setUpScenary1() {
		
	}
	
	public void setUpScenary2() {
		
		g = new AdjacencyListGraph<Character>(false);
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addVertex('E');
		g.addEdge('B', 'D', 3.0);
		g.addEdge('B', 'E', 4.0);
		g.addEdge('D', 'E', 8.0);
		g.addEdge('D', 'C', 7.0);
		g.addEdge('C', 'E', 2.0);
		g.addEdge('C', 'A', 4.0);
		g.addEdge('A', 'E', 3.0);
		
	}
	
	public void setUpScenary3() {
		
		g = new AdjacencyListGraph<Character>(false);
		
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addVertex('E');
		g.addVertex('F');
		
		g.addEdge('A', 'C', 128.0);
		g.addEdge('A', 'D', 74.0);
		g.addEdge('A', 'F', 157.0);
		g.addEdge('B', 'C', 110.0);
		g.addEdge('B', 'E', 145.0);
		g.addEdge('C', 'E', 232.0);
		g.addEdge('C', 'D', 310.0);
		g.addEdge('D', 'F', 88.0);
		g.addEdge('E', 'F', 212.0);
		
	}
	
	@Test 
	void adjacencyListGraphTest() {
		g = new AdjacencyListGraph<Character>(false);
		assertNotNull("The constructor is not workin", g);
	}
	@Test
	void addVertexTest() {
		
		g = new AdjacencyListGraph<Character>(false);
		assertTrue("The method does not initialize the adjacencyLists correctly", g.getAdjacencyList().size()==0);
		g.addVertex('A');
		assertTrue("The method does not add the vertex to adjacencyLists correctly", g.getAdjacencyList().size()==1);
		assertTrue("The method does not add the vertex to adjacencyLists correctly", g.getAdjacencyList().get(0).getFirst()=='A');
		
		
	}
	
	@Test
	void removeVertexTest() {
		g = new AdjacencyListGraph<Character>(false);
		g.addVertex('A');
		g.removeVertex('A');
		assertTrue("The method does not remove the vertex from the adjacencyLists correctly", g.getAdjacencyList().size()==0);
	}
	
	@Test
	void addEgdeTest() {
		
		g = new AdjacencyListGraph<Character>(false);
		g.addVertex('A');
		g.addVertex('B');
		g.addEdge('A', 'B', 500.0);
		
		assertTrue("The method does not add the egde from a given vertex to another correctly", g.getAdjacencyList().get(0).getSecond().get(0).getFirst()=='B');
		assertTrue("The method does not add the egde from a given vertex to another correctly", g.getAdjacencyList().get(1).getSecond().get(0).getFirst()=='A');
		
	}
	
	@Test
	void removeEgdeTest() {
		
		g = new AdjacencyListGraph<Character>(false);
		g.addVertex('A');
		g.addVertex('B');
		g.addEdge('A', 'B', 500.0);
		g.removeEdge('A', 'B');
		
		assertTrue("The method does not remove the egde from the adjacencyLists correctly", g.getAdjacencyList().get(0).getSecond().size()==0);
		assertTrue("The method does not remove the egde from the adjacencyLists correctly", g.getAdjacencyList().get(1).getSecond().size()==0);
		
	}
	
	@Test
	void areConnectedTest() {
		
		g = new AdjacencyListGraph<Character>(false);
		g.addVertex('A');
		g.addVertex('B');
		g.addEdge('A', 'B', 500.0);
		
		assertTrue("The method is not working properly", g.areConnected('A', 'B'));
		
	}
	
	@Test
	void searchVertexTest() {
		
		g = new AdjacencyListGraph<Character>(false);
		g.addVertex('A');
		assertTrue("The method is not working properly", g.searchVertex('A'));
		
	}
	
	@Test
	void makeWeightMatrixTest() {
		setUpScenary2();
		
		double[][] w0 = {{0.0,Double.MAX_VALUE,4.0,Double.MAX_VALUE,3.0},{Double.MAX_VALUE,0.0,Double.MAX_VALUE,3.0,4.0},{4.0,Double.MAX_VALUE, 0.0,7.0,2.0},{Double.MAX_VALUE, 3.0,7.0,0.0,8.0},{3.0,4.0,2.0,8.0,0.0}};
		g.makeWeightsMatrix();
		double[][] w = g.getWeightsMatrix();
		
		for (int i = 0; i < w0.length; i++) {
			for (int j = 0; j < w0.length; j++) {
				assertTrue("The method is not working properly", w0[i][j]==w[i][j]);
			}
		}
		
	}

	@Test
	void BFSTest() {
		
		setUpScenary2();
		ArrayList<Character> arrayL = g.BFS('B');
		assertTrue("BFS is not searching in the tree in the correct order", arrayL.toString().equals("[B, D, E, C, A]"));
		
	}
	
	@Test
	void DFSTest() {
		
		setUpScenary2();
		ArrayList<Character> arrayL = g.DFS('B');
		assertTrue("DFS is not searching in the tree in the correct order", arrayL.toString().equals("[B, D, C, A, E]"));
		
	}
	
	@Test
	void KruskalTest() {
		setUpScenary3();
		
		g.makeWeightsMatrix();
		double[][] w = g.Kruskal(g.getWeightsMatrix());
		int sum = 0;
		
		for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w.length; j++) {
				sum+=w[i][j];
			}
		}
		
		sum = sum/2;
	
		assertTrue("The weight of the MST is not correct", sum==545);
		
	}
	
	@Test
	void FloydWarshallTest(){
		
		setUpScenary2();
		
		g.makeWeightsMatrix();
		double[][] w = g.FloydWarshall(g.getWeightsMatrix());
		
		double[][] weights = {{0,7,4,10,3},{7,0,6,3,4},{4,6,0,7,2},{10,3,7,0,7},{3,4,2,7,0}};
		
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights.length; j++) {
				assertTrue("The value in i,j is no correct", w[i][j]==weights[i][j]);
			}
		}
	}
	

	
	

}
