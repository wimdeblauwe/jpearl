package io.github.wimdeblauwe.jpearl;


import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryUniqueIdGeneratorTest {

    @Test
    void testGenerateUniqueId() {
        InMemoryUniqueIdGenerator generator = new InMemoryUniqueIdGenerator();
        UUID id1 = generator.getNextUniqueId();
        UUID id2 = generator.getNextUniqueId();
        assertThat(id1).isNotEqualTo(id2);
    }
}
