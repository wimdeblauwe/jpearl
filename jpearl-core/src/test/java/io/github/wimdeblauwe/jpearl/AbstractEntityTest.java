package io.github.wimdeblauwe.jpearl;


import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractEntityTest {

    @Test
    void entityIsEqualIfIdIsEqual() {
        UUID id = UUID.randomUUID();
        MyEntity1 entity1 = new MyEntity1(new MyEntity1Id(id));
        MyEntity1 entity1Again = new MyEntity1(new MyEntity1Id(id));

        assertThat(entity1Again).isEqualTo(entity1);
    }

    @Test
    void entityIsNotEqualIfIdIsNotEqual() {
        MyEntity1 entity1 = new MyEntity1(new MyEntity1Id(UUID.randomUUID()));
        MyEntity1 entity1Again = new MyEntity1(new MyEntity1Id(UUID.randomUUID()));

        assertThat(entity1Again).isNotEqualTo(entity1);
    }

    @Test
    void entityIsNotEqualIfIdIsEqualButClassIsDifferent() {
        UUID id = UUID.randomUUID();
        MyEntity1 entity = new MyEntity1(new MyEntity1Id(id));
        MyEntity2 entityOtherClass = new MyEntity2(new MyEntity2Id(id));

        assertThat(entityOtherClass).isNotEqualTo(entity);
    }

    private static class MyEntity1 extends AbstractEntity<MyEntity1Id> {
        public MyEntity1() {
        }

        public MyEntity1(MyEntity1Id id) {
            super(id);
        }
    }

    private static class MyEntity1Id extends AbstractEntityId<UUID> {
        protected MyEntity1Id() {
        }

        public MyEntity1Id(UUID id) {
            super(id);
        }
    }

    private static class MyEntity2 extends AbstractEntity<MyEntity2Id> {
        public MyEntity2() {
        }

        public MyEntity2(MyEntity2Id id) {
            super(id);
        }
    }

    private static class MyEntity2Id extends AbstractEntityId<UUID> {
        protected MyEntity2Id() {
        }

        public MyEntity2Id(UUID id) {
            super(id);
        }
    }
}
