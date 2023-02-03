package io.github.wimdeblauwe.jpearl;


import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract super class for entity id's.
 *
 * @param <T> the type that the id encapsulates (E.g. UUID, Long, ...)
 */
@MappedSuperclass
public abstract class AbstractEntityId<T extends Serializable> implements Serializable, EntityId<T> {
    private T id;

    protected AbstractEntityId() {
    }

    protected AbstractEntityId(T id) {
        this.id = Objects.requireNonNull(id, "id should not be null");
    }

    @Override
    public T getId() {
        return id;
    }

    @Override
    public String asString() {
        return id.toString();
    }

    @Override
    public final boolean equals(Object o) {
        boolean result = false;

        if (this == o) {
            result = true;
        } else if (o == null) {
            return false;
        } else if (o.getClass().equals(getClass())) {
            AbstractEntityId<?> other = (AbstractEntityId<?>) o;
            result = Objects.equals(getId(), other.getId());
        }

        return result;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s]", getClass().getSimpleName(), getId());
    }
}
