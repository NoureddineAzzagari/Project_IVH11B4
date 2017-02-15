package main.dataAcces;

import org.springframework.data.repository.CrudRepository;
import main.Domain.ProductCatalog;

/**
 * Created by ids on 8-2-2017.
 */
public interface IProductCatalogRepository extends CrudRepository<ProductCatalog, Long> {
}
