package autheatre4;

/**
 * 
 * @author Cole Burnham
 *
 * @param <T> - the item to be matched.
 * based off Prof Dathan's matchable interface
 */
public interface Matchable<K> {
/**
 * Generic function to replace the equals function
 * Edit per use in implementing classes
 * @param key - the key to be matched
 * @return - true or false based on match
 */
    public boolean matches(K key);
}
