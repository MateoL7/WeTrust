package myCollections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * An Object that models a graph using an Adjacency Matrix.
 *
 * @param <V> the type of vertex in the graph
 * @author AED Class # 003 // 2019
 * @version 1.0 - 10/2019
 */
public class AdjacencyMatrixGraph<T> implements IGraph<T> {

	/**
	 * The length of the matrix when using the default Constructor.
	 */
	private static final int DEFAULT_CAPACITY = 10;

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
	public boolean addVertex(T v) {
		boolean added = false;

		return added;
	}

	/**
	 * Adds a directed edge from vertex 'u' to vertex 'v' if isDirected with weight 'w'. Adds an edge between vertices
	 * 'u' and 'v' of weight 'w' otherwise.
	 *
	 * @param u a vertex within the graph
	 * @param v a vertex within the graph
	 * @param w is the weight of the edge between 'u' and 'v'
	 */
	@Override
	public void addEdge(T u, T v, int w) {
		int x = verticesIndices.get(u);//TODO: check pre-conditions
		int y = verticesIndices.get(v);
		adjacencyMatrix[x][y] = w;
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
		int uValor = verticesIndices.get(u);//TODO: check if 'u' and 'v' exist in the graph
		int vValor = verticesIndices.get(v);

		return adjacencyMatrix[uValor][vValor] != 0 && adjacencyMatrix[vValor][uValor] != 0;

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

	@Override
	public T search(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}