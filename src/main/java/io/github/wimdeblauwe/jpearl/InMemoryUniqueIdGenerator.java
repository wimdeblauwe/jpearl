package io.github.wimdeblauwe.jpearl;

import java.util.UUID;

/**
 * A {@link UniqueIdGenerator} implementation that generates unique {@link UUID} instances.
 */
public class InMemoryUniqueIdGenerator implements UniqueIdGenerator<UUID> {
    @Override
    public UUID getNextUniqueId() {
        return UUID.randomUUID();
    }
}
