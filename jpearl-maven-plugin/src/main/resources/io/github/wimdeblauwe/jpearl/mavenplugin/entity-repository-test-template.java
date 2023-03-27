package <PACKAGE>;

import <DATAJPATESTANNOTATIONIMPORT>;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import <ENTITYIDTYPEIMPORT>;

import static org.assertj.core.api.Assertions.assertThat;

<DATAJPATESTANNOTATION>
class <ENTITY>RepositoryTest {
    private final <ENTITY>Repository repository;
    private final JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    <ENTITY>RepositoryTest(<ENTITY>Repository repository,
                           JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void validatePreconditions() {
        assertThat(repository.count()).isZero();
    }

    @Test
    void testSave<ENTITY>() {
        <ENTITYID> id = repository.nextId();
        repository.save(new <ENTITY>(id));

        entityManager.flush();

        <ENTITYIDTYPE> idInDb = jdbcTemplate.queryForObject("SELECT id FROM <ENTITYTABLE>", <ENTITYIDTYPE>.class);
        assertThat(idInDb).isEqualTo(id.getId());
    }
}
