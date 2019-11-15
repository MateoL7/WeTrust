package myCollections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class models a graph using an Adjacency list
 * @author AED Class # 003 // 2019
 * @version 1.0 - 10/2019
 * @param <V> Abstract data type which represents an object from a natural problem that is going to be modeled as a vertex in a graph representation of the problem
 */
public class AdjacencyListGraph<V> implements IGraph<V>{
	
	/**
	 * Map with all the vertices within the graph.
	 * Key of the map is the Vertex and Value is the position of the vertex in the adjacencyList
	 */
	private Map<V,Integer> vertices;	
	
	/**
	 * A list for each Vertex within the graph which has a list with all its adjacent Vertices 
	 */
	private List<List<Pair<V, Integer>>> adjacencyLists;
	
	/**
	 * Property that say if a graph is directed or not
	 */
	private boolean isDirected;
	
	/**
	 * Basic constructor that is initialized with default values
	 */
	public AdjacencyListGraph() {
		initialize();
	}

	/**
	 * Constructor that gets the value for "isDirected" attribute.
	 * True if the graph is Directed or false if it's Indirected
	 * @param id value to set "isDirected"
	 */
	public AdjacencyListGraph(boolean id) {
		initialize();
		isDirected = id;
	}
	
	/**
	 * Initializes all the data structures for this graph.
	 * Set "isDirected" attribute in false
	 */
	private final void initialize() {
		isDirected = false;
		adjacencyLists = new ArrayList<List<Pair<V, Integer>>>();
		vertices = new HashMap<V, Integer>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean addVertex(V v) {
		boolean added = false;
		// Check if the vertex is not on the map already
		if(!searchVertex(v)) {
			@SuppressWarnings("unchecked")
			// Create a new empty list for that vertex
			// Get the position for this new vertex
			List<Pair<V, Integer>> vList = (List<Pair<V, Integer>>) new ArrayList<Pair<V, Integer>>();
			int index = adjacencyLists.size();
			// Add the vertex to the map
			vertices.put(v, index);
			// Add the vertex empty list to the adjacencyLists
			adjacencyLists.add(vList);
			vList.add(new Pair<V, Integer>(v, 0));
			// Change the value to true indicating that it was possible to add the vertex
			added = true;
		}
		return added;
	}

	/**
	 * checks if a vertex is within the graph
	 * @param v Vertex to be searched
	 * @return True if found or false if not
	 */
	private boolean searchVertex(V v) {
		return vertices.containsValue(v);
	}
	
	



	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean removeVertex(V v) {
		
		// first looks if the vertex exists
		if(vertices.containsKey(v)) {
			
			// remove the existing list which represents the adjacent vertices of the vertex to remove
			adjacencyLists.remove(vertices.get(v));
		
			// remove any existing connection to the vertex
			for(int i=0; i<adjacencyLists.size(); i++) {
				if(adjacencyLists.get(i).contains(v)) adjacencyLists.get(i).remove(i);
			}
			
			// removes the vertex form the map
			vertices.remove(v);
			
			return true;
			
		}else {
			return false;
		}
		
	}

	@Override
	public void removeEdge(V u, V v) {
		// TODO Auto-generated method stub
	}
	/*
	@Override
	public List<V> vertexAdjacent(V v) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public boolean areConnected(V u, V v) {
		
		int uValor = vertices.get(u);
		int vValor = vertices.get(v);
		
//		return adjacencyLists.get(uValor).contains(v) || adjacencyLists.get(uValor).contains(v);
//		This return exists in case there is no need of being specific about the direction
		
		if(isDirected) {
			return adjacencyLists.get(uValor).contains(v);
			// this returns if u connected and directed to v
		}else {
			return adjacencyLists.get(uValor).contains(v) && adjacencyLists.get(vValor).contains(u);
			// in case the graph is not connected then both should be connected to each other
		}
		
	}
	/*
	@Override
	public double[][] weightMatrix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		return isDirected;
	}
	
	/**
	 * 
	 * @return all the vertices within the graph as a map data structure
	 */
	public Map<V, Integer> getVertices(){		
		return vertices;		
	}
	
	/**
	 *
	 * @return The graph. A list with lists of vertices and its adjacent vertices
	 */
	public List<List<Pair<V, Integer>>> getAdjacencyList(){		
		return adjacencyLists;		
	}
	/*
	@Override
	public int getIndex(V u) {
		return vertices.get(u);
	}

	@Override
	public int getVertexSize() {
		return vertices.size();
	}	*/

	@Override
	public void addEdge(V u, V v, int w) {
		// TODO Auto-generated method stub
		
		//	System.out.println("u "+u);
		int ValueU = vertices.get(u);
	//	System.out.println("v "+v);
		int ValueV = vertices.get(v);
	//	System.out.println("ValueU "+ValueU);
	//	System.out.println("ValueV "+ValueV);
		if(!isDirected) {
		
	//		System.out.println("is Directed");
			adjacencyLists.get(ValueU).add(new Pair<V, Integer>(v, w));
	//		System.out.println(adjacencyLists.get(ValueU));
			adjacencyLists.get(ValueV).add(new Pair<V, Integer>(u, w));
		
		}else {
			//adjacencyLists.get(ValueU).add(v);
		
		}
		
	}

	@Override
	public V search(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String printGrahp() {
		String graphR = "";
		for (int i = 0; i < adjacencyLists.size(); i++) {
			ArrayList<Pair<V, Integer>> arrayL = (ArrayList<Pair<V, Integer>>) adjacencyLists.get(i);
			for (int j = 0; j < arrayL.size(); j++) {
				if(j==0){
				 graphR+=""+arrayL.get(j).getFirst()+","+arrayL.get(j).getSecond()+" : ";
				}else {
					 graphR+=""+arrayL.get(j).getFirst()+","+arrayL.get(j).getSecond()+" ";
				}
			}
			graphR += "\n";
		}
		
		return graphR;
	}
}

