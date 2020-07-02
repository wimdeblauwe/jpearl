package io.github.wimdeblauwe.jpearl;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractVersionedEntity<T extends EntityId<?>> extends AbstractEntity<T> {

    @Version
    private long version;

    protected AbstractVersionedEntity() {
    }

    public AbstractVersionedEntity(T id) {
        super(id);
    }

    public long getVersion() {
        return version;
    }
}
