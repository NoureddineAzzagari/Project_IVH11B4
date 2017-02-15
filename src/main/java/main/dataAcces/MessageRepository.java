package main.dataAcces;

import org.springframework.data.repository.CrudRepository;
import main.Domain.Message;

/**
 * Created by ids on 14-2-2017.
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
}
