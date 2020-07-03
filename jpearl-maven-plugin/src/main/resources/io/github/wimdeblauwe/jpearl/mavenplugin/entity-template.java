package <PACKAGE>;

import io.github.wimdeblauwe.jpearl.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class <ENTITY> extends AbstractEntity<<ENTITYID>> {

    /**
     * Default constructor for JPA
     */
    protected <ENTITY>() {
    }

    public <ENTITY>(<ENTITYID> id) {
        super(id);
    }
}
