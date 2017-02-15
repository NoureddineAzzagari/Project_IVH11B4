package main.dataAcces;

import org.springframework.data.repository.CrudRepository;
import main.Domain.Order;

/**
 * Created by ids on 8-2-2017.
 */
public interface IOrderRepository extends CrudRepository<Order, Long> {
}
