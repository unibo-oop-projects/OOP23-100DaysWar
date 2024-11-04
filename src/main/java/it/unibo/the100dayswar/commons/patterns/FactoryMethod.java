package it.unibo.the100dayswar.commons.patterns;

/**
 * This interface represents a generic Factory Method pattern. Implementing this
 * interface allows the creation of a concrete factory method for various classes.
 *
 * @param <P> the type of the parameter passed to the constructor
 * @param <T> the class of the object returned
 */
public interface FactoryMethod<P, T> {
    /**
     * Creates an instance of {@code T} by passing {@code param} to the constructor.
     * 
     * @param param the parameter of type {@code P} to pass to the constructor
     * @return an instance of {@code T}
     */
    T create(P param);
}
