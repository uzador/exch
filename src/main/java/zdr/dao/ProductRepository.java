package zdr.dao;

import org.springframework.data.repository.CrudRepository;
import zdr.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
