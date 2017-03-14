package main.dataAcces;

import main.domain.User.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ids on 9-3-2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
