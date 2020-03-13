package ir.hitelecom.accounting.repositories.stock;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.entities.stock.Reservoir;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query(value = "from Product p where (:name = null or p.name like %:name%) and (:ptype = null or p.type=:ptype) and p.owner = :owner")
    List<Product> search(@Param("name") String name, @Param("ptype") String type, @Param("owner")User owner);

    Product findByReservoirAndName(Reservoir reservoir, String name);

    List<Product> findAll();

}
