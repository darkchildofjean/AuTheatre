package autheatre4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;

/**
 * 
 * @author Cole Burnham A generic list to use as a parent of any lists needed
 *         Based of Professor Dathan's ItemList.
 *
 * @param <T> - the type of item
 * @param <K> - the key of item
 */
public class GenericList<T extends Matchable<K>, K> implements Serializable {
	// a generic linked list.
	private List<T> list = new LinkedList<T>();

	/**
	 * A generic search function. Can be edited for the types/key. Replace T
	 * with the type used in the children
	 * 
	 * @param key - the key
	 * @return item or null
	 */
	public T search(K key) {
		for (T item : list) {
			if (item.matches(key)) {
				return item;
			} // else return null
		}
		return null;

	}

	/**
	 * A generic add function to add items to a list In child classes - replace
	 * T with the item type.
	 * 
	 * @param item - item to be added
	 * @return item added.
	 */
	public boolean add(T item) {
		return list.add(item);
	}

	/**
	 * A generic add function to remove items from a list. In child classes -
	 * replace T with the item type.
	 * 
	 * @param item - item to be removed
	 * @return true if exists
	 */
	public boolean remove(T item) {
		return list.remove(item);
	}

	/**
	 * A generic iterator to iterate through a collection Replace T with the
	 * type to iterate through
	 * 
	 * @return - the iteration of the list.
	 */
	public Iterator<T> iterator() {
		return list.iterator();
	}
}
