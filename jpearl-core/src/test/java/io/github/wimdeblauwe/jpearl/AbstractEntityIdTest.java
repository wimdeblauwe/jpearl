package io.github.wimdeblauwe.jpearl;


import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractEntityIdTest {

    @Test
    void entityIdIsEqualIfIdIsEqual() {
        UUID id = UUID.randomUUID();
        MyEntityId1 entityId1 = new MyEntityId1(id);
        MyEntityId1 entityId1Again = new MyEntityId1(id);

        assertThat(entityId1Again).isEqualTo(entityId1);
    }

    @Test
    void entityIdIsNotEqualIfIdIsNotEqual() {
        MyEntityId1 entityId1 = new MyEntityId1(UUID.randomUUID());
        MyEntityId1 entityId1Again = new MyEntityId1(UUID.randomUUID());

        assertThat(entityId1Again).isNotEqualTo(entityId1);
    }

    @Test
    void entityIdIsNotEqualIfOtherIsNull() {
        MyEntityId1 entityId1Again = new MyEntityId1(UUID.randomUUID());

        assertThat(entityId1Again).isNotEqualTo(null);
    }

    @Test
    void entityIdIsNotEqualIfIdIsEqualButClassIsDifferent() {
        UUID id = UUID.randomUUID();
        MyEntityId1 entityId = new MyEntityId1(id);
        MyEntityId2 entityIdOtherClass = new MyEntityId2(id);

        assertThat(entityIdOtherClass).isNotEqualTo(entityId);
    }

    private static class MyEntityId1 extends AbstractEntityId<UUID> {
        protected MyEntityId1() {
        }

        public MyEntityId1(UUID id) {
            super(id);
        }
    }

    private static class MyEntityId2 extends AbstractEntityId<UUID> {
        protected MyEntityId2() {
        }

        public MyEntityId2(UUID id) {
            super(id);
        }
    }

}
