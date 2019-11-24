package myCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;

import myExceptions.EmployeeAlreadyCreatedException;
import myExceptions.EmployeeNotRegisteredException;

/**
 * An Object that models a graph using an Adjacency Matrix.
 *
 * @param <V> the type of vertex in the graph
 * @author AED Class # 003 // 2019
 * @version 1.0 - 10/2019
 */
public class AdjacencyMatrixGraph<T extends Comparable<T>> implements IGraph<T> {

	/**
	 * The length of the matrix when using the default Constructor.
	 */
	private static final int DEFAULT_CAPACITY = 25;

	/**
	 * The last index in the matrix at which a vertex exists.
	 */
	private int size; //logic size

	/**
	 * The matrix itself.
	 */
	private double[][] adjacencyMatrix;

	/**
	 * A Map that accesses any vertex in the graph through its index in the matrix.
	 */
	private Map<Integer, T> vertices;

	/**
	 * A Map that uses any vertex as a key to access its corresponding index in the matrix.
	 */
	private Map<T, Integer> verticesIndices;

	/**
	 * Constructs a new, empty matrix of double values of default length, along with two Map objects that interconnect
	 * vertices to their indices in the matrix and indices in the matrix to their vertices.
	 */
	public AdjacencyMatrixGraph() {
		initialize(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs a new, empty matrix of double values of default length, along with two Map objects that interconnect
	 * vertices to their indices in the matrix and indices in the matrix to their vertices.
	 *
	 * @param capacity the initial size of the adjacency matrix
	 */
	public AdjacencyMatrixGraph(int capacity) {
		initialize(capacity);
	}

	/**
	 * Auxiliary method used by the Constructor to set values to the class' fields. Creates the adjacency matrix.
	 *
	 * @param capacity the initial size of the adjacency matrix
	 */
	private void initialize(int capacity) {
		size = 0;
		adjacencyMatrix = new double[capacity][capacity];
		vertices = new HashMap<>();
		verticesIndices = new HashMap<>();
	}

	/**
	 * Adds the given vertex to the graph.
	 *
	 * @param v The new vertex to be added
	 * @return true if the vertex did not already exist in the graph
	 */
	@Override
	public boolean addVertex(T v) throws EmployeeAlreadyCreatedException {
		boolean added = false;
		if(!vertices.containsValue(v)) {
			if(vertices.isEmpty()) {
				vertices.put(0, v);
				verticesIndices.put(v, 0);
			}else {
				int newKey = vertices.size();
				vertices.put(newKey, v);
				verticesIndices.put(v, newKey);
			}
			added = true;
		}else {
			throw new EmployeeAlreadyCreatedException();
		}
		return added;
	}

	/**
	 * Adds a directed edge from vertex 'u' to vertex 'v' if isDirected with weight 'w'. Adds an edge between vertices
	 * 'u' and 'v' of weight 'w' otherwise.
	 *
	 * @param u a vertex within the graph
	 * @param v a vertex within the graph
	 * @param w is the weight of the edge between 'u' and 'v'
	 * @throws EmployeeNotRegisteredException 
	 */
	@Override
	public void addEdge(T u, T v, double w) throws EmployeeNotRegisteredException {
		if(verticesIndices.get(u) != null && verticesIndices.get(v)!= null) {
			int x = verticesIndices.get(u);
			int y = verticesIndices.get(v);
			if(x == y) {
				adjacencyMatrix[x][y] = 0;
				adjacencyMatrix[y][x] = 0;	
			}
			adjacencyMatrix[x][y] = w;
			adjacencyMatrix[y][x] = w;
		}
		else {
			throw new EmployeeNotRegisteredException();
		}
	}

	/**
	 * Attempts to remove vertex 'v' from the graph.
	 *
	 * @param v vertex to be removed from the graph
	 * @return true if 'v' exists in the graph. False otherwise
	 */
	@Override
	public boolean removeVertex(T v) {
		boolean removed = false;
		if(verticesIndices.containsKey(v)) {
			int key = verticesIndices.get(v);
			verticesIndices.remove(v);
			vertices.remove(key);
			removed = true;
		}
		return removed;
	}

	/**
	 * Removes a directed edge from vertex 'u' to vertex 'v' if the graph is directed. Otherwise, removes an undirected
	 * edge between vertices 'u' and 'v'.
	 *
	 * @param u vertex connected with V
	 * @param v vertex connected with U
	 */
	@Override
	public void removeEdge(T u, T v) {
		adjacencyMatrix[(int) u][(int) v] = 0;
	}

	/**
	 * If the graph is undirected, determines if vertices 'u' and 'v' share an edge. Otherwise, determines if there is
	 * a directed edge from 'u' to 'v' and 'v' to 'u'.
	 *
	 * @param u a vertex
	 * @param v a vertex
	 * @return true if and only if said edge exists
	 */
	@Override
	public boolean areConnected(T u, T v) {
		boolean exist = (u != null && v != null);
		if(exist) {
			int uValor = verticesIndices.get(u);
			int vValor = verticesIndices.get(v);

			return adjacencyMatrix[uValor][vValor] < 800 && adjacencyMatrix[vValor][uValor] < 800 && u != v;

		}else {
			return exist;
		}

	}

	public List<T> adjacents(T vertex){
		List<T> adjacents = new ArrayList<T>();
		for(Map.Entry<Integer, T> entry : vertices.entrySet()){
			if(areConnected(vertex, entry.getValue())) {
				adjacents.add(entry.getValue());
			}
		}
		Collections.sort(adjacents);
		return adjacents;
	}

	/**
	 * Returns the weight matrix containing the weights of every edge, directed or not, between all vertices in the
	 * graph.
	 *
	 * @return the matrix containing all weights in the graph
	 */
	public double[][] getMatrix() {
		return adjacencyMatrix;
	}
	
	public void setMatrix(double[][] matrix) {
		adjacencyMatrix = matrix;
	}

	@Override
	public T search(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<T> BFS(T vertex) {
		ArrayList<T> result = new ArrayList<>();
		Queue<T> options = new LinkedList<>();



		result.add(vertex);
		options.add(vertex);

		while(!options.isEmpty()) {
			T actual = options.poll();
			if(!searchAux(result, actual)) {
				result.add(actual);
			}
			ArrayList<T> adj = (ArrayList<T>) adjacents(actual);
			for(int i = 0; i < adj.size(); i++) {
				if(!searchAux(result, adj.get(i))) {
					options.add(adj.get(i));	
				}
			}
		}
		return result;
	}
	@Override
	public ArrayList<T> DFS(T vertex){
		ArrayList<T> result = new ArrayList<>();
		Stack<T> options = new Stack<>();
	
		
		options.push(vertex);
		
		while(!options.empty()) {
			ArrayList<T> visited = new ArrayList<>();
			T actual = options.pop();
			if(!searchAux(result, actual)) {
				result.add(actual);
			}
			ArrayList<T> adj = (ArrayList<T>) adjacents(actual);
			for(int i = 0; i < adj.size(); i++) {
				T max = maxAux(adj, visited);
				visited.add(max);
				if(!searchAux(result, max)) {
					options.push(max);	
				}
			}
		}
		
		return result;
	}

	private boolean searchAux(ArrayList<T> ar, T ver) {
		boolean found = false;
		for(int i = 0; i < ar.size() && !found; i++) {
			if(ver.equals(ar.get(i))) found = true;
		}
		return found;
	}
	
	private T maxAux(ArrayList<T> ar, ArrayList<T> vis) {
		T max = ar.get(0);
		for(int i =0; i < ar.size(); i++) {
			if(ar.get(i).compareTo(max) > 0 && !searchAux(vis, ar.get(i))) {
				max = ar.get(i);
			}
		}
		return max;
	}


	@Override
	public double[][] FloydWarshall(double[][] W) {

		int n = W.length;
		double[][] D = W;
		double v = 0;

		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {

					if(j != k || i != k) {

						if(D[i][k] != Integer.MAX_VALUE && D[k][j] != Integer.MAX_VALUE) {

							v = D[i][k] + D[k][j];

							if(D[i][j] > v) D[i][j] = v;

						}

					}

				}
			}
		}

		return D;

	}
	@Override
	public double[][] Kruskal(double[][] weights){
		int inf = Integer.MAX_VALUE;
		UnionFind<Integer> set = new UnionFind<>();

		double[][] MST = new double[weights.length][weights.length];		

		for(int i = 0; i < weights.length; i++)
			set.makeSet(i);		
		class obj {			
			int A;
			int B;
			double P;			
			obj(int a, int b, double weight){
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
		for(int i = 0; i < weights.length;  i++) {
			for(int j = 0; j < weights.length; j++) {
				double weight = weights[i][j];
				if(weight > 0 && weight < inf) {
					obj o = new obj(i, j, weight);
					aristas.add(o);
				}
			}
		}	

		Comparator<obj> comparator = new Comparator<obj>() {			

			public int compare(obj a, obj b) {
				if(a.getP() > b.getP())
					return  1;
				else if (a.getP() < b.getP())
					return -1;
				else
					return 0;
			}
		};	


		aristas.sort(comparator);		
		for(int i = 0; i < aristas.size(); i++) {
			obj arista = aristas.get(i);
			if(set.findSet(arista.getA()) != set.findSet(arista.getB())) {
				set.union(arista.getA(), arista.getB());
				MST[arista.getA()][arista.getB()] = arista.getP();
				MST[arista.getB()][arista.getA()] = arista.getP();
			}
		}
		//		System.out.println(set.size());
		return MST;
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
	public void prim(double[][] matrix){	
		int[] mst = new int[matrix.length];	//lista de adyacencia
		int[] weight = new int[matrix.length];	// pesos del mst
		boolean[] inMst = new boolean[matrix.length];	// si ya estan conectados en el arbol		// inicio los pesos en valores maximos y conexiones en el arbol en false
		for (int i = 0; i < matrix.length; i++) {
			weight[i] = Integer.MAX_VALUE;
			inMst[i] = false;
		}		

		// se hace prim desde el vertice 0
		weight[0] = 0; 	// peso cero
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
}