package <PACKAGE>;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface <ENTITY>Repository extends CrudRepository<<ENTITY>, <ENTITYID>>, <ENTITY>RepositoryCustom {
}
