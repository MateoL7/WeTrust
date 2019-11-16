package myCollections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class models a graph using an Adjacency list
 * 
 * @author AED Class # 003 // 2019
 * @version 1.0 - 10/2019
 * @param <V> Abstract data type which represents an object from a natural
 *        problem that is going to be modeled as a vertex in a graph
 *        representation of the problem
 */
public class AdjacencyListGraph<V> implements IGraph<V> {

	/**
	 * Map with all the vertices within the graph. Key of the map is the Vertex and
	 * Value is the position of the vertex in the adjacencyList
	 */
	private Map<V, Integer> vertices;

	/**
	 * A list for each Vertex within the graph which has a list with all its
	 * adjacent Vertices
	 */
	private List<Pair<V, List<Pair<V, Double>>>> adjacencyLists;

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
	 * Constructor that gets the value for "isDirected" attribute. True if the graph
	 * is Directed or false if it's Indirected
	 * 
	 * @param id value to set "isDirected"
	 */
	public AdjacencyListGraph(boolean id) {
		initialize();
		isDirected = id;
	}

	/**
	 * Initializes all the data structures for this graph. Set "isDirected"
	 * attribute in false
	 */
	private final void initialize() {
		isDirected = false;
		// adjacencyLists = new ArrayList<List<Pair<V, Integer>>>();
		adjacencyLists = new ArrayList<Pair<V, List<Pair<V, Double>>>>();
		vertices = new HashMap<V, Integer>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addVertex(V v) {
		boolean added = false;
		// Check if the vertex is not on the map already
		if (!searchVertex(v)) {
			// Create a new empty Pair for that vertex
			Pair<V, List<Pair<V, Double>>> vPair = new Pair<>(v, new ArrayList<>());
			// Get the position for this new vertex
			int index = adjacencyLists.size();
			// Add the vertex to the map
			vertices.put(v, index);
			// Add the Pair list to the adjacencyLists
			adjacencyLists.add(vPair);
			// Change the value to true indicating that it was possible to add the vertex
			added = true;
		}
		return added;
	}

	/**
	 * checks if a vertex is within the graph
	 * 
	 * @param v Vertex to be searched
	 * @return True if found or false if not
	 */
	private boolean searchVertex(V v) {
		return vertices.containsValue(v);
	}

	/*
	 * 
	 * @Override public void addEdge(V u, V v, double w) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 */

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean removeVertex(V v) {

		// first looks if the vertex exists
		if (vertices.containsKey(v)) {
			// remove the existing list which represents the adjacent vertices of the vertex
			// to remove
			int index = vertices.get(v);
			adjacencyLists.remove(index);
			vertices.remove(v);

			// remove any existing connection to the vertex
			for (int i = 0; i < adjacencyLists.size(); i++) {
				Pair<V, List<Pair<V, Double>>> currentPair = adjacencyLists.get(i);
				for (int j = 0; j < currentPair.getSecond().size(); j++) {
					if (currentPair.getSecond().get(i).getFirst().equals(v)) {
						currentPair.getSecond().remove(j);
					}
				}
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	public void removeEdge(V u, V v) {
		// TODO Auto-generated method stub
		
		int uValue = vertices.get(u);
		int vValue = vertices.get(v);
		
		List<Pair<V, Double>> uList = adjacencyLists.get(uValue).getSecond();
		
		for (int i = 0; i < uList.size(); i++) {
			if(uList.get(i).getFirst().equals(v)) {
				uList.remove(i);
			}
		}
		
		
		List<Pair<V, Double>> vList = adjacencyLists.get(vValue).getSecond();
		
		for (int i = 0; i < vList.size(); i++) {
			if(vList.get(i).getFirst().equals(u)) {
				vList.remove(i);
			}
		}
	}
	/*
	 * @Override public List<V> vertexAdjacent(V v) { // TODO Auto-generated method
	 * stub return null; }
	 */

	@Override
	public boolean areConnected(V u, V v) {

		int uValue = vertices.get(u);
		int vValue = vertices.get(v);

//		return adjacencyLists.get(uValor).contains(v) || adjacencyLists.get(uValor).contains(v);
//		This return exists in case there is no need of being specific about the direction

		if (isDirected) {
			return adjacencyLists.get(uValue).getSecond().contains(v);
			// this returns if u connected and directed to v
		} else {
			return adjacencyLists.get(uValue).getSecond().contains(v)
					&& adjacencyLists.get(vValue).getSecond().contains(u);
			// in case the graph is not connected then both should be connected to each
			// other
		}

	}

	/*
	 * @Override public double[][] weightMatrix() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public boolean isDirected() { // TODO Auto-generated method stub
	 * return isDirected; }
	 * 
	 * /**
	 * 
	 * @return all the vertices within the graph as a map data structure
	 */
	public Map<V, Integer> getVertices() {
		return vertices;
	}

	/**
	 *
	 * @return The graph. A list with lists of vertices and its adjacent vertices
	 */
	public List<Pair<V, List<Pair<V, Double>>>> getAdjacencyList() {
		return adjacencyLists;
	}
	/*
	 * @Override public int getIndex(V u) { return vertices.get(u); }
	 * 
	 * @Override public int getVertexSize() { return vertices.size(); }
	 */

	@Override
	public void addEdge(V u, V v, double w) {
		// TODO Auto-generated method stub
		if (vertices.get(u) != null && vertices.get(v) != null) {

			int ValueU = vertices.get(u);
			int ValueV = vertices.get(v);

			Pair<V, List<Pair<V, Double>>> uPair = adjacencyLists.get(ValueU);
			Pair<V, List<Pair<V, Double>>> vPair = adjacencyLists.get(ValueV);

			List<Pair<V, Double>> uList = uPair.getSecond();
			List<Pair<V, Double>> vList = vPair.getSecond();

			if (!isDirected) {

				uList.add(new Pair<V, Double>(v, w));
				vList.add(new Pair<V, Double>(u, w));

			} else {
				uList.add(new Pair<V, Double>(v, w));
			}
		}
	}

	@Override
	public V search(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		String msg = "";

		for (int i = 0; i < adjacencyLists.size(); i++) {
			msg += " " + adjacencyLists.get(i).getFirst() + " : ";
			for (int j = 0; j < adjacencyLists.get(i).getSecond().size(); j++) {
				msg += "" + adjacencyLists.get(i).getSecond().get(j).getFirst() + ","
						+ adjacencyLists.get(i).getSecond().get(j).getSecond();
			}
			msg += "\n";
		}
		return msg;
	}


}

