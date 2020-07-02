package io.github.wimdeblauwe.jpearl;

import java.io.Serializable;

/**
 * Interface for primary keys of entities.
 *
 * @param <T> the underlying type of the entity id
 */
public interface EntityId<T> extends Serializable {

    /**
     * The value of the underlying type
     *
     * @return the value
     */
    T getId();

    /**
     * Returns a string representation of the id (e.g. for usage in REST endpoints).
     *
     * @return a human friendly String representation of the id
     */
    String asString();
}
