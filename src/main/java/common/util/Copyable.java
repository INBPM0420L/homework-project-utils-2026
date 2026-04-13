package common.util;

/**
 * Represents an object capable of creating a deep copy of itself.
 *
 * @param <T> the type of the object
 */
public interface Copyable<T extends Copyable<T>> {

    /**
     * {@return a deep copy of this object}
     */
    T copy();

}
