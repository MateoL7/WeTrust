package myCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
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
public class AdjacencyListGraph<V extends Comparable<V>> implements IGraph<V> {

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

	private double[][] weightsMatrix;

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
	public boolean searchVertex(V v) {
		return vertices.containsKey(v);
	}




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
			if (uList.get(i).getFirst().equals(v)) {
				uList.remove(i);
			}
		}

		List<Pair<V, Double>> vList = adjacencyLists.get(vValue).getSecond();

		for (int i = 0; i < vList.size(); i++) {
			if (vList.get(i).getFirst().equals(u)) {
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
			boolean c = false;
			for (int i = 0; i < adjacencyLists.get(uValue).getSecond().size(); i++) {
				if(adjacencyLists.get(uValue).getSecond().get(i).getFirst().equals(v)) {
					c=true;
				}
			}
			return c;
			// this returns if u connected and directed to v
		} else {
			
			boolean c = false;
			for (int i = 0; i < adjacencyLists.get(uValue).getSecond().size(); i++) {
				
				if(adjacencyLists.get(uValue).getSecond().get(i).getFirst().equals(v)) {
					c=true;
				}
			}
			
			boolean cc = false;
			for (int i = 0; i < adjacencyLists.get(vValue).getSecond().size(); i++) {
				if(adjacencyLists.get(vValue).getSecond().get(i).getFirst().equals(u)) {
					cc=true;
				}
			}
			
			
			return c&&cc;
			// in case the graph is not connected then both should be connected to each
			// other
		}

	}

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
				
				Collections.sort(uList);
				Collections.sort(vList);

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
	public double[][] Kruskal(double[][] weights) {
		int inf = Integer.MAX_VALUE;
		UnionFind<Integer> set = new UnionFind<>();

		double[][] MST = new double[weights.length][weights.length];

		for (int i = 0; i < weights.length; i++)
			set.makeSet(i);
		class obj {
			int A;
			int B;
			double P;

			obj(int a, int b, double weight) {
				A = a;
				B = b;
				P = weight;
			}

			int getA() {
				return A;
			}

			int getB() {
				return B;
			}

			double getP() {
				return P;
			}
		}
		ArrayList<obj> aristas = new ArrayList<>();
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights.length; j++) {
				double weight = weights[i][j];
				if (weight > 0 && weight < inf) {
					obj o = new obj(i, j, weight);
					aristas.add(o);
				}
			}
		}

		Comparator<obj> comparator = new Comparator<obj>() {

			public int compare(obj a, obj b) {
				if (a.getP() > b.getP())
					return 1;
				else if (a.getP() < b.getP())
					return -1;
				else
					return 0;
			}
		};

		aristas.sort(comparator);
		for (int i = 0; i < aristas.size(); i++) {
			obj arista = aristas.get(i);
			if (set.findSet(arista.getA()) != set.findSet(arista.getB())) {
				set.union(arista.getA(), arista.getB());
				MST[arista.getA()][arista.getB()] = arista.getP();
				MST[arista.getB()][arista.getA()] = arista.getP();
			}
		}
	
		return MST;
	}

	public void makeWeightsMatrix() {

		int size = adjacencyLists.size();
		weightsMatrix = new double[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i != j)
					weightsMatrix[i][j] = Double.MAX_VALUE;
				else
					weightsMatrix[i][j] = 0.0;
			}
		}

		for (int i = 0; i < size; i++) {

			Pair<V, List<Pair<V, Double>>> vPair = adjacencyLists.get(i);
			int v = vertices.get(vPair.getFirst());
			List<Pair<V, Double>> vList = vPair.getSecond();

			for (int j = 0; j < vList.size(); j++) {
				int vA = vertices.get(vList.get(j).getFirst());
				double w = vList.get(j).getSecond();

				weightsMatrix[v][vA] = w;
			}
		}
	}

	public double[][] getWeightsMatrix() {
		return weightsMatrix;
	}
	
	@Override
	public double[][] FloydWarshall(double[][] w) {

		double dist[][] = w;
		int V = dist.length;
		int i, j, k;

		for (k = 0; k < V; k++) {

			for (i = 0; i < V; i++) {

				for (j = 0; j < V; j++) {

					if (dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}

		return dist;
	}

	@Override
	public String toString() {
		String msg = "";

		for (int i = 0; i < adjacencyLists.size(); i++) {
			msg += " " + adjacencyLists.get(i).getFirst() + " : ";
			for (int j = 0; j < adjacencyLists.get(i).getSecond().size(); j++) {
				msg += "" + adjacencyLists.get(i).getSecond().get(j).getFirst() + ","
						+ adjacencyLists.get(i).getSecond().get(j).getSecond() + " ";
			}
			msg += "\n";
		}
		return msg;
	}

	@Override
	public ArrayList<V> BFS(V vertex) {
		// TODO Auto-generated method stub
		 //
		ArrayList<V> bfs = new ArrayList<V>();
		boolean[] visited = new boolean[adjacencyLists.size()];
		LinkedList<V> queue = new LinkedList<V>();
		visited[vertices.get(vertex)] = true; 
		queue.add(vertex);
		
		while(queue.size()!=0) {
			
			vertex = queue.poll();
			bfs.add(vertex);
			List<Pair<V,Double>> vList= adjacencyLists.get(vertices.get(vertex)).getSecond();
			for (int i = 0; i < vList.size(); i++) {
				vertex = vList.get(i).getFirst();
				int index = vertices.get(vertex);
				if(!visited[index]) {
					queue.add(vertex);
					visited[vertices.get(vertex)]=true;
				}
			}
			
		}
		
		return bfs;
	}



	@Override
	public void prim(double[][] matrix){	
		int[] mst = new int[matrix.length];	
		int[] weight = new int[matrix.length];	
		boolean[] inMst = new boolean[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			weight[i] = Integer.MAX_VALUE;
			inMst[i] = false;
		}		

		
		weight[0] = 0; 
		mst[0] = -1;			

		for (int i = 0; i < matrix.length-1; i++) {
			int u = minVertex(weight, inMst, matrix.length);
			inMst[u] = true;
			for (int j = 0; j < matrix.length; j++) {
				if(matrix[u][j] != 0 && inMst[j] == false && matrix[u][j] < weight[j]){
					mst[j] = u;
					weight[j] = (int) matrix[u][j];
				}
			}
		}
	}
	
	public int minVertex(int[] weight, boolean[] inMst, int vertices){
		int minValue = Integer.MAX_VALUE;
		int minVertex = -1;
		for (int i = 0; i < vertices; i++) {
			if(inMst[i] == false && weight[i] < minValue){
				minValue = weight[i];
				minVertex = i;
			}
		}
		return minVertex;
	}

	@Override
	public ArrayList<V> DFS(V vertex) {
		
		boolean[] visited = new boolean[adjacencyLists.size()];
		ArrayList<V> dfs = new ArrayList<V>();
		DFS(vertex, visited, dfs);
		return dfs;
	}
	
	public void DFS(V vertex, boolean[] visited, ArrayList<V> dfs){
		
		visited[vertices.get(vertex)]=true;
		dfs.add(vertex);
		
		List<Pair<V,Double>> vList= adjacencyLists.get(vertices.get(vertex)).getSecond();
		for (int i = 0; i < vList.size(); i++) {
			vertex = vList.get(i).getFirst();
			if(!visited[vertices.get(vertex)]) {
				DFS(vertex, visited, dfs);
			}
		}
		
	}

}
