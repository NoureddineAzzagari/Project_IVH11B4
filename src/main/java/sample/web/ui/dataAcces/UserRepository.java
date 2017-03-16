package sample.web.ui.dataAcces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.web.ui.domain.User.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
