package <PACKAGE>;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import <ENTITYIDTYPEIMPORT>;

public class <ENTITY>RepositoryImpl implements <ENTITY>RepositoryCustom {
    private final UniqueIdGenerator<<ENTITYIDTYPE>> generator;

    public <ENTITY>RepositoryImpl(UniqueIdGenerator<<ENTITYIDTYPE>> generator) {
        this.generator = generator;
    }

    @Override
    public <ENTITYID> nextId() {
        return new <ENTITYID>(generator.getNextUniqueId());
    }
}
