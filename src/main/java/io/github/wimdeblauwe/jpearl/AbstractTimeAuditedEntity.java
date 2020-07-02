package io.github.wimdeblauwe.jpearl;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractTimeAuditedEntity<T extends EntityId<?>> extends AbstractEntity<T> {
// ------------------------------ FIELDS ------------------------------

    @CreatedDate
    @NotNull
    private ZonedDateTime createdDate;

    @LastModifiedDate
    @NotNull
    private ZonedDateTime lastModifiedDate;

// --------------------------- CONSTRUCTORS ---------------------------

    protected AbstractTimeAuditedEntity() {
    }

    public AbstractTimeAuditedEntity(T id) {
        super(id);
    }

// -------------------------- OTHER METHODS --------------------------

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    protected void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    protected void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
