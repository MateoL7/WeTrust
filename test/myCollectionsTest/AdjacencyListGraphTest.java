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
		g.addEdge('B', 'D', 1);
		g.addEdge('B', 'E', 1);
		g.addEdge('D', 'E', 1);
		g.addEdge('D', 'C', 1);
		g.addEdge('C', 'E', 1);
		g.addEdge('C', 'A', 1);
		g.addEdge('A', 'E', 1);
		
	}

	@Test
	void BFSTest() {
		
		setUpScenary2();
		ArrayList<Character> arrayL = g.BFS('B');
		assertTrue("Bfs is not searching in the tree in the correct order", arrayL.toString().equals("[B, D, E, C, A]"));
		
	}

}
