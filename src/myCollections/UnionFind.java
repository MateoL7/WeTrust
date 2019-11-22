package myCollections;
import java.util.*;

/**
 * modela una coleccion de conjuntos disyuntos
 * @author JuanDavid
 *
 * @param <E> elemento de un conjunto
 */


public class UnionFind<E>{
	
	private HashMap<E,HashMap<E,E>> conjuntos;
	
	public UnionFind(){		
		conjuntos = new HashMap<E,HashMap<E,E>>();
	}
	
	/**
	 * crea un conjunto disyunto
	 * @param element elemento que representara a el conjunto
	 */
	
public boolean makeSet(E element){		
if(findSet(element) == null) {		// reviso que no este ya en algun conjunto	
		HashMap<E,E> map = new HashMap<>();
		map.put(element, element);	// agrego al conjunto el elemento representante
		conjuntos.put(element, map);	// guardo en mi coleccion de conjuntos el conjunto creado
		return true;
	} else {
		return false;
}

}
	
	/**
	 * busca al representante del conjunto al que pertence el elemento
	 * @param element
	 * @return el representante de un conjunto,  null si el elemento no pertenecia a ningun conjunto
	 */
	
public E findSet(E element){		
/*si no hay conjunto, el elemento no era el representante
		* se debe ver si el elemento esta dentro de alguno de los conjuntos existentes
		*/
if(conjuntos.get(element) == null) {			
E representante = null;
boolean found = false;	
		for(Map.Entry<E,HashMap<E,E>> entry : conjuntos.entrySet()) { // coleccion de conjuntos				
				if(!found) {
					if(entry.getValue().containsKey(element)) { // si el conjunto actual tiene el elemento buscado
						representante = entry.getKey();	// se asigna el representante del conjunto que contiene al elemento busccado
						found = true;
					}
				}else {
					break; 
					}
			}		
			return representante;
		}else { // el elemento era el representate
			return element;
		}
	}
	
	/**
	 * retorna los elementos de un conjunto
	 * @param elemento de un conjunto
	 * @return los elementos del conjunto, null si no hay un conjunto
	 */
	
	
public ArrayList<E> getSet(E elemento) {		
E representante = findSet(elemento); 
// busco el representate del conjunto al que pertenece el elemento		
	if(representante != null) { //  si existe un conjunto asociado al elemento			
		Set<E> conjunto = conjuntos.get(representante).keySet();
          	ArrayList<E> elementos = new ArrayList<>();
	for(E key  : conjunto)
		elementos.add(key);	
	
	return elementos;	
	} else
		
	return null;
	}
	

/**
 * une mete todos los elementos del conjunto 2 al conjunto 1
 * @param conjunto1 conjunto 1
 * @param conjunto2 conjunto 2
 * @return true si se unieron los conjuntos
 */	


public boolean union(E conjunto1, E conjunto2) {		
E c1 = findSet(conjunto1);
E c2 = findSet(conjunto2);		
if(c1 != null && c2 != null) {		// los conjuntos a unirse existen			
	HashMap <E,E> conjuntoPrincipal = conjuntos.get(c1);
	Set<E> elementos = conjuntos.get(c2).keySet();	//guardo los elementos del conjunto que se va a unir al otro		
	conjuntos.remove(c2);			
	for(E e : elementos)
		conjuntoPrincipal.put(e, c1);		
	return true;
	
} else
	
	return false;		
}


public int size() {
return conjuntos.size();
}
	
	
}
