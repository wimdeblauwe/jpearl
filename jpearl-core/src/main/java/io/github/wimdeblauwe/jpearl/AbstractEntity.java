package io.github.wimdeblauwe.jpearl;


import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * Abstract super class for entities. We are assuming that early primary key
 * generation will be used.
 *
 * @param <T> the type of {@link EntityId} that will be used for this entity
 */
@MappedSuperclass
public abstract class AbstractEntity<T extends EntityId<?>> implements Entity<T> {
// ------------------------------ FIELDS ------------------------------

    @EmbeddedId
    private T id;

// --------------------------- CONSTRUCTORS ---------------------------

    protected AbstractEntity() {
    }

    public AbstractEntity(T id) {
        this.id = Objects.requireNonNull(id, "id should not be null");
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    @Override
    public T getId() {
        return id;
    }

// ------------------------ CANONICAL METHODS ------------------------

    /**
     * Equals implementation for entities should use the <code>id</code> only
     * to verify equality (As opposed to value objects that should compare all properties). For this reason,
     * this method has been made final. This works fine with Hibernate because we use early primary key generation,
     * so the <code>id</code> is always present.
     *
     * @param obj the object to compare this object to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public final boolean equals(Object obj) {
        boolean result = false;

        if (this == obj) {
            result = true;
        } else if (obj.getClass().equals(getClass())) {
            AbstractEntity<?> other = (AbstractEntity<?>) obj;
            result = Objects.equals(getId(), other.getId());
        }

        return result;
    }

    /**
     * HashCode implementation for entities should use the <code>id</code> only to calculate the hash code (As opposed
     * to value objects that should use all properties). For this reason, this method has been made final. This works
     * fine with Hibernate because we use early primary key generation, so the <code>id</code> is always present.
     *
     * @return the hash code
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s]", getClass().getSimpleName(), getId());
    }
}
