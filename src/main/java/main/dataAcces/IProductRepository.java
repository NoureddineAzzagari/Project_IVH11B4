package main.dataAcces;

import org.springframework.data.repository.CrudRepository;
import main.Domain.Product;

/**
 * Created by ids on 8-2-2017.
 */
public interface IProductRepository extends CrudRepository<Product, Long> {
}
