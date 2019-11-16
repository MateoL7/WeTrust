package myCollections ;

/**
 * Class Pair
 * @author Mateo Loaiza
 * @author Nicolas Penagos
 * @author Juan Jose Calderon
 * 
 *
 */

public class Pair <F,S>{
	
	private F first;
	private S second;
	
	public Pair(F first, S second) {
		this.first=first;
		this.second=second;
	}
	
	/**
	 * @return the first value
	 */
	public F getFirst() {
		return first;
	}
	
	/**
	 * @return the second value
	 */
	public S getSecond() {
		return second;
	}
	
	/**
	 * @param f the new first value
	 */
	public void setFirst(F f) {
		first=f;
	}
	
	/**
	 * @param s the new second value
	 */
	public void setSecond(S s) {
		second=s;
	}

}