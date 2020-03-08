package ir.hitelecom.accounting.repositories.stock;

import ir.hitelecom.accounting.entities.stock.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query(value = "from Product p where (:name = null or p.name like %:name%) and (:ptype = null or p.type=:ptype)")
    List<Product> search(@Param("name") String name, @Param("ptype") String type);
}
