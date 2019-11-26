package myCollectionsTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

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
		g.addEdge('B', 'D', 1.0);
		g.addEdge('B', 'E', 1.0);
		g.addEdge('D', 'E', 1.0);
		g.addEdge('D', 'C', 1.0);
		g.addEdge('C', 'E', 1.0);
		g.addEdge('C', 'A', 1.0);
		g.addEdge('A', 'E', 1.0);
		
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
	
	

}
