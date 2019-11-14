package myCollections;


/**
 * This interface has the minimum and general features that a graph should implement no matter which would be its representation.
 * @author AED Class # 003 // 2019
 * @version 1.0 - 10/2019
 * @param <V> Abstract data type which represents an object from a natural problem that is going to be modeled as a vertex in a graph representation of the problem
 */
public interface IGraph<T> {
	
	/**
	 * Adds a vertex to the graph
	 * @param v The new vertex to be added
	 * @return True if was added and false if it was already in the graph
	 */
	public boolean addVertex(T v);
	
	/**
	 * Adds a weighted edge to the graph
	 * If the graph is directed the connection will be from U to V
	 * <pre> U and V have to exist in the graph
	 * @param u a vertex within the graph
	 * @param v a vertex within the graph
	 * @param w	is the weight of the edge
	 */
	public void addEdge(T u, T v, int w);
	
	/**
	 * Removes a vertex within the graph
	 * @param v A vertex to be removed of the graph
	 * @return True if the vertex was removed or false if the vertex didn't exist
	 */
	public boolean removeVertex(T v);
	
	/**
	 * Removes an edge within the graph
	 * <pre> U and V are within the graph
	 * @param u A vertex connected with V
	 * @param v A vertex connected with U
	 */
	public void removeEdge(T u, T v);
	
	/**
	 * Check if U and V are connected
	 * <pre> U and V are within the graph
	 * @param u Is a vertex
	 * @param v Is a vertex
	 * @return True if U and V are connected or false if they're not
	 */
	public boolean areConnected(T u, T v);
	
	/**
	 * Search for an specific vertex in the graph
	 * @param id Is the identification of the vertex
	 * @return The vertex matching the id
	 */
	public T search(int id);
}
